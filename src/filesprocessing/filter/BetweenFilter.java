package filesprocessing.filter;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import static filesprocessing.Utils.BytSize;

/**
 * Filter all the files in the given dir that there size are between to numbers: the smaller number and the
 * greater number
 */
public class BetweenFilter implements IFilter<String>{
	static final int BAD_BIG_SIZE_PARM = 3;
	static final int BAD_SMALL_SIZE_PARM = 2;
	static final int SIZE_PARM_WITH_NOT = 3;
	static final int FIRST_PARM = 0;
	static final int SEC_PARM = 1;
	static final int THERE_PARM = 2;
	static final double BAD_NAM_BOND = 0;

	@Override
	public List<File> filterFiles(File dir, List<String> parameters) throws FilterWarning {
		File [] files = dir.listFiles();
		if(parameters.size() > BAD_BIG_SIZE_PARM || parameters.size() < BAD_SMALL_SIZE_PARM){
			throw new FilterWarning();
		}
		double low = Double.parseDouble(parameters.get(FIRST_PARM));
		double greater = Double.parseDouble(parameters.get(SEC_PARM));
		if (low < BAD_NAM_BOND || low > greater || greater < BAD_NAM_BOND){
			throw new FilterWarning();
		}
		String isNot;
		boolean result = false;
		LinkedList<File> fileLinkedList = new LinkedList<>();
		if (parameters.size() == SIZE_PARM_WITH_NOT) {
			isNot = parameters.get(THERE_PARM);
			if (isNot.equals("NOT")) {
				result = true;
			}
			else {
				throw new FilterWarning();
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
