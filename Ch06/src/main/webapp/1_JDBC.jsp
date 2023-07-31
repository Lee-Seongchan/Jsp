<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<!-- 
			날짜 : 2023/07/28
			이름 : 이성찬
			내용 : JSP 데이터베이스 실습하기
		 -->
		 <script>
		 	
		 	window.onload = function(){
		 		
		 		const btn1 = document.getElementById("btn1");
		 		const spans = document.getElementsByTagName("span");
		 		
		 		btn1.onclick = function(){
		 			
		 			const xhr = new XMLHttpRequest();
		 			xhr.open("GET","/Ch06/data/json1.jsp");
		 			xhr.send();
		 			
		 			xhr.onreadystatechange = function(){
		 				if(xhr.readyState == XMLHttpRequest.DONE){
		 					if(xhr.status == 200){ // - 200 : 서버에 문서가 존재함. - 404 : 서버에 문서가 존재하지 않음.
		 						const data = JSON.parse(xhr.response);
		 						console.log("data : " + data);
		 						
		 						spans[0].innerText = data.uid;
		 						spans[1].innerText = data.name;
		 						spans[2].innerText = data.hp;
		 						spans[3].innerText = data.age;
		 					}
		 				}
		 			}  //onreadystatechange end
		 			
		 		} // btn1 onclick end
		 		
		 		const table = document.getElementsByTagName("table")[0];
		 		const btn2 = document.getElementById("btn2");
		 		btn2.addEventListener("click", function(){
		 			
		 			fetch("/Ch06/data/json2.jsp").then((response)=>{
		 				console.log(response);
	
		 				return response.json();
		 			})
		 			.then((data)=>{
		 				console.log(data)
		 				
		 				for(let user of data){
		 					console.log(user);
		 					const tr = document.createElement("tr");
		 					const td1 = document.createElement("td");
		 					const td2 = document.createElement("td");
		 					const td3 = document.createElement("td");
		 					const td4 = document.createElement("td");
		 				
		 					td1.innerText = user.uid;
		 					td2.innerText = user.name;
		 					td3.innerText = user.hp;
		 					td4.innerText = user.age;
		 					
		 					tr.appendChild(td1);
		 					tr.appendChild(td2);
		 					tr.appendChild(td3);
		 					tr.appendChild(td4);
		 					
		 					table.appendChild(tr);
		 				
		 				}
		 				
		 				
		 				});
		 		});
		 		
		 	
		 	}; //window.onload end 
		 
		 </script>
		 
		 
	</head>
	<body>
		 <h3>3.AJAX 실습</h3>
		 
		 
		 <h4>1.JDBC 실습</h4>
		 <%--절대 경로 --%>
		 <a href="/Ch06/user1/register.jsp">User1 CRUD 실습</a>
		 <a href="/Ch06/user2/register.jsp">User2 CRUD 실습</a>
		 <a href="/Ch06/user3/register.jsp">User3 CRUD 실습</a>
		 <a href="/Ch06/user4/register.jsp">User4 CRUD 실습</a>
		 <a href="/Ch06/user5/register.jsp">User5 CRUD 실습</a>
		 <a href="/Ch06/member/register.jsp">Member CRUD 실습</a>
		 
		 <h4>AJAX 실습</h4>
		 <button id = btn1>데이터 요청</button>
		 <p>
		 	아이디 : <span></span>
		 	이름 : <span></span>
		 	휴대폰 : <span></span>
		 	나이 : <span></span>
		 </p>
		 
		 <button id ="btn2" >>데이터 요청</button>
		 <table border = "1">
		 	<tr>
		 		<th>아이디</th>
		 		<th>이름</th>
		 		<th>휴대폰</th>
		 		<th>나이</th>
		 	</tr>
		 	
		 
		 
		 </table>
		 
	</body>
</html>