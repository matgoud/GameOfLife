package automate;

import java.awt.*;

/**
 * Cette interface permet de définir un objet univers et ses méthodes.
 */
public interface Univers {

    /**
     * Cette méthode renvoie la taille de l'univers.
     * @return la taille de l'univers
     */
    public int getTaille();

    /**
     * Cette méthode renvoie l'objet de l'univers à la position (y,x).
     * @param y Ordonnée de l'objet
     * @param x Abscisse de l'objet
     * @return l'objet de l'univers à la position (y,x)
     */
    public Object getValAt(int y,int x);

    /**
     * Cette méthode permet de modifier l'objet de l'univers à la position (y,x).
     * @param y Ordonnée de l'objet
     * @param x Abscisse de l'objet
     * @param val Valeur de l'objet
     */
    public void setValAt(int y,int x, Object val);

    /**
     * Cette méthode permet d'afficher l'objet de l'univers de taille taille qui a pour index de départ (y,x).
     * @param g Graphics à utiliser
     * @param y Ordonnée de départ de l'affichage
     * @param x Abscisse de départ de l'affichage
     * @param taille taille à afficher
     * @param zoom puissance du zoom 
     */
    public void Afficher(Graphics g,int y,int x,int taille, float zoom);

}
