import React, { useEffect, useState } from "react";
import axios from "axios";
import "../../App.css";
import PopUp from "../PopUp/PopUp";
import Table from "../Table/Table";

function UserTable() {
  const [usersData, setUsersData] = useState([]);
  const memberEmail = JSON.parse(localStorage.getItem("member"))?.email;
  const loggedInUserId = JSON.parse(localStorage.getItem("member"))?.memberId;
  const memberPassword = JSON.parse(localStorage.getItem("memberPassword"));
  const [tableRender, setTableRender] = useState(false);
  const [currentPage, setCurrentPage] = useState(0);
  const [modal, setModal] = useState(false);
  const [Error, setError] = useState(false);
  const [customMessage, setCustomMessage] = useState("");

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
      })
      .catch((err) => console.log(err));
  }, [tableRender, currentPage, modal]);

  // HANDLE DELETE USER
  const handleDeleteUser = (memberId) => {
    if (memberId === loggedInUserId) {
      setCustomMessage("Can not delete yourself.");
      setError(true);
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
      })
      .catch((err) => {
        setCustomMessage("something went wrong check network connections");
        setError(true);
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

      <Table
        headers={[
          "Serial No.",
          "Name",
          "Department",
          "Role",
          "Email",
          "Remove",
        ]}
        data={usersData.map((user, index) => [
          index + 1,
          user.name,
          user.departmentName,
          user.role,
          user.email,
        ])}
        onRemoveClick={(index) => handleDeleteUser(usersData[index].memberId)}
      />

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
