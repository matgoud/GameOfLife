package GameOfLife;
import test.*;
import java.util.ArrayList;


/**
 * Cette classe exécute tous les test des fonctionnalités du package GameOfLife.
 * @author : Loic Laloux L2 Informatique TP 3A
 */
public class maintest {
        
    public static void main(String[] args){
        int bon = 0;
        int nb = 0;
        ArrayList<String> erreur = new ArrayList<String>(); 
        boolean ok = true;
        System.out.println("Test Lecture_CSV");
        ok = true;
        nb += 1;
        TestLecteur_CSV test_lecture = new TestLecteur_CSV();
        ok = ok && test_lecture.TestFile();
        ok = ok && test_lecture.TestReadFile();
        ok = ok && test_lecture.TestSplitFile();
        ok = ok && test_lecture.TestSearchRules();
        ok = ok && test_lecture.TestSearchMode();
        ok = ok && test_lecture.TestSearchRandType();
        if(ok)bon+= 1;
        else erreur.add("Lecture_CSV");
        System.out.println(ok ? "All_test_OK" : "At_least_one_test_KO");
        System.out.println("Test DecodeGrilleMargolus");
        ok = true;
        nb += 1;
        TestDecodeGrilleMargolus test_decodeGrilleMargolus = new TestDecodeGrilleMargolus();
        ok = ok && test_decodeGrilleMargolus.TestGetTabRegles();
        ok = ok && test_decodeGrilleMargolus.TestAreRulesValid();
        ok = ok && test_decodeGrilleMargolus.TestUseRules();
        if(ok)bon+= 1;
        else erreur.add("DecodeGrilleMargolus");
        System.out.println(ok? "All_test_OK" : "At_least_one_test_KO");
        System.out.println("Test DecodeGrilleLifeLike");
        ok = true;
        nb += 1;
        TestDecodeGrilleLifeLike test_decodeGrilleLifeLike = new TestDecodeGrilleLifeLike();
        ok = ok && test_decodeGrilleLifeLike.TestGetMultiplicateurs();
        ok = ok && test_decodeGrilleLifeLike.TestAreRulesValid();
        ok = ok && test_decodeGrilleLifeLike.TestUseRules();
        if(ok)bon+= 1;
        else erreur.add("DecodeGrilleLifeLike");
        System.out.println(ok? "All_test_OK" : "At_least_one_test_KO");
        System.out.println("Test GOLGrilleLifeLike");
        ok = true;
        nb += 1;
        TestGOLGrilleLifeLike test_GOLGrilleLifeLike = new TestGOLGrilleLifeLike();
        ok = ok && test_GOLGrilleLifeLike.TestBetween0And1();
        ok = ok && test_GOLGrilleLifeLike.TesttickGameOfLife();
        ok = ok && test_GOLGrilleLifeLike.TesttickWolfmanRule30();  
        ok = ok && test_GOLGrilleLifeLike.TesttickStar();
        ok = ok && test_GOLGrilleLifeLike.TesttickWorm();
        ok = ok && test_GOLGrilleLifeLike.TesttickSlimeMold();
        ok = ok && test_GOLGrilleLifeLike.TesttickWave();
        ok = ok && test_GOLGrilleLifeLike.TesttickMitosis();
        ok = ok && test_GOLGrilleLifeLike.TesttickFabric();
        ok = ok && test_GOLGrilleLifeLike.TesttickPathways();

        if(ok)bon+= 1;
        else erreur.add("GOLGrilleLifeLike");
        System.out.println(ok? "All_test_OK" : "At_least_one_test_KO");
        System.out.println("Test DecodeqTree");
        ok = true;
        nb += 1;
        TestDecodeqTree test_decodeqTree = new TestDecodeqTree();
        ok = ok && test_decodeqTree.TestGetBirth();
        ok = ok && test_decodeqTree.TestGetSurvive();
        ok = ok && test_decodeqTree.TestAreRulesValid();
        ok = ok && test_decodeqTree.TestUseRules();
        if(ok)bon+= 1;
        else erreur.add("DecodeqTree");
        System.out.println(ok? "All_test_OK" : "At_least_one_test_KO");
        System.out.println("Test GOLqTreeNode");
        ok = true;
        nb += 1;
        TestGOLqTreeNode test_GolqTreeNode = new TestGOLqTreeNode();
        ok = ok && test_GolqTreeNode.TestCreate();
        ok = ok && test_GolqTreeNode.TestGenerateCenterNode();
        if(ok)bon+= 1;
        else erreur.add("GOLqTreeNode");
        System.out.println(ok ? "All_test_OK" : "At_least_one_test_KO");
        System.out.println("Test CanonicalizedQuadTree");
        ok = true;
        nb += 1;
        TestCanonicalizedqTree test_CanonicalizedqTree = new TestCanonicalizedqTree();
        ok = ok && test_CanonicalizedqTree.TestCreate();
        ok = ok && test_CanonicalizedqTree.TestEquals();
        if(ok)bon+= 1;
        else erreur.add("CanonicalizedQuadTree");
        System.out.println(ok? "All_test_OK" : "At_least_one_test_KO");
        System.out.println("Test MemoizedQTree");
        ok = true;
        nb += 1;
        TestMemoizedqTree test_MemoizedqTree = new TestMemoizedqTree();
        ok = ok && test_MemoizedqTree.TestCreate();
        if(ok)bon+= 1;
        else erreur.add("MemoizedQTree");
        System.out.println(ok? "All_test_OK" : "At_least_one_test_KO");
        System.out.println("Test HashlifeqTree");
        ok = true;
        nb += 1;
        TestHashLifeqTree test_HashlifeqTree = new TestHashLifeqTree();
        ok = ok && test_HashlifeqTree.TestCreate();
        if(ok)bon+= 1;
        else erreur.add("HashlifeqTree");
        System.out.println(ok? "All_test_OK" : "At_least_one_test_KO");
        if(bon==nb)System.out.println("TOUT LES TESTS OK");
        else {
            System.out.print("Les Tests ");
            int i=0;
            for(i=0;i<erreur.size()-1;i++){
                System.out.print(erreur.get(i)+", ");
            }
            System.out.println(erreur.get(i)+" ont échoués");
        }
        /* HashlifeqTree
         */
    }
}
