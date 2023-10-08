import React, { useState } from "react";
import axios from "axios";
import "./ChangePassword.css";
import { useNavigate } from "react-router-dom";
import Label from "../Label/Label";
import InputField from "../InputField/InputField";

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

  // SET OLD PASSWORD
  const handleOldPasswordChange = (e) => {
    const v = e.target.value;
    setOldPassword(v);
    setOldPasswordError(v.trim() === "" ? "Field cannot be empty" : "");
  };

  // SET NEW PASSWORD
  const handleNewPasswordChange = (e) => {
    const v = e.target.value;
    setNewPassword(v);
    const passwordRegex = new RegExp(
      /^(?=.*[A-Z])(?=.*\d)(?=.*[@#$%^&+=!]).*$/
    );
    if (v.trim() === "") {
      setNewPasswordError("Password can not be empty.");
    } else if (v.trim().length < 8 || v.trim().length > 12) {
      setNewPasswordError(
        "Password must be minimum 8 and maximum 12 characters long."
      );
    } else if (passwordRegex.test(v) === false) {
      setNewPasswordError(
        "password must contain capital, small letter," +
          "digit and special character"
      );
    } else {
      setNewPasswordError("");
    }
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
    axios
      .put(
        `http://localhost:8080/api/changepassword/memberId/${memberId}`,
        data,
        config
      )
      .then((response) => {
        handleLogOut();
      })
      .catch((err) => {
        setModal(true);
        console.log(err);
      });
  };

  // TOGGLES THE MODAL
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
      <img src="changepassword.svg" alt="" />
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
          {/* OLD PASSWORD  */}
          <div className="change-password-input-area">
            <Label htmlFor={"oldpassword"} text={"Old password"} />
            {
              <InputField
                type={"password"}
                placeholder={"Enter old password"}
                onChange={(e) => handleOldPasswordChange(e)}
                required
              />
            }
            {oldPasswordError && (
              <span className="error-message">{oldPasswordError}</span>
            )}
          </div>

          {/* NEW PASSWORD  */}
          <div className="change-password-input-area">
            <Label htmlFor={"newpassword"} text={"New password"} />
            {
              <InputField
                type={"password"}
                placeholder={"Enter new password"}
                onChange={(e) => handleNewPasswordChange(e)}
                required
              />
            }
            {newPasswordError && (
              <span className="error-message">{newPasswordError}</span>
            )}
          </div>

          {/* CONFIRM PASSWORD  */}
          <div className="change-password-input-area">
            <Label htmlFor={"conpassword"} text={"Confirm Password"} />
            {
              <InputField
                type={"password"}
                placeholder={"Confirm new password"}
                onChange={(e) => handleconfirmPasswordChange(e)}
                required
              />
            }
            {confirmNewPasswordError && (
              <span className="error-message">{confirmNewPasswordError}</span>
            )}
          </div>

          {/* SUBMIT BUTTON  */}
          <div className="change-password-input-area">
            <input type="submit" className="change-password-btn" />
          </div>
        </form>

        {/* DISPLAY FAIL OR ERROR MESSAGE */}
        {modal && (
          <div className="dept-modal">
            <div onClick={toggleModal} className="overlay"></div>
            <div className="dept-modal-content">
              <h2>Invalid Credentials</h2>
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
