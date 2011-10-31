package net.smartworks.service.impl;

import net.smartworks.model.calendar.CompanyCalendar;
import net.smartworks.model.calendar.CompanyEvent;
import net.smartworks.model.calendar.WorkHour;
import net.smartworks.model.community.Department;
import net.smartworks.model.community.Group;
import net.smartworks.model.community.User;
import net.smartworks.model.community.WorkSpace;
import net.smartworks.model.instance.AsyncMessageInstance;
import net.smartworks.model.instance.BoardInstance;
import net.smartworks.model.instance.CommentsInstance;
import net.smartworks.model.instance.EventInstance;
import net.smartworks.model.instance.MailInstance;
import net.smartworks.model.instance.TaskInstance;
import net.smartworks.model.instance.WorkInstance;
import net.smartworks.model.notice.Notice;
import net.smartworks.model.notice.NoticeBox;
import net.smartworks.model.notice.NoticeMessage;
import net.smartworks.model.work.SmartWork;
import net.smartworks.model.work.SocialWork;
import net.smartworks.model.work.Work;
import net.smartworks.model.work.WorkCategory;
import net.smartworks.server.service.ICommunityService;
import net.smartworks.server.service.INoticeService;
import net.smartworks.service.ISmartWorks;
import net.smartworks.util.LocalDate;
import net.smartworks.util.LocaleInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmartWorks implements ISmartWorks {

	ICommunityService communityService;
	INoticeService noticeService;

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#setCommunityService(net.smartworks.server.service.ICommunityService)
	 */
	@Autowired
	public void setCommunityService(ICommunityService communityService) {
		this.communityService = communityService;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#setNoticeService(net.smartworks.server.service.INoticeService)
	 */
	@Autowired
	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#isSameContextPrefix(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isSameContextPrefix(String contextPrefix,
			String contextId) throws Exception {
		if (contextPrefix == null || contextId == null
				|| contextPrefix.length() >= contextId.length())
			return false;
		if (contextId.subSequence(0, contextPrefix.length()).equals(
				contextPrefix))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#isWorkContextType(java.lang.String)
	 */
	@Override
	public boolean isWorkContextType(String contextId) throws Exception {
		if (contextId == null || contextId.length() < 3)
			return false;
		if (contextId.substring(0, 3).equals("iw.")
				|| contextId.substring(0, 3).equals("pw.")
				|| contextId.substring(0, 3).equals("sw.")
				|| contextId.substring(0, 3).equals("fl.")
				|| contextId.substring(0, 3).equals("fl.")
				|| contextId.substring(0, 3).equals("mm.")
				|| contextId.substring(0, 3).equals("im.")
				|| contextId.substring(0, 3).equals("ev.")
				|| contextId.substring(0, 3).equals("bd.")
				|| contextId.substring(0, 3).equals("ml.")
				|| contextId.substring(0, 3).equals("sv."))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#isWorkSpaceContextType(java.lang.String)
	 */
	@Override
	public boolean isWorkSpaceContextType(String contextId)
			throws Exception {
		if (contextId == null || contextId.length() < 6)
			return false;
		if (contextId.substring(0, 6).equals("iw.sp.")
				|| contextId.substring(0, 6).equals("pw.sp.")
				|| contextId.substring(0, 6).equals("sw.sp.")
				|| contextId.substring(0, 6).equals("fl.sp.")
				|| contextId.substring(0, 6).equals("mm.sp.")
				|| contextId.substring(0, 6).equals("im.sp.")
				|| contextId.substring(0, 6).equals("ev.sp.")
				|| contextId.substring(0, 6).equals("bd.sp.")
				|| contextId.substring(0, 6).equals("ml.sp."))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#isTaskSpaceContextType(java.lang.String)
	 */
	@Override
	public boolean isTaskSpaceContextType(String contextId)
			throws Exception {
		if (contextId == null || contextId.length() < 6)
			return false;
		if (contextId.substring(0, 6).equals("iw.ts.")
				|| contextId.substring(0, 6).equals("pw.ts.")
				|| contextId.substring(0, 6).equals("sw.ts."))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#isCommunitySpaceContextType(java.lang.String)
	 */
	@Override
	public boolean isCommunitySpaceContextType(String contextId)
			throws Exception {
		if (contextId == null || contextId.length() < 6)
			return false;
		if (contextId.substring(0, 6).equals(SmartWorks.CONTEXT_PREFIX_DEPARTMENT_SPACE)
				|| contextId.substring(0, 6).equals(SmartWorks.CONTEXT_PREFIX_GROUP_SPACE)
				|| contextId.substring(0, 6).equals(SmartWorks.CONTEXT_PREFIX_USER_SPACE))
			return true;
		return false;
	}

	public String getSpaceIdFromContentContext(String contentContext)
			throws Exception {
		if (contentContext == null || contentContext.length() <= SmartWorks.CONTEXT_PREFIX_LENGTH)
			return null;
		return contentContext.substring(SmartWorks.CONTEXT_PREFIX_LENGTH);
	}
	
	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getWorkSpaceById(java.lang.String)
	 */
	@Override
	public WorkSpace getWorkSpaceById(String workSpaceId)
			throws Exception {
		WorkSpace workSpace = null;

		Department[] departments = getMyDepartments(getCurrentUser().getId());
		for (Department department : departments) {
			if (department.getId().equals(workSpaceId))
				return department;
		}
		Group[] groups = getMyGroups(getCurrentUser()
				.getId());
		for (Group group : groups) {
			if (group.getId().equals(workSpaceId))
				return group;
		}

		if (getUser1().getId().equals(workSpaceId))
			return getUser1();
		if (getUser2().getId().equals(workSpaceId))
			return getUser2();
		if (getUser3().getId().equals(workSpaceId))
			return getUser3();
		if (getCurrentUser().getId().equals(workSpaceId))
			return getCurrentUser();

		return workSpace;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getContextPrefixByWorkType(int, int)
	 */
	@Override
	public String getContextPrefixByWorkType(int smartWorkType,
			int spaceType) throws Exception {

		if (spaceType == SmartWorks.SPACE_TYPE_WORK_LIST) {
			if (smartWorkType == SmartWork.TYPE_INFORMATION)
				return SmartWorks.CONTEXT_PREFIX_IWORK_LIST;
			if (smartWorkType == SmartWork.TYPE_PROCESS)
				return SmartWorks.CONTEXT_PREFIX_PWORK_LIST;
			if (smartWorkType == SmartWork.TYPE_SCHEDULE)
				return SmartWorks.CONTEXT_PREFIX_SWORK_LIST;
		} else if(spaceType == SmartWorks.SPACE_TYPE_WORK_INSTANCE){
			if (smartWorkType == SmartWork.TYPE_INFORMATION)
				return SmartWorks.CONTEXT_PREFIX_IWORK_SPACE;
			if (smartWorkType == SmartWork.TYPE_PROCESS)
				return SmartWorks.CONTEXT_PREFIX_PWORK_SPACE;
			if (smartWorkType == SmartWork.TYPE_SCHEDULE)
				return SmartWorks.CONTEXT_PREFIX_SWORK_SPACE;
		} else if(spaceType == SmartWorks.SPACE_TYPE_TASK_INSTANCE){
			if (smartWorkType == SmartWork.TYPE_INFORMATION)
				return SmartWorks.CONTEXT_PREFIX_IWORK_TASK;
			if (smartWorkType == SmartWork.TYPE_PROCESS)
				return SmartWorks.CONTEXT_PREFIX_PWORK_TASK;
			if (smartWorkType == SmartWork.TYPE_SCHEDULE)
				return SmartWorks.CONTEXT_PREFIX_SWORK_TASK;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getTargetContentByWorkType(int, int)
	 */
	@Override
	public String getTargetContentByWorkType(int smartWorkType,
			int spaceType) throws Exception {

		if (spaceType == SmartWorks.SPACE_TYPE_WORK_LIST) {
			if (smartWorkType == SmartWork.TYPE_INFORMATION)
				return "iwork_list.sw";
			if (smartWorkType == SmartWork.TYPE_PROCESS)
				return "pwork_list.sw";
			if (smartWorkType == SmartWork.TYPE_SCHEDULE)
				return "swork_list.sw";
		} else if(spaceType == SmartWorks.SPACE_TYPE_WORK_INSTANCE){
			if (smartWorkType == SmartWork.TYPE_INFORMATION)
				return "iwork_space.sw";
			if (smartWorkType == SmartWork.TYPE_PROCESS)
				return "pwork_space.sw";
			if (smartWorkType == SmartWork.TYPE_SCHEDULE)
				return "swork_space.sw";
		} else if(spaceType == SmartWorks.SPACE_TYPE_TASK_INSTANCE){
			if (smartWorkType == SmartWork.TYPE_INFORMATION)
				return "iwork_task.sw";
			if (smartWorkType == SmartWork.TYPE_PROCESS)
				return "pwork_task.sw";
			if (smartWorkType == SmartWork.TYPE_SCHEDULE)
				return "swork_task.sw";

		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getBroadcastingMessages()
	 */
	@Override
	public String[] getBroadcastingMessages()
			throws Exception {
		return new String[] {"오늘 시스템 작업예정으로 오후 5시부터 한시간 동안 시스템을 사용할 수 없으니, 업무진행에 착오없으시길 바랍니다. -- 기술연구소 ---",
						 "금일 전체회식에 전원참석하여 좋은 친목의 시간이 되기를 바랍니다. --- 경영 기획팀 ----"};
	}
	
	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getCompanyCalendars(net.smartworks.util.LocalDate, int)
	 */
	@Override
	public CompanyCalendar[] getCompanyCalendars(LocalDate fromDate, int days) throws Exception{
		CompanyCalendar cc1 = new CompanyCalendar(new LocalDate(), new CompanyEvent[]{getCompanyEvent1(), getCompanyEvent2()}, new WorkHour());
		CompanyCalendar cc2 = new CompanyCalendar(new LocalDate((new LocalDate()).getTime()+LocalDate.ONE_DAY), new CompanyEvent[]{getCompanyEvent2()}, new WorkHour());
		CompanyCalendar cc3 = new CompanyCalendar(new LocalDate((new LocalDate()).getTime()+LocalDate.ONE_DAY*2), new CompanyEvent[]{getCompanyEvent1()}, new WorkHour());
		cc1.getDate().setLocale(LocaleInfo.LOCALE_KOREAN);
		cc2.getDate().setLocale(LocaleInfo.LOCALE_KOREAN);
		cc3.getDate().setLocale(LocaleInfo.LOCALE_KOREAN);
		return new CompanyCalendar[] {cc1, cc2, cc3};
		
	}
	
	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getCompanyCalendars(net.smartworks.util.LocalDate, net.smartworks.util.LocalDate)
	 */
	@Override
	public CompanyCalendar[] getCompanyCalendars(LocalDate fromDate, LocalDate toDate) throws Exception{
		return null;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getEventInstances(net.smartworks.util.LocalDate, int)
	 */
	@Override
	public EventInstance[] getEventInstances(LocalDate fromDate, int days) throws Exception{
		LocalDate time1 = new LocalDate(); time1.plusToGMTTime(-1*LocalDate.ONE_HOUR);
		LocalDate time2 = new LocalDate(); time2.plusToGMTTime(LocalDate.ONE_HOUR);		
		LocalDate time3 = new LocalDate(); time3.plusToGMTTime(3*LocalDate.ONE_HOUR);
		LocalDate time4 = new LocalDate(); time4.plusToGMTTime(5*LocalDate.ONE_HOUR);
		LocalDate time5 = new LocalDate(); time5.plusToGMTTime(LocalDate.ONE_DAY);
		LocalDate time6 = new LocalDate(); time6.plusToGMTTime(LocalDate.ONE_DAY+LocalDate.ONE_HOUR);
		LocalDate time7 = new LocalDate(); time7.plusToGMTTime(2*LocalDate.ONE_DAY+LocalDate.ONE_HOUR*3);
		LocalDate time8 = new LocalDate(); time8.plusToGMTTime(2*LocalDate.ONE_DAY+LocalDate.ONE_HOUR*7);
		LocalDate time9 = new LocalDate(); time9.plusToGMTTime(1*LocalDate.ONE_YEAR+LocalDate.ONE_HOUR*10);
		LocalDate time10 = new LocalDate(); time10.plusToGMTTime(1*LocalDate.ONE_YEAR+LocalDate.ONE_HOUR*14);
		EventInstance event1 = new EventInstance("event1", "정부장님 점심약속", new SocialWork("socialwork1", "Event Work"), getUser1(), new LocalDate());		
		event1.setStart(time1);
		event1.setEnd(time2);
		event1.setRelatedUsers(new User[]{getUser2()});

		EventInstance event2 = new EventInstance("event2", "스마트웍스닷넷 디자인회의", new SocialWork("socialwork1", "Event Work"), getUser2(), new LocalDate());
		event2.setStart(time3);
		event2.setEnd(time4);
		event2.setRelatedUsers(new User[]{getUser1(), getUser2(), getUser3()});
		event2.setWorkSpace(getGroup1());

		EventInstance event3 = new EventInstance("event3", "주간업무 보고회의", new SocialWork("socialwork1", "Event Work"), getUser3(), new LocalDate());
		event3.setStart(time5);
		event3.setEnd(time6);
		event3.setWorkSpace(getDepartment1());
		
		EventInstance event4 = new EventInstance("event4", "KT 스마트웍킹팀 저녁", new SocialWork("socialwork1", "Event Work"), getUser1(), new LocalDate());
		event4.setStart(time7);
		event4.setEnd(time8);
		
		EventInstance event5 = new EventInstance("event5", "진산회 골프모임", new SocialWork("socialwork1", "Event Work"), getCurrentUser(), new LocalDate());
		event5.setStart(time9);
		event5.setEnd(time10);
		return new EventInstance[] {event1, event2, event3, event4, event5};
	}
	
	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getEventInstances(net.smartworks.util.LocalDate, net.smartworks.util.LocalDate)
	 */
	@Override
	public EventInstance[] getEventInstances(LocalDate fromDate, LocalDate toDate) throws Exception{
		return null;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getBoardInstances(net.smartworks.util.LocalDate, int)
	 */
	@Override
	public BoardInstance[] getBoardInstances(LocalDate fromDate, int days) throws Exception{
		LocalDate time1 = new LocalDate(); time1.plusToGMTTime(-(1*LocalDate.ONE_HOUR));
		LocalDate time2 = new LocalDate(); time2.plusToGMTTime(-(LocalDate.ONE_HOUR));		
		LocalDate time3 = new LocalDate(); time3.plusToGMTTime(-(3*LocalDate.ONE_HOUR));
		LocalDate time4 = new LocalDate(); time4.plusToGMTTime(-(5*LocalDate.ONE_HOUR));
		LocalDate time5 = new LocalDate(); time5.plusToGMTTime(-(LocalDate.ONE_DAY));
		LocalDate time6 = new LocalDate(); time6.plusToGMTTime(-(LocalDate.ONE_DAY+LocalDate.ONE_HOUR));
		LocalDate time7 = new LocalDate(); time7.plusToGMTTime(-(2*LocalDate.ONE_DAY+LocalDate.ONE_HOUR*3));
		LocalDate time8 = new LocalDate(); time8.plusToGMTTime(-(2*LocalDate.ONE_DAY+LocalDate.ONE_HOUR*7));
		LocalDate time9 = new LocalDate(); time9.plusToGMTTime(-(10*LocalDate.ONE_DAY+LocalDate.ONE_HOUR*10));
		LocalDate time10 = new LocalDate(); time10.plusToGMTTime(-(10*LocalDate.ONE_DAY+LocalDate.ONE_HOUR*14));
		
		BoardInstance board1 = new BoardInstance("board1", "워크샵 일정계획 공지 합니다.", new SocialWork("socialwork1", "Board Work"), getCurrentUser(), time1);
		board1.setWorkSpace(getGroup2());
		BoardInstance board2 = new BoardInstance("board2", "하반기 해외 B2B마케팅 성공사례 세미나", new SocialWork("socialwork1", "Board Work"), getUser1(), time2);
		BoardInstance board3 = new BoardInstance("board3", "올레캠퍼스 자바개발자 교육과정 소개합니다.", new SocialWork("socialwork1", "Board Work"), getUser2(), time3);
		board3.setWorkSpace(getDepartment2());
		BoardInstance board4 = new BoardInstance("board4", "가을 조직개편 조직도 입니다.", new SocialWork("socialwork1", "Board Work"), getUser3(), time4);
		BoardInstance board5 = new BoardInstance("board5", "가을 정기 임직원 승진 발표", new SocialWork("socialwork1", "Board Work"), getUser3(), time5);
		BoardInstance board6 = new BoardInstance("board6", "2011년도 경영계획 공지합니다.", new SocialWork("socialwork1", "Board Work"), getCurrentUser(), time6);
		BoardInstance board7 = new BoardInstance("board7", "여름휴가 일정 공지합니다.", new SocialWork("socialwork1", "Board Work"), getCurrentUser(), time7);
		BoardInstance board8 = new BoardInstance("board8", "제품개발 프로젝트 전체 일정 계획공지합니다.", new SocialWork("socialwork1", "Board Work"), getUser2(), time8);
		board8.setWorkSpace(getDepartment1());
		BoardInstance board9 = new BoardInstance("board9", "사무실 이전 계획 입니다.", new SocialWork("socialwork1", "Board Work"), getUser1(), time9);
		BoardInstance board10 = new BoardInstance("board10", "스마트웍스닷넷 장기 로드맵 입니다.", new SocialWork("socialwork1", "Board Work"), getCurrentUser(), time10);
		return new BoardInstance[] {board1, board2, board3, board4, board5, board6, board7, board8, board9, board10};
	}
	
	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getBoardInstances(net.smartworks.util.LocalDate, net.smartworks.util.LocalDate)
	 */
	@Override
	public BoardInstance[] getBoardInstances(LocalDate fromDate, LocalDate toDate) throws Exception{
		return null;
	}

	private CompanyEvent getCompanyEvent1(){
		CompanyEvent event = new CompanyEvent("companyevent1", "창립기념일");
		event.setIsHoliday(false);
		event.setPlannedStart(new LocalDate());
		event.setPlannedEnd(new LocalDate());
		return event;
	}
	
	private CompanyEvent getCompanyEvent2(){
		CompanyEvent event = new CompanyEvent("companyevent2", "크리스마스");
		event.setIsHoliday(true);
		event.setPlannedStart(new LocalDate());
		LocalDate date1 = new LocalDate();
		date1.setGMTDate(date1.getGMTDate() + LocalDate.ONE_DAY*2);
		event.setPlannedEnd(date1);
		return event;
	}
	
	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getCompanyEventBox(net.smartworks.util.LocalDate)
	 */
	@Override
	public CompanyCalendar getCompanyEventBox(LocalDate date) throws Exception{
		CompanyCalendar eventBox = new CompanyCalendar();
		eventBox.setDate(date);
		CompanyEvent event1 = new CompanyEvent();
		eventBox.setCompanyEvents(new CompanyEvent[] {event1});
		return eventBox;
		
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getMyFavoriteWorks(java.lang.String)
	 */
	@Override
	public SmartWork[] getMyFavoriteWorks(String userId)
			throws Exception {

		return new SmartWork[] { getSmartWork1(), getSmartWork2(),
				getSmartWork3() };
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getMyWorkCategories(java.lang.String)
	 */
	@Override
	public WorkCategory[] getMyWorkCategories(String userId)
			throws Exception {

		return new WorkCategory[] { getWorkCategory1(), getWorkCategory2() };
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getMyAllWorksByCategoryId(java.lang.String, java.lang.String)
	 */
	@Override
	public SmartWork[] getMyAllWorksByCategoryId(String userId,
			String categoryId) throws Exception {

		SmartWork[] smartWorks = new SmartWork[] { getSmartWork1(),
				getSmartWork2(), getSmartWork3(), getSmartWork4(),
				getSmartWork5(), getSmartWork6() };

		int count = 0;
		for (SmartWork smartWork : smartWorks) {
			if (smartWork.getMyCategory().getId().equals(categoryId)) {
				count++;
			}
		}
		SmartWork[] resultWorks = new SmartWork[count];
		count = 0;
		for (SmartWork smartWork : smartWorks) {
			if (smartWork.getMyCategory().getId().equals(categoryId)) {
				resultWorks[count++] = smartWork;
			}
		}
		return resultWorks;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getMyAllWorksByGroupId(java.lang.String, java.lang.String)
	 */
	@Override
	public SmartWork[] getMyAllWorksByGroupId(String userId,
			String groupId) throws Exception {

		return new SmartWork[] { getSmartWork7(), getSmartWork8(),
				getSmartWork9() };
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getMyRecentInstances(java.lang.String)
	 */
	@Override
	public WorkInstance[] getMyRecentInstances(String userId)
			throws Exception {

		return new WorkInstance[] { getWorkInstance1(), getWorkInstance2(),
				getWorkInstance3(), getWorkInstance4(), getWorkInstance5() };
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getMyDepartments(java.lang.String)
	 */
	@Override
	public Department[] getMyDepartments(String userId) throws Exception {
		return new Department[] { getDepartment1(), getDepartment2(),
				getDepartment3(), getDepartment4() };

	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getDepartmentById(java.lang.String)
	 */
	@Override
	public Department getDepartmentById(String departId)
			throws Exception {
		Department[] departments = getMyDepartments(getCurrentUser().getId());
		for (int i = 0; i < departments.length; i++) {
			if (departments[i].getId().equals(departId))
				return departments[i];
		}
		return null;

	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getMyGroups(java.lang.String)
	 */
	@Override
	public Group[] getMyGroups(String userId) throws Exception {
		return new Group[] { getGroup1(), getGroup2(), getGroup3() };
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getGroupById(java.lang.String)
	 */
	@Override
	public Group getGroupById(String groupId) throws Exception {
		Group[] groups = getMyGroups(getCurrentUser().getId());
		for (int i = 0; i < groups.length; i++) {
			if (groups[i].getId().equals(groupId))
				return groups[i];
		}
		return null;

	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getCurrentUser()
	 */
	@Override
	public User getCurrentUser() throws Exception {
		User user = new User();
		user.setId("jisook@maninsoft.co.kr");
		user.setName("김지숙");
		user.setPosition("CEO");
		user.setDepartment("경영기획실 디자인팀");
		user.setLocale("ko"); // ko, en
		user.setTimeZone("SEOUL");
		user.setCompany("(주)맨인소프트");
		user.setPicturePath("images/");
		user.setOrgPictureName(user.getId() + ".jpg");
		user.setMinPictureName(user.getId() + "_min.gif");
		user.setMidPictureName(user.getId() + "_mid.gif");

		return user;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getUserById(java.lang.String)
	 */
	@Override
	public User getUserById(String userId) throws Exception {
		if (getCurrentUser().getId().equals(userId))
			return getCurrentUser();
		else if (getUser1().getId().equals(userId))
			return getUser1();
		else if (getUser2().getId().equals(userId))
			return getUser2();
		return getCurrentUser();
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#searchWorkList(java.lang.String, java.lang.String)
	 */
	@Override
	public SmartWork[] searchWorkList(String user, String key)
			throws Exception {
		return getMyFavoriteWorks(user);
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#searchCommunityList(java.lang.String, java.lang.String)
	 */
	@Override
	public WorkSpace[] searchCommunityList(String user, String key)
			throws Exception {
		WorkSpace[] comms = new WorkSpace[] { getMyGroups(user)[0],
				getUser1(), getMyDepartments(user)[1],
				getUser2() };
		return comms;

	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#searchCommunityMemberList(java.lang.String, java.lang.String)
	 */
	@Override
	public User[] searchCommunityMemberList(String user, String key)
			throws Exception {
		User[] users = new User[] { getUser1(),
				getUser2() };
		return users;

	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getAvailableChatter()
	 */
	@Override
	public User[] getAvailableChatter() throws Exception {
		User[] chatters = new User[] { getUser2(),
				getUser1(), getCurrentUser(),
				getUser2(), getUser1(),
				getCurrentUser(), getUser2(),
				getUser1(), getCurrentUser(),
				getUser2(), getUser1(),
				getCurrentUser() };
		return chatters;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#searchAvailableChatterList(java.lang.String)
	 */
	@Override
	public User[] searchAvailableChatterList(String key)
			throws Exception {
		User[] chatters = new User[] { getUser2(),
				getUser1(), getCurrentUser(),
				getUser2(), getUser1(),
				getCurrentUser(), getUser2(),
				getUser1(), getCurrentUser(),
				getUser2(), getUser1(),
				getCurrentUser() };
		return chatters;

	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getCompanyEventsByDate(net.smartworks.util.LocalDate, int)
	 */
	@Override
	public EventInstance[] getCompanyEventsByDate(LocalDate date,
			int maxEvents) throws Exception {
		EventInstance[] events = new EventInstance[] {

		};
		return events;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getMyEventsByDate(java.lang.String, net.smartworks.util.LocalDate, int)
	 */
	@Override
	public EventInstance[] getMyEventsByDate(String userId, LocalDate date,
			int maxEvents) throws Exception {
		EventInstance[] events = new EventInstance[] {

		};
		return events;
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getNoticesForMe(java.lang.String)
	 */
	@Override
	public Notice[] getNoticesForMe(String userId) throws Exception {
		return new Notice[] { new Notice(Notice.TYPE_NOTIFICATION, 1),
				new Notice(Notice.TYPE_MESSAGE, 0),
				new Notice(Notice.TYPE_COMMENTS, 29),
				new Notice(Notice.TYPE_ASSIGNED, 0),
				new Notice(Notice.TYPE_MAILBOX, 420),
				new Notice(Notice.TYPE_SAVEDBOX, 7) };
	}

	/* (non-Javadoc)
	 * @see net.smartworks.service.impl.ISmartWorks#getNoticeBoxForMe10(int, net.smartworks.util.LocalDate)
	 */
	@Override
	public NoticeBox getNoticeBoxForMe10(int noticeType,
			LocalDate lastNotice) throws Exception {
		if (noticeType == Notice.TYPE_NOTIFICATION) {
			NoticeBox noticeBox = new NoticeBox();
			noticeBox.setNoticeMessages(getNotificationMessages());
			noticeBox.setNoticeType(Notice.TYPE_NOTIFICATION);
			noticeBox.setDateOfLastNotice(new LocalDate());
			noticeBox.setRemainingLength(48);
			return noticeBox;
		}

		if (noticeType == Notice.TYPE_MESSAGE) {
			NoticeBox noticeBox = new NoticeBox();
			noticeBox.setNoticeMessages(getMessageMessages());
			noticeBox.setNoticeType(Notice.TYPE_MESSAGE);
			noticeBox.setDateOfLastNotice(new LocalDate());
			noticeBox.setRemainingLength(48);
			return noticeBox;
		}

		if (noticeType == Notice.TYPE_COMMENTS) {
			NoticeBox noticeBox = new NoticeBox();
			noticeBox.setNoticeMessages(getCommentsMessages());
			noticeBox.setNoticeType(Notice.TYPE_COMMENTS);
			noticeBox.setDateOfLastNotice(new LocalDate());
			noticeBox.setRemainingLength(48);
			return noticeBox;
		}

		if (noticeType == Notice.TYPE_ASSIGNED) {
			NoticeBox noticeBox = new NoticeBox();
			noticeBox.setNoticeMessages(getAssignedMessages());
			noticeBox.setNoticeType(Notice.TYPE_ASSIGNED);
			noticeBox.setDateOfLastNotice(new LocalDate());
			noticeBox.setRemainingLength(48);
			return noticeBox;
		}

		if (noticeType == Notice.TYPE_MAILBOX) {
			NoticeBox noticeBox = new NoticeBox();
			noticeBox.setNoticeMessages(getMailboxMessages());
			noticeBox.setNoticeType(Notice.TYPE_MAILBOX);
			noticeBox.setDateOfLastNotice(new LocalDate());
			noticeBox.setRemainingLength(48);
			return noticeBox;
		}

		if (noticeType == Notice.TYPE_SAVEDBOX) {
			NoticeBox noticeBox = new NoticeBox();
			noticeBox.setNoticeMessages(getSavedboxMessages());
			noticeBox.setNoticeType(Notice.TYPE_SAVEDBOX);
			noticeBox.setDateOfLastNotice(new LocalDate());
			noticeBox.setRemainingLength(48);
			return noticeBox;
		}

		return null;
	}

	/*
	 * public Date getLocalDate(Date utcDate) throws Exception{ User
	 * currentUser = getCurrentUser(); Calendar cal = Calendar.getInstance();
	 * cal.setTimeZone(currentUser.getTimeZone()); cal.setTime(utcDate); return
	 * cal.getTime(); }
	 */
	// ************************** 테스트용 데이터
	// ******************************************//
	// ************************** 테스트용 데이터
	// ******************************************//

	private User getUser1() throws Exception {
		User user = new User();
		user.setId("kmyu@maninsoft.co.kr");
		user.setName("유광민");
		user.setPosition("기술연구소장");
		user.setDepartment("기술사업팀");
		user.setLocale("ko_KR"); // ko_KR, en_US
		user.setTimeZone("SEOUL");
		user.setCompany("(주)맨인소프트");
		user.setPicturePath("images/");
		user.setOrgPictureName(user.getId() + ".jpg");
		user.setMinPictureName(user.getId() + "_min.gif");
		user.setMidPictureName(user.getId() + "_mid.gif");

		return user;
	}

	private User getUser2() throws Exception {
		User user = new User();
		user.setId("hsshin@maninsoft.co.kr");
		user.setName("신현성");
		user.setPosition("개발팀");
		user.setDepartment("기술연구소");
		user.setLocale("ko_KR"); // ko_KR, en_US
		user.setTimeZone("SEOUL");
		user.setCompany("(주)맨인소프트");
		user.setPicturePath("images/");
		user.setOrgPictureName(user.getId() + ".jpg");
		user.setMinPictureName(user.getId() + "_min.gif");
		user.setMidPictureName(user.getId() + "_mid.gif");

		return user;
	}

	private User getUser3() throws Exception {
		User user = new User();
		user.setId("hjlee@maninsoft.co.kr");
		user.setName("이현정");
		user.setPosition("대리");
		user.setDepartment("경영기획팀");
		user.setLocale("ko_KR"); // ko_KR, en_US
		user.setTimeZone("SEOUL");
		user.setCompany("(주)맨인소프트");
		user.setPicturePath("images/");
		user.setOrgPictureName(user.getId() + ".jpg");
		user.setMinPictureName(user.getId() + "_min.gif");
		user.setMidPictureName(user.getId() + "_mid.gif");

		return user;
	}

	private WorkCategory getWorkCategory1() throws Exception {
		return new WorkCategory("cat1", "공통업무");
	}

	private WorkCategory getWorkCategory2() throws Exception {
		return new WorkCategory("cat2", "영업관리");
	}

	private SmartWork getSmartWork1() throws Exception {
		return new SmartWork("work1", "근태품의", SmartWork.TYPE_PROCESS, "",
				getWorkCategory1());
	}

	private SmartWork getSmartWork2() throws Exception {
		return new SmartWork("work2", "회의록", SmartWork.TYPE_INFORMATION,
				"", getWorkCategory1());
	}

	private SmartWork getSmartWork3() throws Exception {
		return new SmartWork("work3", "구매기안", SmartWork.TYPE_PROCESS, "",
				getWorkCategory1());
	}

	private SmartWork getSmartWork4() throws Exception {
		return new SmartWork("work4", "제안견적프로세스", SmartWork.TYPE_PROCESS,
				"", getWorkCategory2());
	}

	private SmartWork getSmartWork5() throws Exception {
		return new SmartWork("work5", "영업기회", SmartWork.TYPE_INFORMATION,
				"", getWorkCategory2());
	}

	private SmartWork getSmartWork6() throws Exception {
		return new SmartWork("work6", "자료실", SmartWork.TYPE_GROUP, "",
				getWorkCategory2());
	}

	private SmartWork getSmartWork7() throws Exception {
		return new SmartWork("work11", "구매프로세스", SmartWork.TYPE_PROCESS,
				"", getWorkCategory2());
	}

	private SmartWork getSmartWork8() throws Exception {
		return new SmartWork("work21", "구매발주서",
				SmartWork.TYPE_INFORMATION, "", getWorkCategory2());
	}

	private SmartWork getSmartWork9() throws Exception {
		return new SmartWork("work31", "자재발주서", SmartWork.TYPE_PROCESS,
				"", getWorkCategory2());
	}

	private Group getGroup1() throws Exception {
		return new Group("group1", "SmartWorks.net V3 TFT", new User[] {
				getUser1(), getUser2() }, getUser1());
	}

	private Group getGroup2() throws Exception {
		return new Group("group2", "한라공조 협력업체 정보화시스템 고도화 프로젝트", new User[] {
				getCurrentUser(), getUser3() }, getUser2());
	}

	private Group getGroup3() throws Exception {
		return new Group("group3", "금성출판사 그룹웨어 프로젝트",
				new User[] { getUser2() }, getCurrentUser());
	}

	private WorkInstance getWorkInstance1() throws Exception {
		return new WorkInstance("inst1", "휴가 신청합니다.", getSmartWork1(),
				getUser1(), new LocalDate());
	}

	private WorkInstance getWorkInstance2() throws Exception {
		return new WorkInstance("inst2", "스마트웍스 3.0 개발계획 회의록 입니다.",
				getSmartWork2(), getUser1(), new LocalDate());
	}

	private WorkInstance getWorkInstance3() throws Exception {
		return new WorkInstance("inst3", "노트북 구매 기안합니다.", getSmartWork3(),
				getUser1(), new LocalDate());
	}

	private WorkInstance getWorkInstance4() throws Exception {
		return new WorkInstance("inst4", "금성출판사 스마트웍스 프로젝트", getSmartWork5(),
				getUser1(), new LocalDate());
	}

	private WorkInstance getWorkInstance5() throws Exception {
		return new WorkInstance("inst5", "삼성 노트북 구매발주서", getSmartWork8(),
				getUser3(), new LocalDate());
	}

	private Department getDepartment1() throws Exception {
		return new Department("depart1", "(주)맨인소프트", new User[] {
				getCurrentUser(), getUser3() }, getCurrentUser());
	}

	private Department getDepartment2() throws Exception {
		return new Department("depart2", "대표이사", new User[] { getUser3(),
				getUser2() }, getUser1());
	}

	private Department getDepartment3() throws Exception {
		return new Department("depart3", "경영기획본부", new User[] { getUser2() },
				getUser2());
	}

	private Department getDepartment4() throws Exception {
		return new Department("depart4", "기술사업본부", new User[] { getUser1(),
				getCurrentUser() }, getCurrentUser());
	}

	private EventInstance getEventInstance1() throws Exception {
		EventInstance event = new EventInstance("event1", "한라공조 협력업체 설명회",
				new Work("work1", "개인일정"), getCurrentUser(),
				new LocalDate());
		event.setStart(new LocalDate());
		event.setEnd(new LocalDate());
		return event;

	}

	private TaskInstance getTaskInstance1() throws Exception {
		TaskInstance taskInstance = new TaskInstance("taskInstance1", "대표이사승인",
				TaskInstance.TASK_TYPE_PROCESSWORK_TASK_ASSIGNED,
				getUser2(), new LocalDate());
		taskInstance.setWorkInstance(getWorkInstance1());
		taskInstance.setAssignee(getCurrentUser());
		return taskInstance;
	}

	private NoticeMessage getNotificationMessage(int noticeType)
			throws Exception {

		NoticeMessage notice1, notice2, notice3, notice4, notice5;
		if (noticeType == NoticeMessage.TYPE_SYSTEM_NOTICE) {
			notice1 = new NoticeMessage("notice1",
					NoticeMessage.TYPE_SYSTEM_NOTICE,
					getUser1(), new LocalDate());
			notice1.setMessage("금주 주말(토요일, 일요일)에 시스템 정기점검을 실시하는 관계를 시스템을 1시간 가량 사용할 수 없으니 이점 양해 바랍니다.");
			return notice1;
		}

		if (noticeType == NoticeMessage.TYPE_EVENT_ALARM) {
			notice2 = new NoticeMessage("notic2",
					NoticeMessage.TYPE_EVENT_ALARM,
					getUser2(), new LocalDate());
			notice2.setEvent(getEventInstance1());
			return notice2;
		}
		if (noticeType == NoticeMessage.TYPE_TASK_DELAYED) {
			notice3 = new NoticeMessage("notice3",
					NoticeMessage.TYPE_TASK_DELAYED,
					getCurrentUser(), new LocalDate());
			notice3.setInstance(getTaskInstance1());
			return notice3;
		}

		if (noticeType == NoticeMessage.TYPE_JOIN_REQUEST) {
			notice4 = new NoticeMessage("notice4",
					NoticeMessage.TYPE_JOIN_REQUEST,
					getUser1(), new LocalDate());
			notice4.setGroup(getGroup1());
			notice4.setMessage("님이 커뮤너티에 가입을 신청하셨습니다.");
			return notice4;
		}
		if (noticeType == NoticeMessage.TYPE_INSTANCE_CREATED) {
			notice5 = new NoticeMessage("notice5",
					NoticeMessage.TYPE_INSTANCE_CREATED,
					getUser1(), new LocalDate());
			notice5.setInstance(getWorkInstance1());
			notice5.setMessage("새로운 업무를 등록하였습니다..");
			return notice5;
		}
		return null;

	}

	private NoticeMessage[] getNotificationMessages() throws Exception {

		NoticeMessage notice1, notice2, notice3, notice4, notice5;
		notice1 = new NoticeMessage("notice1",
				NoticeMessage.TYPE_SYSTEM_NOTICE,
				getUser1(), new LocalDate());
		notice1.setMessage("금주 주말(토요일, 일요일)에 시스템 정기점검을 실시하는 관계를 시스템을 1시간 가량 사용할 수 없으니 이점 양해 바랍니다.");
		notice2 = new NoticeMessage("notic2",
				NoticeMessage.TYPE_EVENT_ALARM,
				getUser2(), new LocalDate());
		notice2.setEvent(getEventInstance1());
		notice3 = new NoticeMessage("notice3",
				NoticeMessage.TYPE_TASK_DELAYED,
				getCurrentUser(), new LocalDate());
		notice3.setInstance(getTaskInstance1());
		notice4 = new NoticeMessage("notice4",
				NoticeMessage.TYPE_JOIN_REQUEST,
				getUser1(), new LocalDate());
		notice4.setGroup(getGroup1());
		notice4.setMessage("님이 커뮤너티에 가입을 신청하셨습니다.");
		notice5 = new NoticeMessage("notice5",
				NoticeMessage.TYPE_INSTANCE_CREATED,
				getUser1(), new LocalDate());
		notice5.setInstance(getWorkInstance1());
		notice5.setMessage("새로운 업무를 등록하였습니다..");
		return new NoticeMessage[] { notice1, notice2, notice3, notice4,
				notice5 };

	}

	private NoticeMessage[] getMessageMessages() throws Exception {

		NoticeMessage notice1, notice2, notice3, notice4, notice5;
		AsyncMessageInstance messageInstance1 = new AsyncMessageInstance(
				"message1", getUser1(), new LocalDate(), "안녕하세요?  잘지내시죠??? ㅎㅎㅎ");
		notice1 = new NoticeMessage("notice21", 0, getUser1(),
				new LocalDate());
		notice1.setInstance(messageInstance1);

		AsyncMessageInstance messageInstance2 = new AsyncMessageInstance(
				"message2", getUser2(), new LocalDate(),
				"일간 한번 찾아뵙겠습니다. 그동안 몇번 연락드렸었는데, 연락이 안되던데요???");
		notice2 = new NoticeMessage("notice22", 0, getUser1(),
				new LocalDate());
		notice2.setInstance(messageInstance2);

		AsyncMessageInstance messageInstance3 = new AsyncMessageInstance(
				"message3", getUser3(), new LocalDate(), "누구시더라????ㅠ");
		notice3 = new NoticeMessage("notice23", 0, getUser1(),
				new LocalDate());
		notice3.setInstance(messageInstance3);

		return new NoticeMessage[] { notice1, notice2, notice3 };

	}

	private NoticeMessage[] getCommentsMessages() throws Exception {

		NoticeMessage notice1, notice2, notice3, notice4, notice5;
		CommentsInstance commentsInstance1 = new CommentsInstance("comments1",
				CommentsInstance.COMMENTS_TYPE_ON_WORK_DESC,
				"조금더 보강해야 될것 같은데요????", getUser3(), new LocalDate());
		commentsInstance1.setWork(getSmartWork1());
		notice1 = new NoticeMessage("notice21", 0, getUser3(), new LocalDate());
		notice1.setInstance(commentsInstance1);

		CommentsInstance commentsInstance2 = new CommentsInstance("comments2",
				CommentsInstance.COMMENTS_TYPE_ON_WORK_MANUAL, "잘모르겠습니다. ㅠㅠ",
				getUser1(), new LocalDate());
		commentsInstance2.setWork(getSmartWork3());
		notice2 = new NoticeMessage("notice22", 0, getUser1(), new LocalDate());
		notice2.setInstance(commentsInstance2);

		CommentsInstance commentsInstance3 = new CommentsInstance("comments3",
				CommentsInstance.COMMENTS_TYPE_ON_WORK_INSTANCE, "휴가잘다녀오세요!!!",
				getUser2(), new LocalDate());
		commentsInstance3.setWorkInstance(getWorkInstance1());
		notice3 = new NoticeMessage("notice23", 0, getUser2(), new LocalDate());
		notice3.setInstance(commentsInstance3);

		CommentsInstance commentsInstance4 = new CommentsInstance("comments4",
				CommentsInstance.COMMENTS_TYPE_ON_TASK_INSTANCE,
				"재 기안해 주시기 바랍니다...", getUser3(), new LocalDate());
		commentsInstance4.setTaskInstance(getTaskInstance1());
		notice4 = new NoticeMessage("notice24", 0, getUser3(), new LocalDate());
		notice4.setInstance(commentsInstance4);

		return new NoticeMessage[] { notice1, notice2, notice3, notice4 };

	}

	private NoticeMessage[] getAssignedMessages() throws Exception {

		NoticeMessage notice1, notice2, notice3, notice4, notice5;
		TaskInstance assignedInstance1 = new TaskInstance("assignedtask1",
				"대표이사 승인", TaskInstance.TASK_TYPE_PROCESSWORK_TASK_ASSIGNED,
				getUser3(), new LocalDate());
		assignedInstance1.setWorkInstance(getWorkInstance1());
		notice1 = new NoticeMessage("notice31", 0, getUser1(), new LocalDate());
		notice1.setInstance(assignedInstance1);

		TaskInstance assignedInstance2 = new TaskInstance("assignedtask2",
				"구매기안 기안제출", TaskInstance.TASK_TYPE_PROCESSWORK_TASK_FORWARDED,
				getUser3(), new LocalDate());
		notice2 = new NoticeMessage("notice32", 0, getUser2(), new LocalDate());
		assignedInstance2.setWorkInstance(getWorkInstance3());
		notice2.setInstance(assignedInstance2);

		TaskInstance assignedInstance3 = new TaskInstance("assignedtask3",
				"반도체회사 제안서 공유합니다.",
				TaskInstance.TASK_TYPE_INFORMATIONWORK_TASK_FORWARDED,
				getUser3(), new LocalDate());
		notice3 = new NoticeMessage("notice33", 0, getUser3(), new LocalDate());
		assignedInstance3.setWorkInstance(getWorkInstance2());
		notice3.setInstance(assignedInstance3);

		TaskInstance assignedInstance4 = new TaskInstance("assignedtask4",
				"검토자 결재", TaskInstance.TASK_TYPE_APPROVALWORK_TASK_ASSIGNED,
				getUser3(), new LocalDate());
		notice4 = new NoticeMessage("notice34", 0, getCurrentUser(), new LocalDate());
		assignedInstance4.setWorkInstance(getWorkInstance4());
		notice4.setInstance(assignedInstance4);

		TaskInstance assignedInstance5 = new TaskInstance("assignedtask5",
				"일일보고 입니다.",
				TaskInstance.TASK_TYPE_INFORMATIONWORK_TASK_ASSIGNED,
				getUser3(), new LocalDate());
		notice5 = new NoticeMessage("notice35", 0, getCurrentUser(), new LocalDate());
		assignedInstance5.setWorkInstance(getWorkInstance5());
		notice5.setInstance(assignedInstance5);

		return new NoticeMessage[] { notice1, notice2, notice3, notice4,
				notice5 };
	}

	private NoticeMessage[] getMailboxMessages() throws Exception {

		NoticeMessage notice1, notice2, notice3, notice4, notice5;
		MailInstance mailInstance1 = new MailInstance("mailinst1", "하이닉스프로젝트관련 회의록입니다.", getUser3(), new LocalDate());
		mailInstance1.setMailCategory(getWorkCategory1());
		mailInstance1.setMailGroup(getSmartWork6());
		notice1 = new NoticeMessage("notice41", 0, getUser3(), new LocalDate());
		notice1.setInstance(mailInstance1);

		MailInstance mailInstance2 = new MailInstance("mailinst2", "내용검토하시고 의견주시기 바랍니다.", getUser1(), new LocalDate());
		mailInstance2.setMailCategory(getWorkCategory1());
		notice2 = new NoticeMessage("notice42", 0, getUser3(), new LocalDate());
		notice2.setInstance(mailInstance2);

		MailInstance mailInstance3 = new MailInstance("mailinst3", "연락처입니다.", getUser3(), new LocalDate());
		mailInstance3.setMailCategory(getWorkCategory1());
		mailInstance3.setMailGroup(getSmartWork6());
		notice3 = new NoticeMessage("notice43", 0, getUser3(), new LocalDate());
		notice3.setInstance(mailInstance3);


		return new NoticeMessage[] { notice1, notice2, notice3};
	}

	private NoticeMessage[] getSavedboxMessages() throws Exception {

		NoticeMessage[] mailboxNotices = getMailboxMessages();
		
		return mailboxNotices;
	}
}