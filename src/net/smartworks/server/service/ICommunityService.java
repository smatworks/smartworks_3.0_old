package net.smartworks.server.service;

import net.smartworks.model.community.Department;
import net.smartworks.model.community.Group;
import net.smartworks.model.community.User;
import net.smartworks.model.community.WorkSpace;

public interface ICommunityService {

	public abstract User[] searchCommunityMemberList(String user, String key) throws Exception;

	public abstract WorkSpace[] searchCommunityList(String user, String key) throws Exception;

	public abstract User getUserById(String userId) throws Exception;

	public abstract Group getGroupById(String groupId) throws Exception;

	public abstract Group[] getMyGroups(String userId) throws Exception;

	public abstract Department getDepartmentById(String departId) throws Exception;

	public abstract Department[] getMyDepartments(String userId) throws Exception;

	public abstract WorkSpace getWorkSpaceById(String workSpaceId) throws Exception;

	public abstract User[] getAvailableChatter() throws Exception;

	public abstract User[] searchAvailableChatterList(String key) throws Exception;

}
