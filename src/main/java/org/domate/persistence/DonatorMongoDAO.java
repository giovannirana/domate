package org.domate.persistence;

import org.domate.model.Donator;
import org.mongojack.JacksonDBCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.DB;
import com.mongodb.DBCollection;

@Repository
public class DonatorMongoDAO implements DonatorDAO {

	private DB db;

	@Autowired     
	public DonatorMongoDAO(DB db) {
		this.db = db;
	}

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
