package circuits;

public abstract class Porte extends Composant {
    public abstract void probe(SondesTable tableSondes);
    public abstract void unProbe(SondesTable tableSondes);
}
