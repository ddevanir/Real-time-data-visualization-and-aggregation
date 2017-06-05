package agg.traffic;

import org.apache.kafka.clients.producer.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.util.Properties;
import java.util.UUID;

/**
 * Created by sbr on 4/19/17.
 */
public class KProducer {
    private Producer<String, String> producer;
    private static final Logger logger = LogManager.getLogger("MonitorThread");
    public KProducer(){
        Properties kafkaProps = new Properties();
        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put(ProducerConfig.ACKS_CONFIG, "1");
        //kafkaProps.put(ProducerConfig.BATCH_SIZE_CONFIG, "100"); //commented out on purpose

        this.producer = new KafkaProducer<String, String>(kafkaProps);
    }
    public void Produce(String topic, JSONObject obj){

        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, obj.toString());
        String key = "key-" + UUID.randomUUID().toString();
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

//        ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topic,null,obj.toString());
//        System.out.println(obj);
//        producer.send(rec, new Callback() {
//            @Override
//            public void onCompletion(RecordMetadata metadata, Exception e) {
//                System.out.println("Message sent to topic ->" + metadata.topic()+ " ,partition->" + metadata.partition() +" stored at offset->" + metadata.offset());
//            }
//        });

    }


}
