package net.smartworks.model.instance;

import java.util.Date;

import net.smartworks.model.BaseObject;
import net.smartworks.model.community.User;
import net.smartworks.model.community.WorkSpace;
import net.smartworks.model.work.Work;
import net.smartworks.util.LocalDate;

public class EventInstance extends WorkInstance {

	public static int EVENT_TYPE_COMPANY_EVENT		= 1;
	public static int EVENT_TYPE_BIRTHDAY			= 2;
	public static int EVENT_TYPE_NORMAL_EVENT		= 3;
	
	public static int EVENT_STATUS_NORMAL			= 0;
	public static int EVENT_STATUS_ALARM_START		= 1;
	public static int EVENT_STATUS_ALARM_CONFIRMED	= 2;
	
	public static int EVENT_ALARM_NONE				= 0;
	public static int EVENT_ALARM_ON_TIME			= 1;
	public static int EVENT_ALARM_ON_ALARM_TIME		= 2;
	public static int EVENT_ALARM_ON_BOTH_TIME		= 3;
		
	private int				eventType;
	private String			content;
	private User[]			relatedUsers;
	private LocalDate		plannedStart;
	private LocalDate		plannedEnd;
	private int				alarmOption; 	// 시작시간에 한번만, 미리알림시간에 한번만, 미리알리시간과 시작시간에 한번씩, 미리알미리알미리알림없음 
	private Date			alarmTime;

	public int getEventType() {
		return eventType;
	}
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User[] getRelatedUsers() {
		return relatedUsers;
	}
	public void setRelatedUsers(User[] relatedUsers) {
		this.relatedUsers = relatedUsers;
	}
	public LocalDate getPlannedStart() {
		return plannedStart;
	}
	public void setPlannedStart(LocalDate plannedStart) {
		this.plannedStart = plannedStart;
	}
	public LocalDate getPlannedEnd() {
		return plannedEnd;
	}
	public void setPlannedEnd(LocalDate plannedEnd) {
		this.plannedEnd = plannedEnd;
	}
	public int getAlarmOption() {
		return alarmOption;
	}
	public void setAlarmOption(int alarmOption) {
		this.alarmOption = alarmOption;
	}
	public Date getAlarmTime() {
		return alarmTime;
	}
	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}
	public EventInstance(){
		super();
		super.setInstanceType(Instance.INSTANCE_TYPE_EVENT);
	}

	public EventInstance(String id, String subject, Work work, User owner, LocalDate lastModifiedDate){
			super(id, subject, work, owner, lastModifiedDate);
			super.setInstanceType(Instance.INSTANCE_TYPE_EVENT);
	}
}