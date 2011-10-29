<%@page import="net.smartworks.util.SmartUtil"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContextAware"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="net.smartworks.*"%>
<%
	//String[] messages =  ((SmartWorks)WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean("smartWorks")).getBroadcastingMessages();
	SmartWorks smartworks = (SmartWorks)SmartUtil.getService("SmartWorks", request);
	String[] messages = smartworks.getBroadcastingMessages();
	//String[] messages =  SmartUtil.getService("Smartworks", request).getBroadcastingMessages();
%>

<!-- Contents-->

<div id="top_notice">
	<b>[<fmt:message key="broadcasting.board.title"/>]</b><marquee direction="left"><%=messages[0] %></marquee>
</div>
