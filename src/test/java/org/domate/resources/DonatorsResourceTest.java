package org.domate.resources;

import static org.junit.Assert.assertNotNull;

import javax.ws.rs.NotFoundException;

import org.domate.model.Donator;
import org.domate.persistence.DonatorDAO;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class DonatorsResourceTest {

	private static final String NOTEXISTING_DONATOR = "notexistingDonator";
	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();
	DonatorDAO dao = context.mock(DonatorDAO.class);
	DonatorsResource resource;

	@Before
	public void setUp() {
		resource = new DonatorsResource(dao);
	}

	@Test
	public void create() {
		Donator donator = aDonator();

		context.checking(new Expectations() {
			{
				oneOf(dao).insert(donator);
			}
		});

		resource.create(donator);
	}

	@Test
	public void find() throws Exception {
		String username = "donator1";
		
		context.checking(new Expectations() {
			{
				oneOf(dao).findById(username);
				will(returnValue(aDonator()));
			}
		});
		assertNotNull(resource.getDonator(username));
	}

	@Test(expected = NotFoundException.class)
	public void throwExceptionWhenDonatorNotFound() throws Exception {
		context.checking(new Expectations() {
			{
				oneOf(dao).findById(NOTEXISTING_DONATOR);
				will(returnValue(null));
			}
		});
		resource.getDonator(NOTEXISTING_DONATOR);
	}

	private Donator aDonator() {
		return new Donator();
	}

}
