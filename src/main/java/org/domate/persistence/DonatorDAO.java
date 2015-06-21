package org.domate.persistence;

import java.util.UUID;

import org.domate.model.Donator;

public interface DonatorDAO {

	public void insert(Donator donator);

	public Donator findById(String id);

}
