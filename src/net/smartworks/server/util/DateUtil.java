package net.smartworks.server.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	public static final long TIME_SECOND = 1000;
	public static final long TIME_MINUTE = TIME_SECOND * 60;
	public static final long TIME_HOUR = TIME_MINUTE * 60;
	public static final long TIME_DAY = TIME_HOUR * 24;
	public static final long TIME_WEEK = TIME_DAY * 7;
	
	public static final String CYCLE_YEAR = "Year";
	public static final String CYCLE_HALF = "Half";
	public static final String CYCLE_QUARTER = "Quarter";
	public static final String CYCLE_MONTH = "Month";
	public static final String CYCLE_WEEK = "Week";
	public static final String CYCLE_DAY = "Day";
	public static final String CYCLE_HOUR = "Hour";
	public static final String CYCLE_MINUTE = "Minute";
	public static final String[] CYCLE = new String[] {
		CYCLE_YEAR,
		CYCLE_HALF,
		CYCLE_QUARTER,
		CYCLE_MONTH,
		CYCLE_WEEK,
		CYCLE_DAY,
		CYCLE_HOUR,
		CYCLE_MINUTE
	};
	
//	private static String EMPTY = CommonUtil.EMPTY;
	private static String SPACE = CommonUtil.SPACE;
	private static String DOT = CommonUtil.DOT;
	private static String DASH = CommonUtil.DASH;
	private static String COLON = CommonUtil.COLON;
	public static final String DATEFORMATSTRING_XSDDATETIME = "yyyy-MM-dd'T'HH:mm:ss'.'S";
	public static final String DATEFORMATSTRING_XSDSMALLDATETIME = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String DATEFORMATSTRING_XSDDATETIMEZ = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String DATEFORMATSTRING_XSDDATE = "yyyy-MM-dd";
	public static final SimpleDateFormat DATEFORMAT_XSDDATETIME = new SimpleDateFormat(DATEFORMATSTRING_XSDDATETIME);
	public static final SimpleDateFormat DATEFORMAT_XSDSMALLDATETIME = new SimpleDateFormat(DATEFORMATSTRING_XSDSMALLDATETIME);
	public static final SimpleDateFormat DATEFORMAT_XSDDATETIMEZ = new SimpleDateFormat(DATEFORMATSTRING_XSDDATETIMEZ);
	public static final SimpleDateFormat DATEFORMAT_XSDDATE = new SimpleDateFormat(DATEFORMATSTRING_XSDDATE);
	protected static final SimpleDateFormat DATEFORMAT[] = {
		DATEFORMAT_XSDDATETIME,
		DATEFORMAT_XSDSMALLDATETIME,
		DATEFORMAT_XSDDATETIMEZ,
		DATEFORMAT_XSDDATE,
		new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US),
		new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US),
		new SimpleDateFormat("EEEEEE, dd-MMM-yy HH:mm:ss zzz", Locale.US),
		new SimpleDateFormat("EEE MMMM d HH:mm:ss yyyy", Locale.US)
	};
	
	public static int toDay(long milli) {
		long day = milli/TIME_DAY;
		long other = milli%TIME_DAY;
		if (other > 0)
			day++;
		return (int)day;
	}
	public static Date toFromDate(Date date, String cycle) {
		if (date == null || cycle == null)
			return date;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		if (cycle.equals(CYCLE_YEAR)) {
		} else if (cycle.equals(CYCLE_HALF)) {
			if (month <= Calendar.JUNE) {
				cal.set(Calendar.MONTH, Calendar.JANUARY);
			} else {
				cal.set(Calendar.MONTH, Calendar.JULY);
			}
		} else if (cycle.equals(CYCLE_QUARTER)) {
			if (month <= Calendar.MARCH) {
				cal.set(Calendar.MONTH, Calendar.JANUARY);
			} else if (month <= Calendar.JUNE) {
				cal.set(Calendar.MONTH, Calendar.APRIL);
			} else if (month <= Calendar.SEPTEMBER) {
				cal.set(Calendar.MONTH, Calendar.JULY);
			} else {
				cal.set(Calendar.MONTH, Calendar.OCTOBER);
			}
			return cal.getTime();
		} else if (cycle.equals(CYCLE_MONTH)) {
			cal.set(Calendar.MONTH, month);
		} else if (cycle.equals(CYCLE_WEEK)) {
			// TODO
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
		} else if (cycle.equals(CYCLE_DAY)) {
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, day);
		} else if (cycle.equals(CYCLE_HOUR)) {
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, day);
			cal.set(Calendar.HOUR_OF_DAY, hour);
		} else if (cycle.equals(CYCLE_MINUTE)) {
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, day);
			cal.set(Calendar.HOUR_OF_DAY, hour);
			cal.set(Calendar.MINUTE, minute);
		}
		return cal.getTime();
	}
	public static Date toToDate(Date date, String cycle) {
		if (date == null || cycle == null)
			return date;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 997);
		if (cycle.equals(CYCLE_YEAR)) {
		} else if (cycle.equals(CYCLE_HALF)) {
			if (month <= Calendar.JUNE) {
				cal.set(Calendar.MONTH, Calendar.JUNE);
				cal.set(Calendar.DAY_OF_MONTH, 30);
			} else {
				cal.set(Calendar.MONTH, Calendar.DECEMBER);
				cal.set(Calendar.DAY_OF_MONTH, 31);
			}
		} else if (cycle.equals(CYCLE_QUARTER)) {
			if (month <= Calendar.MARCH) {
				cal.set(Calendar.MONTH, Calendar.MARCH);
				cal.set(Calendar.DAY_OF_MONTH, 31);
			} else if (month <= Calendar.JUNE) {
				cal.set(Calendar.MONTH, Calendar.JUNE);
				cal.set(Calendar.DAY_OF_MONTH, 30);
			} else if (month <= Calendar.SEPTEMBER) {
				cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
				cal.set(Calendar.DAY_OF_MONTH, 30);
			} else {
				cal.set(Calendar.MONTH, Calendar.DECEMBER);
				cal.set(Calendar.DAY_OF_MONTH, 31);
			}
			return cal.getTime();
		} else if (cycle.equals(CYCLE_MONTH)) {
			cal.set(Calendar.MONTH, month);
			if (month == Calendar.FEBRUARY) {
				if (year%4 == 0) {
					cal.set(Calendar.DAY_OF_MONTH, 29);
				} else {
					cal.set(Calendar.DAY_OF_MONTH, 28);
				}
			} else if (month == Calendar.APRIL ||
					month == Calendar.JUNE ||
					month == Calendar.SEPTEMBER ||
					month == Calendar.NOVEMBER) {
				cal.set(Calendar.DAY_OF_MONTH, 30);
			} else {
				cal.set(Calendar.DAY_OF_MONTH, 31);
			}
		} else if (cycle.equals(CYCLE_WEEK)) {
			// TODO
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
		} else if (cycle.equals(CYCLE_DAY)) {
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, day);
		} else if (cycle.equals(CYCLE_HOUR)) {
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, day);
			cal.set(Calendar.HOUR_OF_DAY, hour);
		} else if (cycle.equals(CYCLE_MINUTE)) {
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, day);
			cal.set(Calendar.HOUR_OF_DAY, hour);
			cal.set(Calendar.MINUTE, minute);
		}
		return cal.getTime();
	}

	public static Date toDate(String dateString, DateFormat dateFormat) throws Exception {
		if (dateString == null || dateFormat == null)
			return null;
		Date date = dateFormat.parse(dateString);
		return date;
	}
	public static Date toDate(String dateString, String dateFormatString) throws Exception {
		if (dateString == null || dateFormatString == null)
			return null;
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		Date date = dateFormat.parse(dateString);
		return date;
	}
	public static Date toDate(String dateString) throws Exception {
		if (dateString == null || dateString.trim().length() == 0)
			return null;
		dateString = dateString.trim();
		
		// GMT String�� ���
		if (dateString.endsWith("GMT"))
			return new Date(dateString);

		Date date = null;
		for (int i = 0; i < DATEFORMAT.length; i++) {
			try {
				SimpleDateFormat dateFormat = DATEFORMAT[i];
				date = toDate(dateString, dateFormat);
				break;
			} catch (Exception e) {
			}
		}
		return date;
		
//		// XsdDateTime Stirng�� ���
//		String[] dateTimeStrs = StringUtils.tokenizeToStringArray(dateString, "-");
//		if (dateTimeStrs.length == 3) {
//			if (dateTimeStrs[2].endsWith("Z"))
//				dateTimeStrs[2] = dateTimeStrs[2].replaceAll("Z", "");
//			String[] underDateStrs = StringUtils.tokenizeToStringArray(dateTimeStrs[2], "T");
//			
//			int year = Integer.parseInt(dateTimeStrs[0]);
//			int month = Integer.parseInt(dateTimeStrs[1])-1;
//			int date = Integer.parseInt(underDateStrs[0]);
//			int hour = 0;
//			int minute = 0;
//			int second = 0;
//			int millisecond = 0;
//			
//			if (underDateStrs.length > 1) {
//				String[] timeStrs = StringUtils.tokenizeToStringArray(underDateStrs[1], ":");
//				String[] secStrs = StringUtils.tokenizeToStringArray(timeStrs[2], ".");
//				hour = Integer.parseInt(timeStrs[0]);
//				minute = Integer.parseInt(timeStrs[1]);
//				second = Integer.parseInt(secStrs[0]);
//				if (secStrs.length > 1) {
//					String millisecStr = secStrs[1];
//					if (millisecStr.length() == 1) {
//						millisecStr += "00";
//					} else if (millisecStr.length() == 2) {
//						millisecStr += "0";
//					}
//					millisecond = Integer.parseInt(millisecStr);
//				}
//			}
//
//			Calendar cal = Calendar.getInstance();
//			cal.set(year, month, date, hour, minute, second);
//			cal.set(Calendar.MILLISECOND, millisecond);
//			return cal.getTime();
//		}
//		
//		return null;
	}
	
	public static String toString(Date date, DateFormat dateFormat) {
		if (date == null || dateFormat == null)
			return null;
		String dateString = dateFormat.format(date);
		return dateString;
	}
	public static String toString(Date date, String dateFormatString) {
		if (date == null || dateFormatString == null)
			return null;
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		String dateString = dateFormat.format(date);
		return dateString;
	}
	public static String toXsdDateString(Date date) {
		if (date == null)
			return null;
		String dateString = toString(date, DATEFORMAT_XSDDATE);
		return dateString;
	}
	public static String toXsdDateTimezString(Date date) {
		if (date == null)
			return null;
		String dateString = toString(date, DATEFORMAT_XSDDATETIMEZ);
		return dateString;
		
//		String xsdDateStr = toXsdDateTimeString(date);
//		String[] xsdDateStrs = StringUtils.tokenizeToStringArray(xsdDateStr, ".");
//		return xsdDateStrs[0] + "Z";
	}
	public static String toXsdDateTimeString(Date date) {
		if (date == null)
			return null;
		String dateString = toString(date, DATEFORMAT_XSDDATETIME);
		return dateString;
		
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		
//		String year = calendar.get(Calendar.YEAR)+EMPTY;
//		String month = calendar.get(Calendar.MONTH)+1+EMPTY;
//		String day = calendar.get(Calendar.DAY_OF_MONTH)+EMPTY;
//		String hour = calendar.get(Calendar.HOUR_OF_DAY)+EMPTY;
//		String minute = calendar.get(Calendar.MINUTE)+EMPTY;
//		String second = calendar.get(Calendar.SECOND)+EMPTY;
//		String millisecond = calendar.get(Calendar.MILLISECOND)+EMPTY;
//		
//		String zero = "0";
//		if (month.length() == 1)
//			month = zero + month;
//		if (day.length() == 1)
//			day = zero + day;
//		if (hour.length() == 1)
//			hour = zero + hour;
//		if (minute.length() == 1)
//			minute = zero + minute;
//		if (second.length() == 1)
//			second = zero + second;
//		switch (millisecond.length()) {
//			case 1:
//				millisecond = "00" + millisecond;
//				break;
//			case 2:
//				millisecond = zero + millisecond;
//				break;
//		}
//		
//		StringBuffer dateBuf = new StringBuffer();
//		dateBuf.append(year).append(DASH).append(month).append(DASH).append(day);
//		dateBuf.append("T").append(hour).append(COLON).append(minute).append(COLON).append(second).append(DOT).append(millisecond);
//		
//		return dateBuf.toString();
	}
	
	public static String toDateString(Date date) {
		if (date == null)
			return null;
		
		DateInfo dateInfo = new DateInfo(date);
		
		String year = dateInfo.getYear();
		String month = dateInfo.getMonth();
		String day = dateInfo.getDay();
		String hour = dateInfo.getHour();
		String minute = dateInfo.getMinute();
		String second = dateInfo.getSecond();
		String millisecond = dateInfo.getMillisecond();
		
		StringBuffer dateBuf = new StringBuffer();
		dateBuf.append(year).append(DASH).append(month).append(DASH).append(day);
		dateBuf.append(SPACE).append(hour).append(COLON).append(minute).append(COLON).append(second).append(DOT).append(millisecond);
		
		return dateBuf.toString();
	}
}

class DateInfo {
	private String year;
	private String month;
	private String day;
	private String hour;
	private String minute;
	private String second;
	private String millisecond;
	public DateInfo(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		year = calendar.get(Calendar.YEAR)+"";
		month = calendar.get(Calendar.MONTH)+1+"";
		day = calendar.get(Calendar.DAY_OF_MONTH)+"";
		hour = calendar.get(Calendar.HOUR_OF_DAY)+"";
		minute = calendar.get(Calendar.MINUTE)+"";
		second = calendar.get(Calendar.SECOND)+"";
		millisecond = calendar.get(Calendar.MILLISECOND)+"";
		
		if (month.length() == 1)
			month = "0" + month;
		if (day.length() == 1)
			day = "0" + day;
		if (hour.length() == 1)
			hour = "0" + hour;
		if (minute.length() == 1)
			minute = "0" + minute;
		if (second.length() == 1)
			second = "0" + second;
		switch (millisecond.length()) {
			case 1:
				millisecond = "00" + millisecond;
				break;
			case 2:
				millisecond = "0" + millisecond;
				break;
		}
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getMillisecond() {
		return millisecond;
	}
	public void setMillisecond(String millisecond) {
		this.millisecond = millisecond;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String sceond) {
		this.second = sceond;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
}
