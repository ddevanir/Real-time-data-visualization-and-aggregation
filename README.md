Step 1: Download the code
> cd kafka_2.11-0.10.2.0
Step 2: Start the server
//zookeeper instance
> bin/zookeeper-server-start.sh config/zookeeper.properties
//start kafka server
> bin/kafka-server-start.sh config/server.properties

> bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1  --partitions 2 --topic weather (optional)
>bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1  --partitions 3 --topic traffic (optional)
> ./bin/kafka-topics.sh --alter --zookeeper localhost:2181 --topic weather --partitions 2
> ./bin/kafka-topics.sh --alter --zookeeper localhost:2181 --topic traffic --partitions 3

Creation of index in ES

PUT traffic
{
    "settings" : {
        "number_of_shards" : 1
    },
    "mappings" : {
        "incidents" : {
            "properties" : {
                "city" : { "type" : "string" },
                "severity" : { "type" : "integer"},
                "location" : {"type": "geo_point"},
                "startTime" : {"type": "date"},
                "endTime" : {"type": "date"},
                "type" : { "type" : "integer" }
            }
        }
    }
}

PUT airweather
{
    "settings" : {
        "number_of_shards" : 1
    },
    "mappings" : {
        "airweathertype" : {
            "properties" : {
                "city" : { "type" : "string" },
                "location" : {"type": "geo_point"},
                "Date" : {"type": "date"},
                "temp" : { "type" : "integer" },
                "temp_min" : { "type" :"integer" },
                "temp_max" : { "type" :"integer" },
                "humidity" : { "type" :"integer" },
                "AQI" : { "type" : "integer" },
                "type" : { "type" : "string" }
            }
        }
    }
}