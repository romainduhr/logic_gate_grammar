parser grammar EqBoolParser;
options {tokenVocab=EqBoolLexer;}

@header {
    import java.util.*;
    import circuits.*;
}

@members {
    LinkedHashMap<String,EquationCircuit> circuits = new LinkedHashMap<String,EquationCircuit>();
    EquationCircuit currentCircuit;
}

programme : circuits_eq commandes EOF ;

circuits_eq :  circuit_eq circuits_eq
|           circuit_eq ;

circuit_eq :   
    ID
    {
        String name = $ID.getText();
        currentCircuit = new EquationCircuit(name);
        circuits.put(name, currentCircuit);
    }        
    LPAR ins=ports RPAR RETURNS LPAR outs=ports 
    {
        currentCircuit.setInputs($ins.ids);
        currentCircuit.setOutputs($outs.ids);
    }
    RPAR equations END ;

ports returns [ List<String> ids ] : 
    ID COMMA ports  
    { 
        $ports.ids.add($ID.getText());
        $ids = $ports.ids;
    }
|   ID              
    {   
        $ids = new ArrayList<String>();
        $ids.add($ID.getText());
    } ;

equations: equation equations
| ;

equation: ID EQUAL e=expr_bool SCOL 
    {
        String name = $ID.getText();
        List<String> outputs = currentCircuit.getOutputs();
        List<String> signals = currentCircuit.getSignals();
        Composant comp;
                                        
        if(outputs.contains(name)){
            comp = currentCircuit.getOutput(name);
        }else if(signals.contains(name)){
            comp = currentCircuit.getSignal(name);
        }else{
            comp = currentCircuit.addSignal(name);
        }
                                        
        ((Vanne)comp).setIn($e.comp);
    };

expr_bool returns [ Composant comp ]: 
    ID                              
    {
        String name = $ID.getText();
        List<String> inputs = currentCircuit.getInputs();
        Composant comp;
                                        
        if(inputs.contains(name)){
            $comp = currentCircuit.getInput(name);
        }else{
            $comp = currentCircuit.getSignal(name);
        }
    }
|   BOOL                            
    {
        $comp = new Interrupteur($BOOL.getText());
        if (Boolean.parseBoolean($BOOL.getText())) 
            ((Interrupteur)$comp).on();
        else 
            ((Interrupteur)$comp).off();
    }
|   LPAR e=expr_bool RPAR           
    {   
        $comp = $e.comp;
    }
|   e1=expr_bool AND e2=expr_bool   
    { 
        $comp = new And();
        ((And)$comp).setIn1($e1.comp);
        ((And)$comp).setIn2($e2.comp);
        currentCircuit.addComposant($comp);
    }
|   e1=expr_bool OR e2=expr_bool    
    {
        $comp = new Or(); 
        ((Or)$comp).setIn1($e1.comp);
        ((Or)$comp).setIn2($e2.comp);
        currentCircuit.addComposant($comp);
    }
|   NOT e=expr_bool                
    {    
        $comp = new Not();
        ((Not)$comp).setIn($e.comp);
        currentCircuit.addComposant($comp);
    };

commandes: commande commandes
| ;

commande: 
    DESCR LPAR name=ID RPAR             
    { 
        (circuits.get($name.getText())).descr(); 
    }
|   EVAL LPAR name=ID COMMA arguments RPAR    
    { 
        (circuits.get($name.getText())).eval($arguments.vs); 
    };

arguments returns [ List<Boolean> vs ]: 
    BOOL COMMA arguments    
    {
        Boolean bool = Boolean.parseBoolean($BOOL.getText());
        $arguments.vs.add(bool);
        $vs = $arguments.vs;
    }
|   BOOL                    
    {
        Boolean bool = Boolean.parseBoolean($BOOL.getText());
        $vs = new ArrayList<Boolean>();
        $vs.add(bool);
    };
