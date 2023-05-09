from flask import Flask, jsonify
from http import HTTPStatus
from flask_cors import CORS

app = Flask(__name__)

CORS(app)

test = [
    {
        'seq': -1,
        'age': 14,
        'userName': 'hello'
    },
    {
        'seq': -1,
        'age': 20,
        'userName': 'hi'
    }
]

# @app.route('/get', methods=['GET'])
@app.route('/get', methods=['GET'])
def get():
    return test
    # return jsonify({"test2": test2['data1']})
# , "status": HTTPStatus.OK

if __name__ == '__main__':
    app.run(port=5000, debug=True)

