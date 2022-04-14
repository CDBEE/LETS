import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import CommentInput from "../comment_input/commentInput";
import CommentList from "../comment_list/commentList";
import Modal from "../modal/modal_component/modal";
import studyService from "service/study_service";

const CommentContainer = ({ id }) => {
  const [commentList, setCommentList] = useState([]);
  const [content, setContent] = useState("");
  const [isComplete, setIsComplete] = useState(false); // 갱신시 useEffect 발생용 state 
  // const modalVisible = useSelector((state) => state.loginStep.modalVisible); 로그인 화면 띄울지 store 에서 가져옴
  const [modalVisible, setModalVisible] = useState(false);
  const dispatch = useDispatch();

  const openModal = () => {
    document.body.style.overflow = "hidden";
    dispatch(setModalVisible(true));
  };

  const closeModal = () => {
    document.body.style.overflow = "auto";
    dispatch(setModalVisible(false));
  };


  // const userId = useSelector((state) => state.user.id);
  const userId = "testId";

  // 댓글 등록 버튼
  const onRegisterClick = async (e) => {
    if (userId === undefined) {
      openModal();
      return;
    }
    await studyService.registerComment({ id, content });
    setContent("");
    setIsComplete((isComplete) => !isComplete);
  };

  useEffect(() => {
    studyService.getComments(id).then((response) => {
      setCommentList(response.data.comments);
    });
  }, [id, isComplete]);

  return (
    <>
      <CommentInput
        content={content}
        setContent={setContent}
        onRegisterClick={onRegisterClick}
        count={commentList.length}
      ></CommentInput>
      <CommentList
        CommentList={commentList}
        setIsComplete={setIsComplete}
        isComplete={isComplete}
      ></CommentList>
        {/* <Modal visible={modalVisible} name="login" onClose={closeModal}> */}
          {/* 로그인 화면 */}
      {/* </Modal> */}
    </>
  );
};

export default CommentContainer;
