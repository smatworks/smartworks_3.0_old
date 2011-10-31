<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.*"%>
<%@ page import="net.smartworks.model.work.*"%>

<%
	SmartWorks smartWorks = (SmartWorks) request
			.getAttribute("smartWorks");
	WorkCategory[] workCategories = smartWorks
			.getMyWorkCategories(smartWorks.getCurrentUser().getId());
%>

<ul>
	<%
		for (WorkCategory workCategory : workCategories) {
	%>
	<li class="js_drill_down ico_cworks"><a
		targetContent="jsp/nav/worklist_by_category.jsp"
		categoryId="<%=workCategory.getId()%>"> <%=workCategory.getName()%></a>
		<div style="display: none"></div>
	</li>
	<%
		}
	%>
</ul>
