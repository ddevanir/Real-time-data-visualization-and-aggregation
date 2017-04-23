package traffic.test;

/**
 * Created by sbr on 4/23/17.
 */
import agg.traffic.KProducer;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

public class KProdConsTest {
    private static KProducer producer;
    private static KConsumer consumer;

    @BeforeClass
    public static void setup(){
        producer = new KProducer();
        consumer = new KConsumer();

    }

    @Test
    public void test1(){
        producer.Produce("test",new JSONObject());
        consumer.Consume("test");
    }

}
