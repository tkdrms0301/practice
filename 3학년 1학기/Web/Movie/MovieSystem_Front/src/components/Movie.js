import { Fragment, useEffect } from 'react';
import '../css/Movie.css';
import axios from 'axios';
import { useState } from 'react';
import MovieContent from './MovieContent';

function Movie() {
    const [movieContent, setMovieContent] = useState([]);
    const [sort, setSort] = useState('');
    const [check, setCheck] = useState(false);

    const onChangeSort = (event) => {
        setSort(event.target.value);
        console.log(event.target.value);
    };

    function startMovie() {
        axios
            .get('http://localhost:8080/movieTicketingRateGet', {
                sort: 'reservationRate',
            })
            .then((res) => {
                setMovieContent(res.data);
                console.log(res.data);
                setCheck(true);
            })
            .catch((err) => console.log(err));
    }

    useEffect(() => {
        const test = async () => {
            startMovie();
        };
        test();
    }, []);

    const onSubmit = (event) => {
        if (sort === 'reservationRate') {
            axios
                .get('http://localhost:8080/movieTicketingRateGet', {
                    sort: sort,
                })
                .then((res) => {
                    setMovieContent(res.data);
                    setCheck(true);
                })
                .catch((err) => console.log(err));
        } else if (sort === 'orderRate') {
            axios
                .get('http://localhost:8080/movieAverageGradeGet', {
                    sort: sort,
                })
                .then((res) => {
                    setMovieContent(res.data);
                    setCheck(true);
                })
                .catch((err) => console.log(err));
        } else {
        }
        event.preventDefault();
    };

    return (
        <div class="body">
            <div class="movieChart">
                <h1>무비차트</h1>
            </div>
            <div class="movieList">
                <form class="movie_sort" method="get" onSubmit={onSubmit}>
                    <select
                        name="sortMovie"
                        id="movie_sortMovie"
                        value={sort}
                        onChange={onChangeSort}
                    >
                        <option value="reservationRate">예매율 순</option>
                        <option value="orderRate">평점 순</option>
                    </select>
                    <input id="movie_sortSubmit" type="submit" value="GO"></input>
                </form>
                {check ? (
                    <div class="movie_sect-movie-chart">
                        <ol>
                            <MovieContent movieContent={movieContent[0]} index={1}></MovieContent>
                            <MovieContent movieContent={movieContent[1]} index={2}></MovieContent>
                            <MovieContent movieContent={movieContent[2]} index={3}></MovieContent>
                        </ol>
                        <ol>
                            <MovieContent movieContent={movieContent[3]} index={4}></MovieContent>
                            <MovieContent movieContent={movieContent[4]} index={5}></MovieContent>
                            <MovieContent movieContent={movieContent[5]} index={6}></MovieContent>
                        </ol>
                        <ol>
                            <MovieContent movieContent={movieContent[6]} index={7}></MovieContent>
                            <MovieContent movieContent={movieContent[7]} index={8}></MovieContent>
                            <li></li>
                        </ol>
                    </div>
                ) : null}
            </div>
        </div>
    );
}

export default Movie;
