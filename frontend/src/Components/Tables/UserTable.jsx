import React, { useEffect, useState } from "react";
import axios from "axios";
import "../../App.css";
import PopUp from "../PopUp/PopUp";

function UserTable() {
  const [usersData, setUsersData] = useState([]);
  const memberEmail = JSON.parse(localStorage.getItem("member"))?.email;
  const loggedInUserId = JSON.parse(localStorage.getItem("member"))?.memberId;
  const memberPassword = JSON.parse(localStorage.getItem("memberPassword"));
  const [tableRender, setTableRender] = useState(false);
  const [currentPage, setCurrentPage] = useState(0);
  const [modal, setModal] = useState(false);
  const [Error, setError] = useState(false);
  const[customMessage, setCustomMessage] = useState(""); 

  // GETTNG ALL MEMBER DATA
  useEffect(() => {
    const headers = {
      email: memberEmail,
      password: memberPassword,
    };
    const config = {
      headers: headers,
    };
    axios
      .get(
        `http://localhost:8080/api/member/getAll/auth/pageNumber/${currentPage}`,
        config
      )
      .then((response) => {
        setUsersData(response.data);
        console.log(response);
      })
      .catch((err) => console.log(err));
  }, [tableRender, currentPage, modal]);

  // HANDLE DELETE USER
  const handleDeleteUser = (memberId) => {
    console.log(memberId);
    if(memberId === loggedInUserId) {
      setCustomMessage("Can not delete yourself.")
      setError(true)
      return;
    }
    const headers = {
      email: memberEmail,
      password: memberPassword,
    };
    const config = {
      headers: headers,
    };
    axios
      .delete(
        `http://localhost:8080/api/member/delete/memberId/${memberId}`,
        config
      )
      .then((response) => {
        setModal(true);
        setTableRender(!tableRender);
        console.log(response);
      })
      .catch((err) => {
        setCustomMessage("something went wrong check network connections");
        setError(true)
        console.log(err)
      });
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

  // HANDLE "Next" BUTTON CLICK
  const handleNext = () => {
    if (usersData.length > 0) {
      setCurrentPage(currentPage + 1);
    }
  };

  // HANDLE "Back" BUTTON CLICK
  const handleBack = () => {
    if (currentPage >= 1) {
      setCurrentPage(currentPage - 1);
    }
  };

  return (
    <div className="ticket-table-container">
      <h2 className="ticket-table-heading">User Table</h2>

      {/* USER TABLE  */}
      <table>
        <thead>
          <tr className="ticket-table-row">
          <th className="ticket-table-head">Serial No.</th>
            <th className="ticket-table-head">Name</th>
            <th className="ticket-table-head">Department</th>
            <th className="ticket-table-head">Role</th>
            <th className="ticket-table-head">Email</th>
            <th className="ticket-table-head">Remove</th>
          </tr>
        </thead>
        <tbody>
          {usersData &&
            usersData
              .map((user, index) => (
                <tr key={user.memberId}>
                   <td className="ticket-table-data">{index + 1}</td>
                  <td className="ticket-table-data">{user.name}</td>
                  <td className="ticket-table-data">{user.departmentName}</td>
                  <td className="ticket-table-data">{user.role}</td>
                  <td className="ticket-table-data">{user.email}</td>
                  <td className="ticket-table-data">
                    <button
                      className="delete-btn"
                      onClick={() => handleDeleteUser(user.memberId)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
        </tbody>
      </table>

      {/* NOT FOUND IMAGE  */}
      {usersData && usersData.length === 0 && (
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
        <button onClick={handleNext} disabled={usersData.length === 0}>
          Next
        </button>
      </div>

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
          customImageSrc="fail.png"
          customText={customMessage}
          toggleError={toggleError}
        />
      )}
    </div>
  );
}

export default UserTable;
