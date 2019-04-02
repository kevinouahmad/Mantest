package fr.uha.ensisa.gl.clubpiquette.mantest;

import java.util.ArrayList;

public class SingleTest extends Test {
	private ArrayList<Step> steps;
	private ArrayList<Report> reports;
	
	public SingleTest() {
		super();
		this.steps = new ArrayList<Step>();
		this.reports = new ArrayList<Report>();
		super.setNature(Test.TEST_NATURE);
	}
	
	public ArrayList<Step> getSteps() {
		return steps;
	}

	public void setSteps(ArrayList<Step> steps) {
		this.steps = steps;
	}
	
	public ArrayList<Report> getReports() {
		return reports;
	}

	public void setReports(ArrayList<Report> reports) {
		this.reports = reports;
	}

	public void addStep(Step step) {
		this.steps.add(step);
	}
	
	public void removeStep(Step step) {
		this.steps.remove(step);
	}
	
	public void addReport(Report report) {
		this.reports.add(report);
	}
	
	public void removeReport(Report report) {
		this.reports.remove(report);
	}
	
	public Report getReport(long id) {
		for (Report report : this.reports) {
			if (report.getId() == id) {
				return report;
			}
		}
		return null;
	}
	
	public long count() {
		return this.steps.size();
	}
	
	public Step find(long id) {
		for (Step step : this.steps) {
			if (step.getId() == id) {
				return step;
			}
		}
		return null;
	}
	
	public int countReports() {
		return this.reports.size();
	}
	
	public void clean() {
		ArrayList<Report> toRemove = new ArrayList<Report>();
		for (Report current : this.reports) {
			if (current.getTimeOccured() < 0) {
				toRemove.add(current);
			}
		}
		for (Report toBeRemoved : toRemove) {
			this.reports.remove(toBeRemoved);
		}
	}
	
	
	public int progress() {
		if (this.reports.size() > 0) {
			
			boolean already_successed = false;
			Report last = this.reports.get(this.reports.size()-1);
			for (Report current : this.reports) {
				if (current != last) {
					if (current.successed()) {
						already_successed = true;
					}
				}
			}
			if (already_successed) {
				if (last.successed() == false) {
					return -1;
				}
				
				else {
					return 0;
				}
			}
			else {
				if (last.successed() == false) {
					return 0;
				}
				else {
					return 1;
				}
			}
		}
		return 0;
	}
}
