<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file ="../_header.jsp" %>

<script>
//폼 데이터 검증결과 상태변수
let isUidOk   = false;
let isPassOk  = false;
let isNameOk  = false;
let isNickOk  = false;
let isEmailOk = false;
let isHpOk    = false;

// 데이터 검증에 사용하는 정규표현식
let reUid   = /^[a-z]+[a-z0-9]{4,19}$/g;
let rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
let reName  = /^[가-힣]{2,10}$/ 
let reNick  = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
let reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
let reHp    = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;

	$(function(){
		//아이디 검사
		$("input[name=uid]").keydown(function(){
			$(".resultId").text('');
			isUidOk = false;
		})
		
		
		//비밀번호 검사
		
		$("input[name=pass2]").focusout(function(){
			
			const pass1 = $("input[name=pass1]").val();
			const pass2 = $("input[name=pass2]").val();

			if(pass1 == pass2){
				
				if(pass1.match(rePass)){
					
					$(".resultPass").css("color","green").text("사용할 수 있는 비밀번호 입니다.");
					isPasskOk = true;
					
				}else{
					
					$(".resultPass").css("color","red").text("비밀번호는 숫자,영문, 특수문자 조합 5자리 이상이어야 합니다.");
					isPasskOk = false;				
				}
				
			}else{
				$(".resultPass").css("color","red").text("비밀번호가 일치하지 않습니다.");
				isPasskOk = false;		
			}
			
		});
		
		
		//이름 검사
		$("input[name=name]").focusout(function(){
			
			const name = $(this).val();
			
			if(name.match(reName)){
				$(".resultName").text('');
				isNameOk = true;
				
			}else{
				$('.resultName').css('color', 'red').text('유효한 이름이 아닙니다.');
				isNameOk = false;
			}
			
		});
	
		// 최종 전송
		$('#formUser').submit(function(){
			
			if(!isUidOk){
				alert('아이디를 확인 하십시요.');
				return false; // 폼 전송 취소	
			}
			
			if(!isPassOk){
				alert('비밀번호를 확인 하십시요.');
				return false; // 폼 전송 취소	
			}
			
			if(!isNameOk){
				alert('이름를 확인 하십시요.');
				return false; // 폼 전송 취소	
			}
			
			if(!isNickOk){
				alert('별명을 확인 하십시요.');
				return false; // 폼 전송 취소	
			}
			
			if(!isEmailOk){
				alert('이메일을 확인 하십시요.');
				return false; // 폼 전송 취소	
			}
			
			if(!isHpOk){
				alert('휴대폰을 확인 하십시요.');
				return false; // 폼 전송 취소	
			}
							
			return true; // 폼 전송 시작
		});
	
	});//function end 유효성 검사 끝



	$(function(){
		
		//아이디 중복 체크
		$("#btnCheckUid").click(function(){
			
			const uid = $("input[name=uid]").val();
			
			if(!uid.match(reUid)){
				$(".resultId").css("color","red").text("유효한 아이디가 아닙니다.");
				idUidOk = false;
				return;
			}
		
			const jsonData = {
				"uid" : uid
			}
			
			$.ajax({
				url:"/Farmstory2/user/checkUid.do",
				type:"GET",
				data : jsonData,
				dataType:"json",
				success:function(data){
					if(data.result >= 1){
						$(".resultId").css("color","red").text("이미 사용중인 아이디 입니다.");
						isUidOk = false;
					}else{
						console.log("data.result = " + data.result)
						$(".resultId").css("color","green").text("사용 가능한 아이디 입니다.")
					}
				}
				
			}); //ajax end
			
			
		});//#btnCheckUid end //아이디 중복 체크 끝
		
		//닉네임 중복체크
		$("input[name=nick]").focusout(function(){
			
			//입력데이터 가져오기
			const nick = $(this).val();
			
			if(!nick.match(reNick)){
				$(".resultNick").css("color","red").text("유효한 닉네임이 아닙니다.");
				isNickOk = false;
				return;
				
			}
			
			
			//JSON 생성
			const jsonData = {
					"nick" : nick
			}
			
			//데이터 전송
			$.getJSON("/Farmstory2/user/checkNick.do",jsonData,function(data){
		
				console.log(typeof data.result);
				if(data.result >= 1){
					$('.resultNick').css('color', 'red').text('이미 사용중인 별명 입니다.');
					console.log("1"+data.result);
					isNickOk = false;
					
				}else{
					$('.resultNick').css('color', 'green').text('사용 가능한 별명 입니다.');
					console.log("2"+data.result);
					isNickOk = true;
					
				}
				
			}).fail(function () {
			    // 요청 실패 시 실행되는 부분
			    console.error("서버 요청 실패");
			});
			
			
		});//input[name=nick] end 닉네임 중복체크 끝
		
		
		//휴대폰 중복체크
		$('input[name=hp]').focusout(function(){
			
			//입력데이터 받기
			const hp =$('input[name=hp]').val();
			const resultHp = $('span[id=resultHp]').text('');
		
			console.log("hp = " + hp); 
			
			if(!hp.match(reHp)){
				$("#resultHp").css("color","red").text('유효한 휴대폰 번호가 아닙니다');
				isHpOk = false;
				
			}
			
			const url = '/Farmstory2/user/checkHp.do?hp='+this.value;
			
			fetch(url)
				.then(response => response.json())
				.then(data => {
					console.log(data)
					
					if(data.result >=1 ){
						$("#resultHp").css("color", "red").text("이미 사용중인 휴대폰번호 입니다.");
						isHpOk = false;
					}else{
						$("#resultHp").css("color", "green").text("사용 가능한 휴대폰번호 입니다.");
						isHpOk = true;
					}	
				});
		
		})//input[name=hp] end 휴대폰 중복 체크 
	});//end

</script>

        <div id="user">
           	<section class="register">
				<form id="formUser" action="/Farmstory2/user/register.do" method="post">
					<table border="1">
						<caption>사이트 이용정보 입력</caption>
						<tr>
							<td>아이디</td>
							<td><input type="text" name="uid" placeholder="아이디 입력" />
								<button type="button" id="btnCheckUid">
									<img src="/Farmstory2/images/chk_id.gif" alt="">
								</button>
								<span class="resultId"></span>
							</td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="pass1"
								placeholder="비밀번호 입력" /></td>
						</tr>
						<tr>
							<td>비밀번호 확인</td>
							<td><input type="password" name="pass2"
								placeholder="비밀번호 확인 입력" /> <span class="resultPass"></span></td>
						</tr>
					</table>
					
					<table border="1">
						<caption>개인정보 입력</caption>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name" placeholder="이름 입력" />
								<span class="resultName"></span>
							</td>
							
						</tr>
						<tr>
							<td>별명</td>
							<td>
								<p>공백없이 한글, 영문, 숫자만 입력가능</p>
								<input type="text" name="nick"placeholder="별명 입력" />
								<span class="resultNick"></span>
							</td>
						</tr>
						<tr>
							<td>E-Mail</td>
							<td><input type="email" name="email" placeholder="이메일 입력" />
								<span id="resultEmail"></span>
							</td>
						</tr>
						<tr>
							<td>휴대폰</td>
							<td>
								<input type="text" name="hp" placeholder="- 포함 13자리 입력" minlength="13" maxlength="13" />
								<span id="resultHp"></span>
							</td>	
						</tr>
						<tr>
							<td>주소</td>
							<td>
								<div>
									<input type="text" name="zip" placeholder="우편번호" readonly />
									<button type="button" class="btnZip" onclick="zipcode()">
										<img src="../images/chk_post.gif" alt="">
									</button>
								</div>
								<div>
									<input type="text" name="addr1" placeholder="주소를 검색하세요."
										readonly />
								</div>
								<div>
									<input type="text" name="addr2" placeholder="상세주소를 입력하세요." />
								</div>
							</td>
						</tr>
					</table>

					<div>
						<a href="/Farmstory2/user/login.do" class="btnCancel">취소</a> <input type="submit"
							class="btnSubmit" value="회원가입" />
					</div>
				</form>
			</section>
        </div>
<%@include file ="../_footer.jsp" %>