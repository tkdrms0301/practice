import { useEffect, useState } from 'react';
import axios from 'axios';
import '../css/MovieDetail.css';
import '../css/Comment.css';
import { useLocation } from 'react-router-dom';
import Comment from './Comment';

function commentWrite(n) {
    var target = document.getElementsByClassName('detaileView-write-comment');
    target[n].style.display = 'block';

    target = document.getElementsByClassName('detaileView-Modify-comment');
    target[n].style.display = 'none';
}

function commentWriteNone(n) {
    var target = document.getElementsByClassName('detaileView-write-comment');
    target[n].style.display = 'none';
}

function commentModifyWrite(n) {
    var target = document.getElementsByClassName('detaileView-Modify-comment');
    target[n].style.display = 'block';

    var target = document.getElementsByClassName('detaileView-write-comment');
    target[n].style.display = 'none';
}

function commentModifyWriteNone(n) {
    var target = document.getElementsByClassName('detaileView-Modify-comment');
    for (let i = 0; target.length; i++) {
        target[i].style.display = 'none';
    }
}

function MovieDetail({ cookies }) {
    //const movie_id
    const search = useLocation().search;
    const movie = new URLSearchParams(search).get('movie');
    const [check, setCheck] = useState(false);
    const [checkComment, setCheckComment] = useState(false);

    const [movieData, setMovieData] = useState('');
    const [commentData, setCommentData] = useState([]);

    const [grade, setGrade] = useState('');
    const [comment, setComment] = useState('');

    function startMovie() {
        axios
            .get('http://localhost:8080/movieDetail/' + movie)
            .then((res) => {
                setMovieData(res.data);
                setCheck(true);
            })
            .catch((err) => console.log(err));
        axios.get('http://localhost:8080/' + movie + '/comment').then((res) => {
            setCommentData(res.data);
            console.log(res.data);
            setCheckComment(true);
        });
    }

    useEffect(() => {
        startMovie();
    }, []);

    const onChangeGrade = (event) => {
        setGrade(event.target.value);
        console.log(event.target.value);
    };

    const onChangeComment = (event) => {
        setComment(event.target.value);
        console.log(event.target.value);
    };

    const onSubmit = (event) => {
        if (grade == '') {
            window.alert(['평점 선택은 필수입니다.']);
        } else if (comment == '') {
            window.alert(['내용 작성은 필수입니다.']);
        } else {
            axios
                .post('http://localhost:8080/' + movie + '/comment', {
                    grade: grade,
                    contents: comment,
                    memberId: cookies['jwt'],
                })
                .then((res) => {
                    setGrade('');
                    setComment('');
                    axios.get('http://localhost:8080/' + movie + '/comment').then((res) => {
                        setCommentData(res.data);
                        setCheckComment(true);
                    });
                })
                .catch((err) => {
                    console.log(err);
                });
            window.alert(['등록 완료']);
            window.location.reload();
        }

        event.preventDefault();
    };

    return (
        <div class="detaileView-sect-base-movie">
            {check ? (
                <div class="detaileView-movie-box">
                    <div class="detaileView-box-image">
                        <img src={movieData.movies.url} alt="" onerror="errorImage(this)" />
                    </div>
                    <div class="detaileView-box-contents">
                        <div class="detaileView-title">
                            <div class="detaileView-title-content">
                                <span>{movieData.movies.title}</span>
                            </div>
                            <span class="detaileView-round">현재상영중</span>
                        </div>
                        <div class="detaileView-score">
                            <div class="detaileView-ticketRate">
                                <span>예매율 </span>
                                <span>{movieData.movies.ticketingRate}%</span>
                            </div>
                            <div class="detaileView-grade">
                                <span>평점 </span>
                                <span>{movieData.movies.averageGrade}</span>
                            </div>
                        </div>
                        <div class="detaileView-info">
                            <div class="detaileView-movie-info">
                                <span>장르 : </span>
                                <span>{movieData.movies.genre}</span>
                                <span> / 기본 : </span>
                                <span>{movieData.movies.screenGrade}세 이상</span>
                                <span>, </span>
                                <span>{movieData.movies.runtime}</span>
                            </div>
                            <div class="detaileView-open">
                                <span>개봉 : </span>
                                <span>{movieData.movies.openingDate}</span>
                            </div>
                        </div>
                        <span class="detaileView-like">
                            <a class="detaileView-link-reservation" href="#">
                                예매
                            </a>
                        </span>
                    </div>
                </div>
            ) : null}

            <div class="detaileView-real-rating">
                <div class="detaileView-wrap_btn">
                    <a
                        class="detaileView-link-gradewrite"
                        onClick={() => {
                            commentWrite(0);
                            commentModifyWriteNone(0);
                        }}
                    >
                        <span>평점작성</span>
                    </a>
                </div>
            </div>
            <div class="detaileView-write-comment">
                <form class="detaileView-comment-form" onSubmit={onSubmit}>
                    <div class="detaileView-comment-select">
                        <select name="grade" value={grade} onChange={onChangeGrade}>
                            <option value="">평점 선택</option>
                            <option value="5">5</option>
                            <option value="4">4</option>
                            <option value="3">3</option>
                            <option value="2">2</option>
                            <option value="1">1</option>
                        </select>
                    </div>
                    <div class="detaileView-comment-input">
                        <input
                            type="text"
                            maxlength="20"
                            placeholder="댓글을 작성해주세요."
                            value={comment}
                            onChange={onChangeComment}
                        ></input>
                    </div>
                    <div class="detaileView-comment-submit">
                        <input
                            type="submit"
                            value="댓글 작성"
                            onClick={() => {
                                commentWriteNone(0);
                                commentModifyWriteNone(0);
                            }}
                        />
                    </div>
                </form>
            </div>

            <div class="comment_wrap-persongrade">
                <ul class="point_col2" id="movie_point_list_container">
                    {commentData.map((commentContent, index) => (
                        <Comment
                            index={index}
                            commentContent={commentContent}
                            commentModifyWrite={commentModifyWrite}
                            commentWriteNone={commentWriteNone}
                            cookies={cookies}
                        ></Comment>
                    ))}
                </ul>
            </div>
        </div>
    );
}

export default MovieDetail;
