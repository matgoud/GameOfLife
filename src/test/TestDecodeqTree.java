package test;
import automate.*;
import automate.hashlife.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Cette Classe permet de faire des tests sur les différentes méthodes de la classe DecodeqTree.
 * @author : Loic Laloux L2 Informatique TP 3A
 */
public class TestDecodeqTree {
    

    /**
     * Cette méthode permet de tester la méthode getBirth() de la classe DecodeqTree.
     * @return true si la méthode a fonctionné correctement et false sinon.
     */
    @Test
    public static boolean TestGetBirth() {
        DecodeqTree test = new DecodeqTree();
        DecodeqTree test2 = new DecodeqTree("B0/S0");
        DecodeqTree test3 = new DecodeqTree("B1/S1");
        DecodeqTree test4 = new DecodeqTree("B2/S2");
        System.out.println("Test Naissance Commencé");

        try{
            assertTrue(test.getBirth(3));
            assertTrue(test2.getBirth(0));
            assertTrue(test3.getBirth(1));
            assertTrue(test4.getBirth(2));
            assertFalse(test.getBirth(4));
            assertFalse(test2.getBirth(1));
            assertFalse(test3.getBirth(2));
            assertFalse(test4.getBirth(3));


        }
        catch (AssertionError e){
            System.out.println("Test Naissance échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Naissance Réussi");

        return true;
    }

    /**
     * Cette méthode permet de tester la méthode getSurvive() de la classe DecodeqTree.
     * @return true si la méthode a fonctionné correctement et false sinon.
     */
    @Test
    public static boolean TestGetSurvive() {
        DecodeqTree test = new DecodeqTree();
        DecodeqTree test2 = new DecodeqTree("B0/S0");
        DecodeqTree test3 = new DecodeqTree("B1/S1");
        DecodeqTree test4 = new DecodeqTree("B2/S2");
        System.out.println("Test Survie Commencé");

        try{
            assertTrue(test.getSurvive(3));
            assertTrue(test.getSurvive(2));
            assertTrue(test2.getSurvive(0));
            assertTrue(test3.getSurvive(1));
            assertTrue(test4.getSurvive(2));
            assertFalse(test.getSurvive(4));
            assertFalse(test2.getSurvive(1));
            assertFalse(test3.getSurvive(2));
            assertFalse(test4.getSurvive(3));


        }
        catch (AssertionError e){
            System.out.println("Test Survie échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Survie Réussi");

        return true;
    }

    /**
     * Cette méthode permet de tester la méthode areRulesValid() de la classe DecodeqTree.
     * @return true si la méthode a fonctionné correctement et false sinon.
     */
    @Test
    public static boolean TestAreRulesValid() {
        DecodeqTree test = new DecodeqTree();
        DecodeqTree test2 = new DecodeqTree("B/S0");
        DecodeqTree test3 = new DecodeqTree("B1/S");
        DecodeqTree test4 = new DecodeqTree("B/S");
        DecodeqTree test5 = new DecodeqTree("B3S3");
        DecodeqTree test6 = new DecodeqTree("B4/4");
        DecodeqTree test7 = new DecodeqTree("5/S5");
        DecodeqTree test8 = new DecodeqTree("6/6");
        DecodeqTree test9 = new DecodeqTree("B7/S7");
        DecodeqTree test10 = new DecodeqTree("B8/S8");
        System.out.println("Test Validité des règles Commencé");

        try{
            assertTrue(test.areRulesValid());
            assertTrue(test2.areRulesValid());
            assertTrue(test3.areRulesValid());
            assertTrue(test4.areRulesValid());
            assertFalse(test5.areRulesValid());
            assertFalse(test6.areRulesValid());
            assertFalse(test7.areRulesValid());
            assertFalse(test8.areRulesValid());
            assertTrue(test9.areRulesValid());
            assertTrue(test10.areRulesValid());

        }
        catch (AssertionError e){
            System.out.println("Test Validité des règles échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Validité des règles Réussi");

        return true;
    }


    /**
     * Cette méthode permet de tester la méthode useRules() de la classe DecodeqTree.
     * @return true si la méthode a fonctionné correctement et false sinon.
     */
    @Test
    public static boolean TestUseRules() {
        DecodeqTree test = new DecodeqTree();
        DecodeqTree test2 = new DecodeqTree("B/S0");
        DecodeqTree test3 = new DecodeqTree("B1/S");
        DecodeqTree test4 = new DecodeqTree("B/S");
        DecodeqTree test5 = new DecodeqTree("B3S3");
        DecodeqTree test6 = new DecodeqTree("B4/4");
        DecodeqTree test7 = new DecodeqTree("5/S5");
        DecodeqTree test8 = new DecodeqTree("6/6");
        DecodeqTree test9 = new DecodeqTree("B7/S7");
        DecodeqTree test10 = new DecodeqTree("B8/S8");
        System.out.println("Test Utilisation des règles Commencé");

        try{
            test.useRules();
            test2.useRules();
            test3.useRules();
            test4.useRules();
            test5.useRules();
            test6.useRules();
            test7.useRules();
            test8.useRules();
            test9.useRules();
            test10.useRules();
            assertTrue(test.getBirth(3));
            assertTrue(test.getSurvive(2));
            assertTrue(test.getSurvive(3));

            assertTrue(test2.getSurvive(0));

            assertTrue(test3.getBirth(1));

            assertTrue(test5.getBirth(3));
            assertTrue(test5.getSurvive(2));
            assertTrue(test5.getSurvive(3));

            
            assertTrue(test6.getBirth(3));
            assertTrue(test6.getSurvive(2));
            assertTrue(test6.getSurvive(3));

            assertTrue(test7.getBirth(3));
            assertTrue(test7.getSurvive(2));
            assertTrue(test7.getSurvive(3));

            
            assertTrue(test8.getBirth(3));
            assertTrue(test8.getSurvive(2));
            assertTrue(test8.getSurvive(3));

            assertTrue(test9.getBirth(7));
            assertTrue(test9.getSurvive(7));

            assertTrue(test10.getBirth(8));
            assertTrue(test10.getSurvive(8));

        }
        catch (AssertionError e){
            System.out.println("Test Utilisation des règles échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Utilisation des règles Réussi");

        return true;

    }
}
