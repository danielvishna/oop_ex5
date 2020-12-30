package filesprocessing.filter;

import java.io.File;
import java.util.LinkedList;
import java.util.List;


public class AllFilter implements IFilter<String>{
	@Override
	public List<File> filterFiles(File dir, List<String> parameters) throws FilterWarning {
		File [] files = dir.listFiles();
		String isNot;
		boolean result = false;
		if (parameters.size() > 1)
		{
			throw new FilterWarning();
		}
		if (parameters.size() == 1) {
			isNot = parameters.get(0);
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
