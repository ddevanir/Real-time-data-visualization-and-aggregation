package Master;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sbr on 5/2/17.
 */
public class MonitorThread implements Runnable{

    private static final Logger logger = LogManager.getLogger("MonitorThread");
    private Double MIN_RATE = 50.0;
    private Double MAX_RATE = 100.0;
    private Integer WINDOW_SIZE = 5;
    private Integer TIME = 10; // in  seconds
    private HashMap<Socket, ArrayList<Integer>> workerLoads = new HashMap<Socket,ArrayList<Integer>>();
    private enum ACTION {
            NO_ACTION ,
            KILL_NODE,
            CREATE_NEW_NODE
    }

    private List<Process> pro = new ArrayList<Process>();

    private void ReqLoadRate(Socket socket) throws IOException {
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter outToClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        String msg = "Send load rate\n";
        outToClient.print(msg);
        outToClient.flush();
        logger.debug("Sending load req to " + socket.getRemoteSocketAddress().toString());
        String response = inFromClient.readLine();
        logger.debug("Received load from " +socket.getRemoteSocketAddress().toString()+ "-" + response);

        ArrayList<Integer> load = workerLoads.get(socket);
        if(load == null)
        {
            load = new ArrayList<Integer>();
            workerLoads.put(socket,load);
        }
        if(load.size() < WINDOW_SIZE){
            load.add(Integer.valueOf(response));
        }else{
            load.remove(0);
            load.add(Integer.valueOf(response));
        }
        workerLoads.put(socket,load);

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
        logger.debug("Creating New node for topic : " + topic);
//        Process p = Runtime.getRuntime().exec("cd target/classes/app/mapReduce\njava WorkerNode\n");
//        pro.add(p);
        //runProcess("export PATH=$PATH:/Users/sbr/Downloads/apache-maven-3.5.0/bin");
        Runtime.getRuntime().exec("mvn exec:java -Dexec.mainClass=\"app.mapReduce.WorkerNode\"");
        //runProcess("mvn exec:java -Dexec.mainClass=\"app.mapReduce.WorkerNode\"");


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

    private void KillNode(Socket socket) throws IOException {
        logger.debug("Killing Node : " + socket.getRemoteSocketAddress().toString());
        PrintWriter outToClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        String msg = "Shutdown\n";
        outToClient.print(msg);
        outToClient.flush();
        workerLoads.remove(socket);
        socket.close();
    }

    @Override
    public void run() {
        try {
            CreateNewNode("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true){
            Map<String,TCPConnection> clientMap = MasterNode.clientMap;
            for (Map.Entry<String, TCPConnection> entry : clientMap.entrySet()){
                Socket socket = entry.getValue().getSocket();
                if(socket.isConnected() == false){
                    workerLoads.remove(socket);
                    continue;
                }
                try {
                    ReqLoadRate(socket);
                    ACTION action = CheckLoadRate(socket);
                    if(action == ACTION.NO_ACTION)
                        logger.debug("Take No Action" + socket.getRemoteSocketAddress().toString());
                    else if(action == ACTION.CREATE_NEW_NODE)
                        CreateNewNode("");
                    else if(action == ACTION.KILL_NODE)
                        KillNode(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(TIME*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
