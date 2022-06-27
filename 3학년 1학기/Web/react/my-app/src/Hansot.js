import Header from "./components/Header";
import Sidebar from "./components/Sidebar";
import MenuRow from "./components/MenuRow";
import { Routes, Route } from "react-router-dom";
import MenuDetail from "./components/MenuDetail";
import { useState } from "react";

const menu1 = {
  menuId: 1,
  menuName: "1993 왕돈까스 도시락",
  price: 9300,
  imgName: "menu1.jpg",
  carouselImgName: ["menu1_1.jpg", "menu1_2.jpg"],
  desc: "온 가족이 사랑하는 두툼하고 바삭한 왕 돈까스와 과일향 가득 감칠 맛 나는 한솥 특제 데미그라스 소스로 맛도 구성도 왕인 수량한정 신메뉴",
  option: {
    doublePrice: "300",
  },
};
const menu2 = {
  menuId: 2,
  menuName: "나시고랭 콤보",
  price: 6800,
  imgName: "menu2.jpg",
  carouselImgName: ["menu2_1.jpg", "menu2_2.jpg"],
  desc: "CNN에서 선정한 세계에서 가장 맛있는 음식 2위! 큼직한 닭고기와 스파이시한 풍미를 듬뿍 넣어 볶아 더 맛있게 돌아온 한솥 나시고랭과 특제 스리라차 마요소스를 곁들인 바삭바삭 알새우칩 콤보set",
};

const menu3 = {
  menuId: 3,
  menuName: "나시고랭",
  price: 6000,
  imgName: "menu3.jpg",
  carouselImgName: ["menu3_1.jpg", "menu3_2.jpg"],
  desc: "CNN에서 선정한 세계에서 가장 맛있는 음식 2위! 큼직한 닭고기와 스파이시한 풍미를 듬뿍 넣어 볶아 더 맛있게 돌아온 한솥 나시고랭",
};

const menuArr = [menu1, menu2, menu3];

function Hansot() {
  const [clickedMenu, setClickedMenu] = useState();
  return (
    <div>
      <Header></Header>
      <div className="content-container">
        <Sidebar></Sidebar>
        <Routes>
          <Route
            path="/"
            element={
              <div className="menu-container">
                <MenuRow
                  menuInfo={menuArr}
                  setClickedMenu={setClickedMenu}
                ></MenuRow>
              </div>
            }
          ></Route>
          <Route
            path="/detail"
            element={
              <MenuDetail
                menuData={menuArr}
                clickedMenu={clickedMenu}
              ></MenuDetail>
            }
          ></Route>
        </Routes>
      </div>
    </div>
  );
}
export default Hansot;
