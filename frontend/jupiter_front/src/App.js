import React from "react";
import Header from "./components/Header";
import Main from "./components/Main";
import './styles/App.css';
import { BrowserRouter } from "react-router-dom";

function App() {

    return (
        <BrowserRouter>
            <div className="all">
                <div className="header">
                    <Header />
                </div>
                <div className="body">
                    <Main />
                </div>
            </div>
        </BrowserRouter>
    );
}

export default App;