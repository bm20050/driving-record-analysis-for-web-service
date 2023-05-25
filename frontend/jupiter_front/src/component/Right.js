import Chart from "./Chart";

const Right = (probs) => {

    return (
        <>
            <div className="selectbox">
                <form>
                    <input type="date" ref={probs.reqDate} onChange={probs.handleDate} name="reqdate" />
                    <select ref={probs.plate}>
                        <option key="total" value="total">전체</option>
                        <option key="부산70자1854" value="부산70자1854">부산70자1854</option>
                        <option key="부산70자1860" value="부산70자1860">부산70자1860</option>
                        <option key="부산70자1893" value="부산70자1893">부산70자1893</option>
                        <option key="부산70자1894" value="부산70자1894">부산70자1894</option>
                    </select>
                    <select ref={probs.danger}>
                        <option key="totalDan" value="totalDan">전체</option>
                        <option key="suddenAcc" value="suddenAcc">급가속</option>
                        <option key="suddenDrop" value="suddenDrop">급감속</option>
                        <option key="suddenDeparture" value="suddenDeparture">급출발</option>
                        <option key="suddenStop" value="suddenStop">급정지</option>
                    </select>
                    <button onClick={probs.searchData}>조회</button>
                </form>
            </div>
            <div className="chart">
                <Chart chartData={probs.chartData} />
            </div>
        </>
    )
}

export default Right;
