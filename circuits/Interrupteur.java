package circuits;

public class Interrupteur extends Composant {
        
        protected boolean etat;
        protected String name;
        
        public Interrupteur(String n){
            this.name = n;
        }
        
        public void on(){
            this.etat = true;
        }
        
        public void off(){
            this.etat = false;
        }
        
        public boolean getEtat() throws NonConnecteException {
            return this.etat;
        }
}
