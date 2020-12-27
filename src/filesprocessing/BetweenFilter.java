package filesprocessing;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import static filesprocessing.Utils.BytSize;


public class BetweenFilter implements IFilter<String>{
	@Override
	public List<File> filterFiles(File dir, List<String> parameters) throws Warning {
		File [] files = dir.listFiles();
		double low = Double.parseDouble(parameters.get(0));
		double greater = Double.parseDouble(parameters.get(1));
		if (low < 0 || low > greater || greater < 0){
			throw new Warning();
		}
		String isNot;
		boolean result = false;
		LinkedList<File> fileLinkedList = new LinkedList<>();
		if (parameters.size() == 3) {
			isNot = parameters.get(2);
			if (isNot.equals("NOT")) {
				result = true;
			}
			else {
				throw new Warning();
			}
		}
		if (files == null)
			return fileLinkedList;
		for(File f: files) {
			if( f.isFile() && result && ((double) f.length() / BytSize < low ||
										 (double) f.length() / BytSize > greater )) {
				fileLinkedList.add(f);
			}
			else if (f.isFile() && !result && low <= (double) f.length() / BytSize &&
					 (double) f.length() / BytSize <= greater) {
				fileLinkedList.add(f);
			}

		}
		return fileLinkedList;
	}
}
