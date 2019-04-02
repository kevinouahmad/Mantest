package fr.uha.ensisa.gl.clubpiquette.mantest_app.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.uha.ensisa.gl.clubpiquette.mantest.Report;
import fr.uha.ensisa.gl.clubpiquette.mantest.SingleTest;
import fr.uha.ensisa.gl.clubpiquette.mantest.Step;
import fr.uha.ensisa.gl.clubpiquette.mantest.TestBook;
import fr.uha.ensisa.gl.clubpiquette.mantest.TestSuite;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.DaoFactory;

@Controller
public class RunController {
	@Autowired
	public DaoFactory daoFactory;

	@RequestMapping(value = "/runTest")
	public ModelAndView execution(@RequestParam(required=true) long testBookId, @RequestParam(required=true) long testId,
			@RequestParam(required=true) long subtestId, @RequestParam(required=true) int stepIndex, 
			@RequestParam(required=true) long reportId) throws IOException {
		ModelAndView ret = new ModelAndView("run");
		TestBook tb = daoFactory.getTestBookDao().find(testBookId);
		ret.addObject("testBook", tb);
		SingleTest parent;
		if (subtestId != 0) {
			TestSuite grandParent = (TestSuite)tb.find(testId);
			parent = (SingleTest)grandParent.findTest(subtestId);
			ret.addObject("isSub", true);
			ret.addObject("parentTest", grandParent);
		}
		else {
			parent = (SingleTest)tb.find(testId);
			ret.addObject("isSub", false);
		}
		
		Report currentReport;
		if (stepIndex==0) {
			currentReport = new Report();
			long id = parent.countReports() + 1;
			while (parent.getReport(id) != null) { id++; }
			currentReport.setId(id);
			parent.addReport(currentReport);
		}
		else {
			currentReport = parent.getReport(reportId);
		}
		
		ArrayList<Step> steps = parent.getSteps();
		Step currentStep = steps.get(stepIndex);
		boolean isLastStep = (stepIndex == parent.count() - 1);
		ret.addObject("test", parent);
		ret.addObject("steps", steps);
		ret.addObject("currentStep", currentStep);
		ret.addObject("stepIndex", stepIndex);
		ret.addObject("isLastStep", isLastStep);
		ret.addObject("currentReport", currentReport);
		ret.addObject("isAnyConnected", daoFactory.getUserDao().isAnyConnected());
		ret.addObject("user", daoFactory.getUserDao().whoIsConnected());
		return ret;
	}
	
	@RequestMapping(value = "/testExecutionEndedWell")
	public String finished(@RequestParam(required=true) long testBookId, @RequestParam(required=true) long testId,
			@RequestParam(required=true) long subtestId, @RequestParam(required=true) long reportId,
			@RequestParam(required=false, defaultValue="no user was connected") String fname,
			@RequestParam(required=false, defaultValue="") String lname) throws IOException {
		TestBook tb = daoFactory.getTestBookDao().find(testBookId);
		SingleTest parent;
		if (subtestId != 0) {
			TestSuite grandParent = (TestSuite)tb.find(testId);
			parent = (SingleTest)(grandParent.findTest(subtestId));
		}
		else {
			parent = (SingleTest)tb.find(testId);
		}
		Report currentReport = parent.getReport(reportId);
		currentReport.setEnd();
		currentReport.setObservation("Everything went well");
		currentReport.setFname(fname);
		currentReport.setLname(lname);
		return "redirect:/testBook?id=" + testBookId;
	}
	
	@RequestMapping(value = "/reportFilled")
	public String reportFilled(@RequestParam(required=false, defaultValue="no user was connected") String fname,
		@RequestParam(required=false) String lname,
	@RequestParam(required=true) long testBookId, @RequestParam(required=true) long testId,
	@RequestParam(required=true) long subtestId,
	@RequestParam(required=true) long reportId, @RequestParam(required=true) long stepProblemId, 
	@RequestParam(required=true) String observation) throws IOException {
		Report currentReport;
		Step problem;
		if (subtestId != 0) {
			TestSuite grandParent = (TestSuite)daoFactory.getTestBookDao().find(testBookId).find(testId);
			SingleTest parent = (SingleTest)(grandParent.findTest(subtestId));
			currentReport = parent.getReport(reportId);
			problem = parent.find(stepProblemId);
		}
		else {
			SingleTest parent= (SingleTest)daoFactory.getTestBookDao().find(testBookId).find(testId);
			currentReport = parent.getReport(reportId);
			problem = parent.find(stepProblemId);
		}
		
		currentReport.setEnd();
		currentReport.setProblem(problem);
		currentReport.setObservation(observation);
		currentReport.setFname(fname);
		currentReport.setLname(lname);
		return "redirect:/testBook?id=" + testBookId;
	}
}
