package circuits;
import java.io.*;

public abstract class Composant implements Comparable<Composant>, Serializable{
	
	public String getId() {
		return super.toString(); // class@numero renvoye par Object
	}
	
	public String description(){
		return this.getId();
	}
	
	public abstract boolean getEtat() throws NonConnecteException;
	
	public String traceEtat(){
		String s = "";
		s += this.description() + " | Etat : ";
		try{
			s += this.getEtat();
		}catch (NonConnecteException e){
			s += "Composant non connecte";
		}
		
		return s;
	}
	
	public int compareTo(Composant comp){
		return this.getId().compareTo(comp.getId());
	}
}
