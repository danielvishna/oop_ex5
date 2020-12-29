package filesprocessing;

/**
 * object that represent one Command of filter and order in file
 */
public class Command {
	private String filter;
	private String order;
	private int lineFilter;
	private int lineOrder;

	/**
	 * the default  constructor of the  Command
	 */
	public Command() {
		this.filter = "";
		this.order = null;
		this.lineFilter = 0;
		this.lineOrder = 0;
	}

	/**
	 * constructor of Command initialize the Command by other Command
	 * @param other : Command
	 */
	public Command(Command other){
		this.filter = other.filter;
		this.order = other.order;
		this.lineFilter = other.lineFilter;
		this.lineOrder = other.lineOrder;
	}

	/**
	 * set new filter to Command
	 * @param filter : filter to set to Command
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}

	/**
	 * @return  the filter of Command
	 */
	public String getFilter() {
		return filter;
	}

	/**
	 * @param order set the order commend
	 */
	public void setOrder(String order){
		this.order = order;
	}

	/**
	 * @return  the order of Command
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param lineFilter set the line number that filter was appear in the Command file
	 */
	public void setLineFilter(int lineFilter){
		this.lineFilter = lineFilter;
	}

	/**
	 * @return the line number that filter was appear in the Command file
	 */
	public int getLineFilter() {
		return lineFilter;
	}

	/**
	 * @param lineOrder set the line number that order was appear in the Command file
	 */
	public void setLineOrder(int lineOrder){
		this.lineOrder = lineOrder;
	}

	/**
	 *
	 * @return the line number that order was appear in the Command file
	 */
	public int getLineOrder() {
		return lineOrder;
	}

}
