import React, { useState } from "react";
import { Link, useLocation } from "react-router-dom";
import "./Navbar.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import PopUp from "../PopUp/PopUp";

function Navbar({ setIsLoggedIn }) {
  const [modal, setModal] = useState(false);
  const [modal2, setModal2] = useState(false);
  const [error, setError] = useState(false);
  const navigate = useNavigate();
  const [department, setDepartment] = useState();
  const [departmentNameError, setDepartmentNameError] = useState("");

  const role = JSON.parse(localStorage.getItem("member"))?.role;
  const memberEmail = JSON.parse(localStorage.getItem("member"))?.email;
  const memberPassword = JSON.parse(localStorage.getItem("memberPassword"));
  const memberName = JSON.parse(localStorage.getItem("member"))?.name;
  const memberRole = JSON.parse(localStorage.getItem("member"))?.role;
  const memberDept = JSON.parse(localStorage.getItem("member"))?.departmentName;

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

  // TOGGLES THE FAIL MODAL -> POP UP
  const toggleError = () => {
    setError(!error);
  };

  // TOGGLE MODAL2 -> ERROR MESSAGE
  const toggleModal2 = () => {
    setModal2(!modal2);
  };
  if (modal2) {
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

  // ON CHANGE -> CREATE NEW DEPARTMENT
  const handleDepartmentNameChange = (e) => {
    setDepartment(e.target.value);
    let deptName = e.target.value;
    if (deptName.trim() === "") {
      setDepartmentNameError("name can not be empty.");
    } else if (deptName.trim().length < 2 || deptName.trim().length > 20) {
      setDepartmentNameError(
        "Name must be minimum 2 and maximum 20 characters long."
      );
    } else {
      setDepartmentNameError("");
    }
  };

  // NEW DEPARTMENT FORM SUBMIT
  const handleDepartmentFormSubmit = async (event) => {
    event.preventDefault();
    if(departmentNameError !== "") {
      return
    }
    const headers = {
      email: memberEmail,
      password: memberPassword,
    };
    const config = {
      headers: headers,
    };
    let departmentObj = {
      departmentName: "",
    };
    departmentObj.departmentName = department;
    axios
      .post(
        "http://localhost:8080/api/department/create/auth",
        departmentObj,
        config
      )
      .then((response) => {
        setModal(false);
        setModal2(true);
      })
      .catch((err) => {
        setModal(false);
        setError(true);
      });
  };

  return (
    <>
      {/* POP UP ON SUCCESS  */}
      {modal2 && (
        <PopUp
          openPopup={modal2}
          customHeading="Created Successfully"
          customImageSrc="404-Tick.png"
          toggleModal={toggleModal2}
        />
      )}

      {/* POP UP ON FAIL  */}
      {error && (
        <PopUp
          openPopup={error}
          customHeading="Department Already Exists"
          customImageSrc="fail.png"
          toggleError={toggleError}
        />
      )}

      <div className="nav_bar">
        {/* NAV LOGO  */}
        <div className="logo">
          <Link to={"/tickettable"} className="link">
            GMS
          </Link>
        </div>

        <div className="nav_links topnav" id="myTopnav">
          {/* DEPARTMENT BUTTON  */}
          {role === "ADMIN" && (
            <div className="dropdown">
              <button
                className={`dropbtn ${
                  isActive("/departmenttable") ? "active-link" : ""
                }`}
              >
                Department
              </button>
              <div className="dropdown-content">
                <Link to={"departmenttable"} className="link">
                  Department Table
                </Link>

                <a href="#" onClick={toggleModal} className="dept-btn-modal">
                  Add Department
                </a>
                {modal && (
                  <div className="dept-modal">
                    <div onClick={toggleModal} className="overlay"></div>
                    <div className="dept-modal-content">
                      <h2>Add New Department</h2>
                      <form action="" onSubmit={handleDepartmentFormSubmit}>
                        <label htmlFor="department">Department Name :</label>
                        <input
                          type="text"
                          placeholder="Enter Department Name"
                          onChange={(e) => handleDepartmentNameChange(e)}
                          required
                        />
                        {departmentNameError && (
                          <span className="error-message">
                            {departmentNameError}
                          </span>
                        )}
                        <input
                          type="submit"
                          value="ADD"
                          className="dept-form-submit-btn"
                        />
                      </form>
                      <button
                        className="dept-close-modal"
                        onClick={toggleModal}
                      >
                        CLOSE
                      </button>
                    </div>
                  </div>
                )}
              </div>
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

          {/* LOGOUT BUTTON  */}
          {/* <div className="nav_link">
            <button className="link" onClick={handleLogOut}>
              logout
            </button>
          </div> */}
        </div>
      </div>
    </>
  );
}

export default Navbar;
