import axios from "axios";
import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button, Form } from "react-bootstrap";

const Join = () => {

    const navigator = useNavigate();

    const [userid, setUserid] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [password2, setPassword2] = useState("");
    const [email, setEmail] = useState("");
    const [nameBlankError, setNameBlankError] = useState(false);
    const [passwordBlankError, setPasswordBlankError] = useState(false);
    const [passwordValidationError, setPasswordValidationError] = useState(false);

    const handleJoin = async () => {

        if(userid.includes(" ") || username.includes(" ") || email.includes(" ") || password.includes(" ")) {
            alert('올바른 양식으로 입력해주세요.')
            return;
        }

        if(password !== password2) {
            alert('비밀번호를 동일하게 입력해주세요.')
            return;
        }

        await axios
            .post('/api/user/join', {
                'userid': userid,
                'username': username,
                'email': email,
                'password': password,
            })
            .then((response) => {
                console.log(response.data)
                navigator('/login')
            })
            .catch((error) => {
                console.log(error)
                if (error.response.data.code === 'DupUser')
                    alert('아이디가 중복입니다.')
                if (error.response.data.code === 'email')
                    alert('이메일 양식이 올바르지 않습니다.')
            })
    }

    useEffect(() => {
        if (username.includes(" ")) {
            setNameBlankError(true)
        } else {
            setNameBlankError(false)
        }
    }, [username])

    useEffect(() => {
        if (password.includes(" ")) {
            setPasswordBlankError(true)
        } else {
            setPasswordBlankError(false)
        }
    }, [password])

    useEffect(() => {
        if (password2 === "")
            return;
        if (password !== password2) {
            setPasswordValidationError(true)
        } else {
            setPasswordValidationError(false)
        }
    }, [password, password2])

    return (
        <div className="joindiv">
            <p>회원가입</p>
            <Form>
                <Form.Group className="mb-3">
                    <Form.Label>아이디</Form.Label>
                    <Form.Control type="text" value={userid} id="userid"
                        onChange={(e) => setUserid(e.target.value)} required
                        placeholder="아이디를 입력하세요" />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>이름</Form.Label>
                    <Form.Control type="text" value={username} id="username"
                        onChange={(e) => setUsername(e.target.value)} required
                        placeholder="이름을 입력하세요" />
                </Form.Group>
                {nameBlankError && (
                    <div className="loginError" style={{ color: 'red' }}>공백을 제외하고 입력해주세요.</div>
                )}
                <Form.Group className="mb-3">
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control type="password" value={password} id="password"
                        onChange={(e) => setPassword(e.target.value)} required
                        placeholder="비밀번호를 입력하세요" />
                </Form.Group>
                {passwordBlankError && (
                    <div className="loginError" style={{ color: 'red' }}>공백을 제외하고 입력해주세요.</div>
                )}
                <Form.Group className="mb-3">
                    <Form.Label>비밀번호확인</Form.Label>
                    <Form.Control type="password" value={password2} id="passwordcheck"
                        onChange={(e) => setPassword2(e.target.value)} required
                        placeholder="비밀번호를 입력하세요" />
                </Form.Group>
                {passwordValidationError && (
                    <div className="loginError" style={{ color: 'red' }}>비밀번호가 다릅니다.</div>
                )}
                <Form.Group className="mb-3">
                    <Form.Label>이메일</Form.Label>
                    <Form.Control type="text" value={email} id="email"
                        onChange={(e) => setEmail(e.target.value)} required
                        placeholder="aaa@abc.com" />
                </Form.Group>
            </Form>
            <Button onClick={handleJoin}>회원가입</Button>
            <Button>취소</Button>
        </div>
    )
}

export default Join;