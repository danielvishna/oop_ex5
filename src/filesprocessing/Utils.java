package filesprocessing;

import java.util.Arrays;
import java.util.List;

/**
 * service functions class of the package
 */
public class Utils {
	public static final double BytSize = 1024;

	/**
	 * func that split a given line
	 * @param line the line
	 * @return list of string after split all the #
	 */
	public static List<String> getFilterParameters(String line){
		return Arrays.asList(line.split("#"));

	}
}
