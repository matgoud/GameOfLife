package automate.gridlife;



/**
 * Cette classe permet de créer une instance d'un automate cellulaire utilisant les voisinages de Margolus en appliquant ses règles.
 * @author : Brice Andrieux L2 Info TP 3A
 */
public class GOLGrilleMargolus extends GOLGrille{
    protected DecodeGrilleMargolus decodeur;
    private int decalage;
    public GOLGrilleMargolus(int taille){
        this(taille,"0/1/2/3/4/5/6/7/8/9/10/11/12/13/14/15/",new Float[]{0f,0f,0f});
    }
    public GOLGrilleMargolus(int taille,String rules){
        this(taille,rules,new Float[]{0f,0f,0f});
    }

    public GOLGrilleMargolus (int taille,String rules,Float[] couleurs){
        super(taille,rules,0,couleurs);
        decodeur = new DecodeGrilleMargolus(rules);
        decalage=0;
    }

    /**
     * Cette méthode permet de modifier les règles de l'automate cellulaire.
     * @param newRule nouvelle règle
     */
    @Override
    public void setRule(String newRule){
        rules = newRule;
        decodeur = new DecodeGrilleMargolus(rules);
    }

    /**
     * Cette méthode permet de modifier le décodeur de l'automate cellulaire.
     * @param d nouveau décodeur
     */
    public void setDecodeur(DecodeGrilleMargolus d){
        decodeur=d;
    }

    /**
     * Cette renvoie le décodeur de l'automate cellulaire.
     * @return décodeur de l'automate cellulaire
     */
    public DecodeGrilleMargolus getDecodeur(){
        return decodeur;
    }

    /**
     * Cette méthode permet de créer la génération suivante de l'automate cellulaire avec les règles du voisinage de Margolus.
     * @return une instance de Grille qui est la génération suivante de la grille actuelle.
     */
    @Override
    public Grille tick(){
        Grille sortie = new Grille(taille);
        int tmpi;
        int tmpj;
        int mult;
        int res;
        int res2;
        for(int y = decalage; y < taille-1+decalage; y+=2){ // On parcourt toute la grille, en ne prennant que les cases de coordonnées paires, ou impaire, selon la situation
            for(int x = decalage; x < taille-1+decalage; x+=2){
                mult=1;
                res=0;
                for(int i = y; i <= y + 1 ; i++){ // On regarde la valeur de la case selectionnée et des 3 autres qui forment en tout un groupe de 2x2 cases
                    for(int j = x; j <= x + 1 ; j++){
                        if(i>=taille) tmpi=0;
                        else tmpi=i;
                        if(j>=taille) tmpj=0;
                        else tmpj=j;
                        res += grille.getValAt(tmpi, tmpj)*mult;
                        mult*=2;
                    }
                }
                res2=decodeur.getTabRegles()[res];
                for(int i = y+1; i >= y ; i--){
                    for(int j = x+1; j >= x ; j--){
                        if(i>=taille) tmpi=0;
                        else tmpi=i;
                        if(j>=taille) tmpj=0;
                        else tmpj=j;
                        mult/=2;
                        if(res2>=mult){
                            res2-=mult;
                            sortie.setValAt(tmpi, tmpj,1f);
                            //System.out.println("val en"+tmpi+","+tmpj+"vaut 1");
                        }
                        else{
                            sortie.setValAt(tmpi, tmpj,0f);
                        }
                    }
                }

            }
        }
        if (decalage==0) decalage=1;
        else decalage=0;
        return sortie;
    }

    /**
     * Cette méthode permet de mettre à jour la grille actuelle avec celle de la génération suivante.
     */
    @Override
    public void update(){
        grille.cloneUnivers(tick());
        gen += 1;
        //System.out.println("generation :"+gen);
    }

}
