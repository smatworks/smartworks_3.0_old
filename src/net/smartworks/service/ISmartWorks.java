package net.smartworks.service;

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
import net.smartworks.service.impl.SmartWorks;
import net.smartworks.util.LocalDate;

public interface ISmartWorks {

	public static String CONTEXT_HOME = "sf.hm";
	public static String CONTEXT_SMARTCASTER = "sf.sc";
	public static String CONTEXT_DASHBOARD = "sf.db";
	public static String CONTEXT_MYPROFILE = "sf.pf";

	public static String CONTEXT_PREFIX_SELF = "sf.";

	public static int SPACE_TYPE_WORK_LIST = 1;
	public static int SPACE_TYPE_WORK_INSTANCE = 2;
	public static int SPACE_TYPE_TASK_INSTANCE = 3;

	public static int CONTEXT_PREFIX_LENGTH = 6;
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

	public abstract String[] getBroadcastingMessages() throws Exception;

	public abstract CompanyCalendar[] getCompanyCalendars(LocalDate fromDate, int days) throws Exception;

	public abstract WorkSpace getWorkSpaceById(String workSpaceId) throws Exception;

	public abstract CompanyCalendar[] getCompanyCalendars(LocalDate fromDate, LocalDate toDate) throws Exception;

	public abstract EventInstance[] getEventInstances(LocalDate fromDate, int days) throws Exception;

	public abstract EventInstance[] getEventInstances(LocalDate fromDate, LocalDate toDate) throws Exception;

	public abstract BoardInstance[] getBoardInstances(LocalDate fromDate, int days) throws Exception;

	public abstract BoardInstance[] getBoardInstances(LocalDate fromDate, LocalDate toDate) throws Exception;

	public abstract CompanyCalendar getCompanyEventBox(LocalDate date) throws Exception;

	public abstract SmartWork[] getMyFavoriteWorks(String userId) throws Exception;

	public abstract WorkCategory[] getMyWorkCategories(String userId) throws Exception;

	public abstract SmartWork[] getMyAllWorksByCategoryId(String userId, String categoryId) throws Exception;

	public abstract SmartWork[] getMyAllWorksByGroupId(String userId, String groupId) throws Exception;

	public abstract WorkInstance[] getMyRecentInstances(String userId) throws Exception;

	public abstract Department[] getMyDepartments(String userId) throws Exception;

	public abstract Department getDepartmentById(String departId) throws Exception;

	public abstract Group[] getMyGroups(String userId) throws Exception;

	public abstract Group getGroupById(String groupId) throws Exception;

	public abstract User getUserById(String userId) throws Exception;

	public abstract SmartWork[] searchWorkList(String user, String key) throws Exception;

	public abstract WorkSpace[] searchCommunityList(String user, String key) throws Exception;

	public abstract User[] searchCommunityMemberList(String user, String key) throws Exception;

	public abstract User[] getAvailableChatter() throws Exception;

	public abstract User[] searchAvailableChatterList(String key) throws Exception;

	public abstract EventInstance[] getCompanyEventsByDate(LocalDate date, int maxEvents) throws Exception;

	public abstract EventInstance[] getMyEventsByDate(String userId, LocalDate date, int maxEvents) throws Exception;

	public abstract Notice[] getNoticesForMe(String userId) throws Exception;

	public abstract NoticeBox getNoticeBoxForMe10(int noticeType, LocalDate lastNotice) throws Exception;
}