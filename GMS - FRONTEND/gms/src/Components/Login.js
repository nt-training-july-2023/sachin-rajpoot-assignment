import React, { useEffect, useState } from "react";
import "./Login.css";
import axios, { HttpStatusCode } from "axios";
import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();
  const [data, setData] = useState({
    email: "",
    password: "",
  });

  const verifyEmailHandlder = (e) => {

    const email = document.getElementById("email").value;
    
     // Regular expression to match a valid email address
     const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    
     // Check if the email matches the pattern and ends with @nucleusteq.com
     return emailPattern.test(email) && email.endsWith("@nucleusteq.com");

  }

  const handleEmailChange = (e) => {
    setData({ ...data, email: e.target.value });
  };

  const handlePasswordChange = (e) => {
    setData({ ...data, password: e.target.value });
  };

  const submitHandler = (e) => {
    e.preventDefault();
    if(!verifyEmailHandlder()){
      alert("Enter Valid Email Address.");
      
    }
    else {
     
      console.log(data);
      axios
        .post(
          "http://localhost:8080/api/login",
          data
        )
        .then((response) => {
          setData(response.data);
          // if(response.data === "Login Successfully", HttpStatusCode.Accepted){
          navigate("/");
          // }
          console.log(response.data);
          // alert(response.data)
        })
        .catch((err) => console.log(err));
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
              <label for="email">Email:</label>
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
              <label for="password">Password:</label>
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
              <input type="submit" value="Login"  />
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Login;
