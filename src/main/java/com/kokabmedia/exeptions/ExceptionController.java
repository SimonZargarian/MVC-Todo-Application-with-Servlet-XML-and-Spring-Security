package com.kokabmedia.exeptions;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/*
 * This class is designed to handle all exceptions that occur with the controller 
 * methods of this application.
 * 
 * ControllerAdvice means that the content of this class is applicable to all 
 * controllers of this application.
 */
@ControllerAdvice
@EnableWebMvc // enabling Spring MVC for this application
public class ExceptionController {
 
	private Log logger = LogFactory.getLog(ExceptionController.class);

	@ExceptionHandler(value = Exception.class)
	public String handleError(HttpServletRequest req, Exception exception) {
		logger.error("Request: " + req.getRequestURL() + " raised " + exception);
		return "error";
	}
}