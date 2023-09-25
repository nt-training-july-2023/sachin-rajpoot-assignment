import React, { useEffect, useState } from "react";
import "./Login.css";
import axios, { HttpStatusCode } from "axios";
import { useNavigate } from "react-router-dom";
import PopUp from "../PopUp/PopUp";

function Login({ setIsLoggedIn, isLoggedIn }) {
  console.log("Login Component");
  const navigate = useNavigate();
  const memberEmail = JSON.parse(localStorage.getItem("member"))?.email;
  const [Popup, setPopup] = useState(false);
  const [Error, setError] = useState(false);
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
    setData({ ...data, email: e.target.value });
  };

  const handlePasswordChange = (e) => {
    setData({ ...data, password: e.target.value });
  };
  const submitHandler = async (e) => {
    e.preventDefault();
    if (!verifyEmailHandlder()) {
      alert("Enter Valid Email Address.");
    } else {
      const logindata = {
        email: data.email,
        password: btoa(data.password),
      };
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
          setIsLoggedIn(true);
          setPopup(true)
          navigate("/tickettable");
        })
        .catch((err) => {
          setError(true)
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
