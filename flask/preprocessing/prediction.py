import joblib
import numpy as np
import pandas as pd

pacc = []
pdrop = []
prot = []
danger = []
def prediction(input_data):
    model_acc = joblib.load(open('../model/model_acc.pkl', 'rb'))
    model_drop = joblib.load(open('../model/model_drop.pkl', 'rb'))
    model_rotation = joblib.load(open('../model/model_rotation.pkl', 'rb'))

    X = np.array([input_data])
    pred_acc = model_acc.predict(X)[0]
    pred_drop = model_drop.predict(X)[0]
    pred_rotation = model_rotation.predict(X)[0]

    pred = pred_acc * 0.4 + pred_drop * 0.5 + pred_rotation * 0.1


    pacc.append(pred_acc)
    pdrop.append(pred_drop)
    prot.append(pred_rotation)


    if pred == 0:
        result = '안전'
        danger.append(result)
    elif pred < 1:
        result = '주의'
        danger.append(result)
    elif pred < 2:
        result = '위험'
        danger.append(result)
    else:
        result = '매우 위험'
        danger.append(result)


    return result

if __name__ == '__main__':

    df = pd.read_csv('../data/0524_to_db.csv')
    for i in df.index[990000:1000000]:
        if i % 100 == 0:
            print(i)
        prediction([int(df['시'][i]), int(df['시분초'][i][3:5]), float(df['GPS_X'][i]), float(df['GPS_Y'][i])])

    for i in range(4):
        print("acc", i, pacc.count(i))
        print("drop", i, pdrop.count(i))
        print("rotation", i, prot.count(i))

    print(danger.count('안전'))
    print(danger.count('주의'))
    print(danger.count('위험'))
    print(danger.count('매우 위험'))
