package skill.coach03;

import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.Writable;
public class TextPairPartitioner extends Partitioner<TextPair, Writable> {
    public int getPartition(TextPair key, Writable value, int numPartitions) {
        return (key.getFirst().hashCode() & Integer.MAX_VALUE) % numPartitions;
    }
}