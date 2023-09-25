import Login from "./Components/Login/Login";
import { Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./Components/Navbar/Navbar";
import Home from "./Components/Home/Home";
import UserRegistration from "./Components/NewUser/UserRegistration";
import NewTicket from "./Components/NewTicket/NewTicket";
import TicketTable from "./Components/Tables/TicketTable";
import ChangePassword from "./Components/ChangePassword/ChangePassword";
import Profile from "./Components/Profile";
import UserTable from "./Components/Tables/UserTable";
import DepartmentTable from "./Components/Tables/DepartmentTable";
import { useEffect, useState } from "react";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("member"));
    setIsLoggedIn(user !== null);
  }, []);

  const user = JSON.parse(localStorage.getItem("member"));
  // const role = user ? user.role : "GUEST";
  const role = JSON.parse(localStorage.getItem("member"))?.role;

  return (
    <div className="App">
      {JSON.parse(localStorage.getItem("member")) !== null ? (
        <Navbar setIsLoggedIn={setIsLoggedIn} />
      ) : (
        <Navigate to="/login" />
      )}

      <Routes>
        {isLoggedIn ? (
          <>
            {role === "ADMIN" && (
              <>
                <Route path="/newuser" element={<UserRegistration />} />
                <Route path="/newticket" element={<NewTicket />} />
                <Route path="/userTable" element={<UserTable />} />
                <Route path="/departmenttable" element={<DepartmentTable />} />
                <Route path="/tickettable" element={<TicketTable />} />
                <Route
                  path="/changepassword"
                  element={<ChangePassword setIsLoggedIn={setIsLoggedIn} />}
                />
              </>
            )}
            {role === "ADMIN" || role === "USER" && (
              <>
                <Route path="/tickettable" element={<TicketTable />} />
                <Route path="/newticket" element={<NewTicket />} />
                <Route path="/changepassword" element={<ChangePassword setIsLoggedIn={setIsLoggedIn} />}/>
              </>
            )}
          </>
        ) : (
          <Route path="/login" element={<Login setIsLoggedIn={setIsLoggedIn} />} />
        )}

        {/* Catch-all route for unauthorized access */}
        <Route
          path="*"
          element={<div>You are not authorized to access this page.</div>}
        />
      </Routes>
    </div>
  );
}

export default App;



