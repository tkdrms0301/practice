import '../css/Sign.css';
import axios from 'axios';
import { useState } from 'react';
import { Navigate } from 'react-router-dom';

function Sign() {
    const [id, setId] = useState('');
    const [name, setName] = useState('');
    const [password, setPassword] = useState('');
    const [passwordCheck, setPasswordCheck] = useState('');
    const [check, setCheck] = useState(false);
    const onChangeId = (event) => {
        setId(event.target.value);
    };
    const onChangeName = (event) => {
        setName(event.target.value);
    };
    const onChangePassword = (event) => {
        setPassword(event.target.value);
    };
    const onChangePasswordCheck = (event) => {
        setPasswordCheck(event.target.value);
    };

    const onSubmit = (event) => {
        if (password === passwordCheck) {
            axios
                .post('http://localhost:8080/sign/member', {
                    id: id,
                    password: password,
                    name: name,
                })
                .then((res) => {
                    console.log(res);
                    if (res.status === 200) {
                        alert('회원가입 성공!');
                        setCheck(true);
                    }
                })
                .catch((err) => {
                    console.log(err);
                    if (err.response.status === 400) {
                        alert('회원가입 실패!');
                    }
                });
        } else {
            alert('잘못된 비밀번호 확인입니다.');
            setPassword('');
            setPasswordCheck('');
        }
        event.preventDefault();
    };
    return (
        <div class="box-sign">
            <form class="sign-form" method="post" onSubmit={onSubmit}>
                <p class="info">회원정보를 입력하신 후, 가입 버튼을 클릭해 주세요.</p>
                <div class="sign">
                    <p>
                        <input
                            placeholder="  ID"
                            type="text"
                            title="아이디"
                            id="txtUserId"
                            name="txtUserId"
                            value={id}
                            onChange={onChangeId}
                            required="required"
                        />
                    </p>
                    <p>
                        <input
                            placeholder="  이름"
                            type="text"
                            title="이름"
                            id="txtUserName"
                            name="txtUserName"
                            value={name}
                            onChange={onChangeName}
                            required="required"
                        />
                    </p>
                    <p>
                        <input
                            placeholder="  PW"
                            type="password"
                            title="패스워드"
                            id="txtPassword"
                            name="txtPassword"
                            value={password}
                            onChange={onChangePassword}
                            required="required"
                        />
                    </p>
                    <p>
                        <input
                            placeholder="  PW재확인"
                            type="password"
                            title="패스워드"
                            id="txtRePassword"
                            name="txtRePassword"
                            value={passwordCheck}
                            onChange={onChangePasswordCheck}
                            required="required"
                        />
                    </p>
                </div>
                <button type="submit" id="submit" title="회원가입">
                    <span>회원가입</span>
                </button>
            </form>
            {check && <Navigate to="/login" />}
        </div>
    );
}

export default Sign;
