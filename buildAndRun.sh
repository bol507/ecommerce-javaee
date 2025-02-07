#!/bin/sh
mvn clean package && docker build -t com.ecommerce/ecommerce .
docker rm -f ecommerce || true && docker run -d -p 8080:8080 -p 4848:4848 --name ecommerce com.ecommerce/ecommerce 
