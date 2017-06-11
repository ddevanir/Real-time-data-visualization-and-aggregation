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

        cities.add("Vancouver");
        getID.put("Vancouver",14);
        getLatLon.put("Vancouver","49.2827,-123.1207");
        citiesbybb.put("Vancouver","49.198177%2C-123.224740%2C49.317294%2C-123.023068");

        cities.add("Las%20Vegas");
        getID.put("Las%20Vegas",15);
        getLatLon.put("Las%20Vegas","36.1699,-115.1398");
        citiesbybb.put("Las%20Vegas","36.129623%2C-115.414625%2C36.380623%2C-115.062072");

        cities.add("Dallas");
        getID.put("Dallas",16);
        getLatLon.put("Dallas","32.7767,-96.7970");
        citiesbybb.put("Dallas","32.617537%2C-96.999347%2C33.023792%2C-96.463738");

        cities.add("Ottawa");
        getID.put("Ottawa",17);
        getLatLon.put("Ottawa","45.4215,-75.6972");
        citiesbybb.put("Ottawa","44.962733%2C-76.353916%2C45.537580%2C-75.246598");

        cities.add("Miami");
        getID.put("Miami",18);
        getLatLon.put("Miami","25.7617,-80.1918");
        citiesbybb.put("Miami","25.709042%2C-80.319760%2C25.855773%2C-80.139157");

        cities.add("Mexico%20City");
        getID.put("Mexico%20City",19);
        getLatLon.put("Mexico%20City","19.4326,-99.1332");
        citiesbybb.put("Mexico%20City","19.188710%2C-99.326777%2C19.592757%2C-98.960448");

        cities.add("Lima");
        getID.put("Lima",20);
        getLatLon.put("Lima","-12.0464,77.0428");
        citiesbybb.put("Lima","-12.253289%2C-77.187219%2C-11.799988%2C-76.788340");

        cities.add("Buenos%20Aires");
        getID.put("Buenos%20Aires",21);
        getLatLon.put("Buenos%20Aires","-34.6037,58.3816");
        citiesbybb.put("Buenos%20Aires","-34.705101%2C-58.531452%2C-34.526546%2C-58.335145");

        cities.add("Calgary");
        getID.put("Calgary",22);
        getLatLon.put("Calgary","51.0486,-114.0708");
        citiesbybb.put("Calgary","50.842404%2C-114.271360%2C51.183830%2C-113.876951");

        cities.add("Winnipeg");
        getID.put("Winnipeg",23);
        getLatLon.put("Winnipeg","49.8951,-97.1384");
        citiesbybb.put("Winnipeg","49.713770%2C-97.349222%2C49.993874%2C-96.955228");

        cities.add("Winnipeg");
        getID.put("Winnipeg",24);
        getLatLon.put("Winnipeg","49.8951,-97.1384");
        citiesbybb.put("Winnipeg","49.713770%2C-97.349222%2C49.993874%2C-96.955228");
    }
}
