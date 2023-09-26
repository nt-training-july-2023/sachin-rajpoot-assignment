import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";

function Navbar() {
  const [modal, setModal] = useState(false);

  const toggleModal = () => {
    setModal(!modal);
  };

  if (modal) {
    document.body.classList.add("active-modal");
  } else {
    document.body.classList.remove("active-modal");
  }

  return (
    <div className="nav_bar">
      <div className="logo">
        <p>GMS</p>
      </div>

      <div className="nav_links topnav" id="myTopnav">
        <div className="nav_link">
          {/* <Link  to='/login' className="link">Login</Link>  */}
          {/* <a href="#home">Home</a>  */}
          <Link to="/" className="link">
            Home
          </Link>
        </div>

        <div className="nav_link">
          {/* <Link  to='/login' className="link">Login</Link>  */}
          {/* <a href="#home">Login</a> */}
          <Link to="/login" className="link">
            Login
          </Link>
        </div>

        <div className="nav_link">
          <button onClick={toggleModal} className="btn-modal">
            New Department
          </button>
          {modal && (
            <div className="modal">
              <div onClick={toggleModal} className="overlay"></div>
              <div className="modal-content">
                <h2>Add New Department</h2>
                <form action="">
                  <label htmlFor="department">Department Name :</label>
                  <input
                    type="text"
                    placeholder="Enter Department Name"
                    required
                  />

                  <input type="submit" value="Submit" />
                </form>
                <button className="close-modal" onClick={toggleModal}>
                  CLOSE
                </button>
              </div>
            </div>
          )}
        </div>

        {/* <div className="nav_link">
          <Link to="/newuser" className="link">
            New user
          </Link>
        </div> */}

        <div class="dropdown">
          <button class="dropbtn">User 🡓</button>
          <div class="dropdown-content">
            {/* <a href="#">Link 1</a> */}
            <Link to="/newuser" className="link">
              Add new user
            </Link>
            <a href="#">Link 2</a>
            <a href="#">Link 3</a>
          </div>
        </div>

        <div class="dropdown">
          <button class="dropbtn">Ticket 🡓</button>
          <div class="dropdown-content">
            {/* <a href="#">Link 1</a> */}
            <Link to="/newdept" className="link">
              Add new ticket
            </Link>
            <a href="#">Link 2</a>
            <a href="#">Link 3</a>
          </div>
        </div>

        {/* <div className="nav_link">
          <Link to="/newdept" className="link">
            New Ticket
          </Link>
        </div> */}
      </div>
    </div>
  );
}

export default Navbar;
