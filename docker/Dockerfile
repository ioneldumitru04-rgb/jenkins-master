FROM jenkins/inbound-agent:latest

USER root

RUN apt-get update && apt-get install -y \
    python3 \
    python3-pip \
    jq \
    curl \
    git \
    && rm -rf /var/lib/apt/lists/*

RUN pip3 install --no-cache-dir \
    requests \
    boto3 \
    pyyaml \
    jinja2

USER jenkins 

#sudo docker images jenkins-agent-pack:latest