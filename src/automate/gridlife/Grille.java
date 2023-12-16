package automate.gridlife;


import automate.Univers;

import java.awt.*;


/**
 * Cette classe permet de créer une grille et les méthodes afin de la manipuler.
 * @author : Brice Andrieux L2 Info TP 3A
 */
public class Grille implements Univers{
    protected int taille;
    protected Float tab[][] = new Float[0][0];
    protected Float[] couleurs;
    protected boolean isColorBlack;
    public Grille(int taille){
        this(taille,new Float[]{0f,0f,0f});
    }
    public Grille(int taille, Float[] couleurs){
        this.taille = taille;
        tab = new Float[taille][taille];
        for(int y = 0; y < taille; y++) for(int x = 0; x < taille; x++) tab[y][x] = 0f;;
        this.couleurs=couleurs;
        if(couleurs[0]==0f||couleurs[1]==0f||couleurs[2]==0f) isColorBlack=true;
        else isColorBlack=false;
    }
    public int getTaille() {
        return taille;
    }
    /**
     * Cette méthode renvoie l'objet situé à l'index x et y.
     * @param y Ordonnée de l'index y
     * @param x Abscisse de l'index x
     * @return la valeur trouvée a l'index x et y
     */
    public Float getValAt(int y,int x) {
        if(y >= 0 && y < taille && x >= 0 && x < taille)
            return tab[y][x];
        return 0f;
    }
    /**
     * Cette méthode permet de modifier le valeur a l'index x et y d'un tableau.
     * @param y coordonnée y
     * @param x coordonnée x
     * @param tmp valeur a insérer, un Float dans le cas des grilles
     */
    @Override
    public void setValAt(int y,int x,Object tmp) {
        if(tmp instanceof Float){
            Float val = (Float)tmp;
            if(y >= 0 && y < taille && x >= 0 && x < taille)
                tab [y] [x] = val;
        }
    }

    
    /**
     * Cette méthode copie les valeurs d'une autre grille dans celle-ci.
     * @param g grille source
     */
    public void cloneUnivers(Grille g){
        for(int y = 0; y < taille; y++)
            for(int x = 0; x < taille; x++){
                this.tab[y][x] = g.getValAt(y, x);
            }
    }

    /**
     * Cette méthode renvoie la moyenne des valeurs dans l'espace donnée de la grille.
     * @param y Ordonnée du point de départ
     * @param x Abscisse du point de départ
     * @param taille taille de la grille
     * @param hauteur hauteur de l'espace donnée
     * @param largeur largeur de l'espace donnée
     * @return la moyenne des valeurs dans l'espace donnée de la grille
     */
    public float getMoyenne(int y,int x,int taille,int hauteur,int largeur){
        float tot=0f;
        int nbcases=0;
        for(int i=x; i < largeur+x; i++){
            for(int j=y; j < hauteur+y; j++){
                if(i<taille && j<taille){
                    nbcases++;
                    tot+=getValAt(j,i);
                }
            }
        }
        if(nbcases==0) return 0f;
        else return tot/nbcases;
    }

    /**
     * Cette méthode permet d'afficher la grille sur une interface.
     * @param g Graphics à utiliser
     * @param y Ordonnée de départ de l'affichage
     * @param x Abscisse de départ de l'affichage
     * @param taille taille à afficher
     * @param zoom puissance du zoom 
     */
    @Override
    public void Afficher(Graphics g,int y,int x,int taille, float zoom){
        int[] c = new int[3];
        int cblack;
        float val;
        if(zoom >= 1f){
        for(int j = 0; j < taille; j++){
            for(int i = 0; i < taille; i++){
                val=getValAt(j, i);
                if(isColorBlack){
                    cblack=(int)(255.0 - (val*255.0));
                    if (cblack<0) cblack=0;
                    if (cblack>255) cblack=255;
                    g.setColor(new Color(cblack,cblack,cblack));
                }
                else{
                    for(int k=0;k<3;k++){
                        c[k]=(int)(255.0 - ((val-(val*couleurs[k]))*255.0));
                        if (c[k]<0) c[k]=0;
                        if (c[k]>255) c[k]=255;
                    }
                    //if(Float.compare(val,0f)!=0&&i%500==0&&j%500==0) System.out.println("j:"+j+",i:"+i+",val:"+val+",c0:"+c[0]+",c1:"+c[1]+",c2:"+c[2]);
                    g.setColor(new Color(c[0],c[1],c[2]));
                }
                g.fillRect((int)((i+x)*zoom), (int)((j+y)*zoom), (int)(zoom), (int)(zoom));
                //g.drawLine(i+x, j+y, i+x, j+y);
                }
            }
        }
        else{
            int reverseZoom = (int)(1/zoom);
            for(int j = 0; j < taille; j+=reverseZoom){
                for(int i = 0; i < taille; i+=reverseZoom){
                    val=getMoyenne(j, i,taille,reverseZoom,reverseZoom);
                    if(isColorBlack){
                        cblack=(int)(255.0 - (val*255.0));
                            if (cblack<0) cblack=0;
                            if (cblack>255) cblack=255;
                            g.setColor(new Color(cblack,cblack,cblack));
                    }
                    else{
                        for(int k=0;k<3;k++){
                            c[k]=(int)(255.0 - ((val-(val*couleurs[k]))*255.0));
                            if (c[k]<0) c[k]=0;
                            if (c[k]>255) c[k]=255;
                        }
                        g.setColor(new Color(c[0],c[1],c[2]));
                    }
                    g.fillRect((int)((i+x)*zoom), (int)((j+y)*zoom),1,1);
                }
            }
        }
    }

}
