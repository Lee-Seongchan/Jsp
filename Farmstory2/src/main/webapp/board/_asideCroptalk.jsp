<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
    
    <div id="sub">
            <div><img src="/Farmstory2/images/sub_top_tit3.png" alt="CROP TALK"></div>
            <section class="croptalk">
                <aside>
                    <img src="/Farmstory2/images/sub_aside_cate3_tit.png" alt="농작물이야기"/>

                    <ul class="lnb">
                        <li class="${cate eq 'story' ? 'on':'off'}"><a href="/Farmstory2/board/list.do?group=Croptalk&cate=story">농작물이야기</a></li>
                        <li class="${cate eq 'grow' ? 'on':'off'}"><a href="/Farmstory2/board/list.do?group=Croptalk&cate=grow">텃밭가꾸기</a></li>
                        <li class="${cate eq 'school' ? 'on':'off'}"><a href="/Farmstory2/board/list.do?group=Croptalk&cate=shool">귀농학교</a></li>
                    </ul>				

                </aside>
                <article>
                    <nav>
                        <img src="/Farmstory2/images/sub_nav_tit_cate3_tit2.png" alt="텃밭가꾸기"/>
                        <p>
                            HOME > 농작물이야기 > <em>텃밭가꾸기</em>
                        </p>
                    </nav>