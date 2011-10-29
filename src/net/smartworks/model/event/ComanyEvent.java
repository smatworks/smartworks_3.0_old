package net.smartworks.model.event;

import java.util.Date;

import net.smartworks.model.BaseObject;
import net.smartworks.model.community.User;
import net.smartworks.util.LocalDate;

public class ComanyEvent extends BaseObject {

	private Boolean 		isHoliday;
	private String			content;
	private User[]			relatedUsers;
	private LocalDate		plannedStart;
	private LocalDate		plannedEnd;

	public Boolean getIsHoliday() {
		return isHoliday;
	}
	public void setIsHoliday(Boolean isHoliday) {
		this.isHoliday = isHoliday;
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

	public ComanyEvent(){
		super();
	}

	public ComanyEvent(String id, String name){
		super(id, name);
	}
}