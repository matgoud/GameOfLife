package test;
import automate.*;
import automate.gridlife.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Cette Classe permet de faire des tests sur les différentes méthodes de la classe DecodeGrilleMargolus.
 * @author : Loic Laloux L2 Informatique TP 3A
 */

public class TestDecodeGrilleMargolus {


    /**
     * Cette méthode permet de tester la méthode getTabRegles()
     * @return true si la méthode a fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestGetTabRegles(){
        DecodeGrilleMargolus test = new DecodeGrilleMargolus();
        DecodeGrilleMargolus test1 = new DecodeGrilleMargolus("15/1/2/3/4/5/6/7/8/9/10/11/12/13/14/0/");
        DecodeGrilleMargolus test2 = new DecodeGrilleMargolus("15/14/13/3/11/5/6/1/7/9/10/2/12/4/8/0/");
        DecodeGrilleMargolus test3 = new DecodeGrilleMargolus("0/7/11/3/13/5/6/7/14/9/10/11/12/13/14/15/");
        DecodeGrilleMargolus test4 = new DecodeGrilleMargolus("0.565/-0.716/0.565/-0.759/0.627/-0.759/0.565/-0.716/0.565/");
        DecodeGrilleMargolus test5 = new DecodeGrilleMargolus("B3/S23");

        System.out.println("Test Table de régles Commencé");
        try{
            assertTrue(test.getTabRegles()[0]==0);
            assertTrue(test.getTabRegles()[1]==1);
            assertTrue(test.getTabRegles()[2]==2);
            assertTrue(test.getTabRegles()[3]==3);
            assertTrue(test.getTabRegles()[4]==4);
            assertTrue(test.getTabRegles()[5]==5);
            assertTrue(test.getTabRegles()[6]==6);
            assertTrue(test.getTabRegles()[7]==7);
            assertTrue(test.getTabRegles()[8]==8);
            assertTrue(test.getTabRegles()[9]==9);
            assertTrue(test.getTabRegles()[10]==10);
            assertTrue(test.getTabRegles()[11]==11);
            assertTrue(test.getTabRegles()[12]==12);
            assertTrue(test.getTabRegles()[13]==13);
            assertTrue(test.getTabRegles()[14]==14);
            assertTrue(test.getTabRegles()[15]==15);



            assertTrue(test1.getTabRegles()[0]==15);
            assertTrue(test1.getTabRegles()[1]==1);
            assertTrue(test1.getTabRegles()[2]==2);
            assertTrue(test1.getTabRegles()[3]==3);
            assertTrue(test1.getTabRegles()[4]==4);
            assertTrue(test1.getTabRegles()[5]==5);
            assertTrue(test1.getTabRegles()[6]==6);
            assertTrue(test1.getTabRegles()[7]==7);
            assertTrue(test1.getTabRegles()[8]==8);
            assertTrue(test1.getTabRegles()[9]==9);
            assertTrue(test1.getTabRegles()[10]==10);
            assertTrue(test1.getTabRegles()[11]==11);
            assertTrue(test1.getTabRegles()[12]==12);
            assertTrue(test1.getTabRegles()[13]==13);
            assertTrue(test1.getTabRegles()[14]==14);
            assertTrue(test1.getTabRegles()[15]==0);

            assertTrue(test2.getTabRegles()[0]==15);
            assertTrue(test2.getTabRegles()[1]==14);
            assertTrue(test2.getTabRegles()[2]==13);
            assertTrue(test2.getTabRegles()[3]==3);
            assertTrue(test2.getTabRegles()[4]==11);
            assertTrue(test2.getTabRegles()[5]==5);
            assertTrue(test2.getTabRegles()[6]==6);
            assertTrue(test2.getTabRegles()[7]==1);
            assertTrue(test2.getTabRegles()[8]==7);
            assertTrue(test2.getTabRegles()[9]==9);
            assertTrue(test2.getTabRegles()[10]==10);
            assertTrue(test2.getTabRegles()[11]==2);
            assertTrue(test2.getTabRegles()[12]==12);
            assertTrue(test2.getTabRegles()[13]==4);
            assertTrue(test2.getTabRegles()[14]==8);
            assertTrue(test2.getTabRegles()[15]==0);

            assertTrue(test3.getTabRegles()[0]==0);
            assertTrue(test3.getTabRegles()[1]==7);
            assertTrue(test3.getTabRegles()[2]==11);
            assertTrue(test3.getTabRegles()[3]==3);
            assertTrue(test3.getTabRegles()[4]==13);
            assertTrue(test3.getTabRegles()[5]==5);
            assertTrue(test3.getTabRegles()[6]==6);
            assertTrue(test3.getTabRegles()[7]==7);
            assertTrue(test3.getTabRegles()[8]==14);
            assertTrue(test3.getTabRegles()[9]==9);
            assertTrue(test3.getTabRegles()[10]==10);
            assertTrue(test3.getTabRegles()[11]==11);
            assertTrue(test3.getTabRegles()[12]==12);
            assertTrue(test3.getTabRegles()[13]==13);
            assertTrue(test3.getTabRegles()[14]==14);
            assertTrue(test3.getTabRegles()[15]==15);


            assertTrue(test4.getTabRegles()[0]==0);
            assertTrue(test4.getTabRegles()[1]==1);
            assertTrue(test4.getTabRegles()[2]==2);
            assertTrue(test4.getTabRegles()[3]==3);
            assertTrue(test4.getTabRegles()[4]==4);
            assertTrue(test4.getTabRegles()[5]==5);
            assertTrue(test4.getTabRegles()[6]==6);
            assertTrue(test4.getTabRegles()[7]==7);
            assertTrue(test4.getTabRegles()[8]==8);
            assertTrue(test4.getTabRegles()[9]==9);
            assertTrue(test4.getTabRegles()[10]==10);
            assertTrue(test4.getTabRegles()[11]==11);
            assertTrue(test4.getTabRegles()[12]==12);
            assertTrue(test4.getTabRegles()[13]==13);
            assertTrue(test4.getTabRegles()[14]==14);
            assertTrue(test4.getTabRegles()[15]==15);

            assertTrue(test5.getTabRegles()[0]==0);
            assertTrue(test5.getTabRegles()[1]==1);
            assertTrue(test5.getTabRegles()[2]==2);
            assertTrue(test5.getTabRegles()[3]==3);
            assertTrue(test5.getTabRegles()[4]==4);
            assertTrue(test5.getTabRegles()[5]==5);
            assertTrue(test5.getTabRegles()[6]==6);
            assertTrue(test5.getTabRegles()[7]==7);
            assertTrue(test5.getTabRegles()[8]==8);
            assertTrue(test5.getTabRegles()[9]==9);
            assertTrue(test5.getTabRegles()[10]==10);
            assertTrue(test5.getTabRegles()[11]==11);
            assertTrue(test5.getTabRegles()[12]==12);
            assertTrue(test5.getTabRegles()[13]==13);
            assertTrue(test5.getTabRegles()[14]==14);
            assertTrue(test5.getTabRegles()[15]==15);
        }
        catch (AssertionError e){
            System.out.println("Test Table de régles échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Table de régles Réussi");

        return true;
    }

    /**
     * Cette méthode permet de tester la méthode areRulesValid()
     * @return true si la méthode a fonctionné correctement, false sinon
     */
    @Test
    public static boolean TestAreRulesValid(){
        DecodeGrilleMargolus test = new DecodeGrilleMargolus();
        DecodeGrilleMargolus test1 = new DecodeGrilleMargolus("15/1/2/3/4/5/6/7/8/9/10/11/12/13/14/0/");
        DecodeGrilleMargolus test2 = new DecodeGrilleMargolus("15/14/13/3/11/5/6/1/7/9/10/2/12/4/8/0/");
        DecodeGrilleMargolus test3 = new DecodeGrilleMargolus("0/7/11/3/13/5/6/7/14/9/10/11/12/13/14/15/");
        DecodeGrilleMargolus test4 = new DecodeGrilleMargolus("0.565/-0.716/0.565/-0.759/0.627/-0.759/0.565/-0.716/0.565/");
        DecodeGrilleMargolus test5 = new DecodeGrilleMargolus("B3/S23");

        System.out.println("Test Validité des règles Commencé");

        try{
            assertTrue(test.areRulesValid());
            assertTrue(test1.areRulesValid());
            assertTrue(test2.areRulesValid());
            assertTrue(test3.areRulesValid());
            assertFalse(test4.areRulesValid());
            assertFalse(test5.areRulesValid());

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
     * Cette méthode permet de tester la méthode useRules()
     * @return true si la méthode a fonctionné correctement, false sinon
     */
    @Test
    public static boolean TestUseRules(){
        DecodeGrilleMargolus test = new DecodeGrilleMargolus();
        DecodeGrilleMargolus test1 = new DecodeGrilleMargolus("15/1/2/3/4/5/6/7/8/9/10/11/12/13/14/0/");
        DecodeGrilleMargolus test2 = new DecodeGrilleMargolus("15/14/13/3/11/5/6/1/7/9/10/2/12/4/8/0/");
        DecodeGrilleMargolus test3 = new DecodeGrilleMargolus("0/7/11/3/13/5/6/7/14/9/10/11/12/13/14/15/");
        DecodeGrilleMargolus test4 = new DecodeGrilleMargolus("0.565/-0.716/0.565/-0.759/0.627/-0.759/0.565/-0.716/0.565/");
        DecodeGrilleMargolus test5 = new DecodeGrilleMargolus("B3/S23");

        System.out.println("Test Utilisation des règles Commencé");
        try{
            test.useRules();
            test1.useRules();
            test2.useRules();
            test3.useRules();
            test4.useRules();
            test5.useRules();
            
            assertTrue(test.getTabRegles()[0]==0);
            assertTrue(test.getTabRegles()[1]==1);
            assertTrue(test.getTabRegles()[2]==2);
            assertTrue(test.getTabRegles()[3]==3);
            assertTrue(test.getTabRegles()[4]==4);
            assertTrue(test.getTabRegles()[5]==5);
            assertTrue(test.getTabRegles()[6]==6);
            assertTrue(test.getTabRegles()[7]==7);
            assertTrue(test.getTabRegles()[8]==8);
            assertTrue(test.getTabRegles()[9]==9);
            assertTrue(test.getTabRegles()[10]==10);
            assertTrue(test.getTabRegles()[11]==11);
            assertTrue(test.getTabRegles()[12]==12);
            assertTrue(test.getTabRegles()[13]==13);
            assertTrue(test.getTabRegles()[14]==14);
            assertTrue(test.getTabRegles()[15]==15);



            assertTrue(test1.getTabRegles()[0]==15);
            assertTrue(test1.getTabRegles()[1]==1);
            assertTrue(test1.getTabRegles()[2]==2);
            assertTrue(test1.getTabRegles()[3]==3);
            assertTrue(test1.getTabRegles()[4]==4);
            assertTrue(test1.getTabRegles()[5]==5);
            assertTrue(test1.getTabRegles()[6]==6);
            assertTrue(test1.getTabRegles()[7]==7);
            assertTrue(test1.getTabRegles()[8]==8);
            assertTrue(test1.getTabRegles()[9]==9);
            assertTrue(test1.getTabRegles()[10]==10);
            assertTrue(test1.getTabRegles()[11]==11);
            assertTrue(test1.getTabRegles()[12]==12);
            assertTrue(test1.getTabRegles()[13]==13);
            assertTrue(test1.getTabRegles()[14]==14);
            assertTrue(test1.getTabRegles()[15]==0);

            assertTrue(test2.getTabRegles()[0]==15);
            assertTrue(test2.getTabRegles()[1]==14);
            assertTrue(test2.getTabRegles()[2]==13);
            assertTrue(test2.getTabRegles()[3]==3);
            assertTrue(test2.getTabRegles()[4]==11);
            assertTrue(test2.getTabRegles()[5]==5);
            assertTrue(test2.getTabRegles()[6]==6);
            assertTrue(test2.getTabRegles()[7]==1);
            assertTrue(test2.getTabRegles()[8]==7);
            assertTrue(test2.getTabRegles()[9]==9);
            assertTrue(test2.getTabRegles()[10]==10);
            assertTrue(test2.getTabRegles()[11]==2);
            assertTrue(test2.getTabRegles()[12]==12);
            assertTrue(test2.getTabRegles()[13]==4);
            assertTrue(test2.getTabRegles()[14]==8);
            assertTrue(test2.getTabRegles()[15]==0);

            assertTrue(test3.getTabRegles()[0]==0);
            assertTrue(test3.getTabRegles()[1]==7);
            assertTrue(test3.getTabRegles()[2]==11);
            assertTrue(test3.getTabRegles()[3]==3);
            assertTrue(test3.getTabRegles()[4]==13);
            assertTrue(test3.getTabRegles()[5]==5);
            assertTrue(test3.getTabRegles()[6]==6);
            assertTrue(test3.getTabRegles()[7]==7);
            assertTrue(test3.getTabRegles()[8]==14);
            assertTrue(test3.getTabRegles()[9]==9);
            assertTrue(test3.getTabRegles()[10]==10);
            assertTrue(test3.getTabRegles()[11]==11);
            assertTrue(test3.getTabRegles()[12]==12);
            assertTrue(test3.getTabRegles()[13]==13);
            assertTrue(test3.getTabRegles()[14]==14);
            assertTrue(test3.getTabRegles()[15]==15);


            assertTrue(test4.getTabRegles()[0]==0);
            assertTrue(test4.getTabRegles()[1]==1);
            assertTrue(test4.getTabRegles()[2]==2);
            assertTrue(test4.getTabRegles()[3]==3);
            assertTrue(test4.getTabRegles()[4]==4);
            assertTrue(test4.getTabRegles()[5]==5);
            assertTrue(test4.getTabRegles()[6]==6);
            assertTrue(test4.getTabRegles()[7]==7);
            assertTrue(test4.getTabRegles()[8]==8);
            assertTrue(test4.getTabRegles()[9]==9);
            assertTrue(test4.getTabRegles()[10]==10);
            assertTrue(test4.getTabRegles()[11]==11);
            assertTrue(test4.getTabRegles()[12]==12);
            assertTrue(test4.getTabRegles()[13]==13);
            assertTrue(test4.getTabRegles()[14]==14);
            assertTrue(test4.getTabRegles()[15]==15);

            assertTrue(test5.getTabRegles()[0]==0);
            assertTrue(test5.getTabRegles()[1]==1);
            assertTrue(test5.getTabRegles()[2]==2);
            assertTrue(test5.getTabRegles()[3]==3);
            assertTrue(test5.getTabRegles()[4]==4);
            assertTrue(test5.getTabRegles()[5]==5);
            assertTrue(test5.getTabRegles()[6]==6);
            assertTrue(test5.getTabRegles()[7]==7);
            assertTrue(test5.getTabRegles()[8]==8);
            assertTrue(test5.getTabRegles()[9]==9);
            assertTrue(test5.getTabRegles()[10]==10);
            assertTrue(test5.getTabRegles()[11]==11);
            assertTrue(test5.getTabRegles()[12]==12);
            assertTrue(test5.getTabRegles()[13]==13);
            assertTrue(test5.getTabRegles()[14]==14);
            assertTrue(test5.getTabRegles()[15]==15);
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
