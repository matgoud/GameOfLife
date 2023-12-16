package automate.hashlife;
import java.util.HashMap;

/**
 *	Cette classe implémentant "Almost Hashlife".
 *	@author : Clément Bartolone L2 Info TP 3A
 */
public class MemoizedqTree extends CanonicalizedqTree {
	protected DecodeqTree reglesCache = null;
	private GOLqTreeNode next = null;

	/**
	 * Ce constructeur permet de créer une nouvelle instance de MemoizedqTree en une node arbre avec ses enfants donnés en paramètres.
     * @param E1 enfant nw
     * @param E2 enfant ne
     * @param E3 enfant sw
     * @param E4 enfant se
     */
    public MemoizedqTree(GOLqTreeNode E1,GOLqTreeNode E2,GOLqTreeNode E3,GOLqTreeNode E4){
        super(E1,E2,E3,E4);
        reglesCache = getDecodeur();
    }

    /**
	 * Ce constructeur permet de créer une nouvelle instance de MemoizedqTree de la taille donnée en paramètres ;
	 * ne devrait être utilisé que pendant l'initialisation d'un nouvel univers.
     * @param taille spécifie la longeur des côtés de notre quadtree
     */
    public MemoizedqTree(int taille) {
		super(taille);
		reglesCache = getDecodeur();
	}

	/**
	 * Cette méthode créée une node feuille avec la valeur demandée.
	 * @param val (True/False) spécifie la valeur de la node
	 */
	public MemoizedqTree(Boolean val) {
		super(val);
		reglesCache = getDecodeur();
	}

	/**
	 * Cette méthode permet de créer une nouvelle instance de MemoizedqTree avec des enfants donnés en paramètres, le tout canonisé.
     * @param E1 enfant nw
     * @param E2 enfant ne
     * @param E3 enfant sw
     * @param E4 enfant se
     */
    public GOLqTreeNode create(GOLqTreeNode E1,GOLqTreeNode E2,GOLqTreeNode E3,GOLqTreeNode E4){
		GOLqTreeNode res = new MemoizedqTree(E1,E2,E3,E4).integre();
		return res;
	}

	/**
	 * Cette méthode créée une node feuille canonisée avec la valeur demandée.
	 * @param val (True/False) spécifie la valeur de la node
	 */
	public GOLqTreeNode create(Boolean val){
		GOLqTreeNode res = new MemoizedqTree(val).integre();
		return res;
	}

	/**
	 * Cette méthode créée une nouvelle instance de MemoizedqTree de taille donnée en paramètres canonisé ;
	 * ne devrait être utilisé que pendant l'initialisation d'un nouvel univers.
     * @param taille spécifie la longeur des côtés de notre quadtree
     */
	public static GOLqTreeNode create(int taille){
		return new MemoizedqTree(taille);
	}

	/**
	 * Cette méthode renvoie la node centrale à la prochaine génération de notre node ;
	 * si la node a déjà été calculée on renvoie ce résultat a la place de refaire le calcul.
	 * Permet aussi de détruire la node si les règles changent.
     * @param level permet de rapidement déterminer la profondeur du quadtree concerné
     * @return la node centrale a la prochaine génération de notre node
     */
	@Override
	public GOLqTreeNode nextGenerationAux(int level){
		if(reglesCache != getDecodeur()){							//on clear la cache de manière ultra agréssive lorsque les règles changent
			hashMap = new HashMap<GOLqTreeNode,GOLqTreeNode>();		//on vide la hashmap
			reglesCache = getDecodeur();							//on change les regles
			next = null;											//on clear la cache memoizé
		}
		if(next == null){//on regarde si la cache a été définie pour cette node.
			next = super.nextGenerationAux(level);
		}
		return next;
	}




}
