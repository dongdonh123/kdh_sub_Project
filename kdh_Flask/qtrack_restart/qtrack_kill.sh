#!/bin/bash

# qtrack 프로세스의 첫 번째 PID 찾기
PID=$(ps -ef | grep tomcat | grep -v grep | awk 'NR==1 {print $2}')

# PID가 존재하는지 확인
if [ -n "$PID" ]; then
    # 프로세스 종료
    kill "$PID"
    echo "qtrack 프로세스 (PID: $PID)가 종료되었습니다."
else
    echo "qtrack 프로세스를 찾을 수 없습니다."
fi
