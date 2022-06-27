import style from "./FoodCard.module.css";
import { useState } from "react";
import { AiOutlineLike } from "react-icons/ai";
import { AiOutlineDislike } from "react-icons/ai";
import axios from "axios";

export default function FoodCard() {
  const [clicked, setClicked] = useState({
    like: false,
    dislike: false,
  });
  const { like, dislike } = clicked;

  const likeClick = () => {
    if (!like) {
      setClicked({
        ...clicked,
        like: true,
      });
      console.log(clicked);
      axios.post("url", {}).then().catch();
    } else {
      setClicked({
        ...clicked,
        like: false,
      });
      console.log(clicked);
      axios.post("url", {}).then().catch();
    }
  };
  const dislikeClick = () => {
    if (!dislike) {
      setClicked({
        ...clicked,
        dislike: true,
      });
      console.log(clicked);
      axios.post("url", {}).then().catch();
    } else {
      setClicked({
        ...clicked,
        dislike: false,
      });
      console.log(clicked);
      axios.post("url", {}).then().catch();
    }
  };
  return (
    <>
      <div className={`${style.container}`}>
        <div className={`${style.image}`}>
          <img src="" alt="음식이름" />
        </div>
        <div className={`${style.foodName}`}>
          <p>매운돼지갈비찜</p>
        </div>
        <div className={`${style.feedbackBox}`}>
          <div className={style.like}>
            <AiOutlineLike
              onClick={likeClick}
              size={40}
              color={like ? "#ff7473" : "#9f9f9f"}
            />
            <p>100</p>
          </div>
          <div className={style.dislike}>
            <AiOutlineDislike
              onClick={dislikeClick}
              size={40}
              color={dislike ? "#ff7473" : "#9f9f9f"}
            />
            <p>200</p>
          </div>
        </div>
      </div>
    </>
  );
}
