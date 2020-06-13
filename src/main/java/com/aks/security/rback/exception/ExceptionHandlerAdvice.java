package com.aks.security.rback.exception;

import static com.aks.security.rback.constants.Constants.HOME_PAGE;
import static com.aks.security.rback.constants.Constants.INDEX_PAGE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ExceptionHandlerAdvice {

	private static Logger logger = Logger.getLogger(ExceptionHandlerAdvice.class);
	
	@Autowired
	MessageSource messageSource;
	
	@ExceptionHandler({ AccessDeniedException.class })
	public ModelAndView	handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		logger.info("Start: " + getClass().getName() + " : handleException()");
		return new ModelAndView(INDEX_PAGE, "msg", ex.getMessage());
	}
	
	@ExceptionHandler({ CustomException.class })
	public ModelAndView handleNotFoundException(CustomException ex) {
		//String errorMsg = messageSource.getMessage(ex.getErrorCode(), new Object[] {ex.getErrorInfo()}, LocaleContextHolder.getLocale());
		String errorMsg = messageSource.getMessage(ex.getErrorCode(), ex.getObjs(), LocaleContextHolder.getLocale());
		logger.info("Start: " + getClass().getName() + " : handleNotFoundException() : " + errorMsg);
		return new ModelAndView(HOME_PAGE, "msg", errorMsg);
	}
	
}
