package filesprocessing.filter;

import java.io.File;
import java.util.List;

/**
 * interface of filters this
 * @param <String> - the parameters of how to do the filtering
 */
public interface IFilter<String> {
	/**
	 * filter all the file in given dir
	 * @param dir : directory with files
	 * @param parameters : string that save how to filter the files in the dir
	 * @return : list of all the files that fit to the filter
	 * @throws FilterWarning : warning if the parameter of the filter is not good
	 */
	List<File> filterFiles(File dir, List<String> parameters) throws FilterWarning;
}
