package filesprocessing;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class AllFilter implements IFilter<String>{
	@Override
	public List<File> filterFiles(File dir, List<String> parameters){// throws Warning {
		File [] files = dir.listFiles();
		String isNot;
		boolean result = false;
		if (parameters.size() == 1) {
			isNot = parameters.get(0);
			if (isNot.equals("NOT")) {
				result = true;
			}
//			else {
//				throw new Warning();
//			}
		}
		LinkedList<File> fileLinkedList = new LinkedList<>();
		if(!result) {

			if (files == null)
				return null;//todo may not be the best way
			for (File f : files) {
				if (f.isFile()) {
					fileLinkedList.add(f);
				}
			}
		}
		return fileLinkedList;
	}
}
