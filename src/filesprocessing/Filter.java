package filesprocessing;

import java.io.File;
import java.util.List;

public class Filter {
	private final IFilter<String> filter;
	public Filter(IFilter<String> filter){
		this.filter = filter;
	}

	public List<File> filterFiles(File dir, List<String> parameters, int lineCount){
		try {
			return this.filter.filterFiles(dir, parameters);
		}
		catch (NullPointerException | Warning e){
			System.err.printf("Warning in line %s%n", lineCount);
			String line = "all\n";
			List<String> parms = Utils.getFilterParameters(line);
			AllFilter defoult = new AllFilter();
			return defoult.filterFiles(dir, parms.subList(1, parms.size()));
		}
	}
}
