package net.smartworks.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class LocalDate extends Date{

	private static final long serialVersionUID = 1L;
	private TimeZone timeZone;
	private TimeZone hostTimeZone = TimeZone.getDefault();
	private long localNow = System.currentTimeMillis();

	public LocalDate(){
		super();
		super.setTime(super.getTime()-hostTimeZone.getRawOffset());
	}
	public LocalDate(long GMTDate){
		super(GMTDate);
	}
	
	public LocalDate(long GMTDate, String timeZone){
		super(GMTDate);
		this.setTimeZone(timeZone);
	}

	public long getGMTDate(){
		return super.getTime();
	}
	public void setGMTDate(long GMTDate){
		super.setTime(GMTDate);
	}
	public String getTimeZone(){
		if(timeZone == null)
			return null;
		return timeZone.getID();
	}
	public void setTimeZone(String timeZone){
		if(LocalDate.isValidTimeZone(timeZone)){
			this.timeZone = TimeZone.getTimeZone(timeZone);
		}
	}
	public String toLocalString(){
		SimpleDateFormat sdf;
		if(isToday()){
			sdf = new SimpleDateFormat("HH:mm");
		}else{
			sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");			
		}
		return sdf.format(getLocalDate());
	}
	
	private boolean isToday(){
		if(getDateOnly(getLocalDate().getTime()) == getDateOnly(localNow))
			return true;
		return false;
	}

	private Date getLocalDate(){
		if(this.timeZone == null){
			return new Date(super.getTime() + TimeZone.getDefault().getRawOffset());
		}else{
			return new Date(super.getTime() + this.timeZone.getRawOffset());
		}
	}
	
	private long getDateOnly(long date){
		return date/24*60*60*1000;// 24시간 * 60분 * 60초 * 1000밀리세컨
	}
	
	public static boolean isValidTimeZone(String timeZone){
		String[] zoneIds = TimeZone.getAvailableIDs();
		for(String str : zoneIds)
			if(str.equals(timeZone)) return true;
		return false;
		
	}	
	
	public static Date convertLocalToGMT(long localDate, String timeZone){
		if(isValidTimeZone(timeZone))
			return new Date(localDate - TimeZone.getTimeZone(timeZone).getRawOffset());
		return new Date(localDate);
	}
	public static Date convertGMTToLocal(long GMTDate, String timeZone){
		if(isValidTimeZone(timeZone))
			return new Date(GMTDate + TimeZone.getTimeZone(timeZone).getRawOffset());
		return new Date(GMTDate);
	}
}
