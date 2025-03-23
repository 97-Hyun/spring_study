package com.in28minutes.rest.webservices.restfulwebservices.todo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static final Logger log = LoggerFactory.getLogger(TodoService.class);
	private static List<Todo> todos = new ArrayList<>();
	
	private static int todosCount = 0;
	
	static {
		todos.add(new Todo(++todosCount, "hyun","Get AWS Certified",
							LocalDate.now().plusYears(10), false ));
		todos.add(new Todo(++todosCount, "hyun","Learn DevOps",
				LocalDate.now().plusYears(11), false ));
		todos.add(new Todo(++todosCount, "hyun","Learn Full Stack Development",
				LocalDate.now().plusYears(12), false ));
	}
	
	public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predicate = 
				todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	
	public Todo addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todosCount,username,description,targetDate,done);
		todos.add(todo);
		return todo;
	}
	
	public void deleteById(String username, int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id && todo.getUsername().equalsIgnoreCase(username);
		todos.removeIf(predicate);
	}

	public Todo findById(String username, int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id && todo.getUsername().equalsIgnoreCase(username);
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(Todo todo) {
		deleteById(todo.getUsername(), todo.getId());
		todos.add(todo);
	}
}