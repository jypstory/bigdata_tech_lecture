package skill.coach03;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
public class TestDriver extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        mapreduce01(conf);
        mapreduce02(conf);
        return 0;
    }
    public void mapreduce01(Configuration conf) throws Exception {
        Job job = Job.getInstance(conf, "Test_Join_01");
        job.setJarByClass(TestDriver.class);
        FileOutputFormat.setOutputPath(job, new Path(conf.get("tempPath")));
        MultipleInputs.addInputPath(job, new Path(conf.get("inputPath1")), TextInputFormat.class, JoinMapper1.class);
        MultipleInputs.addInputPath(job, new Path(conf.get("inputPath2")), TextInputFormat.class, JoinMapper2.class);
        job.setMapOutputKeyClass(TextPair.class);
        job.setMapOutputValueClass(TextPair.class);
        job.setReducerClass(JoinReducer.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setPartitionerClass(TextPairPartitioner.class);
        job.setGroupingComparatorClass(TextPair.FirstComparator.class);
        job.setNumReduceTasks(conf.getInt("numReduceTasks", 1));
        if (!job.waitForCompletion(true)) {
            throw new Exception("MapReduce Faild..");
        }
    }
    public void mapreduce02(Configuration conf) throws Exception {
        Job job = Job.getInstance(conf, "Test_Join_02");
        job.setJarByClass(TestDriver.class);
        job.addCacheFile(new Path(conf.get("cachePath")).toUri());
        FileInputFormat.addInputPath(job, new Path(conf.get("tempPath")));
        FileOutputFormat.setOutputPath(job, new Path(conf.get("outputPath")));
        job.setMapperClass(TestMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setReducerClass(TestReducer.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setNumReduceTasks(conf.getInt("numReduceTasks", 1));
        if (!job.waitForCompletion(true)) {
            throw new Exception("MapReduce Faild..");
        }
    }
    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new TestDriver(), args);
        System.exit(res);
    }
}