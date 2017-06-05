package agg;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sbr on 4/26/17.
 */
public class Cities {
    public static ArrayList<String> cities = new ArrayList<String>();
    public static HashMap<String,String> citiesbybb = new HashMap<String, String>();
    public static HashMap<String,String> getLatLon = new HashMap<String, String>();

    public static void Init(){
        cities.add("NewYork");


        getLatLon.put("NewYork","40.7128,-74.0059"); // lat,lon
        citiesbybb.put("NewYork","40.477399%2C-74.259090%2C40.917577%2C-73.700272");

    }
}
