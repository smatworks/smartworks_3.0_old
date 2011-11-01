<%@page import="net.smartworks.util.SmartUtil"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.service.ISmartWorks"%>
<%@ page import="net.smartworks.model.work.*"%>

<%
	ISmartWorks smartWorks = (ISmartWorks) request.getAttribute("smartWorks");
	WorkCategory[] workCategories = smartWorks.getMyWorkCategories(SmartUtil.getCurrentUser().getId());
%>

<ul>
	<%
		for (WorkCategory workCategory : workCategories) {
	%>
	<li class="js_drill_down ico_cworks"><a
		targetContent="worklist_by_category.sw"
		categoryId="<%=workCategory.getId()%>"> <%=workCategory.getName()%></a>
		<div style="display: none"></div></li>
	<%
		}
	%>
</ul>
