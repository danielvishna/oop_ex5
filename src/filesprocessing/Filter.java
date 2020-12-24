package filesprocessing;

import java.io.File;
import java.util.List;

public class Filter {
	private final IFilter<String> filter;
	public Filter(IFilter<String> filter){
		this.filter = filter;
	}

	public List<File> doFilter(File dir, List<String> parameters) throws Warning {
		return this.filter.filterFiles(dir, parameters);
	}
}
