import React, {useState, useEffect} from "react";
import axios from 'axios';

function App() {

  const [msg, setMsg] = useState([]);
  const [msgData, setMsgData] = useState([]);

  useEffect(()=> {

    axios.get('/api/work')
      .then((res) => {
        // console.log(JSON.stringify(res.data))
        // return JSON.stringify(res.data)
        // console.log(res.data)
        setMsg(res.data.data)
      
      })

  }, []);

  // console.log('msg', msg)
  // console.log('msgArr', Object.keys(msg))
  // console.log('msgvalueArr', Object.values(msg))

  let keys = Object.keys(msg)
  let values = Object.values(msg)

  useEffect(() => {

    if(!msg) return

    let temp = values.map((item) => 
      <ul>
        <li>{item.age}</li>
        <li>{item.name}</li>
      </ul>
    )

    console.log('temp', temp)
    setMsgData(temp)

  }, [msg])

  return (
    <div className="App">
      {msgData}
    </div>
  );
}

export default App;