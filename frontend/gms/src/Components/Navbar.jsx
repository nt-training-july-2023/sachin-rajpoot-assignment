import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function Navbar({ setIsLoggedIn }) {
  const [modal, setModal] = useState(false);
  const navigate = useNavigate();
  const [department, setDepartment] = useState();
  const role = JSON.parse(localStorage.getItem("member"))?.role;
  const memberEmail = JSON.parse(localStorage.getItem("member"))?.email;

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

  // ON CHANGE -> CREATE NEW DEPARTMENT
  const handleDepartmentNameChange = (e) => {
    setDepartment(e.target.value);
  };

  // NEW DEPARTMENT FORM SUBMIT
  const handleDepartmentFormSubmit = (event) => {
    event.preventDefault();
    const headers = {
      email: memberEmail,
      password: "MTIzNDU2Nzg=",
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
        console.log(department);
        console.log("Form submitted successfully!");
        navigate("/");
        console.log(response.data);
        alert(response.data.message);
      })
      .catch((err) => console.log(err));
  };

  return (
    <div className="nav_bar">
      <div className="logo">
        <p>GMS</p>
      </div>

      <div className="nav_links topnav" id="myTopnav">
        {/* HOME BUTTON  */}
        <div className="nav_link">
          <Link to="/" className="link">
            Home
          </Link>
        </div>

        {/* DEPARTMENT BUTTON  */}
        {role === "ADMIN" && (
          <div className="dropdown">
            <button className="dropbtn">Department</button>
            <div className="dropdown-content">
              <Link to="/departmenttable" className="link">
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
              <Link to="/usertable" className="link">
                User Table
              </Link>
              <Link to="/newuser" className="link">
                Add new user
              </Link>
              <a href="#">Link 3</a>
            </div>
          </div>
        )}

        {/* TICKET BUTTON  */}
        <div className="dropdown">
          <button className="dropbtn">Ticket</button>
          <div className="dropdown-content">
            <Link to="/tickettable" className="link">
              Ticket Table
            </Link>
            <Link to="/newticket" className="link">
              Add new ticket
            </Link>
            <a href="#">Link 3</a>
          </div>
        </div>

        {/* PROFILE BUTTON  */}
        <div className="dropdown">
          <button className="dropbtn">Profile</button>
          <div className="dropdown-content">
            <Link to="/profile" className="link">
              Profile
            </Link>
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
