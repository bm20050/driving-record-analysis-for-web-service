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
- DE : `http://localhost:5000/api/prediction`
   - `POST` 만 사용
- BE : `http://localhost:8080/...`
   - API 명세 (작성예정)
- FE : `http://localhost:3000`
   - `Routers` 내부 링크
