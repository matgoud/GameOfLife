package lecteur_fichier;

import java.io.*;
import java.util.*;

/**
 * Cette classe permet de lire le contenu d'un fichier csv.
 * @author Loic Laloux L2 Informatique Groupe TP 3A
 */
public class Lecture_CSV{

    /**
     * Cette fonction cherche et renvoie le chemin complet du fichier.
     * @param fileName est le nom du fichier ainsi que son extension sous la forme nom.csv
     * requires résultat = le chemin total du fichier
     * @return le chemin système du fichier
     */
    
    public static String getRessourcePath(String fileName){
        File f = new File("");
        String dossierPath = f.getAbsolutePath()+File.separator+"ressource/CSV/"+fileName;
        return dossierPath;
    }

    /**
     * Cette fonction donne un accès au fichier csv.
     * @param fileName est le nom du fichier ainsi que son extension sous la forme nom.csv
     * @return l'accès au fichier
     */

    public static File getRessource(String fileName){
        String completeFileName = getRessourcePath(fileName);
        File file = new File(completeFileName);
        return file;
    }

    /**
     * Cette fonction ouvre le fichier et transforme son contenu en liste de String
     * @param file est l'accès au fichier.csv
     * ensures file à été initialisé grâce à getRessource
     * @return une liste d'entier contenant les données présent dans le fichier csv
     */

    public static List<String> readFile(File file){
        List<String> res = new ArrayList<String>();

        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            for(String line = br.readLine();line!=null;line = br.readLine()){
                res.add(line);
            }

            br.close();
            fr.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return res;
    }

    /**
     * Cette fonction sépare les données présent dans le fichier csv et 
     * renvoie une liste de tableau de String contenant les données.
     * @param listeRegle est une liste d'entier contenant les données présent dans le fichier csv
     * ensures le fichier csv a pour séparateur ";"
     * ensures listeregle a été initialisé par readFile
     * requires résultat = est une liste contenant plusieurs tableaux tel que :[1ere donnée, 2eme donnée, ... ,nième donnée de la ligne du fichier csv ]
     * @return une liste de tableaux de String contenant les données du fichier csv
     */

    public static List<String[]> separer(List<String>listeRegle){
        List<String[]> res = new ArrayList<String[]>();
        for(int i = 0 ;i<listeRegle.size();i++){
            res.add(listeRegle.get(i).split(";")); 
        }
        return res;
    }

    /**
     * Fonction qui affiche le contenue de la liste dans le terminal.
     * @param listeRegle est une liste de tableaux de String contenant les données du fichier csv
     * ensures listeRegle a été correctement initialisé via la methode separer
     */

    public static void afficherContenu(List<String[]> listeRegle){
        for(int i = 0 ;i<listeRegle.size();i++){
            System.out.println(Arrays.toString(listeRegle.get(i)));
        }
    }

    /**
     * Cette fonction recherche la règle dans la liste de tableaux de String et renvoie le code pour un l'automate cellulaire.
     * @param rules est la règle que l'on veut 
     * @param path est le nom du fichier csv
     * @return le code de l'automate cellulaire correspondant à la règle
     */
    
    public static String searchRules(String rules, String path){
        List<String[]> listeRegle = separer(readFile(getRessource(path)));
        String res = "Erreur";
        for(int i = 0 ;i<listeRegle.size();i++){
            res = listeRegle.get(i)[1]+" "+ rules;
            if (rules.equals(listeRegle.get(i)[1])){
                res = listeRegle.get(i)[0];
                return res;
            }
        }
        return res;
    }

    /**
     * Cette fonction recherche la règle dans la liste de tableaux de String et renvoie le mode pour un automate cellulaire.
     * @param rules est le code de la règle que l'on veut 
     * @param path est le nom du fichier csv
     * @return le mode de l'automate cellulaire correspondant à la règle
     */
    public static int searchMode(String rules, String path){
        List<String[]> listeRegle = separer(readFile(getRessource(path)));
        int res = -1;
        for(int i = 0 ;i<listeRegle.size();i++){
            //res = listeRegle.get(i)[1]+" "+ rules;
            if (rules.equals(listeRegle.get(i)[0])){
                String Stringres = listeRegle.get(i)[3];
                res = Integer.parseInt(Stringres);
                return res;
            }
        }
        return res;
    }

    /**
     * Cette fonction recherche la règle dans la liste de tableaux de String et renvoie le randType pour un automate cellulaire.
     * @param rules est le code de la règle que l'on veut 
     * @param path est le nom du fichier csv
     * @return le randType de l'automate cellulaire correspondant à la règle
     */
    public static int searchRandType(String rules, String path){
        List<String[]> listeRegle = separer(readFile(getRessource(path)));
        int res = -1;
        for(int i = 0 ;i<listeRegle.size();i++){
            //res = listeRegle.get(i)[1]+" "+ rules;
            if (rules.equals(listeRegle.get(i)[0])){
                String Stringres = listeRegle.get(i)[2];
                res = Integer.parseInt(Stringres);
                return res;
            }
        }
        return res;
    }
}
