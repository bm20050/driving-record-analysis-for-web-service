import axios from "axios";
import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button, Form } from "react-bootstrap";

const Login = () => {

    const navigator = useNavigate();

    const [userid, setUserid] = useState("");
    const [password, setPassword] = useState("");
    const [showErrorMessage, setShowErrorMessage] = useState(false);

    let response = null;

    useEffect(() => {}, [response])

    const handleLogin = async () => {
        console.log('로그인 버튼클릭')

        response = await axios
            .post('/api/user/login', {
                'userid' : userid,
                'password': password,
            })
            .then((response) => {
                console.log(response.data)
                sessionStorage.setItem('isLoggedIn', true)
                setShowErrorMessage(false)
                navigator('/')
            })
            .catch((error) => {
                setShowErrorMessage(true)
                console.log(error)
                if(error.response.data.code === 'NoUser')
                    console.log('사용자 없음')
                if(error.response.data.code === 'WrongPassword')
                    console.log('비밀번호 오류')
            })

    }

    return (
        <div className="logindiv">
            <Form>
                <Form.Group className="mb-3">
                    <Form.Label>아이디</Form.Label>
                    <Form.Control type="text" value={userid} id="userid"
                        onChange={(e) => setUserid(e.target.value)} required
                        placeholder="아이디를 입력하세요" />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control type="password" value={password} id="password"
                        onChange={(e) => setPassword(e.target.value)} required
                        placeholder="비밀번호를 입력하세요" />
                </Form.Group>
            </Form>
            {showErrorMessage && (
                <div className="loginError" style={{color:'red'}}>ID 혹은 비밀번호가 일치하지 않습니다.</div>
            )}
            <Button onClick={handleLogin}>로그인</Button>
            <Link to="/join">
                <Button>회원가입</Button>
            </Link>
        </div>
    )
}

export default Login;