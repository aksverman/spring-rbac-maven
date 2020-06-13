package com.aks.security.rback.web.controller;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import static com.aks.security.rback.constants.Constants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/accessdenied")
public class AccessDeniedController {

	private static Logger logger = Logger.getLogger(AccessDeniedController.class);
	
	@RequestMapping("/")
	public String accessDeniedPage() {
		logger.info("Access Denied controller");
		return ACCESSDENIED_PAGE;
	}
	
	@RequestMapping(path = "/403/{msg}", method = RequestMethod.GET)
	public ModelAndView	errorPage400(@PathVariable("msg") String msg) {
		logger.info(" " + getClass().getName() + " : " + msg);
		return new ModelAndView("indexjsp", "msg", msg);
	}
	
	@RequestMapping("/sessionExpired")
	public ModelAndView 	sessionExpired(HttpServletRequest request, HttpServletResponse response) {
		String servletPath = request.getHeader("Referer");
		logger.info("Redere path : " + servletPath);
		//return new ModelAndView("redirect:/" + servletPath);
		return new ModelAndView("indexjsp", "msg", "Session is expired!");
	}
	
	@RequestMapping("/tooManySessions")
	public ModelAndView		tooManySessionForOneUser() {
		return new ModelAndView(INDEX_PAGE, "msg", "Users logging into multiple sessions.");
	}
}
