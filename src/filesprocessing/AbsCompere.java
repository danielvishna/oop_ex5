package filesprocessing;

import java.io.File;

public class AbsCompere implements ICompere {
	@Override
	public int compereFiles(File file1, File file2) {
		return file1.getName().compareTo(file2.getName());
	}
}
