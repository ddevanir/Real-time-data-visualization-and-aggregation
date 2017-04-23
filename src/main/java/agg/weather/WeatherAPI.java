package agg.weather;

/**
 * Created by sbr on 4/23/17.
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//Handles all Weather related Functions
public class WeatherAPI {
    JSONObject weatherjson;



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
    { //Removes redundant fields
        getJsonFromServer(city);
        JSONObject finalData = new JSONObject();
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
        //System.out.println(finalData.toString());
        return finalData.toString();

    }

}
