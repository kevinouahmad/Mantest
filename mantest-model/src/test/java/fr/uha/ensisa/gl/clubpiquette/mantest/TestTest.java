package fr.uha.ensisa.gl.clubpiquette.mantest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestTest {
	fr.uha.ensisa.gl.clubpiquette.mantest.Test sut;

	@Before
	public void createTest() {
		sut = (fr.uha.ensisa.gl.clubpiquette.mantest.Test)new fr.uha.ensisa.gl.clubpiquette.mantest.SingleTest();
	}
	
	@Test
	public void testTest() {
		assertEquals(fr.uha.ensisa.gl.clubpiquette.mantest.Test.DEFAULT_ID, sut.getId());
		assertEquals(fr.uha.ensisa.gl.clubpiquette.mantest.Test.DEFAULT_NAME, sut.getName());
		assertEquals(fr.uha.ensisa.gl.clubpiquette.mantest.Test.DEFAULT_NATURE, sut.getNature());
	}

	@Test
	public void testSetId() {
		long id1 = 5412;
		long id2 = -451;
		sut.setId(id1);
		assertEquals(id1, sut.getId());
		sut.setId(id2);
		assertEquals(id1, sut.getId());
	}

	@Test
	public void testSetName() {
		String name = "a test should have a name";
		sut.setName(name);
		assertEquals(name, sut.getName());
	}

	@Test
	public void testSetNature() {
		sut.setNature(0);
		assertEquals(0, sut.getNature());
		sut.setNature(1);
		assertEquals(1, sut.getNature());
	}
}
