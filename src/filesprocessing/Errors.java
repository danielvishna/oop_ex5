package filesprocessing;

public class Errors extends Exception{
	private static final long serialVersionUID = 1L;
	public Errors(){
		super();
	}
	public Errors(String msg){
		super(msg);
	}
}
