<%@page import="net.smartworks.util.SmartUtil"%>
<%@page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContextAware"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="net.smartworks.service.ISmartWorks"%>
<%
	ISmartWorks smartWorks = (ISmartWorks) request.getAttribute("smartWorks");
	String[] messages = smartWorks.getBroadcastingMessages();
%>

<!-- Broadcasting Board -->

<b>[<fmt:message key="broadcasting.board.title" />]
</b>
<marquee direction="left"><%=messages[0]%></marquee>

