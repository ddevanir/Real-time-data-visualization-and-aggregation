package agg.traffic;

import agg.Cities;
import org.apache.http.HttpException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sbr on 4/23/17.
 */
public class TrafficThreadFunc implements Runnable{
    private Integer timeInterval; // in minutes
    private String kafkaTopic;

    public TrafficThreadFunc(Integer delay, String ktopic){
        timeInterval = delay;
        kafkaTopic = ktopic;
    }
    public void run() {
        HashMap<String,String> cities = new HashMap<String, String>();
        cities.put("New York","40.477399%2C-74.259090%2C40.917577%2C-73.700272");
        cities.put("Los Angeles","33.703652,-118.668176,34.337306,-118.155289");

        TrafficDataAgg agg = new TrafficDataAgg();
        KProducer producer = new KProducer();



        while (true) {
            for (Map.Entry<String, String> entry : Cities.citiesbybb.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                JSONObject obj = null;
                try {
                    obj = agg.getTrafficData(value);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (HttpException e) {
                    e.printStackTrace();
                }
                JSONArray arr = agg.parseJSON(obj,key);
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject item = arr.getJSONObject(i);
                    producer.Produce(kafkaTopic,item);

                }

            }
            try {
                Thread.sleep(timeInterval * 60 *1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
