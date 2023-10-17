import React, { useContext, useEffect, useRef } from "react";
import { AuthContext } from "../../context/authContext";
import { ChatContext } from "../../context/ChatContext";
import moment from "moment";
const Message = ({ message }) => {
  const { currentUser } = useContext(AuthContext);
  const { data } = useContext(ChatContext);

  const ref = useRef();

  useEffect(() => {
    ref.current?.scrollIntoView({ behavior: "smooth" });
  }, [message]);

  return (
    <div
      ref={ref}
      className={`chat-message ${message.senderId === currentUser.username && "chat-owner"}`}
    >
      <div className="chat-messageInfo">
      <img
          src={
            message.senderId === currentUser.username
              ? currentUser.image
              : data.user.image
          }
          alt=""
        />
      </div>
      <div className="chat-messageContent">
        <p>{message.text}</p>
        {message.img && <img src={message.img} alt="" />}
      </div>
    </div>
  );
};

export default Message;
