package lecteur_fichier;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import automate.GOL;
import automate.gridlife.GOLGrilleLifeLike;
import automate.gridlife.GOLGrilleMargolus;
import automate.hashlife.GOLqTree;

/**
 * Cette classe permet d'importer des modèles de cellules pour un automate cellulaire.
 * 
 * @author Mathieu Goudal L2 Informatique groupe 3A
 */

public class LecteurRLE {

	JFrame frame;
	JProgressBar progressionBar;
	private final Font font = new Font("Arial", Font.BOLD, 30);
	private final Image icon = Toolkit.getDefaultToolkit().getImage("ressource/img/glider.png");

	/**
	 * Constructeur de la classe LecteurRLE.
	 * Il crée une fenêtre qui indique la progression de l'importation.
	 */
	public LecteurRLE() {

		this.frame = new JFrame("importation");
		frame.setLayout(null);
		frame.setIconImage(icon);

		this.progressionBar = new JProgressBar(0);
		progressionBar.setMinimum(0);
		progressionBar.setMaximum(100);

		JLabel texte = new JLabel("Chargement du modèle en cours");
		texte.setFont(font);

		JPanel prinicipal = new JPanel();
		prinicipal.setBackground(Color.lightGray);
		prinicipal.setBounds(0, 0, 600, 200);

		prinicipal.add(texte);
		prinicipal.add(progressionBar);

		frame.add(prinicipal);
		frame.setSize(600, 200);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

	}

	/**
	 * Calcule la puissance de 2 supérieure à un nombre donné.
	 * 
	 * @param nombre Le nombre pour lequel on souhaite trouver la puissance de 2
	 *               supérieure.
	 * @return La puissance de 2 supérieure à nombre.
	 */
	public int puissanceDeuxSuperieure(int nombre) {
		int puissance = 1;
		while (puissance < nombre) {
			puissance *= 2;
		}
		return puissance;
	}

	public long countLine(String fileName) {

		long lines = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			while (reader.readLine() != null) lines++;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
  
	}

	/**
	 * Importe un modèle de cellules à partir d'un fichier RLE.
	 * 
	 * @param golType  Le type de l'automate cellulaire (1 pour Hashlife, 2 pour
	 *                 GrilleLifeLike, 3 pour GrilleMargolus).
	 * @param filepath Le chemin d'accès du fichier RLE.
	 * @param Px        La coordonnée x de la cellule supérieure gauche du modèle
	 *                 dans l'univers de l'automate.
	 * @param Py        La coordonnée y de la cellule supérieure gauche du modèle
	 *                 dans l'univers de l'automate.
	 * @return Un objet GOL qui représente le modèle importé.
	 */
	public GOL importRLE(int golType,String filepath, int Px, int Py) {
      	int x = 0;
      	int y = 0;
     	GOL gol = null;
      	Float[] couleurs = new Float[]{0f,0f,0f};
      	try{
			double nblignes = countLine(filepath);
			double lignes = 0;
    		Object value = 1.0f;
    		BufferedReader br = new BufferedReader(new FileReader(filepath));
    		String inputLine = null ;            // ligne actuelle
    		int paramArgument = 0 ;         	 // notre argument parametre pour determiner quand incrémenter
												 //notre position horizontale
    		while ((inputLine = br.readLine()) != null) {
				lignes++;
				progressionBar.setValue((int)((lignes/nblignes)*100.0));	//permet de voir en temps reel l'importation
    		    //on ignore les lignes commentaires
    		    if (inputLine.startsWith("#"))
    		        continue ;
    		    if (inputLine.startsWith("x")) {
    		        String[] tokens = inputLine.split(",");
    		        int taille = Integer.parseInt(tokens[0].split("=")[1].trim());
    		        if(golType == 1){
    		            gol = new GOLqTree(puissanceDeuxSuperieure(taille),"B3/S23");
    		            value = true;
    		        }else if(golType == 2){
    		            gol = new GOLGrilleLifeLike(0,"ignorez ca",0);
    		            gol = new GOLGrilleLifeLike(taille,"1/1/1/1/9/1/1/1/1/",0,0,couleurs);
    		            value = 1.0f;
    		        }else{
    		            gol = new GOLGrilleMargolus(taille,"15/1/2/3/4/5/6/7/8/9/10/11/12/13/14/0/",couleurs);
    		            value = 1.0f;
    		        }
    		        continue;
    		    }
    		 	inputLine = inputLine.trim() ;
    		 	for (int i=0; i<inputLine.length(); i++) {
    		    	char c = inputLine.charAt(i) ;
    		    	int param = (paramArgument == 0 ? 1 : paramArgument) ;
    		    	if (c == 'b') {
    		    	   x += param ;
    		    	   paramArgument = 0 ;
    		    	} else if (c == 'o') {
    		    	   while (param-- > 0)
    		    	    	gol.getUnivers().setValAt(y + Py, (x++) + Px,value);
    		    	   paramArgument = 0 ;
    		    	} else if (c == '$') {
    		    	   y += param ;
    		    	   x = 0 ;
    		    	   paramArgument = 0 ;
    		    	} else if ('0' <= c && c <= '9') {
    		    	   paramArgument = 10 * paramArgument + c - '0' ;
    		    	} else if (c == '!') {
    		    	   break;
    		    	}
    			}
    		}
			br.close();	//on ferme notre lecteur
		}catch (IOException e) {
    		e.printStackTrace();
    	}
      	frame.dispose();
    	return gol;
    }

}
