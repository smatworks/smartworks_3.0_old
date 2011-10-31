<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.*"%>
<%@ page import="net.smartworks.model.community.*"%>
<%
	SmartWorks smartWorks = (SmartWorks) request
			.getAttribute("smartWorks");
	String key = request.getParameter("key");
	WorkSpace[] communities = smartWorks.searchCommunityList(SmartWorks
			.getCurrentUser().getId(), key);
%>

<ul>
	<%
		for (WorkSpace workSpace : communities) {
			String picName = null, comContext = null, targetContent = null, comName = null, comId = null;
			if (workSpace.getClass() == User.class) {
				User user = (User) workSpace;
				picName = user.getMinPicture();
				comContext = SmartWorks.CONTEXT_PREFIX_USER_SPACE
						+ user.getId();
				targetContent = "user_space.sw";
				comName = user.getName();
				comId = user.getId();
			} else if (workSpace.getClass() == Department.class) {
				Department depart = (Department) workSpace;
				picName = depart.getMinPicture();
				comContext = SmartWorks.CONTEXT_PREFIX_DEPARTMENT_SPACE
						+ depart.getId();
				targetContent = "department_space.sw";
				comName = depart.getName();
				comId = depart.getId();
			} else if (workSpace.getClass() == Group.class) {
				Group group = (Group) workSpace;
				picName = group.getMinPicture();
				comContext = SmartWorks.CONTEXT_PREFIX_GROUP_SPACE
						+ group.getId();
				targetContent = "group_space.sw";
				comName = group.getName();
				comId = group.getId();
			}
	%>
	<li><img src="<%=picName%>" border="0"><a
		href="<%=targetContent%>?cid=<%=comContext%>&wid=<%=comId%>"><%=comName%></a>
	</li>
	<%
		}
	%>
</ul>
