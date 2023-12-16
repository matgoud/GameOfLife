package automate;


/**
 * Cette classe abstraite initialise un automate cellulaire et ses différentes méthodes.
 */
public abstract class GOL {
    protected String rules;
    protected int taille;
    protected int randType;
    protected int mode;
    private int posY,posX;
    private float zoom;
    protected boolean changespeed = false; 
    protected int requestspeed = 0; 


    public GOL (int taille,String rules){
        this.taille = taille;
        this.rules = rules;
        posX = 0;
        posY = 0;
        zoom = 1.0f;

    }

    /**
     * Cette méthode renvoie la règle de l'automate cellulaire actuel.
     * @return règle de l'automate cellulaire actuel
     */
    public String getRules() {
        return rules;
    }

    /**
     * Cette méthode permet de changer la règle de l'automate cellulaire.
     * @param newRule nouvelle règle de l'automate cellulaire
     */
    public void setRule(String newRule){
        rules = newRule;
    }

    /**
     * Cette méthode renvoie le format de l'automate cellulaire.
     * @return format de l'automate cellulaire
     */
    public abstract Univers getUnivers();

    /**
     * Cette méthode renvoie la taille de l'automate cellulaire.
     * @return la taille de l'automate cellulaire
     */
    public int getTaille() {
        return taille;
    }

    /**
     * Cette méthode renvoie le nombre de génération effectués.
     * @return nombre de génération effectués
     */
    public abstract long getGeneration();

    /**
     * Cette méthode permet de mettre à jour l'automate cellulaire.
     */
    public abstract void update();

    /**
     * Cette méthode permet de remplir le format de l'automate cellulaire de façon aléatoire.
     */
    public abstract void randomizeUnivers();

    /**
     * Cette méthode permet de remplir le Format de l'automate cellulaire de 0.
     */
    public abstract void clearUnivers();


    /**
     * Cette méthode renvoie la position Y de l'automate cellulaire.
     * @return position Y de l'automate cellulaire
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Cette méthode permet de modifier la position Y de l'automate cellulaire.
     * @param posY nouvelle position Y de l'automate cellulaire
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Cette méthode renvoie la position X de l'automate cellulaire.
     * @return position X de l'automate cellulaire
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Cette méthode permet de modifier la position X de l'automate cellulaire.
     * @param posX nouvelle position X de l'automate cellulaire
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }
    
    /**
     * Cette méthode renvoie le zoom de l'automate cellulaire.
     * @return le zoom de l'automate cellulaire
     */
    public float getZoom() {
        return zoom;
    }

    /**
     * Cette méthode permet de modifier le zoom de l'automate cellulaire.
     * @param zoom nouvelle zoom de l'automate cellulaire
     */
    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    /**
     * Cette méthode est une manière indirecte de changer la vitesse, permet de changer entierrement d'automate si correctement utilisé ; 
     * on laisse les classes enfants gérer la requête étant donné qu'elle peut être interprété différement.
     * @param speed vitesse demandée
     */
    public void changeSpeedRequest(int speed){
        changespeed = true;
        requestspeed = speed;
    }

    /**
     * Cette méthode renvoie la vitesse actuel de l'automate cellulaire.
     * @return vitesse actuelle de l'automate cellulaire
     */
    public int getSpeed(){
        return 0;
    }

    /**
     * Cette méthode permet de modifier le randomUnivers utilisé dans les classes GOLGrille de l'automate cellulaire.
     * @param type nouveau randomunivers à utilisé
     */
    public void setRandType(int type){
        randType = type;
    }

    /**
     * Cette méthode renvoie le random univers utilisé dans les classes GOLGrille.
     * @return le random univers utilisé dans les classes GOLGrille
     */
    public int getRandType(){
        return randType;
    }

    /**
     * Cette méthode renvoie le mode utilisé dans les classes GOLGrille de l'automate cellulaire.
     * @return le mode utilisé dans les classes GOLGrille de l'automate cellulaire
     */
    public int getMode() {
        return mode;
    }

    /**
     * Cette méthode permet de modifier le mode utilisé dans les classes GOLGrille de l'automate cellulaire.
     * @param mode nouveau mode à utiliser
     */
    public void setMode(int mode) {
        this.mode = mode;
    }
}
