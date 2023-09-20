import Login from "./Components/Login";
import { Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./Components/Navbar";
import Home from "./Components/Home";
import Footer from "./Components/Footer";
import UserRegistration from "./Components/UserRegistration";
import NewTicket from "./Components/NewTicket";
import TicketTable from "./Components/TicketTable";
import ChangePassword from "./Components/ChangePassword";
import Profile from "./Components/Profile";
import UserTable from "./Components/UserTable";
import DepartmentTable from "./Components/DepartmentTable";
import { useEffect, useState } from "react";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState();

  useEffect(() => {
    setIsLoggedIn(JSON.parse(localStorage.getItem("member")) !== null);
    // console.log("GETTING USER FROM LOCAL STORAGE : "+localStorage.getItem("member"))
  }, []);

  const role = JSON.parse(localStorage.getItem("member"))?.role;
  console.log("IS USER LOGGED IN ? -> "+isLoggedIn);

  return (
    <div className="App">
      {JSON.parse(localStorage.getItem("member")) !== null ? (
        <Navbar setIsLoggedIn={setIsLoggedIn} />
      ) : (
        <Navigate to="/login" />
      )}

      {/* <Login/> */}
      {isLoggedIn ? (
        <Routes>
          <Route path="/newuser" element={<UserRegistration />}></Route>
          <Route path="/" element={<Home />}></Route>
          <Route path="/newticket" element={<NewTicket />}></Route>
          <Route path="/tickettable" element={<TicketTable />}></Route>
          <Route path="/changepassword" element={<ChangePassword />}></Route>
          <Route path="/profile" element={<Profile />}></Route>
          <Route path="/UserTable" element={<UserTable />}></Route>
          <Route path="/Departmenttable" element={<DepartmentTable />}></Route>
        </Routes>
      ) : (
        <Routes>
          <Route
            path="/login"
            element={<Login setIsLoggedIn={setIsLoggedIn} />}
          ></Route>
        </Routes>
      )}

      {/* <Footer/> */}
    </div>
  );
}

export default App;
