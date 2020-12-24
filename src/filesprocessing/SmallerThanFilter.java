package filesprocessing;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class SmallerThanFilter implements IFilter<String> {

	@Override
	public List <File> filterFiles(File dir, List<String> parameters) throws Warning {
		File [] files = dir.listFiles();
		double size = Double.parseDouble(parameters.get(0));
		if (size < 0){
			throw new Warning();
		}
		String isNot;
		boolean result = false;
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
			return null;
		for(File f: files) {
			if(f.isFile() && result && f.length() > size) {
				fileLinkedList.add(f);
			}
			else if (f.isFile() && !result && f.length() <= size) {
				fileLinkedList.add(f);
			}

		}
		return fileLinkedList;
	}
}
