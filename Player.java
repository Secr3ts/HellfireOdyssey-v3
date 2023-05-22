import java.util.Random;
import java.util.Stack;

/**
 * Classe Player - un joueur du jeu d'aventure Zuul.
 * 
 * @author Aloïs Fournier with the help of Sofyan Guillermet-Laouad
 */
public class Player {
    private String aName;
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms;
    private ItemList aInventory;
    private int aMaxWeight;
    private int aCurrentWeight;
    private int aMaxMoves;

    /**
     * Constructeur de la classe Player
     * 
     * @param pName        nom du joueur
     * @param pCurrentRoom salle actuelle du joueur
     */
    public Player(final String pName, final Room pCurrentRoom) {
        this.aCurrentRoom = pCurrentRoom;
        this.aName = pName;
        this.aPreviousRooms = new Stack<Room>();
        this.aInventory = new ItemList();
        this.aMaxWeight = new Random().nextInt(20 + 10) + 10;
        this.aCurrentWeight = 0;
        this.aMaxMoves = 39;

    }

    // Commands

    /**
     * Mange un item de l'inventaire
     * 
     * @param pItemName nom de l'item à manger
     */
    public void eat(final String pItemName) {
        this.aCurrentWeight -= this.aInventory.getItem(pItemName).getWeight();
        this.aInventory.removeItem(pItemName);
    }

    /**
     * Déplace le joueur dans la salle indiquée
     * 
     * @param pNextRoom nom de la salle suivante
     */
    public void goRoom(final String pNextRoom) {
        this.aPreviousRooms.push(this.aCurrentRoom);
        Room vNextRoom = this.aCurrentRoom.getExit(pNextRoom);
        this.aCurrentRoom = vNextRoom;
        this.aMaxMoves -= 1;
    }

    /**
     * Retourne le joueur dans la salle précédente
     */
    public void back() {
        this.aCurrentRoom = this.aPreviousRooms.pop();
        this.aMaxMoves -= 1;
    }

    /**
     * Ajoute un item à l'inventaire du joueur
     * 
     * @param pItemName nom de l'item à take
     */
    public void take(final String pItemName) {
        this.aInventory.addItem(this.aCurrentRoom.getItem(pItemName));
        this.aCurrentRoom.removeItem(pItemName);
        this.aCurrentWeight += this.aInventory.getItem(pItemName).getWeight();
    }

    /**
     * Ajoute une âme à l'inventaire du joueur(commande take spécifique pour les items représentants les âmes)
     * 
     * @param pItemName nom de l'âme devant être ajoutée à l'inventaire
     */
    public void bless(final String pItemName) {
            this.aInventory.addItem(this.aCurrentRoom.getItem(pItemName));
            this.aCurrentRoom.removeItem(pItemName);
            this.aCurrentWeight += this.aInventory.getItem(pItemName).getWeight();
    }

    /**
     * Retire un item de l'inventaire du joueur
     * 
     * @param pItemName nom de l'item à drop
     */
    public void drop(final String pItemName) {
        this.aCurrentRoom.addItem(this.aInventory.getItem(pItemName).getName(), this.aInventory.getItem(pItemName));
        ;
        this.aInventory.removeItem(pItemName);
        this.aCurrentWeight -= this.aInventory.getItem(pItemName).getWeight();
    }

    // Getters
    /**
     * Retourne le nom du joueur
     * 
     * @return String nom du joueur
     */
    public String getName() {
        return this.aName;
    }

    /**
     * Retourne la salle actuelle du joueur
     * 
     * @return Room salle actuelle du joueur
     */
    public Room getCurrentRoom() {
        return this.aCurrentRoom;
    }

    /**
     * Retourne la pile des salles précédentes
     * 
     * @return pile des salles précédentes
     */
    public Stack<Room> getPreviousRooms() {
        return this.aPreviousRooms;
    }

    /**
     * Retourne l'inventaire du joueur
     * 
     * @return ItemList inventaire du joueur
     */
    public ItemList getInventory() {
        return this.aInventory;
    }

    /**
     * Retourne true si l'item est dans l'inventaire du joueur
     * 
     * @param pItemName nom de l'item
     * @return boolean true si l'item est dans l'inventaire du joueur
     */
    public boolean hasItem(final String pItemName) {
        if (this.aInventory.hasItem(pItemName)==true){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Charge le Beamer
     * 
     * @param Room
     */
    public void charge(final Room pRoom, final Beamer pBeamer) {
        pBeamer.charge(pRoom);
    }

    /**
     * Tire le Beamer
     * 
     * @param Beamer
     */
    public void fire(Beamer vBeamer) {
        this.aPreviousRooms.push(this.aCurrentRoom);
        this.aCurrentRoom = vBeamer.getSavedRoom();
        vBeamer.uncharge();
    }

    /**
     * Retourne le poids maximal que le joueur peut porter
     * 
     * @return int poids maximal que le joueur peut porter
     */
    public int getMaxWeight() {
        return this.aMaxWeight;
    }

    /**
     * Retourne le poids actuel de l'inventaire du joueur
     * 
     * @return int poids actuel de l'inventaire du joueur
     */
    public int getCurrentWeight() {
        return this.aCurrentWeight;
    }

    /**
     * Retourne true si le joueur peut porter l'item
     * 
     * @param pItemWeight
     * @return boolean true si le joueur peut porter l'item
     */
    public boolean canCarry(final int pItemWeight) {
        if (this.getCurrentWeight() + pItemWeight <= this.aMaxWeight) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retourne la String de l'inventaire du joueur
     * 
     * @return String de l'inventaire du joueur
     */
    public String getInventoryString() {
        return this.aInventory.getItemString();
    }

    // Setters
    /**
     * Définit la salle actuelle du joueur
     * 
     * @param pCurrentRoom salle actuelle du joueur
     */
    public void setCurrentRoom(final Room pCurrentRoom) {
        this.aCurrentRoom = pCurrentRoom;
    }

    public void setMaxWeight(final int pMaxWeight) {
        this.aMaxWeight = pMaxWeight;
    }

    /**
     * Retire un item de l'inventaire
     * @param pItemName nom de l'item à retirer
     */
    public void removeItem(final String pItemName) {
        this.aInventory.removeItem(pItemName);
    }

    /**
     * Retourne un item de l'inventaire
     * 
     * @param pItemName name of the item
     */
    public Item getItem(final String pItemName) {
        return this.aInventory.getItem(pItemName);
    }

    /**
     * Accesseur du nombre de mouvements restants
     * 
     * @return Le nombre de mouvements restants
     */
    public int getMaxMoves(){
        return this.aMaxMoves;
    }//getMaxMoves

}
