import Post from "../post/Post";
import "./posts.scss";
import { useQuery } from "@tanstack/react-query";
import { endpoints, makeAuthRequest, makeRequest } from "../../axios";
import axios from "axios";
import cookie from 'react-cookies';
const Posts = ({userId}) => {
  const { isLoading, error, data } = useQuery(["posts"], () =>
  makeAuthRequest().get(endpoints[userId===null?"get-all-post":"timeline"](userId)).then((res) => {
      return res.data;
    })
  );

  return (
    <div className="posts">
      {error
        ? "Something went wrong!"
        : isLoading
        ? "loading"
        : data.length==0? "No post":data.map((post) => <Post post={post} key={post.id} />)}
    </div>
  );
};

export default Posts;
