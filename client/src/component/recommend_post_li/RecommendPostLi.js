import React from "react";
import "./RecommendPostLi.css"


const RecommendPostLi = (props) => {
  return (
    <>
        <li className="recommendPostLiWrapper">
            <div className="recommendPostLiIndex">{props.i}.</div>
            <div className="recommendPostLiTitle">
                {props.title}
            </div>
        </li>
    </>
  );
};

export default RecommendPostLi;