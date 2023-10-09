import React, { useEffect, useRef, useState } from "react";
import "./UserRegistration.css";
import axios from "axios";
import PopUp from "../PopUp/PopUp";
import InputField from "../InputField/InputField";
import Label from "../Label/Label";
function UserRegistration() {
  // USE STATES
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [userType, setUserType] = useState("");
  const [userDepartment, setUserDepartment] = useState("");
  const [nameError, setNameError] = useState("");
  const [error, setError] = useState(false);
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [departmentData, setDepartmentData] = useState([]);
  const [modal, setModal] = useState(false);
  const formRef = useRef(null);

  // LOCAL STORAGE DATA
  const memberPassword = JSON.parse(localStorage.getItem("memberPassword"));
  const memberEmail = JSON.parse(localStorage.getItem("member"))?.email;

  // GETTIG ALL DEPARTMENT FOR DROP DOWN
  useEffect(() => {
    const headers = {
      email: memberEmail,
      password: memberPassword,
    };
    const config = {
      headers: headers,
    };
    axios
      .get("http://localhost:8080/api/department/getAll/noPage", config)
      .then((response) => {
        setDepartmentData(response.data);
      })
      .catch((err) => console.log(err));
  }, []);

  // HANDLE NAME CHANGE
  const handleNameChange = (event) => {
    const newName = event.target.value;
    setName(newName.trim());
    const usernameregex = /^[A-Za-z]+(?: [A-Za-z]+)+$/;
    if (newName.trim() === "") {
      setNameError("Name cannot be empty");
    } else if (newName.trim().length < 3) {
      setNameError("Name must be minimum 3 letter");
    } else if (newName.trim().length > 40) {
      setNameError("Name cannot be more than 40 letter");
    } else {
      setNameError("");
    }
  };

  // HANDLE EMAIL CHANGE
  const handleEmailChange = (event) => {
    const newEmail = event.target.value;
    setEmail(newEmail);
    const emailRegex = /^[a-zA-Z0-9._-]+@nucleusteq\.com$/;
    setEmailError(
      newEmail.trim() === ""
        ? "Email cannot be empty"
        : !emailRegex.test(newEmail)
        ? "Email must have domain nucleusteq.com"
        : newEmail.trim().length > 40
        ? "email cannot be more than 40 character"
        : ""
    );
  };

  // HANDLE PASSWORD CHANGE
  const handlePasswordChange = (event) => {
    const v = event.target.value;
    setPassword(v);
    const passwordRegex = new RegExp(
      /^(?=.*[A-Z])(?=.*\d)(?=.*[@#$%^&+=!]).*$/
    );
    if (v.trim() === "") {
      setPasswordError("Password can not be empty.");
    } else if (v.trim().length < 8 || v.trim().length > 12) {
      setPasswordError(
        "Password must be minimum 8 and maximum 12 characters long."
      );
    } else if (passwordRegex.test(v) === false) {
      setPasswordError(
        "password must contain capital, small letter, digit and special character"
      );
    } else {
      setPasswordError("");
    }
  };

  // CHANGE USER TYPE CHANGE
  const handleUserTypeChange = (e) => {
    setUserType(e.target.value);
  };

  // HANDLE DEPARTMENT SELECT CHANGE
  const handleDepartmentChange = (e) => {
    setUserDepartment(e.target.value);
  };

  // TOGGLES THE SUCCESS MODAL -> POP UP
  const toggleModal = () => {
    setModal(!modal);
  };

  // TOGGLES THE FAIL MODAL -> POP UP
  const toggleError = () => {
    setError(!error);
  };
  // HANDLE SUBMIT -> NEW USER
  const handleSubmit = async (event) => {
    event.preventDefault();
    // IF ERROR THEN FORM NOT BE SUBMIT
    if (nameError !== "" || passwordError !== "" || emailError !== "") {
      return;
    }
    // DATA SENT
    let data = {
      name: name.trim(),
      email: email.trim(),
      password: btoa(password.trim()),
      isFirstLogin: true,
      role: userType,
      department: {
        departmentId: userDepartment,
      },
    };
    // HEADERS SENT
    const headers = {
      email: memberEmail,
      password: memberPassword,
    };
    // CONFIG CONATINING HEADERS
    const config = {
      headers: headers,
    };
    await axios
      .post("http://localhost:8080/api/create/", data, config)
      .then((response) => {
        setModal(true);
        setName("");
        setEmail("");
        setPassword("");
        setUserType("");
        setUserDepartment("");
        formRef.current.reset();
      })
      .catch((err) => {
        setError(true);
      });
  };

  return (
    <div className="user_container">
      <img className="user-img" src="adduser.svg" alt="add user" />
      <div className="user_registration_form">
        {/* HEADING */}
        <div className="user_heading">
          <p>Create New User</p>
        </div>

        {/* NEW USER FORM  */}
        <form
          className="user_form"
          onSubmit={handleSubmit}
          method="POST"
          ref={formRef}
        >
          {/* USER NAME  */}
          <div className="user_input_area">
            <Label htmlFor={"name"} text={"Name"} />
            {
              <InputField
                type={"text"}
                name={"name"}
                id={"name"}
                placeholder={"Enter your name"}
                value={name}
                onChange={handleNameChange}
                required
              />
            }
            {nameError && <span className="error-message">{nameError}</span>}
          </div>

          {/* EMAIL  */}
          <div className="user_input_area">
            <Label htmlFor={"email"} text={"Email"} />
            {
              <InputField
                type={"email"}
                name={"email"}
                id={"email"}
                placeholder={"Enter your email"}
                value={email}
                onChange={handleEmailChange}
                required
              />
            }
            {emailError && <span className="error-message">{emailError}</span>}
          </div>

          {/* PASSWORD  */}
          <div className="user_input_area">
            <Label htmlFor={"password"} text={"Password"} />
            {
              <InputField
                type={"password"}
                name={"password"}
                id={"password"}
                placeholder={"Enter password"}
                value={password}
                onChange={handlePasswordChange}
                required
              />
            }
            {passwordError && (
              <span className="error-message">{passwordError}</span>
            )}
          </div>

          {/* USER TYPE  */}
          <div className="user_input_area">
            <Label htmlFor={"usertype"} text={"User Type"} />

            <select
              name="usertype"
              id="usertype"
              value={userType}
              onChange={handleUserTypeChange}
              required
            >
              <option value="">--- Select ---</option>
              <option value="USER">USER</option>
              <option value="ADMIN">ADMIN</option>
            </select>
          </div>

          {/* USER DEPARTMENT  */}
          <div className="user_input_area">
            <Label htmlFor={"dept"} text={"Department"} />

            <select
              name="departmentId"
              value={userDepartment}
              onChange={handleDepartmentChange}
              required
            >
              <option value="">--Select--</option>
              {departmentData.map((dep) => {
                return (
                  <option key={dep.departmentId} value={dep.departmentId}>
                    {dep.departmentName}
                  </option>
                );
              })}
            </select>
          </div>

          {/* SUBMIT BUTTON  */}
          <div className="user_registration_form_btn">
            <input type="submit" value="ADD" />
          </div>
        </form>

        {/* POP UP ON SUCCESS  */}
        {modal && (
          <PopUp
            openPopup={modal}
            customHeading="Member Created Successfully"
            customImageSrc="404-Tick.png"
            toggleModal={toggleModal}
          />
        )}

        {/* POP UP ON FAIL  */}
        {error && (
          <PopUp
            openPopup={error}
            customHeading="Member with this Email already exists"
            customImageSrc="fail.png"
            toggleError={toggleError}
          />
        )}
      </div>
    </div>
  );
}

export default UserRegistration;
