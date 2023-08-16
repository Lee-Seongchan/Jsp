<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>팜스토리::커뮤니티</title>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body>
    <div id="container">
        <header>
            <a href="../index.html" class="logo"><img src="../images/logo.png" alt="로고"/></a>
            <p>
                <a href="#">HOME |</a>
                <a href="#">로그인 |</a>
                <a href="#">회원가입 |</a>
                <a href="#">고객센터</a>
            </p>
            <img src="../images/head_txt_img.png" alt="3만원 이상 무료배송"/>
            
            <ul class="gnb">
                <li><a href="#">팜스토리소개</a></li>
                <li><a href="#"><img src="../images/head_menu_badge.png" alt="30%"/>장바구니</a></li>
                <li><a href="#">농작물이야기</a></li>
                <li><a href="#">이벤트</a></li>
                <li><a href="#">커뮤니티</a></li>
            </ul>
        </header>

        <div id="sub">
            <div><img src="../images/sub_top_tit5.png" alt="COMMUNITY"></div>
            <section class="community">
                <aside>
                    <img src="../images/sub_aside_cate5_tit.png" alt="커뮤니티"/>

                    <ul class="lnb">
                        <li><a href="./notice.html">공지사항</a></li>
                        <li><a href="./menu.html">오늘의식단</a></li>
                        <li class="on"><a href="./chef.html">나도요리사</a></li>
                        <li><a href="./qna.html">1:1고객문의</a></li>
                        <li><a href="./faq.html">자주묻는질문</a></li>
                    </ul>

                </aside>
                <article>
                    <nav>
                        <img src="../images/sub_nav_tit_cate5_tit3.png" alt="나도요리사"/>
                        <p>
                            HOME > 커뮤니티 > <em>나도요리사</em>
                        </p>
                    </nav>

                    <!-- 내용 시작 -->
                    <img src="../images/board_list.png" alt="">

                    <!-- 내용 끝 -->

                </article>
            </section>

        </div>
        
        
<%@ include file="../_footer.jsp" %>  