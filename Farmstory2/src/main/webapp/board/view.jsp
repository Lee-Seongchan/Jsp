<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		
<script>
	
	$(function(){
		$("#btnComment").click(function(e){
		
			e.preventDefault();
			
			const parent  = $("#formComment > input[name=parent]").val();
			const content = $("#formComment > textarea[name=content]").val();
			const writer  = $("#formComment > input[name=writer]").val();
			const nick = '${sessUser.nick}';
			
			const jsonData = {
					"parent" : parent,
					"content" : content,
					"writer":writer
			};
		
			console.log("jsonData : " + JSON.stringify(jsonData));
		
			$.ajax({
				url : "/Farmstory2/board/comment.do",
				type : "post",
				data : jsonData,
				dataType : "json",
				success : function(data){
					console.log(data);
				
					if(data.result > 0){
						alert("댓글이 등록되었습니다.");
						
						$("#formComment > textarea[name=content]").val(''); //댓글 등록 후 초기화
						
						//동적화면 생성
						const dt = new Date();
						const year = dt.getYear().toString().substr(1, 4);
						const month = dt.getMonth()+1;
						const date = dt.getDate();
						const now = year + "-" + month + "-" + date;
						
						const article = `<article>	//동적으로 태그를 생성할 때 ``를 씀
											<form id="formComment" action="#" method="post">
												<span class="nick">\${nick}</span>
												<span class="date">\${now}</span>
												<textarea name="content">\${content}</textarea>
												<div>
													<a href="#" class="delete">삭제</a>
													<a href="#" class="modify">수정</a>
												</div>	
											</form>
										 </article>`;
						$(".commentList").append(article);
										 
					}else{
						alert("댓글 등록이 실패했습니다.");
					}
				}
				
				
			});//ajax end
		})//btnComment end
		
		//댓글 삭제
		//document는 내장객체
		$(document).on("click", ".remove", function(e){
			e.preventDefault();
			
			//alert("클릭");
			const no = $(this).data("no");
			const article = $(this).parent().parent();
			
			console.log("no = " + no);
			
			const jsonData = {
					"kind" : "REMOVE",
					"no" : no
			}
			
			$.ajax({
				url : "/Farmstory2/board/comment.do",
				type : "GET",
				data : jsonData,
				dataType : "json",
				success : function(data){
					console.log(data);
					
					if(data.result > 0){
						alert("댓글이 삭제 되었습니다.");
					
						//화면처리 
						article.remove();
					}
					
				}
			})
			
			
		})
		
	
	})//function end
</script>

<script>

</script>
		
<jsp:include page="./_aside${group}.jsp"/>		
		
			<section class="view">
			    <h3>글보기</h3>
			    
			    <table>
			        <tr>
			            <td>제목</td>
			            <td><input type="text" name="title" value="${article.title}" readonly/></td>
			        </tr>
			       <c:if test="${article.file > 0}">
			        <tr>
			            <td>첨부파일</td>
			            <td>
			                <a href="/Farmstory2/board/fileDownload.do?fno=${article.fileDto.fno}">${article.fileDto.oriName}</a>
			                <span>${article.fileDto.download}회 다운로드</span>
			            </td>
			        </tr>
			       </c:if> 
			        <tr>
			            <td>내용</td>
			            <td>
			                <textarea name="content" readonly>${article.content}</textarea>
			            </td>
			        </tr>
			    </table>
			    <div>
			        <a href="/Farmstory2/board/delete.do?group=${group}&cate=${cate}&no=${article.no}" class="btnDelete">삭제</a>
			        <a href="/Farmstory2/board/modify.do?group=${group}&cate=${cate}&no=${article.no}" class="btnModify">수정</a>
			        <a href="/Farmstory2/board/list.do?group=${group}&cate=${cate}&pg=1" class="btnList">목록</a>
			    </div>
			    
			    <!-- 댓글리스트 -->
			    <section class="commentList">
			        <h3>댓글목록</h3>
			        <c:forEach var="comment" items="${comments}">
			        <article class="comment">
			        	<form id="formComment" action="#" method="post">
							<span>
								<span class="nick">${sessUser.nick }</span>
								<span class="date">${comment.rdate}</span>
							</span>
							<textarea name="comment" readonly>${comment.content}</textarea>
			             	
			             	<c:if test="${sessUser.getUid() eq comment.getWriter()}">
							<div>
								<a href="#" class="del remove" data-no="${comment.no}">삭제</a>
								<a href="/Farmstory2/board/list.do?group=${group}&cate=${cate}" class="can">취소</a>
								<a href="#" class="mod modify">수정</a>
							</div>
							</c:if>                
			            </form>
			        </article>
			        </c:forEach>
			        <c:if test="${empty comments}">
			        <p class="empty">등록된 댓글이 없습니다.</p>
			        </c:if>
			    </section>
			
			    <!-- 댓글입력폼 -->
			    <section class="commentForm">
			        <h3>댓글쓰기</h3>
	                <form id="formComment" action="#" method="post" enctype="multipart/form-data">
		            	<input type="hidden" name="parent" value="${article.no}"/>
		            	<input type="hidden" name="writer" value="${sessUser.uid}"/>
		            	<input type="hidden" name="nick" value="${sessUser.nick}"/>
			            <textarea name="content"></textarea>
			            <div>
			                <a href="#" class="btnCancel">취소</a>
			                <input type="submit" id="btnComment" class="btnWrite" value="작성완료"/>
			            </div>
			        </form>

			    </section>
			</section>
			<!-- 내용 끝 -->
        </article>
    </section>
</div>			
<%@ include file="../_footer.jsp" %>