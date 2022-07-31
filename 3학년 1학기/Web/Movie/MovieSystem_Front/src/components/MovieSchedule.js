import axios from 'axios';
import React, { useEffect, useState } from 'react';
import '../css/MovieSchedule.css';

import EachMovieSchedule from './EachMovieSchedule';
export default function MovieSchedule(props) {
    const [movieSchedulesFirst, setMovieSchedulesFirst] = useState(null);
    const [movieSchedulesSecond, setMovieSchedulesSecond] = useState(null);

    useEffect(() => {
        axios
            .get('http://localhost:8080/movie1')
            .then((res) => {
                setMovieSchedulesFirst(res.data);
                console.log(res.data);
            })
            .catch((err) => console.log(err));
        axios
            .get('http://localhost:8080/movie2')
            .then((res) => {
                setMovieSchedulesSecond(res.data);
                console.log(res.data);
            })
            .catch((err) => console.log(err));
    }, []);

    return (
        <div class="sect-showtimes">
            {movieSchedulesFirst && movieSchedulesSecond ? (
                <ul>
                    <EachMovieSchedule movieSchedule={movieSchedulesFirst} />
                    <EachMovieSchedule movieSchedule={movieSchedulesSecond} />
                </ul>
            ) : null}

            <p>ㆍ입장 지연에 따른 관람 불편을 최소화하기 위해 영화는 10분 후 상영이 시작됩니다.</p>
        </div>
    );
}
