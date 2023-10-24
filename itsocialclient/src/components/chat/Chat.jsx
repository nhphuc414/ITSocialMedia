import React, { useContext } from "react";
import Messages from "./Messages";
import Input from "./Input";
import { ChatContext } from "../../context/ChatContext";
const Chat = () => {
   const { data } = useContext(ChatContext);

  return (
    <div className="chat-chat">
      <div className="chat-chatInfo">
      {data.user?.fullName}
      </div>
      <Messages />
      <Input/>
    </div>
  );
};

export default Chat;
