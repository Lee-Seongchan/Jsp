/**
 * 
 */

$(function(){
	
		
		//아이디 중복체크
		const inputUid = document.getElementsByName("uid")[0];
		const uidResult =  document.getElementsByClassName("uidResult")[0];
		const btnCheckUid = document.getElementById("btnCheckUid");
		
		if(btnCheckUid != null){
			btnCheckUid.click =  function(){
			
			const uid = inputUid.value;
			
			//아이디 유효성 검사
			if(!uid.match(reUid)){
				uidResult.innerText = "유효한 아이디가 아닙니다.";
				uidResult.style.color = "red";
				isUidOk = false;
				return;
			}
			
		}
		
	
			
			// 서버 전송
			const xhr = new XMLHttpRequest(); //json요청
			xhr.open("GET", "/Jboard2/user/checkUid.do?uid=" + inputUid.value);
			xhr.send();
			
			xhr.onreadystatechange = function(){
				
				if(xhr.readyState == XMLHttpRequest.DONE){
					if(xhr.status == 200){
				
						const data = JSON.parse(xhr.response);
						if(data.result > 0){
							uidResult.innerText = "이미 사용중인 아이디 입니다.";
							uidResult.style.color = "red";
							isUidOk = false;
						}else{
							uidResult.innerText = '사용 가능한 아이디 입니다.';
							uidResult.style.color = 'green';
							isUidOk = true;
						}
					}
				} //readyState end
			}// onreadystatechange end
		}//onclick end
		
		//Nick 중복체크
		$("#btnCheckNick").click(function(){
			
			const nick = $("input[name=nick]").val();
			console.log('nick : ' + nick);

			//별명 유효성 검사
			if(!nick.match(reNick)){
				$('.nickResult').css('color', 'red').text('유효한 별명이 아닙니다.');
				isNickOk = false;
				return;				
			}
			
			console.log('here1...');
			
			$.ajax({
				url:"/Jboard2/user/checkNick.do?nick="+nick,
				type:'get',
				dataType:'json',
				success:function(data){
					
					console.log('here2... ' + data.result);
					
					if(data.result > 0){
						$(".nickResult").css("color","red").text("이미 사용중인 별명입니다.");
						isNickOk = false;
					}else{
						$(".nickResult").css("color","green").text("사용 가능한 별명입니다.");
						isNickOk = true;
					}
				}
			});
			
		}); //btnCheckNick end
		
		
		//휴대폰 중복 체크
		$("input[name=hp]").focusout(function(){
			
			const hp = $(this).val();
			if(!hp.match(reHp)){
				$(".resultHp").text("휴대폰 번호가 유효하지 않습니다.");
				isHpOk = false;
				return;
			}		
					
			const url = "/Jboard2/user/checkHp.do?hp="+hp
			
			$.get(url, function(result){
				
				const data = JSON.parse(result);
				
				if(data.result > 0){
					$(".resultHp").css("color","red").text("이미 사용중인 휴대폰 입니다.");
					isHpOk = false;
				}else{
					$(".resultHp").css("color","green").text("사용 가능한 휴대폰 입니다.");
					isHpOk = true;
				}
	
			});
		});
	});