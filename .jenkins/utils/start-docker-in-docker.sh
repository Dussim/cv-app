#!/bin/bash
set -o errexit -o nounset
docker run --detach \
  --name docker-in-docker \
  --restart=unless-stopped \
  --privileged \
  --network jenkins \
  --network-alias docker \
  --env DOCKER_TLS_CERTDIR=/certs \
  --volume jenkins-docker-certs:/certs/client \
  --volume jenkins-data:/var/jenkins_home \
  --publish 2376:2376 \
  --publish 3000:3000 \
  --publish 5000:5000 \
  docker:dind \
  --storage-driver overlay2
