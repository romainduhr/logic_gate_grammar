package circuits;

public class Not extends Porte {

	protected Composant in;
	
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
			return !in.getEtat();
		}
    }
    
    public void probe(SondesTable tableSondes){
		if(in instanceof Interrupteur){
			in = tableSondes.getSonde((Interrupteur)in, this, "in");
		}
    }
    
    public void unProbe(SondesTable tableSondes){
		if(in instanceof LazySonde){
			in = tableSondes.getInterrupteur((LazySonde)in);
		}
    }
}
