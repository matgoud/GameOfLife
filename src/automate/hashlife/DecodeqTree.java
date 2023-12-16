package automate.hashlife;

import automate.Decode;

/**
 * Cette classe permet de décoder les règles du jeu de la vie avec les règles Basique ( B.../S... ).
 * @author : Brice Andrieux L2 Info TP 3A
 */
public class DecodeqTree extends Decode{
    private boolean[] birth;
    private boolean[] survive;
    
    public DecodeqTree(){
        this("B3/S23");
    }

    public DecodeqTree(String rules){
        super(rules);
        birth=new boolean[9];
        survive=new boolean[9];
        useRules();
    }

    /**
     * Cette fonction vérifie selon le nombre de voisins si la case peut naitre ou non.
     * @param IDX nombre de voisins d'une case
     * @return une valeur booléenne pour savoir si la valeur correspond à une naissance d'une case
     */
    public boolean getBirth(int IDX){
        if(IDX < 9 && IDX >= 0){
            return birth[IDX];
        }
        return false;
    }

    /**
     * Cette fonction vérifie selon le nombre de voisins si la case peut survivre ou non.
     * @param IDX nombre de voisins d'une case
     * @return une valeur booléenne pour savoir si la valeur correspond à la survie d'une case
     */
    public boolean getSurvive(int IDX){
        if(IDX < 9 && IDX >= 0){
            return survive[IDX];
        }
        return false;
    }

    /**
     * Cette fonction vérifie si les règles envoyées via le constructeur sont valide ou non.
     * requires résultat = True si les règles sont de format S.../B...
     * requires résultat = False sinon 
     * @return la vérification
     */
    public boolean areRulesValid(){
        if(rules.charAt(0)!='B') return false;
        int i=1;
        while(i<rules.length()-1 && Character.isDigit(rules.charAt(i)) && Character.getNumericValue(rules.charAt(i))!=9) i++;
        if(i>rules.length()-2) return false;
        if(rules.charAt(i)!='/' || rules.charAt(i+1)!='S') return false;
        i+=2;
        while(i<rules.length() && Character.isDigit(rules.charAt(i)) && Character.getNumericValue(rules.charAt(i))!=9) i++;
        if(i==rules.length()) return true;
        return false;
    }

    /**
     * Cette fonction remplit les tableau permettant de tester les conditions.
     */
    public void useRules(){
        for(int tmp=0;tmp<9;tmp++){
            birth[tmp]=false;
            survive[tmp]=false;
        }
        if(areRulesValid()){
            int i=1;
            while(Character.isDigit(rules.charAt(i))){
                birth[Character.getNumericValue(rules.charAt(i))]=true;
                i++;
            }
            i+=2;
            while(i<rules.length()){
                survive[Character.getNumericValue(rules.charAt(i))]=true;
                i++;
            }
        }
        else baseRules();
    }


    /**
     * Cette fonction permet de remplir les tableaux de tests avec les règles de bases.
     */
    public void baseRules(){
        for(int tmp=0;tmp<9;tmp++){
            birth[tmp]=false;
            survive[tmp]=false;
        }
        birth[3]=true;
        survive[2]=true;
        survive[3]=true;
    }

    
}
