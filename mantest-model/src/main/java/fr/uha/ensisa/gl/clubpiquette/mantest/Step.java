package fr.uha.ensisa.gl.clubpiquette.mantest;

public class Step {
	private long id; 
	private String name;
	private String description;
	
	public final static long DEFAULT_ID = 0;
	public final static String DEFAULT_NAME = "unknown step";
	public final static String DEFAULT_DESCRIPTION = "No description for the moment..";
	
	public Step() {
		this.id = DEFAULT_ID;
		this.name = DEFAULT_NAME;
		this.description = DEFAULT_DESCRIPTION;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
