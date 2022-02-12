package Card_Fin_Demo.ms0222.entities;

import java.util.Calendar;
import java.util.UUID;

import Card_Fin_Demo.ms0222.controllers.RenToolOptionsController;
import Card_Fin_Demo.ms0222.util.CalendarUtil;
import Card_Fin_Demo.ms0222.util.RentalCostCalulator;
import Card_Fin_Demo.ms0222.util.RentalDurationUtil;
import Card_Fin_Demo.ms0222.validation.InvalidEntryException;

public class RentalAgreement {

	/** Would be used for a database entry */
	private String uuid;

	private RenTool renTool;

	/** AKA Checkout date */
	private Calendar rentalStartDate;

	/** Set to -1 to act as a check when calculating the return date */
	private int rentalDuration = -1;

	/** AKA Due Date */
	private Calendar returnDate;

	/** Number of days that the customer is actually charged */
	private int chargableDays = -1;

	private int discountPercent = 0;

	private double discountAmount = -1.0;

	/** Amount due before discount is applied */
	private double subTotal = -1.0;

	/** Amount due after discount is applied */
	private double totalAmount = -1.0;

	public RentalAgreement(String toolCode, Calendar startDate, int rentalDuration, int discountPercent)
			throws InvalidEntryException {

		if (rentalDuration < 1) {
			throw new InvalidEntryException("Must rent tool for at least 1 day.");
		}

		if (discountPercent < 0 || discountPercent > 100) {
			throw new InvalidEntryException("Cannot be discounted for less than 0% or more than 100%");
		}

		this.renTool = RenToolOptionsController.getAvailableOptions().get(toolCode);
		this.rentalStartDate = startDate;
		this.rentalDuration = rentalDuration;
		this.discountPercent = discountPercent;

		this.returnDate = RentalDurationUtil.calulateReturnDate(startDate, rentalDuration);

		this.chargableDays = RentalCostCalulator.numOfChargableDays(this);
	}

	public String getUuid() {

		if (uuid == null) {
			this.uuid = UUID.randomUUID().toString();
		}

		return uuid;
	}

	public RenTool getTool() {
		return this.renTool;
	}

	public boolean setReturnDate(Calendar specificRetDate) {
		if (specificRetDate != null) {
			this.returnDate = specificRetDate;
			return true;
		}

		if (rentalDuration > 0) {
			this.returnDate = RentalDurationUtil.calulateReturnDate(this.rentalStartDate, rentalDuration);

			return (this.returnDate != null);
		}

		return false;
	}

	public Calendar getStartDate() {
		return this.rentalStartDate;
	}

	public Calendar getReturnDate() {

		if (this.returnDate == null) {
			setReturnDate(null);
		}

		return this.returnDate;
	}

	public int getDiscountPercent() {
		return this.discountPercent;
	}

	public double getDiscountAmount() {
		if (this.discountAmount < 0) {
			this.discountAmount = getSubTotal() * (getDiscountPercent() / 100.0);
			this.discountAmount = Math.round(this.discountAmount * 100) / 100.0;
		}

		return this.discountAmount;
	}

	public int getChargableDays() {
		if (this.chargableDays < 0) {
			chargableDays = RentalCostCalulator.numOfChargableDays(this);
		}

		return this.chargableDays;
	}

	public int getNumberOfDays() {
		return this.rentalDuration;
	}

	public double getSubTotal() {
		if (this.subTotal < 0) {
			this.subTotal = getChargableDays() * getTool().getToolType().getDailyCharge();
			this.subTotal = (Math.round(this.subTotal * 100.0) / 100.0); // rounds to the nearest hundredth
		}

		return this.subTotal;
	}

	public double getTotalCharge() {
		if (this.totalAmount < 0) {

			this.totalAmount = getSubTotal() - getDiscountAmount();
			this.totalAmount = Math.round(this.totalAmount * 100.0) / 100.0;
		}

		return this.totalAmount;
	}

	public String printAgreement() {
		StringBuilder sb = new StringBuilder();
		String newLine = System.getProperty("line.separator"); // System Independent

		sb.append("Tool Code: " + getTool().getToolCode() + newLine);
		sb.append("Tool Type: " + getTool().getToolType().toString() + newLine);
		sb.append("Tool Brand: " + getTool().getToolBrand().toString() + newLine);
		sb.append("Rental Days: " + getNumberOfDays() + newLine);
		sb.append("Checkout Date: " + CalendarUtil.formatDate(getStartDate()) + newLine);
		sb.append("Due Date: " + CalendarUtil.formatDate(getReturnDate()) + newLine);
		sb.append("Daily Rental Charge: $" + getTool().getToolType().getDailyCharge() + newLine);
		sb.append("Charge days: " + getChargableDays() + newLine);
		sb.append("Pre-Discount Charge: $" + getSubTotal() + newLine);
		sb.append("Discount Percent: " + getDiscountPercent() + "%" + newLine);
		sb.append("Discount Amount: $" + getDiscountAmount() + newLine);
		sb.append("Final Charge: $" + getTotalCharge() + newLine);

		System.out.println(sb.toString());
		return sb.toString();
	}

	
}
