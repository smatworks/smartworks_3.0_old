package net.smartworks.server.service.impl;

import net.smartworks.model.calendar.CompanyCalendar;
import net.smartworks.model.calendar.CompanyEvent;
import net.smartworks.model.calendar.WorkHour;
import net.smartworks.model.community.User;
import net.smartworks.model.instance.EventInstance;
import net.smartworks.model.work.SocialWork;
import net.smartworks.server.service.ICalendarService;
import net.smartworks.util.LocalDate;
import net.smartworks.util.LocaleInfo;
import net.smartworks.util.SmartTest;
import net.smartworks.util.SmartUtil;

import org.springframework.stereotype.Service;

@Service
public class CalendarServiceImpl implements ICalendarService {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getCompanyCalendars(net.smartworks
	 * .util.LocalDate, int)
	 */
	@Override
	public CompanyCalendar[] getCompanyCalendars(LocalDate fromDate, int days) throws Exception {
		CompanyCalendar cc1 = new CompanyCalendar(new LocalDate(), new CompanyEvent[] { SmartTest.getCompanyEvent1(), SmartTest.getCompanyEvent2() },
				new WorkHour());
		CompanyCalendar cc2 = new CompanyCalendar(new LocalDate((new LocalDate()).getTime() + LocalDate.ONE_DAY),
				new CompanyEvent[] { SmartTest.getCompanyEvent2() }, new WorkHour());
		CompanyCalendar cc3 = new CompanyCalendar(new LocalDate((new LocalDate()).getTime() + LocalDate.ONE_DAY * 2),
				new CompanyEvent[] { SmartTest.getCompanyEvent1() }, new WorkHour());
		cc1.getDate().setLocale(LocaleInfo.LOCALE_KOREAN);
		cc2.getDate().setLocale(LocaleInfo.LOCALE_KOREAN);
		cc3.getDate().setLocale(LocaleInfo.LOCALE_KOREAN);
		return new CompanyCalendar[] { cc1, cc2, cc3 };

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getCompanyCalendars(net.smartworks
	 * .util.LocalDate, net.smartworks.util.LocalDate)
	 */
	@Override
	public CompanyCalendar[] getCompanyCalendars(LocalDate fromDate, LocalDate toDate) throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getEventInstances(net.smartworks
	 * .util.LocalDate, int)
	 */
	@Override
	public EventInstance[] getEventInstances(LocalDate fromDate, int days) throws Exception {
		LocalDate time1 = new LocalDate();
		time1.plusToGMTTime(-1 * LocalDate.ONE_HOUR);
		LocalDate time2 = new LocalDate();
		time2.plusToGMTTime(LocalDate.ONE_HOUR);
		LocalDate time3 = new LocalDate();
		time3.plusToGMTTime(3 * LocalDate.ONE_HOUR);
		LocalDate time4 = new LocalDate();
		time4.plusToGMTTime(5 * LocalDate.ONE_HOUR);
		LocalDate time5 = new LocalDate();
		time5.plusToGMTTime(LocalDate.ONE_DAY);
		LocalDate time6 = new LocalDate();
		time6.plusToGMTTime(LocalDate.ONE_DAY + LocalDate.ONE_HOUR);
		LocalDate time7 = new LocalDate();
		time7.plusToGMTTime(2 * LocalDate.ONE_DAY + LocalDate.ONE_HOUR * 3);
		LocalDate time8 = new LocalDate();
		time8.plusToGMTTime(2 * LocalDate.ONE_DAY + LocalDate.ONE_HOUR * 7);
		LocalDate time9 = new LocalDate();
		time9.plusToGMTTime(1 * LocalDate.ONE_YEAR + LocalDate.ONE_HOUR * 10);
		LocalDate time10 = new LocalDate();
		time10.plusToGMTTime(1 * LocalDate.ONE_YEAR + LocalDate.ONE_HOUR * 14);
		EventInstance event1 = new EventInstance("event1", "정부장님 점심약속", new SocialWork("socialwork1", "Event Work"), SmartTest.getUser1(), new LocalDate());
		event1.setStart(time1);
		event1.setEnd(time2);
		event1.setRelatedUsers(new User[] { SmartTest.getUser2() });

		EventInstance event2 = new EventInstance("event2", "스마트웍스닷넷 디자인회의", new SocialWork("socialwork1", "Event Work"), SmartTest.getUser2(), new LocalDate());
		event2.setStart(time3);
		event2.setEnd(time4);
		event2.setRelatedUsers(new User[] { SmartTest.getUser1(), SmartTest.getUser2(), SmartTest.getUser3() });
		event2.setWorkSpace(SmartTest.getGroup1());

		EventInstance event3 = new EventInstance("event3", "주간업무 보고회의", new SocialWork("socialwork1", "Event Work"), SmartTest.getUser3(), new LocalDate());
		event3.setStart(time5);
		event3.setEnd(time6);
		event3.setWorkSpace(SmartTest.getDepartment1());

		EventInstance event4 = new EventInstance("event4", "KT 스마트웍킹팀 저녁", new SocialWork("socialwork1", "Event Work"), SmartTest.getUser1(), new LocalDate());
		event4.setStart(time7);
		event4.setEnd(time8);

		EventInstance event5 = new EventInstance("event5", "진산회 골프모임", new SocialWork("socialwork1", "Event Work"), SmartUtil.getCurrentUser(), new LocalDate());
		event5.setStart(time9);
		event5.setEnd(time10);
		return new EventInstance[] { event1, event2, event3, event4, event5 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getEventInstances(net.smartworks
	 * .util.LocalDate, net.smartworks.util.LocalDate)
	 */
	@Override
	public EventInstance[] getEventInstances(LocalDate fromDate, LocalDate toDate) throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getCompanyEventBox(net.smartworks
	 * .util.LocalDate)
	 */
	@Override
	public CompanyCalendar getCompanyEventBox(LocalDate date) throws Exception {
		CompanyCalendar eventBox = new CompanyCalendar();
		eventBox.setDate(date);
		CompanyEvent event1 = new CompanyEvent();
		eventBox.setCompanyEvents(new CompanyEvent[] { event1 });
		return eventBox;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getCompanyEventsByDate(net.smartworks
	 * .util.LocalDate, int)
	 */
	@Override
	public EventInstance[] getCompanyEventsByDate(LocalDate date, int maxEvents) throws Exception {
		EventInstance[] events = new EventInstance[] {

		};
		return events;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.smartworks.service.impl.ISmartWorks#getMyEventsByDate(java.lang.String
	 * , net.smartworks.util.LocalDate, int)
	 */
	@Override
	public EventInstance[] getMyEventsByDate(String userId, LocalDate date, int maxEvents) throws Exception {
		EventInstance[] events = new EventInstance[] {

		};
		return events;
	}


	
}
