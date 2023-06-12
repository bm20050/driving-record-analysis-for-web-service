import axios from "axios";

const MyPage = () => {

    return (
        <>
        나의 페이지 영역
        {sessionStorage.getItem('email')}
        {/* //email:"aaa@abc.com", userid:"user1", username: "일반사용자" */}

        </>
    )
}

export default MyPage;