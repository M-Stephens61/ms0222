package Card_Fin_Demo.ms0222.util;

import java.util.Calendar;

public abstract class RentalDurationUtil {

	/**
	 * Calculates the return date given the start date and the number of days of
	 * active rental keeping in mind that certain tools
	 * 
	 * @param start
	 * @param duration
	 * @return
	 */
	public static Calendar calulateReturnDate(Calendar start, int duration) {
		Calendar returnDate = null;

		// catch bad parameters
		if (duration < 1 || start == null) {
			return null;
		}

		returnDate = Calendar.getInstance();
		returnDate.set(start.get(Calendar.YEAR), start.get(Calendar.MONTH), start.get(Calendar.DAY_OF_MONTH));
		returnDate.roll(Calendar.DAY_OF_MONTH, duration);

		CalendarUtil.zeroTime(returnDate);

		return returnDate;
	}

}
