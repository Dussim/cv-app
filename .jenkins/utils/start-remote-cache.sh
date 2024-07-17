#!/bin/bash
set -o errexit -o nounset
docker run --detach \
  --name build-cache-node \
  --restart=unless-stopped \
  --volume build-cache-volume:/data \
  --publish 5071:5071 \
  --env JAVA_OPTS="-Xms3g -Xmx3g -XX:MaxDirectMemorySize=1g" \
  gradle/build-cache-node:19.0 \
  start