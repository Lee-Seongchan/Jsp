<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file ="../_header.jsp" %>
        <div id="sub">
            <div><img src="../images/sub_top_tit2.png" alt="MARKET"></div>
            <section class="market">
                <aside>
                    <img src="../images/sub_aside_cate2_tit.png" alt="장보기"/>

                    <ul class="lnb">
                        <li class="on"><a href="./market.do">장보기</a></li>
                    </ul>
                </aside>
                <article class="list">
                    <nav>
                        <img src="../images/sub_nav_tit_cate2_tit1.png" alt="장보기"/>
                        <p>
                            HOME > 장보기 > <em>장보기</em>
                        </p>
                    </nav>

                    <!-- 내용 시작 -->
                    <p class="sort">
		 				<a href="./list.do?type=0" class="${type==0 ? 'on':'off'}">전체<c:if test="${type==0}">(${total})</c:if>&nbsp;|</a>
		                <a href="./list.do?type=1" class="${type==1 ? 'on':'off'}">과일<c:if test="${type==1}">(${total})</c:if>&nbsp;|</a>
		                <a href="./list.do?type=2" class="${type==2 ? 'on':'off'}">야채<c:if test="${type==2}">(${total})</c:if>&nbsp;|</a>
		                <a href="./list.do?type=3" class="${type==3 ? 'on':'off'}">곡류<c:if test="${type==3}">(${total})</c:if>&nbsp;</a>
                    </p>
                    <table border="0">
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>
                                <a href="./view.do?pNo=${product.pNo}"><img src="/Farmstory2/thumb/${product.thumb1}" alt="사과 500g" class="thumb"></a>
                            </td>
                            <td>
			                    <c:choose>
			                    	<c:when test="${product.type==1 }">과일</c:when>
			                    	<c:when test="${product.type==2 }">야채</c:when>
			                    	<c:when test="${product.type==3 }">곡물</c:when>
			                    </c:choose>
							</td>
                            <td><a href="#">${product.pName}</a></td>
                            <td><strong>${product.price}</strong>원</td>
                        </tr>
                    </c:forEach>
                 
                    </table>

                    <p class="paging">
                   	<c:if test="${pageGroupStart > 1 }">
	                   <a href="/Farmstory2/market/list.do?type=0&page=1" class="prev">처음으로</a>
	                   <a href="/Farmstory2/market/list.do.do?type=0&page=${pageGroupStart -1}" class="prev">이전</a>
               		</c:if>

	                <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}" >
	                   <a href="/Farmstory2/market/list.do?type=0&page=${i}" class="num ${currentPage==i ? 'current':''}">${i}</a>
	                </c:forEach>

                	<c:if test="${pageGroupEnd < lastPageNum }">
	                   <a href="/Farmstory2/market/list.do?type=0&page=${pageGroupEnd +1 }" class="next">다음</a>
	                   <a href="/Farmstory2/market/list.do?type=0&page=${lastPageNum}" class="next">마지막으로</a>
                 	</c:if>
            		</p>

                    <!-- 내용 끝 -->

                </article>
            </section>

        </div>
        
<%@include file ="../_footer.jsp" %>