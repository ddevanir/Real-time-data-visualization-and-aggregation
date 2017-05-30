package app.mapReduce;

/**
 * Created by sbr on 5/3/17.
 */
public class WorkerNode {
    public static void main(String args[]) throws InterruptedException {
        MonitorThread monitor = new MonitorThread();
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();

        String zooKeeper = "localhost:2181";
        String groupId = "group3";
        String topic = args[0];
        int threads = Integer.parseInt("1");

        ConsumerGroup obj = new ConsumerGroup(zooKeeper, groupId, topic);
        obj.run(threads);
        monitorThread.join();
        System.out.println("Worker Shutting down");
    }
}
