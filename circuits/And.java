package circuits;

public class And extends Porte2Entrees{
    public boolean eval() throws NonConnecteException {
        return super.in1.getEtat() & super.in2.getEtat();
    }
}
