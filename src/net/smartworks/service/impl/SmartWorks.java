package net.smartworks.service.impl;

import net.smartworks.model.calendar.CompanyCalendar;
import net.smartworks.model.community.Department;
import net.smartworks.model.community.Group;
import net.smartworks.model.community.User;
import net.smartworks.model.community.WorkSpace;
import net.smartworks.model.instance.BoardInstance;
import net.smartworks.model.instance.EventInstance;
import net.smartworks.model.instance.WorkInstance;
import net.smartworks.model.notice.Notice;
import net.smartworks.model.notice.NoticeBox;
import net.smartworks.model.work.SmartWork;
import net.smartworks.model.work.WorkCategory;
import net.smartworks.server.service.ICalendarService;
import net.smartworks.server.service.ICommunityService;
import net.smartworks.server.service.INoticeService;
import net.smartworks.server.service.ITaskInstanceService;
import net.smartworks.server.service.IWorkInstanceService;
import net.smartworks.server.service.IWorkListService;
import net.smartworks.service.ISmartWorks;
import net.smartworks.util.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmartWorks implements ISmartWorks {

	ICommunityService communityService;
	INoticeService noticeService;
	ICalendarService calendarService;
	IWorkInstanceService workInstanceService;
	ITaskInstanceService taskInstanceService;
	IWorkListService workListService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#setCommunityService(net.smartworks
	 * .server.service.ICommunityService)
	 */
	@Autowired
	public void setCommunityService(ICommunityService communityService) {
		this.communityService = communityService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#setNoticeService(net.smartworks
	 * .server.service.INoticeService)
	 */
	@Autowired
	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@Autowired
	public void setCalendarService(ICalendarService calendarService) {
		this.calendarService = calendarService;
	}

	@Autowired
	public void setWorkInstanceService(IWorkInstanceService workInstanceService) {
		this.workInstanceService = workInstanceService;
	}

	@Autowired
	public void setTaskInstanceService(ITaskInstanceService taskInstanceService) {
		this.taskInstanceService = taskInstanceService;
	}

	@Autowired
	public void setWorkListService(IWorkListService workListService) {
		this.workListService = workListService;
	}

	@Override
	public Department[] getMyDepartments(String userId) throws Exception {
		return communityService.getMyDepartments(userId);
	}

	@Override
	public Department getDepartmentById(String departId) throws Exception {
		return communityService.getDepartmentById(departId);
	}

	@Override
	public Group[] getMyGroups(String userId) throws Exception {
		return communityService.getMyGroups(userId);
	}

	@Override
	public Group getGroupById(String groupId) throws Exception {
		return communityService.getGroupById(groupId);
	}

	@Override
	public User getUserById(String userId) throws Exception {
		return communityService.getUserById(userId);
	}

	@Override
	public WorkSpace[] searchCommunityList(String user, String key) throws Exception {
		return communityService.searchCommunityList(user, key);
	}

	@Override
	public User[] searchCommunityMemberList(String user, String key) throws Exception {
		return communityService.searchCommunityMemberList(user, key);
	}

	@Override
	public SmartWork[] searchWorkList(String user, String key) throws Exception {
		return workListService.searchWorkList(user, key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getWorkSpaceById(java.lang.String
	 * )
	 */
	@Override
	public WorkSpace getWorkSpaceById(String workSpaceId) throws Exception {
		return communityService.getWorkSpaceById(workSpaceId);
	}

	@Override
	public User[] getAvailableChatter() throws Exception {
		return communityService.getAvailableChatter();
	}

	@Override
	public User[] searchAvailableChatterList(String key) throws Exception {
		return communityService.searchAvailableChatterList(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.smartworks.service.impl.ISmartWorks#getBroadcastingMessages()
	 */
	@Override
	public String[] getBroadcastingMessages() throws Exception {
		return noticeService.getBroadcastingMessages();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getNoticesForMe(java.lang.String)
	 */
	@Override
	public Notice[] getNoticesForMe(String userId) throws Exception {
		return noticeService.getNoticesForMe(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.smartworks.service.impl.ISmartWorks#getNoticeBoxForMe10(int,
	 * net.smartworks.util.LocalDate)
	 */
	@Override
	public NoticeBox getNoticeBoxForMe10(int noticeType, LocalDate lastNotice) throws Exception {
		return noticeService.getNoticeBoxForMe10(noticeType, lastNotice);
	}

	@Override
	public CompanyCalendar[] getCompanyCalendars(LocalDate fromDate, int days) throws Exception {
		return calendarService.getCompanyCalendars(fromDate, days);
	}

	@Override
	public CompanyCalendar[] getCompanyCalendars(LocalDate fromDate, LocalDate toDate) throws Exception {
		return calendarService.getCompanyCalendars(fromDate, toDate);
	}

	@Override
	public EventInstance[] getEventInstances(LocalDate fromDate, int days) throws Exception {
		return calendarService.getEventInstances(fromDate, days);
	}

	@Override
	public EventInstance[] getEventInstances(LocalDate fromDate, LocalDate toDate) throws Exception {
		return calendarService.getEventInstances(fromDate, toDate);
	}

	@Override
	public CompanyCalendar getCompanyEventBox(LocalDate date) throws Exception {
		return calendarService.getCompanyEventBox(date);
	}

	@Override
	public EventInstance[] getCompanyEventsByDate(LocalDate date, int maxEvents) throws Exception {
		return calendarService.getCompanyEventsByDate(date, maxEvents);
	}

	@Override
	public EventInstance[] getMyEventsByDate(String userId, LocalDate date, int maxEvents) throws Exception {
		return calendarService.getMyEventsByDate(userId, date, maxEvents);
	}

	@Override
	public BoardInstance[] getBoardInstances(LocalDate fromDate, int days) throws Exception {
		return workInstanceService.getBoardInstances(fromDate, days);
	}

	@Override
	public BoardInstance[] getBoardInstances(LocalDate fromDate, LocalDate toDate) throws Exception {
		return workInstanceService.getBoardInstances(fromDate, toDate);
	}

	@Override
	public WorkInstance[] getMyRecentInstances(String userId) throws Exception {
		return workInstanceService.getMyRecentInstances(userId);
	}

	@Override
	public SmartWork[] getMyFavoriteWorks(String userId) throws Exception {
		return workListService.getMyFavoriteWorks(userId);
	}

	@Override
	public WorkCategory[] getMyWorkCategories(String userId) throws Exception {
		return workListService.getMyWorkCategories(userId);
	}

	@Override
	public SmartWork[] getMyAllWorksByCategoryId(String userId, String categoryId) throws Exception {
		return workListService.getMyAllWorksByCategoryId(userId, categoryId);
	}

	@Override
	public SmartWork[] getMyAllWorksByGroupId(String userId, String groupId) throws Exception {
		return workListService.getMyAllWorksByGroupId(userId, groupId);
	}

}
