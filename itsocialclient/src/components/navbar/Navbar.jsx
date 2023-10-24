import "./navbar.scss";
import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import DarkModeOutlinedIcon from "@mui/icons-material/DarkModeOutlined";
import WbSunnyOutlinedIcon from "@mui/icons-material/WbSunnyOutlined";
import EmailOutlinedIcon from "@mui/icons-material/EmailOutlined";
import SearchOutlinedIcon from "@mui/icons-material/SearchOutlined";
import { Link, useNavigate } from "react-router-dom";
import { useContext } from "react";
import { DarkModeContext } from "../../context/darkModeContext";
import { AuthContext } from "../../context/authContext";
import { Chat, Logout, Message } from "@mui/icons-material";

const Navbar = () => {
  const { toggle, darkMode } = useContext(DarkModeContext);
  const { currentUser } = useContext(AuthContext);
  const navigate =useNavigate()
  const {logout} = useContext(AuthContext)
  return (
    <div className="navbar">
      <div className="left">
        <Link to="/" style={{ textDecoration: "none", color: "inherit"}}>
          <span>IT Social</span>
        </Link>
        <Link to="/" style={{ textDecoration: "none", color: "inherit" }}>
        <HomeOutlinedIcon />
        </Link>
        {darkMode ? (
          <WbSunnyOutlinedIcon onClick={toggle} />
        ) : (
          <DarkModeOutlinedIcon onClick={toggle} />
        )}
        <div className="search">
          <SearchOutlinedIcon />
          <input type="text" placeholder="Search..." />
        </div>
      </div>
      <div className="right">
        
        <EmailOutlinedIcon />
        <Link to={`/chat`}
                style={{ textDecoration: "none", color: "inherit" }}>
        <Message />
        </Link>
        <Link to={`/profile/${currentUser.id}`}
                style={{ textDecoration: "none", color: "inherit" }}>
        <div className="user">
          <img
            src={ currentUser.image}
            alt=""
          />
          <span>{currentUser.fullName}</span>
        </div>
        </Link>
        <Logout onClick={()=>{logout()
          navigate("/login")} } style={{ textDecoration: "none", color: "inherit" }}/>
      </div>
    </div>
  );
};

export default Navbar;
