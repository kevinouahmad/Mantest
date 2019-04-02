/**
 * 
 */
package fr.uha.ensisa.gl.clubpiquette.mantest_app.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import fr.uha.ensisa.gl.clubpiquette.mantest.Step;
import fr.uha.ensisa.gl.clubpiquette.mantest.TestBook;
import fr.uha.ensisa.gl.clubpiquette.mantest.User;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.DaoFactory;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.TestBookDao;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.UserDao;

/**
 * @author ambroiserenaud
 *
 */
public class TestControllerTest {
	
	@Mock public DaoFactory daoFactory;
	@Mock public TestBookDao daoTask;
	@Mock public TestBook testBook;
	@Mock public User user;
	@Mock public UserDao daoUser;
	@Mock public fr.uha.ensisa.gl.clubpiquette.mantest.SingleTest test;
	@Mock public ArrayList<Step> stepList;
	public SingleTestController sut;

	@Before
	public void prepareDao() throws Exception {
		MockitoAnnotations.initMocks(this); // cr�e les @Mock
		when(daoFactory.getTestBookDao()).thenReturn(this.daoTask);
		when(daoFactory.getUserDao()).thenReturn(this.daoUser);
		when(daoTask.find(1l)).thenReturn(testBook);
		when(testBook.find(1l)).thenReturn(test);
		when(test.getName()).thenReturn("name");
		when(test.getId()).thenReturn(1l);
		when(test.getSteps()).thenReturn(stepList);
		when(test.find(1l)).thenReturn(new Step());
		when(daoUser.whoIsConnected()).thenReturn(user);
		sut = new SingleTestController(); // System Under Test
		sut.daoFactory = this.daoFactory;
	}

	/**
	 * Test method for {@link fr.uha.ensisa.gl.clubpiquette.mantest_app.controller.TestController#Test(long, long)}.
	 * @throws IOException 
	 */
	@Test
	public void testTest() throws IOException {
		ModelAndView ret = sut.Test(1l, 1l, 0);
		verify(daoFactory.getTestBookDao()).find(1l);
		assertFalse(ret.isEmpty());
	}

	/**
	 * Test method for {@link fr.uha.ensisa.gl.clubpiquette.mantest_app.controller.TestController#create(long, long, java.lang.String)}.
	 * @throws IOException 
	 */
	@Test
	public void testCreate() throws IOException {
		String ret = sut.create(1l, 1l, 0, "name");
		//never reach line 41 : "while (current.find(id) != null) id++;"
		assertNotNull(ret);
	}

	/**
	 * Test method for {@link fr.uha.ensisa.gl.clubpiquette.mantest_app.controller.TestController#remove(long, long, long)}.
	 * @throws IOException 
	 */
	@Test
	public void testRemove() throws IOException {
		String ret = sut.remove(1l, 1l, 0, 1l);
		assertNotNull(ret);
	}

	/**
	 * Test method for {@link fr.uha.ensisa.gl.clubpiquette.mantest_app.controller.TestController#rename(long, long, long, java.lang.String)}.
	 * @throws IOException 
	 */
	@Test
	public void testRename() throws IOException {
		String ret = sut.rename(1l, 1l, 0, 1l, "new name");
		assertEquals("new name", test.find(1l).getName());
		assertNotNull(ret);
	}
}
