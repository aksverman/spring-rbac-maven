package com.aks.security.rback.web.controller;

import static com.aks.security.rback.constants.Constants.CUSTOM_LOGIN_PAGE;
import static com.aks.security.rback.constants.Constants.INDEX_PAGE;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);
	//static DemoLogger	dLogger = new DemoLogger(HomeController.class);
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView	  homeView() {
		logger.info("HomeController : index page accessed");
		return new ModelAndView(INDEX_PAGE);
	}
	
	@RequestMapping(path = "/loginpage", method = RequestMethod.GET)
	public ModelAndView		getCustomLoginPage() {
		logger.info("Custom Login Page accessed...");
		return new ModelAndView(CUSTOM_LOGIN_PAGE);
	}
}
