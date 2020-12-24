package filesprocessing;

import java.io.File;
import java.util.List;

public interface IFilter<String> {
	public File[] filterFiles(File dir, List<String> parameters);
}
