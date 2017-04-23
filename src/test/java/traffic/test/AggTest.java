package traffic.test;

/**
 * Created by sbr on 4/23/17.
 */
import agg.traffic.TrafficDataAgg;
import org.apache.http.HttpException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * Created by sbr on 4/20/17.
 */
public class AggTest {
    private static TrafficDataAgg agg;
    @BeforeClass
    public static void setup(){
        agg = new TrafficDataAgg();
    }

    @Test
    public void test1(){
        HashMap<String,String> city = new HashMap<String, String>();
        city.put("New York","40.477399%2C-74.259090%2C40.917577%2C-73.700272");
        city.put("Los Angeles","33.703652,-118.668176,34.337306,-118.155289");
        JSONObject obj = null;
        try {
            obj = agg.getTrafficData(city.get("New York"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
        JSONArray arr=  agg.parseJSON(obj, "New York");
        for (int i = 0; i < 2; i++) {
            JSONObject item = arr.getJSONObject(i);
            System.out.println(item.toString()) ;

        }
    }

}
