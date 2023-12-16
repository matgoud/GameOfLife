package test;
import automate.*;
import lecteur_fichier.*;
import graphique.*;
import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Cette classe permet de tester les méthode de la classe Lecteur_CSV
 * @author : Loic Laloux L2 Informatique TP 3A
 */
public class TestLecteur_CSV {
    
    /**
     * Cette méthode permet de tester l'ouverture d'un fichier CSV grace a la méthode Lecture_CSV.getRessource()
     * @return true si la méthode readFile() a correctement fonctionné, false sinon
     */
   @Test
    public static boolean TestFile(){
        String File1 = "test.csv";
        String File2 = "GOL";
        File test1 = Lecture_CSV.getRessource(File1);
        long taille = 2222;
        File test2 = Lecture_CSV.getRessource(File2);
        System.out.println("Test Ouverture Commencé");
        try{
            assertEquals(test1.length(),taille);
            assertEquals(test2.length(),0);

        }
        catch (AssertionError e){
            System.out.println("Test Ouverture échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Ouverture Réussi");

        return true;
    }

    /**
     * Cette méthode permet de tester la lecture d'un fichier CSV grace à la méthode Lecture_CSV.readFile()
     * @return true si la méthode readFile() a correctement fonctionné, false sinon
     */
    @Test
    public static boolean TestReadFile(){
        String first = "B3/S23;Conway's Life";
        String last = "0.565/-0.716/0.565/-0.716/0.627/-0.716/0.565/-0.716/0.565/;Wave;1;5";
        int taille = 86;
        
        System.out.println("Test Contenue Commencé");
        List<String> res = Lecture_CSV.readFile(Lecture_CSV.getRessource("test.csv"));
        try{
            //assertEquals(taille,res.size());
            assertEquals(first,res.get(1));
            assertEquals(last,res.get(res.size()-1));

        }
        catch (AssertionError e){
            System.out.println("Test Contenue échoué");
            e.printStackTrace();
            return false;
        }


        System.out.println("Test Contenue Réussi");

        return true;
    }

    /**
     * Cette méthode permet de tester la séparation entre chaque regles du jeu de la vie et de son code d'un fichier CSV grace à la méthode Lecture_CSV.separer()
     * @return true si la méthode separer() a correctement fonctionné, false sinon
     */
    @Test 
    public static boolean TestSplitFile(){
        System.out.println("Test Ligne Commencé");
        String firstRules = "Conway's Life";
        String firstCode = "B3/S23";
        String lastRules = "Wave";
        String lastCode = "0.565/-0.716/0.565/-0.716/0.627/-0.716/0.565/-0.716/0.565/";
        String lastRandType = "1";
        String lastMode = "5";
        int taille = 86;

        List<String> res = Lecture_CSV.readFile(Lecture_CSV.getRessource("test.csv"));

        List<String[]> test = Lecture_CSV.separer(res);

        try{
            assertEquals(taille,test.size());
            assertEquals(firstRules,test.get(1)[1]);
            assertEquals(firstCode,test.get(1)[0]);
            assertEquals(lastRules,test.get(test.size()-1)[1]);
            assertEquals(lastCode,test.get(test.size()-1)[0]);
            assertEquals(lastRandType,test.get(test.size()-1)[2]);
            assertEquals(lastMode,test.get(test.size()-1)[3]);

        }
        catch (AssertionError e){
            System.out.println("Test Ligne échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Ligne Réussi");
        return true;
    }

    /**
     * Cette méthode permet de tester la recherche de règles dans un fichier CSV grace à la méthode Lecture_CSV.searchRules()
     * @return true si la méthode searchRules() a correctement fonctionné, false sinon
     */
    @Test
    public static boolean TestSearchRules(){
        System.out.println("Test Recherche d'une règles Commencé");
        String firstRules = "Conway's Life";
        String firstCode = "B3/S23";
        String Rules = "Tron";
        String Code = "15/1/2/3/4/5/6/7/8/9/10/11/12/13/14/0/";
        String lastRules = "Wave";
        String lastCode = "0.565/-0.716/0.565/-0.716/0.627/-0.716/0.565/-0.716/0.565/";
        String test1 = Lecture_CSV.searchRules(lastRules, "test.csv");
        String test2 = Lecture_CSV.searchRules(firstRules, "test.csv");
        String test4 = Lecture_CSV.searchRules(Rules, "test.csv");
        try{
            assertEquals(test1,lastCode);
            assertEquals(test2,firstCode);
            assertEquals(test4,Code);

        }
        catch (AssertionError e){
            System.out.println("Test Recherche d'une règles échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Recherche d'une règles Réussi");
        return true;
    }

     /**
     * Cette méthode permet de tester la recherche de mode dans un fichier CSV grace à la méthode Lecture_CSV.searchMode()
     * @return true si la méthode searchMode() a correctement fonctionné, false sinon
     */
    @Test
    public static boolean TestSearchMode(){
        System.out.println("Test Recherche d'un mode Commencé");
        String firstRules = "0/0/0/0/0/0/1/2/4/";
        int firstMode = 1;
        String Rules = "0.68/-0.9/0.68/-0.9/-0.66/-0.9/0.68/-0.9/0.68/";
        int mode = 2;
        String lastRules = "0.565/-0.716/0.565/-0.716/0.627/-0.716/0.565/-0.716/0.565/";
        int lastMode = 5;
        int test1 = Lecture_CSV.searchMode(lastRules, "test2.csv");
        int test2 = Lecture_CSV.searchMode(firstRules, "test2.csv");
        int test4 = Lecture_CSV.searchMode(Rules, "test2.csv");
        try{
            assertEquals(test1,lastMode);
            assertTrue(test2==firstMode);
            assertTrue(test4!=mode);

        }
        catch (AssertionError e){
            System.out.println("Test Recherche d'un mode échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Recherche d'un mode Réussi");
        return true;
    }

    /**
     * Cette méthode permet de tester la recherche de randType dans un fichier CSV grace à la méthode Lecture_CSV.searchRandType()
     * @return true si la méthode searchRandType() a correctement fonctionné, false sinon
     */
    @Test
    public static boolean TestSearchRandType(){
        System.out.println("Test Recherche d'un randType Commencé");
        String firstRules = "0/0/0/0/0/0/1/2/4/";
        int firstMode = -1;
        String Rules = "0.68/-0.9/0.68/-0.9/-0.66/-0.9/0.68/-0.9/0.68/";
        int mode = 0;
        String lastRules = "0.565/-0.716/0.565/-0.716/0.627/-0.716/0.565/-0.716/0.565/";
        int lastMode = 1;
        int test1 = Lecture_CSV.searchRandType(lastRules, "test2.csv");
        int test2 = Lecture_CSV.searchRandType(firstRules, "test2.csv");
        int test4 = Lecture_CSV.searchRandType(Rules, "test2.csv");
        try{
            assertEquals(test1,lastMode);
            assertTrue(test2==firstMode);
            assertTrue(test4!=mode);

        }
        catch (AssertionError e){
            System.out.println("Test Recherche d'un randType échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Recherche d'un randType Réussi");
        return true;
    }

}
