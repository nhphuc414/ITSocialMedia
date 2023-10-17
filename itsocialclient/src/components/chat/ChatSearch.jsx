import React, { useContext, useState } from "react";
import {
  collection,
  query,
  where,
  getDocs,
  setDoc,
  doc,
  updateDoc,
  serverTimestamp,
  getDoc,
} from "firebase/firestore";
import { db} from "../../firebase";
import { AuthContext } from "../../context/authContext";

const ChatSearch = () => {
  const [email, setEmail] = useState("");
  const [user, setUser] = useState(null);
  const [err, setErr] = useState(false);

  const { currentUser } = useContext(AuthContext);

  const handleSearch = async () => {
    const q = query(
      collection(db, "users"),
      where("email", "==", email)
    );

    try {
      const querySnapshot = await getDocs(q);
      querySnapshot.forEach((doc) => {
        setUser(doc.data());
      });
    } catch (err) {
      setErr(true);
    }
  };

  const handleKey = (e) => {
    e.code === "Enter" && handleSearch();
  };

  const handleSelect = async () => {
    //check whether the group(chats in firestore) exists, if not create
    const combinedId =
      currentUser.username > user.username
        ? currentUser.username + user.username
        : user.username + currentUser.username;
    try {
      const res = await getDoc(doc(db, "chats", combinedId));

      if (!res.exists()) {
        //create a chat in chats collection
        await setDoc(doc(db, "chats", combinedId), { messages: [] });

        //create user chats
        await updateDoc(doc(db, "userChats", currentUser.username), {
          [combinedId + ".userInfo"]: {
            id: user.id,
            username:user.username,
            fullName: user.fullName,
            image: user.image,
          },
          [combinedId + ".date"]: serverTimestamp(),
        });

        await updateDoc(doc(db, "userChats", user.username), {
          [combinedId + ".userInfo"]: {
            id: currentUser.id,
            username:currentUser.username,
            fullName: currentUser.fullName,
            image: currentUser.image,
          },
          [combinedId + ".date"]: serverTimestamp(),
        });
      }
    } catch (err) {}

    setUser(null);
    setEmail("");
  };
  return (
    <div className="chat-search">
      <div className="chat-searchForm">
        <input
          type="text"
          placeholder="Find a user"
          onKeyDown={handleKey}
          onChange={(e) => setEmail(e.target.value)}
          value={email}
        />
      </div>
      {err && <span>User not found!</span>}
      {user && (
        <div className="chat-userChat" onClick={handleSelect}>
          <img src={user.image} alt="" />
          <div className="chat-userChatInfo">
            <span>{user.fullName}</span>
          </div>
        </div>
      )}
    </div>
  );
};

export default ChatSearch;
