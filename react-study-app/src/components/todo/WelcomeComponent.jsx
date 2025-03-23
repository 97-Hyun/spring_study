import { useParams, Link } from "react-router-dom";
import { useState } from "react";
import { getHelloWorldPathVariable } from "./api/HelloWorldApiService";
import { useAuth } from "./security/AuthContext";

export default function WelcomeComponent() {
  const { username } = useParams();

  const [message, setMessage] = useState(null);
  const authContext = useAuth();

  function callHelloWorldRestApi() {
    getHelloWorldPathVariable("hyun", authContext.token)
      .then((response) => {
        console.log(response);
        setMessage(response.data.message);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (
    <div className="Welocm">
      <h1>Welcome {username}</h1>
      <div>
        You todos. <Link to="/todos">Click here</Link>
      </div>
      <div>
        <button className="btn btn-success m-5" onClick={callHelloWorldRestApi}>
          Call Hello World
        </button>
      </div>
      <div className="test-info">{message}</div>
    </div>
  );
}
