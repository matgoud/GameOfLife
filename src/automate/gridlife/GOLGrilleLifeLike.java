package automate.gridlife;


/**
 * Cette classe permet de créer une instance d'un automate cellulaire neural en appliquant ses règles.
 * @author : Brice Andrieux L2 Info TP 3A
 */
public class GOLGrilleLifeLike extends GOLGrille{

    protected DecodeGrilleLifeLike decodeur;
    protected int randType;

    public GOLGrilleLifeLike(int taille){
        this(taille,"1/1/1/1/9/1/1/1/1/",0,0,new Float[]{0f,0f,0f});
    }

    public GOLGrilleLifeLike(int taille,String rules){
        this(taille,rules,0,0,new Float[]{0f,0f,0f});
    }
    public GOLGrilleLifeLike(int taille,String rules,int mode){
        this(taille,rules,mode,0,new Float[]{0f,0f,0f});
    }
    public GOLGrilleLifeLike(int taille,String rules,int mode,int randType){
        this(taille,rules,mode,randType,new Float[]{0f,0f,0f});
    }
    public GOLGrilleLifeLike (int taille,String rules,int mode,int randType,Float[] couleurs){
        super(taille,rules,randType,couleurs);
        decodeur = new DecodeGrilleLifeLike(rules);
    }

    /**
     * Cette méthode permet de modifier la régle de l'automate cellulaire actuel.
     * @param newRule la nouvelle régle
     */
    @Override
    public void setRule(String newRule){
        rules = newRule;
        decodeur = new DecodeGrilleLifeLike(rules);
    }

    /**
     * Cette méthode permet de modifier le décodeur de l'automate cellulaire actuel.
     * @param d nouveau décodeur
     */
    public void setDecodeur(DecodeGrilleLifeLike d){
        decodeur=d;
    }

    /**
     * Cette méthode renvoie le décodeur de l'automate cellulaire actuel.
     * @return le décodeur de l'automate cellulaire actuelle
     */
    public DecodeGrilleLifeLike getDecodeur(){
        return decodeur;
    }
    /**
     * Cette méthode crée une grille en appliquant les régles de l'automate cellulaire sur celle-ci,
     * basé sur https://neuralpatterns.io .
     * @return une instance de Grille qui est la génération suivante de la grille actuelle
     */
    @Override
    public Grille tick(){
        Grille sortie = new Grille(taille);
        float tot=0;
        int tmpi;
        int tmpj;
        Float res;
        for(int y = 0; y < taille; y++){
            for(int x = 0; x < taille; x++){     //On parcourt toute la grille
                res = 0f;
                for(int i = y - 1; i <= y + 1 ; i++){
                    for(int j = x - 1; j <= x + 1 ; j++){ //On regarde la case et toutes ses voisines
                        if(i<0) tmpi=taille-1;
                        else if(i>=taille) tmpi=0;
                        else tmpi=i;
                        if(j<0) tmpj=taille-1;
                        else if(j>=taille) tmpj=0;
                        else tmpj=j;
                        res += grille.getValAt(tmpi, tmpj)*decodeur.getMultiplicateurs()[3*(i-y+1)+(j-x+1)];
                        //System.out.println((i-y)+","+(j-x)+","+decodeur.getMultiplicateurs()[3*(i-y+1)+(j-x+1)]);
                    }
                }
                // Chaque règle implémenté d'algorithmes cellulaires neuraux est associé à une valeur "mode" ,qui permet de calculer ce que l'on souhaite 
                if(mode==0) sortie.setValAt(y,x,tickGameOfLife(res));
                else if(mode==1){
                    if (grille.getValAt(y, x)==1f||tickWolfmanRule30(res)) sortie.setValAt(y,x,1f);
                }
                else if(mode==2)sortie.setValAt(y,x,tickStar(res));
                else if(mode==3)sortie.setValAt(y,x,tickWorm(res));
                else if(mode==4)sortie.setValAt(y,x,tickSlimeMold(res));
                else if(mode==5)sortie.setValAt(y,x,tickWave(res));
                else if(mode==6)sortie.setValAt(y,x,tickMitosis(res));
                else if(mode==7)sortie.setValAt(y,x,tickFabric(res));
                else if(mode==8)sortie.setValAt(y,x,tickPathways(res));
                else sortie.setValAt(y,x,tickGameOfLife(res));
                tot+=sortie.getValAt(y,x);
            }
        }
        return sortie;
    }

    /**
     * Cette méthode vérifie si un flottant est entre 0 et 1 et le modifie pour que ce soit le cas.
     * @param res flottant à vérifier
     * @return renvoie 1f si le flottant est supérieur à 1, 0f si le flottant est inférieur à 0 et res si le flottant est entre 0 et 1
     */
    public Float between0And1(Float res){
        if(res<0f) return 0f;
        if(res>1f) return 1f;
        return res;
    }

    /**
     * Cette méthode vérifie si un flottant résultant à une matrice de convolution respecte la règle du jeu de la vie.
     * @param res flottant à vérifier
     * @return 1f si le flottant est égale à 3, 11 ou 12, 0f sinon
     */
    public Float tickGameOfLife(Float res){
        if(Float.compare(res,3)==0||Float.compare(res,11)==0||Float.compare(res,12)==0) return 1f;
            return 0f;
    }

    /**
     * Cette méthode vérifie si un flottant résultant à une matrice de convolution respecte la règle de Wolfman's Rule 30.
     * @param res flottant à vérifier
     * @return true si le flottant est égale à 1, 2, 3 ou 4, false sinon
     */
    public Boolean tickWolfmanRule30(Float res){
        if(Float.compare(res,1)==0||Float.compare(res,2)==0||Float.compare(res,3)==0||Float.compare(res,4)==0) return true;
            return false;
    }

    /**
     * Cette méthode renvoie un flottant, selon l'algorithme cellulaire neural nommé Star.
     * @param res flottant à vérifier
     * @return 1f si la valeur absolue du flottant est supérieur à 1 sinon la valeur absolue du flottant
     */
    public Float tickStar(Float res){
        return between0And1((float)Math.abs(res));
    }

    /**
     * Cette méthode renvoie un flottant, selon l'algorithme cellulaire neural nommé Worm.
     * @param res flottant à calculer
     * @return résultat de l'opération : (-1f/(2f^(0.6*(res^2f))))+1f
     */
    public Float tickWorm(Float res){
        //System.out.println(res+","+(float)(-1f/(Math.pow(2f,(0.6*Math.pow(res,2f))))+1f));
        return (float)(-1f/(Math.pow(2f,(0.6*Math.pow(res,2f))))+1f);
    }

    /**
     * Cette méthode renvoie un flottant, selon l'algorithme cellulaire neural nommé Slime Mold.
     * @param res flottant à calculer
     * @return résultat de l'opération : (-1f/(0.89*(res^2f)+1f)+1f
     */
    public Float tickSlimeMold(Float res){
        return (float)(-1f/(0.89*Math.pow(res,2f)+1f)+1f);
    }

    /**
     * Cette méthode renvoie un flottant, selon l'algorithme cellulaire neural nommé Wave.
     * @param res flottant à calculer
     * @return le maximum entre 1f et la valeur absolue de 1.2*res
     */
    public Float tickWave(Float res){
        return between0And1((float)Math.abs(1.2*res));
    }

     /**
     * Cette méthode renvoie un flottant, selon l'algorithme cellulaire neural nommé Mitosis.
     * @param res flottant à calculer
     * @return résultat de l'opération : (-1f/(0.9*(res^2f)+1f)+1f
     */
    public Float tickMitosis(Float res){
        return (float)(-1f/(0.9*Math.pow(res,2f)+1f)+1f);
    }

    /**
     * Cette méthode renvoie un flottant, selon l'algorithme cellulaire neural nommé Fabric.
     * @param res flottant à calculer
     * @return résultat de l'opération, s'il est entre 0 et 1, de : (e^(2f*res)-1)/(e^(2f*res)+1)
     */
    public Float tickFabric(Float res){
        return between0And1((float)((Math.exp(2f*res)-1)/(Math.exp(2f*res)+1)));
    }

    /**
     * Cette méthode renvoie un flottant, selon l'algorithme cellulaire neural nommé Pathways.
     * @param res flottant à calculer
     * @return résultat de l'opération, s'il est entre 0 et 1, de : 1/(2f^((res-3.5)^2f)) 
     */
    public Float tickPathways(Float res){
        return between0And1((float)(1f/Math.pow(2f,(Math.pow(res-3.5,2f)))));
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
