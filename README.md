# 사업용 차량 안전 위험 빅데이터/AI 분석 웹 서비스

> 버스 운행기록 데이터를 이용한 빅데이터/AI 분석 웹 서비스
- 본 프로젝트는 DTG를 통해 사업용 차량으로부터 수집되는 주요 정보 중 속도, 위치 등의 데이터를 기반으로 웹서비스를 제공한다.
- 차량 운전자의 위험운전행동 발생구간, 시간대를 지도, 차트를 이용하여 시각적으로 보여준다.
- 운전자의 급감가속 등의 위험운전행동 데이터를 이용하여 위험운전행동 건수 자료를 제공하고 이를 바탕으로 교육 및 안전습관의 개선에 기여하고자 한다.
- 또한 머신러닝을 이용한 시공간에 대한 위험운전 정도 예측결과를 알려줌으로써 해당 구간 운전 시 경각심을 가질 수 있도록 한다.

## 구조
- `DA` : 데이터 분석
- `BE` : API 및 DB 설계, Flask-Springboot-React간 파이프라인 구축
- `FE` : 사용자 입력 및 시각화

| **React**                                    | **Spring Boot**                                              | **Flask**                                                    |
| -------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| - 날짜,버스,위험분류에 따른 검색결과 요청      | - DB에서 쿼리를 통해 요청결과 응답                             |                                                              |
| (로그인 정보 필수)<br /> - 운행기록 파일 업로드 | - MultipartFile 객체로 User정보와 업로드 한 파일 정보를 받고 <br />- DB에 요청 내용 및 파일 리스트 입력 후 파일 목록 Flask로 전송 <br />- Flask 응답결과 React로 전송 | - 요청파일목록으로 실물파일을 읽어서<br /> - 데이터 전처리, 위험운전 분류 후 <br />결과 응답 |
| (로그인정보 필수)<br /> - 날짜와 위치정보 전송  | - 리액트에서 받은 요청을 Flask로 전송 <br />- Flask 응답결과 React로 전송 | - 예측모델링을 통한 위험운전 결과 응답                         |
## 실행
- DE
```bash
$ flask run
```

- BE
```bash
$ mvn spring-boot:run
```

- FE
   - 실행 전 설치
```bash
$ npm install axios
$ npm install apexcharts
```
   - 실행
```bash
$ npm start
```

## 세부내용
#### ⭐ [DE](https://github.com/bm20050/driving-record-analysis-for-web-service/tree/main/flask) : `http://localhost:5000`
- App.py

| **No** | **Method** | **URI**        | **Description**                              |
| ------ | ---------- | -------------- | -------------------------------------------- |
| 1      | **POST**   | /file_processing | 업로드한 파일의 전처리 및 위험운전 분류 결과 반환            |
| 2      | **POST**   | /prediction | 시간, 위치정보에 대한 위험운전 예측결과 반환 |

#### ⭐ [BE](https://github.com/bm20050/driving-record-analysis-for-web-service/tree/main/springboot) : `http://localhost:8080/`
- [API 상세 명세](https://github.com/bm20050/driving-record-analysis-for-web-service/blob/main/springboot/API%EB%AA%85%EC%84%B8_v0.1_20230619.pdf)
- drivingController

| **No** | **Method** | **URI**        | **Description**                              |
| ------ | ---------- | -------------- | -------------------------------------------- |
| 1      | **POST**   | api/totalCount | 위험운전 카테고리별 조회결과 반환            |
| 2      | **POST**   | api/prediction | 시간, 위치정보 파라미터 전달 후응답결과 반환 |

- uploadController

| **No** | **Method** | **URI**         | **Description**                                              |
| ------ | ---------- | --------------- | ------------------------------------------------------------ |
| 1      | **POST**   | api/uploadFiles | 업로드파일에 대한 리스트 플라스크로 전달하여 전처리 및 위험운전 분류 응답결과 반환 |

- memberController

| **No** | **Method** | **URI**          | **Description**                 |
| ------ | ---------- | ---------------- | ------------------------------- |
| 1      | **POST**   | api/user/getuser | 현재 로그인 된 유저의 정보 조회 |
| 2      | **POST**   | api/user/join    | 회원가입                        |
| 3      | **POST**   | api/user/login   | 로그인                          |
| 4      | **POST**   | api/user/logout  | 로그아웃                        |
| 5      | **PUT**    | api/user/update  | 회원정보 수정                   |

#### ⭐ [FE](https://github.com/bm20050/driving-record-analysis-for-web-service/tree/main/frontend/jupiter_front) : `http://localhost:3000`
- 리액트 라우터
```javascript
<BrowserRouter>
  <div className="header">
      <Header />
  </div>
  <div className="main">
      <RouteMain />
  </div>
</BrowserRouter>
```
```javascript                   
<Routes>
    <Route path="/" element={<Main />} />
    <Route path="/login" element={<Login />} />
    <Route path="/join" element={<Join />}/>
    <Route path="/myPage" element={<MyPage />} />
    <Route path="/prediction" element={<Prediction />}/>
</Routes>
```
