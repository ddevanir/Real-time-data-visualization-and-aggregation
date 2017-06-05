package Master;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sbr on 4/29/17.
 */
public class MasterNode {
    public static Map<String, TCPConnection> trafficMap;
    public static Map<String, TCPConnection> airWeatherMap;
    private static final Logger logger = LogManager.getLogger("MasterNode");
    private static String getTopic(Socket socket) throws IOException {
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter outToClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        String msg = "Send topic\n";
        outToClient.print(msg);
        outToClient.flush();
        String response = inFromClient.readLine();
        return response;
    }
    public static void main(String[] args) throws Exception {
        //PropertyConfigurator.configure("/Users/sbr/IdeaProjects/data-agg-vis/src/main/resources/log4j2.properties");
        logger.debug("Master Node is running.");
        int clientNumber = 0;
        trafficMap = Collections.synchronizedMap(new HashMap<String,TCPConnection>());
        airWeatherMap = Collections.synchronizedMap(new HashMap<String,TCPConnection>());
        MonitorThread monitor = new MonitorThread();
        Thread monitorTHread = new Thread(monitor);
        monitorTHread.start();
        ServerSocket listener = new ServerSocket(9898);

        try {
            while (true) {
                Socket socket = listener.accept();
                //socket.g
                String clientIP = socket.getRemoteSocketAddress().toString();
                String topic = getTopic(socket);
                TCPConnection tcp = new TCPConnection(socket, clientNumber++);
                if(topic.equals("newtraffic")) {
                    trafficMap.put(clientIP, tcp);
                }
                else{
                    airWeatherMap.put(clientIP,tcp);
                }
                logger.debug("Accepted connection from : " + socket.getRemoteSocketAddress());
//                SocketAddress socketAddress = socket.getRemoteSocketAddress();
//                if (socketAddress instanceof InetSocketAddress) {
//                    InetAddress inetAddress = ((InetSocketAddress)socketAddress).getAddress();
//                    if (inetAddress instanceof Inet4Address)
//                        System.out.println("IPv4: " + inetAddress);
//                    else if (inetAddress instanceof Inet6Address)
//                        System.out.println("IPv6: " + inetAddress);
//                    else
//                        System.err.println("Not an IP address.");
//                } else {
//                    System.err.println("Not an internet protocol socket.");
//                }



//                Thread t = new Thread(tcp);
//                t.start();

            }
        } finally {
            listener.close();
        }
    }



}
