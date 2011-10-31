<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="net.smartworks.*"%>
<%@ page import="net.smartworks.model.community.*"%>
<%@ page import="net.smartworks.model.notice.*"%>
<%
	SmartWorks smartWorks = (SmartWorks) request
			.getAttribute("smartWorks");
	User currentUser = smartWorks.getCurrentUser();
	Notice[] notices = smartWorks.getNoticesForMe(currentUser.getId());
	String cid = request.getParameter("cid");
	if (cid == null)
		cid = SmartWorks.CONTEXT_HOME;
	String wid = request.getParameter("wid");
	if (wid == null)

	wid = smartWorks.getCurrentUser().getId();

%>

<div>
	<a class="company_logo" href="home.sw?cid=<%=SmartWorks.CONTEXT_HOME%>"></a>
</div>
<div class="notice_ico">
	<ul>
		<li class="i_info js_notice_count"><a
			href="notice_message_box.sw?noticeType=<%=Notice.TYPE_NOTIFICATION%>"
			title="<fmt:message key='header.notice.icon.notification'/>"> <%
 	if (notices.length > Notice.TYPE_NOTIFICATION
 			&& notices[Notice.TYPE_NOTIFICATION]
 					.getLength() > 0) {
 %> <em class="num_ic"><%=notices[Notice.TYPE_NOTIFICATION]
						.getLength()%><span></span> </em> </a></li>
		<%
			}
		%>
		<li class="i_note js_notice_count"><a
			href="notice_message_box.sw?noticeType=<%=Notice.TYPE_MESSAGE%>"
			title="<fmt:message key='header.notice.icon.message'/>"> <%
 	if (notices.length > Notice.TYPE_MESSAGE
 			&& notices[Notice.TYPE_MESSAGE].getLength() > 0) {
 %> <em class="num_ic"><%=notices[Notice.TYPE_MESSAGE].getLength()%><span></span>
			</em> </a></li>
		<%
			}
		%>
		<li class="i_replay js_notice_count"><a
			href="notice_message_box.sw?noticeType=<%=Notice.TYPE_COMMENTS%>"
			title="<fmt:message key='header.notice.icon.comments'/>"> <%
 	if (notices.length > Notice.TYPE_COMMENTS
 			&& notices[Notice.TYPE_COMMENTS].getLength() > 0) {
 %> <em class="num_ic"><%=notices[Notice.TYPE_COMMENTS]
						.getLength()%><span></span> </em> </a></li>
		<%
			}
		%>
		<li class="i_assworks js_notice_count"><a
			href="notice_message_box.sw?noticeType=<%=Notice.TYPE_ASSIGNED%>"
			title="<fmt:message key='header.notice.icon.assigned'/>"> <%
 	if (notices.length > Notice.TYPE_ASSIGNED
 			&& notices[Notice.TYPE_ASSIGNED].getLength() > 0) {
 %> <em class="num_ic"><%=notices[Notice.TYPE_ASSIGNED]
						.getLength()%><span></span> </em> </a></li>
		<%
			}
		%>
		<li class="i_mail js_notice_count"><a
			href="notice_message_box.sw?noticeType=<%=Notice.TYPE_MAILBOX%>"
			title="<fmt:message key='header.notice.icon.mailbox'/>"> <%
 	if (notices.length > Notice.TYPE_MAILBOX
 			&& notices[Notice.TYPE_MAILBOX].getLength() > 0) {
 %> <em class="num_ic"><%=notices[Notice.TYPE_MAILBOX].getLength()%><span></span>
			</em> </a></li>
		<%
			}
		%>
		<li class="i_imsave js_notice_count"><a
			href="notice_message_box.sw?noticeType=<%=Notice.TYPE_SAVEDBOX%>"
			title="<fmt:message key='header.notice.icon.savedbox'/>"> <%
 	if (notices.length > Notice.TYPE_SAVEDBOX
 			&& notices[Notice.TYPE_SAVEDBOX].getLength() > 0) {
 %> <em class="num_ic"><%=notices[Notice.TYPE_SAVEDBOX]
						.getLength()%><span></span> </em> </a></li>
		<%
			}
		%>

	</ul>
</div>
<div class="pop_i_info" id="notice_message_box" style="display: none">
</div>

<div class="top_menu">
	<ul>
		<li class="idx1">
		<%if(cid.equals(SmartWorks.CONTEXT_HOME)){ %>
		<span><a class="current" href="home.sw?cid=<%=SmartWorks.CONTEXT_HOME%>"><fmt:message
						key="header.top_menu.home" /> </a> </span>
		<%}else{ %>
		<span><a href="home.sw?cid=<%=SmartWorks.CONTEXT_HOME%>"><fmt:message
						key="header.top_menu.home" /> </a> </span>
		<%}%>						
		</li>
		<li class="idx2">
		<%if(cid.equals(SmartWorks.CONTEXT_SMARTCASTER)){ %>
		<span><a class="current" href="smartcaster.sw?cid=<%=SmartWorks.CONTEXT_SMARTCASTER%>"><fmt:message
						key="header.top_menu.smartcaster" /> </a> </span>
		<%}else{ %>
		<span><a href="smartcaster.sw?cid=<%=SmartWorks.CONTEXT_SMARTCASTER%>"><fmt:message
						key="header.top_menu.smartcaster" /> </a> </span>
		<%}%>
						
		</li>
		<li class="idx3">
		<%if(cid.equals(SmartWorks.CONTEXT_DASHBOARD)){ %>
		<span><a class="current" href="dashboard.sw?cid=<%=SmartWorks.CONTEXT_DASHBOARD%>"><fmt:message
						key="header.top_menu.dashboard" /> </a> </span>
		<%}else{ %>
		<span><a href="dashboard.sw?cid=<%=SmartWorks.CONTEXT_DASHBOARD%>"><fmt:message
						key="header.top_menu.dashboard" /> </a> </span>
		<%}%>
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
			target="_blank" href="http://manual.smartWorks.net/"><fmt:message
				key="header.global_menu.help" /> </a>
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
						key="header.global_menu.edit_my_profile" /> </a></li>
			<li><a href=""><fmt:message key="header.global_menu.logout" />
			</a></li>
		</ul>
	</div>

</div>
