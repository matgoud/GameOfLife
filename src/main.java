package GameOfLife;

import java.util.Timer;
import javax.swing.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;
import java.util.List;


import automate.*;
import automate.gridlife.*;
import automate.hashlife.*;
import graphique.*;
import lecteur_fichier.LecteurRLE;


/**
 * Cette Classe permet de lancer un automate cellulaire.
 */
public class main{
      /**
       * Cette méthode fait attendre un nombre X de millisecondes
       * @param milliseconds milisecondes à attendre
       */
      public static void delai(int milliseconds){
            try {
                  Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                  e.printStackTrace();
            }
      }

      /**
       * Cette méthode gère la gestion du menu lors de l'éxecution
       * @return un automate cellulaire correspondant aux choix effectué dans le menu.
       */
      public static GOL manageMenu(){

            Menu menu = new Menu();

            while (!menu.getAutomateSet()){
                  delai(100);
            }

            GOL automate = menu.getGOL();
            menu.getFrame().dispose();
            menu = null;
            return automate;

      }

      /**
       * Cette méthode gère l'importation des fichiers RLE
       * @param modele est le modèle RLE choisie dans le menu déroulant correspondant au modèle
       * @param golType est l'instance du Jeu de la vie ou de l'automate cellulaire lors du choix
       * @return un automate cellulaire avec le modèle choisi et l'instance du jeu de la vie ou de l'automate cellulaire lors du choix
       */
      public static GOL manageRle(String modele,int golType){
            LecteurRLE rle = new LecteurRLE();
            GOL automate = rle.importRLE(golType,"ressource/RLE/"+modele,10, 10);
            rle = null;
            return automate;

      }

      public static void main(String[] args){

            InterfaceGameOfLife inter = new InterfaceGameOfLife(manageMenu());

            while(true){
                  if(inter.getAffichagePrincipal().getRunning()){
                        inter.getGOL().update();
                        inter.getAffichagePrincipal().getAffichageGOL().repaint();
                        if(inter.getGOL() instanceof GOLqTree){
                              if(inter.getGOL().getSpeed() == 1){
                                    delai(5);//on limite la vitesse de hashlife pour qu'on puisse voir
                                                           //quelquechose
                              }
                        }
                  }
                  if(inter.getMenuRequest()){
                        inter.destroy();
                        inter = new InterfaceGameOfLife(manageMenu());
                  }else if(inter.getRleRequest()){
                        String modele = inter.getAffichagePrincipal().getModele();
                        int golType = inter.getAffichagePrincipal().getGolType();
                        inter.destroy();
                        inter = new InterfaceGameOfLife(manageRle(modele,golType));
                  }
            }
      }
      
}
