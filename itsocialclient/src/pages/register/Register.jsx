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
  const [err, setErr] = useState(null);
  const handleChange = (e) => {
    setInputs((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleClick =  (e) => {
    e.preventDefault();
      const process = async () => {
        let form = new FormData();
        for (let field in inputs)
             form.append(field, inputs[field]);
        if(avatarFile!==null)form.append("avatarFile", avatarFile.current.files[0]);
        if(bgImageFile!==null)form.append("bgImageFile", bgImageFile.current.files[0]);
        try{
        const res = await makeRequest.post(endpoints["register"], form);
        navigate("/login");
      } catch(error){
        setErr(error.response.data)
  };}
  process();
}

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
          {err && err}
          <form>
            <input
              type="text"
              placeholder="Username"
              name="username"
              onChange={handleChange} required
            />
            <input
              type="email"
              placeholder="Email"
              name="email"
              onChange={handleChange}required
            />
            <input
              type="password"
              placeholder="Password"
              name="password"
              onChange={handleChange}required
            />
            <input
              type="password"
              placeholder="Confirm Password"
              name="confirmPassword"
              onChange={handleChange}required
            />
            <input
              type="text"
              placeholder="Full Name"
              name="fullName"
              onChange={handleChange}required
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
            <button onClick={handleClick}>Register</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Register;
