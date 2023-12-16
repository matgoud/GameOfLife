package test;
import automate.*;
import automate.gridlife.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Cette Classe permet de faire des tests sur les différentes méthodes de la classe GOLGrilleLifeLike.
 * @author : Loic Laloux L2 Informatique TP 3A
 */

public class TestGOLGrilleLifeLike {
    
    /**
     * Cette méthode permet de tester la méthode between0And1.
     * @return true si la méthode a fonctionné correctement et false sinon.
     */
    @Test
    public static boolean TestBetween0And1(){
        GOLGrilleLifeLike test = new GOLGrilleLifeLike(100);
        Float test1 = -2f;
        Float test2 = 2f;
        Float test3 = 0.5f;
        System.out.println("Test Between Commencé");
        try{
            assertTrue(test.between0And1(test1)==0f);
            assertTrue(test.between0And1(test2)==1f);
            assertTrue(test.between0And1(test3)==0.5f);
        }
        catch (AssertionError e){
            System.out.println("Test Between échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Between Réussi");

        return true;
    }

    /**
     * Cette méthode permet de tester la méthode tickGameOfLife.
     * @return true si la méthode a fonctionné correctement et false sinon.
     */
    @Test
    public static boolean TesttickGameOfLife(){
        GOLGrilleLifeLike test = new GOLGrilleLifeLike(100);
        Float test1 = 11f;
        Float test2 = 2f;
        Float test3 = 3f;
        System.out.println("Test tickGameoflife Commencé");
        try{
            assertTrue(test.tickGameOfLife(test1)==1f);
            assertTrue(test.tickGameOfLife(test2)==0f);
            assertTrue(test.tickGameOfLife(test3)==1f);
        }
        catch (AssertionError e){
            System.out.println("Test tickGameoflife échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test tickGameoflife Réussi");

        return true;
    }



    /**
     * Cette méthode permet de tester la méthode tickWolfmanRule30.
     * @return true si la méthode a fonctionné correctement et false sinon.
     */
    @Test
    public static boolean TesttickWolfmanRule30(){
        GOLGrilleLifeLike test = new GOLGrilleLifeLike(100);
        Float test1 = 1f;
        Float test2 = 2f;
        Float test3 = 5f;
        System.out.println("Test tickWolfmanRule30 Commencé");
        try{
            assertTrue(test.tickWolfmanRule30(test1));
            assertTrue(test.tickWolfmanRule30(test2));
            assertFalse(test.tickWolfmanRule30(test3));
        }
        catch (AssertionError e){
            System.out.println("Test tickWolfmanRule30 échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test tickWolfmanRule30 Réussi");

        return true;
    }



    /**
     * Cette méthode permet de tester la méthode tickStar.
     * @return true si la méthode a fonctionné correctement et false sinon.
     */
    @Test
    public static boolean TesttickStar(){
        GOLGrilleLifeLike test = new GOLGrilleLifeLike(100);
        Float test1 = -2f;
        Float test2 = 0.5f;
        Float test3 = 3f;
        System.out.println("Test tickStar Commencé");
        try{
            assertTrue(test.tickStar(test1)==1f);
            assertTrue(test.tickStar(test2)==0.5f);
            assertTrue(test.tickStar(test3)==1f);
        }
        catch (AssertionError e){
            System.out.println("Test tickStar échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test tickStar Réussi");

        return true;
    }

    /**
     * Cette méthode permet de tester la méthode tickWorm.
     * @return true si la méthode a fonctionné correctement et false sinon.
     */
    @Test
    public static boolean TesttickWorm(){
        GOLGrilleLifeLike test = new GOLGrilleLifeLike(100);
        Float test1 = -2f;
        Float test2 = 0f;
        Float test3 = 3f;
        System.out.println("Test tickWorm Commencé");
        try{
            assertTrue(test.tickWorm(test1)==(float)(-1f/(Math.pow(2f,(0.6*Math.pow(test1,2f))))+1f));
            assertTrue(test.tickWorm(test2)==0f);
            assertTrue(test.tickWorm(test3)==(float)(-1f/(Math.pow(2f,(0.6*Math.pow(test3,2f))))+1f));
        }
        catch (AssertionError e){
            System.out.println("Test tickWorm échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test tickWorm Réussi");

        return true;
    }

    /**
     * Cette méthode permet de tester la méthode tickSlimeMold.
     * @return true si la méthode a fonctionné correctement et false sinon.
     */
    @Test
    public static boolean TesttickSlimeMold(){
        GOLGrilleLifeLike test = new GOLGrilleLifeLike(100);
        Float test1 = -2f;
        Float test2 = 0f;
        Float test3 = 3f;
        System.out.println("Test tickSlimeMold Commencé");
        try{
            assertTrue(test.tickSlimeMold(test1)==(float)(-1f/(0.89*Math.pow(test1,2f)+1f)+1f));
            assertTrue(test.tickSlimeMold(test2)==(float)(-1f/(0.89*Math.pow(test2,2f)+1f)+1f));
            assertTrue(test.tickSlimeMold(test3)==(float)(-1f/(0.89*Math.pow(test3,2f)+1f)+1f));
        }
        catch (AssertionError e){
            System.out.println("Test tickSlimeMold échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test tickSlimeMold Réussi");

        return true;
    }

    /**
     * Cette méthode permet de tester la méthode tickWave.
     * @return true si la méthode a fonctionné correctement et false sinon.
     */
    @Test
    public static boolean TesttickWave(){
        GOLGrilleLifeLike test = new GOLGrilleLifeLike(100);
        Float test1 = -2f;
        Float test2 = 0f;
        Float test3 = 3f;
        System.out.println("Test tickWave Commencé");
        try{
            assertTrue(test.tickWave(test1)==1f);
            assertTrue(test.tickWave(test2)==0f);
            assertTrue(test.tickWave(test3)==1f);
        }
        catch (AssertionError e){
            System.out.println("Test tickWave échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test tickWave Réussi");

        return true;
    }

    /**
     * Cette méthode permet de tester la méthode tickMitosis.
     * @return true si la méthode a fonctionné correctement et false sinon.
     */
    @Test
    public static boolean TesttickMitosis(){
        GOLGrilleLifeLike test = new GOLGrilleLifeLike(100);
        Float test1 = -2f;
        Float test2 = 0f;
        Float test3 = 3f;
        System.out.println("Test tickMitosis Commencé");
        try{
            assertTrue(test.tickMitosis(test1)==(float)(-1f/(0.9*Math.pow(test1,2f)+1f)+1f));
            assertTrue(test.tickMitosis(test2)==(float)(-1f/(0.9*Math.pow(test2,2f)+1f)+1f));
            assertTrue(test.tickMitosis(test3)==(float)(-1f/(0.9*Math.pow(test3,2f)+1f)+1f));
        }
        catch (AssertionError e){
            System.out.println("Test tickMitosis échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test tickMitosis Réussi");

        return true;
    }


    /**
     * Cette méthode permet de tester la méthode tickFabric.
     * @return true si la méthode a fonctionné correctement et false sinon.
     */
    @Test
    public static boolean TesttickFabric(){
        GOLGrilleLifeLike test = new GOLGrilleLifeLike(100);
        Float test1 = -2f;
        Float test2 = 0f;
        Float test3 = 3f;
        System.out.println("Test tickFabric Commencé");
        try{
            assertTrue(test.tickFabric(test1)==0f);
            assertTrue(test.tickFabric(test2)==0f);
            assertTrue(test.tickFabric(test3)==(float)((Math.exp(2f*test3)-1f)/(Math.exp(2f*test3)+1)));
        }
        catch (AssertionError e){
            System.out.println("Test tickFabric échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test tickFabric Réussi");

        return true;
    }

    /**
     * Cette méthode permet de tester la méthode tickPathways.
     * @return true si la méthode a fonctionné correctement et false sinon.
     */
    @Test
    public static boolean TesttickPathways(){
        GOLGrilleLifeLike test = new GOLGrilleLifeLike(100);
        Float test1 = -2f;
        Float test2 = 0f;
        Float test3 = 3f;
        System.out.println("Test tickPathways Commencé");
        try{
            assertTrue(test.tickPathways(test1)==(float)(1f/Math.pow(2f,(Math.pow(test1-3.5,2f)))));
            assertTrue(test.tickPathways(test2)==(float)(1f/Math.pow(2f,(Math.pow(test2-3.5,2f)))));
            assertTrue(test.tickPathways(test3)==(float)(1f/Math.pow(2f,(Math.pow(test3-3.5,2f)))));
        }
        catch (AssertionError e){
            System.out.println("Test tickPathways échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test tickPathways Réussi");

        return true;
    }


}
