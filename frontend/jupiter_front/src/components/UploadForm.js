import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import { Form, Button } from "react-bootstrap";

const UploadForm = (probs) => {

    let fileList = []

    const navigator = useNavigate();
    const [userid, setUserid] = useState("");

    const loginfo = async () => {
        await axios
            .post('/api/user/getuser')
            .then((response) => {
                console.log(response.data)
                setUserid(response.data.userid)
            })
            .catch((error) => {
                console.log('에러1')
                console.log(error)
            })
    }

    useEffect(() => {
        console.log('upload form 진입')
        if (sessionStorage.getItem('isLoggedIn')) {
            loginfo()
        }

    }, [])

    const onSaveFile = (e) => {

        const uploadFile = Array.prototype.slice.call(e.target.files);

        uploadFile.forEach((element) => {
            fileList.push(element);
        });

    }

    const onFileUpload = async () => {

        const formData = new FormData();

        console.log(sessionStorage.getItem('userid'))

        // JSON 형식으로 파싱 후 추가
        formData.append('userid', new Blob([JSON.stringify(userid)], { type: "application/json" }));

        fileList.forEach((file) => {

            formData.append('multipartFiles', file);
        })

        probs.setPrev(probs.prev + 1);

        await axios({
            method: "POST",
            url: `api/uploadFiles`,
            mode: "cors",
            headers: {
                "Content-Type": "multipart/form-data",
            },
            data: formData,

        }).then((response) => {

            console.log("response ok")
            console.log(response.data)
            probs.setData(response.data)
            fileList = [] //첨부파일 초기화

        }).catch((error) => {
            console.log(error);
            console.log('로그인정보 없음')
            navigator('/login')
        });

        probs.setNext(probs.next + 1);

    }


    return (
        <div className="uploadform">
            <Form.Group>
                <Form.Control type="file" id="formFileMultiple" multiple onChange={onSaveFile} />
            </Form.Group>
            <Button variant="outline-primary" onClick={onFileUpload}>파일 업로드</Button>
        </div>
    )
}

export default UploadForm;