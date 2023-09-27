import React, { useState } from "react";
import "./Login.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import PopUp from "../PopUp/PopUp";

function Login({ setIsLoggedIn, isLoggedIn }) {
  console.log("Login Component");
  const navigate = useNavigate();
  const [Popup, setPopup] = useState(false);
  const [Error, setError] = useState(false);
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [data, setData] = useState({
    email: "",
    password: "",
  });

  const toggleModal = () => {
    setPopup(!Popup);
  };
  if (Popup) {
    document.body.classList.add("active-modal");
  } else {
    document.body.classList.remove("active-modal");
  }

  // TOGGLES THE FAIL MODAL -> POP UP
  const toggleError = () => {
    setError(!Error);
  };

  const verifyEmailHandlder = (e) => {
    const email = document.getElementById("email").value;
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    return emailPattern.test(email) && email.endsWith("@nucleusteq.com");
  };

  const handleEmailChange = (e) => {
    const newEmail = e.target.value;
    setData({ ...data, email: e.target.value });
    if (newEmail.trim() == "") {
      setEmailError("Email cannot be empty");
    }
    else if (newEmail.endsWith("@nucleusteq.com") == false) {
      setEmailError("Domain should be @nucleusteq.com");  
    }
    else if (newEmail.endsWith("@nucleusteq.com") == true) {
      setEmailError("");  
    }
  };

  const handlePasswordChange = (e) => {
    const newPassword = e.target.value;
    setData({ ...data, password: e.target.value });
    setPasswordError(
      newPassword.trim() === "" ? "Password cannot be empty" : ""
    );
  };
  const submitHandler = async (e) => {
    e.preventDefault();
    if (!verifyEmailHandlder() || passwordError !== "" || emailError !== "") {
      // alert("Enter Valid Email Address.");
      // setEmailError("Enter Valid Email Address.")
    } else {
      const logindata = {
        email: data.email,
        password: btoa(data.password),
      };
      console.log("Login Data Sent : ");
      console.log(logindata);
      await axios
        .post("http://localhost:8080/api/login", logindata)
        .then((response) => {
          console.log("pop up");
          setData(response.data);
          console.log(response.data);
          localStorage.setItem("member", JSON.stringify(response.data));
          // SAVING PASSWORD AS -> ENCODED PASSWORD
          localStorage.setItem(
            "memberPassword",
            JSON.stringify(btoa(data.password))
          );
          // setIsLoggedIn(true);
          // setPopup(true)
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
            // alert(err.message);
          } else if (err.response.data?.password) {
            // alert(err.response.data.password);
          }
          console.log(err.message);
        });
    }
  };

  return (
    <div className="container pt1 patterns">
      <div className="registration_form">
        <div className="heading">
          <h1 id="heading">Login</h1>
        </div>
        <div className="input_fields">
          <form className="content" onSubmit={submitHandler}>
            <div className="input_area">
              <label htmlFor="email">Email:</label>
              <input
                type="email"
                id="email"
                name="email"
                placeholder="Enter your email "
                onChange={handleEmailChange}
                required
              />
              {emailError && <span className="login-error">{emailError}</span>}
            </div>

            <div className="input_area">
              <label htmlFor="password">Password:</label>
              <input
                type="password"
                id="password"
                name="password"
                placeholder="Enter your password"
                onChange={handlePasswordChange}
                required
              />
              {passwordError && (
                <span className="login-error">{passwordError}</span>
              )}
            </div>

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
