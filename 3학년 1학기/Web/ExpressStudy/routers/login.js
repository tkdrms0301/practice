import "../css/Login.css";
import { useState, useEffect } from "react";
import axios from "axios";

function Login() {
  const [posts, setPosts] = useState(false);
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");

  const onChangeId = useCallback((e) => {
    setId(e.target.value);
  }, []);

  const onChangePw = useCallback((e) => {
    setPassword(e.target.value);
  }, []);

  const onSubmit = useCallback(
    (e) => {
      useEffect(() => {
        axios
          .post("http://localhost:8080/sign/member", {
            id: id,
            password: password,
          })
          .then((res) => {
            console.log(res);
            setPosts(res.data);
          })
          .catch((err) => console.log(err));
      }, []);
      // submit 이벤트는 브라우저에서 새로고침을 발생
      // 이를 방지하기 위해 이 함수를 호출
      e.preventDefault();
    },
    [onInsert, value]
  );

  return (
    <div class="box-login">
      <form class="login-form" method="post" onSubmit={onSubmit}>
        <p>아이디 비밀번호를 입력하신 후, 로그인 버튼을 클릭해 주세요.</p>
        <div class="login">
          <p>
            <input
              placeholder="  ID"
              type="text"
              title="아이디"
              id="txtUserId"
              name="txtUserId"
              required="required"
              onChange={onChangeId}
            />
          </p>
          <p>
            <input
              placeholder="  PW"
              type="password"
              title="패스워드"
              id="txtPassword"
              name="txtPassword"
              required="required"
              onChange={onChangePw}
            />
          </p>
        </div>
        <button type="submit" id="submit" title="로그인">
          <span>로그인</span>
        </button>
      </form>
    </div>
  );
}

export default Login;
