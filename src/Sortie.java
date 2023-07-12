public class Sortie extends CaseTraversable {
    // constructeur
    public Sortie(int lig, int col) {
        super(lig, col);
    }

    // la méthode estLibre() retourne true si la case n'est pas occupée par une entité
    @Override
    public boolean estLibre() {
        return super.estLibre();
    }

    public String toString() {
        Entite entite = this.getContenu();
        if (entite != null) {
            return entite.toString("( )");
        } else {
            return "( )";
        }
    }
}