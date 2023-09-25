import React, { useEffect, useRef, useState } from "react";
import axios from "axios";
import "./TicketTable.css";
import PopUp from "../PopUp/PopUp";

function TicketTable({ isLoggedIn }) {
  console.log("TTTTTTTTTTTTTTTTTTTTTTTT")
  // STATE VARIABLES
  const [modal, setModal] = useState(false);
  const [modal2, setModal2] = useState(false);
  const [ticketsData, setTicketsData] = useState([]);
  const [ticket, setTicket] = useState();
  const [comment, setComment] = useState("");
  const [selectedStatus, setSelectedStatus] = useState("ALL");
  const [currentPage, setCurrentPage] = useState(0);
  const [ticketStatus, setTicketStatus] = useState("");
  const [selectedParam, setSelectedParam] = useState("ALL");
  const [errorMessage, setErrorMessage] = useState(false);
  const [tableParams, setTableParams] = useState({
    myTickets: false,
    adminDept: false,
  });

  // GETTING LOCAL STORAGE SAVED MEMBER DATA
  const memberId = JSON.parse(localStorage.getItem("member"))?.memberId;
  const memberEmail = JSON.parse(localStorage.getItem("member"))?.email;
  const memberUserName = JSON.parse(localStorage.getItem("member"))?.name;
  const memberPassword = JSON.parse(localStorage.getItem("memberPassword"));

  const formRef = useRef(null);

  // GETTING ALL TICKETS FOR TICKET TABLE
  useEffect(() => {
    console.log("Param sent : ");
    console.log(tableParams);
    const headers = {
      email: memberEmail,
      password: memberPassword,
    };
    const config = {
      headers: headers,
      params: tableParams,
    };
    console.log("Headers");
    console.log(config);
    axios
      .get(
        `http://localhost:8080/api/ticket/getAll/auth/memberId/${memberId}/filter/${selectedStatus}/pageNumber/${currentPage}`,
        config
      )
      .then((response) => {
        setTicketsData(response.data);
        console.log("DATA RECEIVED");
        console.log(response);
      })
      .catch((err) => console.log(err));
  }, [selectedStatus, currentPage, tableParams, modal]);

  // FILTER BY STATUS
  const handleSelectChange = (event) => {
    const newSelectedStatus = event.target.value;
    setCurrentPage(0);
    setSelectedStatus(newSelectedStatus);
    console.log("FILTERED TICKETS BY " + newSelectedStatus);
  };

  // HANDLE PARAM CHANGES
  const handleParamChange = async (e) => {
    const selectedValue = e.target.value;
    //  setCurrentPage(0);
    setSelectedParam(selectedValue);
    console.log(selectedValue);
    if (selectedValue === "adminDept") {
      setTableParams({
        myTickets: false,
        adminDept: true,
      });
    } else if (selectedValue === "myTickets") {
      setTableParams({
        myTickets: true,
        adminDept: false,
      });
    } else {
      setTableParams({
        myTickets: false,
        adminDept: false,
      });
    }
  };

  // TOGGLE MODAL -> VIEW DETAIL
  const toggleModal = async (ticketId) => {
    await handleViewDetail(ticketId);
    setModal(!modal);
    setErrorMessage(!errorMessage);
  };
  if (modal) {
    document.body.classList.add("active-modal");
  } else {
    document.body.classList.remove("active-modal");
  }

  // TOGGLE MODAL2 -> ERROR MESSAGE
  const toggleModal2 = () => {
    setModal2(!modal2);
  };
  if (modal2) {
    document.body.classList.add("active-modal");
  } else {
    document.body.classList.remove("active-modal");
  }

  // GETTING TICKET DETAILS ON CLICK ON VIEW DETAIL
  const handleViewDetail = async (ticketId) => {
    console.log(ticketId);
    let res = {};
    const headers = {
      email: memberEmail,
      password: memberPassword,
    };
    const config = {
      headers: headers,
    };
    try {
      res = await axios.get(
        `http://localhost:8080/api/ticket/getbyId/ticketId/${ticketId}`,
        config
      );
      console.log("Ticket data received");
      console.log(res.data);

      setTicketStatus(res.data.status)
    } catch (e) {
      console.log(e);
    }

    setTicket(res.data);
    console.log("ticket status received");
    // console.log(res.data.status)
    // setTicketStatus(res.data.status)
    console.log(res);
  };

  // UPDATES THE TICKET STATUS AND ADD COMMENT ON CLICK -> UPDATE BUTTON
  const handleUpdateTicket = async () => {
    ticket.status = ticketStatus;
    if (
      comment === "" ||
      comment === null ||
      ticket.status === "OPEN" ||
      comment.trim() === ""
    ) {
      setModal2(true);
      return;
    }
    console.log("New Ticket Status Sent");
    console.log(ticketStatus);
    console.log("Comment sent");
    console.log(comment);
    console.log("Member Name Sent");
    console.log(memberUserName);
    console.log("Old Ticket");
    console.log(ticket);
    let res = {};
    const headers = {
      email: memberEmail,
      password: memberPassword,
    };
    const config = {
      headers: headers,
    };
    const ticketUpdateStatusInDto = {
      status: ticketStatus,
      comment: {
        content: comment,
        userName: memberUserName,
      },
    };
    console.log("Data Sent");
    console.log(ticketUpdateStatusInDto);
    try {
      res = await axios.put(
        `http://localhost:8080/api/ticket/update/ticketId/${ticket.ticketId}`,
        ticketUpdateStatusInDto,
        config
      );

      formRef.current.reset();
      // setTicketStatus(res.data.status)
    } catch (e) {
      console.log(e);
    }

    if (typeof res.data !== "undefined") {
      setTicket(res.data);
      setModal2(false);
    }
    console.log("Updated ticket Received");
    console.log(res.data);
    setComment("");
    console.log("Comment after");
    console.log(comment);
    // setTicketStatus("");
    console.log(ticketStatus);
    
  };

  // ONCHANGE FOR TICKET TYPE
  const handleTicketStatus = (e) => {
    setTicketStatus(e.target.value);
    console.log(ticketStatus);
  };

  // HANDLE "Next" BUTTON CLICK
  const handleNext = () => {
    if (ticketsData.length > 0) {
      setCurrentPage(currentPage + 1);
    }
  };

  // HANDLE "Back" BUTTON CLICK
  const handleBack = () => {
    if (currentPage > 0) {
      setCurrentPage(currentPage - 1);
    }
  };

  return (
    <div className="ticket-table-container">
      <h2 className="ticket-table-heading">Ticket Table</h2>

      <div className="table-selects">
        {/* SELECT FOR STATUS  */}
        <div className="ticket-table-select-div">
          <label htmlFor="status">Filter :</label>
          <select
            name="status"
            id="status"
            value={selectedStatus}
            onChange={handleSelectChange}
          >
            <option value="ALL">ALL</option>
            <option value="OPEN">OPEN</option>
            <option value="PROGRESS">PROGRESS</option>
            <option value="CLOSED">CLOSED</option>
          </select>
        </div>

        {/* SELECT FOR PARAMS  */}
        <div className="ticket-table-select-div">
          <label htmlFor="status">Tickets :</label>
          <select
            name="status"
            id="param-status"
            value={selectedParam}
            onChange={handleParamChange}
          >
            <option value="ALL">ALL</option>
            <option value="adminDept">My Department</option>
            <option value="myTickets">My Tickets</option>
          </select>
        </div>
      </div>

      {/* TICKET TABLE  */}
      <table>
        <thead>
          <tr className="ticket-table-row">
            <th className="ticket-table-head">Title</th>
            <th className="ticket-table-head">Department</th>
            <th className="ticket-table-head">Status</th>
            <th className="ticket-table-head">Created by</th>
            <th className="ticket-table-head">Last Updated</th>
            <th className="ticket-table-head">View Details</th>
          </tr>
        </thead>

        <tbody>
          {ticketsData &&
            ticketsData?.map((e) => {
              return (
                <tr key={e.ticketId}>
                  <td className="ticket-table-data">{e.title}</td>
                  <td className="ticket-table-data">{e.departmentName}</td>
                  <td className="ticket-table-data">{e.status}</td>
                  <td className="ticket-table-data">{e.memberName}</td>
                  <td className="ticket-table-data">{e.lastUpdatedOn}</td>
                  <td className="ticket-table-data">
                    <button
                      className="ticket-table-view-btn-modal"
                      onClick={() => toggleModal(e.ticketId)}
                    >
                      view details
                    </button>
                      {/* VIEW TICKET MODAL  */}
                    {modal && (
                      <div className="view-ticket-modal">
                        <div onClick={toggleModal} className="overlay"></div>
                        <div className="view-ticket-modal-content">

                           {/* TICKET DETAILS  */}
                          <div className="view-ticket-modal-left">
                            <form
                              action=""
                              method="POST"
                              className="view-ticket-form"
                            >
                              {/* TICKET TITLE  */}
                              <div className="view-ticket-input-area">
                                <h3>Title :</h3>
                                <p>{ticket.title}</p>
                              </div>

                              {/* TICKET DESC  */}
                              <div
                                className="view-ticket-input-area "
                                id="ticket-desc"
                              >
                                <h3>Description :</h3>
                                <textarea
                                  name=""
                                  id=""
                                  cols="30"
                                  rows="10"
                                  disabled
                                >
                                  {ticket.description}
                                </textarea>
                              </div>

                              {/* TICKET TYPE  */}
                              <div className="view-ticket-input-area">
                                <h3>Ticket Type :</h3>
                                <p>{ticket.ticketType}</p>
                              </div>

                              {/* TICKET CURRENT STATUS  */}
                              <div className="view-ticket-input-area">
                                <h3>Current Status :</h3>
                                <p>{ticket.status}</p>
                              </div>

                              {/* TICKET CHANGE STATUS  */}
                              <div className="view-ticket-input-area">
                                <h3>Change Status :</h3>
                                <select
                                  name="ticketstatus"
                                  id="ticketstatus"
                                  value={ticketStatus}
                                  onChange={(e) => handleTicketStatus(e)}
                                  required
                                >
                                  <option value="">--- Select ---</option>
                                  <option value="PROGRESS">PROGRESS</option>
                                  <option value="CLOSED">CLOSED</option>
                                </select>
                              </div>

                              {/* TICKET DEPARTMENT  */}
                              <div className="view-ticket-input-area">
                                <h3>Department :</h3>
                                <p>{ticket.departmentName}</p>
                              </div>

                              {/* TICKET CREATED BY  */}
                              <div className="view-ticket-input-area">
                                <h3>Created by :</h3>
                                <p>{ticket.memberName}</p>
                              </div>

                              {/* TICKET CREATED TIME  */}
                              <div className="view-ticket-input-area">
                                <h3>Created On :</h3>
                                <p>{ticket.createdOn}</p>
                              </div>

                              {/* TICKET LAST UPDATED TIME  */}
                              <div className="view-ticket-input-area">
                                <h3>Last Updated :</h3>
                                <p>{ticket.lastUpdatedOn}</p>
                              </div>
                            </form>
                          </div>

                           {/* COMMENT AREA  */}
                          <div className="view-ticket-modal-right">

                            {/* TICKET COMMENTS  */}
                            <div className="view-ticket-comment-list">
                              {ticket.comments.map((c) => {
                                return (
                                  <div
                                    key={c.commentId}
                                    className="view-ticket-comment"
                                  >
                                    <h3>{c.userName}</h3>
                                    <p>{c.content}</p>
                                  </div>
                                );
                              })}
                            </div>

                            {/* TICKET COMMENT AREA  */}
                            <div className="view-ticket-create-comment-area">
                              <form action="" ref={formRef}>
                                <label htmlFor="">Comment</label>
                                <textarea
                                  name="comment"
                                  id="comment"
                                  cols="30"
                                  rows="10"
                                  onChange={(e) => setComment(e.target.value)}
                                ></textarea>
                              </form>

                              <button onClick={handleUpdateTicket}>
                                Update
                              </button>
                            </div>

                          </div>
                          
                          {/* TOGGLE CLOSE BUTTON  */}
                          <button
                            className="view-ticket-close-modal"
                            onClick={toggleModal}
                          >
                            CLOSE
                          </button>
                        </div>

                        {/* DISPLAY COMMENT FIRST */}
                        {modal2 && (
                          <div className="dept-modal">
                            <div className="dept-modal-content">
                              <h3>Comment first</h3>
                              <button
                                className="dept-close-modal"
                                onClick={toggleModal2}
                              >
                                CLOSE
                              </button>
                            </div>
                          </div>
                        )}
                      </div>
                    )}
                  </td>
                </tr>
              );
            })}
        </tbody>
      </table>

      {/* NOT FOUND IMAGE  */}
      {ticketsData && ticketsData?.length === 0 && (
        <div className="not-found-img-div">
          <img src="NoDataFound.jpg" className="not-found-img" />
          <h3>Go Back</h3>
        </div>
      )}

      {/* "Back" and "Next" BUTTONS */}
      <div className="back-next-btn">
        <button onClick={handleBack} disabled={currentPage === 0}>
          Back
        </button>
        <button onClick={handleNext} disabled={ticketsData.length === 0}>
          Next
        </button>
      </div>

      {/* {isLoggedIn && (
        <PopUp
          openPopup={isLoggedIn}
          customHeading="Login Successfull"
          customText={`Welcome ${memberUserName}`}
        />
      )} */}
    </div>
  );
}

export default TicketTable;
