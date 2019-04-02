package fr.uha.ensisa.gl.clubpiquette.mantest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SingleTestTest {
	fr.uha.ensisa.gl.clubpiquette.mantest.SingleTest sut;
	
	@Before
	public void createSingleTest() {
		sut = new fr.uha.ensisa.gl.clubpiquette.mantest.SingleTest();
	}
	
	@Test
	public void test() {
		assertEquals(new ArrayList<Step>(), sut.getSteps());
		assertEquals(new ArrayList<Report>(), sut.getReports());
		assertEquals(0, sut.getNature());
	}

	@Test
	public void testSetSteps() {
		ArrayList<Step> steps = new ArrayList<Step>();
		steps.add(new Step());
		steps.add(new Step());
		steps.add(new Step());
		sut.setSteps(steps);
		assertEquals(steps, sut.getSteps());
	}
	
	@Test
	public void testSetReports() {
		ArrayList<Report> reports = new ArrayList<Report>();
		reports.add(new Report());
		reports.add(new Report());
		reports.add(new Report());
		sut.setReports(reports);
		assertEquals(reports, sut.getReports());
	}
	
	@Test
	public void testGetReport() {
		ArrayList<Report> reports = new ArrayList<Report>();
		Report r = new Report();
		long id1 = 154;
		long id2 = 664513;
		r.setId(id1);
		reports.add(r);
		sut.setReports(reports);
		assertEquals(r, sut.getReport(id1));
		assertEquals(null, sut.getReport(id2));
	}
	
	@Test
	public void testAddRemoveReport() {
		ArrayList<Report> reports = new ArrayList<Report>();
		Report r = new Report();
		long id = 548;
		r.setId(id);
		sut.setReports(reports);
		
		assertEquals(null, sut.getReport(id));
		
		sut.addReport(r);
		assertEquals(r, sut.getReport(id));
		
		sut.removeReport(r);
		assertEquals(null, sut.getReport(id));
	}
	
	@Test
	public void testAddRemoveStep() {
		ArrayList<Step> steps = new ArrayList<Step>();
		
		Step s = new Step();
		long id = 548;
		s.setId(id);
		
		sut.setSteps(steps);
		
		assertEquals(steps, sut.getSteps());
		
		steps.add(s);
		sut.addStep(s);
		assertEquals(steps, sut.getSteps());
		
		steps.remove(s);
		sut.removeStep(s);
		assertEquals(steps, sut.getSteps());
	}
	
	@Test
	public void testCount() {
		assertEquals(0, sut.count());
		sut.addStep(new Step());
		assertEquals(1, sut.count());
	}
	
	@Test
	public void testCountReports() {
		assertEquals(0, sut.countReports());
		sut.addReport(new Report());
		assertEquals(1, sut.countReports());
	}
	
	@Test
	public void testFind() {
		Step s = new Step();
		long id = 513;
		s.setId(id);
		assertEquals(null, sut.find(id));
		sut.addStep(s);
		assertEquals(s, sut.find(id));
	}
	
	@Test
	public void testClean() {
		Report r = new Report();
		long id = 463;
		r.setId(id);
		sut.addReport(r);
		assertEquals(r, sut.getReport(id));
		sut.clean();
		assertEquals(null, sut.getReport(id));
	}
	
	@Test
	public void testProgress() {
		assertEquals(0, sut.progress());
		sut.addReport(new Report());
		assertEquals(1, sut.progress());
		Report r = new Report();
		r.setProblem(new Step());
		sut.addReport(r);
		assertEquals(-1, sut.progress());
		sut.addReport(new Report());
		assertEquals(0, sut.progress());
	}
}
