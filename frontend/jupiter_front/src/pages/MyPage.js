import axios from "axios";
import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button, Form } from "react-bootstrap";


const MyPage = () => {

    const navigator = useNavigate();

    const [userid, setUserid] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [password2, setPassword2] = useState("");
    const [email, setEmail] = useState("");
    const [nameBlankError, setNameBlankError] = useState(false);
    const [passwordBlankError, setPasswordBlankError] = useState(false);
    const [passwordValidationError, setPasswordValidationError] = useState(false);

    const loginfo = async () => {
        await axios
        .post('/api/user/getuser')
        .then((response) => {
            // console.log(response.data)
            setUserid(response.data.userid)
            setUsername(response.data.username)
            setEmail(response.data.email)
        })
        .catch((error) => {
            // console.log('에러1')
            // console.log(error)
        })
    }

    useEffect(() => {
        // console.log('MyPage 진입')
        if(sessionStorage.getItem('isLoggedIn')) {
            loginfo()
        }

    }, [])

    const handleUpdate = async () => {
        // console.log('수정버튼 버튼클릭')

        if(username.includes(" ") || email.includes(" ") || password.includes(" ")) {
            alert('올바른 양식으로 입력해주세요.')
            return;
        }

        if(password !== password2) {
            alert('비밀번호를 동일하게 입력해주세요!')
            return;
        }

        await axios
            .put('/api/user/update', {
                'userid': userid,
                'username': username,
                'email': email,
                'password': password,
            })
            .then((response) => {
                navigator('/')
            })
            .catch((error) => {
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
            <p>회원정보 수정</p>
            <Form>
                <Form.Group className="mb-3">
                    <Form.Label>이름</Form.Label>
                    <Form.Control type="text" value={username} id="username"
                        onChange={(e) => setUsername(e.target.value)} required />
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
                        onChange={(e) => setEmail(e.target.value)} required />
                </Form.Group>
            </Form>
            <Button onClick={handleUpdate}>회원정보 수정</Button>
            
            <Link to="/">
                <Button>취소</Button>
            </Link>

        </div>
    )
}

export default MyPage;