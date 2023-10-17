import { createContext, useEffect, useState } from "react";
import { endpoints, makeAuthRequest, makeRequest } from "../axios";
import cookie from "react-cookies";
import { auth, db } from "../firebase";
import { collection, doc, getDocs, query, setDoc, where } from "firebase/firestore";
import { toast } from "react-toastify";
export const AuthContext = createContext();

export const AuthContextProvider = ({ children }) => {
  const [currentUser, setCurrentUser] = useState(
    cookie.load("user") || null
  );
  const login = async (inputs,isLoginGG) => {
      const res = (!isLoginGG) ? await makeRequest.post(endpoints["login"], inputs):
      await makeRequest.post(endpoints["login-google"], inputs);
      cookie.save("token",res.data);
      const data = await makeAuthRequest().get(endpoints["current-user"]);
      cookie.save("user",data.data)
      const usersCollection = collection(db, 'users');
      const userQuery = query(usersCollection, where('username', '==',  cookie.load("user").username));
      const existingUsers = await getDocs(userQuery);
      if (existingUsers.empty){
        await setDoc(doc(db, "users",  cookie.load("user").username), {
          id:  cookie.load("user").id,
          username: cookie.load("user").username,
          fullName: cookie.load("user").fullName,
          email:  cookie.load("user").email,
          image:  cookie.load("user").image,
        });
        //create empty user chats on firestore
        await setDoc(doc(db, "userChats",  cookie.load("user").username), {});
      }
      setCurrentUser(data.data)
  };
   const logout = () => {
      cookie.remove("token")
      setCurrentUser(null);
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
