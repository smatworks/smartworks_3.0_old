<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="net.smartworks.*"%>
<%

	WebApplicationContext webAppliactionContext = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());

	webAppliactionContext.getBean("smartworks");
	
	String[] messages = SmartWorks.getBroadcastingMessages();
%>

<!-- Contents-->

<div id="top_notice">
	<b>[<fmt:message key="broadcasting.board.title"/>]</b><marquee direction="left"><%=messages[0] %></marquee>
</div>
