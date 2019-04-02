package fr.uha.ensisa.gl.clubpiquette.mantest.dao.mem;

import fr.uha.ensisa.gl.clubpiquette.mantest.dao.DaoFactory;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.TestBookDao;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.UserDao;

public class DaoFactoryMem implements DaoFactory {
	public final TestBookDao testBookDao = new TestBookDaoMem();
	public final UserDao userDao = new UserDaoMem();
	
	@Override
	public TestBookDao getTestBookDao() {
		return this.testBookDao;
	}

	@Override
	public UserDao getUserDao() {
		// TODO Auto-generated method stub
		return this.userDao;
	}

}
