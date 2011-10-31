package net.smartworks.server.service;

import net.smartworks.model.calendar.CompanyCalendar;
import net.smartworks.model.instance.EventInstance;
import net.smartworks.util.LocalDate;

public interface ICalendarService {

	public abstract EventInstance[] getEventInstances(LocalDate fromDate, LocalDate toDate) throws Exception;

	public abstract EventInstance[] getEventInstances(LocalDate fromDate, int days) throws Exception;

	public abstract CompanyCalendar[] getCompanyCalendars(LocalDate fromDate, LocalDate toDate) throws Exception;

	public abstract CompanyCalendar[] getCompanyCalendars(LocalDate fromDate, int days) throws Exception;

	public abstract CompanyCalendar getCompanyEventBox(LocalDate date) throws Exception;

	public abstract EventInstance[] getCompanyEventsByDate(LocalDate date, int maxEvents) throws Exception;

	public abstract EventInstance[] getMyEventsByDate(String userId, LocalDate date, int maxEvents) throws Exception;

}
