import Login from "./pages/login/Login";
import Register from "./pages/register/Register";
import {
  createBrowserRouter,
  RouterProvider,
  Outlet,
  Navigate,
  useLocation,
} from "react-router-dom";
import Navbar from "./components/navbar/Navbar";
import LeftBar from "./components/leftBar/LeftBar";
import RightBar from "./components/rightBar/RightBar";
import Home from "./pages/home/Home";
import Profile from "./pages/profile/Profile";
import "./style.scss";
import "./components/chat/chatstyle.scss";
import { useContext } from "react";
import { DarkModeContext } from "./context/darkModeContext";
import { AuthContext } from "./context/authContext";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import ChatHome from "./components/chat/ChatHome";
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from "react-toastify";
function App() {
  const { currentUser } = useContext(AuthContext);
  const { darkMode } = useContext(DarkModeContext);
  const queryClient = new QueryClient();
  const Layout = () => {
    return (
      <QueryClientProvider client={queryClient}>
        <div className={`theme-${darkMode ? "dark" : "light"}`}>
          <Navbar />
          <div style={{ display: "flex" }}>
            <LeftBar />
            <div style={{ flex: 6 }}>
              <Outlet />
            </div>
            <RightBar />
          </div>
        </div>
      </QueryClientProvider>
    );
  };

const ProtectedRoute = ({ children }) => {
    if (!currentUser) {
      return <Navigate to="/login" />;
    }
    return children;
  };
const AnonymousRoute = ({ children }) => {
    const { state } = useLocation();
    if (!currentUser)
      return children;
    return <Navigate to={state?.redirect || "/"} />;
  }  
  const AdminRoute = ({ children }) => {
    const { state } = useLocation();
    if (currentUser!==null && currentUser.role=="ADMIN")
      return children;
    return <Navigate to={state?.redirect || "/"} />;
  }
  
  const router = createBrowserRouter([
    {
      path: "/",
      element: (
        <ProtectedRoute>
          <Layout />
        </ProtectedRoute>
      ),
      children: [
        {
          path: "/",
          element: <Home />,
        },
        {
          path: "/profile/:id",
          element: <Profile />,
        },
      ],
    },
    {
      path: "/chat",
      element: (
        <ProtectedRoute>
          <ChatHome />
        </ProtectedRoute>
      ),
    },
    {path: "/",
    element: (
      <AnonymousRoute>
        <Outlet />
      </AnonymousRoute>
    ),
    children:[
        {
          path: "/login",
          element: <Login />,
        },
        {
          path: "/register",
          element: <Register />,
        },
      ],
      },
      {
        path: "/admin",
      element: (
        <AdminRoute>
          <Outlet />
        </AdminRoute>
      ),
      children:[
        {
          path: "user",
          element: <Login />,
        },
        {
          path: "stat",
          element: <Register />,
        },
      ],
      },
      
      ],
    );

  return (
    <>
      <RouterProvider router={router} />
      <ToastContainer/>
    </>
  );
}

export default App;
