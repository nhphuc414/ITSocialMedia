import { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { AuthContext } from "../../context/authContext";
import "./login.scss";
import LoginGoogleBtn from "../../components/googlebtn/GoogleButton";

const Login = () => {
  const [inputs, setInputs] = useState({
    username: "",
    password: "",
  });
  const [err, setErr] = useState(null);

  const navigate = useNavigate()
  
  const handleChange = (e) => {
    setInputs((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };
  const { login } = useContext(AuthContext);
  const handleLogin = async (e) => {
    e.preventDefault();
    let form = new FormData()
    for (let field in inputs)
         form.append(field, inputs[field]);
    try {
      await login(form,false);
      navigate("/")
    } catch (error) {
      console.log(error.body)
      setErr(error.response.data);
    }
  };
  return (
    <div className="login">
      <div className="card">
        <div className="left">
          <h1>Hello World.</h1>
          <p>
            This is my project.
          </p>
          <span>Don't you have an account?</span>
          <Link to="/register">
            <button>Register</button>
          </Link>
        </div>
        <div className="right">
          <h1>Login</h1>
          {err && err}
          <form>
            <input
              type="text"
              placeholder="Username"
              name="username"
              onChange={handleChange}
            />
            <input
              type="password"
              placeholder="Password"
              name="password"
              onChange={handleChange}
            />
            <button  className="button" onClick={handleLogin}>Login</button>
            <LoginGoogleBtn className="button"/>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Login;
