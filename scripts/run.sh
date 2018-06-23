#!/bin/bash
current_dir=$PWD

docker-compose -f ../docker/docker-compose.yml up --build -d 
mvn clean install -f ../order -Pdevsonar
mvn sonar:sonar -f ../order -Pdevsonar -Dsonar.host.url=http://localhost:9000 
mvn spring-boot:run -f ../order
