import "./leftBar.scss";
import Friends from "../../assets/1.png";
import Groups from "../../assets/2.png";
import Messages from "../../assets/10.png";
import { AuthContext } from "../../context/authContext";
import { useContext } from "react";
import { Link } from "react-router-dom";

const LeftBar = () => {

  const { currentUser } = useContext(AuthContext);

  return (
    <div className="leftBar">
      <div className="container">
        <div className="menu">
        <Link
                to={`/profile/${currentUser.id}`}
                style={{ textDecoration: "none", color: "inherit" }}
              >
          <div className="user">
            <img
              src={currentUser.image}
              alt=""
            />
            <span>{currentUser.fullName}</span>
          </div>
          </Link>
          <div className="item">
            <img src={Friends} alt="" />
            <span>Friends</span>
          </div>
          <div className="item">
            <img src={Groups} alt="" />
            <span>Groups</span>
          </div>
        </div>
        <hr />
        <div className="menu">
          <span>Your shortcuts</span>
          <div className="item">
            <img src={Messages} alt="" />
            <span>Messages</span>
          </div>
        </div>
        <hr />
        <div className="menu">
          <span>Others</span>
        </div>
      </div>
    </div>
  );
};

export default LeftBar;
