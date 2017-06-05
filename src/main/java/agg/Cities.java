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

        cities.add("Chicago");
        getLatLon.put("Chicago","41.8781,-87.6298");
        citiesbybb.put("Chicago", "41.644335%2C-87.940267%2C42.023131%2C-87.523661");

        cities.add("Los%20Angeles");
        getLatLon.put("Los%20Angeles","34.0522,-118.2437");
        citiesbybb.put("Los%20Angeles","33.703652%2C-118.668176%2C34.337306%2C-118.155289");

        cities.add("Irvine");
        getLatLon.put("Irvine","33.6846,-117.8265");
        citiesbybb.put("Irvine","33.599391%2C-117.868752%2C33.773652%2C-117.678026");

        cities.add("Bangalore");
        getLatLon.put("Bangalore","12.971,77.5946");
        citiesbybb.put("Bangalore","12.734289%2C77.379198%2C13.173706%2C77.882681");
    }
}
