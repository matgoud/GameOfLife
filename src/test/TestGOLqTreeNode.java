package test;
import automate.*;
import automate.hashlife.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Cette classe permet de tester les méthodes de la classe GOLqTreeNode.
 * @author : Loic Laloux L2 Informatique TP 3A
 */
public class TestGOLqTreeNode {

    /**
     * Cette méthode permet de tester la méthode create.
     * @return true si la méthode a fonctinné correctement et false sinon.
     */
    @Test
    public static boolean TestCreate(){
        GOLqTreeNode test1 = new GOLqTreeNode(true);
        GOLqTreeNode Test1 = new GOLqTreeNode(test1,null,null,null);
        GOLqTreeNode test2 = new GOLqTreeNode(false);
        GOLqTreeNode test3 = new GOLqTreeNode(true);
        GOLqTreeNode test4 = new GOLqTreeNode(false);
        GOLqTreeNode testtree = new GOLqTreeNode(test1,test2,test3,test4);
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
     * Cette méthode permet de tester la méthode generateCenterNode.
     * @return true si la méthode a fonctinné correctement et false sinon.
     */
    @Test
    public static boolean TestGenerateCenterNode(){
        GOLqTreeNode test1 = new GOLqTreeNode(new GOLqTreeNode(true),new GOLqTreeNode(true),new GOLqTreeNode(true),new GOLqTreeNode(true));
        GOLqTreeNode test2 = new GOLqTreeNode(new GOLqTreeNode(false),new GOLqTreeNode(false),new GOLqTreeNode(false),new GOLqTreeNode(false));
        GOLqTreeNode test3 = new GOLqTreeNode(new GOLqTreeNode(false),new GOLqTreeNode(true),new GOLqTreeNode(false),new GOLqTreeNode(true));
        GOLqTreeNode test4 = new GOLqTreeNode(new GOLqTreeNode(true),new GOLqTreeNode(false),new GOLqTreeNode(true),new GOLqTreeNode(false));

        GOLqTreeNode testtree = new GOLqTreeNode(test1, test2, test3, test4);
        
        System.out.println("Test Node Centrale commencé");

        GOLqTreeNode centre = new GOLqTreeNode(new GOLqTreeNode(true),new GOLqTreeNode(false) ,new GOLqTreeNode(true) ,new GOLqTreeNode(true) );
        GOLqTreeNode verif_centre = testtree.generateCenterNode();
        

        try{
            assertTrue(centre.getValeur()==verif_centre.getValeur());
            assertTrue(centre.enfants[0].getValeur()==verif_centre.enfants[0].getValeur());
            assertTrue(centre.enfants[1].getValeur()==verif_centre.enfants[1].getValeur());
            assertTrue(centre.enfants[2].getValeur()==verif_centre.enfants[2].getValeur());
            assertTrue(centre.enfants[3].getValeur()==verif_centre.enfants[3].getValeur());
            assertEquals(testtree.getProfondeur()/2,verif_centre.getProfondeur());
        }
        catch (AssertionError e){
            System.out.println("Test Node Centrale échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Node Centrale Réussi");

        return true;
    }
    


}
