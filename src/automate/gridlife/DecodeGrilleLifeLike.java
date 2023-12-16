package automate.gridlife;
import automate.*;


/**
 * Cette classe permet de décoder des règles pour les algorithmes cellulaires neuraux.
 * @author : Brice Andrieux L2 Info TP 3A
 */
public class DecodeGrilleLifeLike extends Decode{
    protected Float[] multiplicateurs;

    public DecodeGrilleLifeLike(){
        this("1/1/1/1/9/1/1/1/1/");
    }

    public DecodeGrilleLifeLike(String rules){
        super(rules);
        multiplicateurs=new Float[9];
        useRules();
    }


    /**
     * Cette méthode renvoie un tableau de flottants contenant les multiplicateurs.
     * @return le tableau de flottants contenant les multiplicateurs
     */
    public Float[] getMultiplicateurs(){
        return multiplicateurs;
    }

    /**
     * Cette méthode vérifie si les règles sont valides
     * @return true si les règles sont valides, false sinon
     */
    public boolean areRulesValid(){
        int pos=0;
        int nbslash=0;
        boolean havePoint=false;
        while(pos<rules.length()){
            if(rules.charAt(pos)=='/'||rules.charAt(pos)==','){
                nbslash++;
                havePoint=false;
            }
            else if(!Character.isDigit(rules.charAt(pos))){
                //System.out.println(rules.charAt(pos));
                if(rules.charAt(pos)!='-'){
                    if(havePoint||(rules.charAt(pos)!='.')){
                        //System.out.println("TROP DE POINTS ou SYMBOLE INCORRECT");
                        return false;
                    }
                    havePoint=true;
                }
            }
            pos++;
        }
        if(nbslash==8||nbslash==9) return true;
        //System.out.println("PAS LE BON NOMBRE DE SLASH :"+nbslash);
        return false;
    }

    /**
     * Cette fonction remplit les tableau permettant de tester les conditions.
     */
    public void useRules(){
        if(areRulesValid()){
            //System.out.println("règle valide :"+rules);
            int pos=0;
            String str;
            for(int i=0;i<9;i++){
                str="";
                while(pos<rules.length()&&rules.charAt(pos)!='/'){
                    str+=rules.charAt(pos);
                    pos++;
                }
                pos++;
                if(str=="") {
                    multiplicateurs[i]=1f;
                    //System.out.println("default");
                }
                else {
                    multiplicateurs[i]=Float.valueOf(str);
                    //System.out.println(str);
                }
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
        for(int tmp=0;tmp<9;tmp++){
            multiplicateurs[tmp]=1f;
        }
        multiplicateurs[4]=9f;
    }
}
