package filesprocessing;

//exception of error in the given file
public class Errors extends Exception{
	private static final long serialVersionUID = 1L;
	public Errors(){
		super();
	}//todo
	public Errors(String msg){
		super(msg);
	}
}
