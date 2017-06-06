package agg.weather;

/**
 * Created by sbr on 4/23/17.
 */
import agg.Cities;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//Handles all Weather related Functions
public class WeatherAPI {
    JSONObject weatherjson;

    public JSONObject getJSONFromUrl(String city) throws IOException {
        //Gets Air json
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

    public JSONObject parseJSON( String city) throws IOException{
        JSONObject airData = new JSONObject();
        JSONObject airJson = getJSONFromUrl(city);
        airData.put("AQI", airJson.getJSONObject("data").get("aqi"));
        airData.put("ID", airJson.getJSONObject("data").get("idx"));
        airData.put("City", airJson.getJSONObject("data").getJSONObject("city").get("name"));
        airData.put("location", Cities.getLatLon.get(city));
        airData.put("Time", airJson.getJSONObject("data").getJSONObject("time").get("s"));
        return airData;
    }

    public void getJsonFromServer(String city) throws MalformedURLException, IOException, JSONException
    {  //Gets the JSON API using REST call

        String city_url = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID=52cbf6e6ea6e22b3138dc76808ab3d2e";
        URL url = new URL(city_url);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        int responseCode = urlConnection.getResponseCode();
        System.out.println(responseCode);
        InputStream is = new BufferedInputStream(urlConnection.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder total = new StringBuilder(is.available());

        String line;
        while ((line = reader.readLine()) != null) {
            total.append(line).append('\n');
        }

        String output = total.toString();
        //System.out.println("Output:" + output);
        weatherjson = new JSONObject(output);
        return;
    }

    public String getJSON(String city) throws  MalformedURLException, IOException, JSONException
    { //Removes redundant fields and gets final JSON by
        getJsonFromServer(city);
        JSONObject finalData = new JSONObject();
        JSONObject airData = parseJSON(city);

        Calendar calobj = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        //DateFormat df1 = new SimpleDateFormat("dd/MM/yy");
        //DateFormat df2 = new SimpleDateFormat("HH");
        String date = df.format(calobj.getTime());
        //String hour = df2.format(calobj.getTime());
        finalData.put("Date", date);
        //finalData.put("Hour", hour);
        finalData.put("temp", weatherjson.getJSONObject("main").get("temp"));
        finalData.put("humidity", weatherjson.getJSONObject("main").get("humidity"));
        finalData.put("temp_min", weatherjson.getJSONObject("main").get("temp_min"));
        finalData.put("temp_max", weatherjson.getJSONObject("main").get("temp_max"));
        finalData.put("type", weatherjson.getJSONArray("weather").getJSONObject(0).get("main"));
        finalData.put("city", city);
        finalData.put("cityID", Cities.getID.get(city));
        finalData.put("location", Cities.getLatLon.get(city));
        finalData.put("AQI",airData.get("AQI"));
        finalData.put("ID",airData.get("ID"));
        airData.put("location", airData.get("location"));
        airData.put("Time", airData.get("Time"));
        //System.out.println(finalData.toString());
        return finalData.toString();

    }



}
