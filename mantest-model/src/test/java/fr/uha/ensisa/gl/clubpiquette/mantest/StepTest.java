package fr.uha.ensisa.gl.clubpiquette.mantest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StepTest {
	fr.uha.ensisa.gl.clubpiquette.mantest.Step sut;
	
	@Before
	public void createStep() {
		sut = new fr.uha.ensisa.gl.clubpiquette.mantest.Step();
	}
	
	@Test
	public void testStep() {
		assertEquals(fr.uha.ensisa.gl.clubpiquette.mantest.Step.DEFAULT_ID, sut.getId());	
		assertEquals(fr.uha.ensisa.gl.clubpiquette.mantest.Step.DEFAULT_NAME, sut.getName());	
		assertEquals(fr.uha.ensisa.gl.clubpiquette.mantest.Step.DEFAULT_DESCRIPTION, sut.getDescription());	
	}
	
	@Test
	public void testSetId() {
		long id = 1;
		sut.setId(id);
		assertEquals(id, sut.getId());
		sut.setId(-84512);
		assertEquals(id, sut.getId());
	}
	
	@Test
	public void testSetName() {
		String name = "a step should have a name";
		sut.setName(name);
		assertEquals(name, sut.getName());
	}

	@Test
	public void testSetDescription() {
		String desc = "a step should have a description";
		sut.setDescription(desc);
		assertEquals(desc, sut.getDescription());
	}
}
