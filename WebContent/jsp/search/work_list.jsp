<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.*"%>
<%@ page import="net.smartworks.model.work.*"%>
<%
	SmartWorks smartWorks = (SmartWorks) request
			.getAttribute("smartWorks");
	String key = request.getParameter("key");
	SmartWork[] works = smartWorks.searchWorkList(SmartWorks
			.getCurrentUser().getId(), key);
%>

<ul>
	<%
		for (SmartWork work : works) {
			String iconType = null;
			String workContext = null;
			String targetContent = null;
			if (work.getType() == SmartWork.WORK_TYPE_PROCESS) {
				iconType = "ico_pworks";
				workContext = SmartWorks.CONTEXT_PREFIX_PWORK_LIST
						+ work.getId();
				targetContent = "pwork_list.sw";
			} else if (work.getType() == SmartWork.WORK_TYPE_INFORMATION) {
				iconType = "ico_iworks";
				workContext = SmartWorks.CONTEXT_PREFIX_IWORK_LIST
						+ work.getId();
				targetContent = "iwork_list.sw";
			} else if (work.getType() == SmartWork.WORK_TYPE_SCHEDULE) {
				iconType = "ico_sworks";
				workContext = SmartWorks.CONTEXT_PREFIX_SWORK_LIST
						+ work.getId();
				targetContent = "swork_list.sw";
			}
	%>
	<li class="<%=iconType%>"><a
		href="<%=targetContent%>?cid=<%=workContext%>" class="js_content"><%=work.getFullpathName()%></a>
	</li>
	<%
		}
	%>
</ul>
