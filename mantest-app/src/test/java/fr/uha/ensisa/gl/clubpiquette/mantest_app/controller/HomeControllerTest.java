package fr.uha.ensisa.gl.clubpiquette.mantest_app.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import fr.uha.ensisa.gl.clubpiquette.mantest.TestBook;
import fr.uha.ensisa.gl.clubpiquette.mantest.User;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.DaoFactory;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.TestBookDao;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.UserDao;

public class HomeControllerTest {
	@Mock public DaoFactory daoFactory;
	@Mock public TestBookDao daoTask;
	@Mock public TestBook testBook;
	@Mock public UserDao daoUser;
	@Mock public User user;
	public HomeController sut;
	@Before
	public void prepareDao() {
		MockitoAnnotations.initMocks(this); // creÌ�e les @Mock
		when(daoFactory.getTestBookDao()).thenReturn(this.daoTask);
		when(daoFactory.getUserDao()).thenReturn(this.daoUser);
		when(daoTask.find(1l)).thenReturn(testBook);
		when(daoUser.whoIsConnected()).thenReturn(user);
		sut = new HomeController(); // System Under Test
		sut.daoFactory = this.daoFactory;
	}

	@Test
	public void testHome() throws IOException {
		ModelAndView ret = sut.home();
		assertFalse(ret.getModel().isEmpty());
	}

	@Test
	public void testCreate() throws IOException {
		String ret = sut.create("testBook 1");
		verify(
				sut.daoFactory.getTestBookDao()).persist(any(TestBook.class));
		assertNotNull(ret);
	}



	@Test
	public void testRename() throws IOException {
		String ret = sut.rename(1l, "new name");
		verify(daoTask.find(1l)).setName("new name");
		assertNotNull(ret);
	}
	
	@Test
	public void testRemove() throws IOException {
		String ret = sut.remove(1l);
		verify(daoTask).remove(daoTask.find(1l));
		assertNotNull(ret);
	}
}
