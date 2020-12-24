package filesprocessing;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class SmallerThanFilter implements IFilter<String> {

	@Override
	public File[] filterFiles(File dir, List<String> parameters) {
		File [] files = dir.listFiles();
		double size = Double.parseDouble(parameters.get(0));
		String isNot = "";
		boolean result = false;
		LinkedList<File> fileLinkedList = new LinkedList<>();
		if (parameters.size() == 2) {
			isNot = parameters.get(1);
			//todo resise exsepsion if not equal NOT
			if (isNot.equals("NOT")) {
				result = true;
			}
		}
		for(File f: files) {
			if(result && f.length() > size && f.isFile()) {
				fileLinkedList.add(f);
			}
			else if (f.length() <= size && f.isFile()) {
				fileLinkedList.add(f);
			}

		}
		return fileLinkedList.toArray(new File[0]);
	}
}
