import '../css/Login.css';
import { useState } from 'react';
import axios from 'axios';
import { Navigate } from 'react-router-dom';

export default function Login({ cookies, setCookie, hasCookie, setHasCookie }) {
    const [id, setId] = useState('');
    const [password, setPassword] = useState('');
    const [check, setCheck] = useState(false);

    const onChangeId = (e) => {
        setId(e.target.value);
        console.log(id);
    };

    const onChangePw = (e) => {
        setPassword(e.target.value);
        console.log(password);
    };

    const onSubmit = async (e) => {
        try {
            e.preventDefault(); // 불필요한 렌더링 방지

            console.log(id);
            console.log(password);

            const login = await axios.post(
                'http://localhost:8080/login/member',
                {
                    id: id,
                    password: password,
                },
                { withCredentials: true },
            );

            console.log(login.data);
            if (login.status === 200) {
                alert('로그인 성공!');
                setCookie('jwt', login.data);
                setHasCookie(() => true);
            }
        } catch (err) {
            console.log(err.response.status);
            if (err.response.status === 401) {
                alert('로그인 실패!');
                setId('');
                setPassword('');
            }
        }
    };

    return (
        <div className="box-login">
            <form className="login-form" onSubmit={onSubmit}>
                <p>아이디 비밀번호를 입력하신 후, 로그인 버튼을 클릭해 주세요.</p>
                <div className="login">
                    <p>
                        <input
                            placeholder="  ID"
                            type="text"
                            title="아이디"
                            id="txtUserId"
                            name="txtUserId"
                            required="required"
                            value={id}
                            onChange={onChangeId}
                        />
                    </p>
                    <p>
                        <input
                            placeholder="PW"
                            type="password"
                            title="패스워드"
                            id="txtPassword"
                            name="txtPassword"
                            required="required"
                            value={password}
                            onChange={onChangePw}
                        />
                    </p>
                </div>
                <button type="submit" id="submit" title="로그인">
                    <span>로그인</span>
                </button>
            </form>
            {cookies['jwt'] !== undefined && <Navigate to="/" />}
        </div>
    );
}
