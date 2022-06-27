import { useState, useEffect } from "react";
const MenuDetail = ({ menuData, clickedMenu }) => {
  const [carouselIndex, setCarouselIndex] = useState(1);
  const [totalPrice, setTotalPrice] = useState(0);
  const [doubleChecked, setDoubleChecked] = useState(false);

  useEffect(() => {
    setTotalPrice(menuData[clickedMenu].price);
  }, []);

  setTimeout(() => {
    setCarouselIndex((carouselIndex + 1) % 2);
  }, 3000);
  return (
    <div className="menu-detail-container">
      <div className="menu-info">
        <div className="menu-carousel">
          {carouselIndex === 1 ? (
            <div>
              <img src={"img/" + menuData[clickedMenu].carouselImgName[0]} />
            </div>
          ) : (
            <div>
              <img src={"img/" + menuData[clickedMenu].carouselImgName[1]} />
            </div>
          )}
        </div>
        <div className="menu-info-detail">
          <h2>{menuData[clickedMenu].menuName}</h2>
          <p>{menuData[clickedMenu].desc}</p>
          {menuData[clickedMenu].option ? (
            <div className="option">
              <input
                type="checkbox"
                name="option_chk"
                onClick={() => {
                  setDoubleChecked(!doubleChecked);
                }}
              ></input>
              <span>한솥밥 곱빼기</span>
              <span>+{menuData[clickedMenu].option.doublePrice}원</span>
            </div>
          ) : null}
          <div className="total">
            <span>기본가격:{totalPrice}</span>&nbsp;&nbsp;&nbsp;
            <span>최종가격</span>
            <span>
              {doubleChecked
                ? Number(totalPrice) +
                  Number(menuData[clickedMenu].option.doublePrice)
                : totalPrice}
            </span>
          </div>
        </div>
      </div>
      <div className="menu-info-more">열량, 알레르기</div>
    </div>
  );
};
export default MenuDetail;
