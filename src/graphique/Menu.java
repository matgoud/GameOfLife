package graphique;


import lecteur_fichier.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.util.List;
import automate.*;
import automate.gridlife.*;
import automate.hashlife.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.Principal;

/**
 * Cette classe permet de gérer le menu de l'application et de lancer un automate cellulaire selon le bouton pressé.
 * @author Mathieu Goudal L2 Informatique Groupe TP 3A
 */
public class Menu{

    private JFrame frame;
    private GOL automate;
    private Boolean automateSet;
    private final Image icon = Toolkit.getDefaultToolkit().getImage("ressource/img/glider.png");
    private final Font font = new Font("Arial", Font.BOLD, 30);

    public Menu(){

        this.frame = new JFrame("Menu : Game of life");
        frame.setLayout(null);
        frame.setIconImage(icon);

        JPanel p1 = new JPanel();
        p1.setBounds(270,100,200,50);
        p1.setLayout(new GridLayout());

        JButton hashlifeButton = new JButton("hashlife");
        hashlifeButton.setIcon(new ImageIcon("ressource/img/glider.png"));
        hashlifeButton.setBackground(Color.white);
        hashlifeButton.setHorizontalTextPosition(JButton.CENTER);
        hashlifeButton.setVerticalTextPosition(JButton.BOTTOM);

        p1.add(hashlifeButton);

        JPanel p2 = new JPanel();
        p2.setBounds(270,200,200,50);
        p2.setLayout(new GridLayout());

        JButton margolusButton = new JButton("margolus");
        margolusButton.setIcon(new ImageIcon("ressource/img/critter.png"));
        margolusButton.setBackground(Color.white);
        margolusButton.setHorizontalTextPosition(JButton.CENTER);
        margolusButton.setVerticalTextPosition(JButton.BOTTOM);

        p2.add(margolusButton);

        JPanel p3 = new JPanel();
        p3.setBounds(270,300,200,50);
        p3.setLayout(new GridLayout()); 

        JButton neuralButton = new JButton("neural pattern");
        neuralButton.setIcon(new ImageIcon("ressource/img/glider.png"));
        neuralButton.setBackground(Color.white);
        neuralButton.setHorizontalTextPosition(JButton.CENTER);
        neuralButton.setVerticalTextPosition(JButton.BOTTOM);

        p3.add(neuralButton);

        JLabel titre = new JLabel("GAME OF LIFE");
        titre.setBounds(280,35,360,30);
        titre.setFont(font);

        JPanel principal = new JPanel();
        principal.setLayout(null);
        principal.setBounds(0,0,800,500);
        principal.setBackground(Color.lightGray);

        principal.add(titre);
        principal.add(p1);
        principal.add(p2);
        principal.add(p3);

        //URL fileinfo = classLoader.getResource("img/info.png");
        JButton infoHashlife = new JButton(new ImageIcon("ressource/img/info.png"));
        infoHashlife.setBounds(480,100,50,50);
        infoHashlife.setBackground(Color.lightGray);
        infoHashlife.setBorderPainted(false);

        JButton infoMargolus = new JButton(new ImageIcon("ressource/img/info.png"));
        infoMargolus.setBounds(480,200,50,50);
        infoMargolus.setBackground(Color.lightGray);
        infoMargolus.setBorderPainted(false);

        JButton infoNeural = new JButton(new ImageIcon("ressource/img/info.png"));
        infoNeural.setBounds(480,300,50,50);
        infoNeural.setBackground(Color.lightGray);
        infoNeural.setBorderPainted(false);


        principal.add(infoHashlife);
        principal.add(infoMargolus);
        principal.add(infoNeural);

        frame.add(principal);

        frame.setSize(800,500);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.automateSet = false;
        this.automate = null;
        int taille = 512;

        hashlifeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a){  
                automate = new GOLqTree(taille, "B3/S23");
                automate.setPosX(0);
                automate.setPosY(0);
                automate.setZoom(1);
                for(int i = 0; i < taille;i++)automate.getUnivers().setValAt(taille/2, i, true);
                automate.changeSpeedRequest(0);  
                setAutomateSet(true);          
            }
        });

        margolusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a){  
                automate = new GOLGrilleMargolus(taille,"15/1/2/3/4/5/6/7/8/9/10/11/12/13/14/0/");
                automate.setPosX(0);
                automate.setPosY(0);
                automate.setZoom(1);      
                for(int i = 1; i < taille-1;i++)automate.getUnivers().setValAt(taille/2, i, 1f);  
                setAutomateSet(true);      
            }
        });

        neuralButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a){  
                automate = new GOLGrilleLifeLike(taille,"1/1/1/1/9/1/1/1/1/",0,0);
                automate.setPosX(0);
                automate.setPosY(0);
                automate.setZoom(1);
                for(int i = 1; i < taille-1;i++)automate.getUnivers().setValAt(taille/2, i, 1f);      
                setAutomateSet(true);       
            }
        });

        infoHashlife.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a){  
                JOptionPane.showMessageDialog(null,"Pour plus d'information rdv sur :\nhttps://fr.wikipedia.org/wiki/Hashlife","Information Hashlife",1);      
            }
        });

        infoMargolus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a){  
                JOptionPane.showMessageDialog(null,"Pour plus d'information rdv sur :\nhttps://en.wikipedia.org/wiki/Critters_(cellular_automaton)","Information Margolus",1);      
            }
        });

        infoNeural.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a){  
                JOptionPane.showMessageDialog(null,"Pour plus d'information rdv sur :\nhttps://bdtechtalks.com/2020/09/16/deep-learning-game-of-life/","Information Neural pattern",1);      
            }
        });

    }

    /**
     * Cette méthode permet de récupérer l'automate cellulaire actuel.
     * @return l'automate cellulaire actuel
     */
    public GOL getGOL(){
        return automate;
    }

    /**
     * Cette méthode renvoie l'état de l'initialisation de l'automate cellulaire actuel.
     * @return true si l'automate cellulaire est initialisé et false sinon
     */
    public Boolean getAutomateSet() {
        return automateSet;
    }

    /**
     * Cette méthode permet de modifier l'état de l'initialisation de l'automate cellulaire actuel.
     * @param automateSet nouvel état de l'initialisation
     */
    public void setAutomateSet(Boolean automateSet) {
        this.automateSet = automateSet;
    }

    /**
     * Cette méthode renvoie la Jframe contenant le menu
     * @return JFrame contenant le menu
     */
    public JFrame getFrame(){
        return frame;
    }

}
