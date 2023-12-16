package automate.hashlife;

/**
 *  Cette classe permet d'implementer Hashlife.
 *	@author : Clément Bartolone L2 Info TP 3A
 */
public class HashLifeqTree extends MemoizedqTree{
	/**
	 *	 spécifie la vitesse de simulation : 	0 = 1 génération par génération
	 *											1 = 2**(taille-2) génerations par génération
	 * 											>1 = comme 1 mais on extend la taille de notre arbre
	 */
	private static int speed = 0;
    /**
	 * Ce constructeur permet de créer une node arbre avec les enfants donnés en argument.
     * @param E1 enfant nw
     * @param E2 enfant ne
     * @param E3 enfant sw
     * @param E4 enfant se
     */
    public HashLifeqTree(GOLqTreeNode E1,GOLqTreeNode E2,GOLqTreeNode E3,GOLqTreeNode E4){
        super(E1,E2,E3,E4);
    }

	//il faut absolument clear la cache après l'appel de cette fonction.
	//cela peut être fait en changeant les regles
	/**
	 * Cette méthode permet de modifier la vitesse de la simulation.
	 * @param s (s pour speed), change la vitesse de la simulation.
	 */
	public static void setSpeed(int s){
		speed = s;
	}

	/**
	 * Cette méthode permet d'obtenir la vitesse actuelle de la simulation.
	 * @return la vitesse actuelle
	 */
	public static int getSpeed(){
		return speed;
	}

    /**
	 * Ce constructeur permet de créer une node arbre de la taille demandée.
     * @param taille équivaut a la taille de chaque côté
     */
    public HashLifeqTree(int taille) {
		super(taille);
	}

	/**
	 * Ce constructeur permet de créer une node feuille de valeur demandée.
	 * @param val valeur de la node (True/False)
	 */
	public HashLifeqTree(Boolean val) {
		super(val);
	}

	/**
	 * Cette méthode permet de créer un quadtree instance de HashLifeqTree Canonisé
	 * @param E1 enfant nw
     * @param E2 enfant ne
     * @param E3 enfant sw
     * @param E4 enfant se
	 * @return un quadtree instance de HashLifeqTree avec les enfants spécifiés et Canonisé
	 */
    public GOLqTreeNode create(GOLqTreeNode E1,GOLqTreeNode E2,GOLqTreeNode E3,GOLqTreeNode E4){
		GOLqTreeNode res = new HashLifeqTree(E1,E2,E3,E4).integre();
		return res;
	}

	/**
	 * Cette méthode permet de créer un feuille instance de HashLifeqTree Canonisé
	 * @param val valeur de la node
	 * @return un quadtree instance de HashLifeqTree Canonisé
	 */
	public GOLqTreeNode create(Boolean val){
		GOLqTreeNode res = new HashLifeqTree(val).integre();
		return res;
	}
	
	/**
	 * Cette méthode permet de créer un quadtree instance de HashLifeqTree Canonisé de taille taille
	 * @param taille côté de l'arbre
	 * @return un quadtree instance de HashLifeqTree
	 */
	public static GOLqTreeNode create(int taille){
		return new HashLifeqTree(taille);
	}

    /**
	 * Cette méthode renvoie un arbre dont les nodes superieures viennent de haut et les nodes inferieures viennent de bas
     * @param haut le quadtree dont on veux prendre les deux nodes inférieures : le quadtree le plus haut
     * @param bas le quadtree dont on veux prendre les deux nodes supèrieures : le quadtree le plus bas
     * @return	un arbre dont les nodes superieures viennent de haut et les nodes inferieures viennent de bas
     */
    private GOLqTreeNode generateHorizontalCenterNode(GOLqTreeNode haut,GOLqTreeNode bas){
		if(!isleaf()){
			GOLqTreeNode res = create(
                (haut != null) ? haut.enfants[2] : null,
                (haut != null) ? haut.enfants[3] : null,
                (bas != null) ? bas.enfants[0] : null,
                (bas != null) ? bas.enfants[1] : null);
			return res;
		}
		return null;
	}

    /**
	 * Cette méthode renvoie un arbre dont les nodes de droite viennent de gauche et les nodes de gauche viennent de droite
     * @param gauche le quadtree dont on veux prendre les deux nodes de droite : le quadtree le plus a gauche
     * @param droite le quadtree dont on veux prendre les deux nodes de gauche : le quadtree le plus a droite
     * @return un arbre dont les nodes de droite viennent de gauche et les nodes de gauche viennent de droite
     */
    private GOLqTreeNode generateVerticalCenterNode(GOLqTreeNode gauche,GOLqTreeNode droite){
		if(!isleaf()){
			GOLqTreeNode res = create(
                (gauche != null) ? gauche.enfants[1] : null,
                (droite != null) ? droite.enfants[0] : null,
                (gauche != null) ? gauche.enfants[3] : null,
                (droite != null) ? droite.enfants[2] : null);
			return res;
		}
		return null;
	}
	
    //les fonctions suivantes génèrent les 9 nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife.
	/**
	 * Cette méthode nous renvoie la node centrale de la node centrale de l'arbre
	 * @return l'une des neuf nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife
	 */
    @Override
	protected GOLqTreeNode generateCNode(){
		if(speed > 0)return generateCenterNode().nextGenerationAux(this.getProfondeur()-1);
		return generateCenterNode().generateCenterNode();
	}

	/**
	 * Cette méthode nous renvoie la node centrale gauche de la node centrale de l'arbre.
	 * @return l'une des neuf nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife
	 */
    @Override
	protected GOLqTreeNode generateWNode(){
		if(speed > 0)return generateHorizontalCenterNode(enfants[0], enfants[2]).nextGenerationAux(this.getProfondeur()-1);
		return generateHorizontalCenterNode(enfants[0], enfants[2]).generateCenterNode();
	}

	/**
	 * Cette méthode nous renvoie la node centrale droite de la node centrale de l'arbre.
	 * @return l'une des neuf nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife
	 */
    @Override
	protected GOLqTreeNode generateENode(){
		if(speed > 0)return generateHorizontalCenterNode(enfants[1], enfants[3]).nextGenerationAux(this.getProfondeur()-1);
		return generateHorizontalCenterNode(enfants[1], enfants[3]).generateCenterNode();
	}

	/**
	 * Cette méthode nous renvoie la node centrale Nord de la node centrale de l'arbre.
	 * @return l'une des neuf nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife
	 */
    @Override
	protected GOLqTreeNode generateNNode(){
		if(speed > 0)return generateVerticalCenterNode(enfants[0], enfants[1]).nextGenerationAux(this.getProfondeur()-1);
		return generateVerticalCenterNode(enfants[0], enfants[1]).generateCenterNode();
	}

	/**
	 * Cette méthode nous renvoie la node centrale Sud de la node centrale de l'arbre.
	 * @return l'une des neuf nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife
	 */
    @Override
	protected GOLqTreeNode generateSNode(){
		if(speed > 0)return generateVerticalCenterNode(enfants[2], enfants[3]).nextGenerationAux(this.getProfondeur()-1);
		return generateVerticalCenterNode(enfants[2], enfants[3]).generateCenterNode();
	}

	/**
	 * Cette méthode nous renvoie la node centrale Nord ouest de la node centrale de l'arbre.
	 * @return l'une des neuf nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife
	 */
    @Override
	protected GOLqTreeNode generateNWNode(){
		if(speed > 0)return (enfants[0] != null) ? enfants[0].nextGenerationAux(this.getProfondeur()-1) : null;
		return (enfants[0] != null) ? enfants[0].generateCenterNode() : null;
	}

	/**
	 * Cette méthode nous renvoie la node centrale Nord est de la node centrale de l'arbre.
	 * @return l'une des neuf nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife
	 */
    @Override
	protected GOLqTreeNode generateNENode(){
		if(speed > 0)return (enfants[1] != null) ? enfants[1].nextGenerationAux(this.getProfondeur()-1) : null;
		return (enfants[1] != null) ? enfants[1].generateCenterNode() : null;
	}

	/**
	 * Cette méthode nous renvoie la node centrale Sud Ouest de la node centrale de l'arbre.
	 * @return l'une des neuf nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife
	 */
    @Override
	protected GOLqTreeNode generateSWNode(){
		if(speed > 0)return (enfants[2] != null) ? enfants[2].nextGenerationAux(this.getProfondeur()-1) : null;
		return (enfants[2] != null) ? enfants[2].generateCenterNode() : null;
	}

	/**
	 * Cette méthode nous renvoie la node centrale Sud Est de la node centrale de l'arbre.
	 * @return l'une des neuf nodes dont nous avons besoin pour la prochaine génération d'un quadtree dans QuadLife
	 */
    @Override
	protected GOLqTreeNode generateSENode(){
		if(speed > 0)return (enfants[3] != null) ? enfants[3].nextGenerationAux(this.getProfondeur()-1) : null;
		return (enfants[3] != null) ? enfants[3].generateCenterNode() : null;

	}

}
