package automate.gridlife;


import java.util.Random;



import automate.GOL;

/**
 * Cette classe permet de générer un automate cellulaire sur grille.
 * @author : Brice Andrieux L2 Info TP 3A
 */
public class GOLGrille extends GOL {
    protected Grille grille;
    protected long gen; 
    
    public GOLGrille(int taille){
        this(taille,"1/1/1/1/9/1/1/1/1/",0,new Float[]{0f,0f,0f});
    }

    public GOLGrille(int taille,String rules){
        this(taille,rules,0,new Float[]{0f,0f,0f});
    }
    public GOLGrille(int taille,String rules,int randType){
        this(taille,rules,randType,new Float[]{0f,0f,0f});
    }
    public GOLGrille (int taille,String rules,int randType,Float[] couleurs){
        super(taille,rules);
        this.randType=randType;
        grille = new Grille(taille,couleurs);
        gen = 0;
    }
    /**
     * Cette méthode permet de modifier les règles de l'automate cellulaire.
     * @param newRule nouvelle règle
     */
    @Override
    public void setRule(String newRule){
        rules = newRule;
    }

    /**
     * Cette méthode permet de remplir toute les valeurs de la grille aléatoirement.
     */
    public void randomizeUnivers(){
        if(randType==0)
            randomizeUniversBin();
        else if(randType>0)
            randomizeUniversFloat();
    }

    /**
     * Cette méthode permet de remplacer aléatoirement toutes les valeurs dans la grille par des 1 et des 0.
     */
    public void randomizeUniversBin(){
        Random rand = new Random();
        for(int y = 0; y < taille; y++)
            for(int x = 0; x < taille; x++){
                if(rand.nextInt()%2==1) grille.setValAt(y,x,1f);
                else grille.setValAt(y,x,0f);
            }
    }

    /**
     * Cette méthode permet de remplacer aléatoirement toutes les valeurs dans la grille par des nombres compris entre 0 et 1.
     */
    public void randomizeUniversFloat(){
        for(int y = 0; y < taille; y++)
            for(int x = 0; x < taille; x++){
                grille.setValAt(y,x,(float)Math.pow(Math.random(),1));
            }
    }

    /**
     * Cette méthode permet de remplacer toutes les valeurs dans la grille par 0.
     */
    public void clearUnivers(){
        for(int y = 0; y < taille; y++)
            for(int x = 0; x < taille; x++){
                grille.setValAt(y,x,0f);
            }
    }
    /**
     * Cette méthode permet de renvoyer la grille; elle est redéfinie dans les classes qui hérite celle-ci pour renvoyer la grille de la génération suivante.
     * @return la grille de l'automate cellulaire
     */
    public Grille tick(){
        return grille; //se fait override dans d autres fichiers
    }

   

    /**
     * Cette méthode permet de récupérer le nombre de génération de l'automate cellulaire effectué.
     * @return nombre de génération
     */
    public long getGeneration(){
        return gen;
    }

    /**
     * Cette méthode renvoie la grille de l'automate cellulaire.
     * @return la grille de l'automate cellulaire
     */
    public Grille getUnivers() {
        return grille;
    }

    /**
     * Cette méthode permet de mettre a jour les différents composants de l'automate cellulaire sur une génération.
     */
    public void update(){
        grille.cloneUnivers(tick());
        gen += 1;
    }

}
