package fr.uha.ensisa.gl.clubpiquette.mantest_app.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.uha.ensisa.gl.clubpiquette.mantest.TestBook;
import fr.uha.ensisa.gl.clubpiquette.mantest.User;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.DaoFactory;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.TestBookDao;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.UserDao;

import org.springframework.web.servlet.ModelAndView;

import fr.uha.ensisa.gl.clubpiquette.mantest_app.controller.TestBookController;

public class TestBookControllerTest {
	@Mock public DaoFactory daoFactory;
	@Mock public TestBookDao daoTask;
	@Mock public TestBook testBook;
	@Mock public User user;
	@Mock public UserDao daoUser;
	@Mock public fr.uha.ensisa.gl.clubpiquette.mantest.SingleTest test;
	public TestBookController sut;
	@Before
	public void prepareDao() {
		MockitoAnnotations.initMocks(this); // cr�e les @Mock
		when(daoFactory.getTestBookDao()).thenReturn(this.daoTask);
		when(daoFactory.getUserDao()).thenReturn(this.daoUser);
		when(daoFactory.getTestBookDao().find(1l)).thenReturn(testBook);
		when(testBook.find(1l)).thenReturn(test);
		when(daoUser.whoIsConnected()).thenReturn(user);
		sut = new TestBookController(); // System Under Test
		sut.daoFactory = this.daoFactory;
	}
	@Test
	public void testCreate() throws IOException {
		sut.createTest(1l, 0, "testTest", 0);
		sut.createTest(1l, 0, "testTest", 1);
		//line 36
		
	}
	
	@Test
	public void testTestBook() throws IOException {
		ModelAndView ret = sut.TestBook(1l);
		assertFalse(ret.getModel().isEmpty());
	}

	@Test
	public void testRename() throws IOException {
		String ret = sut.rename(1l, 1l, 0, "new name");
		verify(daoFactory.getTestBookDao().find(1l).find(1l)).setName("new name");
		assertNotNull(ret);
	}
	
	@Test
	public void testRemove() throws IOException {
		String ret = sut.remove(1l, 1l, 0);
		verify(daoFactory.getTestBookDao().find(1l)).removeTest(test);
		assertNotNull(ret);
	}

}
