import { useContext, useState } from "react";
import "./comments.scss";
import { AuthContext } from "../../context/authContext";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { endpoints, makeAuthRequest, makeRequest } from "../../axios";
import moment from "moment";

const Comments = ({ postId },{postStatus}) => {
  const [desc, setDesc] = useState("");
  const { currentUser } = useContext(AuthContext);

  const { isLoading, error, data } = useQuery(["comments"], () =>
    makeRequest.get(endpoints["get-comments-by-post-id"](postId)).then((res) => {
      return res.data.data;
    })
  );

  const queryClient = useQueryClient();
  const mutation = useMutation(
    (newComment) => {
      let form = new FormData();
      form.append("postId",newComment.postId);
      form.append("content",newComment.desc);
      console.log(form);
      return makeAuthRequest().post("http://localhost:8080/api/comment/add", form);
    },
    {
      onSuccess: () => {
        // Invalidate and refetch
        queryClient.invalidateQueries(["comments"]);
      },
    }
  );

  const handleClick = async (e) => {
    e.preventDefault();
    mutation.mutate({ desc, postId });
    setDesc("");
  };

  return (
    <div className="comments">
      <div className="write">
        <img src={currentUser.image} alt="" />
        <input
          type="text"
          placeholder="write a comment"
          value={desc}
          onChange={(e) => setDesc(e.target.value)}
        />
        <button onClick={handleClick}>Send</button>
      </div>
      {error
        ? "Something went wrong"
        : isLoading
        ? "loading"
        : data.map((comment) => (
            <div className="comment">
              <img src={comment.user.image} alt="" />
              <div className="info">
                <span>{comment.user.fullName}</span>
                <p>{comment.content}</p>
              </div>
              <span className="date">
                {moment(comment.createdDate).fromNow()}
              </span>
            </div>
          ))}
    </div>
  );
};

export default Comments;
