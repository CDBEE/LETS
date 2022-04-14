import React from "react";
import "./RecommendPost.css"
import RecommendPostLi from "../recommend_post_li/RecommendPostLi"
import recommendPostList from "./testData"

const RecommendPost = () => {
  console.log(recommendPostList)
  
  return (
    <div className="TotalWrapper">
      <div className="recommendWrapper">
        <div className="userInfoWrapper">
          <div className="recommendPostBar"></div>
          <div className="recommendPostUserInfo">
            <span className="recommendPostUserName">
              {
                // login 되어 있으면 ? 닉네임 : "방문자"

              }
            </span>님이
            <br/>
            좋아하실 글을 모아봤어요!
          </div>
        </div>
        <div className="recommendPostListWrapper">
              {
                recommendPostList.map((v,i)=>{
                  return(<RecommendPostLi i={i+1} title={v.title}></RecommendPostLi>)
                })
              }
              
        </div>
      </div>
    
    </div>
  );
};

export default RecommendPost;