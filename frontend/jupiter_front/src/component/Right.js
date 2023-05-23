import axios from "axios";
import { useState, useEffect, useRef } from "react";

const Right = (probs) => {

    // //날짜선택 state 변수
    // const [targetDt, setTargetDt] = useState();

    // //날짜선택박스 제어
    // const reqDate = useRef();

    // //targetDt 변경 시
    // useEffect ( () => {
    //     console.log('targetDt', targetDt)
    // },[targetDt]);

    // //날짜선택박스 이벤트
    // const handleDate = () => {
    //     setTargetDt(reqDate.current.value);
    // };

    // // 검색버튼
    // const searchData = async (e) => {
    //     e.preventDefault();
    
    //     await axios
    //         .post("http://localhost:8080/api/totalCount",

    //             // console.log('year', targetDt.substring(2,4)),
    //             {
    //                 "year": targetDt.substring(2,4),
    //                 "month": targetDt.substring(5,7),
    //                 "day": targetDt.substring(8,10),
    //                 "busNumber": "null",
    //             }
    //         )
    //         .then((response) => {
    //             // console.log("response ok")
    //             console.log(response.data)
    //         })
    //         .catch((error) => {
    //             console.log(error);
    //         });
    // }
//onChange={probs.handleDate} 
    return (
        <>
            <div className="selectbox">
                <form>
                    <input type="date" ref={probs.reqDate} name="reqdate" />
                    <select>
                        <option key="banana" value="banana">전체</option>
                        <option key="apple" value="apple">사과</option>
                        <option key="orange" value="orange">오렌지</option>
                    </select>
                    <button onClick={probs.searchData}>조회</button>
                </form>
            </div>
            <div className="chart">
                차트 영역
            </div>
        </>
    )
}

export default Right;