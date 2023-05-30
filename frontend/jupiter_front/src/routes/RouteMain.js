import { Route, Routes } from "react-router-dom";
import Main from "../components/Main";

const RouteMain = () => {
    return (
        <>
            <Routes>
                <Route path="/" element={<Main />} />
                <Route path="/login" />
                <Route path="/join" />
                <Route path="/myPage" />
                <Route path="/personalAnalysis" />
            </Routes>
        </>
    );
}

export default RouteMain;