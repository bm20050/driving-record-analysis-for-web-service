import axios from "axios";
import { useState, useEffect, useRef } from "react";

import DrawingMap from "./DrawingMap";
import Right from "./Right";
import SelectBox from "./SelectBox";
import AccChart from "./charts/AccChart";
import DepartChart from "./charts/DepartChart";
import DropChart from "./charts/DropChart";
import StopChart from "./charts/StopChart";

const Main = () => {

        //날짜선택 state 변수
        let [targetDt, setTargetDt] = useState("2022-12-01");
        
        //백엔드 응답 state 변수
        const [data, setData] = useState();

        //temp...!
        let [next, setNext] = useState(0);
        let [prev, setPrev] = useState(0);

        //클러스터 선택하면 변수 담아줄거
        let [chartData, setChartData] = useState();

        //날짜선택박스 제어
        const reqDate = useRef();
    
        //셀렉트박스 제어
        const plate = useRef();
        const danger = useRef();

        //targetDt 변경 시
        useEffect ( () => {
            // console.log('useEffect내 targetDt', targetDt)

        },[targetDt]);
    
        //날짜선택박스 이벤트
        const handleDate = () => {
            setTargetDt(reqDate.current.value);
        };
        
        useEffect(()=> {
            searchData()
        },[])

        // 검색버튼
        const searchData = async (e) => {
            // e.preventDefault();
            // setTargetDt(reqDate.current.value);
            console.log("targetDt", targetDt)
            console.log("plate", plate.current.value)
            setPrev(++prev);

            await axios
                .post("http://localhost:8080/api/totalCount",
    
                    // console.log('year', targetDt.substring(2,4)),
                    {
                        "year": parseInt(targetDt.substring(2,4)),
                        "month": parseInt(targetDt.substring(5,7)),
                        "day": parseInt(targetDt.substring(8,10)),
                        "busNumber": plate.current.value,
                        "danger": danger.current.value
                    }
                )
                .then((response) => {
                    console.log("response ok")
                    console.log(response.data)
                    setData(response.data)
                })
                .catch((error) => {
                    console.log(error);
                });
            
            setNext(++next);
            // console.log(temp);
        }

    return (
        <>
            <div className="selectbox">
                <SelectBox reqDate={reqDate} plate={plate} danger={danger} chartData={chartData} searchData={searchData} handleDate={handleDate} next={next} />
            </div>
            <div className="content">
                <div className="left">
                    <DrawingMap targetDt={targetDt} data={data} next={next} prev={prev} setChartData={setChartData} />
                </div>
                <div className="right">
                    <Right reqDate={reqDate} plate={plate} danger={danger} chartData={chartData} searchData={searchData} handleDate={handleDate} next={next} />
                </div>
            </div>
            <div className="gragh">
                <AccChart chartData={chartData} />
                <DepartChart chartData={chartData} />
                <DropChart chartData={chartData} />
                <StopChart chartData={chartData} />
            </div>
        </>

    )

}

export default Main;