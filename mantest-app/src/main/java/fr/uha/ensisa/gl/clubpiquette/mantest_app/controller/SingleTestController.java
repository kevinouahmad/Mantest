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
public class SingleTestController {
	@Autowired
	public DaoFactory daoFactory;

	@RequestMapping(value = "/singleTest")
	public ModelAndView Test(@RequestParam(required=true) long testBookId, @RequestParam(required=true) long testId,
			@RequestParam(required = true) long subtestId) throws IOException {
		ModelAndView ret = new ModelAndView("test");
		TestBook tb = daoFactory.getTestBookDao().find(testBookId);
		ret.addObject("testBook", tb);
		if (subtestId != 0) {
			TestSuite parent = (TestSuite)tb.find(testId);
			SingleTest current = (SingleTest)parent.findTest(subtestId);
			ret.addObject("isSub", true);
			ret.addObject("parentTest", parent);
			ret.addObject("test", current);
		}
		
		else {
			SingleTest current = (SingleTest)tb.find(testId);
			ret.addObject("isSub", false);
			ret.addObject("test", current);
		}
		ret.addObject("isAnyConnected", daoFactory.getUserDao().isAnyConnected());
		ret.addObject("user", daoFactory.getUserDao().whoIsConnected());
		return ret;
	}

	@RequestMapping(value = "/createStep")
	public String create(@RequestParam(required = true) long testBookId, @RequestParam(required = true) long testId,
			@RequestParam(required=true) long subtestId,
			@RequestParam(required = true) String stepName) throws IOException {
		Step newStep = new Step();
		SingleTest current;
		long id;
		if (subtestId == 0) {
			current = (SingleTest)daoFactory.getTestBookDao().find(testBookId).find(testId);
		}
		else {
			TestSuite parent = (TestSuite)daoFactory.getTestBookDao().find(testBookId).find(testId);
			current = (SingleTest)parent.findTest(subtestId);
		}
		id = current.count() + 1;
		while (current.find(id) != null) id++;
		newStep.setId(id);
		newStep.setName(stepName);
		current.addStep(newStep);
		return "redirect:/singleTest?testBookId=" + testBookId + "&testId=" + testId + "&subtestId=" + subtestId;
	}
	
	@RequestMapping(value = "/deleteStep")
	public String remove(@RequestParam(required = true) long testBookId, @RequestParam(required = true) long testId,
			@RequestParam(required=true) long subtestId,
			@RequestParam(required=true) long stepId) throws IOException {
		SingleTest t;
		if (subtestId != 0) {
			TestSuite parent = (TestSuite)daoFactory.getTestBookDao().find(testBookId).find(testId);
			t = (SingleTest)parent.findTest(subtestId);
		}
		else {
			t = (SingleTest)daoFactory.getTestBookDao().find(testBookId).find(testId);
		}
		t.removeStep(t.find(stepId));
		
		return "redirect:/singleTest?testBookId=" + testBookId + "&testId=" + testId + "&subtestId=" + subtestId;
	}
	
	@RequestMapping(value = "/renameStep")
	public String rename(@RequestParam(required = true) long testBookId, @RequestParam(required=true) long testId,
			@RequestParam(required=true) long subtestId,
			@RequestParam(required=true) long stepId, @RequestParam(required=true) String stepName) throws IOException {
		
		TestBook tb = daoFactory.getTestBookDao().find(testBookId);
		SingleTest parent;
		if (subtestId != 0) {
			TestSuite grandParent = (TestSuite)tb.find(testId);
			parent = (SingleTest)grandParent.findTest(subtestId);
		}
		else {
			parent = (SingleTest)tb.find(testId);
		}
		Step toRename = parent.find(stepId);
		toRename.setName(stepName);
		return "redirect:/singleTest?testBookId=" + testBookId + "&testId=" + testId + "&subtestId=" + subtestId;
	}
}
