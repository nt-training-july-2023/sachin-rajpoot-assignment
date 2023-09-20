import React, { useEffect, useState } from "react";
import axios, { HttpStatusCode } from "axios";
import '../App.css'

function DepartmentTable() {
  const [departmentsData, setDepartmentsData] = useState([]);
  const [tableRender, setTableRender] = useState(false);
  const [currentPage, setCurrentPage] = useState(0);
  const memberEmail = JSON.parse(localStorage.getItem("member"))?.email;

  useEffect(() => {
    const headers = {
      email: memberEmail,
      password: "MTIzNDU2Nzg=",
    };
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
        console.log(response);
      })
      .catch((err) => console.log(err));
  }, [tableRender, currentPage]);

  const handleDeletedepartment = (departmentId) => {
    console.log(departmentId);
    const headers = {
      email: memberEmail,
      password: "MTIzNDU2Nzg=",
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
        setTableRender(!tableRender);
        console.log(response);
      })
      .catch((err) => console.log(err));
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
      <h2 className="ticket-table-heading">Department Table</h2>

      <table>
        <thead>
          <tr className="ticket-table-row">
            <th className="ticket-table-head">Name</th>
            <th className="ticket-table-head">Remove</th>
          </tr>
        </thead>
        <tbody>
          {departmentsData.map((e) => (
            <tr key={e.departmentId}>
              <td className="ticket-table-data">{e.departmentName}</td>
              <td className="ticket-table-data">
                <button className="delete-btn" onClick={() => handleDeletedepartment(e.departmentId)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* "Back" and "Next" BUTTONS */}
      <div className="back-next-btn">
        <button onClick={handleBack} disabled={currentPage === 0}>
          Back
        </button>
        <button onClick={handleNext} disabled={departmentsData.length === 0}>
          Next
        </button>
      </div>

    </div>
  );
}

export default DepartmentTable;
