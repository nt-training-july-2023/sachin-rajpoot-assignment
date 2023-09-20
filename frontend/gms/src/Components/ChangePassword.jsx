import React, { useState } from "react";
import axios, { HttpStatusCode } from "axios";
import "./ChangePassword.css";

function ChangePassword() {
  // const data = {
  //   oldPassword: "",
  //   newPassword: "",
  // };

  const headers = {
    oldPassword: "",
    newPassword: "",
  };
  const [oldPassword, setOldPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmNewPassword, setConfirmNewPassword] = useState("");

  const [oldPasswordError, setOldPasswordError] = useState("");
  const [newPasswordError, setNewPasswordError] = useState("");
  const [confirmNewPasswordError, setConfirmNewPasswordError] = useState("");

  const[errorAlert, setErrorAlert] = useState(true);

  const handleOldPasswordChange = (e) => {
    const v = e.target.value;
    setOldPassword(v);
    setOldPasswordError(v.trim() === "" ? "Field cannot be empty" : "");

    // CHECK FOR SPECIAL CHARACTER
    // const passwordPattern = /^[a-zA-Z0-9]*$/;
    // const isValidPassword = passwordPattern.test(e.target.value);
    // setOldPasswordError(
    //   isValidPassword === "" ? "Only Alphabets and Digits are allowed" : ""
    // );
  };

  const handleNewPasswordChange = (e) => {
    const v = e.target.value;
    setNewPassword(v);
    setNewPasswordError(v.trim() === "" ? "Field cannot be empty" : "");

    // const passwordPattern = /^[a-zA-Z0-9]*$/;
    // const isValidPassword = passwordPattern.test(v);
    // setNewPasswordError(
    //   isValidPassword === "" ? "Only Alphabets and Digits are allowed" : ""
    // );

    setNewPasswordError(
      v.length < 5 ? "Password must be at least 5 characters long." : ""
    );
  };

  const handleconfirmPasswordChange = (e) => {
    const v = e.target.value;
    setConfirmNewPassword(v);

    setConfirmNewPasswordError(v.trim() === "" ? "Field cannot be empty" : "");

    // const passwordPattern = /^[a-zA-Z0-9]*$/;
    // const isValidPassword = passwordPattern.test(v);
    // setConfirmNewPasswordError(
    //   isValidPassword === "" ? "Only Alphabets and Digits are allowed" : ""
    // );

    // console.log((confirmNewPassword != newPassword)+" "+e.target.value+" "+newPassword) 
    setConfirmNewPasswordError(
      e.target.value !== newPassword ? "Password does not match" : ""
    );
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    if(oldPasswordError || newPasswordError || confirmNewPasswordError) {
        // alert(oldPasswordError+" "+ newPasswordError+" "+confirmNewPasswordError)
        console.log(oldPasswordError+" "+ newPasswordError+" "+confirmNewPasswordError)
        return;
    }

    headers.oldPassword = oldPassword;
    headers.newPassword = newPassword;
    // console.log(data);
    console.log(headers);

    console.log("Form submitted successfully!");
  };
  return (
    <div className="change-password-container">
      <div className="change-password-subcontainer">
        <div className="change-password-heading">
          <h1>Change Password</h1>
        </div>
        <form className="change-password-form" method="POST" onSubmit={(e)=>handleSubmit(e)}>
          <div className="change-password-input-area">
            <label htmlFor="oldpassword">Old Password :</label>
            <input
              type="text"
              placeholder="Enter old password"
              onChange={(e) => handleOldPasswordChange(e)}
              required
            />
            {oldPasswordError && (
              <span className="error-message">{oldPasswordError}</span>
            )}
          </div>

          <div className="change-password-input-area">
            <label htmlFor="newpassword">New Password :</label>
            <input
              type="text"
              placeholder="Enter new password"
              onChange={(e)=> handleNewPasswordChange(e)}
              required
            />
            {newPasswordError && (
              <span className="error-message">{newPasswordError}</span>
            )}
          </div>

          <div className="change-password-input-area">
            <label htmlFor="conpassword">Confirm Password :</label>
            <input
              type="text"
              placeholder="confirm new password"
              onChange={(e) => handleconfirmPasswordChange(e)}
              required
            />
            {confirmNewPasswordError && (
              <span className="error-message">{confirmNewPasswordError}</span>
            )}
          </div>

          <div className="change-password-input-area">
            <input
              type="submit"
              className="change-password-btn"
              
              
            />
          </div>
        </form>
      </div>
    </div>
  );
}

export default ChangePassword;
