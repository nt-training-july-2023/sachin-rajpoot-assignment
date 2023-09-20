import React, { useEffect, useState } from "react";
import axios from "axios";
import "./TicketTable.css";

function TicketTable() {
  const [modal, setModal] = useState(false);
  const [ticketsData, setTicketsData] = useState([]);
  const [ticket, setTicket] = useState();
  const [comment, setComment] = useState();
  const [filteredTickets, setFilteredTickets] = useState([]);
  const [selectedStatus, setSelectedStatus] = useState("ALL");
  const [currentPage, setCurrentPage] = useState(0);
  const [myTickets, setMyTickets] = useState(false);
  const [adminDept, setAdminDept] = useState(false);
  const [tableParams, setTableParams] = useState({
    myTickets: false,
    adminDept: false,
  });
  const [selectedParam, setSelectedParam] = useState("ALL");
  const name = JSON.parse(localStorage.getItem("member"))?.name;
  const memberId = JSON.parse(localStorage.getItem("member"))?.memberId;
  const memberEmail = JSON.parse(localStorage.getItem("member"))?.email;
  const memberPassword = JSON.parse(localStorage.getItem("member"))?.password;
  const itemsPerPage = 5;

  // GETTING ALL TICKETS FOR TICKET TABLE
  useEffect(() => {
    console.log("Param sent : ");
    console.log(tableParams);
    const headers = {
      email: memberEmail,
      password: "dGVzdDEyMw==",
    };
    const config = {
      headers: headers,
    };
    console.log("Headers");
    console.log(config);
    axios
      .get(
        `http://localhost:8080/api/ticket/getAll/auth/memberId/${memberId}/filter/${selectedStatus}/pageNumber/${currentPage}`,
        config,
        {
          params: tableParams,
        }
      )
      .then((response) => {
        setTicketsData(response.data);
        console.log("DATA RECEIVED");
        console.log(response);
      })
      .catch((err) => console.log(err));
  }, [selectedStatus, currentPage, tableParams]);

  // FILTER BY STATUS
  const handleSelectChange = (event) => {
    const newSelectedStatus = event.target.value;
    setCurrentPage(0);
    setSelectedStatus(newSelectedStatus);
    console.log("FILTERED TICKETS BY " + newSelectedStatus);
  };

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
  };
  if (modal) {
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
      password: "MTIzNDU2Nzg=",
    };
    const config = {
      headers: headers,
    };
    try {
      res = await axios.get(
        `http://localhost:8080/api/ticket/getbyId/ticketId/${ticketId}`,
        config
      );
    } catch (e) {
      console.log(e);
    }
    setTicket(res.data);
    console.log(res);
  };

  // UPDATES THE TICKET STATUS AND ADD COMMENT ON CLICK -> UPDATE BUTTON
  const handleUpdateTicket = async () => {
    let commentList = ticket.comments;
    commentList.push(comment);
    console.log(ticket.comments);
    console.log(ticket);
    let res = {};
    const headers = {
      email: memberEmail,
      password: "MTIzNDU2Nzg=",
    };
    const config = {
      headers: headers,
    };
    try {
      res = await axios.post(
        `http://localhost:8080/api/comment/create/ticketId/${ticket.ticketId}`,
        {
          content: comment,
          userName: ticket.memberName,
        },
        config
      );
    } catch (e) {
      console.log(e);
    }
    console.log(res.data);
    console.log(ticket);
    console.log("commented");
  };

  // HANDLE "Next" BUTTON CLICK
  const handleNext = () => {
    if (ticketsData.length > 0) {
      setCurrentPage(currentPage + 1);
    }
  };

  // HANDLE "Back" BUTTON CLICK
  const handleBack = () => {
    if (currentPage > 1) {
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
          {ticketsData.map((e) => {
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
                  {modal && (
                    <div className="view-ticket-modal">
                      <div onClick={toggleModal} className="overlay"></div>
                      <div className="view-ticket-modal-content">
                        <div className="view-ticket-modal-left">
                          <form
                            action=""
                            method="POST"
                            className="view-ticket-form"
                          >
                            <div className="view-ticket-input-area">
                              <label htmlFor="">Title :</label>
                              <input
                                type="text"
                                value={ticket.title}
                                disabled
                              />
                            </div>
                            <div className="view-ticket-input-area">
                              <label htmlFor="">Description :</label>
                              <input
                                type="text"
                                placeholder={ticket.description}
                                disabled
                              />
                            </div>
                            <div className="view-ticket-input-area">
                              <label htmlFor="tickettype">Ticket Type :</label>
                              <input
                                type="text"
                                placeholder={ticket.ticketType}
                                disabled
                              />
                            </div>
                            <div className="view-ticket-input-area">
                              <label htmlFor="status">Ticket Status :</label>
                              <input
                                type="text"
                                placeholder={ticket.status}
                                disabled
                              />
                            </div>
                            <div className="view-ticket-input-area">
                              <label htmlFor="depttype">Dept Type :</label>
                              <input
                                type="text"
                                placeholder={ticket.departmentName}
                                disabled
                              />
                            </div>
                          </form>
                        </div>

                        <div className="view-ticket-modal-right">
                          <div className="view-ticket-comment-list">

                            {ticket.comments.map((c) => {
                              return (
                                <div
                                  key={c.commenId}
                                  className="view-ticket-comment"
                                >
                                  <h3>{c.userName}</h3>
                                  <p>{c.content}</p>
                                </div>
                              );
                            })}

                          </div>

                          <div className="view-ticket-create-comment-area">
                            <label htmlFor="">Comment</label>
                            <textarea
                              name="comment"
                              id="comment"
                              cols="30"
                              rows="10"
                              onChange={(e) => setComment(e.target.value)}
                            ></textarea>
                            <button onClick={handleUpdateTicket}>Update</button>
                          </div>
                        </div>

                        <button
                          className="view-ticket-close-modal"
                          onClick={toggleModal}
                        >
                          CLOSE
                        </button>
                        
                      </div>
                    </div>
                  )}
                </td>
              </tr>
            );
          })}
        </tbody>

      </table>

      {/* "Back" and "Next" BUTTONS */}
      <div className="back-next-btn">
        <button onClick={handleBack} disabled={currentPage === 0}>
          Back
        </button>
        <button onClick={handleNext} disabled={ticketsData.length === 0}>
          Next
        </button>
      </div>

    </div>
  );
}

export default TicketTable;
