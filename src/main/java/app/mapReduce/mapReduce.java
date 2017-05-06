package app.mapReduce;

/**
 * Created by sbr on 4/25/17.
 * https://spark.apache.org/docs/2.1.0/quick-start.html
 * http://stackoverflow.com/questions/40041592/spark-streaming-kafka-consumer
 */

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.scalatest.tools.Durations;

import java.util.*;

public class mapReduce {
//    public void initializekafka() {
//        Map<String, Object> kafkaParams = new HashMap<>();
//        kafkaParams.put("bootstrap.servers", "localhost:9092");
//        kafkaParams.put("key.deserializer", StringDeserializer.class);
//        kafkaParams.put("value.deserializer", StringDeserializer.class);
//        kafkaParams.put("group.id", "use_a_separate_group_id_for_each_stream");
//        kafkaParams.put("auto.offset.reset", "latest");
//        kafkaParams.put("enable.auto.commit", false);
//
//        Collection<String> topics = Arrays.asList("air", "weather", "traffic");
//    }

    public void kafkaSpark() {
        String topics = "air, weather, traffic";

        // Create context with a 2 seconds batch interval
        SparkConf sparkConf = new SparkConf().setAppName("StreamingE")
                .setMaster("local[1]");

        JavaStreamingContext jssc;
        //jssc = new JavaStreamingContext(sparkConf, Durations.seconds(2));
        Set<String> topicsSet = new HashSet<>(Arrays.asList(topics.split(",")));
        Map<String, Object> kafkaParams = new HashMap<>();

        kafkaParams.put("bootstrap.servers", "localhost:9092");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "use_a_separate_group_id_for_each_stream");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);

//        // Create direct kafka stream with brokers and topics
//        JavaPairInputDStream<String, String> messages = KafkaUtils.createDirectStream(
//                jssc,
//                String.class,
//                String.class,
//                StringDecoder.class,
//                StringDecoder.class,
//                kafkaParams,
//                topicsSet
//        );
//
//        // Start the computation
//        jssc.start();
//        jssc.awaitTermination();
    }
}
