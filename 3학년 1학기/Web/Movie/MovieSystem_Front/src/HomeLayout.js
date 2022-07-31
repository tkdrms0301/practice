import React from 'react';
import { Link } from 'react-router-dom';
import { Fragment } from 'react';
import './App.css';

function closeNav() {
    return (document.getElementById('mySidenav').style.width = '0px');
}

function openNav() {
    return (document.getElementById('mySidenav').style.width = '200px');
}

function HomeLayout(props) {
    const { children } = props;
    const cookie = props.cookie;
    const setCookie = props.setCookie;
    const hasCookie = props.hasCookie;
    const setHasCookie = props.setHasCookie;
    const removeCookie = props.removeCookie;

    const logout = () => {
        setHasCookie(false);
        removeCookie('jwt');
    };

    return (
        <div>
            <div className="header">
                <div className="header_content">
                    <div className="contents">
                        <span className="More" onClick={() => openNav()}>
                            &#9776;
                        </span>
                        <div className="cgvImeage">
                            <h1 onclick="">
                                <Link to="/">
                                    <img
                                        src="https://img.cgv.co.kr/R2014/images/common/logo/logoRed.png"
                                        alt="CGV"
                                    />
                                </Link>
                                <span>CULTUREPLEX</span>
                            </h1>
                        </div>
                        <ul className="memberInfo_wrap">
                            <div className="ad-partner">
                                <a href="#">
                                    <img
                                        src="https://img.cgv.co.kr/WingBanner/2022/0303/16462658373950.png"
                                        alt="현대M포인트"
                                    />
                                </a>
                            </div>

                            {hasCookie ? (
                                <div id="mySidenav" className="sidenav">
                                    <a
                                        href="javascript:void(0)"
                                        className="closebtn"
                                        onClick={() => closeNav()}
                                    >
                                        &times;
                                    </a>
                                    <li onClick={() => logout()}>
                                        <a href="#none">
                                            <img
                                                src="https://img.cgv.co.kr/R2014/images/common/ico/loginPassword.png"
                                                alt="로그아웃"
                                            />
                                            <span>로그아웃</span>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="/mypage">
                                            <img
                                                src="https://img.cgv.co.kr/R2014/images/common/ico/loginMember.png"
                                                alt="MY CGV"
                                            />
                                            <span>MY CGV</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <img
                                                src="https://img.cgv.co.kr/R2014/images/common/ico/loginCustomer.png"
                                                alt="고객센터"
                                            />
                                            <span>고객센터</span>
                                        </a>
                                    </li>
                                </div>
                            ) : (
                                <div id="mySidenav" className="sidenav">
                                    <a
                                        href="javascript:void(0)"
                                        className="closebtn"
                                        onClick={() => closeNav()}
                                    >
                                        &times;
                                    </a>
                                    <li>
                                        <Link to="/login">
                                            <img
                                                src="https://img.cgv.co.kr/R2014/images/common/ico/loginPassword.png"
                                                alt="로그인"
                                            />
                                            <span>로그인</span>
                                        </Link>
                                    </li>
                                    <li>
                                        <a href="/sign">
                                            <img
                                                src="https://img.cgv.co.kr/R2014/images/common/ico/loginJoin.png"
                                                alt="회원가입"
                                            />
                                            <span>회원가입</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/mypage">
                                            <img
                                                src="https://img.cgv.co.kr/R2014/images/common/ico/loginMember.png"
                                                alt="MY CGV"
                                            />
                                            <span>MY CGV</span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <img
                                                src="https://img.cgv.co.kr/R2014/images/common/ico/loginCustomer.png"
                                                alt="고객센터"
                                            />
                                            <span>고객센터</span>
                                        </a>
                                    </li>
                                </div>
                            )}
                        </ul>
                    </div>
                </div>
                <div className="nav">
                    <div className="contents">
                        <ul className="nav_menu">
                            <li>
                                <Link to="/movies">
                                    <h3>영화</h3>
                                </Link>
                            </li>
                            <li>
                                <Link to="/theaters">
                                    <h3>상영시간표</h3>
                                </Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div>{children}</div>
            {console.log('cookie : ' + cookie)}
            {console.log(hasCookie)}
        </div>
    );
}

export default HomeLayout;
