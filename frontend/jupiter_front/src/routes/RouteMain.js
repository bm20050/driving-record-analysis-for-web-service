import { Route, Routes } from "react-router-dom";
import Main from "../components/Main";
import Login from "../pages/Login";
import Join from "../pages/Join";
import MyPage from "../pages/MyPage";

const RouteMain = () => {
    return (
        <>
            <Routes>
                <Route path="/" element={<Main />} />
                <Route path="/login" element={<Login />} />
                <Route path="/join" element={<Join />}/>
                <Route path="/myPage" element={<MyPage />} />
                <Route path="/personalAnalysis" />
            </Routes>
        </>
    );
}

export default RouteMain;