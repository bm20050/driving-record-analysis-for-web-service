import { useEffect, useState } from "react";

const { kakao } = window;

const PredictMap = (probs) => {

    let [map, setMap] = useState();

    useEffect(() => {

        // 지도 호출
        kakao.maps.load(() => {

            // 지도를 표시할 div 
            const container = document.getElementById('predmap');

            // 지도 그리기
            const options = {
                center: new kakao.maps.LatLng(35.204771, 129.166241), // 지도의 중심좌표
                level: 8 // 지도의 확대 레벨
            }

            // 지도 생성
            setMap(new kakao.maps.Map(container, options))
            // console.log(map)

        })

    }, []);

    // useEffect(() => {

    //     let imgTag = "https://github.com/beeguriri/driving-record-analysis-for-web-service/blob/main/frontend/jupiter_front/src/image/SuddenAcc.png?raw=true";
    //     let imageSize = new kakao.maps.Size(30, 30);

    //     // 마커 이미지 생성
    //     let imgMarker = new kakao.maps.MarkerImage(imgTag, imageSize);

    // }, [])


    return (
        <>
            <div id="predmap" className="predmap">
            </div>
        </>
    )
}

export default PredictMap;