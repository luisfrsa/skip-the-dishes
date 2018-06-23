#!/usr/bin/env bash
mvn clean install -f ../exchange -Pdevsonar && \
mvn sonar:sonar -f ../exchange -Pdevsonar -Dsonar.host.url=http://localhost:9000 