# Data Analysis

```shell
$ python -venv venv
$ .\venv\Scripts\activate
$ (venv) pip install -r .\requirements.txt
```

## Flask 실행 - Spring Boot와 연결
> app.py

## 데이터 합치기
> ./preprocessing/merge_data.py
- txt 파일 합쳐서 raw.csv 생성

## 데이터 각 항목별로 분류
> ./preprocessing/slice_data.py
#### 차량별로 재분류
> ./preprocessing/slice_data_plate.py
* 0000 - 일일주행거리(0000~9999 km)
* 0066443 - 누적주행거리(0000000~9999999 km)
* 22120105071600 - 정보발생일시(YYMMDDhhmmssss)
* 000 - 차량속도(000~255 km/h)
* 0388 - rpm(0000~9999)
* 0 - 브레이크(0:off, 1:on)
* 129158670 - GPS:X(/1000000)
* 035236021 - GPS:Y(/1000000)
* 000 - 방위각(0~360)
* -007.9 - 가속도X(-100.0~+100.0 m/s**2)
* +001.600 - 가속도Y(-100.0~+100.0 m/s**2)

## 차량별 노선 시각화
> ./notebooks/scatter.map.ipynb

## 시간별 평균속도 추출
> ./notebooks/data_extract.ipynb

## 데이터 데이터베이스에 넣기
> ./preprocessing/to_database.py
