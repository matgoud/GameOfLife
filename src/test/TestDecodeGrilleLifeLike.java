package test;
import automate.*;
import automate.gridlife.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Cette Classe permet de faire des tests sur les différentes méthodes de la classe DecodeGrilleLifeLike.
 * @author : Loic Laloux L2 Informatique TP 3A
 */

public class TestDecodeGrilleLifeLike {
    
    /**
     * Cette méthode permet de tester la méthode getMultiplicateurs() de la classe DecodeGrilleLifeLike
     * @return true si la méthode a correctement fonctionné correctement, false sinon.
     */
    @Test
    public static boolean TestGetMultiplicateurs(){
        DecodeGrilleLifeLike test = new DecodeGrilleLifeLike();
        DecodeGrilleLifeLike test1 = new DecodeGrilleLifeLike("0/0/0/0/0/0/1/2/4/");
        DecodeGrilleLifeLike test2 = new DecodeGrilleLifeLike("0.565/-0.716/0.565/-0.759/0.627/-0.759/0.565/-0.716/0.565/");
        DecodeGrilleLifeLike test3 = new DecodeGrilleLifeLike("0.68/-0.9/0.68/-0.9/-0.66/-0.9/0.68/-0.9/0.68/");
        DecodeGrilleLifeLike test4 = new DecodeGrilleLifeLike("0.8/-0.85/0.8/-0.85/-0.2/-0.85/0.8/-0.85/0.8/");
        DecodeGrilleLifeLike test5 = new DecodeGrilleLifeLike("0.565/-0.716/0.565/-0.716/0.627/-0.716/0.565/-0.716/0.565/");
        DecodeGrilleLifeLike test6 = new DecodeGrilleLifeLike("-0.939/0.88/-0.939/0.88/0.4/0.88/-0.939/0.88/-0.939/");
        DecodeGrilleLifeLike test7 = new DecodeGrilleLifeLike("0.037/0.43/-0.737/0.406/-0.321/-0.319/-0.458/0.416/0.478/");
        DecodeGrilleLifeLike test8 = new DecodeGrilleLifeLike("0/1/0/1/1/1/0/1/0/");
        DecodeGrilleLifeLike test9 = new DecodeGrilleLifeLike("1/2/3/4/5/6/7/8/F/10/11/12/13/14/15");
        DecodeGrilleLifeLike test10 = new DecodeGrilleLifeLike("B3/S23");
        System.out.println("Test Multiplicateurs Commencé");
        try{
            assertTrue(test.getMultiplicateurs()[4]==9f);

            assertTrue(test1.getMultiplicateurs()[0]==0);
            assertTrue(test1.getMultiplicateurs()[1]==0);
            assertTrue(test1.getMultiplicateurs()[2]==0);
            assertTrue(test1.getMultiplicateurs()[3]==0);
            assertTrue(test1.getMultiplicateurs()[4]==0);
            assertTrue(test1.getMultiplicateurs()[5]==0);
            assertTrue(test1.getMultiplicateurs()[6]==1f);
            assertTrue(test1.getMultiplicateurs()[7]==2f);
            assertTrue(test1.getMultiplicateurs()[8]==4f);

            assertTrue(test2.getMultiplicateurs()[0]==0.565f);
            assertTrue(test2.getMultiplicateurs()[1]==-0.716f);
            assertTrue(test2.getMultiplicateurs()[2]==0.565f);
            assertTrue(test2.getMultiplicateurs()[3]==-0.759f);
            assertTrue(test2.getMultiplicateurs()[4]==0.627f);
            assertTrue(test2.getMultiplicateurs()[5]==-0.759f);
            assertTrue(test2.getMultiplicateurs()[6]==0.565f);
            assertTrue(test2.getMultiplicateurs()[7]==-0.716f);
            assertTrue(test2.getMultiplicateurs()[8]==0.565f);

            assertTrue(test3.getMultiplicateurs()[0]==0.68f);
            assertTrue(test3.getMultiplicateurs()[1]==-0.9f);
            assertTrue(test3.getMultiplicateurs()[2]==0.68f);
            assertTrue(test3.getMultiplicateurs()[3]==-0.9f);
            assertTrue(test3.getMultiplicateurs()[4]==-0.66f);
            assertTrue(test3.getMultiplicateurs()[5]==-0.9f);
            assertTrue(test3.getMultiplicateurs()[6]==0.68f);
            assertTrue(test3.getMultiplicateurs()[7]==-0.9f);
            assertTrue(test3.getMultiplicateurs()[8]==0.68f);

            assertTrue(test4.getMultiplicateurs()[0]==0.8f);
            assertTrue(test4.getMultiplicateurs()[1]==-0.85f);
            assertTrue(test4.getMultiplicateurs()[2]==0.8f);
            assertTrue(test4.getMultiplicateurs()[3]==-0.85f);
            assertTrue(test4.getMultiplicateurs()[4]==-0.2f);
            assertTrue(test4.getMultiplicateurs()[5]==-0.85f);
            assertTrue(test4.getMultiplicateurs()[6]==0.8f);
            assertTrue(test4.getMultiplicateurs()[7]==-0.85f);
            assertTrue(test4.getMultiplicateurs()[8]==0.8f);

            assertTrue(test5.getMultiplicateurs()[0]==0.565f);
            assertTrue(test5.getMultiplicateurs()[1]==-0.716f);
            assertTrue(test5.getMultiplicateurs()[2]==0.565f);
            assertTrue(test5.getMultiplicateurs()[3]==-0.716f);
            assertTrue(test5.getMultiplicateurs()[4]==0.627f);
            assertTrue(test5.getMultiplicateurs()[5]==-0.716f);
            assertTrue(test5.getMultiplicateurs()[6]==0.565f);
            assertTrue(test5.getMultiplicateurs()[7]==-0.716f);
            assertTrue(test5.getMultiplicateurs()[8]==0.565f);

            assertTrue(test6.getMultiplicateurs()[0]==-0.939f);
            assertTrue(test6.getMultiplicateurs()[1]==0.88f);
            assertTrue(test6.getMultiplicateurs()[2]==-0.939f);
            assertTrue(test6.getMultiplicateurs()[3]==0.88f);
            assertTrue(test6.getMultiplicateurs()[4]==0.4f);
            assertTrue(test6.getMultiplicateurs()[5]==0.88f);
            assertTrue(test6.getMultiplicateurs()[6]==-0.939f);
            assertTrue(test6.getMultiplicateurs()[7]==0.88f);
            assertTrue(test6.getMultiplicateurs()[8]==-0.939f);

            assertTrue(test7.getMultiplicateurs()[0]==0.037f);
            assertTrue(test7.getMultiplicateurs()[1]==0.43f);
            assertTrue(test7.getMultiplicateurs()[2]==-0.737f);
            assertTrue(test7.getMultiplicateurs()[3]==0.406f);
            assertTrue(test7.getMultiplicateurs()[4]==-0.321f);
            assertTrue(test7.getMultiplicateurs()[5]==-0.319f);
            assertTrue(test7.getMultiplicateurs()[6]==-0.458f);
            assertTrue(test7.getMultiplicateurs()[7]==0.416f);
            assertTrue(test7.getMultiplicateurs()[8]==0.478f);

            assertTrue(test8.getMultiplicateurs()[0]==0);
            assertTrue(test8.getMultiplicateurs()[1]==1f);
            assertTrue(test8.getMultiplicateurs()[2]==0);
            assertTrue(test8.getMultiplicateurs()[3]==1f);
            assertTrue(test8.getMultiplicateurs()[4]==1f);
            assertTrue(test8.getMultiplicateurs()[5]==1f);
            assertTrue(test8.getMultiplicateurs()[6]==0);
            assertTrue(test8.getMultiplicateurs()[7]==1f);
            assertTrue(test8.getMultiplicateurs()[8]==0);

            assertTrue(test9.getMultiplicateurs()[4]==9f);

            assertTrue(test10.getMultiplicateurs()[4]==9f);

        }
        catch (AssertionError e){
            System.out.println("Test Multiplicateurs échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Multiplicateurs Réussi");

        return true;
    }

    /**
     * Cette méthode permet de tester la méthode areRulesValid()
     * @return true si la méthode a fonctionné correctement, false sinon
     */
    @Test
    public static boolean TestAreRulesValid() {
        DecodeGrilleLifeLike test = new DecodeGrilleLifeLike();
        DecodeGrilleLifeLike test1 = new DecodeGrilleLifeLike("0/0/0/0/0/0/1/2/4/");
        DecodeGrilleLifeLike test2 = new DecodeGrilleLifeLike("0.565/-0.716/0.565/-0.759/0.627/-0.759/0.565/-0.716/0.565/");
        DecodeGrilleLifeLike test3 = new DecodeGrilleLifeLike("0.68/-0.9/0.68/-0.9/-0.66/-0.9/0.68/-0.9/0.68/");
        DecodeGrilleLifeLike test4 = new DecodeGrilleLifeLike("0.8/-0.85/0.8/-0.85/-0.2/-0.85/0.8/-0.85/0.8/");
        DecodeGrilleLifeLike test5 = new DecodeGrilleLifeLike("0.565/-0.716/0.565/-0.716/0.627/-0.716/0.565/-0.716/0.565/");
        DecodeGrilleLifeLike test6 = new DecodeGrilleLifeLike("-0.939/0.88/-0.939/0.88/0.4/0.88/-0.939/0.88/-0.939/");
        DecodeGrilleLifeLike test7 = new DecodeGrilleLifeLike("0.037/0.43/-0.737/0.406/-0.321/-0.319/-0.458/0.416/0.478/");
        DecodeGrilleLifeLike test8 = new DecodeGrilleLifeLike("0/1/0/1/1/1/0/1/0/");
        DecodeGrilleLifeLike test9 = new DecodeGrilleLifeLike("1/2/3/4/5/6/7/8/F/10/11/12/13/14/15");
        DecodeGrilleLifeLike test10 = new DecodeGrilleLifeLike("B3/S23");
        System.out.println("Test Validité des règles Commencé");

        try{
            assertTrue(test.areRulesValid());
            assertTrue(test1.areRulesValid());
            assertTrue(test2.areRulesValid());
            assertTrue(test3.areRulesValid());
            assertTrue(test4.areRulesValid());
            assertTrue(test5.areRulesValid());
            assertTrue(test6.areRulesValid());
            assertTrue(test7.areRulesValid());
            assertTrue(test8.areRulesValid());
            assertFalse(test9.areRulesValid());
            assertFalse(test10.areRulesValid());

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
        DecodeGrilleLifeLike test = new DecodeGrilleLifeLike();
        DecodeGrilleLifeLike test1 = new DecodeGrilleLifeLike("0/0/0/0/0/0/1/2/4/");
        DecodeGrilleLifeLike test2 = new DecodeGrilleLifeLike("0.565/-0.716/0.565/-0.759/0.627/-0.759/0.565/-0.716/0.565/");
        DecodeGrilleLifeLike test3 = new DecodeGrilleLifeLike("0.68/-0.9/0.68/-0.9/-0.66/-0.9/0.68/-0.9/0.68/");
        DecodeGrilleLifeLike test4 = new DecodeGrilleLifeLike("0.8/-0.85/0.8/-0.85/-0.2/-0.85/0.8/-0.85/0.8/");
        DecodeGrilleLifeLike test5 = new DecodeGrilleLifeLike("0.565/-0.716/0.565/-0.716/0.627/-0.716/0.565/-0.716/0.565/");
        DecodeGrilleLifeLike test6 = new DecodeGrilleLifeLike("-0.939/0.88/-0.939/0.88/0.4/0.88/-0.939/0.88/-0.939/");
        DecodeGrilleLifeLike test7 = new DecodeGrilleLifeLike("0.037/0.43/-0.737/0.406/-0.321/-0.319/-0.458/0.416/0.478/");
        DecodeGrilleLifeLike test8 = new DecodeGrilleLifeLike("0/1/0/1/1/1/0/1/0/");
        DecodeGrilleLifeLike test9 = new DecodeGrilleLifeLike("1/2/3/4/5/6/7/8/F/10/11/12/13/14/15");
        DecodeGrilleLifeLike test10 = new DecodeGrilleLifeLike("B3/S23");
        System.out.println("Test Utilisaion des règles Commencé");
        try{
            test.useRules();
            test1.useRules();
            test2.useRules();
            test3.useRules();
            test4.useRules();
            test5.useRules();
            test6.useRules();
            test7.useRules();
            test8.useRules();
            test9.useRules();
            test10.useRules();


            assertTrue(test.getMultiplicateurs()[4]==9f);

            assertTrue(test1.getMultiplicateurs()[0]==0);
            assertTrue(test1.getMultiplicateurs()[1]==0);
            assertTrue(test1.getMultiplicateurs()[2]==0);
            assertTrue(test1.getMultiplicateurs()[3]==0);
            assertTrue(test1.getMultiplicateurs()[4]==0);
            assertTrue(test1.getMultiplicateurs()[5]==0);
            assertTrue(test1.getMultiplicateurs()[6]==1f);
            assertTrue(test1.getMultiplicateurs()[7]==2f);
            assertTrue(test1.getMultiplicateurs()[8]==4f);

            assertTrue(test2.getMultiplicateurs()[0]==0.565f);
            assertTrue(test2.getMultiplicateurs()[1]==-0.716f);
            assertTrue(test2.getMultiplicateurs()[2]==0.565f);
            assertTrue(test2.getMultiplicateurs()[3]==-0.759f);
            assertTrue(test2.getMultiplicateurs()[4]==0.627f);
            assertTrue(test2.getMultiplicateurs()[5]==-0.759f);
            assertTrue(test2.getMultiplicateurs()[6]==0.565f);
            assertTrue(test2.getMultiplicateurs()[7]==-0.716f);
            assertTrue(test2.getMultiplicateurs()[8]==0.565f);

            assertTrue(test3.getMultiplicateurs()[0]==0.68f);
            assertTrue(test3.getMultiplicateurs()[1]==-0.9f);
            assertTrue(test3.getMultiplicateurs()[2]==0.68f);
            assertTrue(test3.getMultiplicateurs()[3]==-0.9f);
            assertTrue(test3.getMultiplicateurs()[4]==-0.66f);
            assertTrue(test3.getMultiplicateurs()[5]==-0.9f);
            assertTrue(test3.getMultiplicateurs()[6]==0.68f);
            assertTrue(test3.getMultiplicateurs()[7]==-0.9f);
            assertTrue(test3.getMultiplicateurs()[8]==0.68f);

            assertTrue(test4.getMultiplicateurs()[0]==0.8f);
            assertTrue(test4.getMultiplicateurs()[1]==-0.85f);
            assertTrue(test4.getMultiplicateurs()[2]==0.8f);
            assertTrue(test4.getMultiplicateurs()[3]==-0.85f);
            assertTrue(test4.getMultiplicateurs()[4]==-0.2f);
            assertTrue(test4.getMultiplicateurs()[5]==-0.85f);
            assertTrue(test4.getMultiplicateurs()[6]==0.8f);
            assertTrue(test4.getMultiplicateurs()[7]==-0.85f);
            assertTrue(test4.getMultiplicateurs()[8]==0.8f);

            assertTrue(test5.getMultiplicateurs()[0]==0.565f);
            assertTrue(test5.getMultiplicateurs()[1]==-0.716f);
            assertTrue(test5.getMultiplicateurs()[2]==0.565f);
            assertTrue(test5.getMultiplicateurs()[3]==-0.716f);
            assertTrue(test5.getMultiplicateurs()[4]==0.627f);
            assertTrue(test5.getMultiplicateurs()[5]==-0.716f);
            assertTrue(test5.getMultiplicateurs()[6]==0.565f);
            assertTrue(test5.getMultiplicateurs()[7]==-0.716f);
            assertTrue(test5.getMultiplicateurs()[8]==0.565f);

            assertTrue(test6.getMultiplicateurs()[0]==-0.939f);
            assertTrue(test6.getMultiplicateurs()[1]==0.88f);
            assertTrue(test6.getMultiplicateurs()[2]==-0.939f);
            assertTrue(test6.getMultiplicateurs()[3]==0.88f);
            assertTrue(test6.getMultiplicateurs()[4]==0.4f);
            assertTrue(test6.getMultiplicateurs()[5]==0.88f);
            assertTrue(test6.getMultiplicateurs()[6]==-0.939f);
            assertTrue(test6.getMultiplicateurs()[7]==0.88f);
            assertTrue(test6.getMultiplicateurs()[8]==-0.939f);

            assertTrue(test7.getMultiplicateurs()[0]==0.037f);
            assertTrue(test7.getMultiplicateurs()[1]==0.43f);
            assertTrue(test7.getMultiplicateurs()[2]==-0.737f);
            assertTrue(test7.getMultiplicateurs()[3]==0.406f);
            assertTrue(test7.getMultiplicateurs()[4]==-0.321f);
            assertTrue(test7.getMultiplicateurs()[5]==-0.319f);
            assertTrue(test7.getMultiplicateurs()[6]==-0.458f);
            assertTrue(test7.getMultiplicateurs()[7]==0.416f);
            assertTrue(test7.getMultiplicateurs()[8]==0.478f);

            assertTrue(test8.getMultiplicateurs()[0]==0);
            assertTrue(test8.getMultiplicateurs()[1]==1f);
            assertTrue(test8.getMultiplicateurs()[2]==0);
            assertTrue(test8.getMultiplicateurs()[3]==1f);
            assertTrue(test8.getMultiplicateurs()[4]==1f);
            assertTrue(test8.getMultiplicateurs()[5]==1f);
            assertTrue(test8.getMultiplicateurs()[6]==0);
            assertTrue(test8.getMultiplicateurs()[7]==1f);
            assertTrue(test8.getMultiplicateurs()[8]==0);

            assertTrue(test9.getMultiplicateurs()[4]==9f);

            assertTrue(test10.getMultiplicateurs()[4]==9f);

        }
        catch (AssertionError e){
            System.out.println("Test Utilisaion des règles échoué");
            e.printStackTrace();
            return false;
        }
        System.out.println("Test Utilisaion des règles Réussi");

        return true;
    }
}
