package net.smartworks.model.calendar;

public class CalendarInfo {

	public static int DAY_SUNDAY	= 0;
	public static int DAY_MONDAY	= 1;
	public static int DAY_TUESDAY	= 2;
	public static int DAY_WEDNESDAY	= 3;
	public static int DAY_THURDAY	= 4;
	public static int DAY_FRIDAY	= 5;
	public static int DAY_SATERDAY = 6;
			
	public static int DAYS_OF_WEEK	= 7;
	
/*			
			public static const QUARTERS_LONG: ArrayCollection = new ArrayCollection([	{kor:"1분기", eng:"1st Quarter"},
																						{kor:"2분기", eng:"2nd Quarter"},
																						{kor:"3분기", eng:"3rd Quarter"},
																						{kor:"4분기", eng:"4th Quarter"}]);

			public static const QUARTERS_SHORT: ArrayCollection = new ArrayCollection([	{kor:"1분기", eng:"Q1"},
																						{kor:"2분기", eng:"Q2"},
																						{kor:"3분기", eng:"Q3"},
																						{kor:"4분기", eng:"Q4"}]);

			public static const MONTHS_LONG: ArrayCollection = new ArrayCollection([{kor:"1월", eng:"January"},
																					{kor:"2월", eng:"February"},
																					{kor:"3월", eng:"March"},
																					{kor:"4월", eng:"April"},
																					{kor:"5월", eng:"May"},
																					{kor:"6월", eng:"June"},
																					{kor:"7월", eng:"July"},
																					{kor:"8월", eng:"August"},
																					{kor:"9월", eng:"September"},
																					{kor:"10월", eng:"October"},
																					{kor:"11월", eng:"November"},
																					{kor:"12월", eng:"December"}]);
																	
			public static const MONTHS_SHORT: ArrayCollection = new ArrayCollection([	{kor:"1월", eng:"JAN"},
																						{kor:"2월", eng:"FEB"},
																						{kor:"3월", eng:"MAR"},
																						{kor:"4월", eng:"APR"},
																						{kor:"5월", eng:"MAY"},
																						{kor:"6월", eng:"JUN"},
																						{kor:"7월", eng:"JUL"},
																						{kor:"8월", eng:"AUG"},
																						{kor:"9월", eng:"SEP"},
																						{kor:"10월", eng:"OCT"},
																						{kor:"11월", eng:"NOV"},
																						{kor:"12월", eng:"DEC"}]);
															
			public static const DAYS_LONG: ArrayCollection = new ArrayCollection([	{kor:"일요일", eng:"Sunday"},
																					{kor:"월요일", eng:"Monday"},
																					{kor:"화요일", eng:"Tuesday"},
																					{kor:"수요일", eng:"Wednesday"},
																					{kor:"목요일", eng:"Thursday"},
																					{kor:"금요일", eng:"Friday"},
																					{kor:"토요일", eng:"Saterday"}]);
																
			public static const DAYS_SHORT: ArrayCollection = new ArrayCollection([	{kor:"일", eng:"SUN"},
																					{kor:"월", eng:"MON"},
																					{kor:"화", eng:"TUE"},
																					{kor:"수", eng:"WED"},
																					{kor:"목", eng:"THU"},
																					{kor:"금", eng:"FRI"},
																					{kor:"토", eng:"SAT"}]);
																
			public static const HOURS_LONG: ArrayCollection= new ArrayCollection([	{kor:"오전 0:00", eng:"AM 0:00"},
																					{kor:"오전 1:00", eng:"AM 1:00"},
																					{kor:"오전 2:00", eng:"AM 2:00"},
																					{kor:"오전 3:00", eng:"AM 3:00"},
																					{kor:"오전 4:00", eng:"AM 4:00"},
																					{kor:"오전 5:00", eng:"AM 5:00"},
																					{kor:"오전 6:00", eng:"AM 6:00"},
																					{kor:"오전 7:00", eng:"AM 7:00"},
																					{kor:"오전 8:00", eng:"AM 8:00"},
																					{kor:"오전 9:00", eng:"AM 9:00"},
																					{kor:"오전 10:00", eng:"AM 10:00"},
																					{kor:"오전 11:00", eng:"AM 11:00"},
																					{kor:"오전 12:00", eng:"AM 12:00"},
																					{kor:"오후 1:00", eng:"PM 1:00"},
																					{kor:"오후 2:00", eng:"PM 2:00"},
																					{kor:"오후 3:00", eng:"PM 3:00"},
																					{kor:"오후 4:00", eng:"PM 4:00"},
																					{kor:"오후 5:00", eng:"PM 5:00"},
																					{kor:"오후 6:00", eng:"PM 6:00"},
																					{kor:"오후 7:00", eng:"PM 7:00"},
																					{kor:"오후 8:00", eng:"PM 8:00"},
																					{kor:"오후 9:00", eng:"PM 9:00"},
																					{kor:"오후 10:00", eng:"PM 10:00"},
																					{kor:"오후 11:00", eng:"PM 11:00"}]);
														
			public static const HOURS_SHORT: ArrayCollection= new ArrayCollection([	{kor:"00", eng:"00"},
																					{kor:"01", eng:"01"},
																					{kor:"02", eng:"02"},
																					{kor:"03", eng:"03"},
																					{kor:"04", eng:"04"},
																					{kor:"05", eng:"05"},
																					{kor:"06", eng:"06"},
																					{kor:"07", eng:"07"},
																					{kor:"08", eng:"08"},
																					{kor:"09", eng:"09"},
																					{kor:"10", eng:"10"},
																					{kor:"11", eng:"11"},
																					{kor:"12", eng:"12"},
																					{kor:"13", eng:"13"},
																					{kor:"14", eng:"14"},
																					{kor:"15", eng:"15"},
																					{kor:"16", eng:"16"},
																					{kor:"17", eng:"17"},
																					{kor:"18", eng:"18"},
																					{kor:"19", eng:"19"},
																					{kor:"20", eng:"20"},
																					{kor:"21", eng:"21"},
																					{kor:"22", eng:"22"},
																					{kor:"23", eng:"23"}]);
			private static var _locale:String= "ko_KR";
			public static function set locale(value:String):void{
				if(value) _locale = value;
			}
			
			public static function get locale():String{
				return _locale;
			}

			public static function getHoursArrayLong():Array{
				var hoursArray:Array = new Array();
				var hour:Object;
				if(locale == "ko_KR")
					for each(hour in HOURS_LONG)
						hoursArray.push(hour["kor"]);
				else if(locale == "en_US")
					for each(hour in HOURS_LONG)
						hoursArray.push(hour["eng"]);
				return hoursArray;			
			}
			
			public static function getDaysArrayShort():Array{
				var daysArray:Array = new Array();
				var day:Object;
				if(locale == "ko_KR")
					for each(day in DAYS_SHORT)
						daysArray.push(day["kor"]);
				else if(locale == "en_US")
					for each(day in DAYS_SHORT)
						daysArray.push(day["eng"]);
				return daysArray;
			}
			public static function dueMonthToString(date:Date):String{
				if(locale == "ko_KR")
					return "D+" + ((date.getFullYear()-GanttPackage.GANTTCHART_BASEDATE.getFullYear())*12+date.getMonth()).toString() + "월";
				else if(locale == "en_US")
					return "D+" + ((date.getFullYear()-GanttPackage.GANTTCHART_BASEDATE.getFullYear())*12+date.getMonth()).toString() + "Month(s)";
				else
					return null;
			}
			
			public static function dueMonthToDate(dateString:String):Date{
				var months:int=-1, years:int=-1, monthsString:String;
				if(locale == "ko_KR"){
					if(!dateString || dateString.length<5) return null;
					monthsString = dateString.substring(2,dateString.indexOf("월"));
					if(monthsString.length>0) months = monthsString as int;
					if(months == -1) return null;
					years = months/12;
					return new Date(GanttPackage.GANTTCHART_BASEDATE.getFullYear()+years, GanttPackage.GANTTCHART_BASEDATE.getMonth()+(months-years*12));
				}else if(locale == "en_US"){
					if(!dateString || dateString.length<11) return null;
					monthsString = dateString.substring(2,dateString.indexOf("Month(s)"));
					if(monthsString.length>0) months = monthsString as int;
					if(months == -1) return null;
					years = months/12;
					return new Date(GanttPackage.GANTTCHART_BASEDATE.getFullYear()+years, GanttPackage.GANTTCHART_BASEDATE.getMonth()+(months-years*12));
				}else{
					return null;
				}
			}
			
			public static function fullDayToString(date:Date, deploy:Boolean): String{
				if(locale == "ko_KR" )
					if(!deploy)
						return 	dueMonthToString(date) + " " + date.getDate().toString() + "일 " + dayToString(date.getDay()) + " ";
					else
						return 	date.getFullYear().toString() + "년 " + 
								monthToString(date.getMonth()+1)+ " " + 
								date.getDate().toString() + "일 " + 
								dayToString(date.getDay()) + " ";
				else if(locale == "en_US")
					if(!deploy)
						return 	dayToString(date.getDay()) + ", " +
								dueMonthToString(date) + " " +
								date.getDate().toString();
					else
						return 	dayToString(date.getDay()) + ", " +
								monthToString(date.getMonth()+1) + " " +
								date.getDate().toString() + ", " +
								date.getFullYear().toString();
				else
					return null;
			
			}
			
			public static function fullDayToStringShort(date:Date, deploy:Boolean): String{
				if(locale == "ko_KR")
					if(!deploy)
						return 	dueMonthToString(date) + date.getDate().toString() + "일"; 
					else
						return 	date.getFullYear().toString() + "년" + 
								monthToString(date.getMonth()+1) + 
								date.getDate().toString() + "일"; 
				else if(locale == "en_US")
					if(!deploy)
						return 	dueMonthToString(date) + "," + date.getDate().toString() + "Day(s)";
					else
						return 	monthToString(date.getMonth()+1) + " " +
								date.getDate().toString() + ", " +
								date.getFullYear().toString();
				else
					return null;
				
			}
			
			public static function fullDayToDateShort(dateString:String, deploy:Boolean): Date{
				var dueDay:Date = dueMonthToDate(dateString), days:int = -1, daysString:String;
				if(!dueDay) return null;
				if(locale == "ko_KR"){
					if(!deploy){
						daysString = dateString.substring(dateString.indexOf("월")+2, dateString.indexOf("일"));
						if(daysString.length>0) days = daysString as int;
						if(days==-1 || days>31) return null;
						dueDay.setDate(days);
						return dueDay;
					}
					return null;
				}else if(locale == "en_US"){
					if(!deploy){
						daysString = dateString.substring(dateString.indexOf("Month(s)")+9, dateString.indexOf("Day(s)"));
						if(daysString.length>0) days = daysString as int;
						if(days==-1 || days>31) return null;
						dueDay.setDate(days);
						return dueDay;
					}
					return null;
				}
				return null;		
			}
			
			public static function fullMonthToString(date:Date, deploy:Boolean): String{
				if(locale == "ko_KR")
					if(!deploy)
						return 	dueMonthToString(date); 
					else
						return 	date.getFullYear().toString() + "년" + 
								monthToString(date.getMonth()+1); 
				else if(locale == "en_US")
					if(!deploy)
						return 	dueMonthToString(date);
					else
						return 	monthToString(date.getMonth()+1) + ", " +
								date.getFullYear().toString();
				else
					return null;
			
			}
			
			public static function fullYearToString(date:Date, deploy:Boolean): String{
				if(locale == "ko_KR")
					if(!deploy)
						return 	"D+" + (date.getFullYear()-GanttPackage.GANTTCHART_BASEDATE.getFullYear()).toString() + "년"; 
					else
						return 	date.getFullYear().toString() + "년"; 
				else if(locale == "en_US")
					if(!deploy)
						return 	"D+" + (date.getFullYear()-GanttPackage.GANTTCHART_BASEDATE.getFullYear()).toString() + "Year(s)";
					else
						return 	"Year " + date.getFullYear().toString();
				else
					return null;
				
			}
			
			public static function dateDayToString(date: Date): String{
				if(!date) return null;

				if(locale == "ko_KR")
					return date.getDate().toString()+" ("+CalendarInfo.DAYS_SHORT[date.getDay()].kor+")";
				else if(locale == "en_US")
					return date.getDate().toString()+"("+CalendarInfo.DAYS_SHORT[date.getDay()].eng+")" ;
				else			
					return null;
			}

			public static function hourToString(hour: int): String{
				if(hour<0 || hour>23 ) return null;
				
				if(locale == "ko_KR")
					return CalendarInfo.HOURS_LONG[hour].kor;
				else if(locale == "en_US")
					return CalendarInfo.HOURS_LONG[hour].eng;
				else
					return null;
			}

			public static function hourToStringShort(hour: int): String{
				if(hour<0 || hour>23 ) return null;

				if(locale == "ko_KR")
					return CalendarInfo.HOURS_SHORT[hour].kor;
				else if(locale == "en_US")
					return CalendarInfo.HOURS_SHORT[hour].eng;
				else			
					return null;
			}
			
			public static function dayToString(day: int): String{
				if(day<0 || day>=CalendarInfo.DAYS_OF_WEEK ) return null;

				if(locale == "ko_KR")
					return CalendarInfo.DAYS_LONG[day].kor;
				else if(locale == "en_US")
					return CalendarInfo.DAYS_LONG[day].eng;
				else			
					return null;
			}

			public static function dayToStringShort(day: int): String{
				if(day<0 || day>=CalendarInfo.DAYS_OF_WEEK ) return null;

				if(locale == "ko_KR")
					return CalendarInfo.DAYS_SHORT[day].kor;
				else if(locale == "en_US")
					return CalendarInfo.DAYS_SHORT[day].eng;
				else			
					return null;
			}
			
			public static function dateToString(date: int): String{
				if(date<1 || date>31 ) return null;

				if(locale == "ko_KR")
					return date.toString()+"일";
				else if(locale == "en_US")
					return date.toString();
				else
					return null;
			}

			public static function dateToStringShort(date: int): String{
				if(date<1 || date>31 ) return null;

				if(locale == "ko_KR")
					return date.toString();
				else if(locale == "en_US")
					return date.toString();
				else
					return null;
			}

			public static function monthToString(month: int): String{
				if(month<1 || month>12 ) return null;

				if(locale == "ko_KR")
					return CalendarInfo.MONTHS_LONG[month-1].kor;
				else if(locale == "en_US")
					return CalendarInfo.MONTHS_LONG[month-1].eng;
				else
					return null;
			}

			public static function monthToStringShort(month: int): String{
				if(month<1 || month>12 ) return null;

				if(locale == "ko_KR")
					return CalendarInfo.MONTHS_SHORT[month-1].kor;
				else if(locale == "en_US")
					return CalendarInfo.MONTHS_SHORT[month-1].eng;
				else
					return null;
			}

			public static function quarterToString(quarter: int): String{
				if(quarter<1 || quarter>4 ) return null;

				if(locale == "ko_KR")
					return CalendarInfo.QUARTERS_LONG[quarter-1].kor;
				else if(locale == "en_US")
					return CalendarInfo.QUARTERS_LONG[quarter-1].eng;
			
				return null;
			}

			public static function quarterToStringShort(quarter: int): String{
				if(quarter<1 || quarter>4 ) return null;

				if(locale == "ko_KR")
					return CalendarInfo.QUARTERS_SHORT[quarter-1].kor;
				else if(locale == "en_US")
					return CalendarInfo.QUARTERS_SHORT[quarter-1].eng;
				
				return null;
			}
			
			public static function getEndOfThisWeek(date: Date): Date{
				if(date){
					var day: int=date.getDay();
					return new Date(date.time+(CalendarInfo.DAYS_OF_WEEK-(day+1))*24*60*60*1000);
				}
				
				return null;
			}		
		}
	}	
*/	
}
