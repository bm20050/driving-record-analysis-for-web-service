import DrawingMap from "./DrawingMap";
import Right from "./Right";

const Main = () => {

    return (
        <div className="main">
            <div className="left">
                <DrawingMap />
            </div>
            <div className="right">
                <Right />
            </div>
        </div>

    )

}

export default Main;