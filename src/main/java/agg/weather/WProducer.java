package agg.weather;

/**
 * Created by sbr on 4/23/17.
 * ./bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic weather
 * http://www.javaworld.com/article/3066873/big-data/big-data-messaging-with-kafka-part-2.html
 * ./bin/kafka-topics.sh --alter --zookeeper localhost:2181 --topic weather --partitions 2
 * http://stackoverflow.com/questions/27036923/how-to-create-a-topic-in-kafka-through-java
 * bin/kafka-topics.sh --list --zookeeper localhost:2181
 *
 */
import agg.Cities;
import agg.KPartitioner;
import kafka.utils.ZkUtils;
import org.apache.kafka.clients.producer.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;


public class WProducer implements Runnable {

    private Integer timeInterval; // in minutes
    private String kafkaTopic;

    public WProducer(Integer delay, String ktopic){
        timeInterval = delay;
        kafkaTopic = ktopic;
    }
    public void run() {
        //Gets the weather related data
        WeatherAPI wapi = new WeatherAPI();
        ZkUtils zkUtils = null;


        //Sets up the producer
        Properties prop = new Properties();
        prop.put("bootstrap.servers", "127.0.0.1:9092");
        prop.put("linger.ms", 1);
        prop.put("acks", "all");
        prop.put("retries", 0);
        prop.put("batch.size", 16384);
        prop.put("buffer.memory", 33554432);
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,KPartitioner.class.getCanonicalName());




        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<String,String>(prop);
            while (true) {
                for (String city : Cities.cities) {
                    Calendar calobj = Calendar.getInstance();
                    DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                    System.out.println(df.format(calobj.getTime()) + "  Sending message");
                    String weatherjson = "";
                    try {
                        weatherjson = wapi.getJSON(city);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println(weatherjson);
                    ProducerRecord<String, String> rec = new ProducerRecord<String, String>(kafkaTopic,weatherjson);

                    producer.send(rec, new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata metadata, Exception e) {
                           System.out.println("Message sent to topic ->" + metadata.topic()+ " ,parition->" + metadata.partition() +" stored at offset->" + metadata.offset());
                        }
                    });
                    System.out.println(df.format(calobj.getTime()) + "  Sent\n");
                    ;
                }

                Thread.sleep(timeInterval * 40 * 1000);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            producer.close();
        }

    }
}