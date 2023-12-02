package circuits;

public class LazySonde extends Sonde {
    protected boolean etat;
    protected boolean set = false;
    
    public LazySonde(Composant comp, String in){
        super(comp, in);
    }
    
    public boolean getEtat() throws NonConnecteException{
        if(set == false){
            etat = super.getEtat();
            set = true;
        }
        return etat;
    }
    
    public void reset(){
        set = false;
    }
}
