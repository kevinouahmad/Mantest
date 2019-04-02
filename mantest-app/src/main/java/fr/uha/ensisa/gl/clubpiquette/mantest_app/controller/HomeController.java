package fr.uha.ensisa.gl.clubpiquette.mantest_app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.uha.ensisa.gl.clubpiquette.mantest.TestBook;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.DaoFactory;

@Controller
public class HomeController {
	@Autowired
	public DaoFactory daoFactory;

	@RequestMapping(value = "")
	public ModelAndView home() throws IOException {
		ModelAndView ret = new ModelAndView("home");
		// Adds an objet to be used in home.jsp
		ret.addObject("testBooks", daoFactory.getTestBookDao().findAll());
		ret.addObject("isAnyConnected", daoFactory.getUserDao().isAnyConnected());
		ret.addObject("user", daoFactory.getUserDao().whoIsConnected());
		return ret;
	}

	@RequestMapping(value = "/createTestBook")
	public String create(@RequestParam(required = true) String testBookName) throws IOException {
		TestBook newTestBook = new TestBook();
		long id = daoFactory.getTestBookDao().count() + 1;
		while (daoFactory.getTestBookDao().find(id) != null) id++;
		newTestBook.setId(id);
		newTestBook.setName(testBookName);
		daoFactory.getTestBookDao().persist(newTestBook);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/deleteTestBook")
	public String remove(@RequestParam(required = true) long id) throws IOException {
		daoFactory.getTestBookDao().remove(daoFactory.getTestBookDao().find(id));
		return "redirect:/";
	}
	
	@RequestMapping(value = "/renameTestBook")
	public String rename(@RequestParam(required = true) long testBookId, @RequestParam(required=true) String testBookName) 
			throws IOException {
		TestBook toRename = daoFactory.getTestBookDao().find(testBookId);
		toRename.setName(testBookName);
		return "redirect:/";
	}
}
