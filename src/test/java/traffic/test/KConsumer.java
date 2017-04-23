package traffic.test;

/**
 * Created by sbr on 4/23/17.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
/**
 * Created by sbr on 4/19/17.
 */
public class KConsumer {
    private KafkaConsumer<String, String> consumer;
    public KConsumer(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("session.timeout.ms", "30000");
        props.put("group.id", "group-1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<String,String>(props);

    }

    public void Consume(String topic){
        consumer.subscribe(Arrays.asList(topic));
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
            for (ConsumerRecord<String, String> record : records)
                System.out.println(record.value());
        }
        //consumer.close();
    }
}

