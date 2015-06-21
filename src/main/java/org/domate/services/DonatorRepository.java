package org.domate.services;

import org.domate.model.Donator;
import org.domate.persistence.DonatorDAO;

public class DonatorRepository {
	
	private DonatorDAO donatorDAO;
	
	public DonatorRepository(DonatorDAO donatorDAO) {
		this.donatorDAO = donatorDAO;
	}
	
	public void insert(Donator donator) {
		donatorDAO.insert(donator);
	}

}
