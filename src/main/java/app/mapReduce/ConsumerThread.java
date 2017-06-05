package app.mapReduce;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

/**
 * Created by beep on 4/29/17.
 */
public class ConsumerThread implements Runnable{
    private KafkaStream m_stream;
    private int m_threadNumber;
    private String m_topic;
    ESDriver m_ESDriver;
    private  LoadStats stats;

    public ConsumerThread(KafkaStream m_stream, int m_threadNumber, String topic, LoadStats stats) {
        this.m_stream = m_stream;
        this.m_threadNumber = m_threadNumber;
        this.m_topic = topic;
        this.stats = stats;
        try {
            m_ESDriver = new ESDriver();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Thread starting" + m_threadNumber );
            ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
            while(stats.getShutdown() == false) {
                while (it.hasNext()) {
                    String msg = new String(it.next().message());
                    System.out.println("Thread " + m_threadNumber + ": " + msg);
                    stats.increment();

                        try {
                            if(m_topic.equals("newtraffic")) {
                                m_ESDriver.InsertTrafficJSON(new JSONObject(msg));
                            }
                            else{
                                m_ESDriver.InsertAirWeatherJSON(new JSONObject(msg));
                            }
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            }
        }
    }