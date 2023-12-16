package automate.hashlife;

import javax.swing.*;

import automate.Univers;

import java.awt.*;

/**
 *	Cette classe permet de créer des quadtrees ainsi que les méthodes pour les manipuler.
 *	@author : Clément Bartolone L2 Info TP 3A
 * 	merci a cet article : https://www.dev-mind.blog/hashlife/
 * 	qui ma permi de quasi-tout comprendre sur hashlife
 */
public class GOLqTreeNode implements Univers{

	//l'arbre est immutable donc on met les enfants et la valeur en final pour éviter d'y toucher
    public final GOLqTreeNode[] enfants;
	private final Boolean valeur;
	private int taille;
	private final int population;
    private static DecodeqTree decodeur;


    //*******************************************CONSTRUCTEURS*************************************************//

    /**
	 * Ce Constructeur permet de créer une nouvelle instance de GOLqTreeNode en prenant en paramètre la taille ;
	 * ne devrait être utilisé que pendant l'initialisation d'un nouvel univers .
     * @param taille spécifie la longeur des côtés de notre quadtree
     */
    public GOLqTreeNode(int taille) {
		valeur = false;
		enfants = new GOLqTreeNode[4];
		for(int i = 0; i < 4; i++){
			enfants[i] = null;
		}
		this.taille = (int) Math.pow(2,taille);//utile pour le dessin d'un quadtree mais du coup sert vraiment juste a ca et est inutile sur les nodes non racine.
		population = 0;
	}

	/**
	 * Ce constructeur crée une node feuille avec la valeur demandé.
	 * @param val (True/False) spécifie la valeur de la node
	 */
	public GOLqTreeNode(Boolean val) {
		this.taille = 0;
		valeur = val;
		population = (valeur) ? 1 : 0;
		enfants = new GOLqTreeNode[4];
		for(int i = 0; i < 4; i++){
			enfants[i] = null;
		}
	}

	/**
	 * Ce constructeur crée un quadtree avec ses enfants en paramètres.
     * @param E1 enfant nw
     * @param E2 enfant ne
     * @param E3 enfant sw
     * @param E4 enfant se
     */
	public GOLqTreeNode(GOLqTreeNode E1,GOLqTreeNode E2,GOLqTreeNode E3,GOLqTreeNode E4) {
		enfants = new GOLqTreeNode[4];
		enfants[0] = E1;
		enfants[1] = E2;
		enfants[2] = E3;
		enfants[3] = E4;
		this.taille = 0;
		//manière rapide de calculer le nombre de cases vivantes dans un quadtree
		population = ((E1 != null) ? E1.getPopulation() : 0) +
					 ((E2 != null) ? E2.getPopulation() : 0) +
					 ((E3 != null) ? E3.getPopulation() : 0) +
					 ((E4 != null) ? E4.getPopulation() : 0);
		if(population > 0)valeur = true;
		else valeur = false;
	}

    /**
	 * Cette méthode permet de modifier la référence du décodeur (donc le décodeur en lui même) ;
	 * on est obligé de passer la référence du décodeur afin que chaque branche sache comment interpréter le quadtree.
     * @param d référence du décodeur
     */
    public static void setDecodeur(DecodeqTree d){
        decodeur = d;
    }

	/**
	 * La fonction create va nous permettre de "changer" de constructeur, elle ne devrait être utilisée que dans ce fichier.
	 * @param E1 enfant nw
     * @param E2 enfant ne
     * @param E3 enfant sw
     * @param E4 enfant se
	 * @return un nouveau GOLqTreeNode utilisable pour les calculs de génération
	 */
	public GOLqTreeNode create(GOLqTreeNode E1,GOLqTreeNode E2,GOLqTreeNode E3,GOLqTreeNode E4){
		return new GOLqTreeNode(E1,E2,E3,E4);
	}

	/**
	 * La fonction create va nous permettre de "changer" de constructeur, elle ne devrait être utilisée que dans ce fichier.
	 * @param val (True/False) spécifie la valeur de notre node
	 * @return un nouveau GOLqTreeNode utilisable pour les calculs de génération
	 */
	public GOLqTreeNode create(Boolean val){
		return new GOLqTreeNode(val);
	}

	/**
	 * La fonction create va nous permettre de "changer" de constructeur, elle ne devrait être utilisée que dans ce fichier.
	 * @param taille spécifie la longeur des côtés de notre node
	 * @return un nouveau GOLqTreeNode de taille "taille"
	 */
	public static GOLqTreeNode create(int taille){
		return new GOLqTreeNode(taille);
	}

	
	/**
	 * Cette méthode permet de créer un arbre plein de population 0 et de taille demandée (arbre vide de taille demandée).
	 * @param taille spécifie la longeur des côtés de notre node
	 * @return un arbre plein de population 0 et de taille "taille"
	 */
	public GOLqTreeNode emptyTree(int taille){
		if(taille == 0)return create(false);
		GOLqTreeNode res = create(emptyTree(taille-1),emptyTree(taille-1),emptyTree(taille-1),emptyTree(taille-1));
		res.taille = (int) Math.pow(2,taille);
		return res;
	}
    //*************************************************************************************************************//

    //*************************************************CONDITIONS**************************************************//

	/**
	 * Cette méthode vérifie la descendance de l'arbre.
	 * @return true si la node est une feuille et false sinon
	 */
	public boolean isleaf(){
		for(int i = 0; i < 4; i++){
			if(enfants[i] != null)return false;
		}
		return true;
	}


    //*************************************************************************************************************//


    //***************************************************SETTERS***************************************************//


    /**
	 * Cette fonction n'est qu'utilisé une fois, elle permet de changer la taille d'un arbre depuis un autre arbre ; 
	 * c'est une étape nécéssaire au changement de génération étant donné que l'arbre change de taille lorsqu'on change de génération.
     * @param val nouvelle taille de l'arbre
     */
    public void setTaille(int val){
		taille = val;
    }


	//raison pour laquelle on a besoin d'un arbre plein de population 0
	/**
	 * Cette méthode permet de modifier la valeur du quadtree représentant la case de coordonnées (posY,posX).
	 * @param level niveau du quadtree
	 * @param posX Abscisse de la case
	 * @param posY Ordonnée de la case
	 * @param value nouvelle valeur
	 * @return Quadtree après modification
	 */
    private GOLqTreeNode setValAux(int level,int posX,int posY,Boolean value){
		if(posX == 0 && posY == 0 && level == 0){
			return create(value);
		}else{
			int s = (int)Math.pow(2.0,(double)level);
			if(posX < s - (s/2)){
				if(posY < s - (s/2)){
                    return create(enfants[0].setValAux(level-1,posX,posY,value),enfants[1],enfants[2],enfants[3]);
				}else{
					return create(enfants[0],enfants[1],enfants[2].setValAux(level-1,posX,posY-(s/2),value),enfants[3]);
				}
			}else{
				if(posY < s - (s/2)){
					return create(enfants[0],enfants[1].setValAux(level-1,posX-(s/2),posY,value),enfants[2],enfants[3]);
				}else{
					return create(enfants[0],enfants[1],enfants[2],enfants[3].setValAux(level-1,posX-(s/2),posY-(s/2),value));
				}
			}
		}
	}

	/**
	 * insère une valeur a la coordonnée (y,x) dans l'arbre.
     * @param y position y comme dans une grille
     * @param x position x comme dans une grille
     * @param tmp objet porteur de la valeur, ce nomme tmp car temporaire dans la fonction
     */
	public void setValAt(int y, int x, Object tmp) {
		if(x >= 0 && x < Math.pow(2,this.taille) && y >= 0 && y < Math.pow(2,this.taille)){
		int lvl = (int)(Math.log(this.taille) / Math.log(2));
			if(tmp instanceof Boolean){
            	Boolean val = (Boolean)tmp;
				GOLqTreeNode tree = this.setValAux(lvl, x, y, val);

				for(int i = 0;i < 4; i++)this.enfants[i] = tree.enfants[i];
			}
		}
	}

    //*********************************************************************************************************************************//

    //***************************************************GETTERS***********************************************************************//

	/**
	 * Cette méthode renvoie la longeur du côté de notre node.
	 * @return longeur du côté de notre node (si spécifié plus tot dans l'éxécution)
	 */
    public int getTaille() {
		return taille;
	}

	/**
	 * Cette méthode renvoie le décodeur des règles de la node.
	 * @return le décodeur de régles de la node
	 */
	public DecodeqTree getDecodeur() {
		return decodeur;
	}

	/**
	 * Cette méthode renvoie la valeur de la node.
	 * @return la valeur de la node
	 */
	public Boolean getValeur() {
		return valeur;
	}

	/**
	 *	on mémoize la profondeur afin de la retrouver rapidement
	 */
	private int profondeur;
	private boolean profondeurdef = false;

	/**
	 * Cette méthode renvoie la profondeur de l'arbre actuel.
	 * @return la profondeur de l'arbre actuel (une feuille a une profondeur de 0)
	 */
	public int getProfondeur(){
		if(profondeurdef == true)return profondeur;
		int prof = 0;
		for(int i = 0; i < 4; i++){
			if(enfants[i] != null){
				prof = Math.max(enfants[i].getProfondeur() +1 , prof);
			}
		}
		profondeur = prof;
		profondeurdef = true;
		return prof;
	}

	/**
	 * Cette méthode renvoie la valeur de la case à la position de coordonnées (posY,posX) et de niveau level.
	 * @param tree arbre dont on veut la valeur de la position de coordonnées (posY,posX) et de niveau level
	 * @param level niveau la valeur de la position de coordonnées (posY,posX)
	 * @param posX abscisse de la position
	 * @param posY ordonnée de la position
	 * @return la valeur à la position de coordonnées (posY,posX) et de niveau level
	 */
	private Boolean getValAux(GOLqTreeNode tree,int level,int posX,int posY){
		if(posX == 0 && posY == 0 && level == 0){
			return tree.valeur;
		}else{
			int s = (int)Math.pow(2.0,(double)level);
			if(posX < s - (s/2)){
				if(posY < s - (s/2)){
					if(tree.enfants[0] == null){
						return false;
					}
					return getValAux(tree.enfants[0],level-1,posX,posY);
				}else{
					if(tree.enfants[2] == null){
						return false;
					}
					return getValAux(tree.enfants[2],level-1,posX,posY-(s/2));
				}
			}else{
				if(posY < s - (s/2)){
					if(tree.enfants[1] == null){
						return false;
					}
					return getValAux(tree.enfants[1],level-1,posX-(s/2),posY);
				}else{
					if(tree.enfants[3] == null){
						return false;
					}
					return getValAux(tree.enfants[3],level-1,posX-(s/2),posY-(s/2));
				}
			}
		}
	}

	/**
	 * Cette méthode renvoie la valeur trouvé à cette coordonnée dans la node.
	 * @param y position y comme dans une grille
     * @param x position x comme dans une grille
	 * @return la valeur trouvé a cette coordonnée dans la node
	 */
	public Boolean getValAt(int y, int x) {
		if(x >= 0 && x < Math.pow(2,this.taille) && y >= 0 && y < Math.pow(2,this.taille)){   //if the value can be found inside the tree

			return getValAux(this,this.taille,x,y);
		}
		return false;
	}


	/**
	 * Cette méthode renvoie la population de la node.
	 * manière très efficace de savoir si une node est vide
	 * @return la population de la node
	 */
    public int getPopulation(){
        return population;
    }

    /**
	 * Cette méthode permet de copier un quadtree et renvoie le clone de l'arbre.
     * @param q le quadtree a copier
     * @return le quadtree q copié
     */
    public GOLqTreeNode cloneUnivers(GOLqTreeNode q) {
		if(q.isleaf())return create(q.getValeur());
		return create((q.enfants[0] != null ) ? cloneUnivers(q.enfants[0]) : null,(q.enfants[1] != null ) ? cloneUnivers(q.enfants[1]) : null,(q.enfants[2] != null ) ? cloneUnivers(q.enfants[2]) : null,(q.enfants[3] != null ) ? cloneUnivers(q.enfants[3]) : null);
	}

	/**
	 * Cette méthode permet d'obtenir la node centrale de l'arbre.
	 * prennons l'arbre A de profondeur 3, cette fonction permet de générer un arbre de profondeur 2 qui correspond au quatres nodes du milieu de A
	 * @return la node centrale de notre arbre
	 */
	public GOLqTreeNode generateCenterNode(){
		if(!isleaf()){
			GOLqTreeNode res = create(
                (this.enfants[0] != null) ? (this.enfants[0].enfants[3] != null) ? this.enfants[0].enfants[3] : null : null,
                (this.enfants[1] != null) ? (this.enfants[1].enfants[2] != null) ? this.enfants[1].enfants[2] : null : null,
                (this.enfants[2] != null) ? (this.enfants[2].enfants[1] != null) ? this.enfants[2].enfants[1] : null : null,
                (this.enfants[3] != null) ? (this.enfants[3].enfants[0] != null) ? this.enfants[3].enfants[0] : null : null);
			return res;
		}
		return null;
	}

    //code permettant la création des 9 nodes intermédiaires:
	/**
	 *  cette fonction nous permet de selectionner quel "troisieme fils" on veut dans un arbre de profondeur 3 MINIMUM.
	 *  this est père de t1 est père de t2 est père de t3
	 *  @param t1 est le premier arbre
	 *  @param t2 est le deuxième arbre
	 *  @param t3 est le troisieme arbre
	 *  @return un quadtree a trois fils
	*/
	private GOLqTreeNode getThirdChild(int t1,int t2,int t3){
		if(this.enfants[t1] != null)if(this.enfants[t1].enfants[t2] != null)if(this.enfants[t1].enfants[t2].enfants[t3] != null)return this.enfants[t1].enfants[t2].enfants[t3];
		return null;
	}

	//les fonctions suivantes génèrent les 9 nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife

	/**
	 * Cette méthode nous renvoie la node centrale de la node centrale de l'arbre.
	 * @return l'une des neuf nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife
	 */
	protected GOLqTreeNode generateCNode(){
		GOLqTreeNode res = create(getThirdChild(0,3,3),getThirdChild(1,2,2),getThirdChild(2,1,1),getThirdChild(3,0,0));
		return res;
	}

	/**
	 * Cette méthode nous renvoie la node centrale gauche de la node centrale de l'arbre.
	 * @return l'une des neuf nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife
	 */
	protected GOLqTreeNode generateWNode(){
		GOLqTreeNode res = create(getThirdChild(0,2,3),getThirdChild(0,3,2),getThirdChild(2,0,1),getThirdChild(2,1,0));
		return res;
	}

	/**
	 * Cette méthode nous renvoie la node centrale droite de la node centrale de l'arbre.
	 * @return l'une des neuf nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife
	 */
	protected GOLqTreeNode generateENode(){
		GOLqTreeNode res = create(getThirdChild(1,2,3),getThirdChild(1,3,2),getThirdChild(3,0,1),getThirdChild(3,1,0));
		return res;
	}

	/**
	 * Cette méthode nous renvoie la node centrale Nord de la node centrale de l'arbre.
	 * @return l'une des neuf nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife
	 */
	protected GOLqTreeNode generateNNode(){
		GOLqTreeNode res = create(getThirdChild(0,1,3),getThirdChild(1,0,2),getThirdChild(0,3,1),getThirdChild(1,2,0));
		return res;
	}

	/**
	 * Cette méthode nous renvoie la node centrale Sud de la node centrale de l'arbre.
	 * @return l'une des neuf nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife
	 */
	protected GOLqTreeNode generateSNode(){
		GOLqTreeNode res = create(getThirdChild(2,1,3),getThirdChild(3,0,2),getThirdChild(2,3,1),getThirdChild(3,2,0));
		return res;
	}

	/**
	 * Cette méthode nous renvoie la node centrale Nord ouest de la node centrale de l'arbre.
	 * @return l'une des neuf nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife
	 */
	protected GOLqTreeNode generateNWNode(){
		GOLqTreeNode res = create(getThirdChild(0,0,3),getThirdChild(0,1,2),getThirdChild(0,2,1),getThirdChild(0,3,0));
		return res;
	}

	/**
	 * Cette méthode nous renvoie la node centrale Nord est de la node centrale de l'arbre.
	 * @return l'une des neuf nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife
	 */
	protected GOLqTreeNode generateNENode(){
		GOLqTreeNode res = create(getThirdChild(1,0,3),getThirdChild(1,1,2),getThirdChild(1,2,1),getThirdChild(1,3,0));
		return res;
	}

	/**
	 * Cette méthode nous renvoie la node centrale Sud ouest de la node centrale de l'arbre.
	 * @return l'une des neuf nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife
	 */
	protected GOLqTreeNode generateSWNode(){
		GOLqTreeNode res = create(getThirdChild(2,0,3),getThirdChild(2,1,2),getThirdChild(2,2,1),getThirdChild(2,3,0));
		return res;
	}

	/**
	 * Cette méthode nous renvoie la node centrale Sud est de la node centrale de l'arbre.
	 * @return l'une des neuf nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife
	 */
	protected GOLqTreeNode generateSENode(){
		GOLqTreeNode res = create(getThirdChild(3,0,3),getThirdChild(3,1,2),getThirdChild(3,2,1),getThirdChild(3,3,0));
		return res;
	}


    /**
	 * Cette méthode renvoie le nombre de voisins des nodes centrales de l'arbre de niveau 2.
     * @return le nombre de voisins des nodes centrales dans un arbre de niveau 2
     */
    private int[] getNeighbors(){
        //on génère la node grandpère
        boolean[][] tab = new boolean[4][4];
        int accessor = 0;
        for(int i = 0; i < 2; i++ ){
            for(int k = 0; k < 2; k++, accessor++ ){
                if(enfants[accessor] != null){
                    int a = 0;
                    for(int x = 0; x < 2; x++)
                        for(int y = 0; y < 2; y++,a++)
                            if(enfants[accessor].enfants[a] != null)tab[x+(2*i)][y+(2*k)] = enfants[accessor].enfants[a].getValeur();
                }else{
                    for(int x = 0; x < 2; x++)
                        for(int y = 0; y < 2; y++)
                            tab[x+(2*i)][y+(2*k)] = false;
                }
            }
        }
        //une liste des voisins de chaque enfant (représentation plate pour les enfants donc de même pour res).
        //c'est vraiment juste plus pratique pas de raison particulière...
        int[] res = new int[4];

        //c'est moche hein, imaginez mon mal de crane...
        int a = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++,a++){
                int n = 0;
                for(int x = -1; x < 2; x++){
                    for(int y = -1; y < 2; y++){
                        if(x == 0 && y == 0){;}
                        else if (tab[x+1+i][y+1+j] == true) {
                            n += 1;
                        }
                    }
                }
                res[a] = n;
            }
        }

        //on retourne...
        return res;

    }

    //********************************************************************************************************************************//
    //************************************************CODE JEU DE LA VIE**************************************************************//
	/**
	 * Cette méthode permet de nettoyer un node
	 * inutile tant que l'arbre n'est pas dans une cache
	 * @param utilisations nombre d'utilisations maximum pour le nettoyage de la node
	 * @return false
	 */
	public boolean nettoyage(int utilisations){
		return false; //une node sans cache ne peux pas être vidé de la cache
	}

    /**
	 * Cette méthode fait une simulation des prochaines générations.
     * @return la node centrale a la prochaine génération d'une node de niveau 2
     */
    private GOLqTreeNode simulationBase(){
        //faire le machin rigolo(passer a la prochaine génération)
        //on procède au comptage des voisins mais d'abord on verifie que ne fait pas tout pour rien
        //la node est elle vide ?
        //si oui pas de prise de tête...
        if(getPopulation() > 0){
            //voir la fonction précédente
            int[] n = getNeighbors();
            //pour récupérer les données dont nous avons besoin...
            GOLqTreeNode pere = generateCenterNode();
			GOLqTreeNode out = new GOLqTreeNode(false);	//très important de ne pas utiliser create car sinon on canonise une node et on la modifie ce qui casse tout
            //l'arbre que l'on va renvoyer
            for(int i = 0; i < 4; i++){
                Boolean x = false;
                boolean val = false;
                //on récupère la valeur de la case
                if(pere != null)if( pere.enfants[i] != null)val = pere.enfants[i].getValeur();
                //on agit en fonction de cette valeur (vrai/faux)
                if(val)x = decodeur.getSurvive(n[i]);
                else x = decodeur.getBirth(n[i]);
                //et hop on met dans le résultat!
                //j'ai clairement eu de la chance que l'ordre ne change pas :P
                out.enfants[i] = create(x);
            }
            GOLqTreeNode res = create(out.enfants[0],out.enfants[1],out.enfants[2],out.enfants[3]);
            //hop hop hop on néttoie, dans mon expérience l'algorithme fonctionne sans
            //mais je ne suis pas sur de pourquoi et ca évite certaines érreurs
            if(res.getPopulation() == 0)return null;
            return res;
        }
        return null;
    }

    //fonction de génération de la prochaine génération, elle retourne forcément le GOLqTreeNode central de la node qui l'appelle
    /**
	 * Cette méthode renvoie la node centrale à la prochaine génération de notre node.
     * @param level permet de rapidement déterminer la profondeur du quadtree concerné
     * @return la node centrale a la prochaine génération de notre node
     */
    public GOLqTreeNode nextGenerationAux(int level){
        if(getPopulation() < 1 || level < 2)return null;
        if(level == 2){
            return simulationBase();
        }
        if (level > 2){
            //on génère les 9 arbres pour couvrir toutes les cases et leur voisines de l'arbre central de THIS
            GOLqTreeNode C  = generateCNode ();
            GOLqTreeNode N  = generateNNode ();
            GOLqTreeNode S  = generateSNode ();
            GOLqTreeNode W  = generateWNode ();
            GOLqTreeNode E  = generateENode ();
            GOLqTreeNode NW = generateNWNode();
            GOLqTreeNode SW = generateSWNode();
            GOLqTreeNode NE = generateNENode();
            GOLqTreeNode SE = generateSENode();

            //on prend leur prochaine génération (rappelez vous la prochaine génération est 2x plus petite!
            //donc on revient a la taille initiale)

            return create(create(NW, N, W, C).nextGenerationAux(level-1),
                          create(N, NE, C, E).nextGenerationAux(level-1),
                          create(W, C, SW, S).nextGenerationAux(level-1),
                          create(C, E, S, SE).nextGenerationAux(level-1));

        }
        return null;
    }

    /**
	 * Cette méthode renvoie un arbre vide qui a pour node centrale notre node.
     * @return un arbre vide qui a pour node centrale notre node
     */
    protected GOLqTreeNode extend(){
        //on crée l'arbre bordure qui est un arbre dont le centre est l'arbre sur lequel on fait les calculs
        //c'est joli en plus :)
        GOLqTreeNode border = create(create(null,null,null,this.enfants[0]),
                                     create(null,null,this.enfants[1],null),
                                     create(null,this.enfants[2],null,null),
                                     create(this.enfants[3],null,null,null));
        return border;
    }

    /**
	 * Cette méthode renvoie notre node à la prochaine génération.
     * @return notre node a la prochaine génération
     */
    public GOLqTreeNode nextGeneration(){
        GOLqTreeNode tmp = this.extend();
		tmp = tmp.nextGenerationAux(tmp.getProfondeur());
        tmp.setTaille(this.taille);//sinon l'affichage casse...
        return tmp;
    }

    //*********************************************************************************************************//

    //***********************************************AFFICHAGE*************************************************//




	/**
     * Cette méthode permet d'afficher le quadtree de taille taille qui a pour index de départ (y,x).
     * @param g Graphics à utiliser
     * @param y Ordonnée de départ de l'affichage
     * @param x Abscisse de départ de l'affichage
     * @param taille taille à afficher
     * @param zoom puissance du zoom 
     */
    @Override
	public void Afficher(Graphics g, int y, int x, int taille, float zoom) {
		if(this != null){
			g.setColor(Color.white);
			g.fillRect((int)(0), (int)(0), (int)(1024), (int)(1024));
			g.setColor(Color.black);
			if(this.valeur && isleaf()){
				g.fillRect((int)(y*zoom), (int)(x*zoom), (int)(taille*zoom), (int)(taille*zoom));
			}
			int lvl = (int)(Math.log(this.taille) / Math.log(2));
			if(this.enfants[0] != null)this.enfants[0].AfficherAux(g,lvl - 1, x				, y			, taille/2, zoom);
			if(this.enfants[1] != null)this.enfants[1].AfficherAux(g,lvl - 1, x+taille/2	, y			, taille/2, zoom);
			if(this.enfants[2] != null)this.enfants[2].AfficherAux(g,lvl - 1, x				, y+taille/2, taille/2, zoom);
			if(this.enfants[3] != null)this.enfants[3].AfficherAux(g,lvl - 1, x+taille/2	, y+taille/2, taille/2, zoom);
		}
	}

	/**
	 * Cette méthode est une méthode auxilliaire de Afficher.
	 * @param g Graphics à utiliser
	 * @param level niveau du quadtree
     * @param y Ordonnée de départ de l'affichage
     * @param x Abscisse de départ de l'affichage
     * @param taille taille à afficher
     * @param zoom puissance du zoom 
	 */
	private void AfficherAux(Graphics g,int level,int x, int y, int taille,float zoom){
		if(this != null && level >= 0){
			//pour l'affichage DEBUG ne pas enlever SVP
			/*
			g.setColor(Color.red);
			g.fillRect((int)(x*zoom), (int)(y*zoom), (int)(taille*zoom), (int)(taille*zoom));

			g.setColor(Color.white);
			g.fillRect((int)(x*zoom)+1, (int)(y*zoom)+1, (int)(taille*zoom)-2, (int)(taille*zoom)-2);
			//*/
			boolean hasdrawn = false;
			if(zoom >= 1){
				if(this.valeur && level == 0 && isleaf()){
					g.setColor(Color.black);
					g.fillRect((int)(x*zoom), (int)(y*zoom), (int)(taille*zoom), (int)(taille*zoom));
					hasdrawn = true;
				}
			}else{
				if(this.population > 0 && level == Math.log((1.0f/zoom))/Math.log(2)){
					//affichage "joli" mais quasi invisible a l'echelle des metacell
					//double ratio = (double)population / Math.pow(Math.pow(2.0,(double)(level)),2.0);
					//if(ratio < 0.0)ratio = 0.0;
					//if(ratio > 1.0)ratio = 1.0;
					//ratio = 1.0 - ratio;
					//float ratiof = (float)ratio;
					//Color col = new Color(ratiof, ratiof, ratiof);
					//g.setColor(col);
					g.setColor(Color.black);
					g.fillRect((int)(x*zoom), (int)(y*zoom), (int)(taille*zoom), (int)(taille*zoom));
					hasdrawn = true;
				}
			}
			if(!hasdrawn){
				if(this.enfants[0] != null && enfants[0].getPopulation() > 0)this.enfants[0].AfficherAux(g,level - 1, x				, y			, taille/2, zoom);
				if(this.enfants[1] != null && enfants[1].getPopulation() > 0)this.enfants[1].AfficherAux(g,level - 1, x+taille/2	, y			, taille/2, zoom);
				if(this.enfants[2] != null && enfants[2].getPopulation() > 0)this.enfants[2].AfficherAux(g,level - 1, x				, y+taille/2, taille/2, zoom);
				if(this.enfants[3] != null && enfants[3].getPopulation() > 0)this.enfants[3].AfficherAux(g,level - 1, x+taille/2	, y+taille/2, taille/2, zoom);
			}
		}
	}

    //*********************************************************************************************************//
}
//fin
