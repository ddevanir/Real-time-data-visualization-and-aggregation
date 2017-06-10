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
    public static HashMap<String,Integer> getID = new HashMap<String, Integer>();

    public static void Init(){
        cities.add("NewYork");
        getID.put("NewYork",1);
        getLatLon.put("NewYork","40.7128,-74.0059"); // lat,lon
        citiesbybb.put("NewYork","40.477399%2C-74.259090%2C40.917577%2C-73.700272");

        cities.add("Chicago");
        getID.put("Chicago",2);
        getLatLon.put("Chicago","41.8781,-87.6298");
        citiesbybb.put("Chicago", "41.644335%2C-87.940267%2C42.023131%2C-87.523661");

        cities.add("Los%20Angeles");
        getID.put("Los%20Angeles",3);
        getLatLon.put("Los%20Angeles","34.0522,-118.2437");
        citiesbybb.put("Los%20Angeles","33.703652%2C-118.668176%2C34.337306%2C-118.155289");

        cities.add("Irvine");
        getID.put("Irvine",4);
        getLatLon.put("Irvine","33.6846,-117.8265");
        citiesbybb.put("Irvine","33.599391%2C-117.868752%2C33.773652%2C-117.678026");

        cities.add("Bangalore");
        getID.put("Bangalore",5);
        getLatLon.put("Bangalore","12.971,77.5946");
        citiesbybb.put("Bangalore","12.734289%2C77.379198%2C13.173706%2C77.882681");

        cities.add("Seattle");
        getID.put("Seattle",6);
        getLatLon.put("Seattle","47.6062,-122.3321");
        citiesbybb.put("Seattle","47.491912%2C-122.459696%2C47.734145%2C-122.224433");

        cities.add("San%20Francisco");
        getID.put("San%20Francisco",7);
        getLatLon.put("San%20Francisco","37.7749,-122.4194");
        citiesbybb.put("San%20Francisco","37.639830%2C-123.173825%2C37.929824%2C-122.281780");

        cities.add("Austin");
        getID.put("Austin",8);
        getLatLon.put("Austin","30.2672,-97.7431");
        citiesbybb.put("Austin","30.098659%2C-97.938383%2C30.516863%2C-97.568420");

        cities.add("Denver");
        getID.put("Denver",9);
        getLatLon.put("Denver","39.7392,-104.9903");
        citiesbybb.put("Denver","39.614431%2C-105.109927%2C39.914247%2C-104.600296");

        cities.add("Paris");
        getID.put("Paris",10);
        getLatLon.put("Paris","48.8566,2.3522");
        citiesbybb.put("Paris","48.815573%2C2.224199%2C48.902145%2C2.469921");

        cities.add("London");
        getID.put("London",11);
        getLatLon.put("London","51.5074,-0.1278");
        citiesbybb.put("London","51.384940%2C-0.351468%2C51.672343%2C0.148271");

        cities.add("Beijing");
        getID.put("Beijing",12);
        getLatLon.put("Beijing","39.9042,116.4074");
        citiesbybb.put("Beijing","39.442758%2C115.423411%2C41.060816%2C117.514625");

        cities.add("Atlanta");
        getID.put("Atlanta",13);
        getLatLon.put("Atlanta","33.7490,84.3880");
        citiesbybb.put("Atlanta","33.647808%2C-84.551819%2C33.887618%2C-84.289389");




    }
}
