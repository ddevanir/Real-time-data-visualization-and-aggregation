package app.mapReduce;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by beep on 4/29/17.
 */
public class Consumer {
    private KafkaConsumer<String, String> consumer;

    public Consumer() {
        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "groupName");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer",
                "org.apache.kafka.common.serializa-tion.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serializa-tion.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
    }

    public void Consume(String topicName) {
        consumer.subscribe(Arrays.asList(topicName));

        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                // print the value for the consumer records.
                System.out.printf("value = %s\n", record.value());
        }
    }
}