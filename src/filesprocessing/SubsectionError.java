package filesprocessing;

/**
 * exception if there miss part in the commend file
 */
public class SubsectionError extends Errors{
	private static final long serialVersionUID = 1L;
	public SubsectionError(){
		super("ERROR: Bad subsection name");
	}

}
