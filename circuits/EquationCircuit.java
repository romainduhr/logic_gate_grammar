package circuits;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class EquationCircuit extends Circuit {
    LinkedHashMap<String,Interrupteur> inputs;
    LinkedHashMap<String,Vanne> outputs;
    LinkedHashMap<String,Vanne> signals;

    public EquationCircuit(String name) {
        super(name);
        inputs=new LinkedHashMap<String,Interrupteur>();
        outputs=new LinkedHashMap<String,Vanne>();
        signals=new LinkedHashMap<String,Vanne>();
    }
    
    public Vanne addSignal(String name){
        Vanne v=new Vanne(name);
        signals.put(name,v);
        composants.add(v);
        return v;
    } 
    
    public Vanne getSignal(String name){
        return signals.get(name);
    }
    
    public List<String> getSignals(){
        List<String> s = new ArrayList<>(signals.keySet());
        return s;
    }

    /** Adds an input for each name in list 'names'. */
    public void setInputs(List<String> names) {
        for(String n:names) {
            Interrupteur it = new Interrupteur(n);
            inputs.put(n,it);
            composants.add(it);
        }
    }

    /** Adds an output for each name in list 'names'. */
    public void setOutputs(List<String> names) {
        for(String name:names) {
            Vanne v=new Vanne(name);
            outputs.put(name,v);
            composants.add(v);
        }
    }
    
    public List<String> getOutputs(){
        List<String> outs = new ArrayList<>(outputs.keySet());
        return outs;
    }
    
    public List<String> getInputs(){
        List<String> ins = new ArrayList<>(inputs.keySet());
        return ins;
    }

    /** Returns the Interrupteur named 'name' */
    public Interrupteur getInput(String name) {
        return inputs.get(name);
    }

    /** Returns the Vanne named 'name' */
    public Vanne getOutput(String name) {
        return outputs.get(name);
    }

    /** Evaluates and prints the output values of the circuit. Input
     * values are determined by 'vs' */
    public void eval(List<Boolean> vs) {
        System.out.println("\nEval: "+this.name);
    
        List<Interrupteur> ins=new ArrayList<Interrupteur>(inputs.values());
        for (int i=0; i<vs.size(); i++) {
            Interrupteur it=ins.get(i);
            if(vs.get(i))
                it.on();
            else  it.off();
        }
        
        System.out.println("\n\tInputs:");
        for(Map.Entry<String,Interrupteur> i:inputs.entrySet()) {
            System.out.println("\t\t"+i.getKey()+": "+i.getValue().traceEtat());
        }
        
        System.out.println("\n\tSignals:");
        for(Map.Entry<String,Vanne> s:signals.entrySet()) {
            System.out.println("\t\t"+s.getKey()+": "+s.getValue().traceEtat());
        }
        
        System.out.println("\n\tOutputs:");
        for(Map.Entry<String,Vanne> o:outputs.entrySet()) {
            System.out.println("\t\t"+o.getKey()+": "+o.getValue().traceEtat());
        }
    }

    /** Prints the circuit description */
    public void descr() {
        System.out.print(description());
    }
}
