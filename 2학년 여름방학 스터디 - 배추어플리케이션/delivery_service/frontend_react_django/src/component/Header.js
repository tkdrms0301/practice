import style from "./Header.module.css";
import logo from "../img/logo_white.png";

export default function Header() {
  return (
    <header id={style.header}>
      <img src={logo} alt="배달추천" className={style.logo} />
    </header>
  );
}
