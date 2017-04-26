/**
 * Created by sbr on 4/23/17.
 */
import agg.traffic.TrafficThreadFunc;
import agg.air.airThreadFunc;
import agg.weather.WProducer;

public class Driver {
    public static void main (String args[]) throws InterruptedException {

        // in minutes
        Integer trafficThreadDelay = 1;
        Integer airThreadDelay = 1;
        Integer weatherThreadDelay = 1;

        String kafkaTrafficTopic = "traffic";
        String kafkaAirTopic = "air";
        String kafkaWeatherTopic = "weather";

        TrafficThreadFunc trafficRunnable = new TrafficThreadFunc(trafficThreadDelay,kafkaTrafficTopic);
        Thread trafficThread = new Thread(trafficRunnable);

        airThreadFunc airRunnable = new airThreadFunc(airThreadDelay,kafkaAirTopic);
        Thread airThread = new Thread(airRunnable);

        WProducer weatherRunnable = new WProducer(weatherThreadDelay,kafkaWeatherTopic);
        Thread weatherThread = new Thread(weatherRunnable);

        trafficThread.start();
        airThread.start();
        weatherThread.start();

        trafficThread.join();
        airThread.join();
        weatherThread.join();

    }
}
