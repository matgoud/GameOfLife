package automate;

/**
 * Cette classe abstraite initialise un objet de type Decode et ses méthodes.
 */
public abstract class Decode{
    protected String rules;

    public Decode(String rules){
        this.rules=rules;
    }

    /**
     * Cette méthode permet de récupérer les règles du décodeur.
     * @return les règles du décodeur
     */
    public String getRules(){
        return rules;
    }

    /**
     * Cette méthode permet de vérifier les règles du décodeur.
     * @return true si les règles sont valides, false sinon
     */
    public abstract boolean areRulesValid();

}

