//package app.mapReduce;
//
///**
// * Created by sbr on 4/25/17.
// * https://spark.apache.org/docs/2.1.0/quick-start.html
// */
//
//import kafka.serializer.StringDecoder;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.streaming.Duration;
//import org.apache.spark.streaming.api.java.JavaDStream;
//import org.apache.spark.streaming.api.java.JavaPairDStream;
//import org.apache.spark.streaming.api.java.JavaPairInputDStream;
//import org.apache.spark.streaming.api.java.JavaStreamingContext;
//import org.apache.spark.streaming.kafka.KafkaUtils;
//import scala.Tuple2;
//
//import java.util.*;
//
//import static javax.xml.stream.XMLStreamConstants.SPACE;
//
//public class mapReduce {
//
//    public void initializekafka() {
//        Map<String, Object> kafkaParams = new HashMap<>();
//        kafkaParams.put("bootstrap.servers", "localhost:9092");
//        kafkaParams.put("key.deserializer", StringDeserializer.class);
//        kafkaParams.put("value.deserializer", StringDeserializer.class);
//        kafkaParams.put("group.id", "use_a_separate_group_id_for_each_stream");
//        kafkaParams.put("auto.offset.reset", "latest");
//        kafkaParams.put("enable.auto.commit", false);
//        Collection<String> topics = Arrays.asList("air", "weather", "traffic");
//    }
//
//    public static void main(String[] args) {
//
//        SparkConf conf = new SparkConf()
//                .setAppName("kafka-sandbox")
//                .setMaster("local[*]");
//        JavaSparkContext sc = new JavaSparkContext(conf);
//        JavaStreamingContext ssc = new JavaStreamingContext(sc, new Duration(2000));
//
//        // TODO: processing pipeline
//        Map<String, String> kafkaParams = new HashMap<>();
//        kafkaParams.put("metadata.broker.list", "localhost:9092");
//        Set<String> topics = Collections.singleton("mytopic");
//
//        JavaPairInputDStream<String, String> directKafkaStream = KafkaUtils.createDirectStream(ssc,
//                String.class, String.class, StringDecoder.class, StringDecoder.class, kafkaParams, topics);
//        //directKafkaStream.foreach();
//
////        directKafkaStream.mapToPair(
////                new PairFunction<ConsumerRecord<String, String>, String, String>() {
////                    @Override
////                    public Tuple2<String, String> call(ConsumerRecord<String, String> record) {
////                        return new Tuple2<>(record.key(), record.value());
////                    }
////                });
//
//
//        JavaDStream<String> lines = directKafkaStream.map(Tuple2::_2);
//
//        JavaDStream<String> words = lines.flatMap(x -> {
//            return Arrays.asList(SPACE.split(x)).iterator();
//        });
//
//        JavaPairDStream<String, Integer> wordCounts = words.mapToPair(s -> new Tuple2<>(s, 1))
//                .reduceByKey((i1, i2) -> i1 + i2);
//
//
//        ssc.start();
//
//        //ssc.awaitTermination();
//    }
//
//
//}
