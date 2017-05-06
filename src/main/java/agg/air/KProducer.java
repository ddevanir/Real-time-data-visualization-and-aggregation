package agg.air;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
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
        prop.put("num.partitions", "2");
        producer = new KafkaProducer<String,String>(prop);
    }
    public void Produce(String topic, JSONObject obj){
        producer.send(new ProducerRecord<String,String>(topic,obj.toString()));
    }
}
