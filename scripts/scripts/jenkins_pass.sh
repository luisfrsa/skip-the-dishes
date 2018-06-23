#!/usr/bin/env bash
echo "Jenkins user:" &&
echo "admin" &&
echo "Jenkins password:" &&
docker exec -it docker_jenkins_1 cat //var/jenkins_home/secrets/initialAdminPassword