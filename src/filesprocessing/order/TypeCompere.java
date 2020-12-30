package filesprocessing.order;
import java.lang.*;
import java.io.File;

public class TypeCompere implements ICompere{
	@Override
	public int compereFiles(File file1, File file2) {
		String extension1 = "";
		String extension2 = "";

		int fileOneLastIndexOfDot = file1.getName().lastIndexOf('.');
		if (fileOneLastIndexOfDot > 0) {
			extension1 = file1.getName().substring(fileOneLastIndexOfDot+1);
		}
		int fileTwoLastIndexOfDot = file2.getName().lastIndexOf('.');
		if (fileTwoLastIndexOfDot > 0) {
			extension2 = file2.getName().substring(fileTwoLastIndexOfDot+1);
		}
		if(extension1.compareTo(extension2) != 0){
			return extension1.compareTo(extension2);
		}
		return file1.getName().compareTo(file2.getName());
	}
}
