import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
public class Jeu {

    Terrain terrain;
    int sortis;
    boolean partieFinie;

    /* Initialisation d'un jeu avec le terrain initial décrit dans
       le fichier [f] donné en paramètre */
    public Jeu(String f) {
        this.terrain = new Terrain(f);
        this.sortis = 0;
        this.partieFinie = false;
    }

    /*public static void main(String[] args) {
        Jeu j = new Jeu("laby1.txt");
        j.terrain.print();
        for(int i = 0; i < 10; i++){
            j.tour();
            j.terrain.print();
        }
        j.tour();
        j.terrain.print();
    }*/

    public synchronized int tour(int i, int j) {
        Case c = terrain.carte[i][j];
        if (c instanceof CaseTraversable && !c.estLibre()) {
            Entite e = ((CaseTraversable) c).getContenu();
            if (e instanceof EntiteMobile) {
                if ((i + ((EntiteMobile) e).direction.getDeltaC())>=0 && (i + ((EntiteMobile) e).direction.getDeltaC())<=terrain.hauteur && (j + ((EntiteMobile) e).direction.getDeltaL())>=0 && (j + ((EntiteMobile) e).direction.getDeltaL())<=terrain.largeur) {
                    //System.out.println("i : " + i + " j : " + j + " deltaC : " + ((EntiteMobile) e).direction.getDeltaC() + " deltaL : " + ((EntiteMobile) e).direction.getDeltaL());
                    boolean b = true;
                    int a = ((EntiteMobile) e).action(c, terrain.carte[i + ((EntiteMobile) e).direction.getDeltaC()][j + ((EntiteMobile) e).direction.getDeltaL()]);
                    if(a==1) {
                        this.fin();
                        this.sortis = this.sortis/2;

                    }else if (a==2){
                        this.sortis ++;
                    }

                    //System.out.println(b);
                    if ((((EntiteMobile) e).direction.getDeltaC())==1){return 1;}
                    else if ((((EntiteMobile) e).direction.getDeltaL())==1){return 2;}
                    else {return 3;}
                }else{
                    boolean b = false;
                    //System.out.println(b);
                    ((EntiteMobile) e).changerDirection();
                    return 0;

                }


            }

        }
        boolean b = false;
        //System.out.println(b);
        return 0;
    }
    public void fin() {
        this.partieFinie = true;
    }
}
