#!/bin/bash
eureka_port=(8761)
exchange_port=(8100)
transaction_port=(8000 8001 8002 8003)
current_dir=$PWD

docker-compose -f ../docker/docker-compose.yml up --build -d 


folder=eureka
mvn clean package -f ./../$folder/ -DskipTests
for port in "${eureka_port[@]}"
do 
    gnome-terminal --title="$folder PORT: $port" --working-directory=WORK_DIR -x bash -c "java -jar -Dserver.port=$port $current_dir/../$folder/target/*.jar; bash"
done

sleep 10
folder=exchange
mvn clean package -f ./../$folder/ -DskipTests
for port in "${exchange_port[@]}"
do 
    gnome-terminal --title="$folder PORT: $port" --working-directory=WORK_DIR -x bash -c "java -jar -Dserver.port=$port $current_dir/../$folder/target/*.jar; bash"
done

folder=transaction
mvn clean package -f ./../$folder/ -DskipTests
for port in "${transaction_port[@]}"
do 
    gnome-terminal --title="$folder PORT: $port" --working-directory=WORK_DIR -x bash -c "java -jar -Dserver.port=$port $current_dir/../$folder/target/*.jar; bash"
done

#docker-compose up --build -d
# mvn spring-boot:run -Dspring-boot.run.profiles=8001