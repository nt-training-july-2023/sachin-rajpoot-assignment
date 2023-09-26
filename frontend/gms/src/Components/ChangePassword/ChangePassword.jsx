import React, { useState } from "react";
import axios from "axios";
import "./ChangePassword.css";
import { useNavigate } from "react-router-dom";

function ChangePassword({ setIsLoggedIn }) {
  const [oldPassword, setOldPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmNewPassword, setConfirmNewPassword] = useState("");
  const [oldPasswordError, setOldPasswordError] = useState("");
  const [newPasswordError, setNewPasswordError] = useState("");
  const [confirmNewPasswordError, setConfirmNewPasswordError] = useState("");
  const memberId = JSON.parse(localStorage.getItem("member"))?.memberId;
  const memberEmail = JSON.parse(localStorage.getItem("member"))?.email;
  const memberPassword = JSON.parse(localStorage.getItem("memberPassword"));
  const navigate = useNavigate();
  const [errorAlert, setErrorAlert] = useState(true);
  const [modal, setModal] = useState(false);

  // SET OLD PASSWOED
  const handleOldPasswordChange = (e) => {
    const v = e.target.value;
    setOldPassword(v);
    setOldPasswordError(v.trim() === "" ? "Field cannot be empty" : "");
  };

  // SET NEW PASSWORD
  const handleNewPasswordChange = (e) => {
    const v = e.target.value;
    setNewPassword(v);
    setNewPasswordError(v.trim() === "" ? "Field cannot be empty" : "");
    setNewPasswordError(
      v.length < 5 ? "Password must be at least 5 characters long." : ""
    );
  };

  // SET CONFRIM PASSWORD
  const handleconfirmPasswordChange = (e) => {
    const v = e.target.value;
    setConfirmNewPassword(v);
    setConfirmNewPasswordError(v.trim() === "" ? "Field cannot be empty" : "");
    setConfirmNewPasswordError(
      e.target.value !== newPassword ? "Password does not match" : ""
    );
  };

  // LOGOUT BUTTON HANDLER
  const handleLogOut = () => {
    localStorage.clear();
    setIsLoggedIn(false);
    navigate("/");
  };

  //  HANDLE SUBMIT -> CHANGE PASSWORD
  const handleSubmit = (event) => {
    event.preventDefault();
    if (oldPasswordError || newPasswordError || confirmNewPasswordError) {
      console.log(
        oldPasswordError +
          " " +
          newPasswordError +
          " " +
          confirmNewPasswordError
      );
      return;
    }
    const headers = {
      email: memberEmail,
      password: memberPassword,
    };
    const config = {
      headers: headers,
    };
    const data = {
      oldpassword: btoa(oldPassword), // ENCODING OLD PASSWORD BEFORE SENDING
      newPassword: btoa(newPassword), // ENCODING NEW PASSWORD BEFORE SENDING
    };
    console.log("old password -> " + oldPassword);
    console.log("old password encoded -> " + btoa(oldPassword));
    console.log("new password -> " + newPassword);
    console.log("new password encoded -> " + btoa(newPassword));
    console.log("Data sent");
    console.log(data);
    console.log("headers sent");
    console.log(config);
    axios
      .put(
        `http://localhost:8080/api/changepassword/memberId/${memberId}`,
        data,
        config
      )
      .then((response) => {
        // localStorage.clear();
        setModal(true);
        console.log("DATA RECEIVED");
        console.log(response.data);
        console.log("Log Out Function");
        handleLogOut();
      })
      .catch((err) => console.log(err));

    console.log("Form submitted successfully!");
  };

  const toggleModal = () => {
    setModal(!modal);
  };
  if (modal) {
    document.body.classList.add("active-modal");
  } else {
    document.body.classList.remove("active-modal");
  }

  return (
    <div className="change-password-container">
      <div className="change-password-subcontainer">
        {/* HEADING */}
        <div className="change-password-heading">
          <h1>Change Password</h1>
        </div>

        {/* CHANGE PASSWORD FORM  */}
        <form
          className="change-password-form"
          method="POST"
          onSubmit={(e) => handleSubmit(e)}
        >
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
              onChange={(e) => handleNewPasswordChange(e)}
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
            <input type="submit" className="change-password-btn" />
          </div>
        </form>

        {/* DISPLAY SUCCESS OR ERROR MESSAGE */}
        {modal && (
          <div className="dept-modal">
            <div onClick={toggleModal} className="overlay"></div>
            <div className="dept-modal-content">
              <h2>Password Changed Successfully</h2>
              <button className="dept-close-modal" onClick={toggleModal}>
                CLOSE
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default ChangePassword;
