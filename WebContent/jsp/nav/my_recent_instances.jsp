<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.*"%>
<%@ page import="net.smartworks.model.instance.*"%>
<%@ page import="net.smartworks.model.work.*"%>
<%@ page import="net.smartworks.model.community.*" %>

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
			User owner = workInstance.getOwner();
			String userContext = SmartWorks.CONTEXT_PREFIX_USER_SPACE + owner.getId();
			if (workInstance.getWork().getType() == SmartWork.TYPE_PROCESS) {
				iconType = "ico_pworks";
				instanceContext = SmartWorks.CONTEXT_PREFIX_PWORK_SPACE
						+ workInstance.getId();
				targetContent = "pwork_space.sw";
			} else if (workInstance.getWork().getType() == SmartWork.TYPE_INFORMATION) {
				iconType = "ico_iworks";
				instanceContext = SmartWorks.CONTEXT_PREFIX_IWORK_SPACE
						+ workInstance.getId();
				targetContent = "iwork_space.sw";
			} else if (workInstance.getWork().getType() == SmartWork.TYPE_SCHEDULE) {
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
	<li><a href="user_space.sw?cid=<%=userContext %>"><img src="<%=owner.getMinPicture()%>"
		title="<%=owner.getLongName() %>" border="0"></a><a
		href="<%=targetContent%>?cid=<%=instanceContext%>&wid=<%=workSpaceId%>"
		class="<%=classType%>" title="<%=work.getFullpathName()%>"><%=workInstance.getSubject()%></a>
	</li>
	<%
		}
	%>
</ul>