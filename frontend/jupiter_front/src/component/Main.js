import axios from "axios";
import { useState, useEffect, useRef } from "react";

import DrawingMap from "./DrawingMap";
import Right from "./Right";

const Main = () => {

        //날짜선택 state 변수
        let [targetDt, setTargetDt] = useState("2023-01-01");

        //날짜선택박스 제어
        const reqDate = useRef();
    
        // //targetDt 변경 시
        useEffect ( () => {
            console.log('useEffect내 targetDt', targetDt)

        },[targetDt]);
    
        //날짜선택박스 이벤트
        const handleDate = () => {
            setTargetDt(reqDate.current.value);
        };

        //백엔드 응답 state 변수
        const [data, setData] = useState();

        //temp...!
        let [temp, setTemp] = useState(0);
    
        
        // 검색버튼
        const searchData = (e) => {
            e.preventDefault();
            // setTargetDt(reqDate.current.value);
            console.log("targetDt", targetDt)
            setTemp (++temp);

            axios
                .post("http://localhost:8080/api/totalCount",
    
                    // console.log('year', targetDt.substring(2,4)),
                    {
                        "year": targetDt.substring(2,4),
                        "month": targetDt.substring(5,7),
                        "day": targetDt.substring(8,10),
                        // "busNumber": "부산70자1854",
                        "busNumber": "전체",
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
            
            setTemp(++temp);
            // console.log(temp);
        }

    return (
        <div className="main">
            <div className="left">
                <DrawingMap targetDt={targetDt} data={data} temp={temp} />
            </div>
            <div className="right">
                <Right reqDate={reqDate} searchData={searchData} handleDate={handleDate} />
            </div>
        </div>

    )

}

export default Main;