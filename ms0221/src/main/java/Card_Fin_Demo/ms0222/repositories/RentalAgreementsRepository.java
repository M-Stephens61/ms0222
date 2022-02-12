package Card_Fin_Demo.ms0222.repositories;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import Card_Fin_Demo.ms0222.entities.RentalAgreement;

public class RentalAgreementsRepository {

	private Map<String, RentalAgreement> activeRentalAgreements;

	private Map<String, RentalAgreement> pastRentalAgreements;

	private Calendar lastRefreshed;

	public RentalAgreement addRentalAgreement(RentalAgreement newAgreement) {
		try {

			Map<String, RentalAgreement> activeRentals = getActiveRentalAgreements();

			activeRentals.put(newAgreement.getUuid(), newAgreement);

			return newAgreement;

		} catch (Exception e) {

			System.out.println("Failed to add new agreement to repository");

			return null;
		}
	}

	/**
	 * Singleton Pattern for the Map of rental Agreements
	 * 
	 * @return
	 */
	private Map<String, RentalAgreement> getActiveRentalAgreements() {

		refreshActiveRentals();

		return activeRentalAgreements;
	}

	/**
	 * This method will check the rentalAgreements and ensure that there are ONLY
	 * active rentals in the activeRentalAgreements Map.
	 */
	private void refreshActiveRentals() {
		Calendar todaysDate = Calendar.getInstance();

		if (activeRentalAgreements == null) {
			activeRentalAgreements = new HashMap<>();
		}

		if (pastRentalAgreements == null) {
			pastRentalAgreements = new HashMap<>();
		}

		// if we've already refreshed today no need to redo it over and over
		if (lastRefreshed != null && lastRefreshed.compareTo(todaysDate) == 0
				|| this.activeRentalAgreements.isEmpty()) {
			return;
		}

		Map<String, RentalAgreement> activeAgreementsCopy = new HashMap<>(activeRentalAgreements);

		// This lengthy lambda just removes any expired Agreements (Does not account for
		// late rentals!)
		activeRentalAgreements = activeAgreementsCopy.entrySet().stream()
				.filter(entry -> entry.getValue().getReturnDate().after(todaysDate))
				.collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

		// store the old ones in the pastRentals Map
		Map<String, RentalAgreement> expiredAgreements = activeAgreementsCopy.entrySet().stream()
				.filter(entry -> !activeRentalAgreements.containsKey(entry.getKey()))
				.collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

		pastRentalAgreements.putAll(expiredAgreements);

		lastRefreshed = todaysDate;

	}
}
