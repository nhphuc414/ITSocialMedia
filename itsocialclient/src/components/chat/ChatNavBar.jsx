import React, { useContext } from 'react'
import { AuthContext } from '../../context/authContext'
import { useNavigate } from 'react-router-dom'
const ChatNavBar = () => {
  const {currentUser} = useContext(AuthContext)
  const navigate = useNavigate()
  return (
    <div className='chat-navbar'>
      <span className="chat-logo">Chat</span>
      <div className="chat-user">
      <img src={currentUser.image} alt="" />
        <span>{currentUser.fullName}</span>
        <button onClick={()=>navigate("/")}>Home</button>
      </div>
    </div>
  )
}

export default ChatNavBar