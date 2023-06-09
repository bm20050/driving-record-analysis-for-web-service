import pandas as pd
from datetime import datetime
from flask import jsonify

def txt_to_csv(file_list):
    df_list = []
    for file in file_list:
        temp_df = pd.read_fwf('../files/' + file + '.TXT', encoding='cp949', skiprows=1, header=None)  # 파일 읽기
        temp_df.columns = ['data']
        df_list.append(temp_df)

    df = pd.concat(df_list, axis=0, ignore_index=True)
    return df


def slice_data(temp_data):
    lst3 = []
    lst4 = []
    lst5 = []
    lst6 = []
    lst7 = []
    lst8 = []
    lst9 = []
    lst10 = []
    lst11 = []
    lst12 = []
    lst13 = []

    for i in temp_data.index:
        s = temp_data['data'][i]
        lst3.append(s[:4])
        lst4.append(s[4:11])
        lst5.append(s[11:25])
        lst6.append(int(s[25:28]))
        lst7.append(s[28:32])
        lst8.append(s[32:33])
        lst9.append(float(s[33:42]))
        lst10.append(float(s[42:51]))
        lst11.append(s[51:54])
        lst12.append(s[54:60])
        lst13.append(s[60:66])

    # filename = datetime.today().strftime('%Y%m%d') + '_slice.csv'
    df = pd.DataFrame({'일일주행거리': lst3, '누적주행거리': lst4, '정보발생일시': lst5, '차량속도': lst6, 'RPM': lst7,
                       '브레이크': lst8, 'GPS_X': lst9, 'GPS_Y': lst10, '방위각': lst11, '가속도_X': lst12, '가속도_Y': lst13})
    # df.to_csv('../data/' + filename, index=False)
    return df


def count_abnormal_driving(df):
    l = []

    for i in df.index:
        if i == 0:
            l.append(0)
            continue

        l.append(df['차량속도'][i] - df['차량속도'][i - 1])
    df['가속도'] = l

    l1 = []
    l2 = []
    l3 = []
    l4 = []

    for i in df.index:
        if i == 0:
            l1.append(0)
            l2.append(0)
            l3.append(0)
            l4.append(0)
            continue

        # 급출발
        if 5 >= df['차량속도'][i - 1] and df['차량속도'][i] - df['차량속도'][i - 1] >= 8:
            l2.append(1)
            l1.append(0)
            l3.append(0)
            l4.append(0)

        # 급가속
        elif 6 <= df['차량속도'][i] <= 10 and df['차량속도'][i] - df['차량속도'][i - 1] >= 8:
            l1.append(1)
            l2.append(0)
            l3.append(0)
            l4.append(0)
        elif 10 < df['차량속도'][i] <= 20 and df['차량속도'][i] - df['차량속도'][i - 1] >= 7:
            l1.append(1)
            l2.append(0)
            l3.append(0)
            l4.append(0)
        elif 20 < df['차량속도'][i] and df['차량속도'][i] - df['차량속도'][i - 1] >= 6:
            l1.append(1)
            l2.append(0)
            l3.append(0)
            l4.append(0)

        # 급정지
        elif df['차량속도'][i - 1] - df['차량속도'][i] >= 9 and df['차량속도'][i] <= 5 and df['브레이크'][i] == 1:
            l4.append(1)
            l1.append(0)
            l2.append(0)
            l3.append(0)

        # 급감속
        elif 30 >= df['차량속도'][i] and df['차량속도'][i - 1] - df['차량속도'][i] >= 9 and df['차량속도'][i] >= 6 and df['브레이크'][
            i] == 1:
            l3.append(1)
            l1.append(0)
            l2.append(0)
            l4.append(0)
        elif 50 >= df['차량속도'][i] and df['차량속도'][i - 1] - df['차량속도'][i] >= 10 and df['차량속도'][i] >= 6 and df['브레이크'][
            i] == 1:
            l3.append(1)
            l1.append(0)
            l2.append(0)
            l4.append(0)
        elif 50 < df['차량속도'][i] and df['차량속도'][i - 1] - df['차량속도'][i] >= 12 and df['차량속도'][i] >= 6 and df['브레이크'][
            i] == 1:
            l3.append(1)
            l1.append(0)
            l2.append(0)
            l4.append(0)

        # 정상주행
        else:
            l1.append(0)
            l2.append(0)
            l3.append(0)
            l4.append(0)

    df['급가속'] = l1
    df['급출발'] = l2
    df['급감속'] = l3
    df['급정지'] = l4

    return df


def set_data(df):
    df['년'] = df['정보발생일시'].apply(lambda x: int(str(x)[0:2]))
    df['월'] = df['정보발생일시'].apply(lambda x: int(str(x)[2:4]))
    df['일'] = df['정보발생일시'].apply(lambda x: int(str(x)[4:6]))
    df['시'] = df['정보발생일시'].apply(lambda x: int(str(x)[6:8]))
    df['분'] = df['정보발생일시'].apply(lambda x: int(str(x)[8:10]))
    df['초'] = df['정보발생일시'].apply(lambda x: int(str(x)[10:12]))
    df['시분초'] = df['정보발생일시'].apply(lambda x: str(x)[6:8] + ':' + str(x)[8:10] + ':' + str(x)[10:12])

    df['GPS_X'] = df['GPS_X'] / 1e6
    df['GPS_Y'] = df['GPS_Y'] / 1e6
    df = df.query('급가속 == 1 or 급출발 == 1 or 급감속 == 1 or 급정지 == 1')
    return df

def file_processing(file_list):
    df = txt_to_csv(file_list)
    df = slice_data(df)
    df = count_abnormal_driving(df)
    df = set_data(df)

    return df


if __name__ == '__main__':
    file_list = ['1', '2', '3', '4', '5']
    to_spring = file_processing(file_list)
    to_spring.to_csv('../files/temp.csv', index=False)
    print(to_spring)
