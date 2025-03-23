import { useEffect, useState } from "react";
import {
  deleteTodosByUserNameAndId,
  getAllTodosByUserName,
  getTodoByUserNameAndId,
} from "./api/TodoApiService";
import { useAuth } from "./security/AuthContext";
import { useNavigate } from "react-router-dom";

export default function ListTodosComponent() {
  const [todos, setTodos] = useState([]);
  const [message, setMessage] = useState(null);
  const authContext = useAuth();
  const navigate = useNavigate();
  const username = authContext.username;

  useEffect(() => refreshTodos(), []);

  function refreshTodos() {
    getAllTodosByUserName(username)
      .then((response) => {
        setTodos(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  function handleUpdateBtn(id) {
    navigate(`/todo/${id}`);

    getTodoByUserNameAndId(username, id)
      .then((response) => {})
      .catch((error) => {
        console.log(error);
      });
  }

  function handleDeleteBtn(id) {
    deleteTodosByUserNameAndId(username, id)
      .then((response) => {
        setMessage(`Delete of todo with ${id} successful`);
        refreshTodos();
        setTimeout(() => {
          setMessage(null);
        }, 5000);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  function addNewTodo() {
    navigate("/todo/-1");
  }

  return (
    <div className="container">
      <h1>Things You want To Do!</h1>
      {message && <div className="alert alert-warning">{message}</div>}
      <div>
        <table className="table">
          <thead>
            <tr>
              <th>id</th>
              <th>description</th>
              <th>Is Done?</th>
              <th>Target Date</th>
              <th>Update</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            {todos.map((todo) => (
              <tr key={todo.id}>
                <td>{todo.id}</td>
                <td>{todo.description}</td>
                <td>{todo.done.toString()}</td>
                <td>{todo.targetDate.toString()}</td>
                <td>
                  <button
                    className="btn btn-success"
                    type="button"
                    onClick={() => handleUpdateBtn(todo.id)}
                  >
                    Update
                  </button>
                </td>
                <td>
                  <button
                    className="btn btn-danger"
                    type="button"
                    onClick={() => handleDeleteBtn(todo.id)}
                  >
                    Delete
                  </button>
                </td>
                {/* <td>{todo.targetDate.toDateString()}</td> */}
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <button className="btn btn-success m-3" onClick={addNewTodo}>
        Add New Todo
      </button>
    </div>
  );
}
