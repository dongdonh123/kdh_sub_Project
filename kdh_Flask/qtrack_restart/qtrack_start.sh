#!/bin/bash

# qtrack 프로세스의 첫 번째 PID 찾기
PID=$(ps -ef | grep tomcat | grep -v grep | awk 'NR==1 {print $2}')

# PID가 존재하는지 확인
if [ -n "$PID" ]; then
    echo "qtrack 프로세스 (PID: $PID)가 존재해서 구동 명령 할 수 없습니다."
else
    # tomcat start 쉘 실행 
    sh /home/kdh/apache-tomcat-9.0.91/bin/startup.sh
    echo "qtrack서버를 구동했습니다."
    echo "구동완료까지 3분 소요됩니다."
fi

