package net.smartworks.model.calendar;

import net.smartworks.util.LocalDate;

public class CompanyEventBox {
	
	private LocalDate date;
	private CompanyEvent[] companyEvents;
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public CompanyEvent[] getCompanyEvents() {
		return companyEvents;
	}
	public void setCompanyEvents(CompanyEvent[] companyEvents) {
		this.companyEvents = companyEvents;
	}

	public CompanyEventBox(){
		super();
	}

}
