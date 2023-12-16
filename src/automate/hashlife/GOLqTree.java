package automate.hashlife;

import java.io.*;
import java.lang.Thread;
import java.util.HashMap;
import java.util.Random;

import automate.GOL;

/**
 *	Cette classe permet de lancer un instance du Jeu de la vie en utilisant des quadtrees et en utilisant hashlife.
 *	@author : Clément Bartolone L2 Info TP 3A
 */
public class GOLqTree extends GOL{
    private GOLqTreeNode qTree;
    private DecodeqTree decodeur;
    private long gen;
    private int profondeur;

    /**
     * Ce constructeur permet de lancer une instance du Jeu de la vie en utilisant les quadtrees et en utilisant hashlife.
     * @param taille specifie la longeur du côté de l'arbre
     * @param rules chaine de caractères qui spécifie les régles suivies par l'automate instancié
     */
    public GOLqTree (int taille,String rules){
        super(taille,rules);
        gen = 0;
        profondeur = (int)(Math.log(this.taille) / Math.log(2));
        decodeur = new DecodeqTree(rules);
        qTree = HashLifeqTree.create(profondeur);   //plus lent que les quadtree classiques mais permettent hashlife!
        qTree = qTree.emptyTree(profondeur);        //crée un arbre vide de la profondeur taille afin de pouvoir placer des valeurs dans le quadtree
                                                                                    //c'est très gourmand en mémoire, il faut donc que je trouve un moyen de faire sans ou de diminuer l'étandue a générer.
                                                                                    //mais en vrai ca va vu que le quadtree est canonique. (mais du coup a ne pas faire avec un quadtree classique!!!!)
        GOLqTreeNode.setDecodeur(decodeur);
        HashLifeqTree.setSpeed(0);
    }

    /**
     * Cette méthode permet de remplir toute les quadtrees de nodes vide
     */
    public void clearUnivers(){
        qTree = qTree.emptyTree(profondeur);
    }

    /**
     * Cette méthode permet de modifier les règles du jeu de la vie.
     * @param newRule chaine de caractères qui spécifie les nouvelles régles suivies par l'automate instancié
     */
    public void setRule(String newRule){
        rules = newRule;
        decodeur = new DecodeqTree(rules);
        GOLqTreeNode.setDecodeur(decodeur);
    }

    /**
     * Cette méthode permet de remplir toute les valeurs de la liste aléatoirement.
     * WARNING A ÉVITER SURTOUT AVEC DE GROS QUADTREES (votre ordinateur peux prendre feu spontanément)
     */
    public void randomizeUnivers(){
        Random rand = new Random();
        for(int y = 0; y < taille-1; y++)
            for(int x = 0; x < taille-1; x++){
                if(rand.nextInt()%2==1) qTree.setValAt(y,x,true);
                else qTree.setValAt(y,x,false);
            }
    }

    /**
     * Cette méthode permet de modifier la vitesse d'éxécution du jeu de la vie.
     * @param speed nouvelle vitesse de simulation, le vitesses supérieures a 1 ne fonctionnent qu'avec les quadtree pour hashlife
     */
    private void changeSpeed(int speed){
        HashLifeqTree.setSpeed(speed);
        CanonicalizedqTree.hashMap.clear();
        //int num_nodes = CanonicalizedqTree.hashMap.size();
        //System.out.println("suppression de " + num_nodes + " nodes");
        setRule(rules);
    }

    /**
     * Cette méthode renvoie la vitesse d'éxécution du jeu de la vie.
     * @return la vitesse d'éxécution du jeu de la vie
     */
    public int getSpeed(){
        return HashLifeqTree.getSpeed();
    }

    /**
     * Cette méthode renvoie l'univers actuel.
     * @return l'univers actuel
     */
    public GOLqTreeNode getUnivers() {
        return qTree;
    }
    /**
     * Cette méthode met a jour notre univers en changeant de génération et en mettant a jour le compteur de générations.
     */
    public void update(){
        if(changespeed){
            changeSpeed(requestspeed);
            changespeed = false;
        }
        this.qTree = qTree.nextGeneration();
        if(HashLifeqTree.getSpeed() == 1)gen += Math.pow(2, qTree.getProfondeur()-1);
        else gen += 1;
        int num_nodes = CanonicalizedqTree.hashMap.size();
        if(num_nodes >= 20000000){
            CanonicalizedqTree.hashMap.clear();
            System.out.println("suppression de " + num_nodes + " nodes");
        }
    }

    /**
     * Cette méthode renvoie la génération actuelle du jeu de la vie.
     * @return la génération actuelle
     */
    public long getGeneration(){
        return gen;
    }

}
