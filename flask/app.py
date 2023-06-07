from flask import Flask, request, jsonify, render_template, request, url_for

from http import HTTPStatus
from flask_cors import CORS
import pandas as pd
app = Flask(__name__)

CORS(app)

# def getCsv():
#     df = pd.read_csv('./data/1차배포용.csv')
#     print(df)
#     return df

# @app.route('/get', methods=['GET'])
# @app.route('/prediction', methods=['GET'])
# def prediction():
#     f = getCsv().to_json()
#     return f
#     # return jsonify({"test2": test2['data1']})
# # , "status": HTTPStatus.OK

# 파일업로드 테스트중
@app.route('/test', methods=['POST'])
def test():
    # data = request.get_json()
    print("data", request.data)
    print("type", type(request.data))
    print("json", request.get_json())
    print("json", type(request.get_json()['data']))
    # print("len", len(request.data))

    return "hello"


if __name__ == '__main__':
    app.run(port=5000, debug=False)

