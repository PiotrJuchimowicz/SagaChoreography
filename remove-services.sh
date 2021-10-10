docker stop claim-service-db
docker stop policy-service-db
docker stop order-service-db

docker stop zookeeper
docker stop broker
docker stop schema-registry
docker stop connect
docker stop kafka
docker stop control-center
docker stop ksqldb-server
docker stop ksqldb-cli
docker stop ksql-datagen
docker stop rest-proxy

docker rm claim-service-db
docker rm policy-service-db
docker rm order-service-db

docker rm zookeeper
docker rm broker
docker rm schema-registry
docker rm connect
docker rm kafka
docker rm control-center
docker rm ksqldb-server
docker rm ksqldb-cli
docker rm ksql-datagen
docker rm rest-proxy
