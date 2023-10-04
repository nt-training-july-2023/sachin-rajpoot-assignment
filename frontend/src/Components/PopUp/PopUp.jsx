import React from "react";
import "./PopUp.css";
import { useNavigate } from "react-router-dom";
function PopUp({
  openPopup,
  customHeading,
  customText,
  customImageSrc,
  toggleModal,
  toggleError,
  toggleSuccess,
}) {
  const navigate = useNavigate();
  let popup = document.getElementById("popup");

  const closePopup = () => {
    toggleModal && toggleModal();
    toggleError && toggleError();
    toggleSuccess && toggleSuccess();
  };

  return (
    <div className="popup-contaier">
      <div id="popup" className={`popup ${openPopup ? "open-popup" : ""}`}>
        {customImageSrc && <img src={customImageSrc} alt="Custom Image" />}
        {customHeading && <h2>{customHeading}</h2>}
        {customText && <p>{customText}</p>}
        <button type="button" onClick={closePopup}>
          OK
        </button>
      </div>
    </div>
  );
}

export default PopUp;
