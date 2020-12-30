package filesprocessing.order;

import java.io.File;

public class Compere {
	private final ICompere compere;

	/**
	 * creat the Compere object
	 * @param compere ICompere filed
	 */
	public Compere(ICompere compere){
		this.compere = compere;
	}

	/**
	 * compere 2 files and if the first is equal more return positive number if the second bigger native
	 * number and if the equals 0
	 * @param file1 : first file for the compere
	 * @param file2 : second file
	 * @return int the represent the order between the files
	 */
	public int doCompere(File file1, File file2){
		return this.compere.compereFiles(file1, file2);
	}

}
