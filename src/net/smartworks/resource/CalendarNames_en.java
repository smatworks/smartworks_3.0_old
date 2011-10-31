package net.smartworks.resource;

import java.util.ListResourceBundle;

public class CalendarNames_en extends ListResourceBundle {

	@Override
	protected Object[][] getContents() {

		return new Object[][] {
				{ "quarters.long",
						new String[] { "1st Quarter", "2nd Quarter",
								"3rd Quarter", "4th Quarter" } },
				{ "quarters.short", new String[] { "1Q", "2Q", "3Q", "4Q" } },
				{ "weeks.long",
						new String[] { "1st Week", "2nd Week", "3rd Week",
								"4th Week", "5th Week" } },
				{ "weeks.short", new String[] { "1W", "2W", "3W", "4W", "5W" } },
				{ "days.long", new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saterday"}},
				{ "days.short", new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" }},
				{ "halfdays.long", new String[] { "AM", "PM" } },
				{ "workhours.long",
						new String[] { "Before working hour", "Working hour",
								"After working hour" } },
				{ "workhours.short",
						new String[] { "Before work", "Work hour", "After work" } }

		};
	}
}
