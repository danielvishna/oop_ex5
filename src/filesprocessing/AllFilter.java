package filesprocessing;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class AllFilter implements IFilter<String>{
	@Override
	public File[] filterFiles(File dir, List<String> parameters) throws Warning {
		File [] files = dir.listFiles();
		String nameFilter = parameters.get(0);
		LinkedList<File> fileLinkedList = new LinkedList<>();

		if (files == null)
			return null;//todo may not be the best way
		for(File f: files) {
			if(f.isFile()) {
				fileLinkedList.add(f);
			}
		}
		return fileLinkedList.toArray(new File[0]);
	}
}
