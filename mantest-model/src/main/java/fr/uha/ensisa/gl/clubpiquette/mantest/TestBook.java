package fr.uha.ensisa.gl.clubpiquette.mantest;

import java.util.ArrayList;

public class TestBook {
	private long id;
	private String name;
	private ArrayList<Test> tests;
	
	public final static long DEFAULT_ID = 0;
	public final static String DEFAULT_NAME = "unknwown test book";
	
	public TestBook() {
		this.id = DEFAULT_ID;
		this.name = DEFAULT_NAME;
		this.tests = new ArrayList<Test>();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		if (id >= 0)
			this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Test> getTests() {
		return tests;
	}

	public void setTests(ArrayList<Test> tests) {
		this.tests = tests;
	}
	
	public void addTest(Test test) {
		this.tests.add(test);
	}
	
	public void removeTest(Test test) {
		this.tests.remove(test);
	}
	
	public long count() {
		return this.tests.size();
	}
	
	public Test find(long id) {
		for (Test test : this.tests ) {
			if (test.getId() == id) {
				return test;
			}
		}
		return null;
	}
}
