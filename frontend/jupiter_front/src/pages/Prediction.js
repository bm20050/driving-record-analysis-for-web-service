import { useEffect, useState, useRef } from "react";
import PredictMap from "../components/PredictMap";
import tempData from "../data/tempData.json"
import { Button } from "react-bootstrap";
import axios from "axios";

const Prediction = () => {

    let [data, setData] = useState("")
    let [now, setNow] = useState(new Date());
    let [index, setIndex] = useState(160);
    const [flag, setFlag] = useState(false);
    let interval = useRef(now.getSeconds());


    useEffect(() => {

        interval.current = setInterval(() => {
            setNow(new Date())
            handlePred()
            setIndex(index++)
        }, 1000)

        return () => {
            clearInterval(interval.current);
        }
    }, [])

    const handlePred = async () => {

        // console.log('예측버튼 클릭')        
        await axios
            .post('/api/prediction', {
                'hour': now.getHours(),
                'minute': now.getMinutes(),
                'x': tempData[index].GPS_X,
                'y': tempData[index].GPS_Y,
            })
            .then((response) => {
                setData(response.data)
                console.log(tempData[index].index, " : ", response.data)
            })
            .catch((error) => {
                console.log('prediction error')
                console.log(error)
            })
    }

    const stopInterval = () => {
        clearInterval(interval.current);
    }

    return (
        <div className="prediction">
            <PredictMap data={data} x={tempData[index].GPS_X} y={tempData[index].GPS_Y} />
            <div className="predresult">
                {now.getSeconds()}
                <Button onClick={setFlag(true)}>
                    예측결과 찍기
                </Button>
                <Button onClick={stopInterval}>
                    정지
                </Button>
            </div>
        </div>
    )

}

export default Prediction;