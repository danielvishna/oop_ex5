package filesprocessing.filter;

/**
 * Factory of Filter create all the filters
 */
public class FilterFactory {
	/**
	 * Filter Factory create the filter object that filter thr files at the dir
	 * @param filter the given filter
	 * @return IFilter of the wonted filter
	 */
	public static IFilter<String> createFilter(String filter) {
		switch (filter) {
		case "greater_than":
			return new GreaterThanFilter();
		case "smaller_than":
			return new SmallerThanFilter();
		case "between":
			return new BetweenFilter();
		case "file":
			return new FileFilterName();
		case "contains":
			return new ContainsFilter();
		case "prefix":
			return new PrefixFilter();
		case "suffix":
			return new SuffixFilter();
		case "writable":
			return new WritableFilter();
		case "executable":
			return new ExecutableFilter();
		case "hidden":
			return new HiddenFilter();
		case "all":
			return new AllFilter();
		default:
			return null;
		}

	}
}
