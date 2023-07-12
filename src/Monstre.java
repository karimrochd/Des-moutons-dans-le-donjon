public class Monstre extends EntiteMobile {
    // constructeur
    public Monstre(Direction d) {
        super(1, d);
    }

    // méthode pour obtenir le caractère représentant le monstre en fonction de sa direction
    @Override
    public char getRepresentation() {
        switch (direction) {
            case nord: return 'm';
            case sud: return 'w';
            case est: return '»';
            case ouest: return '«';
            default: return '?'; // ce cas ne devrait jamais se produire
        }
    }

    public int action(Case courantee, Case ciblee) {
        if (ciblee instanceof CaseLibre) {
            CaseLibre cible = (CaseLibre) ciblee;
            if (cible.getContenu() != null &&  !(cible.getContenu() instanceof Monstre)) {
                cible.getContenu().decrR();
                if (cible.getContenu().estMort()) {

                    cible.vide();



                }


            } else if (cible.getContenu() == null) {
                CaseLibre courante = (CaseLibre) courantee;
                courante.vide();
                cible.entre(this);
            } else {
                this.changerDirection();
            }
        } else {
            this.changerDirection();
        }
        return 0;
    }
}