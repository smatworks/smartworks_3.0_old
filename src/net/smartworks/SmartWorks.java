package net.smartworks;

import java.util.StringTokenizer;

import net.smartworks.model.community.Department;
import net.smartworks.model.community.Group;
import net.smartworks.model.community.User;
import net.smartworks.model.community.WorkSpace;
import net.smartworks.model.calendar.CompanyEvent;
import net.smartworks.model.calendar.CompanyEventBox;
import net.smartworks.model.instance.AsyncMessageInstance;
import net.smartworks.model.instance.CommentsInstance;
import net.smartworks.model.instance.EventInstance;
import net.smartworks.model.instance.MailInstance;
import net.smartworks.model.instance.TaskInstance;
import net.smartworks.model.instance.WorkInstance;
import net.smartworks.model.notice.Notice;
import net.smartworks.model.notice.NoticeBox;
import net.smartworks.model.notice.NoticeMessage;
import net.smartworks.model.work.SmartWork;
import net.smartworks.model.work.Work;
import net.smartworks.model.work.WorkCategory;
import net.smartworks.server.service.ICommunityService;
import net.smartworks.util.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmartWorks {

	ICommunityService communityService;

	@Autowired
	public void setCommunityService(ICommunityService communityService) {
		this.communityService = communityService;
	}

	public static String CONTEXT_HOME = "sf.hm";
	public static String CONTEXT_SMARTCASTER = "sf.sc";
	public static String CONTEXT_DASHBOARD = "sf.db";
	public static String CONTEXT_MYPROFILE = "sf.pf";

	public static String CONTEXT_PREFIX_SELF = "sf.";

	public static int SPACE_TYPE_WORK_LIST = 1;
	public static int SPACE_TYPE_WORK_INSTANCE = 2;
	public static int SPACE_TYPE_TASK_INSTANCE = 3;
	
	public static String CONTEXT_PREFIX_USER_SPACE = "us.sp.";
	public static String CONTEXT_PREFIX_GROUP_SPACE = "gp.sp.";
	public static String CONTEXT_PREFIX_DEPARTMENT_SPACE = "dp.sp.";

	public static String CONTEXT_PREFIX_IWORK_LIST = "iw.li.";
	public static String CONTEXT_PREFIX_PWORK_LIST = "pw.li.";
	public static String CONTEXT_PREFIX_SWORK_LIST = "sw.li.";
	public static String CONTEXT_PREFIX_FILE_LIST = "fl.li.";
	public static String CONTEXT_PREFIX_IMAGE_LIST = "im.li.";
	public static String CONTEXT_PREFIX_EVENT_LIST = "ev.li.";
	public static String CONTEXT_PREFIX_MEMO_LIST = "mm.li.";
	public static String CONTEXT_PREFIX_BOARD_LIST = "bd.li.";
	public static String CONTEXT_PREFIX_MAIL_LIST = "ml.li.";
	public static String CONTEXT_PREFIX_SAVED_LIST = "sv.li.";

	public static String CONTEXT_PREFIX_IWORK_SPACE = "iw.sp.";
	public static String CONTEXT_PREFIX_PWORK_SPACE = "pw.sp.";
	public static String CONTEXT_PREFIX_SWORK_SPACE = "sw.sp.";
	public static String CONTEXT_PREFIX_FILE_SPACE = "fl.sp.";
	public static String CONTEXT_PREFIX_IMAGE_SPACE = "im.sp.";
	public static String CONTEXT_PREFIX_EVENT_SPACE = "ev.sp.";
	public static String CONTEXT_PREFIX_MEMO_SPACE = "mm.sp.";
	public static String CONTEXT_PREFIX_BOARD_SPACE = "bd.sp.";
	public static String CONTEXT_PREFIX_MAIL_SPACE = "ml.sp.";

	public static String CONTEXT_PREFIX_IWORK_TASK = "iw.ts.";
	public static String CONTEXT_PREFIX_PWORK_TASK = "pw.ts.";
	public static String CONTEXT_PREFIX_SWORK_TASK = "sw.ts.";

	public static boolean isSameContextPrefix(String contextPrefix,
			String contextId) throws Exception {
		if (contextPrefix == null || contextId == null
				|| contextPrefix.length() >= contextId.length())
			return false;
		if (contextId.subSequence(0, contextPrefix.length()).equals(
				contextPrefix))
			return true;
		return false;
	}

	public static boolean isWorkContextType(String contextId) throws Exception {
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

	public static boolean isWorkSpaceContextType(String contextId)
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

	public static boolean isTaskSpaceContextType(String contextId)
			throws Exception {
		if (contextId == null || contextId.length() < 6)
			return false;
		if (contextId.substring(0, 6).equals("iw.ts.")
				|| contextId.substring(0, 6).equals("pw.ts.")
				|| contextId.substring(0, 6).equals("sw.ts."))
			return true;
		return false;
	}

	public static String getSpaceIdFromContentContext(String contentContext)
			throws Exception {
		if (contentContext == null
				|| SmartWorks.isWorkSpaceContextType(contentContext))
			return null;

		StringTokenizer st = new StringTokenizer(contentContext, ".");
		if (st.countTokens() < 3)
			return null;

		String spaceId = null;
		for (int i = 0; i < 3; i++)
			spaceId = st.nextElement().toString();
		return spaceId;
	}
	
	public static WorkSpace getWorkSpaceById(String workSpaceId)
			throws Exception {
		WorkSpace workSpace = null;

		Department[] departments = SmartWorks.getMyDepartments(SmartWorks
				.getCurrentUser().getId());
		for (Department department : departments) {
			if (department.getId().equals(workSpaceId))
				return department;
		}
		Group[] groups = SmartWorks.getMyGroups(SmartWorks.getCurrentUser()
				.getId());
		for (Group group : groups) {
			if (group.getId().equals(workSpaceId))
				return group;
		}

		if (SmartWorks.getUser1().getId().equals(workSpaceId))
			return SmartWorks.getUser1();
		if (SmartWorks.getUser2().getId().equals(workSpaceId))
			return SmartWorks.getUser2();

		return workSpace;
	}

	public static String getContextPrefixByWorkType(int smartWorkType,
			int spaceType) throws Exception {

		if (spaceType == SmartWorks.SPACE_TYPE_WORK_LIST) {
			if (smartWorkType == SmartWork.WORK_TYPE_INFORMATION)
				return SmartWorks.CONTEXT_PREFIX_IWORK_LIST;
			if (smartWorkType == SmartWork.WORK_TYPE_PROCESS)
				return SmartWorks.CONTEXT_PREFIX_PWORK_LIST;
			if (smartWorkType == SmartWork.WORK_TYPE_SCHEDULE)
				return SmartWorks.CONTEXT_PREFIX_SWORK_LIST;
		} else if(spaceType == SmartWorks.SPACE_TYPE_WORK_INSTANCE){
			if (smartWorkType == SmartWork.WORK_TYPE_INFORMATION)
				return SmartWorks.CONTEXT_PREFIX_IWORK_SPACE;
			if (smartWorkType == SmartWork.WORK_TYPE_PROCESS)
				return SmartWorks.CONTEXT_PREFIX_PWORK_SPACE;
			if (smartWorkType == SmartWork.WORK_TYPE_SCHEDULE)
				return SmartWorks.CONTEXT_PREFIX_SWORK_SPACE;
		} else if(spaceType == SmartWorks.SPACE_TYPE_TASK_INSTANCE){
			if (smartWorkType == SmartWork.WORK_TYPE_INFORMATION)
				return SmartWorks.CONTEXT_PREFIX_IWORK_TASK;
			if (smartWorkType == SmartWork.WORK_TYPE_PROCESS)
				return SmartWorks.CONTEXT_PREFIX_PWORK_TASK;
			if (smartWorkType == SmartWork.WORK_TYPE_SCHEDULE)
				return SmartWorks.CONTEXT_PREFIX_SWORK_TASK;
		}
		return null;
	}

	public static String getTargetContentByWorkType(int smartWorkType,
			int spaceType) throws Exception {

		if (spaceType == SmartWorks.SPACE_TYPE_WORK_LIST) {
			if (smartWorkType == SmartWork.WORK_TYPE_INFORMATION)
				return "iwork_list.sw";
			if (smartWorkType == SmartWork.WORK_TYPE_PROCESS)
				return "pwork_list.sw";
			if (smartWorkType == SmartWork.WORK_TYPE_SCHEDULE)
				return "swork_list.sw";
		} else if(spaceType == SmartWorks.SPACE_TYPE_WORK_INSTANCE){
			if (smartWorkType == SmartWork.WORK_TYPE_INFORMATION)
				return "iwork_space.sw";
			if (smartWorkType == SmartWork.WORK_TYPE_PROCESS)
				return "pwork_space.sw";
			if (smartWorkType == SmartWork.WORK_TYPE_SCHEDULE)
				return "swork_space.sw";
		} else if(spaceType == SmartWorks.SPACE_TYPE_TASK_INSTANCE){
			if (smartWorkType == SmartWork.WORK_TYPE_INFORMATION)
				return "iwork_task.sw";
			if (smartWorkType == SmartWork.WORK_TYPE_PROCESS)
				return "pwork_task.sw";
			if (smartWorkType == SmartWork.WORK_TYPE_SCHEDULE)
				return "swork_task.sw";

		}
		return null;
	}

	public static String[] getBroadcastingMessages()
			throws Exception {

		return new String[] {"오늘 시스템 작업예정으로 오후 5시부터 한시간 동안 시스템을 사용할 수 없으니, 업무진행에 착오없으시길 바랍니다. -- 기술연구소 ---",
						 "금일 전체회식에 전원참석하여 좋은 친목의 시간이 되기를 바랍니다. --- 경영 기획팀 ----"};
	}
	
	private static CompanyEvent getCompanyEvent1(){
		CompanyEvent event = new CompanyEvent("companyevent1", "창립기념일");
		event.setIsHoliday(false);
		event.setPlannedStart(new LocalDate());
		event.setPlannedEnd(new LocalDate());
		return event;
	}
	
	private static CompanyEvent getCompanyEvent2(){
		CompanyEvent event = new CompanyEvent("companyevent2", "크리스마스");
		event.setIsHoliday(true);
		event.setPlannedStart(new LocalDate());
		LocalDate date1 = new LocalDate();
		date1.setGMTDate(date1.getGMTDate() + LocalDate.ONE_DAY*2);
		event.setPlannedEnd(date1);
		return event;
	}
	
	public static CompanyEventBox getCompanyEventBox(LocalDate date) throws Exception{
		CompanyEventBox eventBox = new CompanyEventBox();
		eventBox.setDate(date);
		CompanyEvent event1 = new CompanyEvent();
		eventBox.setCompanyEvents(new CompanyEvent[] {event1});
		return eventBox;
		
	}

	public static SmartWork[] getMyFavoriteWorks(String userId)
			throws Exception {

		return new SmartWork[] { getSmartWork1(), getSmartWork2(),
				getSmartWork3() };
	}

	public static WorkCategory[] getMyWorkCategories(String userId)
			throws Exception {

		return new WorkCategory[] { getWorkCategory1(), getWorkCategory2() };
	}

	public static SmartWork[] getMyAllWorksByCategoryId(String userId,
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

	public static SmartWork[] getMyAllWorksByGroupId(String userId,
			String groupId) throws Exception {

		return new SmartWork[] { getSmartWork7(), getSmartWork8(),
				getSmartWork9() };
	}

	public static WorkInstance[] getMyRecentInstances(String userId)
			throws Exception {

		return new WorkInstance[] { getWorkInstance1(), getWorkInstance2(),
				getWorkInstance3(), getWorkInstance4(), getWorkInstance5() };
	}

	public static Department[] getMyDepartments(String userId) throws Exception {
		return new Department[] { getDepartment1(), getDepartment2(),
				getDepartment3(), getDepartment4() };

	}

	public static Department getDepartmentById(String departId)
			throws Exception {
		Department[] departments = getMyDepartments(getCurrentUser().getId());
		for (int i = 0; i < departments.length; i++) {
			if (departments[i].getId().equals(departId))
				return departments[i];
		}
		return null;

	}

	public static Group[] getMyGroups(String userId) throws Exception {
		return new Group[] { getGroup1(), getGroup2(), getGroup3() };
	}

	public static Group getGroupById(String groupId) throws Exception {
		Group[] groups = getMyGroups(getCurrentUser().getId());
		for (int i = 0; i < groups.length; i++) {
			if (groups[i].getId().equals(groupId))
				return groups[i];
		}
		return null;

	}

	public static User getCurrentUser() throws Exception {
		User user = new User();
		user.setId("jisook@maninsoft.co.kr");
		user.setName("김지숙");
		user.setPosition("CEO");
		user.setDepartment("경영기획실 디자인팀");
		user.setLocale("ko_KR"); // ko_KR, en_US
		user.setTimeZone("SEOUL");
		user.setCompany("(주)맨인소프트");
		user.setPicturePath("images/");
		user.setOrgPictureName(user.getId() + ".jpg");
		user.setMinPictureName(user.getId() + "_min.gif");
		user.setMidPictureName(user.getId() + "_mid.gif");

		return user;
	}

	public static User getUserById(String userId) throws Exception {
		if (getCurrentUser().getId().equals(userId))
			return getCurrentUser();
		else if (getUser1().getId().equals(userId))
			return getUser1();
		else if (getUser2().getId().equals(userId))
			return getUser2();
		return getCurrentUser();
	}

	public static SmartWork[] searchWorkList(String user, String key)
			throws Exception {
		return SmartWorks.getMyFavoriteWorks(user);
	}

	public static WorkSpace[] searchCommunityList(String user, String key)
			throws Exception {
		WorkSpace[] comms = new WorkSpace[] { SmartWorks.getMyGroups(user)[0],
				SmartWorks.getUser1(), SmartWorks.getMyDepartments(user)[1],
				SmartWorks.getUser2() };
		return comms;

	}

	public static User[] searchCommunityMemberList(String user, String key)
			throws Exception {
		User[] users = new User[] { SmartWorks.getUser1(),
				SmartWorks.getUser2() };
		return users;

	}

	public static User[] getAvailableChatter() throws Exception {
		User[] chatters = new User[] { SmartWorks.getUser2(),
				SmartWorks.getUser1(), SmartWorks.getCurrentUser(),
				SmartWorks.getUser2(), SmartWorks.getUser1(),
				SmartWorks.getCurrentUser(), SmartWorks.getUser2(),
				SmartWorks.getUser1(), SmartWorks.getCurrentUser(),
				SmartWorks.getUser2(), SmartWorks.getUser1(),
				SmartWorks.getCurrentUser() };
		return chatters;
	}

	public static User[] searchAvailableChatterList(String key)
			throws Exception {
		User[] chatters = new User[] { SmartWorks.getUser2(),
				SmartWorks.getUser1(), SmartWorks.getCurrentUser(),
				SmartWorks.getUser2(), SmartWorks.getUser1(),
				SmartWorks.getCurrentUser(), SmartWorks.getUser2(),
				SmartWorks.getUser1(), SmartWorks.getCurrentUser(),
				SmartWorks.getUser2(), SmartWorks.getUser1(),
				SmartWorks.getCurrentUser() };
		return chatters;

	}

	public static EventInstance[] getCompanyEventsByDate(LocalDate date,
			int maxEvents) throws Exception {
		EventInstance[] events = new EventInstance[] {

		};
		return events;
	}

	public static EventInstance[] getMyEventsByDate(String userId, LocalDate date,
			int maxEvents) throws Exception {
		EventInstance[] events = new EventInstance[] {

		};
		return events;
	}

	public static Notice[] getNoticesForMe(String userId) throws Exception {
		return new Notice[] { new Notice(Notice.NOTICE_TYPE_NOTIFICATION, 1),
				new Notice(Notice.NOTICE_TYPE_MESSAGE, 0),
				new Notice(Notice.NOTICE_TYPE_COMMENTS, 29),
				new Notice(Notice.NOTICE_TYPE_ASSIGNED, 0),
				new Notice(Notice.NOTICE_TYPE_MAILBOX, 420),
				new Notice(Notice.NOTICE_TYPE_SAVEDBOX, 7) };
	}

	public static NoticeBox getNoticeBoxForMe10(int noticeType,
			LocalDate lastNotice) throws Exception {
		if (noticeType == Notice.NOTICE_TYPE_NOTIFICATION) {
			NoticeBox noticeBox = new NoticeBox();
			noticeBox.setNoticeMessages(getNotificationMessages());
			noticeBox.setNoticeType(Notice.NOTICE_TYPE_NOTIFICATION);
			noticeBox.setDateOfLastNotice(new LocalDate());
			noticeBox.setRemainingLength(48);
			return noticeBox;
		}

		if (noticeType == Notice.NOTICE_TYPE_MESSAGE) {
			NoticeBox noticeBox = new NoticeBox();
			noticeBox.setNoticeMessages(getMessageMessages());
			noticeBox.setNoticeType(Notice.NOTICE_TYPE_MESSAGE);
			noticeBox.setDateOfLastNotice(new LocalDate());
			noticeBox.setRemainingLength(48);
			return noticeBox;
		}

		if (noticeType == Notice.NOTICE_TYPE_COMMENTS) {
			NoticeBox noticeBox = new NoticeBox();
			noticeBox.setNoticeMessages(getCommentsMessages());
			noticeBox.setNoticeType(Notice.NOTICE_TYPE_COMMENTS);
			noticeBox.setDateOfLastNotice(new LocalDate());
			noticeBox.setRemainingLength(48);
			return noticeBox;
		}

		if (noticeType == Notice.NOTICE_TYPE_ASSIGNED) {
			NoticeBox noticeBox = new NoticeBox();
			noticeBox.setNoticeMessages(getAssignedMessages());
			noticeBox.setNoticeType(Notice.NOTICE_TYPE_ASSIGNED);
			noticeBox.setDateOfLastNotice(new LocalDate());
			noticeBox.setRemainingLength(48);
			return noticeBox;
		}

		if (noticeType == Notice.NOTICE_TYPE_MAILBOX) {
			NoticeBox noticeBox = new NoticeBox();
			noticeBox.setNoticeMessages(getMailboxMessages());
			noticeBox.setNoticeType(Notice.NOTICE_TYPE_MAILBOX);
			noticeBox.setDateOfLastNotice(new LocalDate());
			noticeBox.setRemainingLength(48);
			return noticeBox;
		}

		if (noticeType == Notice.NOTICE_TYPE_SAVEDBOX) {
			NoticeBox noticeBox = new NoticeBox();
			noticeBox.setNoticeMessages(getSavedboxMessages());
			noticeBox.setNoticeType(Notice.NOTICE_TYPE_SAVEDBOX);
			noticeBox.setDateOfLastNotice(new LocalDate());
			noticeBox.setRemainingLength(48);
			return noticeBox;
		}

		return null;
	}

	/*
	 * public static Date getLocalDate(Date utcDate) throws Exception{ User
	 * currentUser = getCurrentUser(); Calendar cal = Calendar.getInstance();
	 * cal.setTimeZone(currentUser.getTimeZone()); cal.setTime(utcDate); return
	 * cal.getTime(); }
	 */
	// ************************** 테스트용 데이터
	// ******************************************//
	// ************************** 테스트용 데이터
	// ******************************************//

	private static User getUser1() throws Exception {
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

	private static User getUser2() throws Exception {
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

	private static User getUser3() throws Exception {
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

	private static WorkCategory getWorkCategory1() throws Exception {
		return new WorkCategory("cat1", "공통업무");
	}

	private static WorkCategory getWorkCategory2() throws Exception {
		return new WorkCategory("cat2", "영업관리");
	}

	private static SmartWork getSmartWork1() throws Exception {
		return new SmartWork("work1", "근태품의", SmartWork.WORK_TYPE_PROCESS, "",
				getWorkCategory1());
	}

	private static SmartWork getSmartWork2() throws Exception {
		return new SmartWork("work2", "회의록", SmartWork.WORK_TYPE_INFORMATION,
				"", getWorkCategory1());
	}

	private static SmartWork getSmartWork3() throws Exception {
		return new SmartWork("work3", "구매기안", SmartWork.WORK_TYPE_PROCESS, "",
				getWorkCategory1());
	}

	private static SmartWork getSmartWork4() throws Exception {
		return new SmartWork("work4", "제안견적프로세스", SmartWork.WORK_TYPE_PROCESS,
				"", getWorkCategory2());
	}

	private static SmartWork getSmartWork5() throws Exception {
		return new SmartWork("work5", "영업기회", SmartWork.WORK_TYPE_INFORMATION,
				"", getWorkCategory2());
	}

	private static SmartWork getSmartWork6() throws Exception {
		return new SmartWork("work6", "자료실", SmartWork.WORK_TYPE_GROUP, "",
				getWorkCategory2());
	}

	private static SmartWork getSmartWork7() throws Exception {
		return new SmartWork("work11", "구매프로세스", SmartWork.WORK_TYPE_PROCESS,
				"", getWorkCategory2());
	}

	private static SmartWork getSmartWork8() throws Exception {
		return new SmartWork("work21", "구매발주서",
				SmartWork.WORK_TYPE_INFORMATION, "", getWorkCategory2());
	}

	private static SmartWork getSmartWork9() throws Exception {
		return new SmartWork("work31", "자재발주서", SmartWork.WORK_TYPE_PROCESS,
				"", getWorkCategory2());
	}

	private static Group getGroup1() throws Exception {
		return new Group("group1", "SmartWorks.net V3 TFT", new User[] {
				getUser1(), getUser2() }, getUser1());
	}

	private static Group getGroup2() throws Exception {
		return new Group("group2", "한라공조 협력업체 정보화시스템 고도화 프로젝트", new User[] {
				getCurrentUser(), getUser3() }, getUser2());
	}

	private static Group getGroup3() throws Exception {
		return new Group("group3", "금성출판사 그룹웨어 프로젝트",
				new User[] { getUser2() }, getCurrentUser());
	}

	private static WorkInstance getWorkInstance1() throws Exception {
		return new WorkInstance("inst1", "휴가 신청합니다.", getSmartWork1(),
				getUser1(), new LocalDate());
	}

	private static WorkInstance getWorkInstance2() throws Exception {
		return new WorkInstance("inst2", "스마트웍스 3.0 개발계획 회의록 입니다.",
				getSmartWork2(), getUser1(), new LocalDate());
	}

	private static WorkInstance getWorkInstance3() throws Exception {
		return new WorkInstance("inst3", "노트북 구매 기안합니다.", getSmartWork3(),
				getUser1(), new LocalDate());
	}

	private static WorkInstance getWorkInstance4() throws Exception {
		return new WorkInstance("inst4", "금성출판사 스마트웍스 프로젝트", getSmartWork5(),
				getUser1(), new LocalDate());
	}

	private static WorkInstance getWorkInstance5() throws Exception {
		return new WorkInstance("inst5", "삼성 노트북 구매발주서", getSmartWork8(),
				getUser3(), new LocalDate());
	}

	private static Department getDepartment1() throws Exception {
		return new Department("depart1", "(주)맨인소프트", new User[] {
				getCurrentUser(), getUser3() }, getCurrentUser());
	}

	private static Department getDepartment2() throws Exception {
		return new Department("depart2", "대표이사", new User[] { getUser3(),
				getUser2() }, getUser1());
	}

	private static Department getDepartment3() throws Exception {
		return new Department("depart3", "경영기획본부", new User[] { getUser2() },
				getUser2());
	}

	private static Department getDepartment4() throws Exception {
		return new Department("depart4", "기술사업본부", new User[] { getUser1(),
				getCurrentUser() }, getCurrentUser());
	}

	private static EventInstance getEventInstance1() throws Exception {
		EventInstance event = new EventInstance("event1", "한라공조 협력업체 설명회",
				new Work("work1", "개인일정"), SmartWorks.getCurrentUser(),
				new LocalDate());
		event.setPlannedStart(new LocalDate());
		event.setPlannedEnd(new LocalDate());
		return event;

	}

	private static TaskInstance getTaskInstance1() throws Exception {
		TaskInstance taskInstance = new TaskInstance("taskInstance1", "대표이사승인",
				TaskInstance.TASK_TYPE_PROCESSWORK_TASK_ASSIGNED,
				SmartWorks.getUser2(), new LocalDate());
		taskInstance.setWorkInstance(getWorkInstance1());
		taskInstance.setAssignee(SmartWorks.getCurrentUser());
		return taskInstance;
	}

	private static NoticeMessage getNotificationMessage(int noticeType)
			throws Exception {

		NoticeMessage notice1, notice2, notice3, notice4, notice5;
		if (noticeType == NoticeMessage.NOTIFICATION_TYPE_SYSTEM_NOTICE) {
			notice1 = new NoticeMessage("notice1",
					NoticeMessage.NOTIFICATION_TYPE_SYSTEM_NOTICE,
					SmartWorks.getUser1(), new LocalDate());
			notice1.setMessage("금주 주말(토요일, 일요일)에 시스템 정기점검을 실시하는 관계를 시스템을 1시간 가량 사용할 수 없으니 이점 양해 바랍니다.");
			return notice1;
		}

		if (noticeType == NoticeMessage.NOTIFICATION_TYPE_EVENT_ALARM) {
			notice2 = new NoticeMessage("notic2",
					NoticeMessage.NOTIFICATION_TYPE_EVENT_ALARM,
					SmartWorks.getUser2(), new LocalDate());
			notice2.setEvent(getEventInstance1());
			return notice2;
		}
		if (noticeType == NoticeMessage.NOTIFICATION_TYPE_TASK_DELAYED) {
			notice3 = new NoticeMessage("notice3",
					NoticeMessage.NOTIFICATION_TYPE_TASK_DELAYED,
					SmartWorks.getCurrentUser(), new LocalDate());
			notice3.setInstance(getTaskInstance1());
			return notice3;
		}

		if (noticeType == NoticeMessage.NOTIFICATION_TYPE_JOIN_REQUEST) {
			notice4 = new NoticeMessage("notice4",
					NoticeMessage.NOTIFICATION_TYPE_JOIN_REQUEST,
					SmartWorks.getUser1(), new LocalDate());
			notice4.setGroup(getGroup1());
			notice4.setMessage("님이 커뮤너티에 가입을 신청하셨습니다.");
			return notice4;
		}
		if (noticeType == NoticeMessage.NOTIFICATION_TYPE_INSTANCE_CREATED) {
			notice5 = new NoticeMessage("notice5",
					NoticeMessage.NOTIFICATION_TYPE_INSTANCE_CREATED,
					SmartWorks.getUser1(), new LocalDate());
			notice5.setInstance(getWorkInstance1());
			notice5.setMessage("새로운 업무를 등록하였습니다..");
			return notice5;
		}
		return null;

	}

	private static NoticeMessage[] getNotificationMessages() throws Exception {

		NoticeMessage notice1, notice2, notice3, notice4, notice5;
		notice1 = new NoticeMessage("notice1",
				NoticeMessage.NOTIFICATION_TYPE_SYSTEM_NOTICE,
				SmartWorks.getUser1(), new LocalDate());
		notice1.setMessage("금주 주말(토요일, 일요일)에 시스템 정기점검을 실시하는 관계를 시스템을 1시간 가량 사용할 수 없으니 이점 양해 바랍니다.");
		notice2 = new NoticeMessage("notic2",
				NoticeMessage.NOTIFICATION_TYPE_EVENT_ALARM,
				SmartWorks.getUser2(), new LocalDate());
		notice2.setEvent(getEventInstance1());
		notice3 = new NoticeMessage("notice3",
				NoticeMessage.NOTIFICATION_TYPE_TASK_DELAYED,
				SmartWorks.getCurrentUser(), new LocalDate());
		notice3.setInstance(getTaskInstance1());
		notice4 = new NoticeMessage("notice4",
				NoticeMessage.NOTIFICATION_TYPE_JOIN_REQUEST,
				SmartWorks.getUser1(), new LocalDate());
		notice4.setGroup(getGroup1());
		notice4.setMessage("님이 커뮤너티에 가입을 신청하셨습니다.");
		notice5 = new NoticeMessage("notice5",
				NoticeMessage.NOTIFICATION_TYPE_INSTANCE_CREATED,
				SmartWorks.getUser1(), new LocalDate());
		notice5.setInstance(getWorkInstance1());
		notice5.setMessage("새로운 업무를 등록하였습니다..");
		return new NoticeMessage[] { notice1, notice2, notice3, notice4,
				notice5 };

	}

	private static NoticeMessage[] getMessageMessages() throws Exception {

		NoticeMessage notice1, notice2, notice3, notice4, notice5;
		AsyncMessageInstance messageInstance1 = new AsyncMessageInstance(
				"message1", getUser1(), new LocalDate(), "안녕하세요?  잘지내시죠??? ㅎㅎㅎ");
		notice1 = new NoticeMessage("notice21", 0, SmartWorks.getUser1(),
				new LocalDate());
		notice1.setInstance(messageInstance1);

		AsyncMessageInstance messageInstance2 = new AsyncMessageInstance(
				"message2", getUser2(), new LocalDate(),
				"일간 한번 찾아뵙겠습니다. 그동안 몇번 연락드렸었는데, 연락이 안되던데요???");
		notice2 = new NoticeMessage("notice22", 0, SmartWorks.getUser1(),
				new LocalDate());
		notice2.setInstance(messageInstance2);

		AsyncMessageInstance messageInstance3 = new AsyncMessageInstance(
				"message3", getUser3(), new LocalDate(), "누구시더라????ㅠ");
		notice3 = new NoticeMessage("notice23", 0, SmartWorks.getUser1(),
				new LocalDate());
		notice3.setInstance(messageInstance3);

		return new NoticeMessage[] { notice1, notice2, notice3 };

	}

	private static NoticeMessage[] getCommentsMessages() throws Exception {

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

	private static NoticeMessage[] getAssignedMessages() throws Exception {

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

	private static NoticeMessage[] getMailboxMessages() throws Exception {

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

	private static NoticeMessage[] getSavedboxMessages() throws Exception {

		NoticeMessage[] mailboxNotices = getMailboxMessages();
		
		return mailboxNotices;
	}
}
