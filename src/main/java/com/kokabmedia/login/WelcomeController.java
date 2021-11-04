package com.kokabmedia.login;

import org.springframework.beans.factory.annotation.Autowired;
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
 * The dispatcher servlet is the Front Controller for the Spring MVC framework 
 * handles all the requests of the root (/) of the web application. 
 * 
 * Dispatcher servlet knows all the HTTP request methods GET, POST, PUT AND DELETE 
 * and what java methods they are mapped to with annotations. Dispatcher servlet will 
 * delegate which controller should handle a specific request. Dispatcher servlet looks 
 * at the URL and the request method. 
 */
@Controller
public class WelcomeController {
	
	/*
	 * This method will return a a view in the form of a JSP file in the welcome page.
	 * 
	 * When HTTP request is sent to a certain URL and that URL contains a path which
	 * is declared on the @RequestMapping annotation, in this case the appended "/" this method 
	 * will be called.
	 * 
	 * The method = RequestMethod.GET parameter will make the login method respond to a only
	 * HTTP GET request.
	 * 
	 * View Resolver in the todo-servlet.xml file will redirect the Dispatcher Servlet and the 
	 * @RequestMapping annotated controller method to the root login page provided by Spring Security
	 * and set the prefix and suffix for the JSP resources. When there is no @ResponseBody annotation 
	 * the Dispatcher Servlet makes use of the View resolver in the class path and returns a specific 
	 * JSP file with a matching name in this case "welcome" as a view if the authentication from 
	 * WebSecurity in the login page is correct. The Dispatcher Servlet resolves the view.
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	/*
	 * The @ResponseBody annotation will make the Dispatcher servlet understand that the data that being 
	 * returned from the method is not part of a URL or the name of view, but the response that we want 
	 * to send back. The returning value in the method body will be sent back as a response.
	 */
	//@ResponseBody
	/*
	 * When the HTTP payload is read from the post request body the ModelMap makes directly 
	 * available to the view. The model passes the information from the controller to the view
	 * and will pass the "name" attribute to the name variable in the welcome.jsp file and make
	 * it available to the view. The model to view interaction is handled by the Dispatcher Servlet.
	 */
	public String showLoginPage(ModelMap model) {
		
		// The value name is now available to the view through the model and mapped to the jsp value.
		model.put("name", "KokabMedia");
		// A JSP file with the name welcome is returned to the Dispatcher Servlet.
		return "welcome";
	}
}
