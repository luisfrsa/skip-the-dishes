FROM sonarqube:latest

RUN apt-get update -y && \
    apt-get install wget -y && \
    wget https://github.com/SonarSource/sonar-java/releases/download/5.0-RC1/sonar-java-plugin-5.0.0.12773.jar && \
    cp ./sonar-java-plugin-5.0.0.12773.jar $SONARQUBE_HOME/extensions/plugins/sonar-java-plugin-5.0.0.12773.jar
