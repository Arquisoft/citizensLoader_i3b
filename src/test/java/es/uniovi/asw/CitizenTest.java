package es.uniovi.asw;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.parser.Citizen;

public class CitizenTest {

	@Test
	public void testEquals() {
		Citizen dummy = new Citizen("a", "b", "a@a.com", "10/10/2010", "a",
				"a","123456789Z", "132456789", "1234");
		Citizen dummy1 = new Citizen("a", "b", "b@a.com", "10/10/2010", "a",
				"a", "123456789Z","132456789", "1234");
		Citizen dummy2 = new Citizen("a", "b", "b@a.com", "10/10/2010", "a",
				"a", "3","132456789", "1234");
		
		assertTrue(dummy.equals(dummy));
		assertTrue(dummy.equals(dummy1));
		assertFalse(dummy.equals(dummy2));
	}

}
