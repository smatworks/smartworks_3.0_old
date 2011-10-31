package net.smartworks.model.calendar;

import net.smartworks.util.LocalDate;

public class WorkHour {

	public int start; 
	public int end;
	public int workTime;

	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getWorkTime() {
		return workTime;
	}
	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}

	public WorkHour(){
		super();
	}
	public WorkHour(int start, int end, int workTime){
		this.start = start;
		this.end = end;
		this.workTime = workTime;
	}
	
	public int getStartInHour(){
		return start/LocalDate.ONE_HOUR;
	}

	public int getEndInHour(){
		return end/LocalDate.ONE_HOUR;
	}

	public int getWorkTimeInHour(){
		return workTime/LocalDate.ONE_HOUR;
	}
}
