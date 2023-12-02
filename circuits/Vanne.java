package circuits;

public class Vanne extends Composant {
	
	protected Composant in;
	protected String name;
	
	public Vanne(String n){
		this.name = n;
	}
	
	public void setIn(Composant comp) {
		
		in = comp;
		
	}
	
	public String description(){
		if(in != null){
			return super.description() + " in: " + in.getId();
		}else{
			return super.description() + " in: non connecte";
		}
	}
	
	public boolean getEtat() throws NonConnecteException {
		if (in == null) {
			throw new NonConnecteException();
		}else {
			return in.getEtat();
		}
	}
}
