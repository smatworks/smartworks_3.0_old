package net.smartworks.model.calendar;

import net.smartworks.util.LocalDate;

public class CompanyCalendar {
	
	private LocalDate date;
	private CompanyEvent[] companyEvents;
	private WorkHour workHour;
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public CompanyEvent[] getCompanyEvents() {
		return companyEvents;
	}
	public void setCompanyEvents(CompanyEvent[] companyEvents) {
		this.companyEvents = companyEvents;
	}
	public WorkHour getWorkHour() {
		return workHour;
	}
	public void setWorkHour(WorkHour workHour) {
		this.workHour = workHour;
	}
	
	public CompanyCalendar(){
		super();
	}
	
	public CompanyCalendar(LocalDate date, CompanyEvent[] companyEvents, WorkHour workHour){
		super();
		this.date = date;
		this.companyEvents = companyEvents;
		this.workHour = workHour;
	}

	public boolean isHoliday(){
		for(CompanyEvent ce : companyEvents){
			if(ce.getIsHoliday()) return true;
		}
		return false;
	}
}
