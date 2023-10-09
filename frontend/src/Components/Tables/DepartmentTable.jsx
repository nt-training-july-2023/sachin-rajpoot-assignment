import React, { useEffect, useState } from "react";
import axios from "axios";
import "../../App.css";
import PopUp from "../PopUp/PopUp";
import Table from "../Table/Table";

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
  const [newDeptModal, SetNewDeptModal] = useState(false);
  const [customMessage, setCustomMessage] = useState("");
  const [department, setDepartment] = useState();
  const [departmentNameError, setDepartmentNameError] = useState("");
  const [newDeptSuccess, SetNewDeptSuccess] = useState(false);
  const [newDeptFail, SetNewDeptFail] = useState(false);

  // TOGGLES THE MODAL FOR CREATE NEW DEPARTMENT
  const toggleNewDeptModal = () => {
    SetNewDeptModal(!newDeptModal);
  };
  if (newDeptModal) {
    document.body.classList.add("active-modal");
  } else {
    document.body.classList.remove("active-modal");
  }

  // TOGGLES THE CREATE NEW DEPT SUCCESS POP UP
  const toggleNewDeptModalSuccess = () => {
    SetNewDeptSuccess(!newDeptSuccess);
  };

  // TOGGLES THE CREATE NEW DEPT FAIL POP-UP
  const toggleNewDeptModalFail = () => {
    SetNewDeptFail(!SetNewDeptFail);
  };

  // ON CHANGE -> CREATE NEW DEPARTMENT
  const handleDepartmentNameChange = (e) => {
    setDepartment(e.target.value.trim());
    let deptName = e.target.value;
    if (deptName.trim() === "") {
      setDepartmentNameError("name can not be empty.");
    } else if (deptName.trim().length < 2 || deptName.trim().length > 20) {
      setDepartmentNameError(
        "Name must be minimum 2 and maximum 20 characters long."
      );
    } else {
      setDepartmentNameError("");
    }
  };

  // NEW DEPARTMENT FORM SUBMIT
  const handleDepartmentFormSubmit = async (event) => {
    event.preventDefault();
    if (departmentNameError !== "") {
      return;
    }
    const headers = {
      email: memberEmail,
      password: memberPassword,
    };
    const config = {
      headers: headers,
    };
    let departmentObj = {
      departmentName: "",
    };
    departmentObj.departmentName = department;
    axios
      .post(
        "http://localhost:8080/api/department/create/auth",
        departmentObj,
        config
      )
      .then((response) => {
        setTableRender(!tableRender);
        SetNewDeptModal(false);
        SetNewDeptSuccess(true);
      })
      .catch((err) => {
        SetNewDeptModal(false);
        SetNewDeptFail(true);
      });
  };

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

  // TOGGLES -> DELETE DEPT SUCCESS DELETE POP-UP
  const toggleSuccess = () => {
    setModal(!modal);
  };

  // TOGGLES THE DELETE DEPT FAIL MODAL -> POP UP
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
      <div className="dept-heading-section">
        {/* DEPARTMENT TABLE HEADING  */}
        <h2 className="ticket-table-heading">Department Table</h2>

        <a href="#" onClick={toggleNewDeptModal} className="dept-btn-modal">
          Add Department
        </a>
        {newDeptModal && (
          <div className="dept-modal">
            <div onClick={toggleNewDeptModal} className="overlay"></div>
            <div className="dept-modal-content">
              <h2>Add New Department</h2>
              <form action="" onSubmit={handleDepartmentFormSubmit}>
                <label htmlFor="department">Department Name :</label>
                <input
                  type="text"
                  placeholder="Enter Department Name"
                  onChange={(e) => handleDepartmentNameChange(e)}
                  required
                />
                {departmentNameError && (
                  <span className="error-message">{departmentNameError}</span>
                )}
                <input
                  type="submit"
                  value="ADD"
                  className="dept-form-submit-btn"
                />
              </form>
              <button className="dept-close-modal" onClick={toggleNewDeptModal}>
                CLOSE
              </button>
            </div>
          </div>
        )}
      </div>

      {/* DEPARTMENT TABLE  */}
      <Table
        headers={["Serial No.", "Name", "Remove"]}
        data={departmentsData.map((e, index) => [index + 1, e.departmentName])}
        onRemoveClick={(index) =>
          handleDeletedepartment(
            departmentsData[index].departmentId,
            departmentsData[index].departmentName
          )
        }
      />

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
          toggleSuccess={toggleSuccess}
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

      {/* POP UP ON SUCCESS  */}
      {newDeptSuccess && (
        <PopUp
          openPopup={newDeptSuccess}
          customHeading="Created Successfully"
          customImageSrc="404-Tick.png"
          toggleModal={toggleNewDeptModalSuccess}
        />
      )}

      {/* POP UP ON FAIL  */}
      {newDeptFail && (
        <PopUp
          openPopup={newDeptFail}
          customHeading="Department Already Exists"
          customImageSrc="fail.png"
          toggleError={toggleNewDeptModalFail}
        />
      )}
    </div>
  );
}

export default DepartmentTable;
