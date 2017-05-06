package Master;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static scala.collection.concurrent.Debug.log;

/**
 * Created by sbr on 4/29/17.
 */
public class TCPConnection implements Runnable {
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    private Socket socket;
    private int clientNumber;

    public TCPConnection(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
    }

    /**
     * Services this thread's client by first sending the
     * client a welcome message then repeatedly reading strings
     * and sending back the capitalized version of the string.
     */

    public void run() {
        try {

            // Decorate the streams so we can send characters
            // and not just bytes.  Ensure output is flushed
            // after every newline.
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send a welcome message to the client.
            out.println("Hello, you are client #" + clientNumber + ".");
            out.println("Enter a line with only a period to quit\n");

            // Get messages from the client, line by line; return them
            // capitalized
            while (true) {
                String input = in.readLine();
                if (input == null || input.equals(".")) {
                    break;
                }
                out.println(input.toUpperCase());
            }
        } catch (IOException e) {
            log("Error handling client# " + clientNumber + ": " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                log("Couldn't close a socket, what's going on?");
            }
            log("Connection with client# " + clientNumber + " closed");
        }
    }
}
