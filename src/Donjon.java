import javax.swing.Timer;
import java.awt.Toolkit;


public class Donjon {
    public static void main(String[] args) {
        int tempo = 650;
        Jeu jeu = new Jeu("laby2.txt");
        FenetreJeu graphic = new FenetreJeu(jeu);
        Timer timer = new Timer(tempo, e -> {

            for (int i = (1-graphic.jeu.terrain.hauteur); i < graphic.jeu.terrain.hauteur; i++) {
                for (int j = 0; j < graphic.jeu.terrain.largeur; j++) {
                    int m = 0;
                    int n = 0;
                    if (i>0 && (i+j)%2==1){
                        m = i;
                        n=j;

                    } else if (i<0 && (j-i)%2==0){
                        m = i+graphic.jeu.terrain.hauteur;
                        n=j;
                    }
                    graphic.jeu.tour(m,n);

                    graphic.repaint();

                    if (((CaseTraversable) graphic.jeu.terrain.carte[graphic.joueuri][graphic.joueurj]).estLibre()) {
                        if (graphic.jeu.terrain.carte[graphic.joueuri][graphic.joueurj] instanceof Sortie) {
                            graphic.gameOver();
                            ((Timer) e.getSource()).stop(); // arrête le Timer
                            return;
                        } else {
                            graphic.jeu.sortis = graphic.jeu.sortis / 2;
                            graphic.gameOver();
                            ((Timer) e.getSource()).stop(); // arrête le Timer
                            return;
                        }
                    }
                }
            }

            /*

            for (int i = 0; i < jeu.terrain.hauteur; i++) {
                for (int j = 0; j < jeu.terrain.largeur; j++) {
                    if(jeu.tour(i, j)>0){
                        graphic.repaint();
                        Toolkit.getDefaultToolkit().sync();
                        if(jeu.tour(i, j)==1 && i<(jeu.terrain.hauteur-2)) {
                            i+=2;
                        } else if(jeu.tour(i, j)==2) {
                            j+=2;
                        }
                    }
                }
            }
*/
        });
        timer.setInitialDelay(0);
        timer.start();
    }
}
