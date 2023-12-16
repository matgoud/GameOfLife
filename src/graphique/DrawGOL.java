package graphique;

import javax.swing.*;
import automate.*;
import java.awt.*;

/**
 * Cette Classe dessine le Jpanel contenantun automate cellulaire.
 */
public class DrawGOL extends JPanel{

    private GOL gol;
    
    public DrawGOL(GOL gol){
        super();
        this.gol = gol;   
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.white);
        gol.getUnivers().Afficher(g,gol.getPosY()+(int)(512/gol.getZoom())-512, gol.getPosX()+(int)(512/gol.getZoom())-512,gol.getTaille(),gol.getZoom());
    }

}
