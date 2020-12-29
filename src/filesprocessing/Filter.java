package filesprocessing;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Filter {
	private final IFilter<String> filter;

	/**
	 * constructor for filter
	 * @param filter the filter that by the we filter the files
	 */
	public Filter(IFilter<String> filter) {
		this.filter = filter;
	}

	/**
	 * filter files in dir by give filter
	 * @param dir : the directory
	 * @param parameters the parameters that by them we filter
	 * @param lineCount : the line in the file commend
	 * @return list of all the files that suites the filter
	 */
	public List<File> filterFiles(File dir, List<String> parameters, int lineCount) {
		try {
			return this.filter.filterFiles(dir, parameters);
		} catch (NullPointerException | Warning e) {
			System.err.printf("Warning in line %s%n", lineCount);
			LinkedList<File> fileLinkedList = new LinkedList<>();
			File [] files = dir.listFiles();
			if (files == null) {
				return fileLinkedList;
			}
			for (File f : files) {
				if (f.isFile()) {
					fileLinkedList.add(f);
				}
			}
			return fileLinkedList;
		}
	}
}
