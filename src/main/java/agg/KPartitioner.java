package agg;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import java.util.*;

/**
 * Created by kevinsebastian on 4/29/17.
 */
public class KPartitioner implements Partitioner {

    int partition_size[];

    public KPartitioner()
    {
        partition_size = new int[]{0,0,0,0,0,0,0};

    }

    public int minval(int start,int end)
    {
        int minval = partition_size[start],index = start;
        for(int i = start;i<=end;i++){ if(partition_size[i] < minval){ minval = partition_size[i];index = i;}}
        return index;
    }

    public void configure(Map<String, ?> configs) { }

    public void close() { }

    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes,
                         Cluster cluster){
        //Does the partitioning logic
        int partition_id = 0;
        //for(int i = 0;i<7;i++){System.out.print(partition_size[i]+" ");}System.out.println();
        if(topic=="weather") {partition_id = minval(0,1);}
        if(topic=="air") { partition_id = minval(2,3) - 2;}
        if(topic=="traffic"){ partition_id = minval(4,6) - 4;}
        String valueStr = (String)value;
        partition_size[partition_id]+=valueStr.length();
        return partition_id;
    }
}