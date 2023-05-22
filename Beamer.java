/**
 * Un beamer est un objet qui permet de se téléporter dans une salle
 * précédemment mémorisée
 * 
 * @author Alois Fournier 27.04.23
 */
public class Beamer extends Item {
    private Room aSavedRoom;
    private boolean aIsCharged = false;

    /**
     * Constructeur de la classe Beamer
     * 
     * @param pName
     * @param pDescription
     * @param pWorth
     * @param pWeight
     */
    public Beamer(final String pName, final String pDescription, final int pWorth, final int pWeight) {
        super(pName, pDescription, pWorth, pWeight);
        this.aIsCharged = false;
        this.aSavedRoom = null;
    }

    /**
     * @return la Roomm sauvegardée
     */
    public Room getSavedRoom() {
        return this.aSavedRoom;
    }

    /**
     * @return vrai si le Beamer est chargé
     */
    public boolean isCharged() {
        return this.aIsCharged;
    }

    /**
     * Charge le Beamer avec la salle passée en paramètre
     * 
     * @param pRoom the room to save
     */
    public void charge(final Room pRoom) {
        this.aSavedRoom = pRoom;
        this.aIsCharged = true;
    }

    /**
     * Décharge le Beamer
     */
    public void uncharge() {
        this.aSavedRoom = null;
        this.aIsCharged = false;
    }

    /**
     * Tire le Beamer
     * 
     * @param pPlayer
     */
    public void fire(final Player pPlayer) {
        if (this.aIsCharged) {
            pPlayer.setCurrentRoom(this.aSavedRoom);
            this.aIsCharged = false;
            pPlayer.removeItem(this.getName());
        }
    }
}
