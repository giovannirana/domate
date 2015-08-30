package org.domate.persistence;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.UUID;

import org.domate.model.BloodGroup;
import org.domate.model.Donator;
import org.domate.model.PhoneNumber;
import org.domate.model.RhesusFactor;
import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class DonatorMongoDAOIT {

	@Test
	public void saveAndRetrieveADonator() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB db = mongoClient.getDB("domate");
		DonatorMongoDAO dao = new DonatorMongoDAO(db);
		Donator donator = createDonator();
		dao.insert(donator);
		Donator result = dao.findById(donator.getUsername());
		assertNotNull(result);
	}

	private Donator createDonator() {
		Donator ret =  new Donator();
		ret.setUsername(UUID.randomUUID().toString());
		ret.setBirthDate(new Date());
		ret.setBloodGroup(BloodGroup.ZERO);
		ret.setCity("Milan");
		ret.setEmailAddress("giovanni.rossi@pippo.com");
		ret.setName("Giovanni");
		ret.setPhoneNumber(createPhoneNumber());
		ret.setRh(RhesusFactor.POSITIVE);
		ret.setState("Italy");
		ret.setStreet("Via Luigi Angeloni");
		ret.setSurname("Rossi");
		return ret;
	}

	private PhoneNumber createPhoneNumber() {
		PhoneNumber ret = new PhoneNumber();
		ret.setInternationalPrefix("+39");
		ret.setNumber("3471234567");
		return ret;
	}

}
