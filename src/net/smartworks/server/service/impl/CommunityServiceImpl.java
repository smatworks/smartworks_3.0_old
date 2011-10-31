package net.smartworks.server.service.impl;

import net.smartworks.model.community.Department;
import net.smartworks.model.community.Group;
import net.smartworks.model.community.User;
import net.smartworks.model.community.WorkSpace;
import net.smartworks.server.service.ICommunityService;
import net.smartworks.util.SmartTest;
import net.smartworks.util.SmartUtil;

import org.springframework.stereotype.Service;

@Service
public class CommunityServiceImpl implements ICommunityService {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getMyDepartments(java.lang.String
	 * )
	 */
	@Override
	public Department[] getMyDepartments(String userId) throws Exception {
		return new Department[] { SmartTest.getDepartment1(), SmartTest.getDepartment2(), SmartTest.getDepartment3(), SmartTest.getDepartment4() };

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getDepartmentById(java.lang.String
	 * )
	 */
	@Override
	public Department getDepartmentById(String departId) throws Exception {
		Department[] departments = getMyDepartments(SmartUtil.getCurrentUser().getId());
		for (int i = 0; i < departments.length; i++) {
			if (departments[i].getId().equals(departId))
				return departments[i];
		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getMyGroups(java.lang.String)
	 */
	@Override
	public Group[] getMyGroups(String userId) throws Exception {
		return new Group[] { SmartTest.getGroup1(), SmartTest.getGroup2(), SmartTest.getGroup3() };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getGroupById(java.lang.String)
	 */
	@Override
	public Group getGroupById(String groupId) throws Exception {
		Group[] groups = getMyGroups(SmartUtil.getCurrentUser().getId());
		for (int i = 0; i < groups.length; i++) {
			if (groups[i].getId().equals(groupId))
				return groups[i];
		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getUserById(java.lang.String)
	 */
	@Override
	public User getUserById(String userId) throws Exception {
		if (SmartUtil.getCurrentUser().getId().equals(userId))
			return SmartUtil.getCurrentUser();
		else if (SmartTest.getUser1().getId().equals(userId))
			return SmartTest.getUser1();
		else if (SmartTest.getUser2().getId().equals(userId))
			return SmartTest.getUser2();
		return SmartUtil.getCurrentUser();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#searchCommunityList(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public WorkSpace[] searchCommunityList(String user, String key) throws Exception {
		WorkSpace[] comms = new WorkSpace[] { getMyGroups(user)[0], SmartTest.getUser1(), getMyDepartments(user)[1], SmartTest.getUser2() };
		return comms;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#searchCommunityMemberList(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public User[] searchCommunityMemberList(String user, String key) throws Exception {
		User[] users = new User[] { SmartTest.getUser1(), SmartTest.getUser2() };
		return users;

	}

}
