<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="net.smartworks.*"%>

<ul>
	<li class="js_collapse_slide">&gt; <fmt:message
			key="nav.communities.community_members" /></li>
	<li class="nav_srch">
		<div class="srch">
			<input id="" class="input js_auto_complete" type="text"
				title="<fmt:message key='search.search_community_member'/>"
				placeholder="<fmt:message key='search.search_community_member'/>"
				href="jsp/search/community_member_list.jsp">
			<button title="<fmt:message key='search.search'/>" onclick=""></button>
		</div>
		<div style="display: none"></div></li>
	<li class='js_collapsible'>
		<div id='my_communities'>
			<%@ include file='members_by_community.jsp'%>
		</div> <!--내부메뉴//--></li>
</ul>