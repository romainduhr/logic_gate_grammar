package circuits;

public abstract class Porte2Entrees extends Porte {
    
    protected Composant in1;
    protected Composant in2;
    
    public void setIn1(Composant comp){
        in1 = comp;
    }
    public void setIn2(Composant comp){
        in2 = comp;
    }
    
    public String description(){
        String s = super.description() + " in1: ";
        if(in1 != null){
            s += in1.getId();
        }else{
            s+= "non connecte ";
        }
        
        s+= " in2: ";
        
        if(in2 != null){
            s += in2.getId();
        }else{
            s+= "non connecte";
        }
        
        return s;
    }
    
    public abstract boolean eval() throws NonConnecteException;
    
    public boolean getEtat() throws NonConnecteException {
        if(this.in1 == null || this.in2 == null) {
            throw new NonConnecteException();
        }else {
            return eval();
        }
    }
    
    public void probe(SondesTable tableSondes){
		if(in1 instanceof Interrupteur){
			in1 = tableSondes.getSonde((Interrupteur)in1, this, "in1");
		}
		if(in2 instanceof Interrupteur){
			in2 = tableSondes.getSonde((Interrupteur)in2, this, "in2");
		}
    }
    
    public void unProbe(SondesTable tableSondes){
		if(in1 instanceof LazySonde){
			in1 = tableSondes.getInterrupteur((LazySonde)in1);
		}
		if(in2 instanceof LazySonde){
			in2 = tableSondes.getInterrupteur((LazySonde)in2);
		}
    } 
}
