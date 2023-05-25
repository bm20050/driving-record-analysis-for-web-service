import React from "react";
import Header from "./components/Header";
import Main from "./components/Main";
import './styles/App.css';

function App() {

  return (
    <div className="all">
      <div className="header">
          <Header />
      </div>
      <div className="body">
          <Main />
      </div>
    </div>
  );
}

export default App;