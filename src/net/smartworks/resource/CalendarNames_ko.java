package net.smartworks.resource;

import java.util.ListResourceBundle;

public class CalendarNames_ko extends ListResourceBundle {

	@Override
	protected Object[][] getContents() {

		return new Object[][] {
				{ "quarters.long", new String[] { "1분기", "2분기", "3분기", "4분기" } },
				{ "quarters.short", new String[] { "1분기", "2분기", "3분기", "4분기" } },
				{ "weeks.long",
						new String[] { "1주차", "2주차", "3주차", "4주차", "5주차" } },
				{ "weeks.short", new String[] { "1주", "2주", "3주", "4주", "5주" } },
				{ "days.long", new String[] { "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"}},
				{ "days.short", new String[] { "일", "월", "화", "수", "목", "금", "토" }},
				{ "halfdays.long", new String[] { "오전", "오후" }},
				{ "workhours.long", new String[] { "근무시간 이전", "근무시간", "근무시간 이후" } },
				{ "workhours.short", new String[] { "근무전", "근무", "근무후" } } };
	}
}
