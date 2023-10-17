import "./share.scss";
import { useContext, useState } from "react";
import { AuthContext } from "../../context/authContext";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { endpoints, makeAuthRequest, makeRequest } from "../../axios";
import { toast } from "react-toastify";
import { Image } from "@mui/icons-material";
const Share = () => {
  const [file, setFile] = useState(null);
  const [desc, setDesc] = useState("");


  const { currentUser } = useContext(AuthContext);

  const queryClient = useQueryClient();

  const mutation = useMutation(
    (form) => {
      return makeAuthRequest().post(endpoints["add-post"], form);
    },
    {
      onSuccess: () => {
        // Invalidate and refetch
        toast.success('Add post success!', {
          position: "top-center",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "colored",
          });
        queryClient.invalidateQueries(["posts"]);
      },
      onError: ()=>{
        toast.error('Fail to add post', {
          position: "top-center",
          autoClose: 3000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "colored",
          });
      }
    }
  );

  const handleClick = async (e) => {
    e.preventDefault();
    let form = new FormData();
    form.append("userId",currentUser.id);
    form.append("content",desc);
    if (file!==null) form.append("imageFile",file);
    mutation.mutate(form);
    setDesc("");
    setFile(null);
  };

  return (
    <div className="share">
      <div className="container">
        <div className="top">
          <div className="left">
            <img src={currentUser.image} alt="" />
            <input
              type="text"
              placeholder={`What's on your mind ${currentUser.fullName}?`}
              onChange={(e) => setDesc(e.target.value)}
              value={desc}
            />
          </div>
          <div className="right">
            {file && (
              <img className="file" alt="" src={URL.createObjectURL(file)} />
            )}
          </div>
        </div>
        <hr />
        <div className="bottom">
          <div className="left">
            <input
              type="file"
              id="file"
              accept="image/*"
              style={{ display: "none" }}
              onChange={(e) => setFile(e.target.files[0])}
            />
            <label htmlFor="file">
              <div className="item">
                <Image/>
                <span>Add Image</span>
              </div>
            </label>
            
            
          </div>
          <div className="right">
            <button onClick={handleClick}>Share</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Share;
