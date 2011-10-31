<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="net.smartworks.service.ISmartWorks"%>
<%@ page import="net.smartworks.model.community.*"%>
<%@ page import="net.smartworks.model.notice.*"%>
<%@ page import="net.smartworks.model.instance.*"%>
<%@ page import="net.smartworks.model.work.*"%>
<%@ page import="net.smartworks.util.LocalDate"%>
<%
	ISmartWorks smartWorks = (ISmartWorks) request.getAttribute("smartWorks");
	String sNoticeType = request.getParameter("noticeType");
	String sLastNotice = request.getParameter("dateOfLastNotice");
	int noticeType = (sNoticeType == null) ? Notice.TYPE_INVALID
			: Integer.parseInt(sNoticeType);
	LocalDate dateOfLastNotice = (sLastNotice == null) ? new LocalDate(
			0) : new LocalDate(Long.parseLong(sLastNotice));
	NoticeBox noticeBox = smartWorks.getNoticeBoxForMe10(noticeType, dateOfLastNotice);
%>
<%
	for (NoticeMessage nMessage : (NoticeMessage[]) noticeBox
			.getNoticeMessages()) {
		if (noticeBox != null
				&& noticeBox.getNoticeType() == Notice.TYPE_COMMENTS) {
			CommentsInstance commentsInstance = (CommentsInstance) nMessage
					.getInstance();
			String instContext = null, targetContent = null;
			User owner = commentsInstance.getOwner();
			String userContext = ISmartWorks.CONTEXT_PREFIX_USER_SPACE
					+ owner.getId();
			Work work = null;
			if (commentsInstance.getCommentsType() == CommentsInstance.COMMENTS_TYPE_ON_WORK_DESC
					|| commentsInstance.getCommentsType() == CommentsInstance.COMMENTS_TYPE_ON_WORK_MANUAL) {
				work = commentsInstance.getWork();
				owner = commentsInstance.getOwner();
				targetContent = smartWorks
						.getTargetContentByWorkType(work.getType(),
								ISmartWorks.SPACE_TYPE_WORK_LIST);
				instContext = smartWorks
						.getContextPrefixByWorkType(work.getType(),
								ISmartWorks.SPACE_TYPE_WORK_LIST)
						+ work.getId();
%>
<li><div class="info_img">
		<a href="user_space.sw?cid=<%=userContext%>"
			title="<%=owner.getLongName()%>"> <img
			src="<%=owner.getMinPicture()%>" border="0">
		</a>
	</div>
	<div class="info_list">
		<a href="<%=targetContent%>?cid=<%=instContext%>"><%=work.getName()%></a>
		<%=commentsInstance.getComments()%>
		<div class="t_date"><%=commentsInstance.getLastModifiedDate()
								.toLocalString()%>
			<div class="btn_x">
				<a href="">X</a>
			</div>
		</div>
	</div></li>
<%
	} else if (commentsInstance.getCommentsType() == CommentsInstance.COMMENTS_TYPE_ON_WORK_INSTANCE) {
				work = commentsInstance.getWorkInstance().getWork();
				WorkInstance workInstance = commentsInstance
						.getWorkInstance();
				targetContent = smartWorks.getTargetContentByWorkType(
						work.getType(),
						ISmartWorks.SPACE_TYPE_WORK_INSTANCE);
				instContext = smartWorks.getContextPrefixByWorkType(
						work.getType(),
						ISmartWorks.SPACE_TYPE_WORK_INSTANCE)
						+ workInstance.getId();
%>
<li><div class="info_img">
		<a href="user_space.sw?cid=<%=userContext%>"
			title="<%=owner.getLongName()%>"> <img
			src="<%=owner.getMinPicture()%>" border="0">
		</a>
	</div>
	<div class="info_list">
		<a
			href="<%=targetContent%>?cid=<%=instContext%>&wid=<%=workInstance.getWorkSpace()%>"><%=workInstance.getSubject()%></a>
		<%=commentsInstance.getComments()%>
		<div class="t_date"><%=commentsInstance.getLastModifiedDate()
								.toLocalString()%>
			<div class="btn_x">
				<a href="">X</a>
			</div>
		</div>
	</div></li>
<%
	} else if (commentsInstance.getCommentsType() == CommentsInstance.COMMENTS_TYPE_ON_TASK_INSTANCE) {
				work = commentsInstance.getTaskInstance()
						.getWorkInstance().getWork();
				TaskInstance taskInstance = commentsInstance
						.getTaskInstance();
				targetContent = smartWorks.getTargetContentByWorkType(
						work.getType(),
						ISmartWorks.SPACE_TYPE_TASK_INSTANCE);
				instContext = smartWorks.getContextPrefixByWorkType(
						work.getType(),
						ISmartWorks.SPACE_TYPE_TASK_INSTANCE)
						+ taskInstance.getWorkInstance().getId();
%>
<li><div class="info_img">
		<a href="user_space.sw?cid=<%=userContext%>"
			title="<%=owner.getLongName()%>"> <img
			src="<%=owner.getMinPicture()%>" border="0">
		</a>
	</div>
	<div class="info_list">
		<a
			href="<%=targetContent%>?cid=<%=instContext%>&wid=<%=taskInstance.getWorkInstance().getWorkSpace()%>"><%=taskInstance.getSubject()%></a>
		<%=commentsInstance.getComments()%>
		<div class="t_date"><%=commentsInstance.getLastModifiedDate()
								.toLocalString()%>
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
