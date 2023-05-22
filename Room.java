import java.util.HashMap;
import java.util.Set;

/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *
 * @author Aloïs Fournier
 * @version 2023.15.02
 * 
 */
public class Room {
    private String aDescription;
    private HashMap<String, Room> aExits;
    private String aImageName;
    private ItemList aItemList;
    private boolean isTrapDoor;

    /**
     * Constructeur de la classe Room
     * 
     * @param pRoomName  Nom de la salle
     * @param pImageName Nom de l'image de la salle
     */
    public Room(final String pRoomName, final String pImageName) {
        this(pRoomName, pImageName, false);
    }

    /**
     * Constructeur overloadé de la classe Room
     * 
     * @param pRoomName   Nom de la salle
     * @param pImageName  Nom de l'image de la salle
     * @param pIsTrapDoor true si la salle est un trap
     */
    public Room(final String pRoomName, final String pImageName, final boolean pIsTrapDoor) {
        this.aDescription = pRoomName;
        this.aExits = new HashMap<String, Room>();
        this.aImageName = pImageName;
        this.aItemList = new ItemList();
        this.isTrapDoor = pIsTrapDoor;
    }

    /**
     * remove an item from the room
     * 
     * @param pItemName name of the item to remove
     */
    public void removeItem(final String pItemName) {
        this.aItemList.removeItem(pItemName);
    }

    /**
     * returns the trap state
     * 
     * @return true if the room is a trap
     */
    public boolean isExit() {
        return !this.isTrapDoor;
    }

    /**
     * Retourne la description de la salle
     * 
     * @return String description de la salle
     */
    public String getDescription() {
        return this.aDescription;
    }

    /**
     * Définit les sorties de la salle dans la direction indiquée
     * 
     * @param pDirection direction de la sortie
     * @param pRoom      salle de la sortie
     */
    public void setExit(final String pDirection, final Room pRoom) {
        this.aExits.put(pDirection, pRoom);
    }

    /**
     * Retourne la salle dans la direction indiquée
     * 
     * @param pDirection direction de la sortie
     * @return Room salle de la sortie
     */
    public Room getExit(final String pDirection) {
        return this.aExits.get(pDirection);
    }

    /**
     * Retourne les sorties d'une salle en une String
     * 
     * @return String sorties de la salle
     */
    public String getExitString() {

        String exit = "Exits: ";
        Set<String> keys = this.aExits.keySet();

        for (String exitKey : keys) {
            exit += exitKey + " ";
        }
        return exit;
    }

    /**
     * Retourne la description complète de la salle
     * 
     * @return Description complète de la salle
     */
    public String getLongDescription() {
        return "You are in : " + this.aDescription + ".\n" + this.getExitString();
    }

    /**
     * Retourne le nom de l'image de la salle
     * 
     * @return String nom de l'image de la salle
     */
    public String getImageName() {
        return this.aImageName;
    }

    /**
     * Ajoute un item à la salle
     * 
     * @param pItemName nom de l'item à ajouter
     * @param pItem     item à ajouter
     */
    public void addItem(final String pItemName, final Item pItem) {
        this.aItemList.addItem(pItem);
    }

    /**
     * Ajoute un item à la salle
     * 
     * @param pItemName nom de l'item à ajouter
     * @param pDesc     description de l'item
     * @param pWorth    valeur de l'item
     * @param pWeight   poids de l'item
     */
    public void addNewItem(final String pItemName, final String pDesc, final int pWorth, final int pWeight) {
        this.aItemList.addItem(new Item(pItemName, pDesc, pWorth, pWeight));
    }

    /**
     * Vérifie si un item est dans la salle
     * 
     * @param pItemName nom de l'item à vérifier
     * @return true si l'item est dans la salle
     */
    public boolean hasItem(final String pItemName) {
        if (this.aItemList.hasItem(pItemName)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retourne un item de la salle
     * 
     * @param pItemName nom de l'item à retourner
     * @return Item item à retourner
     */
    public Item getItem(final String pItemName) {
        return this.aItemList.getItem(pItemName);
    }

    /**
     * Retourne les items de la salle en une String
     * 
     * @return String items de la salle
     */
    public String getItemsString() {
        return this.aItemList.getItemString();
    }
} // Room
