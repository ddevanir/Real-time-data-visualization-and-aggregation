package agg.traffic;
import java.util.Properties;

import agg.KPartitioner;
import org.apache.kafka.clients.producer.*;
import org.json.JSONObject;

/**
 * Created by sbr on 4/19/17.
 */
public class KProducer {
    private Producer<String, String> producer;

    public KProducer(){
        Properties prop = new Properties();
        prop.put("bootstrap.servers", "127.0.0.1:9092");
        prop.put("linger.ms", 1);
        prop.put("acks", "all");
        prop.put("retries", 0);
        prop.put("batch.size", 16384);
        prop.put("buffer.memory", 33554432);
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //prop.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, KPartitioner.class.getCanonicalName());
        producer = new KafkaProducer<String,String>(prop);
    }
    public void Produce(String topic, JSONObject obj){
        ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topic,obj.toString());
        System.out.println(obj);
        producer.send(rec, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception e) {
                System.out.println("Message sent to topic ->" + metadata.topic()+ " ,partition->" + metadata.partition() +" stored at offset->" + metadata.offset());
            }
        });

    }


}
