import axios from "axios";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Button, Form } from "react-bootstrap";

const Login = () => {

    const [userid, setUserid] = useState("");
    const [password, setPassword] = useState("");
    const [showErrorMessage, setShowErrorMessage] = useState(false);
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const handleLogin = async () => {
        console.log('로그인 버튼클릭')
        console.log('userid', userid)
        console.log('password', password)

        await axios
            .post('/api/login', {
                'userid' : userid,
                'password': password,
            })
            .then((response) => {
                console.log(response.data)
            })
            .catch((error) => {
                console.log(error)
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
            <Button onClick={handleLogin}>로그인</Button>
            <Link to="/join">
                <Button>회원가입</Button>
            </Link>
        </div>
    )
}

export default Login;