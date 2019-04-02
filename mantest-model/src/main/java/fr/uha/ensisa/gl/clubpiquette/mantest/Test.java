package fr.uha.ensisa.gl.clubpiquette.mantest;

public abstract class Test {
	private long id;
	private String name;
	private int nature; // 0 -> Test ; 1 -> TestSuite

	public final static long DEFAULT_ID = 0;
	public final static String DEFAULT_NAME = "unknown test";
	public final static int DEFAULT_NATURE = 0;
	
	public final static int TEST_NATURE = 0;
	public final static int TESTSUITE_NATURE = 1;

	public Test() {
		this.id = DEFAULT_ID;
		this.name = DEFAULT_NAME;
		this.nature = DEFAULT_NATURE;
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

	public int getNature() {
		return nature;
	}

	public void setNature(int nature) {
		this.nature = nature;
	}
	
	
}
