public class CaseIntraversable extends Case {
    // constructeur
    public CaseIntraversable(int lig, int col) {
        super(lig, col);
    }

    // la méthode estLibre() retourne toujours false car une case intraversable ne peut jamais être libre
    @Override
    public boolean estLibre() {
        return false;
    }
    public String toString() {
        return "###";
    }
}