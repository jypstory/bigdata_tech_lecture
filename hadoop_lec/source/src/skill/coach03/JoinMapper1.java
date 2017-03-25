package skill.coach03;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class JoinMapper1 extends Mapper<LongWritable, Text, TextPair, TextPair> {
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // Called once at the beginning of the task.
        Configuration conf = context.getConfiguration();
    }
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // Called once for each key/value pair in the input split.
        String column[] = value.toString().split(",");
        String acc_idx = column[0];
        String local = column[12];
        if (acc_idx.equals("Accident_Index")) { // remove header
            context.getCounter("COUNTER", "SKIP_HEADER").increment(1);
            return;
        }
        context.write(new TextPair(acc_idx, "0"), new TextPair(local, "0"));
        context.getCounter("COUNTER", "MAPPER_NUM_RECORD_1").increment(1);
    }
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        // Called once at the end of the task.
    }
}