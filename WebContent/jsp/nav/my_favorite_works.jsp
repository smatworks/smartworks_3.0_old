<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.*"%>
<%@ page import="net.smartworks.model.work.*"%>
<%
	SmartWorks smartWorks = (SmartWorks) request
			.getAttribute("smartWorks");
	SmartWork[] works = smartWorks.getMyFavoriteWorks("currentUser");
	String iconType = null;
	String classType = "js_content";
	String workContext = null;
	String targetContent = null;
%>

<ul>
	<%
		for (SmartWork work : works) {
			if (work.getType() == SmartWork.WORK_TYPE_PROCESS) {
				iconType = "ico_pworks";
				workContext = SmartWorks.CONTEXT_PREFIX_PWORK_LIST
						+ work.getId();
				targetContent = "pwork_list.sw";
			} else if (work.getType() == SmartWork.WORK_TYPE_INFORMATION) {
				iconType = "ico_iworks";
				workContext = SmartWorks.CONTEXT_PREFIX_IWORK_LIST
						+ work.getId();
				targetContent = "iwork_list.sw";//"information_work_list.sw";
			} else if (work.getType() == SmartWork.WORK_TYPE_SCHEDULE) {
				iconType = "ico_sworks";
				workContext = SmartWorks.CONTEXT_PREFIX_SWORK_LIST
						+ work.getId();
				targetContent = "swork_list.sw";//"schedule_work_list.sw";
			}
	%>
	<li class="<%=iconType%>"><a
		href="<%=targetContent%>?cid=<%=workContext%>" class="<%=classType%>"
		title="<%=work.getFullpathName()%>"><%=work.getName()%></a></li>
	<%
		}
	%>
</ul>