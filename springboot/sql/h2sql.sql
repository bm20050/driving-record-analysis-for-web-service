insert into driving (차량속도,RPM,GPS_X,GPS_Y,년,월,일,시,시분초,가속도,급가속,급출발,급감속,급정지,차량번호)
select * from csvread('D:\Coding Practice\driving-record-analysis-for-web-service\flask\data\0524_to_db.csv')
where 년=22 and 월=12 and 일 in (1,2,3,4,5,6,7,8,9,10)