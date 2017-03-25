package skill.coach03;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
public class TestMapper extends Mapper<LongWritable, Text, Text, Text> {
    private Hashtable<String, String> districtInfo;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // Called once at the beginning of the task.
        Configuration conf = context.getConfiguration();
        Path cacheFile = new Path(context.getCacheFiles()[0]);
        FileSystem fs = FileSystem.get(conf);
        districtInfo = new Hashtable<String, String>();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(cacheFile)));
        while ((line = br.readLine()) != null) {
            String column[] = line.split(",");
            String code = column[0];
            String name = column[1];
            districtInfo.put(code, name);
        }
        br.close();
    }
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // Called once for each key/value pair in the input split.
        String column[] = value.toString().split(",");
        String local = column[0];
        String gender = column[1];
        local = districtInfo.get(local);
        if ("1".equals(gender)) {
            gender = "Male";
        } else if ("2".equals(gender)) {
            gender = "Female";
        } else {
            gender = "Unknown";
        }
        context.write(new Text(local + "," + gender), new Text("1"));
        context.getCounter("COUNTER", "MAPPER_NUM_RECORD").increment(1);
    }
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        // Called once at the end of the task.
    }
}