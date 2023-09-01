import React from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";

function Navbar() {
  function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
      x.className += " responsive";
    } else {
      x.className = "topnav";
    }
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
