<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>팜스토리</title>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css"/>
    <link rel="stylesheet" href="/Farmstory2/css/style.css"/>
    <link rel="stylesheet" href="/Farmstory2/user/css/style.css"/>    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>    
 

</head>
<body>
    <div id="container">
        <header>
            <a href="/Farmstory2" class="logo"><img src="/Farmstory2/images/logo.png" alt="로고"/></a>
            <p>
                <a href="/Farmstory2">HOME |</a>
                <a href="/Farmstory2/user/login.do">로그인 |</a>
                <a href="/Farmstroy2/user/register.do">회원가입 |</a>
                <a href="/Farmstroy2/admin/">관리자 |</a>
                <a href="#">고객센터</a>
            </p>
            <img src="/Farmstory2/images/head_txt_img.png" alt="3만원 이상 무료배송"/>
            
            <ul class="gnb">
                <li><a href="/Farmstory2/introduction/hello.do">팜스토리소개</a></li>
                <li><a href="/Farmstory2/market/list.do"><img src="/Farmstory2/images/head_menu_badge.png" alt="30%"/>장보기</a></li>
                <li><a href="/Farmstory2/board/list.do?group=Croptalk&cate=story">농작물이야기</a></li>
                <li><a href="/Farmstory2/board/list.do?group=Event&cate=event">이벤트</a></li>
                <li><a href="/Farmstory2/board/list.do?group=Community&cate=notice">커뮤니티</a></li>
            </ul>
        </header>