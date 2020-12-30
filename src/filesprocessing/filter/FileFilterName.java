package filesprocessing.filter;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Filter all the files that have exactly a specific name
 */
public class FileFilterName implements IFilter<String> {
	static final int BAD_BIG_SIZE_PARM = 2;
	static final int SIZE_PARM_WITH_NOT = 2;
	static final int FIRST_PARM = 0;
	static final int SEC_PARM = 1;

	@Override
	public List<File> filterFiles(File dir, List<String> parameters) throws FilterWarning {
		if(parameters.size() > BAD_BIG_SIZE_PARM){
			throw new FilterWarning();
		}
		String isNot;
		boolean result = false;
		File [] files = dir.listFiles();
		String nameFilter = parameters.get(FIRST_PARM);
		LinkedList<File> fileLinkedList = new LinkedList<>();
		if (parameters.size() == SIZE_PARM_WITH_NOT) {
			isNot = parameters.get(SEC_PARM);
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
			if(f.isFile() && !f.getName().equals(nameFilter) && result) {
				fileLinkedList.add(f);
			}
			else if (f.isFile() && !result && f.getName().equals(nameFilter)) {
				fileLinkedList.add(f);
			}

		}
		return fileLinkedList;
	}
}
