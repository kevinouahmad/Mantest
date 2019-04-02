package fr.uha.ensisa.gl.clubpiquette.mantest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestSuiteTest {
	fr.uha.ensisa.gl.clubpiquette.mantest.TestSuite sut;
	
	@Before
	public void createTestSuite() {
		sut = new fr.uha.ensisa.gl.clubpiquette.mantest.TestSuite();
	}
	
	@Test
	public void testConstructor() {
		assertEquals(new ArrayList<Test>(), sut.getTests());
		assertEquals(1, sut.getNature());
	}
	
	@Test
	public void testSetTests() {
		ArrayList<fr.uha.ensisa.gl.clubpiquette.mantest.Test> tests = new ArrayList<fr.uha.ensisa.gl.clubpiquette.mantest.Test>();
		tests.add(new SingleTest());
		tests.add(new SingleTest());
		tests.add(new SingleTest());
		sut.setTests(tests);
		assertEquals(tests, sut.getTests());
	}
	
	@Test
	public void testFindTest() {
		long id = 165320;
		ArrayList<fr.uha.ensisa.gl.clubpiquette.mantest.Test> tests = new ArrayList<fr.uha.ensisa.gl.clubpiquette.mantest.Test>();
		SingleTest t = new SingleTest();
		t.setId(id);
		tests.add(t);
		
		assertEquals(null, sut.findTest(id));
		sut.setTests(tests);
		assertEquals(t, sut.findTest(id));
	}

	@Test
	public void testAddRemoveTest() {
		SingleTest t = new SingleTest();
		long id = 865132;
		t.setId(id);
		
		assertEquals(null, sut.findTest(id));
		sut.addTest(t);
		assertEquals(t, sut.findTest(id));
		sut.removeTest(t);
		assertEquals(null, sut.findTest(id));
	}
	
	@Test
	public void testCount() {
		assertEquals(0, sut.count());
		sut.addTest(new SingleTest());
		assertEquals(1, sut.count());
	}
}
