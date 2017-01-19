import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeSet;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class myDriver {	
	public static void main(String[] args)throws Exception{
		Configuration conf = new Configuration();
        	Job job = new Job(conf, "Word Count");
        	job.setJarByClass(myDriver.class);
        	job.setMapperClass(myMapper.class);
        	job.setReducerClass(myReducer.class);
        	job.setMapOutputKeyClass(Text.class);
        	job.setMapOutputValueClass(LongWritable.class);
        	job.setOutputKeyClass(Text.class);
        	job.setOutputValueClass(Text.class);
        	FileInputFormat.setInputPaths(job, new Path(args[0]));
        	FileOutputFormat.setOutputPath(job, new Path(args[1]));
       	 	job.waitForCompletion(true);
    	}
}
