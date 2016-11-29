import org.apache.hadoop.io.Text;

public class ResultPair implements Comparable<ResultPair>  {
	

	double relFreq;
	String key;
	String value;

	ResultPair(double relFreq, String key, String value) {
		this.relFreq = relFreq;
		this.key = key;
		this.value = value;
	}

	@Override
	public int compareTo(ResultPair resultPair) {
		if (this.relFreq <= resultPair.relFreq) {
			return 1;
		} else {
			return -1;
		}
	}
}
