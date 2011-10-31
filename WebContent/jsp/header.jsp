<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="net.smartworks.*"%>
<%@ page import="net.smartworks.model.community.*"%>
<%@ page import="net.smartworks.model.notice.*"%>
<%
	User currentUser = SmartWorks.getCurrentUser();
	Notice[] notices = SmartWorks.getNoticesForMe(currentUser.getId());
%>

<div>
	<a class="company_logo" href="home.sw?cid=<%=SmartWorks.CONTEXT_HOME%>"></a>
</div>
<div class="notice_ico">
	<ul>
		<li class="i_info js_notice_count"><a
			href="jsp/notice/notice_message_box.jsp?noticeType=<%=Notice.NOTICE_TYPE_NOTIFICATION%>"
			title="<fmt:message key='header.notice.icon.notification'/>"> 
<%
 	if (notices.length > Notice.NOTICE_TYPE_NOTIFICATION
 			&& notices[Notice.NOTICE_TYPE_NOTIFICATION]
 					.getNoticeLength() > 0) {
 %>
				<em class="num_ic"><%=notices[Notice.NOTICE_TYPE_NOTIFICATION]
						.getNoticeLength()%><span></span>
			</em> </a>
		</li>
<%
	}
%>
		<li class="i_note js_notice_count"><a
			href="jsp/notice/notice_message_box.jsp?noticeType=<%=Notice.NOTICE_TYPE_MESSAGE%>"
			title="<fmt:message key='header.notice.icon.message'/>"> 
<%
 	if (notices.length > Notice.NOTICE_TYPE_MESSAGE
 			&& notices[Notice.NOTICE_TYPE_MESSAGE].getNoticeLength() > 0) {
 %>
				<em class="num_ic"><%=notices[Notice.NOTICE_TYPE_MESSAGE].getNoticeLength()%><span></span>
			</em> </a>
		</li>
<%
	}
%>
		<li class="i_replay js_notice_count"><a
			href="jsp/notice/notice_message_box.jsp?noticeType=<%=Notice.NOTICE_TYPE_COMMENTS%>"
			title="<fmt:message key='header.notice.icon.comments'/>"> 
<%
 	if (notices.length > Notice.NOTICE_TYPE_COMMENTS
 			&& notices[Notice.NOTICE_TYPE_COMMENTS].getNoticeLength() > 0) {
 %>
				<em class="num_ic"><%=notices[Notice.NOTICE_TYPE_COMMENTS]
						.getNoticeLength()%><span></span>
			</em> </a>
		</li>
<%
	}
%>
		<li class="i_assworks js_notice_count"><a
			href="jsp/notice/notice_message_box.jsp?noticeType=<%=Notice.NOTICE_TYPE_ASSIGNED%>"
			title="<fmt:message key='header.notice.icon.assigned'/>"> 
<%
 	if (notices.length > Notice.NOTICE_TYPE_ASSIGNED
 			&& notices[Notice.NOTICE_TYPE_ASSIGNED].getNoticeLength() > 0) {
 %>
				<em class="num_ic"><%=notices[Notice.NOTICE_TYPE_ASSIGNED]
						.getNoticeLength()%><span></span>
			</em> </a>
		</li>
<%
	}
%>
		<li class="i_mail js_notice_count"><a
			href="jsp/notice/notice_message_box.jsp?noticeType=<%=Notice.NOTICE_TYPE_MAILBOX%>"
			title="<fmt:message key='header.notice.icon.mailbox'/>"> 
<%
 	if (notices.length > Notice.NOTICE_TYPE_MAILBOX
 			&& notices[Notice.NOTICE_TYPE_MAILBOX].getNoticeLength() > 0) {
 %>
				<em class="num_ic"><%=notices[Notice.NOTICE_TYPE_MAILBOX].getNoticeLength()%><span></span>
			</em> </a>
		</li>
<%
	}
%>
		<li class="i_imsave js_notice_count"><a
			href="jsp/notice/notice_message_box.jsp?noticeType=<%=Notice.NOTICE_TYPE_SAVEDBOX%>"
			title="<fmt:message key='header.notice.icon.savedbox'/>"> 
<%
 	if (notices.length > Notice.NOTICE_TYPE_SAVEDBOX
 			&& notices[Notice.NOTICE_TYPE_SAVEDBOX].getNoticeLength() > 0) {
 %>
				<em class="num_ic"><%=notices[Notice.NOTICE_TYPE_SAVEDBOX]
						.getNoticeLength()%><span></span>
			</em> </a>
		</li>
<%
	}
%>

	</ul>
</div>
<div class="pop_i_info" id="notice_message_box" style="display: none">
</div>

<div class="top_menu js_top_menu">
	<ul>
		<li class="idx1 "><span><a
				href="home.sw?cid=<%=SmartWorks.CONTEXT_HOME%>"><fmt:message
						key="header.top_menu.home" />
			</a>
		</span>
		</li>
		<li class="idx2"><span><a
				href="smartcaster.sw?cid=<%=SmartWorks.CONTEXT_SMARTCASTER%>"><fmt:message
						key="header.top_menu.smartcaster" />
			</a>
		</span>
		</li>
		<li class="idx3"><span><a
				href="dashboard.sw?cid=<%=SmartWorks.CONTEXT_DASHBOARD%>"><fmt:message
						key="header.top_menu.dashboard" />
			</a>
		</span>
		</li>
	</ul>
</div>

<div class="global_srch">
	<div class="srch">
		<input id="" class="input" type="text"
			title="<fmt:message key='search.global_search'/>"
			placeholder="<fmt:message key='search.global_search'/>">
		<button title="<fmt:message key='search.search'/>" onclick=""></button>
	</div>
</div>
<div class="global_menu">
	<div>
		<a title="<fmt:message key='header.global_menu.help'/>"
			target="_blank" href="http://manual.smartworks.net/"><fmt:message
				key="header.global_menu.help" />
		</a>
	</div>
	<div>
		<a href="" onclick="$(this).next('div').toggle(); return false;"><%=currentUser.getPosition()%>
		<%=currentUser.getName()%>▼
		</a>
	</div>

	<!-- global_menu sub -->
	<div class="pop" style="display: none">
		<ul>
			<li><a
				href="my_profile.sw?cid=<%=SmartWorks.CONTEXT_MYPROFILE%>"><fmt:message
						key="header.global_menu.edit_my_profile" />
			</a>
			</li>
			<li><a href=""><fmt:message key="header.global_menu.logout" />
			</a>
			</li>
		</ul>
	</div>

</div>