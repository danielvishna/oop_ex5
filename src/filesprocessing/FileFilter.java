package filesprocessing;

import java.io.File;
import java.util.List;

public class FileFilter implements IFilter<String> {
	@Override
	public File[] filterFiles(File dir, List<String> parameters) throws Warning {
		return new File[0];
	}
}
