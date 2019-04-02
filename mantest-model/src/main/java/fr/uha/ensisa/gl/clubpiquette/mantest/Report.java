package fr.uha.ensisa.gl.clubpiquette.mantest;

import java.util.Date;

public class Report {
	private long id = 0;
	private Date date;
	private Step problem;
	private String observation;
	private long start = 0;
	private long end = 0;
	private String fname = "no user was connected";
	private String lname = "";
	
	public static final Step DEFAULT_STEP = null;
	public static final String DEFAULT_OBSERVATION = "";
	
	public Report() {
		this.date = new Date();
		this.problem = DEFAULT_STEP;
		this.observation = DEFAULT_OBSERVATION;
		this.start = new Date().getTime();
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	protected long getEnd() {
		return end;
	}

	public Step getProblem() {
		return problem;
	}
	
	public void setProblem(Step problem) {
		this.problem = problem;
	}
	
	public String getObservation() {
		return observation;
	}
	
	public void setObservation(String observation) {
		this.observation = observation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		if (id >= 0)
			this.id = id;
	}
	
	public void setEnd() {
		this.end = new Date().getTime();
	}
	
	public long getTimeOccured() {
		return this.end - this.start;
	}
	
	public long getStart() {
		return this.start;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public boolean successed() {
		return (this.problem == null);
	}
}
