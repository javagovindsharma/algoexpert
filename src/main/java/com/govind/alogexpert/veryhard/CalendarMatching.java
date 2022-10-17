package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.jcp.xml.dsig.internal.dom.Utils;

public class CalendarMatching {
	public static List<StringMeeting> calendarMatching(List<StringMeeting> calendar1, StringMeeting dailyBounds1,
			List<StringMeeting> calendar2, StringMeeting dailyBounds2, int meetingDuration) {

		List<Meeting> updatedCalendar1 = updateCalendar(calendar1, dailyBounds1);
		List<Meeting> updatedCalendar2 = updateCalendar(calendar2, dailyBounds2);
		List<Meeting> mergedCalendar = mergeCalendar(updatedCalendar1, updatedCalendar2);
		List<Meeting> flatternedCalendar = flatternCalendar(mergedCalendar);
		return getMatchingAvailabilities(flatternedCalendar, meetingDuration);
	}

	private static List<Meeting> updateCalendar(List<StringMeeting> calendar, StringMeeting dailyBounds) {
		List<StringMeeting> updatedCalendar = new ArrayList<StringMeeting>();
		updatedCalendar.add(new StringMeeting("0:00", dailyBounds.start));
		updatedCalendar.addAll(calendar);
		updatedCalendar.add(new StringMeeting(dailyBounds.end, "23:59"));
		List<Meeting> calendarInMinutes = new ArrayList<Meeting>();
		for (int i = 0; i < updatedCalendar.size(); i++) {
			calendarInMinutes.add(new Meeting(timeToMinutes(updatedCalendar.get(i).start),
					timeToMinutes(updatedCalendar.get(i).end)));
		}

		return calendarInMinutes;
	}

	private static List<Meeting> mergeCalendar(List<Meeting> calendar1, List<Meeting> calendar2) {
		List<Meeting> merged = new ArrayList<Meeting>();
		int i = 0;
		int j = 0;
		while (i < calendar1.size() && j < calendar2.size()) {
			Meeting meeting1 = calendar1.get(i);
			Meeting meeting2 = calendar2.get(j);

			if (meeting1.start < meeting2.start) {
				merged.add(meeting1);
				i++;
			} else {
				merged.add(meeting2);
				j++;
			}
		}
		while (i < calendar1.size())
			merged.add(calendar1.get(i++));
		while (j < calendar2.size())
			merged.add(calendar2.get(j++));
		return merged;
	}

	private static List<Meeting> flatternCalendar(List<Meeting> calendar) {
		List<Meeting> flattened = new ArrayList<Meeting>();
		flattened.add(calendar.get(0));

		for (int i = 1; i < calendar.size(); i++) {
			Meeting currentMeeting = calendar.get(i);
			Meeting previousMeeting = flattened.get(flattened.size() - 1);

			if (previousMeeting.end >= currentMeeting.start) {
				Meeting newPreviousMeeting = new Meeting(previousMeeting.start,
						Math.max(previousMeeting.end, currentMeeting.end));
				flattened.set(flattened.size() - 1, newPreviousMeeting);
			} else {
				flattened.add(currentMeeting);
			}
		}
		return flattened;
	}

	private static List<StringMeeting> getMatchingAvailabilities(List<Meeting> calendar, int meetingDuration) {
		List<Meeting> matchingAvailabilities = new ArrayList<Meeting>();
		for (int i = 1; i < calendar.size(); i++) {
			int start = calendar.get(i - 1).end;
			int end = calendar.get(i).start;
			int availabilitiesDuration = end - start;
			if (availabilitiesDuration >= meetingDuration) {
				matchingAvailabilities.add(new Meeting(start, end));
			}
		}

		List<StringMeeting> matchingAvailabilitiesInHours = new ArrayList<StringMeeting>();
		for (int i = 0; i < matchingAvailabilities.size(); i++) {
			matchingAvailabilitiesInHours.add(new StringMeeting(minutesToTime(matchingAvailabilities.get(i).start),
					minutesToTime(matchingAvailabilities.get(i).end)));
		}
		return matchingAvailabilitiesInHours;
	}

	private static String minutesToTime(int minutes) {
		int hours = minutes / 60;
		int mins = minutes % 60;
		String hoursString = Integer.toString(hours);
		String minutesString = mins < 10 ? "0" + Integer.toString(mins) : Integer.toString(mins);
		return hoursString + ":" + minutesString;
	}

	private static int timeToMinutes(String time) {
		int delimiterPos = time.indexOf(":");
		int hour = Integer.parseInt(time.substring(0, delimiterPos));
		int minutes = Integer.parseInt(time.substring(delimiterPos + 1, time.length()));
		return hour * 60 + minutes;
	}

	static class StringMeeting {
		public String start;
		public String end;

		public StringMeeting(String start, String end) {
			this.start = start;
			this.end = end;
		}
	}

	static class Meeting {
		public int start;
		public int end;

		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static boolean arraysEqual(List<CalendarMatching.StringMeeting> arr1, List<CalendarMatching.StringMeeting> arr2) {
		if (arr1.size() != arr2.size())
			return false;

		for (int i = 0; i < arr1.size(); i++) {
			if (!arr1.get(i).start.equals(arr2.get(i).start) || !arr1.get(i).end.equals(arr2.get(i).end)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		List<CalendarMatching.StringMeeting> calendar1 = new ArrayList<CalendarMatching.StringMeeting>();
		calendar1.add(new CalendarMatching.StringMeeting("9:00", "10:30"));
		calendar1.add(new CalendarMatching.StringMeeting("12:00", "13:00"));
		calendar1.add(new CalendarMatching.StringMeeting("16:00", "18:00"));

		CalendarMatching.StringMeeting dailyBounds1 = new CalendarMatching.StringMeeting("9:00", "20:00");

		List<CalendarMatching.StringMeeting> calendar2 = new ArrayList<CalendarMatching.StringMeeting>();
		calendar2.add(new CalendarMatching.StringMeeting("10:00", "11:30"));
		calendar2.add(new CalendarMatching.StringMeeting("12:30", "14:30"));
		calendar2.add(new CalendarMatching.StringMeeting("14:30", "15:00"));
		calendar2.add(new CalendarMatching.StringMeeting("16:00", "17:00"));

		CalendarMatching.StringMeeting dailyBounds2 = new CalendarMatching.StringMeeting("10:00", "18:30");

		int meetingDuration = 30;

		List<CalendarMatching.StringMeeting> expected = new ArrayList<CalendarMatching.StringMeeting>();
		expected.add(new CalendarMatching.StringMeeting("11:30", "12:00"));
		expected.add(new CalendarMatching.StringMeeting("15:00", "16:00"));
		expected.add(new CalendarMatching.StringMeeting("18:00", "18:30"));

		List<CalendarMatching.StringMeeting> actual = CalendarMatching.calendarMatching(calendar1, dailyBounds1, calendar2, dailyBounds2,
				meetingDuration);
		assertTrue(arraysEqual(expected, actual));
	}
}
