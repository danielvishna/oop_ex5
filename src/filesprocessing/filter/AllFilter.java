package filesprocessing.filter;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Filter that return all the files in the give dir or noting
 */
public class AllFilter implements IFilter<String>{
	static final int BAD_BIG_SIZE_PARM = 2;
	static final int SIZE_PARM_WITH_NOT = 1;
	static final int FIRST_PARM = 0;

	@Override
	public List<File> filterFiles(File dir, List<String> parameters) throws FilterWarning {
		File [] files = dir.listFiles();
		String isNot;
		boolean result = false;
		if (parameters.size() > BAD_BIG_SIZE_PARM)
		{
			throw new FilterWarning();
		}
		if (parameters.size() == SIZE_PARM_WITH_NOT) {
			isNot = parameters.get(FIRST_PARM);
			if (isNot.equals("NOT")) {
				result = true;
			}
			else{
				throw new  FilterWarning();
			}
		}
		LinkedList<File> fileLinkedList = new LinkedList<>();
		if(!result) {

			if (files == null)
				return fileLinkedList;
			for (File f : files) {
				if (f.isFile()) {
					fileLinkedList.add(f);
				}
			}
		}
		return fileLinkedList;
	}
}
