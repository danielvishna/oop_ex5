package filesprocessing;

import java.io.File;
import java.util.List;

public interface IFilter<String> {
	public List<File> filterFiles(File dir, List<String> parameters) throws Warning;
}
