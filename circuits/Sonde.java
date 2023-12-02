package circuits;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Sonde extends Composant{

    protected Composant in;
    protected Composant connectedComposant;
    protected String connectedInput;
    
    public Sonde(Composant comp, String in){
        connectedComposant = comp;
        connectedInput = in;
    }
    
    public void setIn(Composant comp){
        in = comp;
    }
    
    public boolean getEtat() throws NonConnecteException{
        boolean etat;
        if(in != null){
            etat = in.getEtat();
        }else{
            String result;
            Scanner scanner = new Scanner(System.in);
            
            do {
                System.out.println(connectedInput + " de " + connectedComposant.getId() + ", true or false?");
                result = scanner.next();
            }while(result.compareTo("true") != 0 && result.compareTo("false") != 0);  

            if(result.compareTo("true") == 0){
                etat = true;
            }else{
                etat = false;
            }
        }
        return etat;
    }
}
