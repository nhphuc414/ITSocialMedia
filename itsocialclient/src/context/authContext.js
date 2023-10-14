import axios from "axios";
import { createContext, useEffect, useState } from "react";
import { endpoints, makeAuthRequest, makeRequest } from "../axios";
import cookie from "react-cookies";
import { useNavigate } from "react-router-dom";
export const AuthContext = createContext();

export const AuthContextProvider = ({ children }) => {
  const [currentUser, setCurrentUser] = useState(
    cookie.load("user") || null
  );
  const login = async (inputs) => {
      const res = await makeRequest.post(endpoints["login"], inputs);
      console.log(res.data)
      cookie.save("token",res.data);
      const data = await makeAuthRequest().get(endpoints["current-user"]);
      cookie.save("user",data.data)
      setCurrentUser(data.data)
  };
   const logout = () => {
      cookie.remove("token")
      setCurrentUser({});
      cookie.remove("user");
  };  
  useEffect(() => {
    if (currentUser!==null) cookie.save("user",currentUser)
  }, [currentUser]);
  return (
    <AuthContext.Provider value={{ currentUser, login, logout}}>
      {children}
    </AuthContext.Provider>
  );
};
