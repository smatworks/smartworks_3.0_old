<%@ page contentType="text/html; charset=utf-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="net.smartworks.*" %> 
<%@ page import="net.smartworks.model.community.*" %> 

<% 
	User[] chatters = smartWorks.getAvailableChatter();
%>

<ul>
	<li class="nav_srch">
		<div class="srch">
			<input id="" class="input js_auto_complete" type="text"
				title="<fmt:message key="search.search_available_chatter"/>"
				placeholder="<fmt:message key="search.search_available_chatter"/>"
				href="jsp/search/available_chatter_list.jsp">
			<button title="<fmt:message key='search.search'/>" onclick=""></button>
		</div>
		<div style="display:none">
		</div>
	</li>
	<li class="ico_chatpe">
		<% for( User chatter : chatters){ %>
			<img src="<%=chatter.getMinPicture()%>" title="<%=chatter.getLongName() %>" />
		<%} %>
	</li>
</ul>