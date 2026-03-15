FROM jenkins/inbound-agent:latest

USER root

RUN apt-get update && apt-get install -y \
    python3 \
    python3-pip \
    jq \
    curl \
    git \
    && rm -rf /var/lib/apt/lists/*

RUN pip3 install --break-system-package --no-cache-dir \
    requests \
    boto3 \
    pyyaml \
    jinja2 \
    pytest \
    flask_security \
    flask_cors \
    mysql-connector \
    bandit

USER jenkins 

#sudo docker images jenkins-agent-pack:latest
#docker build -t packages-agent .