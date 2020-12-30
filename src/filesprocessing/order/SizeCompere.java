package filesprocessing.order;

import java.io.File;

public class SizeCompere implements ICompere{
	@Override
	public int compereFiles(File file1, File file2) {
		if(file1.length() > file2.length()){
			return 1;
		}
		if (file1.length() < file2.length()){
			return -1;
		}
		return file1.getName().compareTo(file2.getName());
	}
}
