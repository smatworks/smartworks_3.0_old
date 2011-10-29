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
	SmartWorks smartworks = (SmartWorks)request.getAttribute("smartworks");
	String sNoticeType = request.getParameter("noticeType");
	String sLastNotice = request.getParameter("dateOfLastNotice");
	int iNoticeType = (sNoticeType == null) ? Notice.NOTICE_TYPE_INVALID
			: Integer.parseInt(sNoticeType);
	LocalDate dateOfLastNotice = (sLastNotice == null) ? new LocalDate(0)
			: new LocalDate(Long.parseLong(sLastNotice));
	NoticeBox noticeBox = smartworks.getNoticeBoxForMe10(iNoticeType,
			dateOfLastNotice);
%>
<%
	for (NoticeMessage nMessage : (NoticeMessage[]) noticeBox
			.getNoticeMessages()) {
		String instContext = null;
		if (noticeBox != null
				&& noticeBox.getNoticeType() == Notice.NOTICE_TYPE_MESSAGE) {
			AsyncMessageInstance messageInstance = (AsyncMessageInstance) nMessage
					.getInstance();
			User owner = messageInstance.getSender();
			instContext = SmartWorks.CONTEXT_PREFIX_USER_SPACE
					+ owner.getId();
%>
<li><div class="info_img">
		<a href="user_space.sw?cid=<%=instContext%>&wid=<%=owner.getId()%>"
			title="<%=owner.getLongName()%>"> <img
			src="<%=owner.getMinPicture()%>" border="0"> </a>
	</div>
	<div class="info_list"><%=messageInstance.getMessage()%>
		<div class="t_date"><%=messageInstance.getSendDate().toLocalString()%>
			<div class="btn_x">
				<a href="">X</a>
			</div>
		</div>
	</div>
</li>
<%
		}
	}
%>
