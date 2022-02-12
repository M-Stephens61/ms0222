package Card_Fin_Demo.ms0222.util;

import java.util.Calendar;

public abstract class CalendarUtil {

	public static Calendar parseCalendarDate(String date) {

		String[] dateArr = date.split("/");

		Calendar calDate = Calendar.getInstance();

		calDate.set(Calendar.MONTH, Integer.parseInt(dateArr[0]) - 1); // month is zero indexed in Calendar
		calDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateArr[1]));
		calDate.set(Calendar.YEAR, (2000 + Integer.parseInt(dateArr[2])));

		zeroTime(calDate);

		return calDate;
	}

	public static void zeroTime(Calendar calDate) {
		calDate.set(Calendar.HOUR, 0);
		calDate.set(Calendar.HOUR_OF_DAY, 0);
		calDate.set(Calendar.MINUTE, 0);
		calDate.set(Calendar.SECOND, 0);
		calDate.set(Calendar.MILLISECOND, 0);
	}

	public static String formatDate(Calendar date) {

		String dateString = "";

		int month = date.get(Calendar.MONTH) + 1; // month is zero indexed
		int day = date.get(Calendar.DAY_OF_MONTH);
		int year = date.get(Calendar.YEAR);

		// add formatted month
		if (month < 10)
			dateString += "0" + month;
		else
			dateString += month;

		dateString += "/";

		// add formatted day
		if (day < 10)
			dateString += "0" + day;
		else
			dateString += day;

		dateString += "/";

		year = (year % 100);// returns the last 2 digits of the year
		// add formatted Year
		if (year < 10)
			dateString += "0" + year;
		else
			dateString += year;

		return dateString;
	}

	public static boolean datesMatch(Calendar date1, Calendar date2) {
		boolean match = true;

		if (date1.get(Calendar.YEAR) != date2.get(Calendar.YEAR))
			match = false;

		if (date1.get(Calendar.MONTH) != date2.get(Calendar.MONTH))
			match = false;

		if (date1.get(Calendar.DAY_OF_MONTH) != date2.get(Calendar.DAY_OF_MONTH))
			match = false;

		return match;
	}

}
