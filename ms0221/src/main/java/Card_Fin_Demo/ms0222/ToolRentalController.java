/**
 @author Matthew Stephens
 */
package Card_Fin_Demo.ms0222;

import Card_Fin_Demo.ms0222.controllers.RentalAgreementsRepoController;
import Card_Fin_Demo.ms0222.entities.RentalAgreement;
import Card_Fin_Demo.ms0222.util.CalendarUtil;
import Card_Fin_Demo.ms0222.util.RentalCostCalulator;
import Card_Fin_Demo.ms0222.validation.InvalidEntryException;

/**
 * This class will represent a REST API controller that can easily be modified
 * for use in an actual web-service.
 * 
 * @author matthew stephens
 *
 */
public class ToolRentalController {

	private RentalAgreementsRepoController rentalAgreementsRepoController;

	public ToolRentalController() {

		rentalAgreementsRepoController = new RentalAgreementsRepoController();
	}

	public RentalAgreement submitNewRentalAgreement(String toolCode, int numberOfDays, int disountPercent, String startDate) {
		try {

			RentalAgreement newAgreement = new RentalAgreement(toolCode, CalendarUtil.parseCalendarDate(startDate),
					numberOfDays, disountPercent);

			return submitNewRentalAgreement(newAgreement);

		} catch (InvalidEntryException iee) {
			System.out.println(iee.getMessage());
		}

		return null;
	}

	/**
	 * Stores the given RentalAgreement in the RentalAgreement repository. (A.K.A.
	 * checking out)
	 * 
	 * @param newAgreement
	 * @return
	 */
	public RentalAgreement submitNewRentalAgreement(RentalAgreement newAgreement) {

		return rentalAgreementsRepoController.addNewRentalAgreement(newAgreement);

	}

	/**
	 * Calculates how much the total for a rental would be given the required
	 * specifications.
	 * 
	 * @param possibleAgreement
	 * @return
	 */
	public double checkRentalTotal(RentalAgreement possibleAgreement) {
		double total = 0.0;

		RentalCostCalulator.calculateTotal(possibleAgreement);

		return total;
	}

}
