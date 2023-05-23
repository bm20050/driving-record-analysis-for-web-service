from flask import Flask, jsonify
from http import HTTPStatus
from flask_cors import CORS
import pandas as pd
app = Flask(__name__)

CORS(app)

def getCsv():
    df = pd.read_csv('./data/1차배포용.csv')
    print(df)
    return df

# @app.route('/get', methods=['GET'])
@app.route('/prediction', methods=['GET'])
def prediction():
    f = getCsv().to_json()
    return f
    # return jsonify({"test2": test2['data1']})
# , "status": HTTPStatus.OK

if __name__ == '__main__':
    app.run(port=5000, debug=False)

