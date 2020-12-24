package filesprocessing;

public class Warning extends Exception{
	private static final long serialVersionUID = 1L;
	public Warning(String msg){
		super(msg);
	}
	public Warning(){
		super();
	}
}
