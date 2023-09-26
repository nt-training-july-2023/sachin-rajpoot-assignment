import React from "react";
import "./Footer.css";
function Footer() {
  return (
    <div className="footer">

      <div className="one">
        <p>
          Your <br /> Feedback <br />
          Matters
        </p>
      </div>

      <div className="two">
        <div className="contact">
          <h3>Contact us</h3>
          <div className="address">
            <ul>
              <li>NucleusTeq</li>
              <li>Indore, Madhya Pradesh</li>
              <li>Email - nucleusteq.com</li>
              <li>Phone +1234567890</li>
            </ul>
          </div>
        </div>
      </div>

      <div className="three">
        <div className="socials">
          <h3>Socials</h3>
          <div className="social_icons">

            <ul>
              <li>Instagram</li>
              <li>Facebook</li>
              <li>Youtube</li>
              <li>Twitter</li>
            </ul>
          </div>
        </div>
      </div>

      
    </div>
  );
}

export default Footer;
