import { GoogleLogin, GoogleOAuthProvider } from "@react-oauth/google";
import jwt_decode from "jwt-decode";
import { useNavigate } from "react-router-dom";
import { endpoints, makeRequest } from "../../axios";
import { AuthContext } from "../../context/authContext";
import { useContext } from "react";
const LoginGoogleBtn = () => {
    const { login } = useContext(AuthContext);
    const navigate = useNavigate()
    const handleLoginGoogle = async (credential) => {
        const data = {
          username:credential.email,
          password: process.env.REACT_APP_PASSWORD,
          confirmPassword: process.env.REACT_APP_PASSWORD,
          fullName: credential.family_name+credential.given_name,
          email: credential.email,
          avatar: credential.picture,
        };
        try {
            let form = new FormData()
            for (let field in data)
            form.append(field, data[field]);
            await login(form,true)
            navigate("/")
          } catch (error) {
            console.log(error.body)
            navigate("/")
          }
      };
    return (
        <>
        <GoogleOAuthProvider clientId={1052482945968-r7a44l95g2h8a6b1nl2utq39d0cmt63u.apps.googleusercontent.com}>
            <GoogleLogin 
                onSuccess={(res) => {
                    handleLoginGoogle(jwt_decode(res.credential))
                }}
                onError={() => {
                    console.log("Login Failed");
                }}
            />
        </GoogleOAuthProvider>
        </>
    )
}
export default LoginGoogleBtn;