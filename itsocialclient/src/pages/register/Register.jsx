import { useRef, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./register.scss";
import { endpoints, makeRequest } from "../../axios";

const Register = () => {
  const [inputs, setInputs] = useState({
    username: "",
    email: "",
    password: "",
    confirmPassword:"",
    fullName: ""
  });
  const navigate = useNavigate()
  const avatarFile = useRef(null);
  const bgImageFile = useRef(null);
  const [err, setErr] = useState(false);

  const handleChange = (e) => {
    setInputs((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleClick =  (e) => {
    const process = async () => {
    let form = new FormData();
    for (let field in inputs)
         form.append(field, inputs[field]);
    form.append("avatarFile", avatarFile.current.files[0]);
    form.append("bgImageFile", bgImageFile.current.files[0]);
    const res = await makeRequest.post(endpoints["register"], form);
    console.log(res);
    if (res?.data?.status === 400)
        setErr(res.data);
    if(res?.data?.status === 201)
        navigate("/login");
    };
    e.preventDefault();
    if (inputs["password"] === inputs["confirmPassword"]) process();
  };
  console.log(err)

  return (
    <div className="register">
      <div className="card">
        <div className="left">
          <h1>IT Social.</h1>
          <p>
            MY Project.
          </p>
          <span>Do you have an account?</span>
          <Link to="/login">
            <button>Login</button>
          </Link>
        </div>
        <div className="right">
          <h1>Register</h1>
          <form>
            <input
              type="text"
              placeholder="Username"
              name="username"
              onChange={handleChange}
            />
            <input
              type="email"
              placeholder="Email"
              name="email"
              onChange={handleChange}
            />
            <input
              type="password"
              placeholder="Password"
              name="password"
              onChange={handleChange}
            />
            <input
              type="password"
              placeholder="Confirm Password"
              name="confirmPassword"
              onChange={handleChange}
            />
            <input
              type="text"
              placeholder="Full Name"
              name="fullName"
              onChange={handleChange}
            />
            <span>Avatar</span>
            <input
              type="file"
              placeholder="File"
              name="avatarFile"
              accept="image/*"
              ref={avatarFile} required
            />
            <span>Background</span>
            <input
              type="file"
              name="bgImageFile"
              accept="image/*"
              ref={bgImageFile} required
            />
            {err && err}
            <button onClick={handleClick}>Register</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Register;
