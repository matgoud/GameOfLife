package automate.gridlife;
import automate.*;

/**
 * Cette classe permet de décoder des règles pour les voisinages de Margolus.
 * @author : Brice Andrieux L2 Info TP 3A
 */
public class DecodeGrilleMargolus extends Decode{
    protected int[] tabRegles;

    public DecodeGrilleMargolus(){
        this("0/1/2/3/4/5/6/7/8/9/10/11/12/13/14/15/");
    }
    public DecodeGrilleMargolus(String rules){
        super(rules);
        tabRegles=new int[16];
        useRules();
    }

    /**
     * Cette méthode renvoie le tableau des règles pour les voisinages de Margolus.
     * @return le tableau des règles
     */
    public int[] getTabRegles(){
        return tabRegles;
    }

    /**
     * Cette méthode renvoie si les règles sont valides.
     * @return true si les règles sont valides et false sinon
     */
    public boolean areRulesValid(){
        int pos=0;
        int nbslash=0;
        String tmp="";
        while(pos<rules.length()){
            if(rules.charAt(pos)=='/'||rules.charAt(pos)==','){
                if(tmp=="") return false;
                if(Integer.valueOf(tmp)<0||Integer.valueOf(tmp)>15) return false;
                nbslash++;
                tmp="";
            }
            else if(!Character.isDigit(rules.charAt(pos))){
                return false;
            }
            else{
                tmp+=rules.charAt(pos);
            }
            pos++;
        }
        if(nbslash==15||nbslash==16) return true;
        //System.out.println("PAS LE BON NOMBRE DE SLASH :"+nbslash);
        return false;
    }

    /**
     * Cette fonction remplis les tableau permmettant de tester les conditions.
     */
    public void useRules(){
        if(areRulesValid()){
            //System.out.println("règle valide :"+rules);
            int pos=0;
            String str;
            for(int i=0;i<16;i++){
                str="";
                while(pos<rules.length()&&rules.charAt(pos)!='/'){
                    str+=rules.charAt(pos);
                    pos++;
                }
                pos++;
                tabRegles[i]=Integer.valueOf(str);
                //System.out.println(str);
            }
        }
        else{
            //System.out.println("règle INVALIDE :"+rules);
            baseRules();
        }
    }


    /**
     * Cette fonction permet de remplir les tableaux de tests avec les règles de bases.
     */
    public void baseRules(){
        for(int tmp=0;tmp<16;tmp++){
            tabRegles[tmp]=tmp;
        }
    }

}
