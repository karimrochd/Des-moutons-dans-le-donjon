public class Personnage extends EntiteMobile {
    // constructeur
    public Personnage(Direction d) {
        super(4, d);
    }

    // méthode pour obtenir le caractère représentant le personnage en fonction de sa direction
    @Override
    public char getRepresentation() {
        switch (direction) {
            case nord: return '^';
            case sud: return 'v';
            case est: return '>';
            case ouest: return '<';
            default: return '?'; // ce cas ne devrait jamais se produire
        }
    }

    public int action(Case courantee, Case ciblee) {
        CaseTraversable courante = (CaseTraversable) courantee;

        if (courante instanceof Sortie) {
            courante.vide();
            return 2;
        } else if (ciblee instanceof CaseLibre) {
            CaseLibre cible = (CaseLibre) ciblee;
            if (cible.getContenu() instanceof Obstacle) {
                cible.getContenu().decrR();
                if (cible.getContenu().estMort()) {
                    cible.vide();
                }
            } else if (cible.getContenu() == null) {

                courante.vide();
                cible.entre(this);
            } else {
                this.changerDirection();
            }
        } else if (ciblee instanceof Sortie) {
            Sortie cible = (Sortie) ciblee;
            courante.vide();
            cible.entre(this);
        } else {
            this.changerDirection();
        }
        return 0;
    }
}