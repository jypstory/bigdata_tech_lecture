package skill.coach03;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class JoinReducer extends Reducer<TextPair, TextPair, NullWritable, Text> {
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // Called once at the start of the task.
        Configuration conf = context.getConfiguration();
    }
    @Override
    protected void reduce(TextPair key, Iterable<TextPair> values, Context context) throws IOException, InterruptedException {
        // This method is called once for each key.
        String local = null;
        String gender = null;
        String outputValue = null;
        for (TextPair value : values) {
            if ("0".equals(value.getSecond().toString())) {
                local = value.getFirst().toString();
            } else {
                if (local != null) {
                    gender = value.getFirst().toString();
                    outputValue = local + "," + gender;
                    context.write(NullWritable.get(), new Text(outputValue));
                    context.getCounter("COUNTER", "REDUCER_NUM_RECORD").increment(1);
                } else {
                    return;
                }
            }
        }
    }
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        // Called once at the end of the task.
    }
}