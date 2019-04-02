package fr.uha.ensisa.gl.clubpiquette.mantest.dao.mem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import fr.uha.ensisa.gl.clubpiquette.mantest.TestBook;

class TestBookDaoMemTest {
	public final TestBookDaoMem sut = new TestBookDaoMem();
	
	@Test
	public void testPersistFindRemove() {
		assertNotEquals(null, sut);
		TestBook t = new TestBook();
		long id = 541;
		t.setId(id);
		sut.persist(t);
		assertEquals(t, sut.find(id));
		sut.remove(t);
		assertEquals(null, sut.find(id));
	}
	
	@Test
	public void testFindAll() {
		ArrayList<TestBook> tb = new ArrayList<TestBook>();
		tb.add(new TestBook());
		tb.add(new TestBook());
		long id1 = 513;
		long id2 = 451;
		tb.get(0).setId(id1);
		tb.get(1).setId(id2);
		
		sut.persist(tb.get(0));
		sut.persist(tb.get(1));
		
		Collection<TestBook> fa = sut.findAll();
		assertEquals(true, tb.containsAll(fa));
	}

	@Test
	public void testCount() {
		assertEquals(0, sut.count());
	}

}
