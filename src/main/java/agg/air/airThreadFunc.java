package agg.air;
import agg.Cities;
import org.json.JSONObject;

import java.io.IOException;
/**
 * Created by sbr on 4/23/17.
 */
public class airThreadFunc implements Runnable{
    private Integer timeInterval; // in minutes
    private String kafkaTopic;

    public airThreadFunc(Integer delay, String ktopic){
        timeInterval = delay;
        kafkaTopic = ktopic;
    }
    public void run() {
        airDataAgg air = new airDataAgg();
        while (true) {
            for (String city : Cities.cities) {
                JSONObject airJson = null;
                try {
                    airJson = air.getJSONFromUrl(city);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JSONObject airParse = air.parseJSON(airJson,city);
                KProducer pub = new KProducer();
                pub.Produce(kafkaTopic, airParse);
            }
            try {
                Thread.sleep(timeInterval * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
