import { Link } from "react-router-dom";
const MenuCard = (props) => {
  return (
    <Link to="/detail">
      <div
        className="menu-card"
        data-order={props.cardOrder}
        onClick={(e) => {
          const order = e.currentTarget.dataset.order;
          console.log("clicked");
          console.log(order);
          props.setClickedMenu(order);
        }}
      >
        <div className="menu-img">
          <img
            src={"./img/" + props.menuInfo.imgName}
            alt={props.menuInfo.menuName}
          />
        </div>
        <div class="menu-text">
          <h4>{props.menuInfo.menuName}</h4>
          <h4>{props.menuInfo.price}원</h4>
        </div>
      </div>
    </Link>
  );
};

export default MenuCard;
