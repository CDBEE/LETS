/*eslint-disable*/

import React, { useState, useEffect } from "react";
import axios from "axios";
import * as S from "./styles";

function SocialLogin(props) {
  return (
    <S.StyledGreyBackground show={props.show}>
      <S.LoginWrapper>
        <S.LoginHeader>
          <S.Close onClick={props.handleClose}>로고</S.Close>
          <S.Close onClick={props.handleClose}>X</S.Close>
        </S.LoginHeader>
        <S.LoginBody>
          <S.WelcomeText>렛츠에 오신 것을 환영합니다!</S.WelcomeText>
          <S.BtnWrapper>
            <S.LoginColumn>
              <S.LoginBtn>
                <S.BtnImg
                  src={`/images/login/googleBtn.png`}
                  // 현재로서는 로그인 작동 안 됨
                  // 오류
                  onClick={() => {
                    console.log("clicked!");
                    axios
                      .get(
                        "https://lets-team-project.herokuapp.com/oauth2/authorization/google"
                      )
                      .then((res) => {
                        console.log(res);
                      })
                      .catch((err) => {
                        console.log(err);
                      });
                  }}
                />
                <S.LoginText>Google 로그인</S.LoginText>
              </S.LoginBtn>
              <S.LoginBtn>
                <S.BtnImg src={`/images/login/githubBtn.png`} />
                <S.LoginText>Github 로그인</S.LoginText>
              </S.LoginBtn>
              <S.LoginBtn>
                <S.BtnImg src={`/images/login/kakaoBtn.png`} />
                <S.LoginText>Kakao 로그인</S.LoginText>
              </S.LoginBtn>
            </S.LoginColumn>
          </S.BtnWrapper>
        </S.LoginBody>
      </S.LoginWrapper>
    </S.StyledGreyBackground>
  );
}

export default SocialLogin;
