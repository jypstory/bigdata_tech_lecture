package skill.coach01;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class TestReducer extends Reducer<Text, Text, NullWritable, Text> {
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // Called once at the beginning of the task.
        Configuration conf = context.getConfiguration();
    }
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // This method is called once for each key.
        String grp = key.toString();
        Integer cnt = 0;
        for (Text value : values) {
            cnt += Integer.parseInt(value.toString());
        }
        context.write(NullWritable.get(), new Text(grp + " = " + cnt.toString()));
        context.getCounter("COUNTER", "REDUCER_NUM_RECORD").increment(1);
    }
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        // Called once at the end of the task.
    }
}