import pandas as pd


def txt_to_df(lst):
    df_list = []
    for file in lst:
        file_path = './files/' + str(file) + '.TXT'
        temp_df = pd.read_fwf(file_path, encoding='cp949', skiprows=1, header=None)  # 파일 읽기
        temp_df.columns = ['data']
        df_list.append(temp_df)

    df = pd.concat(df_list, axis=0, ignore_index=True)

    return df


def slice_data(temp_data):
    lst1 = []
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
        lst1.append(i)
        lst3.append(s[:4])
        lst4.append(s[4:11])
        lst5.append(s[11:25])
        lst6.append(int(s[25:28]))
        lst7.append(s[28:32])
        lst8.append(int(s[32:33]))
        lst9.append(float(s[33:42]))
        lst10.append(float(s[42:51]))
        lst11.append(s[51:54])
        lst12.append(s[54:60])
        lst13.append(s[60:66])

    # filename = datetime.today().strftime('%Y%m%d') + '_slice.csv'
    df = pd.DataFrame({'id': lst1, '일일주행거리': lst3, '누적주행거리': lst4, '정보발생일시': lst5, 'velocity': lst6, 'rpm': lst7,
                       '브레이크': lst8, 'gpsX': lst9, 'gpsY': lst10, '방위각': lst11, '가속도_X': lst12, '가속도_Y': lst13})
    # df.to_csv('../data/' + filename, index=False)
    return df


def count_abnormal_driving(df):
    l = []

    for i in df.index:
        if i == 0:
            l.append(0)
            continue

        l.append(df['velocity'][i] - df['velocity'][i - 1])
    df['acceleration'] = l

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
        if 5 >= df['velocity'][i - 1] and df['velocity'][i] - df['velocity'][i - 1] >= 8:
            l2.append(1)
            l1.append(0)
            l3.append(0)
            l4.append(0)

        # 급가속
        elif 6 <= df['velocity'][i] <= 10 and df['velocity'][i] - df['velocity'][i - 1] >= 8:
            l1.append(1)
            l2.append(0)
            l3.append(0)
            l4.append(0)
        elif 10 < df['velocity'][i] <= 20 and df['velocity'][i] - df['velocity'][i - 1] >= 7:
            l1.append(1)
            l2.append(0)
            l3.append(0)
            l4.append(0)
        elif 20 < df['velocity'][i] and df['velocity'][i] - df['velocity'][i - 1] >= 6:
            l1.append(1)
            l2.append(0)
            l3.append(0)
            l4.append(0)

        # 급정지
        elif df['velocity'][i - 1] - df['velocity'][i] >= 9 and df['velocity'][i] <= 5 and df['브레이크'][i] == 1:
            l4.append(1)
            l1.append(0)
            l2.append(0)
            l3.append(0)

        # 급감속
        elif 30 >= df['velocity'][i] and df['velocity'][i - 1] - df['velocity'][i] >= 9 and df['velocity'][i] >= 6 and df['브레이크'][i] == 1:
            l3.append(1)
            l1.append(0)
            l2.append(0)
            l4.append(0)
        elif 50 >= df['velocity'][i] and df['velocity'][i - 1] - df['velocity'][i] >= 10 and df['velocity'][i] >= 6 and df['브레이크'][i] == 1:
            l3.append(1)
            l1.append(0)
            l2.append(0)
            l4.append(0)
        elif 50 < df['velocity'][i] and df['velocity'][i - 1] - df['velocity'][i] >= 12 and df['velocity'][i] >= 6 and df['브레이크'][i] == 1:
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

    df['suddenAcc'] = l1
    df['suddenDeparture'] = l2
    df['suddenDrop'] = l3
    df['suddenStop'] = l4

    return df


def set_data(df):
    df['year'] = df['정보발생일시'].apply(lambda x: str(x)[0:2])
    df['month'] = df['정보발생일시'].apply(lambda x: str(x)[2:4])
    df['day'] = df['정보발생일시'].apply(lambda x: str(x)[4:6])
    df['time'] = df['정보발생일시'].apply(lambda x: str(int(str(x)[6:8])))
    df['분'] = df['정보발생일시'].apply(lambda x: int(str(x)[8:10]))
    df['초'] = df['정보발생일시'].apply(lambda x: int(str(x)[10:12]))
    df['hms'] = df['정보발생일시'].apply(lambda x: str(x)[6:8] + ':' + str(x)[8:10] + ':' + str(x)[10:12])

    df['gpsX'] = df['gpsX'].apply(lambda x: str(x/1e6))
    df['gpsY'] = df['gpsY'].apply(lambda x: str(x/1e6))

    print(df['suddenAcc'].tolist().count(1))
    print(df['suddenDeparture'].tolist().count(1))
    print(df['suddenDrop'].tolist().count(1))
    print(df['suddenStop'].tolist().count(1))

    df = df.query('suddenAcc == 1 or suddenDeparture == 1 or suddenDrop == 1 or suddenStop == 1')
    df = df.drop(['일일주행거리', '누적주행거리', '정보발생일시', '브레이크',
                  '방위각', '가속도_X', '가속도_Y', '분', '초'], axis=1)
    df['plate'] = ['개인' for _ in range(len(df.index))]

    return df


def file_processing(file_list):
    df = txt_to_df(file_list)
    df = slice_data(df)
    df = count_abnormal_driving(df)
    df = set_data(df)
    print(len(df.index))
    return df


if __name__ == '__main__':
    file_list = ['16862827315376797', '16862827315337495']
    to_spring = file_processing(file_list)
    to_spring.to_csv('../files/temp.csv', index=False)
    print(to_spring)
