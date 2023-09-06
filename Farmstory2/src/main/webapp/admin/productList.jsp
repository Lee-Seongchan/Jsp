<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file ="./_header.jsp" %>
        <main>
               <%@ include file="./_aside.jsp" %>
            <section id="productList">
                <nav>
                    <h3>상품목록</h3>
                </nav>

                <article>

                    <table border="0">
                        <tr>
                            <th><input type="checkbox" name="all"/></th>
                            <th>사진</th>
                            <th>상품번호</th>
                            <th>상품명</th>
                            <th>구분</th>
                            <th>가격</th>
                            <th>재고</th>
                            <th>등록일</th>
                        </tr>
                        <c:forEach var="product" items="${products}">
                        <tr>
                            <td><input type="checkbox" name=""/></td>
                            <td><img src="./images/sample_${product.thumb1}.jpg" class="thumb" alt="샘플1"></td>
                            <td>${product.pNo}</td>
                            <td>${product.pName}</td>
                            <td>${product.type}</td>
                            <td>${product.price}원</td>
                            <td>${product.stock}</td>
                            <td>${product.rdate}</td>
                        </tr>
                        </c:forEach>
                    </table>

                    <p>
                        <a href="#" class="productDelete">선택삭제</a>
                        <a href="/Farmstory2/admin/productRegister.do" class="productRegister">상품등록</a>
                    </p>
                    
                    <p class="paging">
                        <a href="#"><</a>
                        <a href="#" class="on">[1]</a>
                        <a href="#">[2]</a>
                        <a href="#">[3]</a>
                        <a href="#">[4]</a>
                        <a href="#">[5]</a>
                        <a href="#">></a>
                    </p>

                </article>

                
            </section>
        </main>
<%@include file ="./_footer.jsp" %>