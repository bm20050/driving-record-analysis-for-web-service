import axios from "axios";

const UploadForm = (probs) => {

    let fileList = []

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
        formData.append('userid', new Blob([JSON.stringify('user1')], { type: "application/json" }));
        
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
        });

        probs.setNext(probs.next + 1);

    }


    return (
        <div className="uploadform">
            <div className="mb-3">
                <label htmlFor="formFileMultiple" className="form-label">txt 파일을 첨부하세요!</label>
                <input className="form-control" type="file" id="formFileMultiple" multiple onChange={onSaveFile}></input>
            </div>
            <div className="col-auto">
                <button type="button" className="btn btn-outline-primary" onClick={onFileUpload}>파일 업로드</button>
            </div>
        </div>
    )
}

export default UploadForm;