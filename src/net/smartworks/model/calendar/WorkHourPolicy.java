package net.smartworks.model.calendar;

import java.util.Date;

import net.smartworks.util.LocalDate;

public class WorkHourPolicy {

	public static WorkHour[] DEFAULT_WORKHOURS = new WorkHour[]{
							new WorkHour(9*60, 18*60, 9*60),
							new WorkHour(9*60, 18*60, 9*60),
							new WorkHour(9*60, 18*60, 9*60),
							new WorkHour(9*60, 18*60, 9*60),
							new WorkHour(9*60, 18*60, 9*60),
							new WorkHour(0, 0, 0)};		

	private int startDayOfWeek = CalendarInfo.DAY_SUNDAY;
	private int workingDays = 5;
	private LocalDate validFrom = new LocalDate((new Date(0)).getTime()); 
	private LocalDate validTo;
	private WorkHour[] workHours = DEFAULT_WORKHOURS;

}
