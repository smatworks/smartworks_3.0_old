<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="net.smartworks.service.ISmartWorks"%>

<ul class="navi_tit">
	<li class="js_collapse_slide">&gt; <fmt:message
			key="nav.communities.my_communities" />
	</li>
	<li class="nav_srch">
		<div class="srch">
			<input id="" class="input js_auto_complete" type="text"
				title="<fmt:message key='search.search_people_depart_group'/>"
				placeholder="<fmt:message key='search.search_people_depart_group'/>"
				href="community_list.sw">
			<button title="<fmt:message key='search.search'/>" onclick=""></button>
		</div>
		<div style="display: none"></div>
	</li>
</ul>


<div class='navi_list js_collapsible'>
	<!-- 내부 메뉴 -->
	<div class="categ_link js_tab_com">
		<a href="my_departments.sw" class="current"><fmt:message
				key="nav.communities.my_departments" /> </a> <a href="my_groups.sw"><fmt:message
				key="nav.communities.my_groups" /> </a>
	</div>
	<div id='my_communities'>
		<jsp:include page='/jsp/nav/my_departments.jsp' />
	</div>
	<!--내부메뉴//-->
</div>
