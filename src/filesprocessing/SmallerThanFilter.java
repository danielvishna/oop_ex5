package filesprocessing;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class SmallerThanFilter implements IFilter<String> {

	@Override
	public File[] filterFiles(File dir, List<String> parameters) throws Warning {
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
		for(File f: files) {
			if(result && f.length() > size && f.isFile()) {
				fileLinkedList.add(f);
			}
			else if (!result && f.length() <= size && f.isFile()) {
				fileLinkedList.add(f);
			}

		}
		return fileLinkedList.toArray(new File[0]);
	}
}
