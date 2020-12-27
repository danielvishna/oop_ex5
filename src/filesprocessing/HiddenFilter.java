package filesprocessing;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class HiddenFilter implements IFilter<String>{
	@Override
	public List <File> filterFiles(File dir, List<String> parameters) throws Warning {
		String isNot;
		boolean result = false;
		File [] files = dir.listFiles();
		String nameFilter = parameters.get(0);
		if (!nameFilter.equals("YES") && !nameFilter.equals("NO")){
			throw new Warning();
		}
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
			return fileLinkedList;
		for(File f: files) {
			if(nameFilter.equals("YES")) {
				if (f.isFile() && !f.isHidden() && result) {
					fileLinkedList.add(f);
				} else if (f.isFile() && !result && f.isHidden()) {
					fileLinkedList.add(f);
				}
			}
			else {
				if (f.isFile() && f.isHidden() && result) {
					fileLinkedList.add(f);
				} else if (f.isFile() && !result && !f.isHidden()) {
					fileLinkedList.add(f);
				}
			}

		}
		return fileLinkedList;
	}
}
