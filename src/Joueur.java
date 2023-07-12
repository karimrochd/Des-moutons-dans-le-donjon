public class Joueur extends Entite {
    public Joueur() {

        super(7);
    }
    public String toString(String background) {
        return background.charAt(0) + "J" + background.charAt(2);
    }

    public int move(Case courantee, Case ciblee) {
    CaseTraversable courante = (CaseTraversable) courantee;
    CaseTraversable cible = (CaseTraversable) ciblee;
        if (cible.estLibre()) {
            courante.vide();
            cible.entre(this);
            return 1;
        }
        return 2;
    }

}