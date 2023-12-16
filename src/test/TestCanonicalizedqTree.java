package test;
import automate.*;
import automate.hashlife.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Cette Classe permet de faire des tests sur les différentes méthodes de la classe CanicalizedqTree.
 * @author : Loic Laloux L2 Informatique TP 3A
 */
public class TestCanonicalizedqTree {
    
    /**
     * Cette méthode permet de tester la méthode create.
     * @return true si la méthode a fonctionné correctement et false sinon.
     */
    @Test
    public static boolean TestCreate(){
        CanonicalizedqTree test1 = new CanonicalizedqTree(true);
        CanonicalizedqTree Test1 = new CanonicalizedqTree(test1,null,null,null);
        CanonicalizedqTree test2 = new CanonicalizedqTree(false);
        CanonicalizedqTree test3 = new CanonicalizedqTree(true);
        CanonicalizedqTree test4 = new CanonicalizedqTree(false);
        CanonicalizedqTree testtree = new CanonicalizedqTree(test1,test2,test3,test4);
        GOLqTreeNode verif_test_1_3 = test1.create(true);
        GOLqTreeNode verif_test_2_4 = test2.create(false);
        GOLqTreeNode verif_test_tree = testtree.create(test1, test2, test3, test4);
        System.out.println("Test Création Commencé");

        try{
            assertTrue(test1.getValeur()==verif_test_1_3.getValeur());
            assertTrue(test2.getValeur()==verif_test_2_4.getValeur());
            assertTrue(test3.getValeur()==verif_test_1_3.getValeur());
            assertTrue(test4.getValeur()==verif_test_2_4.getValeur());
            assertTrue(test1.getProfondeur()==verif_test_1_3.getProfondeur());
            assertTrue(test2.getProfondeur()==verif_test_2_4.getProfondeur());
            assertTrue(test3.getProfondeur()==verif_test_1_3.getProfondeur());
            assertTrue(test4.getProfondeur()==verif_test_2_4.getProfondeur());
            assertTrue(testtree.getValeur()==verif_test_tree.getValeur());
            assertTrue(testtree.getProfondeur()==verif_test_tree.getProfondeur());
            assertTrue(testtree.enfants[0].getValeur()==verif_test_1_3.getValeur());
            assertTrue(testtree.enfants[0].getProfondeur()==verif_test_1_3.getProfondeur());
            assertTrue(testtree.enfants[1].getValeur()==verif_test_2_4.getValeur());
            assertTrue(testtree.enfants[1].getProfondeur()==verif_test_2_4.getProfondeur());
            assertTrue(testtree.enfants[2].getValeur()==verif_test_1_3.getValeur());
            assertTrue(testtree.enfants[2].getProfondeur()==verif_test_1_3.getProfondeur());
            assertTrue(testtree.enfants[3].getValeur()==verif_test_2_4.getValeur());
            assertTrue(testtree.enfants[3].getProfondeur()==verif_test_2_4.getProfondeur());
            assertFalse(test1.getValeur()==verif_test_2_4.getValeur());
            assertFalse(test2.getValeur()==verif_test_1_3.getValeur());
            assertFalse(test3.getValeur()==verif_test_2_4.getValeur());
            assertFalse(test4.getValeur()==verif_test_1_3.getValeur());
            assertFalse(testtree.getValeur()==verif_test_2_4.getValeur());
            assertFalse(testtree.enfants[0].getValeur()==verif_test_2_4.getValeur());
            assertFalse(testtree.enfants[1].getValeur()==verif_test_1_3.getValeur());
            assertFalse(testtree.enfants[2].getValeur()==verif_test_2_4.getValeur());
            assertFalse(testtree.enfants[3].getValeur()==verif_test_1_3.getValeur());


        }
        catch (AssertionError e){
            System.out.println("Test Création échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Création Réussi");

        return true;
    }

    /**
     * Cette méthode permet de tester la méthode equals.
     * @return true si la méthode a fonctionné correctement et false sinon.
     */
    @Test
    public static boolean TestEquals(){
        CanonicalizedqTree test1 = new CanonicalizedqTree(true);
        CanonicalizedqTree Test1 = new CanonicalizedqTree(test1,null,null,null);
        CanonicalizedqTree test2 = new CanonicalizedqTree(false);
        CanonicalizedqTree test3 = new CanonicalizedqTree(true);
        CanonicalizedqTree test4 = new CanonicalizedqTree(false);
        CanonicalizedqTree testtree = new CanonicalizedqTree(test1,test2,test3,test4);

        CanonicalizedqTree test1_test = new CanonicalizedqTree(true);
        CanonicalizedqTree Test1_test = new CanonicalizedqTree(test1,null,null,null);
        CanonicalizedqTree test2_test = new CanonicalizedqTree(false);
        CanonicalizedqTree test3_test = new CanonicalizedqTree(true);
        CanonicalizedqTree test4_test = new CanonicalizedqTree(false);
        CanonicalizedqTree testtree_test = new CanonicalizedqTree(test1,test2,test3,test4);
        System.out.println("Test Egalité Commencé");

        try{
            assertTrue(test1.equals(test1_test));
            assertTrue(test2.equals(test2_test));
            assertTrue(test3.equals(test3_test));
            assertTrue(test4.equals(test4_test));
            assertTrue(testtree.equals(testtree_test));
            assertTrue(test1.equals(Test1_test));
            assertFalse(test2.equals(test1_test));
            assertFalse(test3.equals(test2_test));
            assertFalse(test4.equals(test3_test));
            assertFalse(testtree.equals(test4_test));
            assertFalse(test1.equals(testtree_test));
        }
        catch (AssertionError e){
            System.out.println("Test Egalité échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Egalité Réussi");

        return true;
    }
}
