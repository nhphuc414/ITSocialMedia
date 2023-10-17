import { useRef, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./register.scss";
import { endpoints, makeRequest } from "../../axios";
import { toast } from "react-toastify";

const Register = () => {
  const [inputs, setInputs] = useState({
    username: "",
    email: "",
    password: "",
    confirmPassword:"",
    fullName: ""
  });
  const navigate = useNavigate()
  const avatarFile = useRef("");
  const bgImageFile = useRef("");
  const [err, setErr] = useState("");
  const handleChange = (e) => {
    setInputs((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleClick =  (e) => {
    e.preventDefault();
      const process = async () => {
        try{
        let form = new FormData();
        for (let field in inputs)
             {
              console.log(inputs[field])
              if (inputs[field]!=="") form.append(field, inputs[field]); 
             }
        if (avatarFile.current.files[0]!=undefined)form.append("avatarFile", avatarFile.current.files[0]);
        if (avatarFile.current.files[0]!=undefined)form.append("bgImageFile", bgImageFile.current.files[0]);
        const res = await makeRequest.post(endpoints["register"], form);
        toast.success('Register Successfull!', {
          position: "top-right",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "colored",
          });
          
        navigate("/login");
      } catch(error){
        console.log(error)
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
          <form onSubmit={handleClick}>
            <input
              type="text"
              placeholder="Username"
              name="username"
              onChange={handleChange} required={true}
            />
            <input
              type="email"
              placeholder="Email"
              name="email"
              onChange={handleChange}
              required={true}
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
              ref={avatarFile} 
            />
            <span>Background</span>
            <input
              type="file"
              name="bgImageFile"
              accept="image/*"
              ref={bgImageFile}
            />
            <button type="Submit" >Register</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Register;
