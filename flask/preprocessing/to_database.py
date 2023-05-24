import sqlalchemy
from sqlalchemy import create_engine
import pandas as pd


def to_database():
    db_connection_str = "mysql+pymysql://root:1234@localhost/jupiterdb"
    db_connection = create_engine(db_connection_str)
    conn = db_connection.connect()
    df_data = pd.read_csv('../data/0524_to_db.csv')
    print(df_data['일'])
    # dtypesql = {
    #     '차량번호': sqlalchemy.types.VARCHAR(45),
    #     '년': sqlalchemy.types.VARCHAR(2),
    #     '월': sqlalchemy.types.VARCHAR(2),
    #     '일': sqlalchemy.types.VARCHAR(2),
    #     '시': sqlalchemy.types.VARCHAR(2),
    #     '시분초': sqlalchemy.types.VARCHAR(10),
    #     '차량속도': sqlalchemy.types.BIGINT,
    #     '가속도': sqlalchemy.types.BIGINT,
    #     'RPM': sqlalchemy.types.BIGINT,
    #     '급가속': sqlalchemy.types.BIGINT,
    #     '급출발': sqlalchemy.types.BIGINT,
    #     '급감속': sqlalchemy.types.BIGINT,
    #     '급정지': sqlalchemy.types.BIGINT,
    #     'GPS_X': sqlalchemy.types.VARCHAR(45),
    #     'GPS_Y': sqlalchemy.types.VARCHAR(45),
    # }
    #
    # df_data.to_sql(name='driving', con=db_connection, if_exists='append', index=False, dtype=dtypesql)

if __name__ == '__main__':
    to_database()
