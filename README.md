# 버스 운행기록 데이터를 이용한 빅데이터/AI 분석 웹 서비스

> 버스 운행기록 데이터를 이용하여 도로 상황 분석 및 예측 하는 프로젝트 입니다.

## 구조
- `DA` : 데이터 분석 관련
- `BE` : API 관련
- `FE` : 사용자 입력 및 시각화 관련

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
