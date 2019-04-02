package fr.uha.ensisa.gl.clubpiquette.mantest.dao.mem;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import fr.uha.ensisa.gl.clubpiquette.mantest.TestBook;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.TestBookDao;

public class TestBookDaoMem implements TestBookDao {
	private final Map<Long, TestBook> store = Collections.synchronizedMap(new TreeMap<Long, TestBook>());
	
	@Override
	public void persist(TestBook testBook) {
		this.store.put(testBook.getId(), testBook);
	}

	@Override
	public void remove(TestBook testBook) {
		this.store.remove(testBook.getId());
	}

	@Override
	public TestBook find(long id) {
		return this.store.get(id);
	}

	@Override
	public Collection<TestBook> findAll() {
		return this.store.values();
	}

	@Override
	public long count() {
		return this.store.size();
	}

}
