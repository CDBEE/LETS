import React from "react";
import styles from "./loadingSpinner.module.css";
const LoadingSpinner = (props) => {
  return (
    <div className={styles.spinner}>
      <img
        className={styles.spinnerImg}
        src="/images/info/heart_filled.png"
        alt="default loading spinner"
      />
    </div>
  );
};

export default LoadingSpinner;