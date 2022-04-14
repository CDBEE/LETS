/*eslint-disable*/

import languageList from "common/languageList";
import { useNavigate } from "react-router-dom";
import React, { useState } from "react";
import "./Register.css";
import ReactQuill from "react-quill";
import "../../../node_modules/react-quill/dist/quill.snow.css";
import { Multiselect } from "multiselect-react-dropdown";
import Navbar from "component/nav_bar/navbar";

const Register = (props) => {
  const Language = ["Java", "Python", "Swift", "Go"];
  let [input, setInput] = useState("");
  let [title, setTitle] = useState("");
  const [body, setBody] = useState("");
  const handleBody = (e) => {
    setBody(e);
  };

  const [options, setOptions] = useState(languageList);
  const name = "면접후기";
  return (
    <>
      <Navbar />;
      <div className="Register">
        <section className="postRegister">
          <section>
            <InputTemplate
              title={title}
              setTitle={setTitle}
              setInput={setInput}
            ></InputTemplate>
            <div className="languageWrapper">
              <h3>사용 언어: </h3>
              <Multiselect
                className="languageControl"
                placeholder="프로젝트/스터디 진행 언어 선택"
                options={options}
                displayValue="value"
              />
            </div>
            <ReactQuill
              placeholder="프로젝트/스터디 진행 방식 및 신청 방법(오픈카톡, 댓글 등)에 대해 구체적으로 작성 부탁드려요!"
              modules={Register.modules}
              formats={Register.formats}
              onChange={handleBody}
              autoClose={true}
              value={body}
            ></ReactQuill>
          </section>
          <section>
            <ButtonComponent history={history}></ButtonComponent>
          </section>
        </section>
      </div>
    </>
  );
};

Register.modules = {
  toolbar: [
    //[{ 'font': [] }],
    [{ header: [1, 2, false] }],
    ["bold", "italic", "underline", "strike"],
    [{ list: "ordered" }, { list: "bullet" }],
    ["link", "image"],
  ],
};

Register.formats = [
  //'font',
  "header",
  "bold",
  "italic",
  "underline",
  "strike",
  "list",
  "bullet",
  "link",
  "image",
];

function ButtonComponent() {
  let navigate = useNavigate();

  return (
    <section className="writeButton">
      <button
        className="cancelButton"
        onClick={() => {
          navigate("/#");
        }}
      >
        취소
      </button>
      <button className="registerButton">글 등록</button>
    </section>
  );
}

function InputTemplate(props) {
  return (
    <div>
      <input
        className="titleInput"
        onChange={(e) => {
          props.setInput(() => {
            props.setTitle(e.target.value);
          });
        }}
        placeholder="제목을 입력하세요"
        value={props.title}
      />
    </div>
  );
}

export default Register;
