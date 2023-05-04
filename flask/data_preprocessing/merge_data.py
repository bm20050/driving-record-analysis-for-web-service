import pandas as pd
from glob import glob
files = glob('./dataset/**/**/*')
df_list = []
for file in files:
    file_st = file.split('\\')[-1].split('-') # 파일명 읽기
    if file_st[2] == 'C':
        continue
    date = file_st[0]
    plate = file_st[1]
    temp_df = pd.read_fwf(file, encoding='cp949', skiprows=1, header=None) # 파일 읽기
    temp_df[date] = date
    temp_df[plate] = plate
    temp_df.columns = ['data', 'date', 'plate']
    df_list.append(temp_df)


df = pd.concat(df_list, axis=0, ignore_index=True)
df.to_csv('./dataset/raw.csv', index=False)