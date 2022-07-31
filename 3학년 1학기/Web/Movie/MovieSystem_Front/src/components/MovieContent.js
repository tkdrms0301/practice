import { useEffect } from 'react';
import { Link } from 'react-router-dom';

function MovieContent({ movieContent, index }) {
    return (
        <li>
            <div class="movie_box-image">
                <strong class="movie_rank">No.{index}</strong>
                <Link to={'/movieDetail?movie=' + movieContent._id}>
                    <span class="thumb-image">
                        <img src={movieContent.url}></img>
                        <span class="ico-grade grade-12">
                            <img
                                src={`https://img.cgv.co.kr/R2014/images/common/flag/age/grade-${movieContent.screenGrade}.png`}
                                alt=""
                            />
                        </span>
                    </span>
                </Link>
                <span class="screentype"></span>
            </div>
            <div class="movie_box-contents">
                <div className="movie_movieName_movie">
                    <Link to={'/movieDetail?movie=' + movieContent._id}>
                        <strong class="title">{movieContent.title}</strong>
                    </Link>
                </div>

                <div class="movie_score">
                    <div class="movie-tekectRate">
                        <span>예매율 {movieContent.ticketingRate}%</span>
                    </div>
                    <div className="movie_movie-egg">
                        <span>
                            <img
                                src="https://img.cgv.co.kr/R2014/images/common/egg/eggGoldeneggPreegg.png"
                                alt="Golden Egg Preegg"
                            ></img>
                            {movieContent.averageGrade}점
                        </span>
                    </div>
                </div>

                <div class="movie_movieRelease">
                    <span>{movieContent.openingDate} 개봉</span>
                </div>

                <span class="like">
                    <a class="movie_link-reservation" href="#">
                        예매하기
                    </a>
                </span>
            </div>
        </li>
    );
}

export default MovieContent;
