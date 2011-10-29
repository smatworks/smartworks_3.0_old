<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="net.smartworks.*" %> 
<%@ page import="net.smartworks.model.community.*" %>
<%@ page import="net.smartworks.model.notice.*" %> 
<%@ page import="net.smartworks.model.instance.*" %>
<%@ page import="net.smartworks.model.work.*" %>
<%@ page import="net.smartworks.util.LocalDate" %>

<%
	SmartWorks smartworks = (SmartWorks)request.getAttribute("smartworks");
	User currentUser = smartworks.getCurrentUser();
%>
<fmt:setLocale value="<%=currentUser.getLocale() %>" scope="request"/>
<fmt:setBundle basename="resource.smartworksMessage" scope="request"/>

<% 
	String strNoticeType = request.getParameter("noticeType");
	int noticeType = (strNoticeType==null) ? Notice.NOTICE_TYPE_INVALID : Integer.parseInt(strNoticeType);
%>
<ul>
	<%
	if(noticeType == Notice.NOTICE_TYPE_NOTIFICATION){ 
	%>
		<li class="t_bold"><fmt:message key="notice.message.box.notification"/></li>
		<%@ include file="notification_list_box.jsp" %>
    <%
    }else if(noticeType == Notice.NOTICE_TYPE_MESSAGE){ 
    %>
    	<li class="t_bold"><fmt:message key="notice.message.box.message"/></li>
		<%@ include file="message_list_box.jsp" %>
    <%
    }else if(noticeType == Notice.NOTICE_TYPE_COMMENTS){ 
    %>
    	<li class="t_bold"><fmt:message key="notice.message.box.comments"/></li>
		<%@ include file="comments_list_box.jsp" %>
    <%
    }else if(noticeType == Notice.NOTICE_TYPE_ASSIGNED){ 
    %>
    	<li class="t_bold"><fmt:message key="notice.message.box.assigned"/></li>
		<%@ include file="assigned_list_box.jsp" %>
    <%
    }else if(noticeType == Notice.NOTICE_TYPE_MAILBOX){ 
    %>
    	<li class="t_bold"><fmt:message key="notice.message.box.mailbox"/></li>
		<%@ include file="mailbox_list_box.jsp" %>
    <%
    }else if(noticeType == Notice.NOTICE_TYPE_SAVEDBOX){
    %>
    	<li class="t_bold"><fmt:message key="notice.message.box.savedbox"/></li>
		<%@ include file="savedbox_list_box.jsp" %>
    <%
    }
	%>
	
    <div class="btn_black close_message_box">
		<a href="">
			<span class="Btn01Start"></span>
			<span class="Btn01Center"><fmt:message key="notice.message.box.close"/></span>
			<span class="Btn01End"></span>
		</a>
	</div>
</ul>