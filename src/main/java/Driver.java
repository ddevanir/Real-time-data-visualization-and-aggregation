/**
 * Created by sbr on 4/23/17.
 */
import agg.Cities;
import agg.traffic.TrafficThreadFunc;
import agg.weather.WProducer;
import agg.air.airThreadFunc;

public class Driver {
    public static void main (String args[]) throws InterruptedException {
        Cities.Init();

        // in minutes
        Integer trafficThreadDelay = 1;
        Integer airThreadDelay = 1;
        Integer weatherThreadDelay = 1;

        String kafkaTrafficTopic = "newtraffic";
        String kafkaAirTopic = "newair";
        String kafkaWeatherTopic = "newweather";

        TrafficThreadFunc trafficRunnable = new TrafficThreadFunc(trafficThreadDelay,kafkaTrafficTopic);
        Thread trafficThread = new Thread(trafficRunnable);

        airThreadFunc airRunnable = new airThreadFunc(airThreadDelay,kafkaAirTopic);
        //AProducer airRunnable = new AProducer(airThreadDelay,kafkaAirTopic);
        Thread airThread = new Thread(airRunnable);

        WProducer weatherRunnable = new WProducer(weatherThreadDelay,kafkaWeatherTopic);
        Thread weatherThread = new Thread(weatherRunnable);

        trafficThread.start();
        //airThread.start();
        weatherThread.start();
        trafficThread.join();
        //airThread.join();
        weatherThread.join();
    }
}
