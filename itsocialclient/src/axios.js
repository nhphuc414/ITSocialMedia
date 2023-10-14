import axios from "axios";
const SERVER = "http://localhost:8080";
export const endpoints = {
  "login": `${SERVER}/api/auth/login`,
  "current-user": `${SERVER}/api/user/current-user`,
  "register": `${SERVER}/api/auth/register`,
  "get-all-post":`${SERVER}/api/post/get`,
  "get-reactions-by-post-id":(postId) =>  `${SERVER}/api/post/${postId}/reactions`,
  "get-comments-by-post-id":(postId) =>  `${SERVER}/api/post/${postId}/comments`,
  "add-post": `${SERVER}/api/post/add`,
}
export const makeRequest = axios.create({
  baseURL: "http://localhost:8080",
});
export const makeAuthRequest = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Authorization": "Bearer "+ localStorage.getItem("token"),
  }
});