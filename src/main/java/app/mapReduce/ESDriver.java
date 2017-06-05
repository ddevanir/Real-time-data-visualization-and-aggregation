package app.mapReduce;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

/**
 * Created by sbr on 4/26/17.
 */

public class ESDriver {
   private TransportClient client;
   private String esURL = "https://c18a49f9375e6eb9d592e2c47f8d67bf.us-east-1.aws.found.io";
   private String trafficIndex;
   private String trafficIndexype;
   private String airWeatherIDx;
   private String airWeatherIdxType;

    public ESDriver() throws UnknownHostException {
       // Settings settings = Settings.builder()
       //         .put("cluster.name", "c18a49f9375e6eb9d592e2c47f8d67bf").build();
        client = new PreBuiltTransportClient(Settings.EMPTY)
               // .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esURL), 9243));
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        trafficIndex = "traffic";
        trafficIndexype = "incidents";
        airWeatherIDx = "airweather";
        airWeatherIdxType = "airweathertype";
    }

    public void InsertAirWeatherJSON(JSONObject object){
        IndexResponse response = client.prepareIndex(airWeatherIDx, airWeatherIdxType)
                .setSource(object.toMap())
                .get();
    }

    public void InsertTrafficJSON(JSONObject object) throws ExecutionException, InterruptedException {
//        IndexRequest indexRequest = new IndexRequest(trafficIndex,trafficIndexype, String.valueOf(object.getLong("id")))
//                .source(object.toMap());
//        UpdateRequest updateRequest = new UpdateRequest(trafficIndex,trafficIndexype, String.valueOf(object.getLong("id")))
//
//                .upsert(indexRequest);
//        UpdateResponse response = client.update(updateRequest).get();
        IndexResponse response = client.prepareIndex(trafficIndex,trafficIndexype, String.valueOf(object.getLong("id")))
                .setSource(object.toMap())
                .get();
    }

}
