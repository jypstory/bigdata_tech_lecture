package hdfs;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsTest {

	public static void main(String[] args) {
		try {
			Configuration conf = new Configuration();
			FileSystem hdfs = FileSystem.get(conf);
			Path inputPath = new Path("/user/hdfs/input/acc/Accidents_2005_2015.csv");
			Path outputPath = new Path("/user/hdfs/input/acc/Accidents_2005_2015_sun.csv");
			FSDataOutputStream outStream = hdfs.create(outputPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(hdfs.open(inputPath)));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(",");
				if (fields[10].equals("1")) {
					outStream.writeUTF(line + "\n");
				}
			}
			outStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
