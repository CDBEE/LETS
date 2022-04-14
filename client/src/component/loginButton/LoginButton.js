/*eslint-disable*/
import "./LoginButton.css";
import React, { useState } from "react";
import {
  Button,
  Modal,
  Navbar,
  Container,
  Nav,
  CloseButton,
} from "react-bootstrap";
// import Popup from "../social-login/popup";

// 승훈님과 겹치는 코드가 너무 많아서
// utils도 손보면 좋을 듯....!

function LoginButton() {
  const [isPressed, setIsPressed] = useState(false);
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  return (
    <div className="Main">
      {/* <Popup
        show={show}
        setShow={setShow}
        handleClose={handleClose}
        handleShow={handleShow}
      ></Popup> */}
      <NavTemplate
        isPressed={isPressed}
        setIsPressed={setIsPressed}
        show={show}
        setShow={setShow}
        handleClose={handleClose}
        handleShow={handleShow}
      ></NavTemplate>
    </div>
  );
}

function NavTemplate(props) {
  return (
    <Navbar expand="lg">
      <Container>
        <Navbar.Brand href="/">
          {props.temp}
          <img src="/llogo.png"></img>
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse className="justify-content-end" id="basic-navbar-nav">
          <Nav.Link href="#register">
            <p>새 글 쓰기</p>
          </Nav.Link>
          <Nav.Link href="#" onClick={props.handleShow}>
            <p>로그인</p>
          </Nav.Link>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default LoginButton;
