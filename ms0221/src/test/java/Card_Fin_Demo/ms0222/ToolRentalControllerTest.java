package Card_Fin_Demo.ms0222;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Card_Fin_Demo.ms0222.entities.RentalAgreement;
import Card_Fin_Demo.ms0222.util.CalendarUtil;
import junit.framework.TestCase;

public class ToolRentalControllerTest extends TestCase {

	ToolRentalController rentalController;

	@BeforeClass
	public void setUp() {
		rentalController = new ToolRentalController();
	}

	@AfterClass
	public void tearDown() {
		rentalController = null;
	}

	@Test
	public void testInvalidDiscount() {

		String toolCode = "JAKR";
		String startDate = "9/3/15";
		int duration = 5;
		int discount = 101;

		// error printed to the console for this test but could easily be thrown and
		// caught here
		RentalAgreement agreement = rentalController.submitNewRentalAgreement(toolCode, duration, discount, startDate);

		assertNull(agreement);

	}

	@Test
	public void testIndpendenceDay2020() {

		String toolCode = "LADW";
		String startDate = "7/2/20"; // Thur
		int duration = 3;
		int discount = 10;

		RentalAgreement agreement = rentalController.submitNewRentalAgreement(toolCode, duration, discount, startDate);

		assertNotNull(agreement);

		System.out.println("---Agreement Info---\r\n");
		agreement.printAgreement();
		System.out.println("--------------------\r\n\r\n");

		assertEquals(agreement.getTool().getToolCode(), toolCode);
		assertEquals(agreement.getTool().getToolType().toString(), "Ladder");
		assertEquals(agreement.getTool().getToolBrand().toString(), "Werner");
		assertEquals(agreement.getNumberOfDays(), duration);
		assertEquals(CalendarUtil.formatDate(agreement.getStartDate()), "07/02/20");
		assertEquals(CalendarUtil.formatDate(agreement.getReturnDate()), "07/05/20");
		assertEquals(agreement.getTool().getToolType().getDailyCharge(), 1.99);
		assertEquals(agreement.getChargableDays(), 2); // charges weekends
		assertEquals(agreement.getSubTotal(), 3.98);
		assertEquals(agreement.getDiscountPercent(), discount);
		assertEquals(agreement.getDiscountAmount(), 0.4);
		assertEquals(agreement.getTotalCharge(), 3.58);

	}

	@Test
	public void testIndpendenceDay2015() {

		String toolCode = "CHNS";
		String startDate = "7/2/15"; // Thurs
		int duration = 5;
		int discount = 25;

		RentalAgreement agreement = rentalController.submitNewRentalAgreement(toolCode, duration, discount, startDate);

		assertNotNull(agreement);

		System.out.println("---Agreement Info---\r\n");
		agreement.printAgreement();
		System.out.println("--------------------\r\n\r\n");

		assertEquals(agreement.getTool().getToolCode(), toolCode);
		assertEquals(agreement.getTool().getToolType().toString(), "Chainsaw");
		assertEquals(agreement.getTool().getToolBrand().toString(), "Stihl");
		assertEquals(agreement.getNumberOfDays(), duration);
		assertEquals(CalendarUtil.formatDate(agreement.getStartDate()), "07/02/15");
		assertEquals(CalendarUtil.formatDate(agreement.getReturnDate()), "07/07/15");
		assertEquals(agreement.getTool().getToolType().getDailyCharge(), 1.49);
		assertEquals(agreement.getChargableDays(), 3); // charges holidays
		assertEquals(agreement.getSubTotal(), 4.47);
		assertEquals(agreement.getDiscountPercent(), discount);
		assertEquals(agreement.getDiscountAmount(), 1.12);
		assertEquals(agreement.getTotalCharge(), 3.35);
	}

	@Test
	public void testLaborDay() {

		String toolCode = "JAKD";
		String startDate = "9/3/15";
		int duration = 6;
		int discount = 0;

		RentalAgreement agreement = rentalController.submitNewRentalAgreement(toolCode, duration, discount, startDate);

		assertNotNull(agreement);

		System.out.println("---Agreement Info---\r\n");
		agreement.printAgreement();
		System.out.println("--------------------\r\n\r\n");

		assertEquals(agreement.getTool().getToolCode(), toolCode);
		assertEquals(agreement.getTool().getToolType().toString(), "Jackhammer");
		assertEquals(agreement.getTool().getToolBrand().toString(), "DeWalt");
		assertEquals(agreement.getNumberOfDays(), duration);
		assertEquals(CalendarUtil.formatDate(agreement.getStartDate()), "09/03/15");
		assertEquals(CalendarUtil.formatDate(agreement.getReturnDate()), "09/09/15");
		assertEquals(agreement.getTool().getToolType().getDailyCharge(), 2.99);
		assertEquals(agreement.getChargableDays(), 3); // only charges weekdays
		assertEquals(agreement.getSubTotal(), 8.97);
		assertEquals(agreement.getDiscountPercent(), discount);
		assertEquals(agreement.getDiscountAmount(), 0.0);
		assertEquals(agreement.getTotalCharge(), 8.97);
	}

	@Test
	public void testIndpendenceDayFor9Days() {
		String toolCode = "JAKR";
		String startDate = "7/2/15";
		int duration = 9;
		int discount = 0;

		RentalAgreement agreement = rentalController.submitNewRentalAgreement(toolCode, duration, discount, startDate);

		assertNotNull(agreement);

		System.out.println("---Agreement Info---\r\n");
		agreement.printAgreement();
		System.out.println("--------------------\r\n\r\n");

		assertEquals(agreement.getTool().getToolCode(), toolCode);
		assertEquals(agreement.getTool().getToolType().toString(), "Jackhammer");
		assertEquals(agreement.getTool().getToolBrand().toString(), "Ridgid");
		assertEquals(agreement.getNumberOfDays(), duration);
		assertEquals(CalendarUtil.formatDate(agreement.getStartDate()), "07/02/15");
		assertEquals(CalendarUtil.formatDate(agreement.getReturnDate()), "07/11/15");
		assertEquals(agreement.getTool().getToolType().getDailyCharge(), 2.99);
		assertEquals(agreement.getChargableDays(), 5); // only charges weekdays
		assertEquals(agreement.getSubTotal(), 14.95);
		assertEquals(agreement.getDiscountPercent(), discount);
		assertEquals(agreement.getDiscountAmount(), 0.0);
		assertEquals(agreement.getTotalCharge(), 14.95);
	}

	@Test
	public void testIndpendenceDayForHalfOff() {
		String toolCode = "JAKR";
		String startDate = "7/2/20";
		int duration = 4;
		int discount = 50;

		RentalAgreement agreement = rentalController.submitNewRentalAgreement(toolCode, duration, discount, startDate);

		assertNotNull(agreement);

		System.out.println("---Agreement Info---\r\n");
		agreement.printAgreement();
		System.out.println("--------------------\r\n\r\n");

		assertEquals(agreement.getTool().getToolCode(), toolCode);
		assertEquals(agreement.getTool().getToolType().toString(), "Jackhammer");
		assertEquals(agreement.getTool().getToolBrand().toString(), "Ridgid");
		assertEquals(agreement.getNumberOfDays(), duration);
		assertEquals(CalendarUtil.formatDate(agreement.getStartDate()), "07/02/20");
		assertEquals(CalendarUtil.formatDate(agreement.getReturnDate()), "07/06/20");
		assertEquals(agreement.getTool().getToolType().getDailyCharge(), 2.99);
		assertEquals(agreement.getChargableDays(), 1); // only charges weekdays
		assertEquals(agreement.getSubTotal(), 2.99);
		assertEquals(agreement.getDiscountPercent(), discount);
		assertEquals(agreement.getDiscountAmount(), 1.5);
		assertEquals(agreement.getTotalCharge(), 1.49);
	}

	@Test
	public void testMultipleAgreements() {

		String toolCode = "JAKR";
		String startDate = "9/3/15";
		int duration = 5;
		int discount = 10;

		RentalAgreement agreement = rentalController.submitNewRentalAgreement(toolCode, duration, discount, startDate);

		assertNotNull(agreement);

		toolCode = "JAKR";
		startDate = "7/2/20";
		duration = 4;
		discount = 50;

		agreement = rentalController.submitNewRentalAgreement(toolCode, duration, discount, startDate);

		assertNotNull(agreement);

		toolCode = "CHNS";
		startDate = "4/30/20";
		duration = 5;
		discount = 25;

		agreement = rentalController.submitNewRentalAgreement(toolCode, duration, discount, startDate);

		assertNotNull(agreement);
	}
}
