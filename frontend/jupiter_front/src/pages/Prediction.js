import PredictMap from "../components/PredictMap";
import data from "../data/위험운전행동_GPS.json"
import { Button } from "react-bootstrap";

const Prediction = () => {

    const handlePred = () => {
        
    }

    return (
        <div className="prediction">
            <PredictMap data={data} />
            <div className="predresult">
                <Button onClick={handlePred}>
                    예측결과 찍기
                </Button>
            </div>
        </div>
    )

}

export default Prediction;