import Chart from "./charts/Chart";
// import 'bootstrap/dist/css/bootstrap.min.css'

const Right = (probs) => {

    return (
        <>

            <div className="chartarea">
                <div className="explain">
                    설명
                </div>
                <Chart chartData={probs.chartData} />
            </div>
        </>
    )
}

export default Right;
