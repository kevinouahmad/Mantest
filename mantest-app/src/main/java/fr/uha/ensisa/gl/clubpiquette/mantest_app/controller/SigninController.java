package fr.uha.ensisa.gl.clubpiquette.mantest_app.controller;

import java.io.IOException;
import java.security.NoSuchProviderException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.uha.ensisa.gl.clubpiquette.mantest.User;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.DaoFactory;
import fr.uha.ensisa.gl.clubpiquette.mantest.dao.UserDao;

@Controller
public class SigninController {
	@Autowired
	public DaoFactory daoFactory;

	@RequestMapping(value = "/signin")
	public ModelAndView signin() throws IOException {
		ModelAndView ret = new ModelAndView("signin");
		ret.addObject("isAnyConnected", daoFactory.getUserDao().isAnyConnected());
		ret.addObject("user", daoFactory.getUserDao().whoIsConnected());
		return ret;
	}
	
	@RequestMapping(value = "/signup")
	public ModelAndView signup() throws IOException {
		ModelAndView ret = new ModelAndView("signup");
		ret.addObject("isAnyConnected", daoFactory.getUserDao().isAnyConnected());
		ret.addObject("user", daoFactory.getUserDao().whoIsConnected());
		return ret;
	}
	
	@RequestMapping(value = "/connect")
	public String connect(@RequestParam(required=true) String mail, @RequestParam(required=true) String password) throws IOException, NoSuchProviderException {
		if (daoFactory.getUserDao().connect(mail, password)) {
			return "redirect:/";
		}
		return "redirect:/signin";
		
	}
	
	@RequestMapping(value = "/disconnect")
	public String disconnect() throws IOException, NoSuchProviderException {
		daoFactory.getUserDao().disconnect();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/createUser")
	public String create(@RequestParam(required = true) String fname, @RequestParam(required=true) String lname,
			@RequestParam(required=true) String mail, @RequestParam(required=true) String password) throws IOException {
		UserDao userDao = daoFactory.getUserDao();
		long id = userDao.count() + 1;
		while (userDao.find(id) != null) id++;
		User newUser = new User(id, mail, password, fname, lname);
		userDao.persist(newUser);
		return "redirect:/";
	}
}
