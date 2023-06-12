import React from "react";
import Header from "./components/Header";
import Main from "./components/Main";
import './styles/App.css';
import { BrowserRouter } from "react-router-dom";
import RouteMain from "./routes/RouteMain";

function App() {

    return (
        <>
            <div className="all">
                <BrowserRouter>
                    <div className="header">
                        <Header />
                    </div>
                    <div className="main">
                        <RouteMain />
                    </div>
                </BrowserRouter>
            </div>
        </>
    );
}

export default App;