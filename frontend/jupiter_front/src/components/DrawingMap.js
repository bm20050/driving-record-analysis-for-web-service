import React, { useEffect, useState } from "react";

const { kakao } = window;

const DrawingMap = (props) => {

    let [map, setMap] = useState();
    // console.log("map", probs.data)

    useEffect(() => {

        // 지도 호출
        kakao.maps.load(() => {

            // 지도를 표시할 div 
            const container = document.getElementById('map');

            // 지도 그리기
            const options = {
                center: new kakao.maps.LatLng(35.204771, 129.166241), // 지도의 중심좌표
                level: 8 // 지도의 확대 레벨
            }

            // 지도 생성
            setMap(new kakao.maps.Map(container, options))
            // console.log(map)

        })

    }, [props.prev, props.selectView]);


    useEffect(() => {

        // if (probs.targetDt === "2023-01-01") {
        //     return;
        // }

        if (!props.data) {
            return;
        }

        // console.log('data', probs.data)

        let suddenAcc = props.data.filter((i) => i.suddenAcc)
        let suddenDrop = props.data.filter((i) => i.suddenDrop)
        let suddenDeparture = props.data.filter((i) => i.suddenDeparture)
        let suddenStop = props.data.filter((i) => i.suddenStop)

        // 마커 이미지
        let imgSuddenAcc = "https://github.com/beeguriri/driving-record-analysis-for-web-service/blob/main/frontend/jupiter_front/src/image/SuddenAcc.png?raw=true";
        let imgSuddenDrop = "https://github.com/beeguriri/driving-record-analysis-for-web-service/blob/main/frontend/jupiter_front/src/image/SuddenDrop.png?raw=true";
        let imgSuddenDeparture = "https://github.com/beeguriri/driving-record-analysis-for-web-service/blob/main/frontend/jupiter_front/src/image/SuddenDeparture.png?raw=true";
        let imgSuddenStop = "https://github.com/beeguriri/driving-record-analysis-for-web-service/blob/main/frontend/jupiter_front/src/image/SuddenStop.png?raw=true";
        let imageSize = new kakao.maps.Size(30, 30);

        // 마커 이미지 생성
        let SuddenAccmarker = new kakao.maps.MarkerImage(imgSuddenAcc, imageSize);
        let SuddenDropmarker = new kakao.maps.MarkerImage(imgSuddenDrop, imageSize);
        let SuddenDeparturemarker = new kakao.maps.MarkerImage(imgSuddenDeparture, imageSize);
        let SuddenStopmarker = new kakao.maps.MarkerImage(imgSuddenStop, imageSize);

        // 마커를 생성
        let Accmarkers = suddenAcc.map((i) => {

            let Accmarker = new kakao.maps.Marker({
                // map: map, // 마커를 표시할 지도
                position: new kakao.maps.LatLng(i.gpsY, i.gpsX), // 마커를 표시할 위치
                image: SuddenAccmarker, // 마커 이미지 
                clickable: true
            })
            // Accmarker.setMap(map)

            let iwContent = '<div class="infowindow">' +
                '    <div class="suddenAcc">급가속</div><div class="suddenAccinfo">' +
                '        <span class="number">차량번호: ' + i.plate + '</span>' +
                '        <span class="number">발생시각: ' + i.hms + '</span>' +
                '        <span class="number">차량속도: ' + i.velocity + ' km/h </span>' +
                '        <span class="number">가속도: ' + Math.round(i.acceleration * 0.27778 * 10) / 10 + ' ㎨ </span>' +
                '        <span class="number">RPM: ' + i.rpm + '</span></div>' +
                '</div>', iwRemovable = true

            let infowindow = new kakao.maps.InfoWindow({
                content: iwContent,
                removable: iwRemovable
            })

            kakao.maps.event.addListener(Accmarker, 'click', function () {
                infowindow.open(map, Accmarker)
            })

            Accmarker.idx = i.id;
            Accmarker.time = i.time;
            Accmarker.title = "급가속";

            return Accmarker;
        });

        let Dropmarkers = suddenDrop.map((i) => {
            let Dropmarker = new kakao.maps.Marker({
                // map: map, // 마커를 표시할 지도
                position: new kakao.maps.LatLng(i.gpsY, i.gpsX), // 마커를 표시할 위치
                image: SuddenDropmarker, // 마커 이미지 
                clickable: true
            })

            let iwContent = '<div class="infowindow">' +
                '    <div class="suddenDrop">급감속</div><div class="suddenDropinfo">' +
                '        <span class="number">차량번호: ' + i.plate + '</span>' +
                '        <span class="number">발생시각: ' + i.hms + '</span>' +
                '        <span class="number">차량속도: ' + i.velocity + ' km/h </span>' +
                '        <span class="number">가속도: ' + Math.round(i.acceleration * 0.27778 * 10) / 10 + ' ㎨ </span>' +
                '        <span class="number">RPM: ' + i.rpm + '</span></div>' +
                '</div>', iwRemovable = true

            let infowindow = new kakao.maps.InfoWindow({
                content: iwContent,
                removable: iwRemovable
            })

            kakao.maps.event.addListener(Dropmarker, 'click', function () {
                infowindow.open(map, Dropmarker)
            })

            Dropmarker.idx = i.id;
            Dropmarker.time = i.time;
            Dropmarker.title = "급감속";

            return Dropmarker;
        });

        let Depmarkers = suddenDeparture.map((i) => {
            let Depmarker = new kakao.maps.Marker({
                // map: map, // 마커를 표시할 지도
                position: new kakao.maps.LatLng(i.gpsY, i.gpsX), // 마커를 표시할 위치
                image: SuddenDeparturemarker, // 마커 이미지 
                clickable: true
            })

            let iwContent = '<div class="infowindow">' +
                '    <div class="suddenDep">급출발</div><div class="suddenDepinfo">' +
                '        <span class="number">차량번호: ' + i.plate + '</span>' +
                '        <span class="number">발생시각: ' + i.hms + '</span>' +
                '        <span class="number">차량속도: ' + i.velocity + ' km/h </span>' +
                '        <span class="number">가속도: ' + Math.round(i.acceleration * 0.27778 * 10) / 10 + ' ㎨ </span>' +
                '        <span class="number">RPM: ' + i.rpm + '</span></div>' +
                '</div>', iwRemovable = true

            let infowindow = new kakao.maps.InfoWindow({
                content: iwContent,
                removable: iwRemovable
            })

            kakao.maps.event.addListener(Depmarker, 'click', function () {
                infowindow.open(map, Depmarker)
            })

            Depmarker.idx = i.id;
            Depmarker.time = i.time;
            Depmarker.title = "급출발";

            return Depmarker;
        });

        let Stopmarkers = suddenStop.map((i) => {

            let Stopmarker = new kakao.maps.Marker({
                // map: map, // 마커를 표시할 지도
                position: new kakao.maps.LatLng(i.gpsY, i.gpsX), // 마커를 표시할 위치
                image: SuddenStopmarker, // 마커 이미지 
                clickable: true
            })

            let iwContent = '<div class="infowindow">' +
                '    <div class="suddenStop">급정지</div><div class="suddenStopinfo">' +
                '        <span class="number">차량번호: ' + i.plate + '</span>' +
                '        <span class="number">발생시각: ' + i.hms + '</span>' +
                '        <span class="number">차량속도: ' + i.velocity + ' km/h </span>' +
                '        <span class="number">가속도: ' + Math.round(i.acceleration * 0.27778 * 10) / 10 + ' ㎨ </span>' +
                '        <span class="number">RPM: ' + i.rpm + '</span></div>' +
                '</div>', iwRemovable = true

            let infowindow = new kakao.maps.InfoWindow({
                content: iwContent,
                removable: iwRemovable
            })

            kakao.maps.event.addListener(Stopmarker, 'click', function () {
                infowindow.open(map, Stopmarker)
            })

            Stopmarker.idx = i.id;
            Stopmarker.time = i.time;
            Stopmarker.title = "급정지";

            return Stopmarker;

        });

        // console.log(Stopmarkers)

        // 마커 클러스터러 생성
        let clusterer = new kakao.maps.MarkerClusterer({
            map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
            averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
            minLevel: 5, // 클러스터 할 최소 지도 레벨 
            disableClickZoom: true
        })

        clusterer.addMarkers(Accmarkers)
        clusterer.addMarkers(Dropmarkers)
        clusterer.addMarkers(Depmarkers)
        clusterer.addMarkers(Stopmarkers)

        // console.log('Stopmarker: ', Stopmarkers[3].idx)
        // 마커 클러스터러에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(clusterer, 'clusterclick', function (cluster) {
            // console.log(cluster.getMarkers());
            let marker = cluster.getMarkers().map((marker) => {

                let temp = {}
                temp['idx'] = marker.idx
                temp['위험'] = marker.title
                temp['시간'] = marker.time

                return temp
            })

            // forEach(marker => {
            //     console.log(marker.idx+ ':' + marker.time + ':' + marker.title)
            //     temp.append(marker)
            // })
            props.setChartData(marker)
        });

    }, [props.next])


    return (

        <div id="map" className="map">
        </div>

    );

}

export default DrawingMap;