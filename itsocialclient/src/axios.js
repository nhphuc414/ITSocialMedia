import { Cookie } from "@mui/icons-material";
import axios from "axios";
import cookie from "react-cookies";
const SERVER = "http://localhost:8080";
export const endpoints = {
  "login": `${SERVER}/api/auth/login`,
  "current-user": `${SERVER}/api/user/current-user`,
  "get-user-by-id": (userId) => `${SERVER}/api/user/${userId}`,
  "register": `${SERVER}/api/auth/register`,
  "get-all-post":(userId) => `${SERVER}/api/post/get`,
  "get-reactions-by-post-id":(postId) =>  `${SERVER}/api/post/${postId}/reactions`,
  "get-comments-by-post-id":(postId) =>  `${SERVER}/api/post/${postId}/comments`,
  "add-post": `${SERVER}/api/post/add`,
  "update-post": (postId) => `${SERVER}/api/post/${postId}/update`,
  "delete-post": (postId) => `${SERVER}/api/post/${postId}/delete`,
  "timeline":(userId) => `${SERVER}/api/user/${userId}/timeline`,
  "add-comment": `${SERVER}/api/comment/add`,
  
  "add-post-reaction": (postId) =>`${SERVER}/api/reaction/add?postId=${postId}`,
  "delete-reaction-by-post": (postId) =>`${SERVER}/api/reaction/delete?postId=${postId}`,

  "isfollowing": (followingId)=>`${SERVER}/api/follow/${followingId}/is-following`,
  "follow": `${SERVER}/api/follow/add`,
  "unfollow": (followingId) => `${SERVER}/api/follow/${followingId}/delete`,
  "get-all-following" : `${SERVER}/api/follow/get-all-following`,
}
export const makeRequest = axios.create({
  baseURL: "http://localhost:8080",
});
export const makeAuthRequest = () => {
  return axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Authorization": `Bearer ${cookie.load("token")}`,
  }
})
}