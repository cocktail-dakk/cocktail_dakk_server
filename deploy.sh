#!/usr/bin/env bash

REPOSITORY=/home/ubuntu/app/cocktaildakk/build/libs
cd $REPOSITORY

APP_NAME=cocktail_dakk
JAR_NAME=$(ls $REPOSITORY/*.jar | tail -n 1)

CURRENT_PID=$(pgrep -f $APP_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 종료할것 없음."
else
  echo "> kill -9 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi



echo "> $JAR_NAME 배포"
nohup java -jar \ -Dspring.config.location=classpath:/application.properties,/home/ubuntu/app/application-real-db.properties \  $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
