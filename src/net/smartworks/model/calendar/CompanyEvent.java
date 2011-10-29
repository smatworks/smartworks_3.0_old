package net.smartworks.model.calendar;

import java.util.Date;

import net.smartworks.model.BaseObject;
import net.smartworks.model.community.User;
import net.smartworks.util.LocalDate;

public class CompanyEvent extends BaseObject {

	private Boolean 		isHoliday;
	private User[]			relatedUsers;
	private LocalDate		plannedStart;
	private LocalDate		plannedEnd;

	public Boolean getIsHoliday() {
		return isHoliday;
	}
	public void setIsHoliday(Boolean isHoliday) {
		this.isHoliday = isHoliday;
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

	public CompanyEvent(){
		super();
	}

	public CompanyEvent(String id, String name){
		super(id, name);
	}
}