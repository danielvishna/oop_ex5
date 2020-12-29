package filesprocessing;

public class OrderFactory {
	/**
	 * Order Factory creat the order we wont to set
	 * @param order a string that say how we wont to sort the files
	 * @return ICompere of how to order the files
	 */
	public static ICompere createOrder(String order){
		switch (order) {
		case "abs":
			return new AbsCompere();
		case "type":
			return new TypeCompere();
		case "size":
			return new SizeCompere();
		default:
			return null;
		}
	}
}
