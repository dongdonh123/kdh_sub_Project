from flask import Flask, render_template, request, jsonify
import subprocess

app = Flask(__name__)

# 허용된 명령어 리스트
ALLOWED_COMMANDS = ['sh /home/kdh/my_flask_app/qtrack_kill.sh','ps -ef | grep tomcat','sh /home/kdh/my_flask_app/qtrack_start.sh']  # 필요에 따라 추가

# 홈 페이지
@app.route('/')
def home():
    return render_template('index.html')

# 쉘 명령어 실행
@app.route('/run-status-command', methods=['POST'])
def run_status_command():
    if request.method == 'POST':
        command = request.form.get('command')
        if command and command in ALLOWED_COMMANDS:
            try:
                result = subprocess.check_output(command, shell=True, stderr=subprocess.STDOUT)
                return jsonify({'status': 'success', 'output2': result.decode('utf-8')})
            except subprocess.CalledProcessError as e:
                return jsonify({'status': 'error', 'output2': e.output.decode('utf-8')})
        else:
            return jsonify({'status': 'error', 'output2': 'Command not allowed'})
    return jsonify({'status': 'error', 'output2': 'Invalid command'})

# 쉘 명령어 실행
@app.route('/run-kill-command', methods=['POST'])
def run_kill_command():
    if request.method == 'POST':
        command = request.form.get('command')
        if command and command in ALLOWED_COMMANDS:
            try:
                result = subprocess.check_output(command, shell=True, stderr=subprocess.STDOUT)
                return jsonify({'status': 'success', 'output': result.decode('utf-8')})
            except subprocess.CalledProcessError as e:
                return jsonify({'status': 'error', 'output': e.output.decode('utf-8')})
        else:
            return jsonify({'status': 'error', 'output': 'Command not allowed'})
    return jsonify({'status': 'error', 'output': 'Invalid command'})

# 쉘 명령어 실행
@app.route('/run-start-command', methods=['POST'])
def run_start_command():
    if request.method == 'POST':
        command = request.form.get('command')
        if command and command in ALLOWED_COMMANDS:
            try:
                result = subprocess.check_output(command, shell=True, stderr=subprocess.STDOUT)
                return jsonify({'status': 'success', 'output3': result.decode('utf-8')})
            except subprocess.CalledProcessError as e:
                return jsonify({'status': 'error', 'output3': e.output.decode('utf-8')})
        else:
            return jsonify({'status': 'error', 'output3': 'Command not allowed'})
    return jsonify({'status': 'error', 'output3': 'Invalid command'})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
