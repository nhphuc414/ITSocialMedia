import Chat from './Chat';
import ChatSidebar from './ChatSidebar'
const ChatHome = () => {
  return (
    <div className='chat-home'>
      <div className="chat-container">
        <ChatSidebar/>
        <Chat/>
      </div>
    </div>
  )
}

export default ChatHome