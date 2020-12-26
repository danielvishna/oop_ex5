package filesprocessing;

import java.io.File;
import java.util.List;

public class Compere {
	private final ICompere compere;
	public Compere(ICompere compere){
		this.compere = compere;
	}

	public int doCompere(File file1, File file2){
		return this.compere.compereFiles(file1, file2);
	}

}
