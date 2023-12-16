package graphique;

import automate.*;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;
/**
 * Cette Class permet de gérer l'interface graphique de l'automate cellulaire.
 * @author Mathieu Goudal L2 Informatique groupe TP 3A
 */
public class InterfaceGameOfLife{

    private JFrame frame;
    private AffichagePrincipal affichageprincipal;
    private GOL automate;
    private Boolean running;

    public InterfaceGameOfLife(GOL automate){
        
        this.automate = automate;
        this.running = true;
        Image icon = Toolkit.getDefaultToolkit().getImage("ressource/img/glider.png");
        this.frame = new JFrame("Game of life");
        frame.setIconImage(icon);
        frame.setLayout(null);
        this.affichageprincipal = new AffichagePrincipal(this);
        frame.add(affichageprincipal.getAffichagePrincipal());
        frame.setSize(1300,800);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
    }

    /**
     * Cette méthode renvoie l'affichage principal de l'automate cellulaire.
     * @return l'affichage principal de l'automate cellulaire.
     */
    public AffichagePrincipal getAffichagePrincipal(){
        return affichageprincipal;
    }

    /**
     * Cette méthode permet de modifier l'automate cellulaire.
     * @param newautomate Nouvel automate cellulaire
     */
    public void setGOL(GOL newautomate){
        automate = newautomate;
    }

    /**
     * Cette méthode permet de retourner l'automate cellulaire.
     * @return l'automate cellulaire
     */
    public GOL getGOL(){
        return automate;
    }

    /**
     * Cette méthode renvoie la JFrame contenant l'automate cellulaire.
     * @return JFrame contenant l'automate cellulaire
     */
    public JFrame getFrame(){
        return frame;
    }

    /**
     * Cette méthode permet de modifier l'affichage principal de l'automate cellulaire.
     * @param newA Nouveau affichage principal de l'automate cellulaire.
     */
    public void setAffichagePrincipal(AffichagePrincipal newA){
        affichageprincipal = newA;
    }

    /**
     * Cette méthode renvoie l'état de l'automate cellulaire.
     * @return l'état de l'automate cellulaire
     */
    public Boolean getRunning(){
        return running;
    }

    /**
     * Cette méthode permet de modifier l'état de l'automate cellulaire.
     * @param bool Nouveau état de l'automate cellulaire
     */
    public void setRunning(Boolean bool){
        running = bool;
    }

    /**
     * Cette méthode permet de retourner l'état du menu de l'automate cellulaire.
     * @return l'état du menu de l'automate cellulaire
     */
    public Boolean getMenuRequest(){
        return affichageprincipal.getMenu();
    }

    /**
     * Cette méthode vérifie si une requête pour accéder a un modèle rle 
     * @return true si une requête pour accéder a un modèle rle a été envoyé et false sinon
     */
    public Boolean getRleRequest(){
        return affichageprincipal.getRle();
    }

    /**
     * Cette méthode permet de détruire l'affichage principal de l'automate cellulaire.
     */
    public void destroy(){
        frame.dispose();
        affichageprincipal = null;
    }

}