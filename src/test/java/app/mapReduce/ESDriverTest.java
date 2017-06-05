package app.mapReduce;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

/**
 * Created by sbr on 4/26/17.
 */
public class ESDriverTest {
    private static ESDriver driver;
    @BeforeClass
    public static void setDriver() throws IOException {
        driver = new ESDriver();
    }

    @Test
    public void test1() throws IOException {
        JSONObject object = new JSONObject();
        object.put("name","USA");
        driver.InsertAirWeatherJSON(object);
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException, IOException {
        JSONObject object = new JSONObject();
        object.put("id",2);
        object.put("name","sachin");
        driver.InsertTrafficJSON(object);
        JSONObject object2 = new JSONObject();
        object2.put("id",2);
        object2.put("name","sachin-2");
        driver.InsertTrafficJSON(object2);
    }
}
