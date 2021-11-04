package com.kokabmedia.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;


/*
 * This class will function as a controller for the TodoService class and as servlet
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
/*
 * The @SessionAttributes lets us utilise a session.
 * 
 * The difference between session and request is that in a request we have to hold on to a value
 * with explicit GET request calls and model.addAttribute() methods, while a session handles that 
 * for us, and stores the value in a session and makes it available in every model. The Spring 
 * framework talks to the model and not to the session. 
 * 
 * The value of of name in this case that has been added to the model will live in the server 
 * memory for the duration of the session which is 30 minutes.
 */
@SessionAttributes("name")
public class TodoController {
	
	/* 
	 * The @Autowired annotation tells the Spring framework that service instance (bean)
	 * is an dependency of TodoController class, it is a mechanism for implementing Spring 
	 * dependency injection.
	 * 
	 * The TodoService bean is now a dependency of the TodoController class.
	 * 
	 * The Spring framework creates an instance (bean) of the TodoService and autowires 
	 * as a dependency to the TodoController class object when it is instantiated.
	 */
	@Autowired
	TodoService service;
	
	// For binding and formating date
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
	/*
	 * This method will return a a view in the form of a JSP file.
	 * 
	 * When HTTP request is sent to a certain URL and that URL contains a path which
	 * is declared on the @RequestMapping annotation, in this case the appended "/list-todos" 
	 * this method will be called.
	 * 
	 * The method = RequestMethod.GET parameter will make the login method respond to a only
	 * HTTP GET request.
	 * 
	 * View Resolver in the todo-servlet.xml file will redirect the Dispatcher Servlet and the 
	 * @RequestMapping annotated controller method to WEB-INF/views/login.jsp and set the prefix 
	 * and suffix for the JSP resources. When there is no @ResponseBody annotation the Dispatcher 
	 * Servlet makes use of the View resolver in the class path and returns a specific JSP file with
	 * a matching name in this case "list-todos" as a view. The Dispatcher Servlet resolves the view.
	 */
	@RequestMapping(value="/list-todos", method = RequestMethod.GET)
	/*
	 * The @ResponseBody annotation will make the Dispatcher servlet understand that the data that being 
	 * returned from the method is not part of a URL or the name of view, but the response that we want 
	 * to send back. The returning value in the method body will be sent back as a response.
	 */
	//@ResponseBody
	public String showListOfTodos(ModelMap model) {
		
		/* 
		 * The "todos" attribute will be mapped to variable in the JSP file that contains the variable "todos"
		 * and a list of todos will be shown coming in from the second parameter value. The model instance will
		 * make it available to the JSP view file.
		 */
		model.addAttribute("todos", service.retrieveTodos(retrieveLoggenInUserName()));
		
		// A JSP file with the name list-todos is returned to the Dispatcher Servlet.
		return "list-todos";
	}
	
	// Get user information from login form
	private String retrieveLoggenInUserName() {
		
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();

			}
	
	
	@RequestMapping(value="/add-todo", method = RequestMethod.GET)
	public String showLTodoPage(ModelMap model) {

		model.addAttribute("todo", new Todo(0, retrieveLoggenInUserName(), "", new Date(), false));
	
		return "todo";
	}
	
	
	/* 
	 * This method will return a a view in the form of a JSP file and take in a HTTP POST
	 * request parameter.
	 * 
	 * The method = RequestMethod.POST parameter will make the login method respond to a only
	 * HTTP POST request.
	 *
	 * We add a command bean Spring form tags in the todo.jsp file by and specify the command 
	 * object Todo java class and specifying the path to bean fields, Form Binding will be 
	 * performed from the value that is inserted in to the form and that data will be mapped 
	 * to java bean. Form Binding is taking data from a form and making them available to a 
	 * Spring bean.
	 * 
	 * BindingResult will collect validation errors messages.
	 * 
	 * The ModelMap has the ability to store attributes in a map and chain to he method call.
	 */
	@RequestMapping(value="/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, Todo todo) {
	
	
		// Creates a new todo
		service.addTodo(retrieveLoggenInUserName(), todo.getDesc(), new Date(), false);
		
		// Clears the model so no values are being passed in the URL
		model.clear();
		
		// the redirect keyword redirects the request to a specific URL
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id, ModelMap model) {
	
		/* 
		 * Delete a specific todo, the id of a todo is passed in as a  parameter value ${todo.id}
		 * in the list-todo.jsp file
		 */
		service.deleteTodo(id);
		
		// Clears the model so no values are being passed in the URL
		model.clear();
		
		// the redirect keyword redirects the request to a specific URL
		return "redirect:list-todos";
	}
 
	@RequestMapping(value="/update-todo", method = RequestMethod.GET)
	public String updateTodo(@RequestParam int id, ModelMap model) {
	
		/* 
		 * Retrieves a specific todo, the id of a todo is passed in as a  parameter value ${todo.id}
		 * in the list-todo.jsp file
		 */
		Todo todo = service.retrieveTodo(id);
		model.addAttribute("todo", todo);

		// Returns to todo JSP view page
		return "todo";
	}
	
	@RequestMapping(value="/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, Todo todo) {
	
		todo.setUser(retrieveLoggenInUserName());
		service.updateTodo(todo);
		// the redirect keyword redirects the request to a specific URL
		return "redirect:list-todos";
	}
		
	
}
