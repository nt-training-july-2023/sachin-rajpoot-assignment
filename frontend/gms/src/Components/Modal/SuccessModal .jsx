import React, { useState, useEffect } from "react";

const SuccessModal = () => {
  const [modal, setModal] = useState(true);

  // TOGGLES THE MODAL FOR CREATE NEW DEPARTMENT
  //   useEffect(() => {
  //     if (isVisible) {
  //       setModal(!modal);
  //     }
  //     setModal(false);
  //   }, [isVisible]);
  //     if(isVisible){
  //       setModal(!modal);
  //     }
  const toggleModal = () => {
    setModal(!modal);
  };
  if (modal) {
    document.body.classList.add("active-modal");
  } else {
    document.body.classList.remove("active-modal");
  }

  return (
    <>
    {modal &&
        <div className="dept-modal">
        <div onClick={toggleModal} className="overlay"></div>
        <div className="dept-modal-content">
          <h2>Success</h2>
          <button className="dept-close-modal" onClick={toggleModal}>
            CLOSE
          </button>
        </div>
      </div>
    }
    </>
  );
};

export default SuccessModal;
