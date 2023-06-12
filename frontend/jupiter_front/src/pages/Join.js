import axios from "axios";
import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button, Form } from "react-bootstrap";

const Join = () => {

    const navigator = useNavigate();

    const [userid, setUserid] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");

    const handleJoin = async () => {
        console.log('회원가입 버튼클릭')

        await axios
            .post('/api/join', {
                'userid' : userid,
                'username': username,
                'email': email,
                'password': password,
            })
            .then((response) => {
                console.log(response.data)
                navigator('/login')
            })
            .catch((error) => {
                console.log('에러발생화면')
                alert('아이디가 중복입니다.')
                console.log(error)
            })

    }

    return(
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
                <Form.Group className="mb-3">
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control type="password" value={password} id="password"
                        onChange={(e) => setPassword(e.target.value)} required
                        placeholder="비밀번호를 입력하세요" />
                </Form.Group>
                {/* <Form.Group className="mb-3">
                    <Form.Label>비밀번호확인</Form.Label>
                    <Form.Control type="password" value={password} id="passwordcheck"
                        onChange={(e) => setPassword(e.target.value)} required
                        placeholder="비밀번호를 입력하세요" />
                </Form.Group> */}
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