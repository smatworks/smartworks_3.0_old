package net.smartworks.model.calendar;

public class WorkHour {

	public int start;		//분단위 시작시간 
	public int end;			//분단위 종료시간
	public int workTime;	//분단위 업무시간

	public int getStart() {
		return start;
	}
	public void setStart(int startInMinute) {
		this.start = startInMinute;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int endInMinute) {
		this.end = endInMinute;
	}
	public int getWorkTime() {
		return workTime;
	}
	public void setWorkTime(int workTimeInMinute) {
		this.workTime = workTimeInMinute;
	}

	public WorkHour(){
		super();
	}
	public WorkHour(int startInMinute, int endInMinute, int workTimeInMinute){
		this.start = startInMinute;
		this.end = endInMinute;
		this.workTime = workTimeInMinute;
	}
	
	public int getStartInHour(){
		return start/60;
	}

	public int getEndInHour(){
		return end/60;
	}

	public int getWorkTimeInHour(){
		return workTime/60;
	}
}
