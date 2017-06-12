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

./kibana-5.4.1-darwin-x86_64/config/kibana.yml uncomment elasticsearch.url: "http://localhost:9200" in line 21. This will connect kibana with elasticsearch

Setup details:
1) run kafka on your Mac.
no need to create topic in prior. just run.
but run kafka version: kafka_2.11-0.10.1.0
2) start driver which will push data to kafka
3) start master node
4) start one worker node with cmd line argument as newtraffic
5) start one worker node with cmd line argument as newweather
6) Data will be pushed to the elastic cloud.
7) To verify step 6, run the below queries on traffic and airweather indices, you should be able to see an increase in the document count.

GET airweather/_search
{
  "query": {
    "match_all": {}
  }
}

GET traffic/_search
{
  "query": {
    "match_all": {}
  }
}

Sample response : 
"hits": {
    "total": 366,
    "max_score": 1,
    "hits":

The number total:336 is the total number of documents in the queried index.
So this number should be increasing every minute for airweather.
But for traffic, it may or may not increase as we update old entries.
Only if a new traffic incident is reported, this number increases.

Kibana details:
1)  Kibana https://c984fbba62aceaab2f6c8abf3ca90b63.us-east-1.aws.found.io
new cluster username : elastic
new cluster password: ZvXhxvOxMmszYfUxMynvLTj2
2) HTTP endpoint : https://2fe405d238cb5f93c1de41de0a2531a1.us-east-1.aws.found.io:9243
3) https://www.elastic.co/guide/en/cloud/current/security.html#security-transport
4) login URL https://cloud.elastic.co/#/authentication/login/
Step 1: login to login URL using kevseb1993@gmail.com and ucirvine
Step 2: Click view existing clusters
Step 3: Click Kibana endpoint
Step 4: Log into Kibana using cluster username and password

Visualisation in Kibana
1 = Construction
2 = Event
3 = Congestion/Flow
4 = Incident/accident
severity 0 - 4, 4 indicates the highest severity
To generate the pixel box around the cities
1) http://www.mastersindatascience.org/blog/open-source-tools-for-big-data-analysis/
South Latitude, West Longitude, North Latitude, East Longitude
2) https://www.mapdevelopers.com/geocode_bounding_box.php