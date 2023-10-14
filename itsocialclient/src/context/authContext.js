import axios from "axios";
import { createContext, useEffect, useState } from "react";
import { endpoints, makeAuthRequest, makeRequest } from "../axios";

export const AuthContext = createContext();

export const AuthContextProvider = ({ children }) => {
  const [currentUser, setCurrentUser] = useState(
    localStorage.getItem("user") || null
  );

  const login = async (inputs) => {
    const res = await makeRequest.post(endpoints["login"], inputs);
    localStorage.setItem("token",res.data);
    console.log(localStorage.getItem("token"));
    let data =  await makeAuthRequest.get(endpoints["current-user"]);
    setCurrentUser(data.data)
  };

  useEffect(() => {
    localStorage.setItem("user", currentUser);
  }, [currentUser]);

  return (
    <AuthContext.Provider value={{ currentUser, login }}>
      {children}
    </AuthContext.Provider>
  );
};
