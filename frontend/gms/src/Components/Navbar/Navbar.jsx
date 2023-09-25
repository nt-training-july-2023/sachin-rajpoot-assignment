import React, { useState } from "react";
import { Link } from "react-router-dom";
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

  const role = JSON.parse(localStorage.getItem("member"))?.role;
  const memberEmail = JSON.parse(localStorage.getItem("member"))?.email;
  const memberPassword = JSON.parse(localStorage.getItem("memberPassword"));
  const memberName = JSON.parse(localStorage.getItem("member"))?.name;
  const memberRole = JSON.parse(localStorage.getItem("member"))?.role;
  const memberDept = JSON.parse(localStorage.getItem("member"))?.departmentName;

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
  };

  // NEW DEPARTMENT FORM SUBMIT
  const handleDepartmentFormSubmit = (event) => {
    event.preventDefault();
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
        if (response.data.success === false) {
          // setError(true);
        }
        setModal2(true);
        console.log(department);
        console.log("Form submitted successfully!");
        // navigate("/");
        console.log(response.data);
        // alert(response.data.message);
      })
      .catch((err) => {
        setError(true);
        console.log(err);
        if (err?.response?.data?.message) {
          // alert(err.response.data.message);
        }
      });
  };

  return (
    <div className="nav_bar">
      {/* NAV LOGO  */}
      <div className="logo">
        <p>GMS</p>
      </div>

      <div className="nav_links topnav" id="myTopnav">
        {/* HOME BUTTON  */}
        {/* <div className="nav_link active">
          <Link to="/" className="link">
            Home
          </Link>
        </div> */}

        {/* DEPARTMENT BUTTON  */}
        {role === "ADMIN" && (
          <div className="dropdown">
            <button className="dropbtn">Department</button>
            <div className="dropdown-content">
              <Link to={"departmenttable"} className="link">
                Department Table
              </Link>

              <a href="#" onClick={toggleModal} className="dept-btn-modal">
                New Department
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
                        onChange={handleDepartmentNameChange}
                        required
                      />
                      <input
                        type="submit"
                        value="Submit"
                        className="dept-form-submit-btn"
                      />
                    </form>
                    <button className="dept-close-modal" onClick={toggleModal}>
                      CLOSE
                    </button>
                  </div>

                  {/* DISPLAY SUCCESS OR ERROR MESSAGE */}
                  {/* {modal2 && (
                    <div className="dept-modal">
                      <div className="dept-modal-content">
                        {!error && <h2>Department Created Successfully</h2>}
                        {error && (
                          <h2>
                            THIS FIELD ALREADY EXISTS,
                            <br /> CHECK FOR UNIQUE FIELDS
                          </h2>
                        )}
                        <button
                          className="dept-close-modal"
                          onClick={toggleModal2}
                        >
                          CLOSE
                        </button>
                      </div>
                    </div>
                  )} */}

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
                </div>
              )}
            </div>
          </div>
        )}

        {/* USER BUTTON  */}
        {role === "ADMIN" && (
          <div className="dropdown">
            <button className="dropbtn">User</button>
            <div className="dropdown-content">
              <Link to={"usertable"} className="link">
                User Table
              </Link>
              <Link to={"newuser"} className="link">
                Add new user
              </Link>
            </div>
          </div>
        )}

        {/* TICKET BUTTON  */}
        <div className="dropdown">
          <button className="dropbtn">Ticket</button>
          <div className="dropdown-content">
            <Link to={"tickettable"} className="link">
              Ticket Table
            </Link>

            <Link to={"newticket"} className="link">
              Add new ticket
            </Link>
          </div>
        </div>

        {/* PROFILE BUTTON  */}
        <div className="dropdown">
          <button className="dropbtn">Profile</button>
          <div className="dropdown-content">
            <a href="#" onClick={toggleModal} className="dept-btn-modal">
              Profile
            </a>
            {modal && (
              <div className="dept-modal">
                <div onClick={toggleModal} className="overlay"></div>
                <div className="dept-modal-content">
                  <h2>Name : {memberName}</h2>
                  <h2>Role : {memberRole}</h2>
                  <h2>Email : {memberEmail}</h2>
                  <h2>Department : {memberDept}</h2>
                  <button className="dept-close-modal" onClick={toggleModal}>
                    CLOSE
                  </button>
                </div>
              </div>
            )}
            <Link to="/changepassword" className="link">
              Change Password
            </Link>
            <a href="#">Link 3</a>
          </div>
        </div>

        {/* LOGOUT BUTTON  */}
        <div className="nav_link">
          <button className="link" onClick={handleLogOut}>
            logout
          </button>
        </div>
      </div>
    </div>
  );
}

export default Navbar;
