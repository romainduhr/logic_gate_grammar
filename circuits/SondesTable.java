package circuits;
import java.util.*;

class SondesTable {
    protected Map<Sonde,Interrupteur> sondesToInterrupteurs = new HashMap<Sonde,Interrupteur>();
    protected Map<Interrupteur, Sonde> interrupteursToSondes = new HashMap<Interrupteur, Sonde>();
    
    public Interrupteur getInterrupteur(LazySonde sonde){
        return sondesToInterrupteurs.get(sonde);
    } 
    
    public LazySonde getSonde(Interrupteur interrupteur, Composant cible, String entree){
        if(interrupteursToSondes.containsKey(interrupteur)){
            return (LazySonde)(interrupteursToSondes.get(interrupteur));
        }else{
            LazySonde sonde = new LazySonde(cible, entree);
            sondesToInterrupteurs.put(sonde, interrupteur);
            interrupteursToSondes.put(interrupteur, sonde);
            return sonde;
        }
    }
    
    public void resetSondes(){
        Set<Sonde> sondes = sondesToInterrupteurs.keySet(); 
        for(Sonde sonde : sondes){
            ((LazySonde)sonde).reset();
        } 
    }
    
    public void clear(){
        sondesToInterrupteurs.clear();
        interrupteursToSondes.clear();
    }
}
