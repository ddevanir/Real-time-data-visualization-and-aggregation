package agg.air;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
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
        ArrayList<String> cities = new ArrayList<String>();
        cities.add("NewYork");
        cities.add("Irvine");

        while (true) {
            for (String city : cities) {
                JSONObject airJson = null;
                try {
                    airJson = air.getJSONFromUrl(city);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JSONObject airParse = air.parseJSON(airJson);
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
