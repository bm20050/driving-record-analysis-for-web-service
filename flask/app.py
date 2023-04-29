from flask import Flask, jsonify
from http import HTTPStatus
from flask_cors import CORS

app = Flask(__name__)

CORS(app)

data = {
    'data1': {
        'age': 14,
        'name': 'hello'
    },
    'data2': {
        'age': 20,
        'name': 'hi'
    }
}

@app.route('/get', methods=['GET'])
def get():
    return jsonify({"data": data, "status": HTTPStatus.OK})


if __name__ == '__main__':
    app.run(port=5000, debug=True)
