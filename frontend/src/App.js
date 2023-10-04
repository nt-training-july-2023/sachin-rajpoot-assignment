import Login from "./Components/Login/Login";
import { Routes, Route } from "react-router-dom";
import Navbar from "./Components/Navbar/Navbar";
import UserRegistration from "./Components/NewUser/UserRegistration";
import NewTicket from "./Components/NewTicket/NewTicket";
import TicketTable from "./Components/Tables/TicketTable";
import ChangePassword from "./Components/ChangePassword/ChangePassword";
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
  const role = user ? user.role : "GUEST";
  const isFirstLogin = user ? user.isFirstLogin : false;

  return (
    <div className="App">
      {isFirstLogin === false && isLoggedIn && (
        <Navbar setIsLoggedIn={setIsLoggedIn} />
      )}

      <Routes>
        {isLoggedIn === false && (
          <Route path="/" element={<Login setIsLoggedIn={setIsLoggedIn} />} />
        )}

        {isFirstLogin === true ? (
          <Route
            path="/changepassword"
            element={<ChangePassword setIsLoggedIn={setIsLoggedIn} />}
          />
        ) : (
          <>
            {isLoggedIn ? (
              <>
                {role === "ADMIN" && (
                  <>
                    <Route path="/newuser" element={<UserRegistration />} />
                    <Route path="/newticket" element={<NewTicket />} />
                    <Route path="/userTable" element={<UserTable />} />
                    <Route
                      path="/departmenttable"
                      element={<DepartmentTable />}
                    />
                    <Route path="/tickettable" element={<TicketTable />} />
                    <Route
                      path="/changepassword"
                      element={<ChangePassword setIsLoggedIn={setIsLoggedIn} />}
                    />
                  </>
                )}
                {role === "ADMIN" ||
                  (role === "USER" && (
                    <>
                      <Route path="/tickettable" element={<TicketTable />} />
                      <Route path="/newticket" element={<NewTicket />} />
                      <Route
                        path="/changepassword"
                        element={
                          <ChangePassword setIsLoggedIn={setIsLoggedIn} />
                        }
                      />
                    </>
                  ))}
              </>
            ) : (
              <Route
                path="/login"
                element={<Login setIsLoggedIn={setIsLoggedIn} />}
              />
            )}
          </>
        )}
        {/* Catch-all route for unauthorized access */}
        <Route
          path="*"
          element={<div>NOT FOUND.</div>}
        />
      </Routes>
    </div>
  );
}

export default App;
