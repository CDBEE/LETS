import React, { useState } from "react";
import styles from "./studyButtons.module.css";
import Modal from "../modal/modal_component/modal";
import CancelButton from "../cancel_button/cancelButton";

// todo 나중에 데이터 받아오는 것 확인 / Portal 에러 수정하고나서 Modal로 창 띄워지는 것 확인

const StudyButtons = ({
  navigate,
  dispatch,
  handleEdit,
  handleDelete,
  isClosed,
  handleEnd,
}) => {
  const [showPopup, setShowPopup] = useState(false);
  const [close, setClose] = useState(isClosed);
  const [isDeleteButton, setIsDeleTeButton] = useState(false);

  const openModal = (target) => {
    if (target === "deleteModal") setIsDeleTeButton(true);
    else setIsDeleTeButton(false);
    document.body.style.overflow = "hidden";
    setShowPopup((state) => !state);
  };

  const closeModal = () => {
    document.body.style.overflow = "auto";
    setShowPopup((state) => !state);
  };

  const handleStudy = () => {
    handleEnd(!close);
    closeModal();
    setClose((state) => !state);
  };

  return (
    <>
      <section className={styles.buttonWrapper}>
        <button onClick={() => openModal("endModal")}>
          {close === true ? "마감 취소" : "마감"}
        </button>
        <button onClick={() => handleEdit(dispatch, navigate)}>수정</button>
        <button onClick={() => openModal("deleteModal")}>삭제</button>
      </section>

      {/* <Modal visible={showPopup} onClose={closeModal}> */}
        {isDeleteButton ? (
          <CancelButton
            confirmMsg="작성하신 글을 삭제 하시겠어요?"
            positiveMsg="네, 삭제할래요"
            negativeMsg="아니요"
            onCancel={closeModal}
            onPublish={handleDelete}
          ></CancelButton>
        ) : (
          <CancelButton
            confirmMsg={
              close === true
                ? "마감을 취소하시겠어요?"
                : "마감 처리 하시겠어요?"
            }
            positiveMsg={close === true ? "네, 취소할게요" : "네, 마감할게요"}
            negativeMsg="아니요"
            onCancel={closeModal}
            onPublish={handleStudy}
          ></CancelButton>
        )}
      {/* </Modal> */}
    </>
  );
};

export default StudyButtons;
