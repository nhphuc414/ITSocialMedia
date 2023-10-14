import "./post.scss";
import FavoriteBorderOutlinedIcon from "@mui/icons-material/FavoriteBorderOutlined";
import FavoriteOutlinedIcon from "@mui/icons-material/FavoriteOutlined";
import TextsmsOutlinedIcon from "@mui/icons-material/TextsmsOutlined";
import ShareOutlinedIcon from "@mui/icons-material/ShareOutlined";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import { Link } from "react-router-dom";
import Comments from "../comments/Comments";
import { useState } from "react";
import moment from "moment";
import { useQuery, useQueryClient, useMutation } from "@tanstack/react-query";
import { endpoints, makeAuthRequest, makeRequest } from "../../axios";
import { useContext } from "react";
import { AuthContext } from "../../context/authContext";

const Post = ({ post }) => {
  const [commentOpen, setCommentOpen] = useState(false);
  const [menuOpen, setMenuOpen] = useState(false);

  const { currentUser } = useContext(AuthContext);

  const { isLoading, error, data } = useQuery(["likes", post.id], () =>
    makeAuthRequest().get(endpoints["get-reactions-by-post-id"](post.id)).then((res) => {
      return res.data.data;
    })
  );

  const queryClient = useQueryClient();
  function hasUserInArray(dataArray,user) {
    for (const item of dataArray) {
      if (item.user.id===user.id) {
        return true;
      }
    }
    return false;
  }

  const mutation = useMutation(
    (liked) => {
      if (liked) return makeAuthRequest().delete(endpoints['delete-reaction-by-post'](post.id));
      return makeAuthRequest().post(endpoints['add-post-reaction'](post.id));
    },
    {
      onSuccess: () => {
        // Invalidate and refetch
        queryClient.invalidateQueries(["likes"]);
      },
    }
  );
  const deleteMutation = useMutation(
    (postId) => {
      return makeAuthRequest().delete(endpoints['delete-post'](postId));
    },
    {
      onSuccess: () => {
        // Invalidate and refetch
        queryClient.invalidateQueries(["posts"]);
      },
    }
  );

  const handleLike = () => {
    mutation.mutate(hasUserInArray(data,currentUser));
  };

  const handleDelete = () => {
    deleteMutation.mutate(post.id);
  };
  return (
    <div className="post">
      <div className="container">
        <div className="user">
          <div className="userInfo">
            <img src={post.user.image} alt="" />
            <div className="details">
              <Link
                to={`/profile/${post.user.id}`}
                style={{ textDecoration: "none", color: "inherit" }}
              >
                <span className="name">{post.user.fullName}</span>
              </Link>
              <span className="date">{moment(post.createdDate).fromNow()}</span>
            </div>
          </div>
          <MoreHorizIcon onClick={() => setMenuOpen(!menuOpen)} />
          {menuOpen && (post.user.id === currentUser.id|| currentUser.role==="ADMIN" )&& (
            <button onClick={handleDelete}>delete</button>
          )}
        </div>
        <div className="content">
          <p>{post.content}</p>
          <img src={post.image} alt="" />
        </div>
        <div className="info">
          <div className="item">
            {isLoading ? (
              "loading"
            ) : hasUserInArray(data,currentUser) ? (
              <FavoriteOutlinedIcon
                style={{ color: "red" }}
                onClick={handleLike}
              />
            ) : (
              <FavoriteBorderOutlinedIcon onClick={handleLike} />
            )}
            {data?.length} Likes
          </div>
          <div className="item" onClick={() => setCommentOpen(!commentOpen)}>
            <TextsmsOutlinedIcon />
            See Comments
          </div>
          <div className="item">
            <ShareOutlinedIcon />
            Share
          </div>
        </div>
        {commentOpen && <Comments postId={post.id} postStatus = {post.status} />}
      </div>
    </div>
  );
};

export default Post;
