import { useEffect, useState } from "react";
import tayo from '../image/tayo.png'

const { kakao } = window;

let map = null;
let marker = null;

const PredictMap = (props) => {

    // let [map, setMap] = useState();

    // console.log('data', probs.data, ",", probs.x, ", ", probs.y)

    useEffect(() => {

        // 지도 호출
        kakao.maps.load(() => {

            // 지도를 표시할 div 
            const container = document.getElementById('predmap');

            // 지도 그리기
            const options = {
                center: new kakao.maps.LatLng(props.y, props.x), // 지도의 중심좌표
                level: 2 // 지도의 확대 레벨
            }

            // 지도 생성
            // setMap(new kakao.maps.Map(container, options))
            map = new kakao.maps.Map(container, options)

        })

    }, []);

    useEffect(() => {

        if (!props.data)
            return;

        // 지도 호출
        kakao.maps.load(() => {

            // 지도를 표시할 div 
            const container = document.getElementById('predmap');

            // 지도 그리기
            const options = {
                center: new kakao.maps.LatLng(props.y, props.x), // 지도의 중심좌표
                level: 2 // 지도의 확대 레벨
            }

            // 지도 생성
            // setMap(new kakao.maps.Map(container, options))
            map = new kakao.maps.Map(container, options)

        })

        if (marker != null)
            marker.setMap(null);

        // let imgTag = tayo
        let imageSize = new kakao.maps.Size(30, 30);

        // 마커 이미지 생성
        let imgMarker = new kakao.maps.MarkerImage(tayo, imageSize);

        // 마커 생성
        marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(props.y, props.x),
            image: imgMarker,
        })

        // map.center = new kakao.maps.LatLng(probs.y, probs.x);

        marker.setMap(map)

    }, [props.y, props.x])


    return (
        <>
            <div id="predmap" className="predmap">
            </div>
        </>
    )
}

export default PredictMap;