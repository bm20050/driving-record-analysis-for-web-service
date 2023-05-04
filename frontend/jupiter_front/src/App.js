import React, {useState, useEffect} from "react";
import axios from 'axios';

function App() {

  const [msg, setMsg] = useState([]);
  const [msgData, setMsgData] = useState([]);

  useEffect(()=> {

    axios.get('/api/getData')
      .then((res) => {
        // console.log(JSON.stringify(res.data))
        // return JSON.stringify(res.data)
        // console.log(res.data)
        setMsg(res.data)
      
      })

  }, []);

  // console.log('msg', msg)
  // console.log('msgArr', Object.keys(msg))
  // console.log('msgvalueArr', Object.values(msg))

  // let keys = Object.keys(msg)
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

  const handleSubmit = async (e) => {
    e.preventDefault();

    await axios
        .post("http://localhost:8080/api/send1",
            {
            "userName": "hello",
            "password": "1234"
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