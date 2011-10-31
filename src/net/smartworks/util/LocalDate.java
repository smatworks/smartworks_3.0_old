package net.smartworks.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class LocalDate extends Date{

	public static int ONE_SECOND = 1000;
	public static int ONE_MINUTE = 60*ONE_SECOND;
	public static int ONE_HOUR = 60*ONE_MINUTE;
	public static int ONE_DAY = 24*ONE_HOUR;
	public static int ONE_YEAR = 365*ONE_DAY;
	
	private static final long serialVersionUID = 1L;
	private TimeZone timeZone;
	private TimeZone hostTimeZone = TimeZone.getDefault();
	private Locale locale= new Locale(LocaleInfo.LOCALE_GENERAL);
	private long localNow = System.currentTimeMillis();
	public LocalDate(){
		super();
		super.setTime(super.getTime()-hostTimeZone.getRawOffset());
		this.timeZone = hostTimeZone;
	}
	public LocalDate(long GMTDate){
		super(GMTDate);
		if(this.timeZone==null) this.timeZone = hostTimeZone;
	}
	
	public LocalDate(long GMTDate, String timeZone, String locale){
		super(GMTDate);
		this.setTimeZone(timeZone);
		if(LocaleInfo.isSupportingLocale(locale))
			this.setLocale(locale);
	}

	public String getLocale(){
		return locale.toString();
	}
	public void setLocale(String locale){
		if(LocaleInfo.isSupportingLocale(locale)){
			this.locale = new Locale(locale);
		}
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
		if(isToday()){
			return (new SimpleDateFormat("HH:mm")).format(getLocalTime());
		}else if(isThisYear()){
			return (new SimpleDateFormat("MM.dd HH:mm")).format(getLocalTime());
		}
		return (new SimpleDateFormat("yyyy.MM.dd HH:mm")).format(getLocalTime());			
	}

	public String toLocalDateString(){
		return DateFormat.getDateInstance(DateFormat.FULL, locale).format(getLocalTime());
	}
	
	public String toLocalDateShortString(){
		return (new SimpleDateFormat("MM.dd E", locale)).format(getLocalTime());
	}
	
	public String toLocalTimeString(){
		return DateFormat.getTimeInstance(DateFormat.MEDIUM, locale).format(getLocalTime());
	}
	
	public String toLocalTimeShortString(){
		return (new SimpleDateFormat("HH:mm")).format(getLocalTime());
	}
	
	public void plusToGMTTime(long timeValue){
		this.setTime(this.getTime() + timeValue);
	}

	public boolean isSameDate(LocalDate when){
		if( getLocalDateOnly(this).getTime() == getLocalDateOnly(when).getTime()) return true;
		return false;
	}

	public boolean isBeforeDate(LocalDate when){
		if( getLocalDateOnly(this).getTime() > getLocalDateOnly(when).getTime()) return true;
		
		return false;
	}

	public boolean isAfterDate(LocalDate when){
		if(getLocalDateOnly(this).getTime() < getLocalDateOnly(when).getTime()) return true;
		return false;
	}
	
	private boolean isToday(){
		if(getLocalDateOnly(this).getTime() == getLocalDateOnly(new LocalDate(localNow)).getTime())
			return true;
		return false;
	}

	private boolean isThisYear(){
		if(getLocalYearOnly(this).getTime() == getLocalYearOnly(new LocalDate(localNow)).getTime())
			return true;
		return false;
	}

	public long getLocalTime(){
		if(this.timeZone == null){
			return super.getTime() + TimeZone.getDefault().getRawOffset();
		}else{
			return super.getTime() + this.timeZone.getRawOffset();
		}
	}
	
	private LocalDate getLocalDateOnly(LocalDate localDate){
		long time = localDate.getLocalTime();
		time = time/LocalDate.ONE_DAY;
		time = time*LocalDate.ONE_DAY;
		LocalDate lDate = new LocalDate(time-localDate.timeZone.getRawOffset());
		lDate.setTimeZone(localDate.getTimeZone());
		return lDate;

	}
	private LocalDate getLocalYearOnly(LocalDate localDate){
		long time = localDate.getLocalTime();
		time = time/LocalDate.ONE_YEAR;
		time = time*LocalDate.ONE_YEAR;
		LocalDate lDate = new LocalDate(time-localDate.timeZone.getRawOffset());
		lDate.setTimeZone(localDate.getTimeZone());
		return lDate;

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
