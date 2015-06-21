package org.domate.persistence;

import java.util.UUID;

import org.domate.model.Donator;
import org.domate.persistence.DonatorDAO;
import org.mongojack.JacksonDBCollection;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class DonatorMongoDAO implements DonatorDAO {

	MongoClient mongoClient = new MongoClient("localhost", 27017);
	DB db = mongoClient.getDB("domate");

	@Override
	public void insert(Donator donator) {
		try {
			JacksonDBCollection<Donator, String> collection = JacksonDBCollection
					.wrap(getCollection(), Donator.class, String.class);
			collection.insert(donator);
		} catch (Throwable t) {
			throw new RuntimeException("ERROR INSERTING DONATOR: "
					+ donator.getUsername(), t);
		}
	}

	@Override
	public Donator findById(String id) {
		try {
			JacksonDBCollection<Donator, String> collection = JacksonDBCollection
					.wrap(getCollection(), Donator.class, String.class);
			return collection.findOneById(id);
		} catch (Throwable t) {
			throw new RuntimeException("ERROR RETIRIEVING DONATOR: "
					+ id.toString(), t);
		}
	}

	private DBCollection getCollection() {
		DBCollection donators = db.getCollection("donators");
		return donators;
	}

}
