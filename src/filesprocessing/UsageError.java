package filesprocessing;

public class UsageError extends Errors{
	private static final long serialVersionUID = 1L;
	public UsageError(){
		super("ERROR: Wrong usage. Should receive 2 arguments");
	}
}
