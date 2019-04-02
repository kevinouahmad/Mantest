package fr.uha.ensisa.gl.clubpiquette.mantest.dao.mem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import fr.uha.ensisa.gl.clubpiquette.mantest.User;

class UserDaoMemTest {
	public final UserDaoMem sut = new UserDaoMem();

	@Test
	void testPersistFindRemove() {
		long id = 5412;
		User u = new User(id, "uname", "password", "fname", "lname");
		sut.persist(u);
		assertEquals(u, sut.find(id));
		sut.remove(u);
		assertEquals(null, sut.find(id));
	}
	
	@Test
	void testFindAll() {
		ArrayList<User> u = new ArrayList<User>();
		u.add(new User(5, "", "", "", ""));
		u.add(new User(4, "", "", "", ""));
		long id1 = 513;
		long id2 = 451;
		u.get(0).setId(id1);
		u.get(1).setId(id2);
		
		sut.persist(u.get(0));
		sut.persist(u.get(1));
		
		Collection<User> fa = sut.findAll();
		assertEquals(true, u.containsAll(fa));
	}

	@Test
	void testCount() {
		assertEquals(0, sut.count());
		sut.persist(new User(4, "", "", "", ""));
		assertEquals(1, sut.count());
	}
	
	@Test
	void testConnectIsAnyConnectedWhoIsConnectedDisconnected() {
		long id = 851;
		String uname = "username";
		String fname = "first name";
		String lname = "last name";
		String pass = "password";
		User u = new User(id, uname, pass, fname, lname);
		sut.persist(u);
		try {
			assertEquals(false, sut.connect(uname, "wrongpass"));
			assertEquals(true, sut.connect(uname, pass));
			assertEquals(true, sut.isAnyConnected());
			assertEquals(u, sut.whoIsConnected());
			sut.disconnect();
			assertEquals(false, sut.isAnyConnected());
			assertEquals(null, sut.whoIsConnected());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
