package agg.weather;

/**
 * Created by sbr on 4/23/17.
 */
import agg.Cities;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

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


        String city_list[] = {"New York", "Los Angeles"};


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
                    producer.send(new ProducerRecord<String, String>(kafkaTopic, weatherjson));
                    System.out.println(df.format(calobj.getTime()) + "  Sent\n");
                }

                Thread.sleep(timeInterval * 60 * 1000);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            producer.close();
        }

    }
}