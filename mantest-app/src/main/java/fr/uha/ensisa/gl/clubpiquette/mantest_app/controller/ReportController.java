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
import fr.uha.ensisa.gl.clubpiquette.mantest.TestBook;
import fr.uha.ensisa.gl.clubpiquette.mantest.TestSuite;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.DaoFactory;

@Controller
public class ReportController {
	@Autowired
	public DaoFactory daoFactory;

	@RequestMapping(value = "/viewReports")
	public ModelAndView displayReports(@RequestParam(required=true) long testBookId, @RequestParam(required=true) long testId,
			@RequestParam(required=true) long subtestId) throws IOException {
		ModelAndView ret = new ModelAndView("reports");
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
			ret.addObject("isSub", false);
			parent = (SingleTest)tb.find(testId);
		}
		parent.clean();
		ArrayList<Report> reports = parent.getReports();
		
		ret.addObject("test", parent);
		ret.addObject("reports", reports);
		ret.addObject("isAnyConnected", daoFactory.getUserDao().isAnyConnected());
		ret.addObject("user", daoFactory.getUserDao().whoIsConnected());
		return ret;
	}
}
