import React, { useEffect, useRef, useState } from "react";
import "./NewTicket.css";
import axios from "axios";
import SuccessModal from "../Modal/SuccessModal ";
import PopUp from "../PopUp/PopUp";

function DeptRegistration() {
  const [title, setTitle] = useState("");
  const [desc, setDesc] = useState("");
  const [titleError, setTitleError] = useState("");
  const [descError, setDescError] = useState("");
  const [submitSuccessMessage, setSubmitSuccessMessage] = useState(false);
  const [isButtonDisabled, setIsButtonDisabled] = useState(true);
  const [departmentData, setDepartmentData] = useState([]);
  const [departmentId, setDepartmentId] = useState([]);
  const [tickettype, setTickettype] = useState();
  const formRef = useRef(null);
  const memberId = JSON.parse(localStorage.getItem("member"))?.memberId;
  const memberEmail = JSON.parse(localStorage.getItem("member"))?.email;
  const memberPassword = JSON.parse(localStorage.getItem("memberPassword"));
  const data = {
    title: "",
    description: "",
    status: "",
    ticketType: "",
  };
  const [Error, setError] = useState(false);
  const [isModalVisible, setIsModalVisible] = useState(false);

  const [modal, setModal] = useState(false);

  // GETTING ALL DEPARTMENTS TO SHOW ON SELECT DEPARTMENT DROPDOWN
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
        console.log(response);
      })
      .catch((err) => console.log(err));
  }, []);

  // ONCHANGE FOR TITLE
  const handleTitleChange = (event) => {
    const newTitle = event.target.value;
    setTitle(newTitle);
    setTitleError(newTitle.trim() === "" ? "Title cannot be empty" : "");
    updateButtonStatus();
  };

  // ONCHANGE FOR DESCRIPTION
  const handleDescChange = (event) => {
    const newDesc = event.target.value;
    setDesc(newDesc);
    setDescError(newDesc.trim() === "" ? "Description cannot be empty" : "");
    updateButtonStatus();
  };

  // ONCHANGE FOR TICKET TYPE
  const handleTicketTypeChange = (e) => {
    setTickettype(e.target.value);
    console.log(tickettype);
  };

  // ONCHANGE FOR SELECT
  const handleSelectChange = (e) => {
    const { name, value } = e.target;
    console.log(value);
    setDepartmentId(Number(value));
  };

  const updateButtonStatus = () => {
    setIsButtonDisabled(title.trim() === "" || desc.trim() === "");
  };

  // SUBMIT BUTTON HANDLER
  const handleSubmit = (event) => {
    event.preventDefault();
    data.title = title;
    data.description = desc;
    data.status = "OPEN";
    data.ticketType = tickettype;
    console.log("Data Sent");
    console.log(data);
    const headers = {
      email: memberEmail,
      password: memberPassword,
    };
    const config = {
      headers: headers,
    };
    axios
      .post(
        `http://localhost:8080/api/ticket/create/memberId/${memberId}/departmentId/${departmentId}`,
        data,
        config
      )
      .then((response) => {
        console.log("Form submitted successfully!");
        console.log("Data received");
        setSubmitSuccessMessage(true);
        console.log(setSubmitSuccessMessage);
        console.log(response.data);
        setIsModalVisible(true);
        setModal(true);
        formRef.current.reset();
      })
      .catch((err) => console.log(err));
  };

  const toggleModal = () => {
    setModal(!modal);
  };
  if (modal) {
    document.body.classList.add("active-modal");
  } else {
    document.body.classList.remove("active-modal");
  }
  // TOGGLES THE FAIL MODAL -> POP UP
  const toggleError = () => {
    setError(!Error);
  };

  return (
    <div className="dept_container">
      <div className="dept_registration_form">
        {/* HEADING  */}
        <div className="dept_heading">
          <p>Create New Ticket</p>
        </div>

        {/* NEW TICKET FROM  */}
        <form
          className="dept_form"
          onSubmit={handleSubmit}
          method="POST"
          ref={formRef}
        >
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
            <select
              name="tickettype"
              id="tickettype"
              defaultValue=""
              onChange={handleTicketTypeChange}
              required
            >
              <option value="">--Select--</option>
              <option value="GRIEVANCE">GRIEVANCE</option>
              <option value="FEEDBACK">FEEDBACK</option>
            </select>
          </div>

          <div className="dept_input_area">
            <label htmlFor="dept">Department:</label>
            <select
              name="departmentId"
              defaultValue=""
              onChange={handleSelectChange}
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
              className={isButtonDisabled ? "disabled-button" : ""}
            />
          </div>
        </form>

        {/* DISPLAY SUCCESS OR ERROR MESSAGE
        {modal && (
          <div className="dept-modal">
            <div onClick={toggleModal} className="overlay"></div>
            <div className="dept-modal-content">
              <h2>Ticket Created Successfully</h2>
              <button className="dept-close-modal" onClick={toggleModal}>
                CLOSE
              </button>
            </div>
          </div>
        )} */}

        {/* POP UP ON SUCCESS  */}
        {modal && (
          <PopUp
            openPopup={modal}
            customHeading="Member Deleted Successfully"
            customImageSrc="404-Tick.png"
            toggleModal={toggleModal}
          />
        )}

        {/* POP UP ON FAIL  */}
        {Error && (
          <PopUp
            openPopup={Error}
            customText="something went wrong
          check network connections"
            toggleError={toggleError}
          />
        )}
      </div>
    </div>
  );
}

export default DeptRegistration;