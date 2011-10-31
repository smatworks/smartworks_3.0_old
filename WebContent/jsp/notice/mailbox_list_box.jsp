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
	int noticeType = (sNoticeType == null) ? Notice.TYPE_INVALID : Integer.parseInt(sNoticeType);
	LocalDate dateOfLastNotice = (sLastNotice == null) ? new LocalDate(0) : new LocalDate(Long.parseLong(sLastNotice));
	NoticeBox noticeBox = smartWorks.getNoticeBoxForMe10(noticeType, dateOfLastNotice);
%>
<%
	for (NoticeMessage nMessage : (NoticeMessage[]) noticeBox.getNoticeMessages()) {
		if (noticeBox != null && noticeBox.getNoticeType() == Notice.TYPE_MAILBOX) {
			MailInstance mailInstance = (MailInstance) nMessage.getInstance();
			User owner = mailInstance.getSender();
			String instContext = ISmartWorks.CONTEXT_PREFIX_MAIL_SPACE + owner.getId();
%>
<li><div class="info_img">
		<a href="mail_space.sw?cid=<%=instContext%>&wid=<%=owner.getId()%>"
			title="<%=owner.getLongName()%>"> <img
			src="<%=owner.getMinPicture()%>" border="0">
		</a>
	</div>
	<div class="info_list"><%=mailInstance.getSubject()%>
		<div class="t_date"><%=mailInstance.getSendDate().toLocalString()%>
			<div class="btn_x">
				<a href="">X</a>
			</div>
		</div>
	</div></li>
<%
	}
	}
%>
