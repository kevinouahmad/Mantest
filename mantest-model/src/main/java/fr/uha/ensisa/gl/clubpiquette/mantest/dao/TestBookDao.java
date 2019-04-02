package fr.uha.ensisa.gl.clubpiquette.mantest.dao;

import java.util.Collection;

import fr.uha.ensisa.gl.clubpiquette.mantest.TestBook;

public interface TestBookDao {
	public void persist(TestBook testBook);
	public void remove(TestBook testBook);
	public TestBook find(long id);
	public Collection<TestBook> findAll();
	public long count();
}
