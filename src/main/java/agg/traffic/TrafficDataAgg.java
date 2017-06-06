package agg.traffic;

import agg.Cities;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class TrafficDataAgg{
    private String appKey = "cPF1vAQ0yQOCSJ76v94CStE6XsanwaBt";


    public JSONObject getTrafficData(String city) throws URISyntaxException, IOException, HttpException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("https://www.mapquestapi.com/traffic/v2/incidents?&outFormat=json&boundingBox="+city+"&filters=incidents,event,congestion,construction&key=" + appKey);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            sb.append(line).append('\n');
        }
        JSONObject trafficData = new JSONObject(sb.toString());
        return trafficData;
    }

    public JSONArray parseJSON(JSONObject trafficData, String key) throws JSONException{
        JSONArray ret = new JSONArray();
        JSONArray array = trafficData.getJSONArray("incidents");

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            JSONObject traffic = new JSONObject();
            traffic.put("id",obj.getLong("id"));
            traffic.put("city",key);
            traffic.put("cityID",Cities.getID.get(key));
            traffic.put("location", Cities.getLatLon.get(key));
            traffic.put("type",obj.getInt("type"));
            traffic.put("severity",obj.getInt("severity"));
            traffic.put("startTime",obj.getString("startTime"));
            traffic.put("endTime",obj.getString("endTime"));
            traffic.put("delay",obj.getDouble("delayFromTypical"));
            ret.put(traffic);
        }
        return  ret;
    }
//    public static void main(String args[]) throws HttpException, IOException, URISyntaxException {
//        TrafficDataAgg t = new TrafficDataAgg();
//        HashMap<String,String> city = new HashMap<String, String>();
//        city.put("New York","40.477399%2C-74.259090%2C40.917577%2C-73.700272");
//        city.put("Los Angeles","33.703652,-118.668176,34.337306,-118.155289");
//        JSONObject obj = t.getTrafficData(city.get("New York"));
//        JSONArray arr=  t.parseJSON(obj, "New York");
//        for (int i = 0; i < 2; i++) {
//            JSONObject item = arr.getJSONObject(i);
//            System.out.println(item.toString()) ;
//
//        }
//
//    }

}