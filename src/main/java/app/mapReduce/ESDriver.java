package app.mapReduce;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.elasticsearch.client.transport.TransportClient;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

/**
 * Created by sbr on 4/26/17.
 */

public class ESDriver {
   private TransportClient client;
   private String esURL = "https://c18a49f9375e6eb9d592e2c47f8d67bf.us-east-1.aws.found.io";
   private String trafficIndex;
   private String trafficIndexype;
   private String airWeatherIDx;
   private String airWeatherIdxType;
   private CloseableHttpClient httpclient = HttpClients.createDefault();

    private String baseURL = "https://2fe405d238cb5f93c1de41de0a2531a1.us-east-1.aws.found.io:9243";
    private String username = "elastic";
    private String password = "ZvXhxvOxMmszYfUxMynvLTj2";
    HttpURLConnection connection;

    private URLConnection setUsernamePassword(URL url) throws IOException {
        URLConnection urlConnection = url.openConnection();
        String authString = username + ":" + password;
        String authStringEnc = new String(Base64.encodeBase64(authString.getBytes()));
        urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
        return urlConnection;
    }

    public ESDriver() throws IOException {
//        Settings settings = Settings.builder()
//                .put("cluster.name", "c18a49f9375e6eb9d592e2c47f8d67bf").build();
//        client = new PreBuiltTransportClient(Settings.EMPTY)
//               // .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(esURL), 9243));
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        trafficIndex = "traffic";
        trafficIndexype = "incidents";
        airWeatherIDx = "airweather";
        airWeatherIdxType = "airweathertype";
    }

    public void InsertAirWeatherJSON(JSONObject object) throws IOException {
        URL url = new URL(this.baseURL + "/" + this.airWeatherIDx + "/" + this.airWeatherIdxType);
        String authStr = username + ":" + password;
        String authStringEnc = new String(Base64.encodeBase64(authStr.getBytes()));


        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Basic " + authStringEnc);
        connection.connect();
        OutputStream os = connection.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");

        osw.write(object.toString());
        osw.flush();
        osw.close();

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        System.out.println("" + sb.toString());



//        HttpPost httpPost = new HttpPost(esURL +":" + String.valueOf(9243)+ "/" + airWeatherIDx + "/" + airWeatherIdxType );
////        List<NameValuePair> params = new ArrayList<NameValuePair>(object);
////        params.add(new BasicNameValuePair("client_context_id",clientID ));
//        StringEntity params = new StringEntity(object.toString());
//        httpPost.setEntity(params);
//        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
//        CloseableHttpResponse response = httpclient.execute(httpPost);
//        int a=1;
//        DefaultHttpClient httpclient = new DefaultHttpClient();
//
//        httpclient.getCredentialsProvider().setCredentials(
//                new AuthScope(esURL, 9243),
//                new UsernamePasswordCredentials("elastic", "hXocTG6fZAukJD6HubB4fZbU"));
//
//        HttpPost httpPost = new HttpPost(esURL +":" + String.valueOf(9243)+ "/" + airWeatherIDx + "/" + airWeatherIdxType );
//        System.out.println("executing request " + httpPost.getRequestLine());
//        HttpResponse response2;
//        response2 = httpclient.execute(httpPost);
//        int a=1;



//        IndexResponse response = client.prepareIndex(airWeatherIDx, airWeatherIdxType)
//                .setSource(object.toMap())
//                .get();
    }

    public void InsertTrafficJSON(JSONObject object) throws ExecutionException, InterruptedException, IOException {
//        IndexRequest indexRequest = new IndexRequest(trafficIndex,trafficIndexype, String.valueOf(object.getLong("id")))
//                .source(object.toMap());
//        UpdateRequest updateRequest = new UpdateRequest(trafficIndex,trafficIndexype, String.valueOf(object.getLong("id")))
//
//                .upsert(indexRequest);
//        UpdateResponse response = client.update(updateRequest).get();
//        IndexResponse response = client.prepareIndex(trafficIndex,trafficIndexype, String.valueOf(object.getLong("id")))
//                .setSource(object.toMap())
//                .get();


        URL url = new URL(this.baseURL + "/" + this.trafficIndex + "/" + this.trafficIndexype + "/" + String.valueOf(object.getLong("id")));
        String authStr = username + ":" + password;
        String authStringEnc = new String(Base64.encodeBase64(authStr.getBytes()));


        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Basic " + authStringEnc);
        connection.connect();
        OutputStream os = connection.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");

        osw.write(object.toString());
        osw.flush();
        osw.close();

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        System.out.println("" + sb.toString());

    }

}
