package graphique;

import javax.swing.*;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.util.List;
import automate.*;
import automate.gridlife.*;
import automate.hashlife.*;
import lecteur_fichier.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Cette classe permet de générer le Panel contenant l'affichage principal d'un automate cellulaire.
 * @author Mathieu Goudal L2 Informatique groupe 3A
 */
public class AffichagePrincipal{

    private JPanel affichagePrincipal;
    private JPanel options;
    private JPanel affichageGOL;
    private JButton clear;
    private JButton random;
    private JTextField rule;
    private JMenuBar rulesMenuBar;
    private JButton run;
    private JButton speed;
    private JButton secret;
    private JButton controle;
    private JButton retour;
    public Boolean running;
    private JToolBar optionBar;
    private Boolean changeSpeed;
    private InterfaceGameOfLife inter;
    private Boolean menu;
    private Boolean rleRequest;
    private String modele;
    private int golType;

    public AffichagePrincipal(InterfaceGameOfLife inter){    

        this.inter = inter;
        this.menu = false;

        if(inter.getGOL() instanceof GOLqTree){
            this.golType = 1;
        }else if(inter.getGOL() instanceof GOLGrilleLifeLike){
            this.golType = 2;
        }else{
            this.golType = 3;
        }

        this.modele  = "";

        //Panel principal
        this.affichagePrincipal = new JPanel();
        affichagePrincipal.setLayout(null);
        affichagePrincipal.setBounds(0,0,1300,1059);

        //Panel d'options
        this.options = new JPanel();
        options.setBackground(Color.lightGray);
        options.setBounds(0, 0,1300,35);

        //Panel d'affichage GOL
        this.affichageGOL = new JPanel();
        affichageGOL.setBackground(Color.white);
        affichageGOL.setLayout(new GridLayout());
        affichageGOL.add(new DrawGOL(inter.getGOL()));
        affichageGOL.setBounds(0, 35,1300,1029);

        /*Les images*/
        ImageIcon play = new ImageIcon("ressource/img/play.png");
        ImageIcon pause = new ImageIcon("ressource/img/pause.png");
        ImageIcon glider = new ImageIcon("ressource/img/glider.png");
        ImageIcon move = new ImageIcon("ressource/img/move.png");
        ImageIcon home = new ImageIcon("ressource/img/home.png");

        /*Les boutons*/
        this.clear = new JButton("clear");   
        this.random = new JButton("random");
        this.speed = new JButton("speed");
        this.run = new JButton(play);
        this.secret = new JButton(glider);
        this.controle = new JButton(move);
        this.retour = new JButton(home);
        clear.setBackground(Color.white);
        random.setBackground(Color.white);
        speed.setBackground(Color.white);
        run.setBackground(Color.white);
        controle.setBackground(Color.white);
        retour.setBackground(Color.white);
        secret.setBackground(Color.lightGray);
        secret.setBorderPainted(false);

        this.rule = new JTextField(20);
        String text = "";
        if(inter.getGOL() instanceof GOLGrilleLifeLike ){
            text = "1/1/1/1/9/1/1/1/1/";
            speed.setEnabled(false);
        }
        else if(inter.getGOL() instanceof GOLGrilleMargolus){
            text = "15/1/2/3/4/5/6/7/8/9/10/11/12/13/14/0/";
            speed.setEnabled(false);
        }
        else{
            text = "B3/S23";
        }
        rule.setText(text);
        
        rulesMenuBar = new JMenuBar();
        JComboBox<String> listeRules = new JComboBox<>();
        List<String[]> splitline;
        File file;
        if(inter.getGOL() instanceof GOLGrilleMargolus){
            file = Lecture_CSV.getRessource("Margolus.csv");
        }
        else if(inter.getGOL() instanceof GOLGrilleLifeLike){
            file = Lecture_CSV.getRessource("LifeLike.csv");
        }
        else{
            file = Lecture_CSV.getRessource("GOL.csv");
        }

        List<String> line = Lecture_CSV.readFile(file);
        splitline = Lecture_CSV.separer(line);
        for (int i = 1; i < splitline.size(); i++) {
            String ruleName = splitline.get(i)[1];
            listeRules.addItem(ruleName);
        }
        rulesMenuBar.add(listeRules);

        JMenuBar modeleBar = new JMenuBar();
        JComboBox<String> listeModele = new JComboBox<>();
        List<String[]> split;
        File filerle;
        if(inter.getGOL() instanceof GOLqTree){
            filerle = Lecture_CSV.getRessource("hashlifeRLE.csv");
        }else{
            filerle = Lecture_CSV.getRessource("margolus&neural.csv");
        }
        List<String> ligne = Lecture_CSV.readFile(filerle);
        split = Lecture_CSV.separer(ligne);
        for (int i = 0; i < split.size(); i++) {
            String modeleName = split.get(i)[0];
            listeModele.addItem(modeleName);
        }
        modeleBar.add(listeModele);

        this.optionBar = new JToolBar();
        optionBar.setBackground(Color.lightGray);
        optionBar.setFloatable(false);

        optionBar.add(rule);    
        optionBar.add(rulesMenuBar);
        optionBar.add(run);
        optionBar.add(random);
        optionBar.add(clear);
        optionBar.add(speed);
        optionBar.add(modeleBar);
        options.add(retour,0);
        options.add(controle,1);
        options.add(secret,2);
        options.add(optionBar,3);

        
        clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                inter.getGOL().clearUnivers();
                affichageGOL.repaint();
            }
        });

        listeRules.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < splitline.size(); i++) {
                    if(splitline.get(i)[1] == listeRules.getSelectedItem()){
                        rule.setText(splitline.get(i)[0]);
                    }
                }
                changeRule();
            }
        });

        this.rleRequest = false;
        listeModele.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                modele = listeModele.getSelectedItem().toString();
                if(modele.equals("modele") ){
                    return;
                }else{
                    int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment importer : "+modele+"?","Importation d'un nouveau modèle",1);
                    if (choix == JOptionPane.YES_OPTION) {
                        rleRequest = true;
                    } else {
                        return;
                    }
                }

            }
        });

        random.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inter.getGOL().randomizeUnivers();
                affichageGOL.repaint();
            }
        });

        this.changeSpeed = false;
        speed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeSpeed = true;
                if(inter.getGOL().getSpeed() == 0){
                    inter.getGOL().changeSpeedRequest(1);
                }else {
                    inter.getGOL().changeSpeedRequest(0);
                }
                affichageGOL.revalidate();
            }
        });

        this.running = false;
        run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(running){
                    running = false;
                    run.setIcon(play);
                }else{
                    running = true;
                    run.setIcon(pause);
                }
            }
        });

        secret.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"©Andrieux,Bartolone,Goudal,Laloux","Créateurs",1);
            }
        });

        affichageGOL.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int posClicY=(int)((e.getY()-512)/inter.getGOL().getZoom()+512-inter.getGOL().getPosY());
                int posClicX=(int)((e.getX()-512)/inter.getGOL().getZoom()+512-inter.getGOL().getPosX());
                Object tmp = inter.getGOL().getUnivers().getValAt(posClicY,posClicX);
                if(tmp instanceof Float){
                    Float val = (Float)tmp;
                    //inter.getGOL().getUnivers().setValAt(e.getY(), e.getX(), 1f-val);
                    if(inter.getGOL().getZoom()>=1f) inter.getGOL().getUnivers().setValAt(posClicY,posClicX, 1f-val);
                    else{
                        int size= (int)(1/inter.getGOL().getZoom());
                        for(int i=0;i<size;i++){
                            for(
                                int j=0;j<size;j++){
                                val = (Float)inter.getGOL().getUnivers().getValAt(posClicY+i,posClicX+j);
                                inter.getGOL().getUnivers().setValAt(posClicY+i,posClicX+j, 1f-val);
                            }
                        }
                    }
                    //(e.getY()-inter.getGOL().getPosY())/inter.getGOL().getZoom()
                    //System.out.println(" clic en y:"+e.getY()+"x:"+e.getX());
                    //System.out.println(" reelement en y:"+posClicY+"x:"+posClicX);
                    //System.out.println("posY:"+inter.getGOL().getPosY()+",posX:"+inter.getGOL().getPosX()+",zoom:"+inter.getGOL().getZoom());
                }
                else if(tmp instanceof Boolean){
                    try{
                        Boolean val = (Boolean)tmp;
                        if(inter.getGOL().getZoom()>=1f){
                            if(val) inter.getGOL().getUnivers().setValAt(posClicY, posClicX, false);
                            else inter.getGOL().getUnivers().setValAt(posClicY,posClicX, true);
                        }
                        else{
                            int size= (int)(1/inter.getGOL().getZoom());
                            for(int i=0;i<size;i++){
                                for(int j=0;j<size;j++){
                                    val = (Boolean)inter.getGOL().getUnivers().getValAt(posClicY+i,posClicX+j);
                                    if(val) inter.getGOL().getUnivers().setValAt(posClicY+i,posClicX+j, false);
                                    else inter.getGOL().getUnivers().setValAt(posClicY+i,posClicX+j, true);
                                }
                            }
                        }
                    }catch(Exception exe){
                        System.out.println("il faut vider l'arbre avant, un arbre canonisé est immutable!");
                    }
                }
                affichageGOL.repaint();
            }
        });

        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a){  
                setMenu(true);             
            }
        });

        controle.addKeyListener(new Clavier(inter.getGOL(),affichageGOL));
        run.addKeyListener(new Clavier(inter.getGOL(),affichageGOL));

        affichagePrincipal.add(options);
        affichagePrincipal.add(affichageGOL);

    }

    /**
     * Cette méthode permet de changer la règles de automate cellulaire et désactive le bouton random pour certaine règles.
     */
    public void changeRule(){

        inter.getGOL().setRule(rule.getText());

        if(inter.getGOL() instanceof GOLGrilleLifeLike){
            if(inter.getGOL().getRules().equals("0/0/0/0/0/0/1/2/4/")){
                inter.getGOL().clearUnivers();
                int taille= inter.getGOL().getUnivers().getTaille();
                inter.getGOL().getUnivers().setValAt(taille/2,taille/2, 1f);
                affichageGOL.repaint();
                random.setEnabled(false);
                inter.getGOL().setRandType(-1);
                inter.getGOL().setMode(1);
            }
            else {
                random.setEnabled(true);
                inter.getGOL().setRandType(Lecture_CSV.searchRandType(inter.getGOL().getRules(),"LifeLike.csv"));
                inter.getGOL().setMode(Lecture_CSV.searchMode(inter.getGOL().getRules(),"LifeLike.csv"));
                
            }

        }
        else if(inter.getGOL() instanceof GOLGrilleMargolus){
            if(inter.getGOL().getRules().equals("0/7/11/3/13/5/6/7/14/9/10/11/12/13/14/15/")){
                inter.getGOL().clearUnivers();
                int taille= inter.getGOL().getUnivers().getTaille();
                inter.getGOL().getUnivers().setValAt(taille/2,taille/2, 1f);
                inter.getGOL().getUnivers().setValAt(taille/2+1,taille/2+1, 1f);
                affichageGOL.repaint();
                random.setEnabled(false);
                inter.getGOL().setRandType(-1);
            }
            else {
                random.setEnabled(true);
                inter.getGOL().setRandType(0);
            }
        }

    }


    /**
     * Cette méthode renvoie le Panel contenant l'affichage principal
     * @return Panel contenant l'affichage principal.
     */
    public JPanel getAffichagePrincipal(){
        return affichagePrincipal;
    }

    /**
     * Cette méthode renvoie l'état de automate cellulaire.
     * @return true si l'automate cellulaire est en cours et false si l'automate cellulaire est arrété.
     */
    public Boolean getRunning(){
        return running;
    }

    /**
     * Cette méthode vérifie si une requête pour accéder a un modèle rle.
     * @return true si une requête pour accéder a un modèle rle a été envoyé et false sinon
     */
    public Boolean getRle(){
        return rleRequest;
    }

    /**
     * Cette méthode renvoie le Panel contenant l'affichage GOL.
     * @return Panel contenant l'affichage de automate cellulaire
     */
    public JPanel getAffichageGOL(){
        return affichageGOL;
    }

    /**
     * Cette méthode renvoie le bouton permettant d'aller au menu.
     * @return Bouton permettant d'aller au menu
     */
    public JButton getRetour(){
        return retour;
    }

    /**
     * Cette méthode permet de mettre a jour l'affichage de l'automate cellulaire.
     * @param automate automate cellulaire
     */
    public void change(GOL automate){
        affichageGOL.removeAll();
        affichageGOL.add(new DrawGOL(automate));
    }


    /**
     * Cette méthode renvoie l'état du menu.
     * @return true si le bouton retour a été pressée et false sinon
     */
    public Boolean getMenu(){
        return menu;
    }
    
    /**
     * Cette méthode permet de modifier l'état du menu.
     */
    public void setMenu(Boolean bool){
        menu = bool;
    }

    /**
     * Cette méthode permet de retourner le modele choisi.
     * @return le modele choisi
     */
    public String getModele(){
        return modele;
    }

    /**
     * Cette méthode permet de retourner le type de l'automate cellulaire.
     * @return 1 si l'automate cellulaire est une instance de GOLGrilleLifeLike, 2 si c'est une instance de GOLGrilleLifeLike, 3 si c'est une instance de GOLGrilleMargolus.
     */
    public int getGolType(){
        return golType;
    }

}
