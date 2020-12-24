package filesprocessing;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class FileFilter implements IFilter<String> {
	@Override
	public List<File> filterFiles(File dir, List<String> parameters) throws Warning {
		String isNot;
		boolean result = false;
		File [] files = dir.listFiles();
		String nameFilter = parameters.get(0);
		LinkedList<File> fileLinkedList = new LinkedList<>();
		if (parameters.size() == 2) {
			isNot = parameters.get(1);
			if (isNot.equals("NOT")) {
				result = true;
			}
			else {
				throw new Warning();
			}
		}
		if (files == null)
			return null;//todo may not be the best way
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
