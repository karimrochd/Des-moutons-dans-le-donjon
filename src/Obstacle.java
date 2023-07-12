public class Obstacle extends Entite {

    // constructeur par défaut
    public Obstacle() {
        super(3);
    }

    // constructeur prenant une résistance en paramètre
    public Obstacle(int resistance) {
        super(resistance);
    }

    // concrétisation de la méthode toString
    @Override
    public String toString(String background) {
        if (resistance >= 3) {
            return "@@@";
        } else if (resistance == 2) {
            return "@@" + background.charAt(2);
        } else { // resistance == 1
            return background.charAt(0) + "@" + background.charAt(2);
        }
    }
}
