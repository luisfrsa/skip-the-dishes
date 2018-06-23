#!/bin/bash
folders=("exchange" "transaction")
dockerfolder=../docker
for folder in "${folders[@]}"
do
    rm -rf ./$dockerfolder/targets/target_$folder
    mvn clean package -f ./../$folder/ -DskipTests
    cp -r ./../$folder/target ./$dockerfolder/targets/target_$folder
done

docker-compose -f ./$dockerfolder/docker-compose.yml up

#docker-compose up --build -d
# mvn spring-boot:run -Dspring-boot.run.profiles=8001