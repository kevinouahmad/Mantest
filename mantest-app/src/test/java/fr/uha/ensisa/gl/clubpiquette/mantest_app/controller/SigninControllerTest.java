package fr.uha.ensisa.gl.clubpiquette.mantest_app.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.security.NoSuchProviderException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import fr.uha.ensisa.gl.clubpiquette.mantest.User;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.DaoFactory;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.TestBookDao;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.UserDao;

public class SigninControllerTest {
	
	@Mock public DaoFactory daoFactory;
	@Mock public TestBookDao daoTask;
	@Mock public User user;
	@Mock public UserDao daoUser;
	public SigninController sut;

	@Before
	public void prepareDao() throws Exception {
		MockitoAnnotations.initMocks(this); // cr�e les @Mock
		when(daoFactory.getTestBookDao()).thenReturn(this.daoTask);
		when(daoFactory.getUserDao()).thenReturn(this.daoUser);
		when(daoUser.whoIsConnected()).thenReturn(user);
		sut = new SigninController(); // System Under Test
		sut.daoFactory = this.daoFactory;
	}

	@Test
	public void testSignin() throws IOException {
		ModelAndView ret = sut.signin();
		assertFalse(ret.isEmpty());
	}

	@Test
	public void testSignup() throws IOException {
		ModelAndView ret = sut.signup();
		assertFalse(ret.isEmpty());
	}

	@Test
	public void testConnect() throws NoSuchProviderException, IOException {
		when(daoUser.connect("mail@mail.fr", "pass")).thenReturn(true);
		String ret = sut.connect("mail@mail.fr", "pass");
		assertNotNull(ret);
		String ret2 = sut.disconnect();
		assertNotNull(ret2);
		when(daoUser.connect("mail@mail.fr", "pass")).thenReturn(false);
		sut.connect("mail@mail.fr", "pass2");
	}


	@Test
	public void testCreate() throws IOException {
		String ret = sut.create("First", "Last", "first.last@mail.fr", "pass");
		assertNotNull(ret);
	}

}
