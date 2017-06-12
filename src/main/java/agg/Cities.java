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

        cities.add("Lisbon");
        getID.put("Lisbon",24);
        getLatLon.put("Lisbon","38.7223,-9.1393");
        citiesbybb.put("Lisbon","38.691399%2C-9.229836%2C38.795854%2C-9.090571");

        cities.add("Madrid");
        getID.put("Madrid",25);
        getLatLon.put("Madrid","40.4168,-3.7038");
        citiesbybb.put("Madrid","40.312064%2C-3.834162%2C40.563845%2C-3.524912");

        cities.add("Paris");
        getID.put("Paris",26);
        getLatLon.put("Paris","48.8566,2.3522");
        citiesbybb.put("Paris","48.815573%2C2.224199%2C48.902145%2C2.469921");

        cities.add("London");
        getID.put("London",27);
        getLatLon.put("London","51.5074,-0.1278");
        citiesbybb.put("London","51.384940%2C-0.351468%2C51.672343%2C0.148271");

        cities.add("Dublin");
        getID.put("Dublin",28);
        getLatLon.put("Dublin","53.3498,-6.2603");
        citiesbybb.put("Dublin","53.223430%2C-6.447485%2C53.425210%2C-6.043924");

        cities.add("Copenhagen");
        getID.put("Copenhagen",29);
        getLatLon.put("Copenhagen","55.6761,12.5683");
        citiesbybb.put("Copenhagen","55.615441%2C12.453382%2C55.727094%2C12.734265");

        cities.add("Amsterdam");
        getID.put("Amsterdam",30);
        getLatLon.put("Amsterdam","52.3702,4.8952");
        citiesbybb.put("Amsterdam","52.318274%2C4.728856%2C52.431157%2C5.068390");

        cities.add("Moscow");
        getID.put("Moscow",31);
        getLatLon.put("Moscow","55.7558,37.6173");
        citiesbybb.put("Moscow","55.489927%2C37.319329%2C56.009657%2C37.945661");

        cities.add("Istanbul");
        getID.put("Istanbul",32);
        getLatLon.put("Istanbul","41.0082,28.9784");
        citiesbybb.put("Istanbul","40.811404%2C28.595554%2C41.199239%2C29.428805");

        cities.add("Ankara");
        getID.put("Ankara",33);
        getLatLon.put("Ankara","39.9334,32.8597");
        citiesbybb.put("Ankara","39.730421%2C32.518474%2C40.076332%2C33.007056");

        cities.add("Dubai");
        getID.put("Dubai",34);
        getLatLon.put("Dubai","25.2048,55.2708");
        citiesbybb.put("Dubai","24.792136%2C54.890454%2C25.358561%2C55.565039");

        cities.add("Chennai");
        getID.put("Chennai",35);
        getLatLon.put("Chennai","13.0827,80.2707");
        citiesbybb.put("Chennai","12.833885%2C80.081701%2C13.261166%2C80.336323");

        cities.add("New%20Delhi");
        getID.put("New%20Delhi",36);
        getLatLon.put("New%20Delhi","28.6139,77.2090");
        citiesbybb.put("New%20Delhi","28.404100%2C77.073010%2C28.650480%2C77.344960");

        cities.add("Beijing");
        getID.put("Beijing",37);
        getLatLon.put("Beijing","39.9042,116.407");
        citiesbybb.put("Beijing","39.442758%2C115.423411%2C41.060816%2C117.514625");

        cities.add("Tokyo");
        getID.put("Tokyo",38);
        getLatLon.put("Tokyo","35.6895,139.6917");
        citiesbybb.put("Tokyo","35.469356%2C139.448945%2C35.642820%2C139.835906");

        cities.add("Hong%20Kong");
        getID.put("Hong%20Kong",39);
        getLatLon.put("Hong%20Kong","22.3964,114.10957");
        citiesbybb.put("Hong%20Kong","22.143500%2C113.825900%2C22.561968%2C114.429500");

        cities.add("Singapore");
        getID.put("Singapore",40);
        getLatLon.put("Singapore","1.3521,103.8198");
        citiesbybb.put("Singapore","1.149600%2C103.594000%2C1.478400%2C104.094500");

        cities.add("Mumbai");
        getID.put("Mumbai",41);
        getLatLon.put("Mumbai","19.0760,72.8777");
        citiesbybb.put("Mumbai","18.892868%2C72.775873%2C19.271634%2C72.986499");

        cities.add("Darwin");
        getID.put("Darwin",42);
        getLatLon.put("Darwin","-12.4634,130.8456");
        citiesbybb.put("Darwin","-12.5217418%2C130.815117%2C-12.330060%2C131.051500");

        cities.add("Perth");
        getID.put("Perth",43);
        getLatLon.put("Perth","-31.9505,115.8605");
        citiesbybb.put("Perth","-32.455642%2C115.684048%2C-31.624485%2C116.239023");

        cities.add("Melbourne");
        getID.put("Melbourne",44);
        getLatLon.put("Melbourne","-37.8136,144.9631");
        citiesbybb.put("Melbourne","-38.433859%2C144.593742%2C-37.511274%2C145.512529");

        cities.add("Auckland");
        getID.put("Auckland",45);
        getLatLon.put("Auckland","-36.8485,174.7633");
        citiesbybb.put("Auckland","-37.065475%2C174.443802%2C-36.660571%2C175.287137");

        cities.add("Auckland");
        getID.put("Auckland",46);
        getLatLon.put("Auckland","-36.8485,174.7633");
        citiesbybb.put("Auckland","-37.065475%2C174.443802%2C-36.660571%2C175.287137");
    }
}
