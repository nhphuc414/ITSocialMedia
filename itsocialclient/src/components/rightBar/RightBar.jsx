import { useQuery } from "@tanstack/react-query";
import { endpoints, makeAuthRequest } from "../../axios";
import "./rightBar.scss";

const RightBar = () => {
  return (
    <div className="rightBar">
      <div className="container">
        <div className="item">
          <span>Suggestions For You</span>
        </div>
        <div className="item">
          <span>Latest Activities</span>
        </div>
      </div>
    </div>
  );
};

export default RightBar;
