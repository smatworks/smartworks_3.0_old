<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="net.smartworks.*"%>
<%@ page import="net.smartworks.model.community.*"%>
<%@ page import="net.smartworks.model.notice.*"%>
<%@ page import="net.smartworks.model.instance.*"%>
<%@ page import="net.smartworks.model.work.*"%>
<%@ page import="net.smartworks.util.LocalDate"%>
<%@ page import="java.util.Date"%>
<%
	String sNoticeType = request.getParameter("noticeType");
	String sLastNotice = request.getParameter("dateOfLastNotice");
	int iNoticeType = (sNoticeType == null) ? Notice.NOTICE_TYPE_INVALID
			: Integer.parseInt(sNoticeType);
	LocalDate dateOfLastNotice = (sLastNotice == null) ? new LocalDate(
			0) : new LocalDate(Long.parseLong(sLastNotice));
	NoticeBox noticeBox = smartWorks.getNoticeBoxForMe10(iNoticeType,
			dateOfLastNotice);
%>
<%
	for (NoticeMessage nMessage : (NoticeMessage[]) noticeBox
			.getNoticeMessages()) {
		if (noticeBox != null
				&& noticeBox.getNoticeType() == Notice.NOTICE_TYPE_ASSIGNED) {
			TaskInstance taskInstance = (TaskInstance) nMessage
					.getInstance();
			int taskType = taskInstance.getTaskType();
			if (taskType == TaskInstance.TASK_TYPE_PROCESSWORK_TASK_ASSIGNED
					|| taskType == TaskInstance.TASK_TYPE_APPROVALWORK_TASK_ASSIGNED
					|| taskType == TaskInstance.TASK_TYPE_INFORMATIONWORK_TASK_ASSIGNED
					|| taskType == TaskInstance.TASK_TYPE_INFORMATIONWORK_TASK_FORWARDED
					|| taskType == TaskInstance.TASK_TYPE_PROCESSWORK_TASK_FORWARDED) {
				User owner = taskInstance.getOwner();
				WorkInstance workInstance = taskInstance
						.getWorkInstance();
				Work work = workInstance.getWork();
				String targetContent = SmartWorks
						.getTargetContentByWorkType(work.getType(),
								SmartWorks.SPACE_TYPE_TASK_INSTANCE);
				String contextId = SmartWorks
						.getContextPrefixByWorkType(work.getType(),
								SmartWorks.SPACE_TYPE_TASK_INSTANCE)
						+ taskInstance.getId();
				String workSpaceId = taskInstance.getWorkSpace()
						.getId();
				String userContext = SmartWorks.CONTEXT_PREFIX_USER_SPACE
						+ owner.getId();
%>
<li><div class="info_img">
		<a href="user_space.sw?cid=<%=userContext%>"
			title="<%=owner.getLongName()%>"> <img
			src="<%=owner.getMinPicture()%>" border="0"> </a>
	</div>
	<div class="info_list">
		<a href="<%=targetContent%>?cid=<%=contextId%>&wid=<%=workSpaceId%>"><%=workInstance.getSubject()%>▶<%=taskInstance.getName()%></a>
		<div class="t_date"><%=taskInstance.getLastModifiedDate()
								.toLocalString()%>
		</div>
	</div>
</li>
<%
	}
		}
	}
%>
