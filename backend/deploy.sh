#!/bin/bash
./gradlew clean build -x test
docker buildx build --platform linux/amd64 --load --tag hsh111366/snowball_backend:1.0 .
docker push hsh111366/snowball_backend:1.0