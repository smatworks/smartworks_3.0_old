<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="net.smartworks.*"%>
<%@ page import="net.smartworks.model.community.*"%>
<%@ page import="net.smartworks.model.notice.*"%>
<%@ page import="net.smartworks.model.instance.*"%>
<%@ page import="net.smartworks.model.work.*"%>
<%@ page import="net.smartworks.util.LocalDate"%>

<%
	SmartWorks smartWorks = (SmartWorks) request
			.getAttribute("smartWorks");
	User currentUser = smartWorks.getCurrentUser();
%>
<fmt:setLocale value="<%=currentUser.getLocale() %>" scope="request" />
<fmt:setBundle basename="resource.smartworksMessage" scope="request" />

<%
	String strNoticeType = request.getParameter("noticeType");
	int noticeType = (strNoticeType == null) ? Notice.TYPE_INVALID
			: Integer.parseInt(strNoticeType);
%>
<ul>
	<%
		if (noticeType == Notice.TYPE_NOTIFICATION) {
	%>
	<li class="t_bold"><fmt:message
			key="notice.message.box.notification" />
	</li>
	<jsp:include page="/jsp/notice/notification_list_box.jsp" />
	<%
		} else if (noticeType == Notice.TYPE_MESSAGE) {
	%>
	<li class="t_bold"><fmt:message key="notice.message.box.message" />
	</li>
	<jsp:include page="/jsp/notice/message_list_box.jsp" />
	<%
		} else if (noticeType == Notice.TYPE_COMMENTS) {
	%>
	<li class="t_bold"><fmt:message key="notice.message.box.comments" />
	</li>
	<jsp:include page="/jsp/notice/comments_list_box.jsp" />
	<%
		} else if (noticeType == Notice.TYPE_ASSIGNED) {
	%>
	<li class="t_bold"><fmt:message key="notice.message.box.assigned" />
	</li>
	<jsp:include page="/jsp/notice/assigned_list_box.jsp" />
	<%
		} else if (noticeType == Notice.TYPE_MAILBOX) {
	%>
	<li class="t_bold"><fmt:message key="notice.message.box.mailbox" />
	</li>
	<jsp:include page="/jsp/notice/mailbox_list_box.jsp" />
	<%
		} else if (noticeType == Notice.TYPE_SAVEDBOX) {
	%>
	<li class="t_bold"><fmt:message key="notice.message.box.savedbox" />
	</li>
	<jsp:include page="/jsp/notice/savedbox_list_box.jsp" />
	<%
		}
	%>

	<div class="btn_black close_message_box">
		<a href=""> <span class="Btn01Start"></span> <span
			class="Btn01Center"><fmt:message
					key="notice.message.box.close" />
		</span> <span class="Btn01End"></span> </a>
	</div>
</ul>