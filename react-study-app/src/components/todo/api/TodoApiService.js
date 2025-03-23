import { apiClient } from "./ApiClient";

export const getAllTodosByUserName = (username) =>
  apiClient.get(`/users/${username}/todos`);

export const getTodoByUserNameAndId = (username, id) =>
  apiClient.get(`/users/${username}/todos/${id}`);

export const deleteTodosByUserNameAndId = (username, id) =>
  apiClient.delete(`/users/${username}/todos/${id}`);

export const updateTodosByUserNameAndId = (username, id, todo) =>
  apiClient.put(`/users/${username}/todos/${id}`, todo);

export const createTodo = (username, todo) =>
  apiClient.post(`/users/${username}/todos`, todo);
