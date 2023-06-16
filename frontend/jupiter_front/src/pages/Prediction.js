import { useEffect, useState, useRef } from "react";
import PredictMap from "../components/PredictMap";
import tempData from "../data/tempData.json"
import { Button } from "react-bootstrap";
import axios from "axios";

const Prediction = () => {

    let [data, setData] = useState("")
    let [now, setNow] = useState(new Date());
    let [index, setIndex] = useState(500);
    let [flag, setFlag] = useState(false);
    let interval = useRef(now.getSeconds());

    useEffect(() => {

        flag && (interval.current = setInterval(() => {
            setNow(new Date())
            handlePred()
            setIndex(index++)
        }, 1000))

        return () => {
            clearInterval(interval.current);
        }

    }, [flag])

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

    const startInterval = () => {
        setFlag(true)
        handlePred()
    }

    const stopInterval = () => {
        setFlag(false)
        clearInterval(interval.current);
    }

    const makeTag = (data) => {

        if (data === "안전")
            return "predinfo-green"

        else if (data === "주의")
            return "predinfo-yellow"

        else if (data === "위험")
            return "predinfo-orange"

        else if (data === "매우 위험")
            return "predinfo-red"
    }

    return (
        <div className="prediction">
            <div className="predresult">
                <div className="predbutton">
                    <Button onClick={startInterval} variant="outline-primary">
                        예측결과 분석
                    </Button>
                    <Button onClick={stopInterval} variant="outline-primary">
                        정지
                    </Button>
                </div>
                <div className="predinfo">
                    <span className={makeTag(data)}>{data}</span> 운전 예측 구간입니다.
                </div>
            </div>
            <PredictMap data={data} x={tempData[index].GPS_X} y={tempData[index].GPS_Y} />
        </div>
    )

}

export default Prediction;