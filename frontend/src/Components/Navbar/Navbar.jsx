import React, { useState } from "react";
import { Link, useLocation } from "react-router-dom";
import "./Navbar.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import PopUp from "../PopUp/PopUp";

function Navbar({ setIsLoggedIn }) {
  const [modal, setModal] = useState(false);
  const navigate = useNavigate();
  const role = JSON.parse(localStorage.getItem("member"))?.role;
  const memberEmail = JSON.parse(localStorage.getItem("member"))?.email;
  const memberPassword = JSON.parse(localStorage.getItem("memberPassword"));
  const memberName = JSON.parse(localStorage.getItem("member"))?.name;
  const memberRole = JSON.parse(localStorage.getItem("member"))?.role;
  const memberDept = JSON.parse(localStorage.getItem("member"))?.departmentName;
  const memberId = JSON.parse(localStorage.getItem("member"))?.memberId;

  const location = useLocation();

  // Define a function to determine if a link is active
  const isActive = (path) => {
    if (location.pathname === path) return true;
    else return false;
  };

  // TOGGLES THE MODAL FOR CREATE NEW DEPARTMENT
  const toggleModal = () => {
    setModal(!modal);
  };
  if (modal) {
    document.body.classList.add("active-modal");
  } else {
    document.body.classList.remove("active-modal");
  }

  // LOGOUT BUTTON HANDLER
  const handleLogOut = () => {
    localStorage.clear();
    setIsLoggedIn(false);
    navigate("/login");
  };

  return (
    <>
      <div className="nav_bar">
        {/* NAV LOGO  */}
        <div className="logo">
          <Link to={"/tickettable"} className="link">
            GMS
          </Link>
        </div>

        {/* NAV LINKS  */}
        <div className="nav_links topnav" id="myTopnav">
          {/* DEPARTMENT BUTTON  */}
          {role === "ADMIN" && (
            <div className="dropdown">
              <Link
                to={"departmenttable"}
                className={`dropbtn ${
                  isActive("/departmenttable") ? "active-link" : ""
                }`}
              >
                Department
              </Link>
            </div>
          )}

          {/* USER BUTTON  */}
          {role === "ADMIN" && (
            <div className="dropdown">
              <button
                className={`dropbtn ${
                  isActive("/usertable") || isActive("/newuser")
                    ? "active-link"
                    : ""
                }`}
              >
                User
              </button>
              <div className="dropdown-content">
                <Link to={"usertable"} className="link">
                  User Table
                </Link>
                <Link to={"newuser"} className="link">
                  Add User
                </Link>
              </div>
            </div>
          )}

          {/* TICKET BUTTON  */}
          <div className="dropdown">
            <button
              className={`dropbtn ${
                isActive("/tickettable") || isActive("/newticket")
                  ? "active-link"
                  : ""
              }`}
            >
              Ticket
            </button>
            <div className="dropdown-content">
              <Link to={"tickettable"} className="link">
                Ticket Table
              </Link>

              <Link to={"newticket"} className="link">
                Add Ticket
              </Link>
            </div>
          </div>

          {/* PROFILE BUTTON  */}
          <div className="dropdown">
            <button
              className={`dropbtn ${
                isActive("/changepassword") ? "active-link" : ""
              }`}
            >
              Profile
            </button>
            <div className="dropdown-content">
              <a href="#" onClick={toggleModal} className="dept-btn-modal">
                Profile
              </a>
              {modal && (
                <div className="dept-modal">
                  <div onClick={toggleModal} className="overlay"></div>
                  <div className="dept-modal-content">
                    <h2 style={{ textDecoration: "none" }}>
                      EMP ID : {memberId}
                    </h2>
                    <h2 style={{ textDecoration: "none" }}>
                      Name : {memberName}
                    </h2>
                    <h2 style={{ textDecoration: "none" }}>
                      Role : {memberRole}
                    </h2>
                    <h2 style={{ textDecoration: "none" }}>
                      Email : {memberEmail}
                    </h2>
                    <h2 style={{ textDecoration: "none" }}>
                      Department : {memberDept}
                    </h2>
                    <button className="dept-close-modal" onClick={toggleModal}>
                      CLOSE
                    </button>
                  </div>
                </div>
              )}
              <Link to="/changepassword" className="link">
                Change Password
              </Link>

              <a href="#" className="link" onClick={handleLogOut}>
                logout
              </a>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default Navbar;
