package graphique;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import automate.GOL;

/**
 * Cette Classe permet de gérer les intéraction entre le clavier et l'interface.
 */
public class Clavier implements KeyListener{
    private GOL automate;
    private JPanel graphismes;
    public Clavier(GOL gol,JPanel gr){
        automate = gol;
        graphismes = gr;
    }

    /**
     * Cette méthode permet d'interagir avec l'interface.
     * appuyer sur la touche 'z' pour déplacer vers le haut
     * appuyer sur la touche 'd' pour déplacer vers le droite
     * appuyer sur la touche 's' pour déplacer vers le bas
     * appuyer sur la touche 'q' pour déplacer vers la gauche
     * appuyer sur la touche 'a' pour zoomer
     * appuyer sur la touche 'e' pour dézoomer
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'z' ) {
            automate.setPosY(automate.getPosY() + (int)(100/automate.getZoom()));
        }
        if (e.getKeyChar() == 'd' ) {
            automate.setPosX(automate.getPosX() - (int)(100/automate.getZoom()));
        }
        if (e.getKeyChar() == 's' ) {
            automate.setPosY(automate.getPosY() - (int)(100/automate.getZoom()));
        }
        if (e.getKeyChar() == 'q' ) {
            automate.setPosX(automate.getPosX() + (int)(100/automate.getZoom()));
        }
        if (e.getKeyChar() == 'a' ) {
            automate.setZoom(automate.getZoom()*2.0f);
        }
        if (e.getKeyChar() == 'e' ) {
            automate.setZoom(automate.getZoom()/2.0f);
        }
        graphismes.repaint();
        //char c = e.getKeyChar();
        //System.out.println("Key Pressed: " + c);
    }
    public void keyTyped(KeyEvent e) {
        //char c = e.getKeyChar();
        //System.out.println("Key Pressed: " + c);
        ;
    }
    public void keyReleased(KeyEvent e) {
        ;
    }
}
