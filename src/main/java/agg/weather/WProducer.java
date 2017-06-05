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
import kafka.utils.ZkUtils;
import org.apache.kafka.clients.producer.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.UUID;


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
        Properties kafkaProps = new Properties();
        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put(ProducerConfig.ACKS_CONFIG, "1");
        //kafkaProps.put(ProducerConfig.BATCH_SIZE_CONFIG, "100"); //commented out on purpose
        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<String,String>(kafkaProps);
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

                    ProducerRecord<String, String> record = new ProducerRecord<String, String>(kafkaTopic,weatherjson);
                    final String key = "key-" + UUID.randomUUID().toString();
                    producer.send(record, new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata rm, Exception excptn) {
                            if (excptn != null) {
                                //logger.log(Level.WARNING, "Error sending message with key \n{1}", new Object[]{excptn.getMessage()});
                            } else {
                                // logger.log(Level.INFO, "Partition for key is {1}", new Object[]{key,rm.partition()});
                                System.out.println("Message sent to topic ->" + rm.topic()+ " ,partition->" + rm.partition() +" stored at offset->" + rm.offset());
                            }

                        }
                    });

                    System.out.println(df.format(calobj.getTime()) + "  Sent\n");
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