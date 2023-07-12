import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;


public class FenetreJeu extends JPanel implements KeyListener {
    private HashMap<Direction, Image> monsterImages;
    private HashMap<Integer, Image> stonesImages;
    protected Jeu jeu;
    protected int joueuri, joueurj;
    private int tailleCase = 24;
    private int hauteur, largeur;
    private JFrame frame;
    private Image sheepImage, bergerImage;


    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if(joueuri>0) {
                    if (((Joueur) ((CaseTraversable) jeu.terrain.carte[joueuri][joueurj]).getContenu()).move( jeu.terrain.carte[joueuri][joueurj], jeu.terrain.carte[joueuri-1][joueurj])==1) {
                        joueuri = joueuri - 1;
                    }
                }
                break;
            case KeyEvent.VK_DOWN:
                if(joueuri<hauteur-1) {
                    if (((Joueur) ((CaseTraversable) jeu.terrain.carte[joueuri][joueurj]).getContenu()).move( jeu.terrain.carte[joueuri][joueurj], jeu.terrain.carte[joueuri+1][joueurj])==1) {
                        joueuri = joueuri + 1;
                    }
                }
                break;
            case KeyEvent.VK_LEFT:
                if ((joueurj - 1) >= 0) {
                    if (((Joueur) ((CaseTraversable) jeu.terrain.carte[joueuri][joueurj]).getContenu()).move( jeu.terrain.carte[joueuri][joueurj], jeu.terrain.carte[joueuri][joueurj-1])==1) {
                        joueurj = joueurj - 1;
                    }
                }
                break;
            case KeyEvent.VK_RIGHT:
                if ((joueurj + 1) < largeur) {
                    if (((Joueur) ((CaseTraversable) jeu.terrain.carte[joueuri][joueurj]).getContenu()).move( jeu.terrain.carte[joueuri][joueurj], jeu.terrain.carte[joueuri][joueurj+1])==1) {
                        joueurj = joueurj + 1;
                    }
                }
                break;
            case KeyEvent.VK_SPACE:
                // Vérifier si le joueur est actuellement sur une sortie
                if (jeu.terrain.carte[joueuri][joueurj] instanceof Sortie) {

                    System.out.println("Le joueur a quitté le donjon !");
                    jeu.partieFinie= true;
                    this.gameOver();
                }
                break;
        }
        this.repaint();  // Repaint the board after a move
    }

    public void keyReleased(KeyEvent e) {
        // Not used but needs to be present because of the KeyListener interface
    }

    public void keyTyped(KeyEvent e) {
        // Not used but needs to be present because of the KeyListener interface
    }

    public void gameOver() {

        JLabel message = new JLabel("<html><div style='text-align: center;'>Game Over<br/>Votre score est: " + this.jeu.sortis + "</div></html>");

        JOptionPane.showMessageDialog(frame, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }



    public FenetreJeu(Jeu jeu) {
        monsterImages = new HashMap<>();
        monsterImages.put(Direction.nord, new ImageIcon("loup3.jpg").getImage());
        monsterImages.put(Direction.sud, new ImageIcon("loup4.jpg").getImage());
        monsterImages.put(Direction.est, new ImageIcon("loup1.jpg").getImage());
        monsterImages.put(Direction.ouest, new ImageIcon("loup2.jpg").getImage());
        stonesImages = new HashMap<>();
        stonesImages.put(1, new ImageIcon("stone.jpg").getImage());
        stonesImages.put(2, new ImageIcon("2stones.jpg").getImage());
        stonesImages.put(3, new ImageIcon("3stones.jpg").getImage());
        this.hauteur = jeu.terrain.getHauteur();
        this.largeur = jeu.terrain.getLargeur();
        this.jeu = jeu;

        this.sheepImage = new ImageIcon("sheep.jpg").getImage();
        this.bergerImage = new ImageIcon("berger.jpg").getImage();

        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(largeur * tailleCase, hauteur * tailleCase));

        JFrame frame = new JFrame("Donjon");
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

        int n=1;
        int m=1;
        for(int i=0; i<hauteur; i++){
            for(int j=0; j<largeur; j++){
                if(jeu.terrain.carte[i][j] instanceof CaseLibre){
                    if(((CaseLibre) jeu.terrain.carte[i][j]).getContenu() instanceof Joueur){
                        n=i;
                        m=j;
                    }
                }
            }
        }
        joueuri = n;
        joueurj = m;
        //joueur = (Joueur) ((CaseLibre) terrain.carte[n][m]).getContenu();
        frame.addKeyListener(this);
        frame.setFocusable(true);
    }

    public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                Case currentCase = jeu.terrain.carte[i][j];
                if (currentCase instanceof CaseIntraversable) {
                    g.setColor(Color.BLACK);
                    g.fillRect(j * tailleCase, i * tailleCase, tailleCase, tailleCase);
                } else if (currentCase instanceof Sortie) {
                    g.setColor(Color.GREEN);
                    g.fillRect(j * tailleCase, i * tailleCase, tailleCase, tailleCase);
                } else if (currentCase instanceof CaseLibre) {
                    Entite entity = ((CaseLibre) currentCase).getContenu();
                    if (entity instanceof Personnage) {
                        g.drawImage(sheepImage, j * tailleCase, i * tailleCase, tailleCase, tailleCase, this);
                        //g.setColor(Color.BLUE);
                        //g.fillRect(j * tailleCase, i * tailleCase, tailleCase, tailleCase);
                    } else if (entity instanceof Monstre) {
                        //g.setColor(Color.RED);
                        Image monsterImage = monsterImages.get(((EntiteMobile) entity).direction);
                        if (monsterImage == null) {
                        } else {
                            g.drawImage(monsterImage, j * tailleCase, i * tailleCase, tailleCase, tailleCase, this);
                        }
                    }else if (entity instanceof Obstacle) {
                        int resistance = entity.resistance;
                        Image stoneImage = stonesImages.get(resistance);

                        /*switch (resistance) {
                            case 1:
                                g.setColor(new Color(200, 200, 200)); // Un gris clair pour une résistance de 1
                                break;
                            case 2:
                                g.setColor(new Color(100, 100, 100)); // Un gris moyen pour une résistance de 2
                                break;
                            case 3:
                                g.setColor(new Color(50, 50, 50)); // Un gris foncé pour une résistance de 3
                                break;
                        }
                        g.fillRect(j * tailleCase, i * tailleCase, tailleCase, tailleCase);*/
                        g.drawImage(stoneImage, j * tailleCase, i * tailleCase, tailleCase, tailleCase, this);

                    }else if (entity instanceof Joueur) {
                        g.drawImage(bergerImage, j * tailleCase, i * tailleCase, tailleCase, tailleCase, this);
                        //g.setColor(Color.PINK);
                        //g.fillRect(j * tailleCase, i * tailleCase, tailleCase, tailleCase);

                    } else {
                        g.setColor(Color.WHITE);
                        g.fillRect(j * tailleCase, i * tailleCase, tailleCase, tailleCase);
                    }
                }
            }
        }
    }


    public void ecranFinal(int n) {
        frame.remove(this);
        JLabel label = new JLabel("Score " + n);
        label.setFont(new Font("Verdana", 1, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        frame.getContentPane().add(label);
        frame.repaint();
    }
}
