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
import fr.uha.ensisa.gl.clubpiquette.mantest.TestBook;
import fr.uha.ensisa.gl.clubpiquette.mantest.User;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.DaoFactory;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.TestBookDao;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.UserDao;

public class ReportControllerTest {
	public ReportController sut;
	@Mock public DaoFactory daoFactory;
	@Mock public TestBookDao daoTask;
	@Mock public TestBook testBook;
	@Mock public UserDao daoUser;
	@Mock public User user;
	@Mock public SingleTest test;
	@Before
	public void setUpClass() throws Exception {
		MockitoAnnotations.initMocks(this); // creÌ�e les @Mock
		when(daoFactory.getTestBookDao()).thenReturn(this.daoTask);
		when(daoFactory.getUserDao()).thenReturn(this.daoUser);
		when(daoTask.find(1l)).thenReturn(testBook);
		when(testBook.find(1l)).thenReturn(test);
		when(daoUser.whoIsConnected()).thenReturn(user);
		sut = new ReportController();
		sut.daoFactory = this.daoFactory;
	}

	@Test
	public void testDisplayReports() throws IOException {
		ModelAndView ret = sut.displayReports(1l, 1l, 0);
		verify(test).clean();
		assertFalse(ret.getModel().isEmpty());
	}

}
