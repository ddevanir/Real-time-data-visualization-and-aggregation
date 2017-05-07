package app.mapReduce;

import agg.Cities;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import static agg.Cities.cities;

/**
 * Created by beep on 4/29/17.
 */
public class ConsumerThread implements Runnable{
    private KafkaStream m_stream;
    private int m_threadNumber;

    public ConsumerThread(KafkaStream m_stream, int m_threadNumber) {
        this.m_stream = m_stream;
        this.m_threadNumber = m_threadNumber;
    }

    @Override
    public void run() {
        System.out.println("Thread starting" + m_threadNumber );
            ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
            while(true) {
                while (it.hasNext()) {
                    System.out.println("Thread " + m_threadNumber + ": " + new String(it.next().message()));
                }
        }
    }
}