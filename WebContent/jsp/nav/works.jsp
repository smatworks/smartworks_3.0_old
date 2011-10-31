<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="net.smartworks.*"%>
<ul class="navi_tit">
	<li class='js_collapse_slide'>&gt; <fmt:message
			key="nav.works.my_works" />
	</li>
	<li class="nav_srch">
		<div class="srch">
			<input id="" class="input js_auto_complete" type="text"
				title="<fmt:message key='search.search_work'/>"
				placeholder="<fmt:message key='search.search_work'/>"
				href="layouts/search/work_list.jsp" />
			<button title="<fmt:message key='search.search'/>" onclick=""></button>
		</div>
		<div style="display: none"></div>
	</li>
</ul>


<div class="navi_list js_collapsible">
	<!-- 내부 메뉴 -->
	<div class="categ_link js_tab_work">
		<a href="jsp/nav/my_favorite_works.jsp" class="current"><fmt:message
				key="nav.works.my_favorite_works" /> </a> <a
			href="jsp/nav/my_recent_instances.jsp" class=""><fmt:message
				key="nav.works.my_recent_instances" /> </a> <a
			href="jsp/nav/my_all_works.jsp" class=""><fmt:message
				key="nav.works.my_all_works" /> </a>
	</div>
	<div id='my_works'>
		<jsp:include page="/jsp/nav/my_favorite_works.jsp" />
	</div>
	<!--내부메뉴//-->
</div>
