import Chart from "./Chart";

// import 'bootstrap/dist/css/bootstrap.min.css'
import React from "react";


const Right = (probs) => {

    return (
        <>
            <div className="chartarea">
                <Chart chartData={probs.chartData} />
            </div>
        </>
    )
}

export default Right;
