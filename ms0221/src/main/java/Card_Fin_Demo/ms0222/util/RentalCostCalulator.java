package Card_Fin_Demo.ms0222.util;

import java.util.Calendar;

import Card_Fin_Demo.ms0222.entities.RentalAgreement;
import Card_Fin_Demo.ms0222.entities.RenTool.ToolType;

public abstract class RentalCostCalulator {

	private static Calendar nextLaborDay;

	/** Hold the nearest Weekday to July 4th that hasn't yet passed */
	private static Calendar independenceDay;

	public static double calculateTotal(RentalAgreement rentalAgreement) {

		int chargeableDays = numOfChargableDays(rentalAgreement);

		double subTotal = rentalAgreement.getTool().getToolType().getDailyCharge() * chargeableDays;

		subTotal *= rentalAgreement.getDiscountPercent();

		return subTotal;
	}

	/**
	 * Iterates the start day by 1 until it matches the end day, adding 1 to the
	 * chargeable days for each chargeable day
	 * 
	 * @param rentalAgreement
	 * @return
	 */
	public static int numOfChargableDays(RentalAgreement rentalAgreement) {
		int chargableDays = 0;

		Calendar start = rentalAgreement.getStartDate();

		findLaborDay(start);// load the next labor day holiday
		findIndependenceDay(start); // load the holiday for independence day

		Calendar end = rentalAgreement.getReturnDate();

		Calendar dateCheck = Calendar.getInstance();
		dateCheck.set(start.get(Calendar.YEAR), start.get(Calendar.MONTH), start.get(Calendar.DAY_OF_MONTH));
		dateCheck.roll(Calendar.DAY_OF_MONTH, true); // roll up one day to account for starting the day after checkout
		CalendarUtil.zeroTime(dateCheck);

		ToolType toolType = rentalAgreement.getTool().getToolType();

		while (dateCheck.compareTo(end) < 1) {

			if (CalendarUtil.datesMatch(dateCheck, nextLaborDay)
					|| CalendarUtil.datesMatch(dateCheck, independenceDay)) {

				if (toolType.chargesHolidays())
					chargableDays++;

			} else if ((dateCheck.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
					&& dateCheck.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) && toolType.chargesWeekdays()) {

				chargableDays++;

			} else if ((dateCheck.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
					|| dateCheck.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) && toolType.chargesWeekends()) {

				chargableDays++;

			}

			dateCheck.roll(Calendar.DAY_OF_MONTH, true);
		}

		return chargableDays;
	}

	private static void findLaborDay(Calendar start) {

		int nextLaborDayYear = start.get(Calendar.YEAR);
		int startingDay = start.get(Calendar.DAY_OF_MONTH);

		if (start.get(Calendar.MONTH) > Calendar.SEPTEMBER) {
			nextLaborDayYear++; // look at next year
			startingDay = 1;
		} else if (start.get(Calendar.MONTH) == Calendar.SEPTEMBER && start.get(Calendar.DAY_OF_MONTH) > 7) {
			nextLaborDayYear++;// look at next year
			startingDay = 1;
		} else if (start.get(Calendar.MONTH) < Calendar.SEPTEMBER) {
			startingDay = 1; // look at this year
		}

		Calendar september = Calendar.getInstance();

		september.set(nextLaborDayYear, Calendar.SEPTEMBER, startingDay);

		while (september.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {

			if (september.get(Calendar.DAY_OF_MONTH) > 7) {
				september.roll(Calendar.YEAR, true); // check next year
				september.set(Calendar.DAY_OF_MONTH, 1); // start back at one
				continue;
			}

			september.roll(Calendar.DAY_OF_MONTH, true);
		}

		nextLaborDay = september;

		CalendarUtil.zeroTime(nextLaborDay);

	}

	private static void findIndependenceDay(Calendar start) {

		int nextIndependDayYear = start.get(Calendar.YEAR);

		independenceDay = Calendar.getInstance();

		if (start.get(Calendar.MONTH) > Calendar.JULY)
			nextIndependDayYear++;
		else if (start.get(Calendar.MONTH) == Calendar.JULY && start.get(Calendar.DAY_OF_MONTH) > 4) {
			nextIndependDayYear++;
		}

		Calendar independDayActual = Calendar.getInstance();
		independDayActual.set(nextIndependDayYear, Calendar.JULY, 4);

		if (independDayActual.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			independenceDay.set(nextIndependDayYear, Calendar.JULY, 3);
		} else if (independDayActual.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			independenceDay.set(nextIndependDayYear, Calendar.JULY, 5);
		} else {
			independenceDay = independDayActual;
		}

		CalendarUtil.zeroTime(independenceDay);

	}
}
