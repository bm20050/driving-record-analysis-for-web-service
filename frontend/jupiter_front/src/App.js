import React from "react";
import Header from "./component/Header";
import Main from "./component/Main";
import './css/App.css';

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