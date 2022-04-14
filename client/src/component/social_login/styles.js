import styled from "styled-components";

export const StyledGreyBackground = styled.div`
  z-index: 1000;
  position: fixed;
  display: ${(props) => {
    return props.show ? "flex" : "none";
  }};
  justify-content: center;
  align-items: center;

  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.5);
`;

export const LoginWrapper = styled.div`
  border-radius: 20px;
  background: white;
  z-index: 1001;
  width: 800px;
  display: flex;
  height: 450px;
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 9%);
  flex-direction: column;
  transform: translateY(-50%);
`;

export const LoginHeader = styled.div`
  background-color: #f8f9fa;
  display: flex;
  align-items: center;
  padding: 0 1rem;
  justify-content: space-between;
  border-radius: 8px 8px 0 0;
  height: 3rem;
`;
export const Close = styled.button``;

export const LoginBody = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  height: 100%;
`;

export const WelcomeText = styled.h2`
  display: flex;
  justify-content: center;
`;
export const BtnWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: row;
`;
export const LoginColumn = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
`;
export const LoginBtn = styled.div`
  margin: 16px;
`;
export const BtnImg = styled.img`
  box-shadow: 1px 1px 6px grey;
  border-radius: 20px;
`;
export const LoginText = styled.div``;
