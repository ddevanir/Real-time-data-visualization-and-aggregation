package app.mapReduce;

/**
 * Created by sbr on 4/25/17.
 * https://spark.apache.org/docs/2.1.0/quick-start.html
 */
import java.util.*;

import org.apache.spark.api.java.function.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import scala.Tuple2;

public class mapReduce {

    public void initializekafka()
    {
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "localhost:9092");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "use_a_separate_group_id_for_each_stream");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);
        Collection<String> topics = Arrays.asList("air", "weather","traffic");


    }
}
