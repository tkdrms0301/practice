import axios from 'axios';
import { useState } from 'react';
import { useLocation } from 'react-router-dom';

function commentModifyButton(memberId, commentContentID) {
    var target = document.getElementsByClassName('comment_modify_button');

    console.log(memberId);
    console.log(commentContentID);

    for (let i = 0; i < target.length; i++) {
        if (memberId === commentContentID) {
            console.log('aa ' + memberId);
            console.log('aa ' + commentContentID);
            target[i].style.display = 'block';
        }
    }
}

function Comment({ cookies, commentContent, commentModifyWrite, commentWriteNone, index }) {
    //이것만 변경하면 다른것들 수정버튼 사용불가능
    const memberId = cookies['jwt'];
    const strDate = String(commentContent.date);
    const date = strDate.split('T');
    const search = useLocation().search;
    const movie = new URLSearchParams(search).get('movie');

    const [modifyGrade, setModifyGrade] = useState('');
    const [modifyComment, setModifyComment] = useState('');

    const onClick = (event) => {
        console.log(commentContent.member);
        console.log('sdfasdfasdf' + memberId);
        axios
            .put(
                'http://localhost:8080/' +
                    movie +
                    '/comment/' +
                    commentContent._id +
                    '/' +
                    commentContent.member._id,
                {
                    recommandMember: memberId,
                    recommandNum: commentContent.recommandNum + 1,
                },
            )
            .then((res) => {
                console.log(res.data);
                commentContent.recommandNum = res.data.recommandNum;
                window.location.reload();
            })
            .catch((err) => console.log(err));
        event.preventDefault();
    };

    const onChangeModifyGrade = (event) => {
        setModifyGrade(event.target.value);
        console.log(event.target.value);
    };

    const onChangeModifyComment = (event) => {
        setModifyComment(event.target.value);
        console.log(event.target.value);
    };

    const onModifySubmit = (event) => {
        if (modifyGrade == '') {
            window.alert(['평점 선택은 필수입니다.']);
        } else if (modifyComment == '') {
            window.alert(['내용 작성은 필수입니다.']);
        } else {
            setModifyComment(commentContent.contents);
            setModifyGrade(commentContent.grade);
            axios
                .put('http://localhost:8080/' + movie + '/comment/' + commentContent._id, {
                    content: modifyComment,
                    grade: modifyGrade,
                })
                .then((res) => {
                    console.log(commentContent._id);
                    console.log(memberId);
                })
                .catch((err) => console.log(err));
            window.alert(['수정 완료']);
            window.location.reload();
        }

        event.preventDefault();
    };

    return (
        <li data-spoilercnt="0" data-reportcnt="0">
            <div class="comment-li-box">
                <div class="comment-li-box-content">
                    <a href="javascript:return false;" class="comment_screen_spoiler">
                        &nbsp;
                    </a>
                    <div class="comment_box-image">
                        <span class="comment_thumb-image">
                            <img
                                src="http://img.cgv.co.kr/R2014/images/common/default_profile.gif"
                                alt="사용자 프로필"
                                onerror="errorImage(this, {'type':'profile'})"
                            />
                            <span class="comment_profile-mask"></span>
                            <div class="comment_theater-sticker"></div>
                        </span>
                    </div>
                    <div class="comment_box-contents">
                        <ul class="comment_writerinfo">
                            <li class="comment_writer-name">
                                <a href="#select_main" class="comment_commentMore">
                                    {' '}
                                    <span class="comment_egg-icon comment_good"></span>
                                    {commentContent.member.id}
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="comment_box-comment">
                        <p>{commentContent?.contents}</p>
                    </div>
                    {console.log('ssss' + memberId)}
                    {console.log('ssss' + commentContent.member._id)}
                    <div
                        class="comment_writer-etc"
                        onLoad={() => {
                            commentModifyButton(memberId, commentContent.member._id);
                        }}
                    >
                        <span class="comment_day">{date[0].replaceAll('-', '.')}</span>
                        <span class="comment_like point_like">
                            <a class="comment_btn_point_like">
                                <span>
                                    <img
                                        src="http://img.cgv.co.kr/R2014/images/point/ico_point_default.png"
                                        alt="like"
                                        class="comment_like_red"
                                        onClick={onClick}
                                    />
                                </span>
                                <span class="comment_recommand_count">
                                    {commentContent?.recommandNum}
                                </span>
                            </a>
                        </span>

                        <span class="comment_grade">평점 : {commentContent?.grade}</span>
                        {commentContent.member._id == memberId ? (
                            <button
                                class="comment_modify_button"
                                id="comment_modify_button"
                                onClick={() => {
                                    console.log(index);
                                    commentModifyWrite(index);
                                    commentWriteNone(0);
                                }}
                            >
                                수정
                            </button>
                        ) : null}
                    </div>
                </div>
                <div class="detaileView-Modify-comment">
                    <form class="detaileView-Modify-comment-form" onSubmit={onModifySubmit}>
                        <div class="detaileView-Modify-comment-select">
                            <select
                                name="grade-Modify"
                                value={modifyGrade}
                                onChange={onChangeModifyGrade}
                            >
                                <option value="">평점 선택</option>
                                <option value="5">5</option>
                                <option value="4">4</option>
                                <option value="3">3</option>
                                <option value="2">2</option>
                                <option value="1">1</option>
                            </select>
                        </div>
                        <div class="detaileView-Modify-comment-input">
                            <input
                                type="text"
                                maxlength="20"
                                placeholder="댓글을 작성해주세요."
                                onChange={onChangeModifyComment}
                            ></input>
                        </div>
                        <div class="detaileView-Modify-comment-submit">
                            <input
                                className="detaileView-Modify-comment-submit-input"
                                type="submit"
                                value="댓글 수정"
                                onClick={() => {
                                    commentWriteNone(0);
                                }}
                            />
                        </div>
                    </form>
                </div>
            </div>
        </li>
    );
}
export default Comment;
