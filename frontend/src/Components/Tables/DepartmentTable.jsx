import React, { useEffect, useState } from "react";
import axios from "axios";
import "../../App.css";
import PopUp from "../PopUp/PopUp";

function DepartmentTable() {
  const [departmentsData, setDepartmentsData] = useState([]);
  const [tableRender, setTableRender] = useState(false);
  const [currentPage, setCurrentPage] = useState(0);
  const memberEmail = JSON.parse(localStorage.getItem("member"))?.email;
  const loggedInUserDeptName = JSON.parse(
    localStorage.getItem("member")
  )?.departmentName;
  const memberPassword = JSON.parse(localStorage.getItem("memberPassword"));
  const [modal, setModal] = useState(false);
  const [Error, setError] = useState(false);
  const [customMessage, setCustomMessage] = useState("");

  // GETTING ALL DEPARTMENT DATA
  useEffect(() => {
    // HEADERS SENT
    const headers = {
      email: memberEmail,
      password: memberPassword,
    };
    // CONFIG CONTAINING HEADERS
    const config = {
      headers: headers,
    };
    axios
      .get(
        `http://localhost:8080/api/department/getAll/pageNumber/${currentPage}`,
        config
      )
      .then((response) => {
        setDepartmentsData(response.data);
      })
      .catch((err) => console.log(err));
  }, [tableRender, currentPage]);

  // DELETING DEPARTMENT
  const handleDeletedepartment = (departmentId, departmentName) => {
    if (departmentName === loggedInUserDeptName) {
      setCustomMessage("Can not delete your department.");
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
        `http://localhost:8080/api/department/delete/departmentId/${departmentId}`,
        config
      )
      .then((response) => {
        setModal(true);
        setTableRender(!tableRender);
      })
      .catch((err) => {
        setCustomMessage("Something went wrong,Check network connection");
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
    if (departmentsData.length > 0) {
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
      {/* DEPARTMENT TABLE HEADING  */}
      <h2 className="ticket-table-heading">Department Table</h2>

      {/* DEPARTMENT TABLE  */}
      <table>
        <thead>
          <tr className="ticket-table-row">
            <th className="ticket-table-head">Serial No.</th>
            <th className="ticket-table-head">Name</th>
            <th className="ticket-table-head">Remove</th>
          </tr>
        </thead>
        <tbody>
          {departmentsData &&
            departmentsData.map((e, index) => (
              <tr key={e.departmentId}>
                <td className="ticket-table-data">{index + 1}</td>
                <td className="ticket-table-data">{e.departmentName}</td>
                <td className="ticket-table-data">
                  <button
                    className="delete-btn"
                    onClick={() =>
                      handleDeletedepartment(e.departmentId, e.departmentName)
                    }
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
        </tbody>
      </table>

      {/* NOT FOUND IMAGE  */}
      {departmentsData && departmentsData.length === 0 && (
        <div className="not-found-img-div">
          <img src="NoDataFound.jpg" className="not-found-img" />
          <h3>No Data Found</h3>
        </div>
      )}

      {/* "Back" and "Next" BUTTONS */}
      {departmentsData && (
        <div className="back-next-btn">
          <button onClick={handleBack} disabled={currentPage === 0}>
            Back
          </button>
          <button onClick={handleNext} disabled={departmentsData?.length === 0}>
            Next
          </button>
        </div>
      )}

      {/* POP UP ON SUCCESS  */}
      {modal && (
        <PopUp
          openPopup={modal}
          customHeading="Department Deleted"
          customImageSrc="404-Tick.png"
          toggleModal={toggleModal}
        />
      )}

      {/* POP UP ON FAIL  */}
      {Error && (
        <PopUp
          openPopup={Error}
          customHeading={customMessage}
          customImageSrc="fail.png"
          toggleError={toggleError}
        />
      )}
    </div>
  );
}

export default DepartmentTable;
