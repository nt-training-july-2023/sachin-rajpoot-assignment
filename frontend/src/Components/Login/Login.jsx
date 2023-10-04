import React, { useState } from "react";
import "./Login.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import PopUp from "../PopUp/PopUp";
import InputField from "../InputField/InputField";
import Label from "../Label/Label";

function Login({ setIsLoggedIn, isLoggedIn }) {
  const navigate = useNavigate();
  const [Popup, setPopup] = useState(false);
  const [Error, setError] = useState(false);
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [data, setData] = useState({
    email: "",
    password: "",
  });

  // TOGGLE POPUP -> SUCCESS
  const toggleModal = () => {
    setPopup(!Popup);
  };

  // TOGGLES THE FAIL MODAL -> POP UP
  const toggleError = () => {
    setError(!Error);
  };

  // EMAIL CHECK
  const verifyEmailHandlder = (e) => {
    const email = document.getElementById("email").value;
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    return emailPattern.test(email) && email.endsWith("@nucleusteq.com");
  };

  // EMAIL CHANGE
  const handleEmailChange = (e) => {
    const newEmail = e.target.value;
    setData({ ...data, email: e.target.value });
    if (newEmail.trim() == "") {
      setEmailError("Email cannot be empty");
    } else if (newEmail.endsWith("@nucleusteq.com") == false) {
      setEmailError("Domain should be @nucleusteq.com");
    } else if (newEmail.endsWith("@nucleusteq.com") == true) {
      setEmailError("");
    }
  };

  // PASSWORD CHANGE
  const handlePasswordChange = (e) => {
    const newPassword = e.target.value;
    setData({ ...data, password: e.target.value });
    setPasswordError(
      newPassword.trim() === "" ? "Password cannot be empty" : ""
    );
  };

  // SUBMIT LOGIN
  const submitHandler = async (e) => {
    e.preventDefault();
    if (!verifyEmailHandlder() || passwordError !== "" || emailError !== "") {
    } else {
      const logindata = {
        email: data.email,
        password: btoa(data.password),
      };
      await axios
        .post("http://localhost:8080/api/login", logindata)
        .then((response) => {
          setData(response.data);
          localStorage.setItem("member", JSON.stringify(response.data));
          // SAVING PASSWORD AS -> ENCODED PASSWORD
          localStorage.setItem(
            "memberPassword",
            JSON.stringify(btoa(data.password))
          );
          setIsLoggedIn(true);
          if (response.data.isFirstLogin === true) {
            navigate("/changepassword");
          } else {
            navigate("/tickettable");
          }
        })
        .catch((err) => {
          setError(true);
          console.log(err);
          if (err?.message) {
          } else if (err.response.data?.password) {
          }
        });
    }
  };

  return (
    <div className="container pt1 patterns">
      <h1>Grievance Management System</h1>
      {/* LOGIN FORM  */}
      <div className="registration_form">
        {/* HEADING  */}
        <div className="heading">
          <h1 id="heading">Login</h1>
        </div>
        <div className="input_fields">
          <form className="content" onSubmit={submitHandler}>
            {/* EMAIL  */}
            <div className="input_area">
              <Label htmlFor={"email"} text={"Email"} />
              {
                <InputField
                  type={"email"}
                  name={"email"}
                  id={"email"}
                  placeholder={"Enter your email"}
                  onChange={handleEmailChange}
                  required
                />
              }
              {emailError && <span className="login-error">{emailError}</span>}
            </div>
            {/* PASSWORD  */}
            <div className="input_area">
              <Label htmlFor={"password"} text={"Password"} />
              {
                <InputField
                  type={"password"}
                  name={"password"}
                  id={"password"}
                  placeholder={"Enter password"}
                  onChange={handlePasswordChange}
                  required
                />
              }
              {passwordError && (
                <span className="login-error">{passwordError}</span>
              )}
            </div>
            {/* SUBMIT BUTTON */}
            <div className="registraction_btn">
              <input type="submit" value="Login" />
            </div>
          </form>
        </div>
      </div>

      {/* POP UP ON SUCCESS  */}
      {Popup && (
        <PopUp
          openPopup={Popup}
          customHeading="Login Successfull"
          customImageSrc="404-Tick.png"
          toggleModal={toggleModal}
        />
      )}

      {/* POP UP ON FAIL  */}
      {Error && (
        <PopUp
          openPopup={Error}
          customHeading="Invalid Credentials"
          customImageSrc="fail.png"
          toggleError={toggleError}
        />
      )}
    </div>
  );
}

export default Login;
