package net.smartworks.service.impl;

import net.smartworks.model.calendar.CompanyCalendar;
import net.smartworks.model.calendar.CompanyEvent;
import net.smartworks.model.calendar.WorkHour;
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
import net.smartworks.model.work.SocialWork;
import net.smartworks.model.work.WorkCategory;
import net.smartworks.server.service.ICommunityService;
import net.smartworks.server.service.INoticeService;
import net.smartworks.service.ISmartWorks;
import net.smartworks.util.LocalDate;
import net.smartworks.util.LocaleInfo;
import net.smartworks.util.SmartTest;
import net.smartworks.util.SmartUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmartWorks implements ISmartWorks {

	ICommunityService communityService;
	INoticeService noticeService;

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
		return null;
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
		WorkSpace workSpace = null;

		Department[] departments = getMyDepartments(SmartUtil.getCurrentUser().getId());
		for (Department department : departments) {
			if (department.getId().equals(workSpaceId))
				return department;
		}
		Group[] groups = getMyGroups(SmartUtil.getCurrentUser().getId());
		for (Group group : groups) {
			if (group.getId().equals(workSpaceId))
				return group;
		}

		if (SmartTest.getUser1().getId().equals(workSpaceId))
			return SmartTest.getUser1();
		if (SmartTest.getUser2().getId().equals(workSpaceId))
			return SmartTest.getUser2();
		if (SmartTest.getUser3().getId().equals(workSpaceId))
			return SmartTest.getUser3();
		if (SmartUtil.getCurrentUser().getId().equals(workSpaceId))
			return SmartUtil.getCurrentUser();

		return workSpace;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.smartworks.service.impl.ISmartWorks#getBroadcastingMessages()
	 */
	@Override
	public String[] getBroadcastingMessages() throws Exception {
		return new String[] { "오늘 시스템 작업예정으로 오후 5시부터 한시간 동안 시스템을 사용할 수 없으니, 업무진행에 착오없으시길 바랍니다. -- 기술연구소 ---",
				"금일 전체회식에 전원참석하여 좋은 친목의 시간이 되기를 바랍니다. --- 경영 기획팀 ----" };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getCompanyCalendars(net.smartworks
	 * .util.LocalDate, int)
	 */
	@Override
	public CompanyCalendar[] getCompanyCalendars(LocalDate fromDate, int days) throws Exception {
		CompanyCalendar cc1 = new CompanyCalendar(new LocalDate(), new CompanyEvent[] { SmartTest.getCompanyEvent1(), SmartTest.getCompanyEvent2() },
				new WorkHour());
		CompanyCalendar cc2 = new CompanyCalendar(new LocalDate((new LocalDate()).getTime() + LocalDate.ONE_DAY),
				new CompanyEvent[] { SmartTest.getCompanyEvent2() }, new WorkHour());
		CompanyCalendar cc3 = new CompanyCalendar(new LocalDate((new LocalDate()).getTime() + LocalDate.ONE_DAY * 2),
				new CompanyEvent[] { SmartTest.getCompanyEvent1() }, new WorkHour());
		cc1.getDate().setLocale(LocaleInfo.LOCALE_KOREAN);
		cc2.getDate().setLocale(LocaleInfo.LOCALE_KOREAN);
		cc3.getDate().setLocale(LocaleInfo.LOCALE_KOREAN);
		return new CompanyCalendar[] { cc1, cc2, cc3 };

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getCompanyCalendars(net.smartworks
	 * .util.LocalDate, net.smartworks.util.LocalDate)
	 */
	@Override
	public CompanyCalendar[] getCompanyCalendars(LocalDate fromDate, LocalDate toDate) throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getEventInstances(net.smartworks
	 * .util.LocalDate, int)
	 */
	@Override
	public EventInstance[] getEventInstances(LocalDate fromDate, int days) throws Exception {
		LocalDate time1 = new LocalDate();
		time1.plusToGMTTime(-1 * LocalDate.ONE_HOUR);
		LocalDate time2 = new LocalDate();
		time2.plusToGMTTime(LocalDate.ONE_HOUR);
		LocalDate time3 = new LocalDate();
		time3.plusToGMTTime(3 * LocalDate.ONE_HOUR);
		LocalDate time4 = new LocalDate();
		time4.plusToGMTTime(5 * LocalDate.ONE_HOUR);
		LocalDate time5 = new LocalDate();
		time5.plusToGMTTime(LocalDate.ONE_DAY);
		LocalDate time6 = new LocalDate();
		time6.plusToGMTTime(LocalDate.ONE_DAY + LocalDate.ONE_HOUR);
		LocalDate time7 = new LocalDate();
		time7.plusToGMTTime(2 * LocalDate.ONE_DAY + LocalDate.ONE_HOUR * 3);
		LocalDate time8 = new LocalDate();
		time8.plusToGMTTime(2 * LocalDate.ONE_DAY + LocalDate.ONE_HOUR * 7);
		LocalDate time9 = new LocalDate();
		time9.plusToGMTTime(1 * LocalDate.ONE_YEAR + LocalDate.ONE_HOUR * 10);
		LocalDate time10 = new LocalDate();
		time10.plusToGMTTime(1 * LocalDate.ONE_YEAR + LocalDate.ONE_HOUR * 14);
		EventInstance event1 = new EventInstance("event1", "정부장님 점심약속", new SocialWork("socialwork1", "Event Work"), SmartTest.getUser1(), new LocalDate());
		event1.setStart(time1);
		event1.setEnd(time2);
		event1.setRelatedUsers(new User[] { SmartTest.getUser2() });

		EventInstance event2 = new EventInstance("event2", "스마트웍스닷넷 디자인회의", new SocialWork("socialwork1", "Event Work"), SmartTest.getUser2(), new LocalDate());
		event2.setStart(time3);
		event2.setEnd(time4);
		event2.setRelatedUsers(new User[] { SmartTest.getUser1(), SmartTest.getUser2(), SmartTest.getUser3() });
		event2.setWorkSpace(SmartTest.getGroup1());

		EventInstance event3 = new EventInstance("event3", "주간업무 보고회의", new SocialWork("socialwork1", "Event Work"), SmartTest.getUser3(), new LocalDate());
		event3.setStart(time5);
		event3.setEnd(time6);
		event3.setWorkSpace(SmartTest.getDepartment1());

		EventInstance event4 = new EventInstance("event4", "KT 스마트웍킹팀 저녁", new SocialWork("socialwork1", "Event Work"), SmartTest.getUser1(), new LocalDate());
		event4.setStart(time7);
		event4.setEnd(time8);

		EventInstance event5 = new EventInstance("event5", "진산회 골프모임", new SocialWork("socialwork1", "Event Work"), SmartUtil.getCurrentUser(), new LocalDate());
		event5.setStart(time9);
		event5.setEnd(time10);
		return new EventInstance[] { event1, event2, event3, event4, event5 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getEventInstances(net.smartworks
	 * .util.LocalDate, net.smartworks.util.LocalDate)
	 */
	@Override
	public EventInstance[] getEventInstances(LocalDate fromDate, LocalDate toDate) throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getBoardInstances(net.smartworks
	 * .util.LocalDate, int)
	 */
	@Override
	public BoardInstance[] getBoardInstances(LocalDate fromDate, int days) throws Exception {
		LocalDate time1 = new LocalDate();
		time1.plusToGMTTime(-(1 * LocalDate.ONE_HOUR));
		LocalDate time2 = new LocalDate();
		time2.plusToGMTTime(-(LocalDate.ONE_HOUR));
		LocalDate time3 = new LocalDate();
		time3.plusToGMTTime(-(3 * LocalDate.ONE_HOUR));
		LocalDate time4 = new LocalDate();
		time4.plusToGMTTime(-(5 * LocalDate.ONE_HOUR));
		LocalDate time5 = new LocalDate();
		time5.plusToGMTTime(-(LocalDate.ONE_DAY));
		LocalDate time6 = new LocalDate();
		time6.plusToGMTTime(-(LocalDate.ONE_DAY + LocalDate.ONE_HOUR));
		LocalDate time7 = new LocalDate();
		time7.plusToGMTTime(-(2 * LocalDate.ONE_DAY + LocalDate.ONE_HOUR * 3));
		LocalDate time8 = new LocalDate();
		time8.plusToGMTTime(-(2 * LocalDate.ONE_DAY + LocalDate.ONE_HOUR * 7));
		LocalDate time9 = new LocalDate();
		time9.plusToGMTTime(-(10 * LocalDate.ONE_DAY + LocalDate.ONE_HOUR * 10));
		LocalDate time10 = new LocalDate();
		time10.plusToGMTTime(-(10 * LocalDate.ONE_DAY + LocalDate.ONE_HOUR * 14));

		BoardInstance board1 = new BoardInstance("board1", "워크샵 일정계획 공지 합니다.", new SocialWork("socialwork1", "Board Work"), SmartUtil.getCurrentUser(), time1);
		board1.setWorkSpace(SmartTest.getGroup2());
		BoardInstance board2 = new BoardInstance("board2", "하반기 해외 B2B마케팅 성공사례 세미나", new SocialWork("socialwork1", "Board Work"), SmartTest.getUser1(), time2);
		BoardInstance board3 = new BoardInstance("board3", "올레캠퍼스 자바개발자 교육과정 소개합니다.", new SocialWork("socialwork1", "Board Work"), SmartTest.getUser2(), time3);
		board3.setWorkSpace(SmartTest.getDepartment2());
		BoardInstance board4 = new BoardInstance("board4", "가을 조직개편 조직도 입니다.", new SocialWork("socialwork1", "Board Work"), SmartTest.getUser3(), time4);
		BoardInstance board5 = new BoardInstance("board5", "가을 정기 임직원 승진 발표", new SocialWork("socialwork1", "Board Work"), SmartTest.getUser3(), time5);
		BoardInstance board6 = new BoardInstance("board6", "2011년도 경영계획 공지합니다.", new SocialWork("socialwork1", "Board Work"), SmartUtil.getCurrentUser(), time6);
		BoardInstance board7 = new BoardInstance("board7", "여름휴가 일정 공지합니다.", new SocialWork("socialwork1", "Board Work"), SmartUtil.getCurrentUser(), time7);
		BoardInstance board8 = new BoardInstance("board8", "제품개발 프로젝트 전체 일정 계획공지합니다.", new SocialWork("socialwork1", "Board Work"), SmartTest.getUser2(), time8);
		board8.setWorkSpace(SmartTest.getDepartment1());
		BoardInstance board9 = new BoardInstance("board9", "사무실 이전 계획 입니다.", new SocialWork("socialwork1", "Board Work"), SmartTest.getUser1(), time9);
		BoardInstance board10 = new BoardInstance("board10", "스마트웍스닷넷 장기 로드맵 입니다.", new SocialWork("socialwork1", "Board Work"), SmartUtil.getCurrentUser(),
				time10);
		return new BoardInstance[] { board1, board2, board3, board4, board5, board6, board7, board8, board9, board10 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getBoardInstances(net.smartworks
	 * .util.LocalDate, net.smartworks.util.LocalDate)
	 */
	@Override
	public BoardInstance[] getBoardInstances(LocalDate fromDate, LocalDate toDate) throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getCompanyEventBox(net.smartworks
	 * .util.LocalDate)
	 */
	@Override
	public CompanyCalendar getCompanyEventBox(LocalDate date) throws Exception {
		CompanyCalendar eventBox = new CompanyCalendar();
		eventBox.setDate(date);
		CompanyEvent event1 = new CompanyEvent();
		eventBox.setCompanyEvents(new CompanyEvent[] { event1 });
		return eventBox;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getMyFavoriteWorks(java.lang.
	 * String)
	 */
	@Override
	public SmartWork[] getMyFavoriteWorks(String userId) throws Exception {

		return new SmartWork[] { SmartTest.getSmartWork1(), SmartTest.getSmartWork2(), SmartTest.getSmartWork3() };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getMyWorkCategories(java.lang
	 * .String)
	 */
	@Override
	public WorkCategory[] getMyWorkCategories(String userId) throws Exception {

		return new WorkCategory[] { SmartTest.getWorkCategory1(), SmartTest.getWorkCategory2() };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getMyAllWorksByCategoryId(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public SmartWork[] getMyAllWorksByCategoryId(String userId, String categoryId) throws Exception {

		SmartWork[] smartWorks = new SmartWork[] { SmartTest.getSmartWork1(), SmartTest.getSmartWork2(), SmartTest.getSmartWork3(), SmartTest.getSmartWork4(),
				SmartTest.getSmartWork5(), SmartTest.getSmartWork6() };

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getMyAllWorksByGroupId(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public SmartWork[] getMyAllWorksByGroupId(String userId, String groupId) throws Exception {

		return new SmartWork[] { SmartTest.getSmartWork7(), SmartTest.getSmartWork8(), SmartTest.getSmartWork9() };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getMyRecentInstances(java.lang
	 * .String)
	 */
	@Override
	public WorkInstance[] getMyRecentInstances(String userId) throws Exception {

		return new WorkInstance[] { SmartTest.getWorkInstance1(), SmartTest.getWorkInstance2(), SmartTest.getWorkInstance3(), SmartTest.getWorkInstance4(),
				SmartTest.getWorkInstance5() };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.smartworks.service.impl.ISmartWorks#getAvailableChatter()
	 */
	@Override
	public User[] getAvailableChatter() throws Exception {
		User[] chatters = new User[] { SmartTest.getUser2(), SmartTest.getUser1(), SmartUtil.getCurrentUser(), SmartTest.getUser2(), SmartTest.getUser1(),
				SmartUtil.getCurrentUser(), SmartTest.getUser2(), SmartTest.getUser1(), SmartUtil.getCurrentUser(), SmartTest.getUser2(), SmartTest.getUser1(),
				SmartUtil.getCurrentUser() };
		return chatters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#searchAvailableChatterList(java
	 * .lang.String)
	 */
	@Override
	public User[] searchAvailableChatterList(String key) throws Exception {
		User[] chatters = new User[] { SmartTest.getUser2(), SmartTest.getUser1(), SmartUtil.getCurrentUser(), SmartTest.getUser2(), SmartTest.getUser1(),
				SmartUtil.getCurrentUser(), SmartTest.getUser2(), SmartTest.getUser1(), SmartUtil.getCurrentUser(), SmartTest.getUser2(), SmartTest.getUser1(),
				SmartUtil.getCurrentUser() };
		return chatters;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getCompanyEventsByDate(net.smartworks
	 * .util.LocalDate, int)
	 */
	@Override
	public EventInstance[] getCompanyEventsByDate(LocalDate date, int maxEvents) throws Exception {
		EventInstance[] events = new EventInstance[] {

		};
		return events;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getMyEventsByDate(java.lang.String
	 * , net.smartworks.util.LocalDate, int)
	 */
	@Override
	public EventInstance[] getMyEventsByDate(String userId, LocalDate date, int maxEvents) throws Exception {
		EventInstance[] events = new EventInstance[] {

		};
		return events;
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

}
