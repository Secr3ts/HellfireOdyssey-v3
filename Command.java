/**
 * Classe Command - une commande du jeu d'aventure Zuul.
 *
 * @author Aloïs Fournier
 */
public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    
    /**
     * Constructeur de la classe Command
     * @param pFirstWord Le premier mot de la commande
     * @param pSecondWord Le second mot de la commande  
     */
    public Command(final String pFirstWord, final String pSecondWord) {
        this.aCommandWord = pFirstWord;
        this.aSecondWord = pSecondWord;
    }
    
    /**
     * Retourne le premier mot de la commande
     * @return Le premier mot de la commande
    */
    public String getCommandWord() {
        return this.aCommandWord;
    }
    
    /**
     * Retourne le second mot de la commande
     * @return Le second mot de la commande
    */
    public String getSecondWord() {
        return this.aSecondWord;
    }
    
    /**
     * Modifie le premier mot de la commande
     * @param pNewCommandWord Le nouveau premier mot de la commande
    */
    public void setCommandWord(final String pNewCommandWord) {
        this.aCommandWord = pNewCommandWord;
    }
    
    /**
     * Modifie le second mot de la commande
     * @param pNewSecondWord Le nouveau second mot de la commande
    */
    public void setSecondWord(final String pNewSecondWord) {
        this.aSecondWord = pNewSecondWord;
    }
    
    /**
     * Retourne false si la commande n'a pas de second mot
     * @return false si la commande n'a pas de second mot
    */
    public boolean hasSecondWord() {
        return this.aSecondWord == null ? false : true;
    }
    
    /**
     * @return true si la commande est inconnue
     */
    public boolean isUnknown() {
        return this.aCommandWord == null ? true : false;
    }
} // Command
