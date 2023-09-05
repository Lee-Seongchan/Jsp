<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="./_aside${group}.jsp"/>
		
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
		
		
	})

</script>
		
		
		
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
			        <article class="comment">
			        	<form id="formComment" action="#" method="post">
							<span>
								<span>닉네임</span>
								<span>23-09-04</span>
							</span>
							<textarea name="comment" readonly>댓글내용</textarea>
			             
							<div>
								<a href="./delete.do?no=${article.no}" class="del">삭제</a>
								<a href="./list.do?group=${group}&cate=${cate}" class="can">취소</a>
								<a href="./modify.do?group=${group}&cate=${cate}" class="mod">수정</a>
							</div>                
			            </form>
			        </article>
			        <p class="empty">등록된 댓글이 없습니다.</p>
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