public abstract class Entite {
    protected int resistance;

    // Constructeur
    public Entite(int resistance) {
        this.resistance = resistance;
    }

    // Méthode abstraite
    public abstract String toString(String background);

    public void decrR() {
        this.resistance--;
    }

    // Vérifie si l'entité doit être retirée du jeu
    public boolean estMort() {
        return this.resistance <= 0;
    }
}
