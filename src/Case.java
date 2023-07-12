public abstract class Case {
    protected final int lig;  // numéro de ligne
    protected final int col;  // numéro de colonne

    // constructeur
    public Case(int lig, int col) {
        this.lig = lig;
        this.col = col;
    }

    // méthode abstraite
    public abstract boolean estLibre();
}
