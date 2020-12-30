package filesprocessing.order;

import java.io.File;

/**
 * Get the order between 2 files by there names
 */
public class AbsCompere implements ICompere {
	@Override
	public int compereFiles(File file1, File file2) {
		return file1.getName().compareTo(file2.getName());
	}
}
