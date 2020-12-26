package filesprocessing;

public class Command {
	private String filter;
	private String order;
	private int lineFilter;
	private int lineOrder;
	public Command() {
		this.filter = "";
		this.order = "";
		this.lineFilter = 0;
		this.lineOrder = 0;
	}
	public Command(Command other){
		this.filter = other.filter;
		this.order = other.order;
		this.lineFilter = other.lineFilter;
		this.lineOrder = other.lineOrder;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getFilter() {
		return filter;
	}

	public void setOrder(String order){
		this.order = order;
	}

	public String getOrder() {
		return order;
	}

	public void setLineFilter(int lineFilter){
		this.lineFilter = lineFilter;
	}

	public int getLineFilter() {
		return lineFilter;
	}

	public void setLineOrder(int lineOrder){
		this.lineOrder = lineOrder;
	}

	public int getLineOrder() {
		return lineOrder;
	}

}
