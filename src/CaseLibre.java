public class CaseLibre extends CaseTraversable {
    // constructeur
    public CaseLibre(int lig, int col) {
        super(lig, col);
    }
    public CaseLibre(int lig, int col, Entite e) {
        super(lig, col, e);
    }


    // la méthode estLibre() retourne true si la case n'est pas occupée par une entité
    @Override
    public boolean estLibre() {
        return super.estLibre();
    }

    public String toString() {
        Entite entite = this.getContenu();
        if (entite != null) {
            return entite.toString("   ");
        } else {
            return "   ";
        }
    }
}
