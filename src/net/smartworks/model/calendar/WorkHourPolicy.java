package net.smartworks.model.calendar;

import java.util.Date;

import net.smartworks.util.LocalDate;

public class WorkHourPolicy {

	public static WorkHour[] DEFAULT_WORKHOURS = new WorkHour[]{
							new WorkHour(9*LocalDate.ONE_HOUR, 18*LocalDate.ONE_HOUR, 9*LocalDate.ONE_HOUR),
							new WorkHour(9*LocalDate.ONE_HOUR, 18*LocalDate.ONE_HOUR, 9*LocalDate.ONE_HOUR),
							new WorkHour(9*LocalDate.ONE_HOUR, 18*LocalDate.ONE_HOUR, 9*LocalDate.ONE_HOUR),
							new WorkHour(9*LocalDate.ONE_HOUR, 18*LocalDate.ONE_HOUR, 9*LocalDate.ONE_HOUR),
							new WorkHour(9*LocalDate.ONE_HOUR, 18*LocalDate.ONE_HOUR, 9*LocalDate.ONE_HOUR),
							new WorkHour(0, 0, 0)};		

	private int startDayOfWeek = 0;
	private int workingDays = 5;
	private LocalDate validFrom = new LocalDate((new Date(0)).getTime()); 
	private LocalDate validTo;
	private WorkHour[] workHours = DEFAULT_WORKHOURS;
	
	public int getStartDayOfWeek() {
		return startDayOfWeek;
	}
	public void setStartDayOfWeek(int startDayOfWeek) {
		this.startDayOfWeek = startDayOfWeek;
	}
	public int getWorkingDays() {
		return workingDays;
	}
	public void setWorkingDays(int workingDays) {
		this.workingDays = workingDays;
	}
	public LocalDate getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}
	public LocalDate getValidTo() {
		return validTo;
	}
	public void setValidTo(LocalDate validTo) {
		this.validTo = validTo;
	}
	public WorkHour[] getWorkHours() {
		return workHours;
	}
	public void setWorkHours(WorkHour[] workHours) {
		if(workHours == null || workHours.length < startDayOfWeek)
			this.workHours = DEFAULT_WORKHOURS;
		this.workHours = workHours;
	}
	
	public WorkHourPolicy(){
		super();
	}
}
