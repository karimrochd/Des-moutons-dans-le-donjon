public abstract class EntiteMobile extends Entite {

    protected Direction direction;

    // constructeur
    public EntiteMobile(int r, Direction d) {
        super(r);
        this.direction = d;
    }

    // méthode pour obtenir le caractère représentant l'entité en fonction de sa direction
    public abstract char getRepresentation();
    public void changerDirection() {
        this.direction = Direction.random();
    }

    // méthode toString(String background)
    @Override
    public String toString(String background) {
        return background.charAt(0) + String.valueOf(getRepresentation()) + background.charAt(2);
    }
    public abstract int action(Case courante, Case cible);
}
