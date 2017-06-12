package Master;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.time.Instant;
import java.util.*;

/**
 * Created by sbr on 5/2/17.
 */
public class MonitorThread implements Runnable{

    private static final Logger logger = LogManager.getLogger("MonitorThread");
    private Double MIN_RATE = 50.0;
    private Double MAX_RATE = 100.0;
    private Integer WINDOW_SIZE = 5;
    private Integer TIME = 10; // in  seconds
    private Integer WAIT_BEFORE_CREATION = WINDOW_SIZE * TIME / 2;
    private long lastCreationTime = 0;
    private HashMap<Socket, ArrayList<Integer>> workerLoads = new HashMap<Socket,ArrayList<Integer>>();
    private enum ACTION {
            NO_ACTION ,
            KILL_NODE,
            CREATE_NEW_NODE
    }

    private List<Process> pro = new ArrayList<Process>();

    private void ReqLoadRate(Socket socket) throws IOException {
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outToClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String msg = "Send load rate\n";
            outToClient.print(msg);
            outToClient.flush();
            logger.debug("Sending load req to " + socket.getRemoteSocketAddress().toString());
            String response = inFromClient.readLine();
            logger.debug("Received load from " + socket.getRemoteSocketAddress().toString() + "-" + response);

            ArrayList<Integer> load = workerLoads.get(socket);
            if (load == null) {
                load = new ArrayList<Integer>();
                workerLoads.put(socket, load);
            }
            if (load.size() < WINDOW_SIZE) {
                load.add(Integer.valueOf(response));
            } else {
                load.remove(0);
                load.add(Integer.valueOf(response));
            }
            workerLoads.put(socket, load);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private ACTION CheckLoadRate(Socket socket){
        ArrayList<Integer> load = workerLoads.get(socket);
        if(load.size() < WINDOW_SIZE) return ACTION.NO_ACTION;
        Integer sum=0;
        for(Integer num : load){
            sum+=num;
        }
        Double avg = (double)sum/WINDOW_SIZE;
        if(avg < MIN_RATE) return ACTION.KILL_NODE;
        if(avg > MAX_RATE) return ACTION.CREATE_NEW_NODE;
        return ACTION.NO_ACTION;
    }

    private void CreateNewNode (String topic) throws Exception {
        long curTime = Instant.now().toEpochMilli() / 1000L;
       if(lastCreationTime == 0 || (curTime - lastCreationTime) > WAIT_BEFORE_CREATION) {
           logger.debug("Creating New node for topic : " + topic);
//        Process p = Runtime.getRuntime().exec("cd target/classes/app/mapReduce\njava WorkerNode\n");
//        pro.add(p);
           //runProcess("export PATH=$PATH:/Users/sbr/Downloads/apache-maven-3.5.0/bin");
           Runtime.getRuntime().exec("mvn exec:java -Dexec.mainClass=\"app.mapReduce.WorkerNode\" " + "-Dexec.args=\"" +topic+ "\"");
           //runProcess("mvn exec:java -Dexec.mainClass=\"app.mapReduce.WorkerNode\"");
           lastCreationTime = curTime;
       }
       else{
           logger.debug("Wait to create new node");
       }


    }

    private static void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(command + " stdout:", pro.getInputStream());
        printLines(command + " stderr:", pro.getErrorStream());
        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
    }
    private static void printLines(String cmd, InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(cmd + " " + line);
        }
    }

    private void KillNode(Socket socket, String topic,Iterator iterator) throws IOException {
        Map<String, TCPConnection> clientMap;
        if (topic.equals("newtraffic")){
            clientMap = MasterNode.trafficMap;
        }
        else {
            clientMap = MasterNode.airWeatherMap;
        }
        if(clientMap.size() > 1) {
            logger.debug("Killing Node : " + socket.getRemoteSocketAddress().toString());
            PrintWriter outToClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String msg = "Shutdown\n";
            outToClient.print(msg);
            outToClient.flush();
            workerLoads.remove(socket);
//            if (topic.equals("newtraffic")){
//                MasterNode.trafficMap.remove(socket.getRemoteSocketAddress().toString());
//            }
//            else {
//                MasterNode.airWeatherMap.remove(socket.getRemoteSocketAddress().toString());
//            }
            iterator.remove();
            socket.close();
        }
    }

    @Override
    public void run() {

        while(true){
            try{
            for(int i = 0 ; i < 2 ; i++) {
                Map<String, TCPConnection> clientMap;
                String topic;
                Iterator it;
                if (i == 0) {
                    clientMap = MasterNode.trafficMap;
                    topic = "newtraffic";
                   it = clientMap.entrySet().iterator();
                } else {
                    clientMap = MasterNode.airWeatherMap;
                    topic = "newweather";
                    it = clientMap.entrySet().iterator();
                }

                while (it.hasNext())  {
                    Map.Entry<String, TCPConnection> pair = (Map.Entry<String, TCPConnection>)it.next();
                    Socket socket = pair.getValue().getSocket();
                    if (socket.isConnected() == false || socket.isClosed() == true) {
                        workerLoads.remove(socket);
                        if (topic.equals("newtraffic")) {
                            MasterNode.trafficMap.remove(socket.getRemoteSocketAddress().toString());
                        } else {
                            MasterNode.airWeatherMap.remove(socket.getRemoteSocketAddress().toString());
                        }
                        continue;
                    }

                    ReqLoadRate(socket);
                    ACTION action = CheckLoadRate(socket);
                    if (action == ACTION.NO_ACTION)
                        logger.debug("Take No Action" + socket.getRemoteSocketAddress().toString());
                    else if (action == ACTION.CREATE_NEW_NODE) {
                        //CreateNewNode(topic);
                    } else if (action == ACTION.KILL_NODE) {
                        //KillNode(socket, topic,it);
                    }
                }

                Thread.sleep(TIME * 1000);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
