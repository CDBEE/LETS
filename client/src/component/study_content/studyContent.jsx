import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { formatDate } from "common/utils";
import CommentContainer from "../comment_container/commentContainer";
import LikesAndViews from "../likes_and_views/likesAndViews";
import studyService from "service/study_service";
import { setPost } from "store/write";
import StudyButtons from "../study_buttons/studyButtons";
import styles from "./studyContent.module.css";
import { FaArrowLeft } from "react-icons/fa";
import { toast } from "react-toastify";
import RecommendPost from "../recomment_post/RecommendPost";

// todo data 받아오고 formatDate 잘 출력되는지 확인

const StudyLanguage = ({ languages }) => {
  const usedLanguage = languages;
  return (
    <section className={styles.usedLanguageWrapper}>
      <div className={styles.usedLanguageInfo}>사용 언어 :</div>
      <ul className={styles.languageList}>
        {usedLanguage.map((lang, i) => (
          <LangItem Language={lang} key={i}></LangItem>
        ))}
      </ul>
    </section>
  );
};

const LangItem = ({ Language }) => {
  return <li className={styles.languageItem}>{Language}</li>;
};

const StudyContent = ({ id }) => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  // const user = useSelector((state) => state.user);
  const user = {id : 'test ID',
                nickName: "test Nic"};
  // const read = useSelector((state) => state.read);
  const read = {
      post : {
        title: "sample title",
        nickname: "sample nick",
        createdAt: "2021.01.05",
        language: ["React", "Vue"],
      }
  }

  const handleDelete = async (id) => {
    await studyService.deleteStudy(id);
    document.body.style.overflow = "auto";
    toast.success("글 삭제가 완료되었어요!", {
      position: "top-right",
      autoClose: 3000,
    });
    navigate("/");
  };

  const handleEnd = async (editValue) => {
    await studyService.editClose(id, editValue);
  };

  const handleEdit = (dispatch, navigate) => {
    dispatch(setPost(read.post));
    navigate("/register");
  };

  const handleBack = () => {
    navigate(-1);
  };

  return (
    <div className={styles.wrapper}>
      <section className={styles.postHeader}>
        <FaArrowLeft
          size="30"
          color="808080"
          cursor="pointer"
          onClick={handleBack}
        />
        <div className={styles.title}>{read.post.title}</div>
        <div className={styles.userAndDate}>
          <div className={styles.user}>
            <img
              className={styles.userImg}
              src={read.post.imagePath}
              alt="userImg"
            />
            <div className={styles.userName}>{read.post.nickname}</div>
          </div>
          <div className={styles.registeredDate}>
            {/* {formatDate(read.post.createdAt)} */}
          </div>
        </div>
        {user.nickName === read.post.nickname && (
          <StudyButtons
            navigate={navigate}
            dispatch={dispatch}
            handleEdit={handleEdit}
            handleDelete={() => handleDelete(id)}
            handleEnd={handleEnd}
            isClosed={read.post.isClosed}
          ></StudyButtons>
        )}
        {/* RecommendPost */}
        <RecommendPost/>

        <StudyLanguage languages={read.post.language}></StudyLanguage>
      </section>
      <div className={styles.postContentWrapper}>
        <div
          className={styles.postContent}
          dangerouslySetInnerHTML={{ __html: read.post.content }}
        ></div>
      </div>

      <section className={styles.modalComment}>
        <LikesAndViews
          views={read.post.views}
          likeUser={read.post.likes}
          totalLikes={read.post.totalLikes}
          studyId={read.post.id}
          userId={user.id}
        ></LikesAndViews>
        <div className={styles.postComment}>
          <CommentContainer id={read.post.id}></CommentContainer>
        </div>
      </section>
    </div>
  );
};

export default StudyContent;
