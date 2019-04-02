package fr.uha.ensisa.gl.clubpiquette.mantest;

import java.util.ArrayList;

public class TestSuite extends Test {
	private ArrayList<Test> tests;
	
	public TestSuite() {
		super();
		tests = new ArrayList<Test>();
		super.setNature(Test.TESTSUITE_NATURE);
	}

	public ArrayList<Test> getTests() {
		return tests;
	}

	public void setTests(ArrayList<Test> tests) {
		this.tests = tests;
	}
	
	public void addTest(Test t) {
		this.tests.add(t);
	}
	
	public void removeTest(Test t) {
		this.tests.remove(t);
	}
	
	public Test findTest(long id) {
		for (Test t : this.tests) {
			if (t.getId() == id)
				return t;
		}
		return null;
	}
	
	public int count() {
		return this.tests.size();
	}
}
