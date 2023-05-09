import sqlalchemy
from sqlalchemy import create_engine
import pandas as pd

def to_database():
    db_connection_str = "mysql+pymysql://root:1234@localhost/jupiterdb"
    db_connection = create_engine(db_connection_str)
    conn = db_connection.connect()
    df_data = pd.read_csv('../data/slice_data_1854.csv')
    dtypesql = {'날짜': sqlalchemy.types.VARCHAR(45),
                '번호판': sqlalchemy.types.VARCHAR(45),
                '일일주행거리': sqlalchemy.types.VARCHAR(45),
                '누적주행거리': sqlalchemy.types.VARCHAR(45),
                '정보발생일시': sqlalchemy.types.VARCHAR(45),
                '차량속도': sqlalchemy.types.VARCHAR(45),
                'RPM': sqlalchemy.types.VARCHAR(45),
                '브레이크': sqlalchemy.types.VARCHAR(45),
                'GPS_X': sqlalchemy.types.VARCHAR(45),
                'GPS_Y': sqlalchemy.types.VARCHAR(45),
                '방위각': sqlalchemy.types.VARCHAR(45),
                '가속도_X': sqlalchemy.types.VARCHAR(45),
                '가속도_Y': sqlalchemy.types.VARCHAR(45),
                }

    df_data.to_sql(name='raw_test', con=db_connection, if_exists='append', index=False, dtype=dtypesql)


if __name__ == '__main__':
    to_database()
