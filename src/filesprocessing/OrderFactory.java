package filesprocessing;

public class OrderFactory {
	public static ICompere createOrder(String line) throws Warning {
		switch (line) {
		case "abs":
			return new AbsCompere();
		case "type":
			return new TypeCompere();
		case "size":
			return new SizeCompere();
		default:
			throw new Warning();
		}
	}
}
