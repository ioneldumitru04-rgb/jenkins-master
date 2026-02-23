FROM jenkins/jenkins:latest-jdk21

USER root

RUN apt-get update && apt-get install -y docker.io

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt

USER jenkins