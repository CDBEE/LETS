/*eslint-disable*/
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Modal } from "react-bootstrap";
import { ToastContainer, toast } from "react-toastify";

import "react-toastify/dist/ReactToastify.css";
import logo from "logo.svg";
import languageList from "common/languageList";
import Navbar from "component/nav_bar/navbar";
import "./Settings.css";
import axios from "axios";
import LikeLanguages from "component/like_languages/likeLanguages";

const TEST = "테스트 닉네임";

function Settings() {
  // const [isPressed, setIsPressed] = useState(false);
  // const [show, setShow] = useState(false);
  const [options, setOptions] = useState(languageList);
  // const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const notify = () => toast("변경이 완료되었어요!");
  const navigate = useNavigate();

  const backHistory = () => {};
  //좋아하는 언어 (선택된)
  const [likeLanguages, setLikeLanguages] = useState([]);
  console.log("likeLanguages", likeLanguages);

  //[이미지]
  //이미지 관련 변수
  const [imgSrc, setImgSrc] = useState(logo);

  //이미지 관련 함수
  // 1. 이미지 등록 함수 실행
  const onChangeImg = (evt) => {
    console.log("onChangeImgHandle 밑에 있는 거 정상 작동");
    if (evt.target.files.length) {
      const imgTarget = evt.target.files[0];
      const fileReader = new FileReader();
      fileReader.readAsDataURL(imgTarget);
      fileReader.onload = function (e) {
        setImgSrc(e.target.result);
      };
    } else {
      setImgSrc(logo);
    }
  };

  //2. 이미지 초기화 함수
  const onResetImg = (e) => {
    e.preventDefault();
    setImgSrc(logo);
  };

  //[닉네임]
  const [nickname, setNickname] = useState(TEST);
  console.log("nickname", nickname);
  //[서버]
  //서버 전공 관련 변수

  //서버에 전송할 obejct
  const [settingData, setSettingData] = useState({});

  //서버에 전송할 obejct 만드는 함수
  const onHandleSubmit = (e) => {
    console.log("e: " + imgSrc);
    console.log("onHandleSubmit 위에 있는 거 정상 작동");
    const object = new FormData();

    object.append("img", imgSrc);
    object.append("nickname", nickname);
    // object.append("img", evt.target.img.files[0]);
    // // object.append("userImg", evt.target.userImg.attributes.src.value);
    // try {
    //   console.log(object);
    //   const result = await postCreate(object);
    //   console.log(result);
    //   props.history.push("/");
    // } catch (e) {
    //   console.log(e);
    // }
    //console.log(object);
  };
  const onCompleteClick = () => {
    // let payload = {
    //   id: user.id,
    //   likeLanguages: likeLanguages.map((element) => element.value),
    // };

    navigate("/");
  };
  const onSetToast = (e) => {
    e.preventDefault();
    toast("변경이 완료되었어요!", {
      position: "top-right",
      autoClose: 3000,
    });
  };

  //서버에 전송하는 함수
  const postCreate = async (object) => {
    try {
      const { data } = await axios.post(`${url}/projects/read`, object, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      return data.data;
    } catch (e) {
      console.error("[FAIL] POST ANSWER", e);
      return e;
    }
  };

  return (
    <div className="settingMain">
      {/* <form onSubmit={onHandleSubmit} id="settingForm"> */}
      <Navbar />
      <form>
        <ToastContainer />
        <h1>내 정보 수정</h1>
        <div className="userImageUpload">
          <img
            className="userImageUploadUserImg"
            src={imgSrc}
            alt="user avatar"
          />
          <div className="userImageUploadImageControl">
            <label className="userImageUploadCustomLabel">
              이미지 선택
              <div>
                <input name="img" type="file" onChange={onChangeImg}></input>
              </div>
              <button type="submit" value="등록하기"></button>
            </label>
            <button
              className="userImageUploadButtonImageDelete"
              onClick={onResetImg}
            >
              이미지 제거
            </button>
          </div>
        </div>
        <div className="settingTitleWrapper">
          <h3>닉네임</h3>
          <input
            className="nicknameInput"
            type="text"
            name="nickNameInput"
            value={nickname}
            onChange={(e) => {
              setNickname(e.target.value);
            }}
          />
        </div>
        <p className="settingDescription">Hola에서 사용되는 이름입니다.</p>
        <hr />
        <div className="settingTitleWrapper settingLikeLanguages">
          <h3>관심 기술 태그</h3>

          <div className="likeLanguageWrapper">
            <LikeLanguages
              placeholder={"관심 태그 선택"}
              likeLanguages={likeLanguages}
              setLikeLanguages={setLikeLanguages}
            ></LikeLanguages>
          </div>
        </div>
        <p className="description">관심 있는 기술 태그를 등록해주세요.</p>
        <hr />
        <button
          type="submit"
          form="settingForm"
          className="settingButtonComplete"
          onClick={() => {
            console.log("submit button이 클릭되었습니다.");
            axios
              .patch(
                "https://lets-team-project.herokuapp.com/api/users/setting",
                { name: "태희", id: 1, socialLoginId: "108552210897697745160" },
                {
                  headers: {
                    Authorization:
                      "Bearer eyJyb2xlIjoiUk9MRV9VU0VSIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiIyIiwiaWF0IjoxNjQ0ODIyMDMyLCJleHAiOjE2NDQ4MjM4MzJ9.AzFz8DCiGNARmLVzikgQzGppaZtjFU4I8yagTFdFRbdhKmwaFv_Ue90JrJGRkluKD0ycFKbdPdzWrFJPFIq3cQ",
                  },
                }
              )
              .then((res) => {
                console.log(res);
              })
              .catch((err) => {
                console.log(err.response.status); // 401
                console.log(err.response.data.error);
              });
            console.log("submit button이 끝났습니다.");
          }}
          name="complete"
        >
          완료
        </button>
        <button
          className="settingButtonSignOut"
          onClick={handleShow}
          name="signOut"
        >
          회원탈퇴
        </button>
        <button className="settingButtonSignOut" onClick={onCompleteClick}>
          toast 테스트버튼
        </button>
        {console.log("재렌더링")}
        <button
          className="settingButtonSignOut"
          onClick={(e) => {
            onSetToast(e);
          }}
        >
          2toast 테스트버튼
        </button>
        <button
          type="submit"
          form="settingForm"
          className="settingButtonComplete"
          onClick={() => {
            console.log("테스트 button이 클릭되었습니다.");
            axios
              .get(
                "https://lets-team-project.herokuapp.com/api/users/setting",
                {
                  headers: {
                    Authorization:
                      "Bearer eyJyb2xlIjoiUk9MRV9VU0VSIiwiYWxnIjoiSFM1MTIifQ.eyJzdWIiOiIyIiwiaWF0IjoxNjQ0ODIyMDMyLCJleHAiOjE2NDQ4MjM4MzJ9.AzFz8DCiGNARmLVzikgQzGppaZtjFU4I8yagTFdFRbdhKmwaFv_Ue90JrJGRkluKD0ycFKbdPdzWrFJPFIq3cQ",
                  },
                }
              )
              .then((res) => {
                console.log(res);
              })
              .catch((err) => {
                console.log(err.response.status); // 401
                console.log(err.response.data.error);
              });
            console.log("테스트 button이 끝났습니다.");
          }}
          name="complete"
        >
          테스트
        </button>
      </form>
    </div>
  );
}

function Popup(props) {
  return (
    <div className="popupDiv">
      <Modal
        {...props}
        size="lg"
        centered
        show={props.show}
        onHide={props.handleClose}
      >
        <div className="cancelButtonWrapper">
          <Modal.Body>
            <div className="bodyTitle">Hola에서 계정을 삭제하시겠어요?</div>
            <section className="CancelButtonButtons">
              <div>
                <button
                  className="CancelButtonCancelButton"
                  onClick={props.handleClose}
                >
                  아니요
                </button>
                <button className="CancelButtonRegisterButton">
                  네, 삭제할래요
                </button>
              </div>
            </section>
          </Modal.Body>
        </div>
      </Modal>
    </div>
  );
}

export default Settings;
