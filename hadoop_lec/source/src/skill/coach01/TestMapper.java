package skill.coach01;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class TestMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // Called once at the beginning of the task.
        Configuration conf = context.getConfiguration();
    }
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // Called once for each key/value pair in the input split.
        String[] input = value.toString().split(",");
        if (input[0].equals("Accident_Index")) { // remove header
            context.getCounter("COUNTER", "SKIP_HEADER").increment(1);
            return;
        }
        String year = input[9].substring(6);
        String cond = input[25];
        if (cond.equals("-1")) { // skip invalid status
            context.getCounter("COUNTER", "INVALID_STATUS").increment(1);
            return;
        }
        context.write(new Text(year + ":" + cond), new Text("1"));
    }
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        // Called once at the end of the task.
    }
}
