package com.hyun.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    private static int todosCount = 0;

    static {
        todos.add(new Todo(++todosCount, "hyun", "Learn AWS", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "hyun", "Learn Devops", LocalDate.now().plusYears(2), false));
        todos.add(new Todo(++todosCount, "hyun", "Learn Full Stack Development", LocalDate.now().plusYears(3), false));
    }

    public List<Todo> findByUserName(String userName) {
        return todos.stream().filter(todo -> todo.getUsername().equalsIgnoreCase(userName)).toList();
    }

    public void  addTodo(String userName, String description, LocalDate targetDate) {
        todos.add(new Todo(++todosCount + 1, userName, description, targetDate, false));
    }

    public void deleteById(long id) {
        todos.removeIf(todo -> todo.getId() == id);
    }

    public Todo findById(long id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst().get();
    }

    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
