import React, { useEffect, useState } from "react";
import "./UserRegistration.css";
import { useNavigate } from "react-router-dom";
import axios, { HttpStatusCode } from "axios";
function UserRegistration() {
  const navigate = useNavigate();
  const memberEmail = JSON.parse(localStorage.getItem("member"))?.email;
  let data = {
    name: "",
    email: "",
    password: "",
    isFirstLogin: "",
    role: "",
    department: {
      departmentId: 0,
    },
  };
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [nameError, setNameError] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [isButtonDisabled, setIsButtonDisabled] = useState(true);
  const [departmentData, setDepartmentData] = useState([]);

  useEffect(() => {
    const headers = {
      email: memberEmail,
      password: "dGVzdDEyMw==",
    };
    const config = {
      headers: headers,
    };
    axios
      .get("http://localhost:8080/api/department/getAll/noPage", config)
      .then((response) => {
        setDepartmentData(response.data);
        console.log(response);
      })
      .catch((err) => console.log(err));
  }, []);

  const handleNameChange = (event) => {
    const newName = event.target.value;
    setName(newName);
    setNameError(newName.trim() === "" ? "Name cannot be empty" : "");
    updateButtonStatus();
  };

  const handleEmailChange = (event) => {
    const newEmail = event.target.value;
    setEmail(newEmail);
    const emailRegex = /^[a-zA-Z0-9._-]+@nucleusteq\.com$/;
    setEmailError(
      newEmail.trim() === ""
        ? "Email cannot be empty"
        : !emailRegex.test(newEmail)
        ? "Email must have domain nucleusteq.com"
        : ""
    );
    updateButtonStatus();
  };

  const handlePasswordChange = (event) => {
    const newPassword = event.target.value;
    setPassword(newPassword);
    setPasswordError(
      newPassword.length < 8
        ? "Password must be at least 8 characters long."
        : ""
    );
    updateButtonStatus();
  };

  const updateButtonStatus = () => {
    setIsButtonDisabled(
      name.trim() === "" ||
        email.trim() === "" ||
        password.length < 8 ||
        !email.endsWith("nucleusteq.com")
    );
  };

  const handleUserTypeChange = (e) => {
    data.role = e.target.value;
    console.log(data.role);
  };

  const handleSelectChange = (e) => {
    const { name, value } = e.target;
    console.log(value);
    data = {
      ...data,
      department: {
        [name]: Number(value),
      },
    };
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    data.name = name;
    data.email = email;
    data.password = password;
    data.isFirstLogin = true;
    const headers = {
      email: memberEmail,
      password: "MTIzNDU2Nzg=",
    };
    const config = {
      headers: headers,
    };
    console.log(data);
    console.log("Form submitted successfully!");

    axios
      .post("http://localhost:8080/api/create/nodept", data, config)
      .then((response) => {
        navigate("/");
        console.log(response.data);
      })
      .catch((err) => console.log(err));
  };

  return (
    <div className="user_container">
      <div className="user_registration_form">
        <div className="user_heading">
          <p>Create New User</p>
        </div>

        <form className="user_form" onSubmit={handleSubmit} method="POST">
          <div className={`user_input_area ${nameError ? "error" : ""}`}>
            <label htmlFor="name">Name:</label>
            <input
              type="text"
              id="name"
              name="name"
              placeholder="Enter your name"
              value={name}
              onChange={handleNameChange}
              required
            />
            {nameError && <span className="error-message">{nameError}</span>}
          </div>

          <div className={`user_input_area ${emailError ? "error" : ""}`}>
            <label htmlFor="email">Email:</label>
            <input
              type="email"
              id="email"
              name="email"
              placeholder="Enter your email"
              value={email}
              onChange={handleEmailChange}
              required
            />
            {emailError && <span className="error-message">{emailError}</span>}
          </div>

          <div className={`user_input_area ${passwordError ? "error" : ""}`}>
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              id="password"
              name="password"
              placeholder="Enter your password"
              value={password}
              onChange={handlePasswordChange}
              required
            />
            {passwordError && (
              <span className="error-message">{passwordError}</span>
            )}
          </div>

          <div className="user_input_area">
            <label htmlFor="usertype">User Type:</label>
            <select
              name="usertype"
              id="usertype"
              defaultValue=""
              onChange={handleUserTypeChange}
            >
              <option value="">--- Select ---</option>
              <option value="USER">USER</option>
              <option value="ADMIN">ADMIN</option>
            </select>
          </div>

          {/* <div className="user_input_area">
            <label htmlFor="dept">Department:</label>
            <select name="dept" id="dept" onClick={handleDeptType}>
              <option value="it">IT</option>
              <option value="hr">HR</option>
              <option value="infrastructure">Infrastructure</option>
              <option value="accounts">Accounts</option>
            </select>
          </div> */}

          <div className="user_input_area">
            <label htmlFor="dept">Department:</label>
            <select
              name="departmentId"
              defaultValue=""
              onChange={handleSelectChange}
            >
              <option value="">--Select--</option>
              {departmentData.map((dep) => {
                // console.log(dep)
                return (
                  <option key={dep.departmentId} value={dep.departmentId}>
                    {dep.departmentName}
                  </option>
                );
              })}
            </select>
          </div>

          <div className="user_registration_form_btn">
            <input
              type="submit"
              value="Register"
              // disabled={isButtonDisabled}
              className={isButtonDisabled ? "disabled_button" : ""}
            />
          </div>
        </form>
      </div>
    </div>
  );
}

export default UserRegistration;
