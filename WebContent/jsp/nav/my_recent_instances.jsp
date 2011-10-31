<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.*"%>
<%@ page import="net.smartworks.model.instance.*"%>
<%@ page import="net.smartworks.model.work.*"%>

<%
	SmartWorks smartWorks = (SmartWorks) request
			.getAttribute("smartWorks");
	WorkInstance[] workInstances = smartWorks
			.getMyRecentInstances("currentUser");
	String iconType = null;
	String classType = null;
	String instanceContext = null;
	String targetContent = null;
%>

<ul>
	<%
		for (WorkInstance workInstance : workInstances) {
			SmartWork work = (SmartWork) workInstance.getWork();
			String workSpaceId = workInstance.getWorkSpace().getId();
			if (workInstance.getWork().getType() == SmartWork.WORK_TYPE_PROCESS) {
				iconType = "ico_pworks";
				instanceContext = SmartWorks.CONTEXT_PREFIX_PWORK_SPACE
						+ workInstance.getId();
				targetContent = "pwork_space.sw";
			} else if (workInstance.getWork().getType() == SmartWork.WORK_TYPE_INFORMATION) {
				iconType = "ico_iworks";
				instanceContext = SmartWorks.CONTEXT_PREFIX_IWORK_SPACE
						+ workInstance.getId();
				targetContent = "iwork_space.sw";
			} else if (workInstance.getWork().getType() == SmartWork.WORK_TYPE_SCHEDULE) {
				iconType = "ico_sworks";
				instanceContext = SmartWorks.CONTEXT_PREFIX_SWORK_SPACE
						+ workInstance.getId();
				targetContent = "swork_space.sw";
			}
			if (workSpaceId != null
					&& !workSpaceId.equals(SmartWorks.getCurrentUser()
							.getId())) {
				classType = "";
			} else {
				classType = "js_content";
			}
	%>
	<li><img src="<%=workInstance.getOwner().getMinPicture()%>"
		border="0"><a
		href="<%=targetContent%>?cid=<%=instanceContext%>&wid=<%=workSpaceId%>"
		class="<%=classType%>" title="<%=work.getFullpathName()%>"><%=workInstance.getSubject()%></a>
	</li>
	<%
		}
	%>
</ul>