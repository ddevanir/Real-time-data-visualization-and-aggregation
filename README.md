Step 1: Download the code
> cd kafka_2.11-0.10.2.0
Step 2: Start the server
//zookeeper instance
> bin/zookeeper-server-start.sh config/zookeeper.properties
//start kafka server
> bin/kafka-server-start.sh config/server.properties

> bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1  --partitions 2 --topic weather (optional)
> ./bin/kafka-topics.sh --alter --zookeeper localhost:2181 --topic weather --partitions 2
> ./bin/kafka-topics.sh --alter --zookeeper localhost:2181 --topic traffic --partitions 3