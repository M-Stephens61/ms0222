package Card_Fin_Demo.ms0222.entities;

/**
 * Simple Entity class that represents a tool available for rent.
 */
public class RenTool {

	private String toolCode;

	private ToolType toolType;

	private Brand toolBrand;

	public RenTool(String toolCode, ToolType toolType, Brand toolBrand) {
		this.toolCode = toolCode;
		this.toolType = toolType;
		this.toolBrand = toolBrand;
	}

	public String getToolCode() {
		return toolCode;
	}

	public ToolType getToolType() {
		return toolType;
	}

	public Brand getToolBrand() {
		return toolBrand;
	}

	public enum ToolType {

		CHAINSAW("Chainsaw", 1.49, true, false, true), 
		JACKHAMMER("Jackhammer", 2.99, true, false, false),
		LADDER("Ladder", 1.99, true, true, false);

		private String strValue;
		private double dailyCharge;
		private boolean weekdayCharge;
		private boolean weekendCharge;
		private boolean holidayCharge;

		ToolType(String strValue, double dailyCharge, boolean weekday, boolean weekend, boolean holiday) {
			this.strValue = strValue;
			this.dailyCharge = dailyCharge;
			this.weekdayCharge = weekday;
			this.weekendCharge = weekend;
			this.holidayCharge = holiday;
		}

		public String toString() {
			return this.strValue;
		}

		public double getDailyCharge() {
			return this.dailyCharge;
		}

		/**
		 * Returns true if this tool will charge the customer for weekdays during the
		 * rental period.
		 * 
		 * @return boolean
		 */
		public boolean chargesWeekdays() {
			return this.weekdayCharge;
		}

		/**
		 * Returns true if this tool will charge the customer for weekends during the
		 * rental period.
		 * 
		 * @return boolean
		 */
		public boolean chargesWeekends() {
			return this.weekendCharge;
		}

		/**
		 * Returns true if this tool will charge the customer for holidays during the
		 * rental period.
		 * 
		 * @return boolean
		 */
		public boolean chargesHolidays() {
			return this.holidayCharge;
		}
	}

	public enum Brand {
		DEWALT("DeWalt"), RIDGID("Ridgid"), STIHL("Stihl"), WERNER("Werner");

		private String strValue;

		Brand(String strValue) {
			this.strValue = strValue;
		}

		public String toString() {
			return this.strValue;
		}
	}
}
