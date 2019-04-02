package fr.uha.ensisa.gl.clubpiquette.mantest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestBookTest {
	TestBook sut;
	
	@Before
	public void createTestBook() {
		sut = new TestBook();
	}

	@Test
	public void testConstructor() {
		assertEquals(TestBook.DEFAULT_ID, sut.getId());
		assertEquals(TestBook.DEFAULT_NAME, sut.getName());
		assertEquals(new ArrayList<fr.uha.ensisa.gl.clubpiquette.mantest.Test>(), sut.getTests());
	}
	
	@Test
	public void testSetId() {
		long id = 51;
		sut.setId(id);
		assertEquals(id, sut.getId());
		sut.setId(-5320);
		assertEquals(id, sut.getId());
	}
	
	@Test
	public void testSetName() {
		String name = "a test book should have a name";
		sut.setName(name);
		assertEquals(name, sut.getName());
	}

	@Test
	public void testSetTests() {
		ArrayList<fr.uha.ensisa.gl.clubpiquette.mantest.Test> tests = new ArrayList<fr.uha.ensisa.gl.clubpiquette.mantest.Test>();
		tests.add(new SingleTest());
		sut.setTests(tests);
		assertEquals(tests, sut.getTests());
	}
	
	@Test
	public void testFind() {
		ArrayList<fr.uha.ensisa.gl.clubpiquette.mantest.Test> tests = new ArrayList<fr.uha.ensisa.gl.clubpiquette.mantest.Test>();
		SingleTest t = new SingleTest();
		long id = 15;
		t.setId(id);
		tests.add(t);
		assertEquals(null, sut.find(id));
		sut.setTests(tests);
		assertEquals(t, sut.find(id));
	}
	
	@Test
	public void testAddRemoveTest() {
		SingleTest t = new SingleTest();
		long id = 15;
		t.setId(id);
		
		assertEquals(null, sut.find(id));
		sut.addTest(t);
		assertEquals(t, sut.find(id));
		sut.removeTest(t);
		assertEquals(null, sut.find(id));
	}
	
	@Test
	public void testCount() {
		assertEquals(0, sut.count());
		sut.addTest(new SingleTest());
		assertEquals(1, sut.count());
	}
}
