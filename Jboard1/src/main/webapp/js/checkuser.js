/**
 * 
 */
	//사용자 개인정보 중복 체크
	  	$(function(){
	  		//아이디 중복체크
	  		$("#btnCheckUid").click(function() {
				const uid = $("input[name=uid]").val();
				const jsonData = {
					"uid":uid
				}
	  				$.ajax({
	  					url:"/Jboard1/user/checkUid.jsp",
	  					type:"GET",
	  					data: jsonData,
	  					datatype:"json",
	  					success: function(data) {
	  						
	  						if(data.result >= 1){
	  							$(".resultId").css("color", "red").text("이미 사용중인 아이디 입니다.");
	  						}else{
	  							$(".resultId").css("color", "red").text("사용 가능한 아이디 입니다.");
	  						}
	  					}
	  				});	
	  		}); //아이디 중복체크 끝
	  		
	  		// 닉네임 중복체크
	  	 	$("input[name=nick]").focusout(function() {
				
				//입력데이터 가져오기
				const nick = $(this).val();
				//console.log("nick : " + nick);
				
				//json 생성
				const jsonData = {
						"nick": nick
				};
				//데이터 전송
				$.get("/Jboard1/user/checkNick.jsp", jsonData, function(data){
					console.log(data);
					if(data.result >= 1){
						$('.resultNick').css('color', 'red').text('이미 사용중인 별명 입니다');
					}else{
						$('.resultNick').css("color","green").text("사용 가능한 별명입니다.");
					}
				});
			}); // 닉네임 중복 체크 끝
			
			//이메일 중복 체크
			const email = document.getElementsByName("email")[0];
			email.onfocusout = function(){
				//alert("확인");
				
				//입력데이터 가져오기 
				const email = this.value;
				console.log("email : " + email);
				
				const xhr = new XMLHttpRequest();
				xhr.open("GET", '/Jboard1/uder/checkEamil.jsp='+email)
				xhr.send();
				
				xhr.onreadystatechange = function(){
					if(xhr.readyState == XMLHpptReqest.DONE){
	  					if(xhr.status == 200){
	  						const data = JSON.parse(xhr.response);
	  						console.log("data : " + data);
	  						
	  						const resultEmail = document.getElementById("resultEmail");
	  						
	  						if(data.result >= 1){
	  							alert("이미 사용중인 이메일 입니다.");
	  							resultEmail.innerText = "이미 사용중인 이메일 입니다.";
	  							resultEmail.style.color = "red";
	  						}else{
	  							alert("사용 가능한 이메일 입니다.");
	  							resultEmail.innerText = "사용 가능한 이메일 입니다.";
	  							resultEmail.style.color = "green";
	  						}
	  					}
					}
				} // onreadystatechange end
			}	//email.onfocusout end
			
			
			document.getElementsByName('hp')[0].addEventListener("focusout", function(){
				
				
				const url = "Jboard1/user/checkHp.jsp?hp="+this.value;
				fetch("url")
					.then((response)=> response.json)
					.then(data=>{
						
						console.log(data);
						const resultHp = document.getElementById("resultHp");
						
						if(data.result >= 1){
							resultHp.innerText = "이미 사용중인 휴대폰 번호 입니다.";
							resultHp.style.color = "red";
						}else{
							resultHp.innerText = "사용 가능한 휴대폰 번호 입니다.";
							resultHp.style.color = "green";
						}
					})
			}) //휴대폰 중복 체크 끝
			
	  	}); //사용자 개인정보 체크 끝
	  	
	