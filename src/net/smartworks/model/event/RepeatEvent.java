package net.smartworks.model.event;
import java.util.Date;

import net.smartworks.util.LocalDate;

public class RepeatEvent{

	public static int	REPEAT_INTERVAL_EVERY_WEEK 		= 10;
	public static int	REPEAT_INTERVAL_ANOTHER_WEEK 	= 11;
	public static int	REPEAT_INTERVAL_EVERY_MONTH 	= 20;
	public static int	REPEAT_INTERVAL_ANOTHER_MONTH 	= 21;
	public static int	REPEAT_INTERVAL_EVERY_QUARTER 	= 30;
	public static int	REPEAT_INTERVAL_EVERY_YEAR 		= 40;

	public static int	DAY_OF_WEEK_SUNDAY		= 0;
	public static int	DAY_OF_WEEK_MONDAY 		= 1;
	public static int	DAY_OF_WEEK_TUESDAY 	= 2;
	public static int	DAY_OF_WEEK_WENDSDAY 	= 3;
	public static int	DAY_OF_WEEK_THURSDAY	= 4;
	public static int	DAY_OF_WEEK_FRIDAY 		= 5;
	public static int	DAY_OF_WEEK_SATERDAY 	= 6;
	
	private int 		repeatInterval; 	// 반복 기간 : 매주, 격주, 매월, 격월, 매분기, 매년
	private LocalDate	repeatStart;		// 반복 시작일자
	private LocalDate		repeatEnd;			// 반복 종료일(반복종료일이나 반복횟수중 하나만 선택)
	private int			repeatCount;		// 반복 횟수(반복종료일이나 반복횟수중 하나만 선택)
	private int			monthOfYear;		// 년중에 몇번째 월  
	private int			weekOfMonth;		// 달중에 몇번째 주 
	private int			dayOfWeek;			// 주중에 무슨 요일 
	private int			dateOfMonth;		// 월중에 무슨 일
	private Date		dateOfYear;			// 년중에 무슨일
	private Date		startTime;			// 시작 시간
	private Date		endTime;			// 종료 시간(선택)
	public int getRepeatInterval() {
		return repeatInterval;
	}
	public void setRepeatInterval(int repeatInterval) {
		this.repeatInterval = repeatInterval;
	}
	public LocalDate getRepeatStart() {
		return repeatStart;
	}
	public void setRepeatStart(LocalDate repeatStart) {
		this.repeatStart = repeatStart;
	}
	public LocalDate getRepeatEnd() {
		return repeatEnd;
	}
	public void setRepeatEnd(LocalDate repeatEnd) {
		this.repeatEnd = repeatEnd;
	}
	public int getRepeatCount() {
		return repeatCount;
	}
	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}
	public int getMonthOfYear() {
		return monthOfYear;
	}
	public void setMonthOfYear(int monthOfYear) {
		this.monthOfYear = monthOfYear;
	}
	public int getWeekOfMonth() {
		return weekOfMonth;
	}
	public void setWeekOfMonth(int weekOfMonth) {
		this.weekOfMonth = weekOfMonth;
	}
	public int getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public int getDateOfMonth() {
		return dateOfMonth;
	}
	public void setDateOfMonth(int dateOfMonth) {
		this.dateOfMonth = dateOfMonth;
	}
	public Date getDateOfYear() {
		return dateOfYear;
	}
	public void setDateOfYear(Date dateOfYear) {
		this.dateOfYear = dateOfYear;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public RepeatEvent(){
		super();
	}
}
