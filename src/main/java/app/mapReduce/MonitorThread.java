package app.mapReduce;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;

/**
 * Created by sbr on 5/3/17.
 */
public class MonitorThread implements Runnable{
    private boolean bRun = true;
    private Socket socket = null;
    private static final Logger logger = LogManager.getLogger("MonitorThread");
    private LoadStats stats;

    public MonitorThread( LoadStats stats){
        this.stats = stats;
    }

    private void HandleServerMsg(String msg) throws IOException {
        logger.debug("Received msg :" + msg);
        if (msg.equals("Send load rate")) {
            PrintWriter outToServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            outToServer.print(stats.getLoadrate() + "\n");
            outToServer.flush();

        } else if (msg.equals("Shutdown")) {
            stats.setShutdown();
            bRun = false;

        }
        else if(msg.equals("Send topic")){
            PrintWriter outToServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            outToServer.print(WorkerNode.topic + "\n");
            outToServer.flush();
        }

    }
    @Override
    public void run() {

        BufferedReader inFromServer = null;
        String serverMsg = null;
        try {
            socket = new Socket("localhost", 9898);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(bRun){
            try {
                inFromServer  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                serverMsg = inFromServer.readLine();
                HandleServerMsg(serverMsg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
