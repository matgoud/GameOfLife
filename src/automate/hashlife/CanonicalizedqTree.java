package automate.hashlife;
import java.util.HashMap;


/**
 *	Cette classe permet de canonicaliser les nodes
 *	@author : Clément Bartolone L2 Info TP 3A
 */
public class CanonicalizedqTree extends GOLqTreeNode {
    //el senior se llama hashmap
    public static HashMap<GOLqTreeNode,GOLqTreeNode> hashMap = new HashMap<GOLqTreeNode,GOLqTreeNode>();
    //il nous faut le hashcode qui acompagne les fonction des hashmaps
    //tests : une node feuille avec une valeur True renverra 1
    //tests : une node feuille avec une valeur False renverra 0
    /**
     * Cette méthode permet de hasher la node actuelle afin de la stocker dans une hashmap
     * @return 1 si la node est feuille avec une population égale à 1, et 0 si la node est une feuille avec une population différent de 1
     */
    @Override
    public int hashCode() {
        if (isleaf()){
			if(getPopulation() == 1)return 1;        //getValue() and getPopulation() seem to always be equal to 0 therefore creating our issue.
			return 0;
		}
		//j'ai trouvé ces valeurs en ligne, elles mènent a moins de collisions
		//voir : https://www.drdobbs.com/jvm/an-algorithm-for-compressing-space-and-t/184406478
		//(l'article fonctionne mal faut éditer le HTML pour accéder aux prochaine pages)
        return System.identityHashCode(enfants[0]) +
             11 * System.identityHashCode(enfants[1]) +
             101 * System.identityHashCode(enfants[2]) +
             1007 * System.identityHashCode(enfants[3]) ;
    }

    //tests : deux nodes avec les même enfants même population et meme valeur (si la node est feuille)
    /**
     * Cette méthode permet de déterminer si deux nodes sont égales a nos yeux afin de les stocker dans une hashmap
     * @param o la node a comparer
     */
    public boolean equals(Object o){
        GOLqTreeNode q = (GOLqTreeNode)o;
		if(getPopulation() != q.getPopulation())return false;
		if(isleaf()){
			if(getValeur() != q.getValeur())return false;
			return true;
		}
		return enfants[0] == q.enfants[0] && enfants[1] == q.enfants[1] && enfants[2] == q.enfants[2] && enfants[3] == q.enfants[3];
	}

    /**
     * Ce constructeur permet de créer une node arbre avec les enfants donnés en argument.
     * @param E1 enfant nw
     * @param E2 enfant ne
     * @param E3 enfant sw
     * @param E4 enfant se
     */
    public CanonicalizedqTree(GOLqTreeNode E1,GOLqTreeNode E2,GOLqTreeNode E3,GOLqTreeNode E4){
        super(E1,E2,E3,E4);
    }

    /**
     * Ce constructeur permet de créer une node de taille souhaitée ;
	 * ne devrait être utilisé que pendant l'initialisation d'un nouvel univers.
     * @param taille spécifie la longeur des côtés de notre quadtree
     */
    public CanonicalizedqTree(int taille) {
		super(taille);
	}

	/**
     * Ce constructeur permet de créer une node feuille avec la valeur demandée.
	 * @param val (True/False) spécifie la valeur de la node
	 */
	public CanonicalizedqTree(Boolean val) {
		super(val);
	}

    /**
     * Cette méthode permet canoniser la node : si la node n'a jamais été rencontrée au préalable on la met dans une hashmap ; 
     * si la node est dans la hashmap on renvoie celle déjà existante dans la hashmap.
     * @return notre node canonisée
     */
    public GOLqTreeNode integre() {
        GOLqTreeNode canon = (GOLqTreeNode)hashMap.get(this) ;
        if (canon != null){ //si la node est déjà dans la hashmap on balance et on renvoie celle déjà existante
            return canon ;
        }
        hashMap.put(this, this) ;   //si la node n'est pas dans la hashmap on la met dedans
        return this ;
    }
    //tests, renvoie la meme node si elle n'est pas deja dans la hashmap
    /**
     * Cette méthode permet de créer une node arbre canonisé avec les enfants donnés en argument.
     * @param E1 enfant nw
     * @param E2 enfant ne
     * @param E3 enfant sw
     * @param E4 enfant se
     */
    public GOLqTreeNode create(GOLqTreeNode E1,GOLqTreeNode E2,GOLqTreeNode E3,GOLqTreeNode E4){
		GOLqTreeNode res = new CanonicalizedqTree(E1,E2,E3,E4).integre();
		return res;
	}
    //tests, renvoie la meme node si elle n'est pas deja dans la hashmap
    /**
	 * Cette méthode crée une node feuille avec la valeur demandée.
	 * @param val (True/False) spécifie la valeur de la node
	 */
	public GOLqTreeNode create(Boolean val){
		GOLqTreeNode res = new CanonicalizedqTree(val).integre();
		return res;
	}
    //tests, renvoie la meme node si elle n'est pas deja dans la hashmap
    /**
	 * Cette méthode crée une node arbre de la taille demandée ; 
     * ne devrait être utilisé que pendant l'initialisation d'un nouvel univers.
     * @param taille spécifie la longeur des côtés de notre quadtree
     */
	public static GOLqTreeNode create(int taille){
		return new CanonicalizedqTree(taille);
	}

}
