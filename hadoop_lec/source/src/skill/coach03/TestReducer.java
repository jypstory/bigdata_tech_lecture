package skill.coach03;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class TestReducer extends Reducer<Text, Text, NullWritable, Text> {
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // Called once at the start of the task.
        Configuration conf = context.getConfiguration();
    }
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // This method is called once for each key.
        Integer count = 0;
        for (Text value : values) {
            count++;
        }
        context.write(NullWritable.get(), new Text(key.toString() + " : " + count.toString()));
    }
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        // Called once at the end of the task.
    }
}