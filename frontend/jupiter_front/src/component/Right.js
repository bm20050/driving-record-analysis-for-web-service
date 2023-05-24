import axios from "axios";
import { useState, useEffect, useRef } from "react";

const Right = (probs) => {

    return (
        <>
            <div className="selectbox">
                <form>
                    <input type="date" ref={probs.reqDate} onChange={probs.handleDate} name="reqdate" />
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