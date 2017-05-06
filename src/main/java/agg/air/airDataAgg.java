package agg.air;
import agg.Cities;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * Created by sbr on 4/23/17.
 */
public class airDataAgg {
    public JSONObject getJSONFromUrl(String city) throws IOException {
        String result = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://api.waqi.info/feed/"+ city + "/?token=693eeabd30da32ea0c269922b4a822233a2f9874");
        CloseableHttpResponse response = httpclient.execute(httpGet);
        InputStream ips = response.getEntity().getContent();
        BufferedReader buf = new BufferedReader(new InputStreamReader(ips,"UTF-8"));
        StringBuilder sb = new StringBuilder();
        String s;
        while (true) {
            s = buf.readLine();
            if (s == null || s.length() == 0)
                break;
            sb.append(s + "\n");
        }
        buf.close();
        ips.close();
        result = sb.toString();
        JSONObject airJson = new JSONObject(result);
        return airJson;
    }

    public String parseJSON(JSONObject airJson,String city) {
        JSONObject airData = new JSONObject();
        airData.put("AQI", airJson.getJSONObject("data").get("aqi"));
        airData.put("ID", airJson.getJSONObject("data").get("idx"));
        airData.put("City", airJson.getJSONObject("data").getJSONObject("city").get("name"));
        airData.put("location", Cities.getLatLon.get(city));
        airData.put("Time", airJson.getJSONObject("data").getJSONObject("time").get("s"));
        return airData.toString();
    }
}
