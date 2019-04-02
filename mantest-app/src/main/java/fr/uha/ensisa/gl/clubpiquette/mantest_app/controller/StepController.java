package fr.uha.ensisa.gl.clubpiquette.mantest_app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.uha.ensisa.gl.clubpiquette.mantest.SingleTest;
import fr.uha.ensisa.gl.clubpiquette.mantest.Step;
import fr.uha.ensisa.gl.clubpiquette.mantest.TestBook;
import fr.uha.ensisa.gl.clubpiquette.mantest.TestSuite;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.DaoFactory;

@Controller
public class StepController {
	@Autowired
	public DaoFactory daoFactory;

	@RequestMapping(value = "/step")
	public ModelAndView Step(@RequestParam(required=true) long testBookId, @RequestParam(required=true) long testId,
			@RequestParam(required=true) long subtestId,
			@RequestParam(required=true) long stepId) throws IOException {
		ModelAndView ret = new ModelAndView("step");
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
		Step current = parent.find(stepId);
		
		ret.addObject("test", parent);
		ret.addObject("step", current);
		ret.addObject("isAnyConnected", daoFactory.getUserDao().isAnyConnected());
		ret.addObject("user", daoFactory.getUserDao().whoIsConnected());
		return ret;
	}
	
	@RequestMapping(value = "/editStep")
	public String edit(@RequestParam(required = true) long testBookId, @RequestParam(required = true) long testId,
			@RequestParam(required=true) long subtestId,
			@RequestParam(required=true) long stepId, @RequestParam(required=true) String stepDescription) throws IOException {
		Step current;
		if (subtestId != 0) {
			TestSuite grandParent = (TestSuite)daoFactory.getTestBookDao().find(testBookId).find(testId);
			SingleTest parent = (SingleTest)grandParent.findTest(subtestId);
			current = parent.find(stepId);
		}
		else {
			current = ((SingleTest)daoFactory.getTestBookDao().find(testBookId).find(testId)).find(stepId);
		}
		current.setDescription(stepDescription);
		return "redirect:/singleTest?testBookId=" + testBookId + "&testId=" + testId + "&subtestId=" + subtestId;
	}
}
