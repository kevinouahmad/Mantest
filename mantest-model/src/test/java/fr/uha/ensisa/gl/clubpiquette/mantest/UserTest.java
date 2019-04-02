package fr.uha.ensisa.gl.clubpiquette.mantest;

import static org.junit.Assert.*;

import java.security.NoSuchProviderException;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	fr.uha.ensisa.gl.clubpiquette.mantest.User sut;
	String uname = "username";
	String password = "password";
	String fname = "first name";
	String lname = "last name";

	@Before
	public void setUpBeforeClass() throws Exception {
		sut = new fr.uha.ensisa.gl.clubpiquette.mantest.User(1, this.uname, this.password, this.fname, this.lname);
	}

	@Test
	public void testUser() {
		assertEquals(1, sut.getId());
		assertEquals(this.uname, sut.getUsername());
		assertEquals(this.fname, sut.getfName());
		assertEquals(this.lname, sut.getlName());
	}

	@Test
	public void testSetUsername() {
		String nUname = "new username";
		sut.setUsername(nUname);
		assertEquals(nUname, sut.getUsername());
	}

	@Test
	public void testSetPassword() throws NoSuchProviderException {
		String newPassword = "new password";
		String falsePassword = "false pass";
		sut.setPassword(newPassword);
		assertEquals(true, sut.comparePassword(newPassword));
		assertEquals(false, sut.comparePassword(falsePassword));
	}

	@Test
	public void testSetfName() {
		String fname = "new fname";
		sut.setfName(fname);
		assertEquals(fname, sut.getfName());
	}

	@Test
	public void testSetlName() {
		String lname = "new lname";
		sut.setlName(lname);
		assertEquals(lname, sut.getlName());
	}

	@Test
	public void testSetId() {
		long nid = 516548;
		sut.setId(nid);
		assertEquals(nid, sut.getId());
	}
}
