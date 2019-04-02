package fr.uha.ensisa.gl.clubpiquette.mantest_app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.uha.ensisa.gl.clubpiquette.mantest.SingleTest;
import fr.uha.ensisa.gl.clubpiquette.mantest.Test;
import fr.uha.ensisa.gl.clubpiquette.mantest.TestBook;
import fr.uha.ensisa.gl.clubpiquette.mantest.TestSuite;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.DaoFactory;

@Controller
public class TestBookController {
	@Autowired
	public DaoFactory daoFactory;

	@RequestMapping(value = "/testBook")
	public ModelAndView TestBook(@RequestParam(required=true) long id) throws IOException {
		ModelAndView ret = new ModelAndView("testBook");
		// Adds an objet to be used in home.jsp
		TestBook current = daoFactory.getTestBookDao().find(id);
		ret.addObject("testBook", current);
		ret.addObject("tests", current.getTests());
		ret.addObject("isAnyConnected", daoFactory.getUserDao().isAnyConnected());
		ret.addObject("user", daoFactory.getUserDao().whoIsConnected());
		return ret;
	}

	@RequestMapping(value = "/createTest")
	public String createTest(@RequestParam(required = true) long testBookId, @RequestParam(required=true) long testId,
			@RequestParam(required = true) String testName, 
			@RequestParam(required=true) int testNature) throws IOException {
		Test newTest;
		long id;
		if (testNature == 0) //single test
		{
			newTest = new SingleTest();
		}
		else {
			newTest = new TestSuite();
		}
		
		if (testId != 0) {
			TestSuite parent = (TestSuite)daoFactory.getTestBookDao().find(testBookId).find(testId);
			id = parent.count() + 1;
			while (parent.findTest(id) != null) id++;
			newTest.setId(id);
			newTest.setName(testName);
			parent.addTest(newTest);
		}
		else {
			TestBook current = daoFactory.getTestBookDao().find(testBookId);
			id = current.count() + 1;
			while (current.find(id) != null) id++;
			newTest.setId(id);
			newTest.setName(testName);
			current.addTest(newTest);
		}
		
		return "redirect:/testBook?id=" + testBookId;
	}
	
	@RequestMapping(value = "/deleteTest")
	public String remove(@RequestParam(required = true) long testBookId, @RequestParam(required = true) long testId,
			@RequestParam(required=true) long subtestId) throws IOException {
		if (subtestId != 0) {
			TestSuite parent = (TestSuite)daoFactory.getTestBookDao().find(testBookId).find(testId);
			parent.removeTest(parent.findTest(subtestId));
		}
		else {
			daoFactory.getTestBookDao().find(testBookId).removeTest(daoFactory.getTestBookDao().find(testBookId).find(testId));
		}
		
		return "redirect:/testBook?id=" + testBookId;
	}
	
	@RequestMapping(value = "/renameTest")
	public String rename(@RequestParam(required = true) long testBookId, @RequestParam(required=true) long testId,
			@RequestParam(required=true) long subtestId,
			@RequestParam(required=true) String testName) throws IOException {
		if (subtestId != 0) {
			TestSuite parent = (TestSuite)daoFactory.getTestBookDao().find(testBookId).find(testId);
			parent.findTest(subtestId).setName(testName);
		}
		else {
			TestBook parent = daoFactory.getTestBookDao().find(testBookId);
			Test toRename = parent.find(testId);
			toRename.setName(testName);
		}
		return "redirect:/testBook?id=" + testBookId;
	}
}
