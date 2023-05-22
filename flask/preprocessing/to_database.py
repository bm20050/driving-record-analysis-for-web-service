import sqlalchemy
from sqlalchemy import create_engine
import pandas as pd

def to_database():
    db_connection_str = "mysql+pymysql://root:1234@localhost/jupiterdb"
    db_connection = create_engine(db_connection_str)
    conn = db_connection.connect()
    df_data = pd.read_csv('../data/to_db.csv')
    df_data['차량번호'] = df_data['번호판']
    df_data = df_data.drop(['번호판', '날짜', '누적주행거리', '정보발생일시', '차량속도', 'RPM', '브레이크', '방위각', '가속도_X', '가속도_Y', 'seq'], axis=1)
    dtypesql = {
                # 'seq': sqlalchemy.types.BIGINT,
                '차량번호': sqlalchemy.types.VARCHAR(45),
                # '번호판': sqlalchemy.types.VARCHAR(45),
                '년': sqlalchemy.types.VARCHAR(2),
                '월': sqlalchemy.types.VARCHAR(2),
                '일': sqlalchemy.types.VARCHAR(2),
                '시': sqlalchemy.types.VARCHAR(2),
                '일일주행거리': sqlalchemy.types.BIGINT,
                '급가속': sqlalchemy.types.BIGINT,
                '급출발': sqlalchemy.types.BIGINT,
                '급감속': sqlalchemy.types.BIGINT,
                '급정지': sqlalchemy.types.BIGINT,
                # '누적주행거리': sqlalchemy.types.VARCHAR(45),
                # '정보발생일시': sqlalchemy.types.VARCHAR(45),
                # '차량속도': sqlalchemy.types.VARCHAR(45),
                # 'RPM': sqlalchemy.types.VARCHAR(45),
                # '브레이크': sqlalchemy.types.VARCHAR(45),
                'GPS_X': sqlalchemy.types.VARCHAR(45),
                'GPS_Y': sqlalchemy.types.VARCHAR(45),
                # '방위각': sqlalchemy.types.VARCHAR(45),
                # '가속도_X': sqlalchemy.types.VARCHAR(45),
                # '가속도_Y': sqlalchemy.types.VARCHAR(45),
                }

    df_data.to_sql(name='driving', con=db_connection, if_exists='append', index=False, dtype=dtypesql)


if __name__ == '__main__':
    to_database()
