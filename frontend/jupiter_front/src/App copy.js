import React, {useState, useEffect} from "react";
import axios from 'axios';

function App() {

  const [msg, setMsg] = useState([]);
  const [msgData, setMsgData] = useState([]);

  // console.log('msg', msg)
  // console.log('msgArr', Object.keys(msg))
  // console.log('msgvalueArr', Object.values(msg))

  // let keys = Object.keys(msg)
  //스프링부트에서 데이터 받아오기
  useEffect(()=> {

    axios.get('/api/getData')
      .then((res) => {
        // console.log(JSON.stringify(res.data))
        // return JSON.stringify(res.data)
        console.log('res', res.data)
        setMsg(res.data)
      
      })

  }, []);

  let values = Object.values(msg)

  useEffect(() => {

    if(!msg) return

    let temp = values.map((item) => 
      <ul>
        <li>{item.age}</li>
        <li>{item.userName}</li>
      </ul>
    )

    console.log('temp', temp)
    setMsgData(temp)

  }, [msg])
  
  // 리액트에서 스프링부트로 데이터 전송
  const handleSubmit = async (e) => {
    e.preventDefault();

    await axios
        .post("http://localhost:8080/api/sendData",
            {
            "date": "2023-01-01",
            "time": "17"
            }
        )
        .then((response) => {
            console.log(response.data)
        })
        .catch((error) => {
            console.log(error);
        });
  }


  return (
    <div className="App">
      {msgData}
      <button onClick={handleSubmit} >버튼</button>
    </div>
  );
}

export default App;