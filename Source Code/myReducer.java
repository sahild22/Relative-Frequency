import java.io.IOException;
import java.util.TreeSet;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class myReducer extends Reducer<Text, LongWritable,Text, Text>{
	
	DoubleWritable count = new DoubleWritable();
	DoubleWritable relCount = new DoubleWritable();
	Text word = new Text("");
	TreeSet<ResultPair> sortedOutput_temp = new TreeSet<>();
	TreeSet<ResultPair> sortedOutput = new TreeSet<>();
	
	public TreeSet<ResultPair> getSortedOutput() {
		return sortedOutput;
	}

	public void setSortedOutput(TreeSet<ResultPair> sortedOutput) {
		this.sortedOutput = sortedOutput;
	}
	
	@Override
	protected void reduce(Text key, Iterable<LongWritable> value,
			Reducer<Text, LongWritable, Text, Text>.Context con) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
	
		String[] keyy = key.toString().split(" ");
		if(keyy[1].equals("*")){
			if(keyy[0].equals(word.toString())){
				count.set(count.get() + getCount(value));
			}
			else{
				word.set(keyy[0]);
				count.set(0);
				count.set(getCount(value));
			}
		}
		
		else{
			double cnt = getCount(value);
			relCount.set((double)cnt / count.get());
			Double rel = relCount.get();
			sortedOutput_temp.add(new ResultPair(rel, key.toString(), word.toString()));
			if (sortedOutput_temp.size() > 100) {
				sortedOutput_temp.pollLast();
			}
        }
    }
	
	private double getCount(Iterable<LongWritable> values) {
		double count = 0;
		for (LongWritable value : values) {
			count += value.get();
		}
		return count;
	}
	
	protected void cleanup(Context context)
            throws IOException,
            InterruptedException {
		while(!sortedOutput_temp.isEmpty()){
			ResultPair p1= sortedOutput_temp.pollFirst();
			context.write(new Text(p1.key+" / "+pi.key.split(" ")[0] + "="), new Text(Double.toString(p1.relFreq)));
		}
	}
	
	
	    
}
