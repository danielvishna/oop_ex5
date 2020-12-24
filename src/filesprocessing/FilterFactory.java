package filesprocessing;

public class FilterFactory {
	public static IFilter<String> createFilter(String line) {
		switch (line) {
		case "greater_than":
			return new GreaterThanFilter();
		case "smaller_than":
			return new SmallerThanFilter();
		case "between":
			return new BetweenFilter();
		case "file":
			return new FileFilter();
		case "contains":
			return new ContainsFilter();
		case "prefix":
			return new PrefixFilter();
		case "suffix":
			return new SuffixFilter();
		default:
			return null;
		}

	}
}
