import { useNavigate, useParams } from "react-router-dom";
import {
  createTodo,
  getTodoByUserNameAndId,
  updateTodosByUserNameAndId,
} from "./api/TodoApiService";
import { useAuth } from "./security/AuthContext";
import { useEffect, useState } from "react";
import { ErrorMessage, Field, Form, Formik } from "formik";
import moment from "moment";

export default function TodoComponent() {
  const { id } = useParams();
  const authContext = useAuth();
  const navigate = useNavigate();
  const username = authContext.username;
  const [description, setDescription] = useState("");
  const [targetDate, setTargetDate] = useState("");
  const [done, setDone] = useState(false);

  useEffect(() => fetchTodo(), [id]);

  function fetchTodo() {
    if (id !== -1) {
      getTodoByUserNameAndId(username, id)
        .then((response) => {
          setDescription(response.data.description);
          setTargetDate(response.data.targetDate);
          setDone(response.data.done);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }

  function onSubmit(values) {
    const todo = {
      id: id,
      username: username,
      description: values.description,
      targetDate: values.targetDate,
      done: values.done,
    };

    if (id === -1) {
      createTodo(username, todo)
        .then((response) => {
          navigate("/todos");
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      updateTodosByUserNameAndId(username, id, todo)
        .then((response) => {
          navigate("/todos");
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }

  function validate(values) {
    let errors = {};

    if (values.description.length < 5) {
      errors.description = "Enter a valid description";
    }

    if (
      values.targetDate == null ||
      values.targetDate === "" ||
      !moment(values.targetDate).isValid()
    ) {
      errors.targetDate = "Enter a target date";
    }

    return errors;
  }

  return (
    <div className="container">
      <h1>Enter Todo Details</h1>
      <div>
        <Formik
          initialValues={{ description, targetDate, done }}
          enableReinitialize={true}
          onSubmit={onSubmit}
          validate={validate}
          validateOnChange={false}
          validateOnBlur={false}
        >
          {(props) => (
            <Form>
              <ErrorMessage
                name="description"
                className="alert alert-warning"
                component="div"
              />
              <fieldset className="form-group">
                <label htmlFor="description">Description</label>
                <Field
                  type="text"
                  className="form-control"
                  name="description"
                ></Field>
              </fieldset>

              <ErrorMessage
                name="targetDate"
                className="alert alert-warning"
                component="div"
              />
              <fieldset className="form-group">
                <label htmlFor="targetDate">Target Date</label>
                <Field
                  type="date"
                  className="form-control"
                  name="targetDate"
                ></Field>
              </fieldset>
              <fieldset className="form-group">
                <label>
                  <Field type="checkbox" name="done" /> Done
                </label>
              </fieldset>
              <div>
                <button className="btn btn-success m-5" type="submit">
                  Save
                </button>
              </div>
            </Form>
          )}
        </Formik>
      </div>
    </div>
  );
}
