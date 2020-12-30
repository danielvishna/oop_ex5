package filesprocessing;

/**
 * excision of error if we don't get the 2 argument to the program
 */
public class UsageError extends Errors{
	private static final long serialVersionUID = 1L;
	public UsageError(){
		super("ERROR: Wrong usage. Should receive 2 arguments");
	}
}
