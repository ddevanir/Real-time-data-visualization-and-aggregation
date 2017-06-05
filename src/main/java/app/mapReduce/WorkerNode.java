package app.mapReduce;

/**
 * Created by sbr on 5/3/17.
 */
public class WorkerNode {
    public static String topic;
    public static void main(String args[]) throws InterruptedException {
        LoadStats stats = new LoadStats();
        MonitorThread monitor = new MonitorThread(stats);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();

        String zooKeeper = "localhost:2181";
        String groupId = "group3";
        String topic = WorkerNode.topic = args[0];
        int threads = Integer.parseInt("1");

        ConsumerGroup obj = new ConsumerGroup(zooKeeper, groupId, topic,stats);
        obj.run(threads);
        monitorThread.join();
        obj.shutdown();
        System.out.println("Worker Shutting down");
    }
}
