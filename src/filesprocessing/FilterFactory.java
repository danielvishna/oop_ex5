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
		default:
			return null;
		}

	}
}
