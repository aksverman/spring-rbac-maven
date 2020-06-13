package com.aks.security.rback.exception;

import static com.aks.security.rback.constants.Constants.INDEX_PAGE;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

//@Component
public class CustomHandlerResolverImpl implements HandlerExceptionResolver {

	private static Logger logger = Logger.getLogger(CustomHandlerResolverImpl.class);
	
	@Autowired
	MessageSource messageSource;
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		logger.error(" " + getClass().getName() + " : " + ex.getMessage());
		String msg ="";
		if ( null != ((CustomException)ex).getErrorCode() )
			messageSource.getMessage("E0002", new Object[] {}, LocaleContextHolder.getLocale());
		return new ModelAndView(INDEX_PAGE, "msg", msg);
	}

}
