import { Fragment } from 'react';
import '../css/Ticketing.css';
import axios from 'axios';
import React, { useEffect, useState } from 'react';

function Ticketing() {
    const [movieInfoAll, setMovieInfoAll] = useState(null);
    const [test, setTest] = useState(null);
    const [time, setTime] = useState(null);

    useEffect(() => {
        axios
            .get('http://localhost:8080/theaterTime')
            .then((res) => {
                setMovieInfoAll(res.data);
            })
            .catch((err) => console.log(err));
    }, []);

    const onClickMovie = (event) => {
        let acc = document.getElementsByClassName('ticket-MovieListLI');
        let time = document.getElementsByClassName('ticket-movieTime');
        let select = document.getElementsByClassName('ticket_tnb-seating-input');

        let i;
        for (i = 0; i < acc.length; i++) {
            acc[i].addEventListener('click', function () {
                setTime(null);

                this.style.backgroundColor = 'rgb(107, 104, 104)'; // 요소 선택 시 색 바꾸기
                this.style.color = 'white';

                for (var j = 0; j < acc.length; j++) {
                    if (this !== acc[j]) {
                        setTest(j);
                        acc[j].style.backgroundColor = '#f2f0e5'; // 다른요소 선택 시 색 되돌리기
                        acc[j].style.color = 'black';

                        select[0].style.backgroundColor = 'rgb(105, 57, 57)';
                        select[0].style.color = 'rgb(136, 130, 130)';
                        select[0].style.cursor = 'auto';

                        for (var k = 0; k < time.length; k++) {
                            time[k].style.backgroundColor = '#f2f0e5';
                            time[k].style.color = 'black';
                        }
                    }
                }
            });
        }
    };
    const onClickTime = (event) => {
        if (test === null) {
            return;
        }

        let acc = document.getElementsByClassName('ticket-movieTime');
        let select = document.getElementsByClassName('ticket_tnb-seating-input');
        let i;

        for (i = 0; i < acc.length; i++) {
            acc[i].addEventListener('click', function () {
                this.style.backgroundColor = 'rgb(107, 104, 104)'; // 요소 선택 시 색 바꾸기
                this.style.color = 'white';

                select[0].style.backgroundColor = 'rgb(209, 56, 56)';
                select[0].style.color = 'white';
                select[0].style.cursor = 'pointer';

                for (var j = 0; j < acc.length; j++) {
                    if (this !== acc[j]) {
                        console.log(j);
                        setTime(j);
                        acc[j].style.backgroundColor = '#f2f0e5'; // 다른요소 선택 시 색 되돌리기
                        acc[j].style.color = 'black';
                    }
                }
            });
        }
    };

    return (
        <div class="ticket-container">
            {movieInfoAll ? (
                <Fragment>
                    <div class="ticket-layout">
                        <div class="ticket-movie">
                            <div class="ticket-title">
                                <span>영화</span>
                            </div>
                            <ul className="ticket-movieList" onClick={onClickMovie}>
                                <li className="ticket-MovieListLI">
                                    <div class="ticket-movieInfo">
                                        <div class="ticket-grade">
                                            <img src="https://img.cgv.co.kr/R2014/images/common/flag/age/grade-15.png"></img>
                                        </div>
                                        <div class="ticket-movieName">
                                            <span>{movieInfoAll[0]?.movie?.title}</span>
                                        </div>
                                    </div>
                                </li>
                                <li className="ticket-MovieListLI">
                                    <div class="ticket-movieInfo">
                                        <div class="ticket-grade">
                                            <img src="https://img.cgv.co.kr/R2014/images/common/flag/age/grade-12.png"></img>
                                        </div>
                                        <div class="ticket-movieName">
                                            <span>{movieInfoAll[2]?.movie?.title}</span>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>

                        <div class="ticket-time">
                            <div class="ticket-title">
                                <span>시간</span>
                            </div>

                            <div class="ticket-timeTable">
                                <div class="ticket-schdule">
                                    <div class="ticket-cinemaInfo">
                                        <span class="dimension">2D</span>
                                        {test === 0 ? (
                                            <span>{movieInfoAll[2]?.theater?.theaterName}</span>
                                        ) : (
                                            <span>{movieInfoAll[0]?.theater?.theaterName}</span>
                                        )}
                                        <span>(총100석)</span>
                                    </div>
                                    <div class="ticket-timeInfo-box" onClick={onClickTime}>
                                        <div class="ticket-timeInfo">
                                            <div class="ticket-movieTime">
                                                {test === 0 ? (
                                                    <span>{movieInfoAll[2]?.time}</span>
                                                ) : (
                                                    <span>{movieInfoAll[0]?.time}</span>
                                                )}
                                            </div>

                                            <div class="ticket-seat">
                                                {test === 0 ? (
                                                    <span>
                                                        {movieInfoAll[2]?.theater?.totalSeat}석
                                                    </span>
                                                ) : (
                                                    <span>
                                                        {movieInfoAll[0]?.theater?.totalSeat}석
                                                    </span>
                                                )}
                                            </div>
                                        </div>

                                        <div class="ticket-timeInfo">
                                            <div class="ticket-movieTime">
                                                {test === 0 ? (
                                                    <span>{movieInfoAll[3]?.time}</span>
                                                ) : (
                                                    <span>{movieInfoAll[1]?.time}</span>
                                                )}
                                            </div>

                                            <div class="ticket-seat">
                                                {test === 0 ? (
                                                    <span>
                                                        {movieInfoAll[3]?.theater?.totalSeat}석
                                                    </span>
                                                ) : (
                                                    <span>
                                                        {movieInfoAll[1]?.theater?.totalSeat}석
                                                    </span>
                                                )}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="ticket_tnb">
                        <div class="ticket_tnb-box">
                            <div class="ticket_tnb-img">
                                {test === 0 ? (
                                    <img src={movieInfoAll[2]?.movie?.url} />
                                ) : (
                                    <img src={movieInfoAll[0]?.movie?.url} />
                                )}
                            </div>
                            <div class="ticket_tnb-movieName">
                                {test === 0 ? (
                                    <span>{movieInfoAll[2]?.movie?.title}</span>
                                ) : (
                                    <span>{movieInfoAll[0]?.movie?.title}</span>
                                )}
                                <span>2D</span>
                                {test === 0 ? (
                                    <span>{movieInfoAll[2]?.movie?.screenGrade} 관람가</span>
                                ) : (
                                    <span>{movieInfoAll[0]?.movie?.screenGrade} 관람가</span>
                                )}
                            </div>
                            <div class="ticket_tnb-movieInfo">
                                <span>극장</span>
                                <span class="ticket_tnb-movieInfo_detaile">CGV 구미</span>

                                <span>시간</span>
                                {test === 0 && time === 0 ? (
                                    <span class="ticket_tnb-movieInfo_detaile">
                                        {movieInfoAll[3]?.time}
                                    </span>
                                ) : test === 0 && time === 1 ? (
                                    <span class="ticket_tnb-movieInfo_detaile">
                                        {movieInfoAll[2]?.time}
                                    </span>
                                ) : test === 1 && time === 0 ? (
                                    <span class="ticket_tnb-movieInfo_detaile">
                                        {movieInfoAll[1]?.time}
                                    </span>
                                ) : test === 1 && time === 1 ? (
                                    <span class="ticket_tnb-movieInfo_detaile">
                                        {movieInfoAll[0]?.time}
                                    </span>
                                ) : (
                                    <span class="ticket_tnb-movieInfo_detaile"></span>
                                )}

                                <span>상영관</span>
                                {test === 0 ? (
                                    <span class="ticket_tnb-movieInfo_detaile">
                                        {movieInfoAll[2]?.theater?.theaterName}
                                    </span>
                                ) : (
                                    <span class="ticket_tnb-movieInfo_detaile">
                                        {movieInfoAll[0]?.theater?.theaterName}
                                    </span>
                                )}
                            </div>

                            <div className="ticket_tnb-seating">
                                <input
                                    className="ticket_tnb-seating-input"
                                    type="submit"
                                    value="좌 석 선 택"
                                ></input>
                            </div>
                        </div>
                    </div>
                </Fragment>
            ) : null}
        </div>
    );
}

export default Ticketing;
