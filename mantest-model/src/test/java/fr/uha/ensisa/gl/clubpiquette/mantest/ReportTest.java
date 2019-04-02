package fr.uha.ensisa.gl.clubpiquette.mantest;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ReportTest {
	fr.uha.ensisa.gl.clubpiquette.mantest.Report sut;
	
	@Before
	public void createReport() {
		sut = new fr.uha.ensisa.gl.clubpiquette.mantest.Report();
	}
	
	@Test
	public void testReport() {
		assertEquals(fr.uha.ensisa.gl.clubpiquette.mantest.Report.DEFAULT_STEP, sut.getProblem());
		assertEquals(fr.uha.ensisa.gl.clubpiquette.mantest.Report.DEFAULT_OBSERVATION, sut.getObservation());
	}

	@Test
	public void testSetDate() {
		Date t = new Date();
		sut.setDate(t);
		assertEquals(t, sut.getDate());
	}

	@Test
	public void testSetProblem() {
		Step s = new Step();
		sut.setProblem(s);
		assertEquals(s, sut.getProblem());
	}

	@Test
	public void testSetObservation() {
		String o = "a report should have an observation";
		sut.setObservation(o);
		assertEquals(o, sut.getObservation());
	}

	@Test
	public void testSetId() {
		long id = 512;
		sut.setId(id);
		assertEquals(id, sut.getId());
		sut.setId(-45);
		assertEquals(id, sut.getId());
	}

	@Test
	public void testSetEnd() {
		long timestamp = (new Date()).getTime();
		sut.setEnd();
		long timestamp2 = (new Date()).getTime();
		assertEquals(true, (sut.getEnd() >= timestamp && sut.getEnd() <= timestamp2));
	}
	
	@Test
	public void testGetTimeOccured() {
		sut.setEnd();
		long end = sut.getEnd();
		long start = sut.getStart();
		assertEquals(end - start, sut.getTimeOccured());
	}
}
