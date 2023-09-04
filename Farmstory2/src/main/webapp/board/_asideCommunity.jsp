<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
  		<div id="sub">
            <div><img src="/Farmstory2/images/sub_top_tit5.png" alt="COMMUNITY"></div>
            <section class="community">
                <aside>
                    <img src="/Farmstory2/images/sub_aside_cate5_tit.png" alt="커뮤니티"/>

                    <ul class="lnb">
                        <li class="${cate eq 'notice' ? 'on':'off'}"><a href="/Farmstory2/board/list.do?group=Community&cate=notice">공지사항</a></li>                        
                        <li class="${cate eq 'menu' ? 'on':'off'}"><a href="/Farmstory2/board/list.do?group=Community&cate=menu">오늘의 식단</a></li>                        
                        <li class="${cate eq 'chef' ? 'on':'off'}"><a href="/Farmstory2/board/list.do?group=Community&cate=chef">나도요리사</a></li>                        
                        <li class="${cate eq 'qua' ? 'on':'off'}"><a href="/Farmstory2/board/list.do?group=Community&cate=qua">1대1고객문의</a></li>                        
                        <li class="${cate eq 'faq' ? 'on':'off'}"><a href="/Farmstory2/board/list.do?group=Community&cate=faq">자주믇는질문</a></li>                        
                    </ul>

                </aside>
                <article>
                    <nav>
                        <img src="/Farmstory2/images/sub_nav_tit_cate5_tit3.png" alt="나도요리사"/>
                        <p>
                            HOME > 커뮤니티 > <em>나도요리사</em>
                        </p>
                    </nav>
