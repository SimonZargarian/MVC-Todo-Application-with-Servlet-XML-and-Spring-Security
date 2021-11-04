package com.kokabmedia.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

/*
 * This class will be responsible for managing all the todos and function as a temporary
 * storage unit.
 * 
 * The @Service annotation allows the Spring framework to creates an instance (bean) 
 * of this interface and manage it with the Spring Application Context (the IOC container)
 * that maintains all the beans for the application.  
 *
 * The @Repository annotation lets the Spring framework manage the TodoService 
 * class as a Spring bean. The Spring framework will find the bean with auto-detection 
 * when scanning the class path with component scanning, that is declared in the 
 * todo-servlet.xml file. It turns the class into a Spring bean at the auto-scan time.
 * 
 * The @Service annotation is a specialisation of @Component annotation for more specific 
 * use cases.
 */
@Service 
public class TodoService {

	
	// Temporary storage unit.
	private static List<Todo> todos = new ArrayList<Todo>();
	
	// Count of amount of todos that will increment with the addTodo() method.
	private static int todoCount = 3;

	// Static block to insert dummy methods that cannot be initialized int the constructor. 
	static {
		todos.add(new Todo(1, "Ghiam", "Learn Spring MVC", new Date(), false));
		todos.add(new Todo(2, "Ghiam", "Learn Struts", new Date(), false));
		todos.add(new Todo(3, "Ghiam", "Learn Hibernate", new Date(),	false));
	}

	// Adds a todo to the list of todos
	public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
		todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
	}
	
	// Checks if the user name matches and retrieves that specific todo
	public List<Todo> retrieveTodos(String user) {
		List<Todo> filteredTodos = new ArrayList<Todo>();
		for (Todo todo : todos) {
			if (todo.getUser().equals(user))
				filteredTodos.add(todo);
		}
		return filteredTodos;
	}
	
	// Loops around the list, identifies the a todo with its id and deletes it from the list
	public void deleteTodo(int id) {
		Iterator<Todo> iterator = todos.iterator();
		while (iterator.hasNext()) {
			Todo todo = iterator.next();
			if (todo.getId() == id) {
				iterator.remove();
			}
		}
	}
	
	// Retrieve a specific Todo
	public Todo retrieveTodo(int id) {
		for (Todo todo : todos) {
			if (todo.getId() == id)
				return todo;
		}
		return null;
	}

	// Update a specific Todo
	public void updateTodo(Todo todo) {
		todos.remove(todo);
		todos.add(todo);
	}
	
}
