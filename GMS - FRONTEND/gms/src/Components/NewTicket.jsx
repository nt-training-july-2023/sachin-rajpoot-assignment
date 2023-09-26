import React, { useState } from "react";
import "./NewTicket.css";
import { useNavigate } from "react-router-dom";
import axios, { HttpStatusCode } from "axios";

function DeptRegistration() {

  const navigate = useNavigate();

  const data = {
    "title":"",
    "description":"",
    "status":"",
    "ticketType":""
  };

  const [title, setTitle] = useState("");
  const [desc, setDesc] = useState("");
  const [titleError, setTitleError] = useState("");
  const [descError, setDescError] = useState("");
  const [isButtonDisabled, setIsButtonDisabled] = useState(true);

  const handleTitleChange = (event) => {
    const newTitle = event.target.value;
    setTitle(newTitle);
    setTitleError(newTitle.trim() === "" ? "Title cannot be empty" : "");
    updateButtonStatus();
  };

  const handleDescChange = (event) => {
    const newDesc = event.target.value;
    setDesc(newDesc);
    setDescError(newDesc.trim() === "" ? "Description cannot be empty" : "");
    updateButtonStatus();
  };

  const updateButtonStatus = () => {
    setIsButtonDisabled(title.trim() === "" || desc.trim() === "");
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    
    data.title = title;
    data.description=desc;
    data.status="OPEN";
    data.ticketType="GRIEVANCE";
    
    console.log(data);
    console.log("Form submitted successfully!");

    axios
      .post("http://localhost:8080/api/ticket/create/memberId/3/departmentId/15", data)
      .then((response) => {
        // setData(response.data);
        // if(response.data === "Login Successfully", HttpStatusCode.Accepted){
        navigate("/");
        // }
        console.log(response.data);
        // alert(response.data)
      })
      .catch((err) => console.log(err));

  };

  return (
    <div className="dept_container">
      <div className="dept_registration_form">
        <div className="dept_heading">
          <p>Create New Ticket</p>
        </div>

        <form className="dept_form" onSubmit={handleSubmit} method="POST">
          <div className={`dept_input_area ${titleError ? "error" : ""}`}>
            <label htmlFor="title">Title:</label>
            <input
              type="text"
              id="title"
              name="title"
              placeholder="Enter ticket title"
              value={title}
              onChange={handleTitleChange}
              required
            />
            {titleError && <span className="error-message">{titleError}</span>}
          </div>

          <div className={`dept_input_area ${descError ? "error" : ""}`}>
            <label htmlFor="desc">Description:</label>
            <textarea
              id="desc"
              name="desc"
              placeholder="Enter ticket description"
              value={desc}
              onChange={handleDescChange}
              required
            />
            {descError && <span className="error-message">{descError}</span>}
          </div>

          <div className="dept_input_area">
            <label htmlFor="tickettype">Ticket Type:</label>
            <select name="tickettype" id="tickettype">
              <option value="greivance">Greivance</option>
              <option value="feedback">Feedback</option>
            </select>
          </div>

          <div className="dept_input_area">
            <label htmlFor="depttype">Dept Type:</label>
            <select name="dept" id="dept">
              <option value="it">IT</option>
              <option value="hr">HR</option>
              <option value="infrastructure">Infrastructure</option>
              <option value="accounts">Accounts</option>
            </select>
          </div>

          <div className="dept_input_area">
            <label htmlFor="status">Ticket Status:</label>
            <select name="status" id="status" disabled>
              <option value="open">Open</option>
              <option value="progress">Progress</option>
              <option value="closed">Closed</option>
            </select>
          </div>

          <div className="dept_registration_form_btn">
            <input
              type="submit"
              value="Register"
              // disabled={isButtonDisabled}
              className={isButtonDisabled ? "disabled-button" : ""}
            />
          </div>
        </form>
      </div>
    </div>
  );
}

export default DeptRegistration;
