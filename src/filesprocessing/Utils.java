package filesprocessing;

import java.util.Arrays;
import java.util.List;

public class Utils {
	public static List<String> getFilterParameters(String line){
		return Arrays.asList(line.split("#"));

	}
}
