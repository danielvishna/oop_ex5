package filesprocessing;

import java.io.File;

public interface ICompere {
	/**
	 * interface of compere 2 files
	 * @param file1 : file one for the compere
	 * @param file2 : file two for the compere
	 * @return : int that if it positive that say file one need to come after 2 if neg file one need to
	 * come before two
	 */
	int compereFiles(File file1, File file2);
}
