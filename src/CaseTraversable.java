public class CaseTraversable extends Case {
    private Entite contenu;

    public CaseTraversable(int lig, int col) {
        super(lig, col);
        this.contenu = null; // Initialisé à null, aucune entité au départ
    }

    public CaseTraversable(int lig, int col, Entite e) {
        super(lig, col);
        this.contenu = e;
    }

    public Entite getContenu() {
        return this.contenu;
    }

    public void vide() {
        this.contenu = null;
    }

    public void entre(Entite e) {
        if(this.contenu == null) {
            this.contenu = e;
        }
    }

    @Override
    public boolean estLibre() {
        return this.contenu == null;
    }
}
