package org.domate.rest;
import static spark.Spark.get;
import static spark.Spark.post;

import org.domate.model.Donator;
import org.domate.persistence.DonatorMongoDAO;
import org.domate.services.DonatorRepository;

import com.google.gson.Gson;

public class DomateRESTServer {
	
	
	public static void main(String[] args) {
		get("/hello", (req, res) -> "Hello World");
		
		// matches POST /donator
		post("donator", (request, response) -> {
			Donator donator = new Gson().fromJson(request.body(), Donator.class);
			DonatorRepository repository = new DonatorRepository(new DonatorMongoDAO());
			repository.insert(donator);
			return null;
		});

	}

}
