import Chart from "./charts/Chart";
// import 'bootstrap/dist/css/bootstrap.min.css'

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
