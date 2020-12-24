package filesprocessing;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class ContainsFilter implements IFilter<String> {

	@Override
	public File[] filterFiles(File dir, List<String> parameters) throws Warning {
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
		for(File f: files) {
			if(!f.getName().contains(nameFilter) && result) {
				fileLinkedList.add(f);
			}
			else if (f.getName().contains(nameFilter) && !result) {
				fileLinkedList.add(f);
			}

		}
		return fileLinkedList.toArray(new File[0]);
	}
}