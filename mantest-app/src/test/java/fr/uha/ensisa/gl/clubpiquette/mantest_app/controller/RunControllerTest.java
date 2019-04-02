package fr.uha.ensisa.gl.clubpiquette.mantest_app.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import fr.uha.ensisa.gl.clubpiquette.mantest.Report;
import fr.uha.ensisa.gl.clubpiquette.mantest.SingleTest;
import fr.uha.ensisa.gl.clubpiquette.mantest.Step;
import fr.uha.ensisa.gl.clubpiquette.mantest.TestBook;
import fr.uha.ensisa.gl.clubpiquette.mantest.User;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.DaoFactory;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.TestBookDao;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.UserDao;

import java.util.ArrayList;

public class RunControllerTest {

	public RunController sut;
	@Mock public DaoFactory daoFactory;
	@Mock public TestBookDao daoTask;
	@Mock public UserDao daoUser;
	@Mock public TestBook testBook;
	@Mock public Step step;
	@Mock public ArrayList<Step> stepList;
	@Mock public Report report;
	@Mock public fr.uha.ensisa.gl.clubpiquette.mantest.SingleTest test;
	@Mock public User user;
	@Before
	public void setUpClass() throws Exception {
		MockitoAnnotations.initMocks(this); // cr�e les @Mock
		when(daoFactory.getTestBookDao()).thenReturn(this.daoTask);
		when(daoFactory.getUserDao()).thenReturn(this.daoUser);
		when(daoTask.find(1l)).thenReturn(testBook);
		when(testBook.find(1l)).thenReturn(test);
		when(test.getSteps()).thenReturn(stepList);
		when(stepList.get(1)).thenReturn(step);
		when(test.getReport(1l)).thenReturn(report);
		when(daoUser.whoIsConnected()).thenReturn(user);
		sut = new RunController();
		sut.daoFactory = this.daoFactory;
	}

	@Test
	public void testExecution() throws IOException {
		ModelAndView ret = sut.execution(1l, 1L, 0L, 1, 1l);
		assertFalse(ret.isEmpty());
		sut.execution(1l, 1L, 0, 0, 1l);
		sut.execution(1l, 1L, 0, (int)test.count()-1, 1l);
	}

	@Test
	public void testFinished() throws IOException {
		String ret = sut.finished(1l, 1l, 0,  1l, "fname", "lname");
		verify(report).setEnd();
		verify(report).setFname("fname");
		verify(report).setLname("lname");
		verify(report).setObservation("Everything went well");
		assertNotNull(ret);
	}

	@Test
	public void testReportFilled() throws IOException {
		String ret = sut.reportFilled("fname", "lname", 1l, 1l, 0, 1l, 1, "problem");
		verify(report).setEnd();
		verify(report).setProblem(((SingleTest) daoTask.find(1l).find(1l)).find(1l));
		verify(report).setObservation("problem");
		verify(report).setFname("fname");
		verify(report).setLname("lname");
		assertNotNull(ret);
	}

}
