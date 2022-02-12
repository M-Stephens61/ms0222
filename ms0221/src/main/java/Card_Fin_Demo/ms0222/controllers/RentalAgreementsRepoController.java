package Card_Fin_Demo.ms0222.controllers;

import Card_Fin_Demo.ms0222.entities.RentalAgreement;
import Card_Fin_Demo.ms0222.repositories.RentalAgreementsRepository;

public class RentalAgreementsRepoController {

	private RentalAgreementsRepository rentalAgreementsRepo;
	
	
	public RentalAgreementsRepoController() {
		this.rentalAgreementsRepo = new RentalAgreementsRepository();
	}
	
	public RentalAgreement addNewRentalAgreement(RentalAgreement newAgreement) {
		return rentalAgreementsRepo.addRentalAgreement(newAgreement);
	}
	
}
