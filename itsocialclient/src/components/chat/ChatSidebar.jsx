import ChatSearch from "./ChatSearch";
import Chats from "./Chats"
import ChatNavBar from "./ChatNavBar";
const ChatSidebar = () => {
  return (
    <div className="chat-sidebar">
      <ChatNavBar />
      <ChatSearch/>
      <Chats/>
    </div>
  );
};

export default ChatSidebar;
