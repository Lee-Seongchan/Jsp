/**
 * 
 */
	//이메일 인증
	$(function(){
	
		let preventDoubleClick = false;
		
		$("#btnEmailCode").click(function(){
			
			//서버로 전송 ajax			
			const type = $("input[name=type]").val();
			const name = $("input[name=name]").val();
			const email = $("input[name=email]").val();
			
			console.log('here1');
	
			const jsonData = {
					"type":type,
					"name":name ,
					"email":email
			}; //jsonData end
			
			
			if(preventDoubleClick){
				return;
			}
			
			preventDoubleClick = true;
			
			$(".resultEmail").text("인증코드 전송 중 입니다. 잠시만 기다려세요.");
			$('.resultEmailForId').text('인증코드 전송 중 입니다. 잠시만 기다리세요...');
			
			setTimeout(function(){
				
				$.ajax({
					url:"/Jboard2/user/authEmail.do",
					type:"GET",
					data: jsonData,
					dataType: "json",
					success:function(data){
						console.log(data);
						
						if(data.result > 0){
								$(".resultEmail").css("color","red").text("이미 사용중인 이메일 입니다.");
								isEmailOk = false;
								
								if(data.status > 0){
									$(".resultEmailForId").css("color","green").text("이메일을 확인 후 인증코드를 확인하세요.");
									$('input[name=auth]').prop('disabled', false);
								}else{
									$(".resultEmailForId").css("color","red").text("인증코드 전송이 실패했습니다. 잠시후 다시 시도하십시오.");
								}			
										
							}else{
								if(data.status > 0){
									$(".resultEmail").css("color","green").text("이메일을 확인 후 인증코드를 확인하세요.");
									$(".auth").show();
									$("input[name=email]").attr("readonly",true); //이메일 코드 발송 후 이메일 수정 불가
								}else{
									$(".resultEmail").css("color","red").text("인증코드 전송이 실패했습니다. 잠시후 다시 시도하십시오.");
									$(".resultEmailForId").css("color","red").text("해당하는 사용자, 이메일이 일치하지 않습니다.")
								}			
							}	
						preventDoubleClick = false;   //이메일 발송 후 다시 false로 전환
					} //success end
				}); //ajax end
			
			},1000); //setTimeout
		});	//#btnEmailCode end
		
		//인증 확인 버튼
		$("#btnEmailAuth").click(function(){
			
			const code = $("input[name=auth]").val();
			const jsonData = {
					"code" : code
			}
			
			$.ajax({
				url:"/Jboard2/user/authEmail.do",
				type:"POST",
				data:jsonData,
				dataType:"json",
				success:function(data){
					
					console.log(data);
					
					if(data.result > 0){
						$(".resultEmail").css("color","green").text("이메일 인증이 완료 되었습니다.");
						$('.resultEmailForId').css('color', 'green').text('이메일 인증이 완료 되었습니다.');
						isEmailOk = true;
					}else{
						$(".resultEmail").css("color","red").text("이메일 인증이 실패 했습니다. 다시 시도하십시오");
						$('.resultEmailForId').css('color', 'red').text('이메일 인증이 실패 했습니다.다시 시도하십시요.');
						isEmailOk = false;
					}
				}//success end
				
			}); //ajax end 
		});	//btnEmailAuthend
	}); //function end