package app.mapReduce;

/**
 * Created by sbr on 5/3/17.
 */
public class WorkerNode {
    public static void main(String args[]) throws InterruptedException {
        MonitorThread monitor = new MonitorThread();
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();
        monitorThread.join();
        System.out.println("Worker Shutting down");
    }
}
