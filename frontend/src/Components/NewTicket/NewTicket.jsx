import React, { useEffect, useRef, useState } from "react";
import "./NewTicket.css";
import axios from "axios";
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
      })
      .catch((err) => console.log(err));
  }, []);

  // ONCHANGE FOR TITLE
  const handleTitleChange = (event) => {
    const newTitle = event.target.value;
    setTitle(newTitle);
    setTitleError(newTitle.trim() === "" ? "Title cannot be empty" : "");
  };

  // ONCHANGE FOR DESCRIPTION
  const handleDescChange = (event) => {
    const newDesc = event.target.value;
    setDesc(newDesc);
    setDescError(newDesc.trim() === "" ? "Description cannot be empty" : "");
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

  // SUBMIT BUTTON HANDLER
  const handleSubmit = (event) => {
    event.preventDefault();
    data.title = title;
    data.description = desc;
    data.status = "OPEN";
    data.ticketType = tickettype;
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
        setSubmitSuccessMessage(true);
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
          {/* TITLE  */}
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

          {/* DESCRIPTION  */}
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

          {/* TICKET TYPE  */}
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

          {/* DEPARTMENT NAME  */}
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

          {/* DEFAULT STATUS  */}
          <div className="dept_input_area">
            <label htmlFor="status">Ticket Status:</label>
            <select name="status" id="status" disabled>
              <option value="open">Open</option>
              <option value="progress">Progress</option>
              <option value="closed">Closed</option>
            </select>
          </div>

          {/* SUBMIT BUTTON  */}
          <div className="dept_registration_form_btn">
            <input
              type="submit"
              value="ADD"
              className={isButtonDisabled ? "disabled-button" : ""}
            />
          </div>
        </form>

        {/* POP UP ON SUCCESS  */}
        {modal && (
          <PopUp
            openPopup={modal}
            customHeading="Created Successfully"
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
