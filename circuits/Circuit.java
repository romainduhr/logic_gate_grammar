package circuits;
import java.util.*;
import java.io.*;

public class Circuit {
    protected String name; 
    protected List<Composant> composants = new ArrayList<Composant>();
    protected SondesTable tableSondes = new SondesTable();
    
    @SuppressWarnings("unchecked")
    
    public Circuit(String name_, Composant[] cps){
        name = name_;
        composants.addAll(Arrays.asList(cps));
        Collections.sort(composants);
    }
    
    public Circuit(String name_){
        this.name = name_;
    }
    
    public void addComposant(Composant comp){
        composants.add(comp);
    }
    
    public String getName(){
        return this.name;
    }
    
    public String description(){
        String description = "\nCircuits: ";
        description += this.name;
        for(Composant comp : composants){
            description += "\n\t";
            description += comp.description();
        }
        description += "\n";
        return description;
    }
    
    public List<String> nomenclature(){
        List<String> ids = new ArrayList<String>();
        
        for(Composant comp : composants){
            ids.add(comp.getId());
        } 
        
        return ids;
    }
    
    public void printDescription(){
        System.out.println("Circuit : " + this.name);
        for(Composant comp : composants){
            System.out.println(comp.description());
        }
    }
    
    public void traceEtats(){
        System.out.println("Circuit : " + name);
        for(Composant comp : composants){
            System.out.println(comp.traceEtat());
        }
    }
    
    public List<Interrupteur> getIns(){
        List<Interrupteur> ins = new LinkedList<Interrupteur>();
        for(Composant comp : composants){
            if(comp instanceof Interrupteur) ins.add((Interrupteur)comp);
        }
        return ins;
    }
    
    public List<Vanne> getOuts(){
        List<Vanne> outs = new LinkedList<Vanne>();
        for(Composant comp : composants){
            if(comp instanceof Vanne) outs.add((Vanne)comp);
        }
        return outs;
    }
    
    public void save(String fileName) throws IOException{
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(this.name);
            out.writeObject(this.composants);
        }catch (FileNotFoundException e1){
            System.err.println("Echec de la sauvegarde : fichier introuvable");
        }catch (IOException e2){
            throw new IOException();
        }
    }
    
    public void load(String fileName){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            this.name = (String)in.readObject();
            this.composants = (List<Composant>)in.readObject();
        }catch (FileNotFoundException e1){
            System.err.println("Echec du chargement : fichier introuvable");
        }catch (IOException e2){
            System.err.println("Echec du chargement : Echec des opérations I/O");
        }catch (ClassNotFoundException e3){
            System.err.println("Echec du chargement : class introuvable dans le fichier");
        }
    }
    
    public void probe(){
        for(Composant comp : composants){
            if(comp instanceof Porte) ((Porte)comp).probe(tableSondes);
        }
    }
    
    public void resetSondes(){
        tableSondes.resetSondes();
    }
    
    public void unProbe(){
        for(Composant comp : composants){
            if(comp instanceof Porte) ((Porte)comp).unProbe(tableSondes);
        }
        tableSondes.clear();
    }
}
