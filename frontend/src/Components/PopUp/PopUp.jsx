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
}) {
  const navigate = useNavigate();
  let popup = document.getElementById("popup");
  //   const openPopup = () => {
  //     popup.classList.add("open-popup");
  //   };
  const closePopup = () => {
    toggleModal && toggleModal();
    toggleError && toggleError();
    // popup.classList.remove("open-popup");
  };

  return (
    <div className="popup-contaier">
      <div id="popup" className={`popup ${openPopup ? "open-popup" : ""}`}>
        {/* <img src="404-tick.png" alt="Tick Image" /> */}
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
