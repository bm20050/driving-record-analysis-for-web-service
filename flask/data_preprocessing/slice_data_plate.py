import pandas as pd

lst1 = []
lst2 = []
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

def slice_data_plate():
    temp_data = pd.read_csv('../../../dataset/raw.csv')

    for i in temp_data.index:
        if temp_data['plate'][i] == '부산70자1894':
            s = temp_data['data'][i]
            if s[11:17] == '221201':
                lst1.append(temp_data['date'][i])
                lst2.append(temp_data['plate'][i])
                lst3.append(s[:4])
                lst4.append(s[4:11])
                lst5.append(s[11:25])
                lst6.append(s[25:28])
                lst7.append(s[28:32])
                lst8.append(s[32:33])
                lst9.append(s[33:42])
                lst10.append(s[42:51])
                lst11.append(s[51:54])
                lst12.append(s[54:60])
                lst13.append(s[60:66])


    # temp_data = temp_data.loc[:, ['date', 'plate', '일일주행거리', '누적주행거리', '정보발생일시', '차량속도', 'RPM', '브레이크', 'GPS_X', 'GPS_Y', '방위각', '가속도_X', '가속도_Y']]
    df = pd.DataFrame({'날짜': lst1, '번호판': lst2, '일일주행거리': lst3, '누적주행거리': lst4, '정보발생일시': lst5, '차량속도': lst6, 'RPM': lst7, '브레이크': lst8, 'GPS_X': lst9, 'GPS_Y': lst10, '방위각': lst11, '가속도_X': lst12, '가속도_Y': lst13})
    df.to_csv('./dataset/slice_data_1894.csv', index=False)

if __name__ == '__main__':
    slice_data_plate()