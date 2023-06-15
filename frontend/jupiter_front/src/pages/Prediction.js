import { useEffect, useState, useRef } from "react";
import PredictMap from "../components/PredictMap";
import tempData from "../data/위험운전행동_GPS.json"
import { Button } from "react-bootstrap";
import axios from "axios";

const Prediction = () => {

    const [data, setData] = useState("")
    const [now, setNow] = useState(new Date());
    const [seconds, setSeconds] = useState(now.getSeconds())
    const [flag, SetFlag] = useState(false)
    const interval = useRef(now.getSeconds());

    useEffect(() => {
        interval.current = setInterval(() => {
            setSeconds(prev => prev + 1);
            timerCheck()
        }, 1000)
        return () => {
            clearInterval(interval.current);
        }
    },[])



    const timerCheck = () => {
        console.log('hihi')
    }

    const handlePred = async () => {
 
        console.log('예측버튼 클릭')
        tempData.map()
        
        await axios
            .post('/api/prediction', {
                'hour': now.getHours(),
                'minute': now.getMinutes(),
                'x': "",
                'y': "",
            })
            .then((response) => {
                setData(response.date)
            })
            .catch((error) => {
                console.log('prediction error')
                console.log(error)
            })
    }

    return (
        <div className="prediction">
            <PredictMap data={data} />
            <div className="predresult">
                {seconds}
                <Button onClick={handlePred}>
                    예측결과 찍기
                </Button>
                <Button onClick={handlePred}>
                    정지
                </Button>
            </div>
        </div>
    )

}

export default Prediction;