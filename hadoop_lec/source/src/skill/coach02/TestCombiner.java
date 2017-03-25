package skill.coach02;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class TestCombiner extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // Called once at the beginning of the task.
        Configuration conf = context.getConfiguration();
    }
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // This method is called once for each key.
        Integer cnt = 0;
        for (Text value : values) {
            cnt += Integer.parseInt(value.toString());
        }
        context.write(key, new Text(cnt.toString()));
        context.getCounter("COUNTER", "COMBINER_NUM_RECORD").increment(1);
    }
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        // Called once at the end of the task.
    }
}