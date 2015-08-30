package org.domate.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.domate.model.Donator;
import org.domate.persistence.DonatorDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/donators")
@Consumes("application/json")
@Produces("application/json")
public class DonatorsResource {

	private final static Logger logger = LoggerFactory
			.getLogger(DonatorsResource.class);

	private final DonatorDAO donatorDao;

	@Autowired
	public DonatorsResource(DonatorDAO donatorDAO) {
		donatorDao = donatorDAO;
	}

	@POST
	public Response create(Donator donator) {
		logger.info("new donator...{}", donator.getUsername());
		addNewDonator(donator);
		return Response.ok().build();
	}

	@GET
	@Path("/{donator}")
	public Response getDonator(@PathParam("donator") String username) {
		logger.info("finding donator... {}", username);
		Donator donator = donatorDao.findById(username);
		if (null == donator)
			throw new NotFoundException();
		return Response.ok(donator).build();
	}

	private void addNewDonator(Donator donator) {
		donatorDao.insert(donator);
	}

}
