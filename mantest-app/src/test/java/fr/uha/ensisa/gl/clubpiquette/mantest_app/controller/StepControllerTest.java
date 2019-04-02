package fr.uha.ensisa.gl.clubpiquette.mantest_app.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import fr.uha.ensisa.gl.clubpiquette.mantest.SingleTest;
import fr.uha.ensisa.gl.clubpiquette.mantest.Step;
import fr.uha.ensisa.gl.clubpiquette.mantest.TestBook;
import fr.uha.ensisa.gl.clubpiquette.mantest.User;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.DaoFactory;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.TestBookDao;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.UserDao;

public class StepControllerTest {
	@Mock public DaoFactory daoFactory;
	@Mock public TestBookDao daoTask;
	@Mock public User user;
	@Mock public UserDao daoUser;
	@Mock public SingleTest test;
	@Mock public TestBook testBook;
	@Mock public Step step;
	public StepController sut;

	@Before
	public void prepareDao() throws Exception {
		MockitoAnnotations.initMocks(this); // cr�e les @Mock
		when(daoFactory.getTestBookDao()).thenReturn(this.daoTask);
		when(daoFactory.getUserDao()).thenReturn(this.daoUser);
		when(daoUser.whoIsConnected()).thenReturn(user);
		when(daoTask.find(1l)).thenReturn(testBook);
		when(testBook.find(1l)).thenReturn(test);
		when(test.find(1l)).thenReturn(step);
		sut = new StepController(); // System Under Test
		sut.daoFactory = this.daoFactory;
	}
	@Test
	public void testStep() throws IOException {
		
		ModelAndView ret = sut.Step(1l, 1l, 0, 1l);
		assertFalse(ret.isEmpty());
	}

	@Test
	public void testEdit() throws IOException {
		when(step.getDescription()).thenReturn("");
		String ret = sut.edit(1l, 1l, 0, 1l, "description");
		verify(step).setDescription("description");
		assertNotNull(ret);
	}

}
