import React, { useEffect, useState } from "react";
import axios from "axios";
import '../App.css'

function UserTable() {
  const [usersData, setUsersData] = useState([]);
  const memberEmail = JSON.parse(localStorage.getItem("member"))?.email;
  const [tableRender, setTableRender] = useState(false);
  const [currentPage, setCurrentPage] = useState(0);

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
        `http://localhost:8080/api/member/getAll/auth/pageNumber/${currentPage}`,
        config
      )
      .then((response) => {
        setUsersData(response.data);
        console.log(response);
      })
      .catch((err) => console.log(err));
  }, [tableRender, currentPage]);

  const handleDeleteUser = (memberId) => {
    console.log(memberId);
    const headers = {
      email: memberEmail,
      password: "MTIzNDU2Nzg=",
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
        setTableRender(!tableRender);
        console.log(response);
      })
      .catch((err) => console.log(err));
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

      <table>
        <thead>
          <tr className="ticket-table-row">
            <th className="ticket-table-head">Name</th>
            <th className="ticket-table-head">Department</th>
            <th className="ticket-table-head">Role</th>
            <th className="ticket-table-head">Email</th>
            <th className="ticket-table-head">Remove</th>
          </tr>
        </thead>
        <tbody>
          {usersData.map((user) => (
            <tr key={user.memberId}>
              <td className="ticket-table-data">{user.name}</td>
              <td className="ticket-table-data">{user.departmentName}</td>
              <td className="ticket-table-data">{user.role}</td>
              <td className="ticket-table-data">{user.email}</td>
              <td className="ticket-table-data">
                <button className="delete-btn" onClick={() => handleDeleteUser(user.memberId)}>
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
        <button onClick={handleNext} disabled={usersData.length === 0}>
          Next
        </button>
      </div>
    </div>
  );
}

export default UserTable;
