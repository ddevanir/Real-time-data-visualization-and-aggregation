package Master;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sbr on 4/29/17.
 */
public class MasterNode {
    public static ConcurrentHashMap<String, TCPConnection> clientMap;
    private static final Logger logger = LogManager.getLogger("MasterNode");
    public static void main(String[] args) throws Exception {
        //PropertyConfigurator.configure("/Users/sbr/IdeaProjects/data-agg-vis/src/main/resources/log4j2.properties");
        logger.debug("Master Node is running.");
        int clientNumber = 0;
        clientMap = new ConcurrentHashMap<String,TCPConnection>();
        MonitorThread monitor = new MonitorThread();
        Thread monitorTHread = new Thread(monitor);
        monitorTHread.start();
        ServerSocket listener = new ServerSocket(9898);

        try {
            while (true) {
                Socket socket = listener.accept();
                //socket.g
                String clientIP = socket.getRemoteSocketAddress().toString();
                TCPConnection tcp = new TCPConnection(socket, clientNumber++);
                clientMap.put(clientIP,tcp);
                logger.debug("Accepted connection from : " + socket.getRemoteSocketAddress());
                clientMap.put(clientIP,tcp);
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
