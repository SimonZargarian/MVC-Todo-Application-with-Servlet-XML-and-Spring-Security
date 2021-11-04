package com.kokabmedia.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;


/*
 * This class will function as a controller for the LoginService class and as servlet
 * that responds to HTTP requests.
 * 
 * The dispatcher servlet is the Front Controller for the Spring MVC framework handles 
 * all the requests of the root (/) of the web application. 
 * 
 * Dispatcher servlet knows all the HTTP request methods GET, POST, PUT AND DELETE 
 * and what java methods they are mapped to with annotations. Dispatcher servlet will 
 * delegate which controller should handle a specific request. Dispatcher servlet looks 
 * at the URL and the request method. 
 */
@Controller
public class LogoutController {
	
	/*
	 * This method will return a a view in the form of a JSP file, log the user out and
	 * return the view to the root login page provided by default by Spring Security.
	 * 
	 * When HTTP request is sent to a certain URL and that URL contains a path which
	 * is declared on the @RequestMapping annotation, in this case the appended "/logout" 
	 * this method will be called.
	 * 
	 * The method = RequestMethod.GET parameter will make the logout method respond to a only
	 * HTTP GET request.
	 * 
	 * View Resolver in the todo-servlet.xml file will redirect the Dispatcher Servlet and the 
	 * @RequestMapping annotated controller method to root login page which is part of 
	 * Spring Security and set the prefix and suffix for the JSP resources. When there is no 
	 * @ResponseBody annotation the Dispatcher Servlet makes use of the View resolver in the 
	 * class path and returns a specific JSP file with a matching name in this case "login" 
	 * because it is configured as root as a view. The Dispatcher Servlet resolves the view.
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	/*
	 * The @ResponseBody annotation will make the Dispatcher servlet understand that the data that being 
	 * returned from the method is not part of a URL or the name of view, but the response that we want 
	 * to send back. The returning value in the method body will be sent back as a response.
	 */
	//@ResponseBody
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		
		// Terminate authentication
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		// Redirect to home page which is the login page.
		return "redirect:/";
	}	
}
