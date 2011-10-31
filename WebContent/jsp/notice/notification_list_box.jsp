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
	String sNoticeType = request.getParameter("noticeType");
	String sLastNotice = request.getParameter("dateOfLastNotice");
	int noticeType = (sNoticeType == null) ? Notice.NOTICE_TYPE_INVALID
			: Integer.parseInt(sNoticeType);
	LocalDate dateOfLastNotice = (sLastNotice == null) ? new LocalDate(
			0) : new LocalDate(Long.parseLong(sLastNotice));
	NoticeBox noticeBox = smartWorks.getNoticeBoxForMe10(noticeType,
			dateOfLastNotice);
%>
<%
	for (NoticeMessage nMessage : (NoticeMessage[]) noticeBox
			.getNoticeMessages()) {
		if (noticeBox != null
				&& noticeBox.getNoticeType() == Notice.NOTICE_TYPE_NOTIFICATION) {
			String instContext = null, targetContent = null, userContext = null;
			User owner = null;
			if (nMessage.getNotificationType() == NoticeMessage.NOTIFICATION_TYPE_SYSTEM_NOTICE) {
%>
<li><div class="info_img">
		<img src="images/pop_ico_info.jpg" border="0">
	</div>
	<div class="info_list"><%=nMessage.getMessage()%><div
			class="t_date"><%=nMessage.getIssuedDate().toLocalString()%>
			<div class="btn_x">
				<a href="">X</a>
			</div>
		</div>
	</div></li>
<%
	} else if (nMessage.getNotificationType() == NoticeMessage.NOTIFICATION_TYPE_EVENT_ALARM) {
				EventInstance event = (EventInstance) nMessage
						.getEvent();
				owner = event.getOwner();
				instContext = SmartWorks.CONTEXT_PREFIX_EVENT_SPACE
						+ event.getId();
				userContext = SmartWorks.CONTEXT_PREFIX_USER_SPACE
						+ owner.getId();
%>
<li><div class="info_img">
		<a href="user_space.sw?cid=<%=userContext%>&wid=<%=owner.getId()%>"
			title="<%=owner.getLongName()%>"><img
			src="<%=owner.getMidPicture()%>" border="0"> </a>
	</div>
	<div class="info_list">
		<a
			href="event_space.sw?cid=<%=instContext%>&wid=<%=event.getWorkSpace().getId()%>"><%=event.getSubject()%></a>
		<fmt:message key="notice.message.start.time" />
		<%=event.getPlannedStart().toLocalString()%>
		<div class="t_date"><%=nMessage.getIssuedDate().toLocalString()%>
			<div class="btn_x">
				<a href=""></a>
			</div>
		</div>
	</div></li>
<%
	} else if (nMessage.getNotificationType() == NoticeMessage.NOTIFICATION_TYPE_TASK_DELAYED) {
				TaskInstance task = (TaskInstance) nMessage
						.getInstance();
				owner = task.getOwner();
				WorkInstance work = (WorkInstance) task
						.getWorkInstance();
				instContext = SmartWorks.CONTEXT_PREFIX_PWORK_TASK
						+ task.getId();
				userContext = SmartWorks.CONTEXT_PREFIX_USER_SPACE
						+ owner.getId();
%>
<li><div class="info_img">
		<a href="user_space.sw?cid=<%=userContext%>&wid=<%=owner.getId()%>"
			title="<%=owner.getLongName()%>"><img
			src="<%=owner.getMidPicture()%>" border="0"> </a>
	</div>
	<div class="info_list">
		<a
			href="pwork_task.sw?cid=<%=instContext%>&wid=<%=task.getWorkSpace().getId()%>"><%=work.getSubject()%>▶<%=task.getName()%></a>
		<fmt:message key="notice.message.task.delayed" />
		<div class="t_date"><%=nMessage.getIssuedDate().toLocalString()%>
			<div class="btn_x">
				<a href="">X</a>
			</div>
		</div>
	</div></li>
<%
	} else if (nMessage.getNotificationType() == NoticeMessage.NOTIFICATION_TYPE_JOIN_REQUEST) {
				owner = nMessage.getIssuer();
				instContext = SmartWorks.CONTEXT_PREFIX_GROUP_SPACE
						+ nMessage.getGroup().getId();
				userContext = SmartWorks.CONTEXT_PREFIX_USER_SPACE
						+ owner.getId();
%>
<li><div class="info_img">
		<a href="user_space.sw?cid=<%=userContext%>&wid=<%=owner.getId()%>"
			title="<%=owner.getLongName()%>"><img
			src="<%=owner.getMinPicture()%>" border="0"> </a>
	</div>
	<div class="info_list">
		<a
			href="group_space.sw?cid=<%=instContext%>&wid=<%=nMessage.getGroup().getId()%>">
			<%=nMessage.getGroup().getName()%></a><%=nMessage.getMessage()%>
		<div class="t_date"><%=nMessage.getIssuedDate().toLocalString()%>
			<div class="btn_x">
				<a href="">X</a>
			</div>
		</div>
	</div></li>
<%
	} else if (nMessage.getNotificationType() == NoticeMessage.NOTIFICATION_TYPE_INSTANCE_CREATED) {
				WorkInstance instance = (WorkInstance) nMessage
						.getInstance();
				owner = instance.getOwner();
				targetContent = smartWorks.getTargetContentByWorkType(
						nMessage.getInstance().getWork().getType(),
						SmartWorks.SPACE_TYPE_TASK_INSTANCE);
				instContext = smartWorks.getContextPrefixByWorkType(
						nMessage.getInstance().getWork().getType(),
						SmartWorks.SPACE_TYPE_TASK_INSTANCE)
						+ nMessage.getInstance().getOwner().getId();
				userContext = SmartWorks.CONTEXT_PREFIX_USER_SPACE
						+ owner.getId();
%>
<li><div class="info_img">
		<a href="user_space.sw?cid=<%=userContext%>&wid=<%=owner.getId()%>"
			title="<%=owner.getLongName()%>"><img
			src="<%=owner.getMinPicture()%>" border="0"> </a>
	</div>
	<div class="info_list">
		<a
			href="<%=targetContent%>?cid=<%=instContext%>&wid=<%=instance.getWorkSpace().getId()%>"><%=instance.getSubject()%></a><%=nMessage.getMessage()%>
		<div class="t_date"><%=nMessage.getIssuedDate().toLocalString()%>
			<div class="btn_x">
				<a href="">X</a>
			</div>
		</div>
	</div></li>
<%
	}
		}
	}
%>
