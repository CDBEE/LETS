import React from "react";
import StudyItem from "../study_item/studyItem";
import styles from "./studyList.module.css";

const StudyList = ({ studyList }) => (
  <ul className={styles.studyList}>
    {studyList.map((study) => {
      return <StudyItem study={study} key={study.id}></StudyItem>;
    })}
  </ul>
);

export default StudyList;