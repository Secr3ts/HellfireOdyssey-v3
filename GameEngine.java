import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe GameEngine - le moteur du jeu d'aventure Zuul.
 * 
 * @author Aloïs Fournier with the help of Sofyan Guillermet-Laouad
 */
public class GameEngine {
    private Parser aParser;
    private UserInterface aGui;
    private Player aPlayer;
    private HashMap<String, Room> aRooms;
    private Room spawnRoom;
    private boolean isTesting;
    private String fakeRandomString;

    /**
     * Crée le jeu et initialise les salles.
     */
    public GameEngine() {
        this.aParser = new Parser();
        this.createRooms();
        this.isTesting = false;
    }

    public void setGUI(final UserInterface pGUI) {
        this.aGui = pGUI;
        this.printWelcome();
    }

    /**
     * Crée toutes les salles et les relie entre elles.
     */
    private void createRooms() {

        // Create Initial 9 circles of Hell

        Room vLimbo = new Room("Limbo, the First Circle of Hell, Residence of all Pagans", "Images/limbo.jpeg");
        Room vLust = new Room("Lust, Second Circle of Hell, Residence of all the Lustful", "Images/lust.jpeg");
        Room vGluttony = new Room("Gluttony, Third Circle of Hell, Residence of all the Gluttonous",
                "Images/gluttony.jpeg");
        Room vAvarice = new Room("Avarice, Fourth Circle of Hell, Residence of all the Avaricious",
                "Images/avarice.jpeg");
        Room vWrath = new Room("Wrath, Fifth Circle of Hell, Residence of all the Wrathful", "Images/wrath.jpeg");
        Room vHeresy = new Room("Heresy, Sixth Circle of Hell, Residence of all the Heretics", "Images/heresy.jpeg");
        Room vViolence = new Room("Violence, Seventh Circle of Hell, Residence of all the Violent",
                "Images/violence.png");

        // vLimboCytadel has been moved to a TransporterRoom

        TransporterRoom vLimboCitadel = new TransporterRoom("Limbo Citadel, the Residence of the Virtuous Pagans",
                "Images/limbo_citadel.jpeg", false);

        Room vTreachery = new Room("Treachery, Ninth Circle of Hell, Residence of all the Treacherous",
                "Images/treachery.jpeg");
        Room vHeaven = new Room("Heaven, your home.", "Images/paradise.jpeg");
        // Create additional rooms

        Room vCharonsFerry = new Room("Charon\'s Ferry", "Images/charron_s_ferry.jpeg");
        Room vFraud = new Room("Fraud, Eighth Circle of Hell, Jail of all the Fraudulent",
                "Images/fraud.jpeg");

        Room vMarrakech = new Room("Marrakech, the City of the Lustfuls", "Images/marrakech.jpeg");

        Room vWheelOfFortune = new Room("The Wheel of Fortune, Where the fate of the earth is being decided",
                "Images/wheel_of_fortune.jpeg");

        Room vRiverStyx = new Room("The River Styx, Eternal battlefield of the wrathful and sullen",
                "Images/river_styx.jpeg");

        Room vCityOfDis = new Room("The City of Dis, Jail of all heretics and pagans", "Images/city_of_dis.jpeg");

        Room vPhlegethon = new Room("Phlegethon, blood river", "Images/lake_phlegethon.jpeg");
        Room vBurningSands = new Room("The Burning Sands, cursed home of the blasphemers", "Images/burning_sands.jpeg");

        Room vMalebolge = new Room("The City of Malebolge, residence of the bolges", "Images/malebolge_city.jpeg");

        Room vLakeCocytus = new Room("The Lake of Cocytus, frozen lake that imprisons Lucifer",
                "Images/lake_cocytus.jpeg");

        // Create array of rooms

        // Initialise room exits

        vLimbo.setExit("south", vCharonsFerry);

        vCharonsFerry.setExit("east", vLimboCitadel);
        vCharonsFerry.setExit("down", vLust);

        vLust.setExit("south", vMarrakech);

        vMarrakech.setExit("down", vGluttony);

        vGluttony.setExit("down", vAvarice);

        vAvarice.setExit("south", vWheelOfFortune);

        vWheelOfFortune.setExit("down", vWrath);

        vWrath.setExit("south", vRiverStyx);

        vRiverStyx.setExit("down", vHeresy);

        vHeresy.setExit("south", vCityOfDis);

        vCityOfDis.setExit("down", vViolence);

        vViolence.setExit("east", vPhlegethon);
        vViolence.setExit("west", vBurningSands);

        vPhlegethon.setExit("down", vFraud);

        vBurningSands.setExit("down", vFraud);

        vFraud.setExit("south", vMalebolge);

        vMalebolge.setExit("down", vTreachery);

        vTreachery.setExit("south", vLakeCocytus);

        vLakeCocytus.setExit("up", vHeaven);
        vLakeCocytus.setExit("down", vLimbo);


        Item vLordBlessing = new Item("Lord\'s-blessing", "holy relic", 10, 0);
        Item vPriestRobe = new Item("Priest\'s-robe", "holy relic", 5, 2);
        Item vDeathScythe = new Item("Death\'s-scythe", "unholy weapon to bring death upon mankind", -5, 6);
        Item vIvoryCross = new Item("Ivory-cross", "Holy cross from the holy crusades", 7, 1);
        Item vHolyWater = new Item("Holy-water", "Flask of water from Jerusalem", 2, 1);
        Item vHellBible = new Item("Hell\'s-bible", "Bible from the devil himself, contains evil but useful spells", -5,
                3);
        Item vBible = new Item("Bible", "Holy book from the Lord", 2, 1);
        Item vHolyGrail = new Item("Holy-grail", "Holy relic from the holy crusades", 10, 2);
        Item vPurseOfCoins = new Item("Purse-of-coins", "The last 30 coins from the purse of Judas", -10, 1);
        Item vSplinterOfCharon = new Item("Splinter-of-Charon", "Splinter of the boat of the underworld", 1, 3);
        Item vViolentFire = new Item("Violent-fire", "Fire from the violent, could be useful", -1, 1);
        Item vBloodFlask = new Item("Blood-flask", "Flask of blood from Phlegethon, the river of blood", -1, 2);
        Item vMercyOfVirgil = new Item("Mercy-of-Virgil", "Mercy of the poet Virgil, the most respected pagan of hell",
                5, 0);
        Item vEyesOfTheGorgon = new Item("Eyes-of-the-Gorgon", "Eyes of the Gorgon, the most feared monster of hell",
                -3, 3);
        Item vHeadOfLucifer = new Item("Head-of-Lucifer", "Head of Lucifer, the most feared demon of hell", 0, 9);

        //The Lost Souls

        Item vChasteSoul = new Item("Chaste-Soul", "A soul that has never comitted the sin of luxury.", 0, 0);
        Item vGenerousSoul = new Item("Generous-Soul", "A soul that has never comitted the sin of avarice.", 0, 0);
        Item vSoftSoul = new Item("Soft-Soul", "A soul that has never comitted the sin of wrath.", 0, 0);
        Item vOrthodoxSoul = new Item("Orthodox-Soul", "A soul that has never comitted the sin of heresy.", 0, 0);
        Item vTemperateSoul = new Item("Temperate-Soul", "A soul that has never comitted the sin of violence.", 0, 0);
        Item vJustSoul = new Item("Just-Soul", "A soul that has never comitted the sin of fraud.", 0, 0);
        Item vHonestSoul = new Item("Honest-Soul", "A soul that has never comitted the sin of treachery.", 0, 0);

        //Special Items

        Item vMagicCookie = new Item("magic-cookie", "A magic cookie that will give you double inventory capacity", 0,
                0);

        Beamer vBeamer = new Beamer("beamer", "A beamer that will teleport you to the last room you beamed from", 20,10);

        // Add Items to rooms
        vLimbo.addItem(vLordBlessing.getName(), vLordBlessing);
        vLimbo.addItem(vDeathScythe.getName(), vDeathScythe);

        vLimboCitadel.addItem(vIvoryCross.getName(), vIvoryCross);

        vLust.addItem(vPriestRobe.getName(), vPriestRobe);

        vMarrakech.addItem(vHolyWater.getName(), vHolyWater);
        vMarrakech.addItem(vMagicCookie.getName(), vMagicCookie);
        vMarrakech.addItem(vChasteSoul.getName(), vChasteSoul);

        vAvarice.addItem(vPurseOfCoins.getName(), vPurseOfCoins);
        vAvarice.addItem(vHolyGrail.getName(), vHolyGrail);

        vWheelOfFortune.addItem(vGenerousSoul.getName(), vGenerousSoul);

        vWrath.addItem(vSplinterOfCharon.getName(), vSplinterOfCharon);
        vRiverStyx.addItem(vSoftSoul.getName(), vSoftSoul);

        vHeresy.addItem(vHellBible.getName(), vHellBible);
        vHeresy.addItem(vBible.getName(), vBible);

        vCityOfDis.addItem(vOrthodoxSoul.getName(), vOrthodoxSoul);

        vViolence.addItem(vViolentFire.getName(), vViolentFire);
        vViolence.addItem(vEyesOfTheGorgon.getName(), vEyesOfTheGorgon);

        vPhlegethon.addItem(vBloodFlask.getName(), vBloodFlask);

        vBurningSands.addItem(vTemperateSoul.getName(), vTemperateSoul);

        vFraud.addItem(vJustSoul.getName(), vJustSoul);

        vMalebolge.addItem(vMercyOfVirgil.getName(), vMercyOfVirgil);

        vTreachery.addItem(vHonestSoul.getName(), vHonestSoul);

        vLakeCocytus.addItem(vHeadOfLucifer.getName(), vHeadOfLucifer);

        vLimbo.addItem(vBeamer.getName(), vBeamer);

        // Add all Rooms to the HashMap
        this.aRooms = new HashMap<String, Room>();
        this.aRooms.put("Limbo", vLimbo);
        this.aRooms.put("LimboCitadel", vLimboCitadel);
        this.aRooms.put("Lust", vLust);
        this.aRooms.put("Marrakech", vMarrakech);
        this.aRooms.put("Avarice", vAvarice);
        this.aRooms.put("WheelOfFortune", vWheelOfFortune);
        this.aRooms.put("Wrath", vWrath);
        this.aRooms.put("Heresy", vHeresy);
        this.aRooms.put("Violence", vViolence);
        this.aRooms.put("Phlegethon", vPhlegethon);
        this.aRooms.put("BurningSands", vBurningSands);
        this.aRooms.put("Fraud", vFraud);
        this.aRooms.put("Malebolge", vMalebolge);
        this.aRooms.put("Treachery", vTreachery);
        this.aRooms.put("LakeCocytus", vLakeCocytus);
        this.aRooms.put("Heaven", vHeaven);

        ArrayList<Room> aRoomArray = new ArrayList<Room>(this.aRooms.values());

        vLimboCitadel.addRooms(aRoomArray);

        // Initial Room
        this.aPlayer = new Player("Gr4ve", vLimbo);

        // Spawn room
        this.spawnRoom = vLimbo;
    }

    /**
     * Change de salle
     * 
     * @param pCommand Commande de changement de salle
     */
    private void goRoom(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Go Where ?");
            return;
        }

        if (!CommandWords.isDirection(pCommand.getSecondWord())) {
            this.aGui.println("Unknown direction !");
            return;
        }

        String vDirection = pCommand.getSecondWord();

        if (this.aPlayer.getCurrentRoom().getExit(vDirection) == null) {
            this.aGui.println("There is no door !");
            return;
        }

        if (this.aPlayer.getCurrentRoom().getClass().equals(TransporterRoom.class)) {
            if (this.isTesting) {
                this.aPlayer.setCurrentRoom(this.aRooms.get(this.fakeRandomString));
            }
            TransporterRoom vTransporterRoom = (TransporterRoom) this.aPlayer.getCurrentRoom();
            this.aPlayer.setCurrentRoom(vTransporterRoom.getRandomExit());
            // On vide la liste des salles précédentes
            this.aPlayer.getPreviousRooms().clear();
            return;
        }

        this.aPlayer.goRoom(vDirection);
        this.printLocationInfo();
    }

    /**
     * Affiche les informations de la salle courante
     */
    private void printLocationInfo() {
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        this.aGui.println(this.aPlayer.getCurrentRoom().getItemsString());
        if (this.aPlayer.getCurrentRoom().getImageName() != null) {
            this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
        }
    }

    /**
     * Message de bienvenue du jeu
     */
    private void printWelcome() {
        this.aGui.println("You were sent in Hell by God to save 7 innocent lost souls.\n You can\'t stay here for too long, otherwise you will be corrupted, so be quick! \nType \'help\' if you need help.");
        this.printLocationInfo();
    }

    /**
     * Affiche l'aide du jeu
     */
    private void printHelp() {
        this.aGui.println("You were sent in Hell by God to save 7 innocent lost souls.\n You can\'t stay here for too long, otherwise you will be corrupted, so be quick! \nType \'help\' if you need help.");
        this.aGui.println(this.aParser.getCommandString());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param pCommandLine The command to be processed.
     */
    public void interpretCommand(final String pCommandLine) {
        this.aGui.println("> " + pCommandLine);
        Command vCommand = this.aParser.getCommand(pCommandLine);

        if (vCommand.isUnknown()) {
            this.aGui.println("I don't know what you mean...");
            return;
        }

        String vCommandWord = vCommand.getCommandWord();
        vCommandWord = vCommandWord.toLowerCase();
        switch (vCommandWord) {
            case "help":
                this.printHelp();
                break;
            case "look":
                this.look();
                break;
            case "pray":
                this.pray();
                break;
            case "go":
                this.goRoom(vCommand);
                break;
            case "back":
                this.back(vCommand);
                break;
            case "take":
                this.take(vCommand);
                break;
            case "bless":
                this.bless(vCommand);
                break;
            case "drop":
                this.drop(vCommand);
                break;
            case "quit":
                if (vCommand.hasSecondWord())
                    this.aGui.println("Quit what?");
                else
                    this.endGame();
                break;
            case "items":
                this.printItems();
                break;
            case "eat":
                this.eat(vCommand);
                break;
            case "test":
                this.test(vCommand);
                break;
            case "respawn":
                this.respawn();
                break;
            case "charge":
                this.charge(vCommand);
                break;
            case "fire":
                this.fire(vCommand);
                break;
            case "alea":
                this.alea(vCommand);
                break;
            default:
                this.aGui.println("Command not implemented yet");
                break;
        }

        this.runMechanics();
    }//interpretCommand()

    private void alea(Command vCommand) {
        if (!vCommand.hasSecondWord()) {
            this.aGui.println("Alea what ?");
            return;
        }

        if (!this.isTesting) {
            this.aGui.println("You can't use this command if you are not in test mode !");
            return;
        }

        if (!this.fakeRandomString.isEmpty() || this.fakeRandomString.equals(null)) {
            this.aGui.println("Clearing previous alea...");
            this.fakeRandomString = "";
            return;
        }

        String vCommandWord = vCommand.getSecondWord();
        this.fakeRandomString = vCommandWord;

    }

    /**
     * Charge the beamer
     * 
     * @param pCommand
     */
    public void charge(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Charge what ?");
            return;
        }

        String vItemName = pCommand.getSecondWord();

        if (!this.aPlayer.hasItem(vItemName)) {
            this.aGui.println("You don't have " + vItemName + " in your inventory.");
            return;
        }

        if (!(this.aPlayer.getItem(vItemName) instanceof Beamer)) {
            this.aGui.println("You can't charge " + vItemName + " !");
            return;
        }
        Item vItem = this.aPlayer.getItem(vItemName);
        Beamer vBeamer = (Beamer) vItem;
        if (vBeamer.isCharged()) {
            this.aGui.println("The beamer is already charged !");
            return;
        }

        this.aPlayer.charge(this.aPlayer.getCurrentRoom(), vBeamer);
        this.aGui.println("The beamer is charged !");
        // TODO: DISABLE CHARGE AND ENABLE FIRE BUTTON?

    }

    /**
     * Fire the beamer
     * 
     * @param pCommand
     */
    public void fire(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Fire what ?");
            return;
        }

        String vItemName = pCommand.getSecondWord();

        if (!this.aPlayer.hasItem(vItemName)) {
            this.aGui.println("You don't have " + vItemName + " in your inventory.");
            return;
        }

        if (!(this.aPlayer.getItem(vItemName) instanceof Beamer)) {
            this.aGui.println("You can't fire " + vItemName + " !");
            return;
        }

        Beamer vBeamer = (Beamer) this.aPlayer.getItem(vItemName);
        if (!vBeamer.isCharged()) {
            this.aGui.println("The beamer is not charged !");
            return;
        }

        this.aPlayer.fire(vBeamer);
        this.aGui.println("The beamer is fired !");
    }

    /**
     * Respawn the player
     */
    public void respawn() {
        this.aPlayer.setCurrentRoom(this.spawnRoom);
        this.printLocationInfo();
        this.aGui.println("You respawned");
    }

    /**
     * Mechanics of the game
     */
    private void runMechanics() {
        boolean vReachedHeaven = this.aPlayer.getCurrentRoom().getDescription().contains("Heaven");
        boolean vSoulSaved = this.aPlayer.hasItem("Chaste-Soul") 
                && this.aPlayer.hasItem("Generous-Soul")
                && this.aPlayer.hasItem("Soft-Soul") 
                && this.aPlayer.hasItem("Orthodox-Soul")
                && this.aPlayer.hasItem("Temperate-Soul") 
                && this.aPlayer.hasItem("Just-Soul")
                && this.aPlayer.hasItem("Honest-Soul");
        
        this.winloseConditions(vReachedHeaven, vSoulSaved);

    }

    /**
     * Eat an item
     * 
     * @param pCommand
     */
    private void eat(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Eat What ?");
            return;
        }

        String vItemName = pCommand.getSecondWord();

        if (!this.aPlayer.hasItem(vItemName)) {
            this.aGui.println("You don't have " + vItemName + " in your inventory.");
            return;
        }

        // Item vItem = this.aPlayer.getCurrentRoom().getItem(vItemName);

        this.aPlayer.eat(vItemName);
        if (vItemName.equals("magiccookie")) {
            this.aGui.println("You feel a strange power in you.");
            this.aPlayer.setMaxWeight(this.aPlayer.getMaxWeight() * 2);
        }
        this.aGui.println("You eat " + vItemName + ".");
    }

    /**
     * Print the items in the player's inventory
     */
    private void printItems() {
        this.aGui.println(this.aPlayer.getInventoryString());
    }

    /**
     * Drop an item
     * 
     * @param pCommand
     */
    private void drop(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Drop What ?");
            return;
        }

        String vItemName = pCommand.getSecondWord();

        if (!this.aPlayer.hasItem(vItemName)) {
            this.aGui.println("You don't have " + vItemName + " in your inventory.");
            return;
        }

        this.aPlayer.drop(vItemName);
    }

    /**
     * Prends un objet
     */
    private void take(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Take What ?");
            return;
        }

        String vItemName = pCommand.getSecondWord();

        if (!this.aPlayer.getCurrentRoom().hasItem(vItemName)) {
            this.aGui.println("There is no " + vItemName + " in this room.");
            return;
        }

        if(this.aPlayer.getCurrentRoom().getItem(vItemName).getName().contains("Soul")){
            this.aGui.println("You can't just take a soul with your hand!");
            return;
        }

        if (!this.aPlayer.canCarry(this.aPlayer.getCurrentRoom().getItem(vItemName).getWeight())) {
            this.aGui.println("You can't carry " + vItemName + " because it's too heavy. Drop some Items first.");
            return;
        }

        this.aPlayer.take(vItemName);

    }

    /**
     * Bénissez une âme pour la prendre avec vous
     */
    private void bless(final Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Which lost soul shall be blessed?");
            return;
        }

        String vItemName = pCommand.getSecondWord();

        if (!this.aPlayer.getCurrentRoom().hasItem(vItemName)) {
            this.aGui.println("The " + vItemName + " is not in this place.");
            return;
        }

        if (!this.aPlayer.getCurrentRoom().getItem(vItemName).getName().contains("Soul")) {
            this.aGui.println("You can't bless " + vItemName + " because it's not a lost soul.");
            return;
        }

        this.aPlayer.bless(vItemName);
        this.aGui.println("You blessed the " + vItemName + " and took it with you.");

    }

    private void look() {
        this.printLocationInfo();
    }

    private void pray() {
        this.aGui.println("You pray to the gods in hope that they will help you escape this place.");
        // Add hints
        // Taint soul (?)
    }

    private void back(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            this.aGui.println("Back what ?");
            return;
        }

        if (this.aPlayer.getPreviousRooms().empty()) {
            this.aGui.println("You can't go back.");
            return;
        }

        if (!this.aPlayer.getPreviousRooms().peek().isExit()) {
            this.aGui.println("You can't go back. It\'s a trap !");
            return;
        }

        this.aGui.println("You go back to the previous room.");
        this.aPlayer.back();
        this.printLocationInfo();
    }

    private void endGame() {
        this.aGui.println("Thank you for playing.  Goodbye.");
        this.aGui.enable(false);
    }

    private void test(final Command pCommand) {
        this.isTesting = true;
        this.alea(new Command("alea", "Malebolge"));
        if (!pCommand.hasSecondWord()) { // Verification de la présence d'un second mot
            this.aGui.println("What do you want to test ?");
            return;
        }

        String vFileToScan = pCommand.getSecondWord();
        try {
            Scanner vScanner = new Scanner(new File("" + vFileToScan + ".txt"));
            this.aGui.println("Testing " + vFileToScan);
            while (vScanner.hasNextLine()) {
                this.interpretCommand(vScanner.nextLine());
            }
        } catch (final FileNotFoundException pErr) {
            this.aGui.println("Error, FNF " + pErr.toString());
        }
        this.alea(new Command("alea", ""));
        this.isTesting = false;
    }

    /**
     * Vérifie si les conditions de victoire ou de défaite sont atteintes
     * 
     * @param pReachedHeaven Vérifie si le joueur est dans la room Heaven
     * @param pSoulSaved Vérifie si le joueur a sauvé toutes les âmes
     */
    private void winloseConditions(final boolean pReachedHeaven, final boolean pSoulSaved) {
            if (pReachedHeaven && pSoulSaved){
                this.aGui.println("You saved all the lost souls and got back to Heaven without getting corrupted. \n You won the game !");
                this.aGui.showImage("Images/WinImage.png");
                this.endGame();
            }

            if(this.aPlayer.getMaxMoves() == 0){
                this.aGui.println("You stayed too long in Hell and got corrupted. \n You lost the game !");
                this.aGui.showImage("Images/FallenAngel.png");
                this.endGame();
            }
            if(pReachedHeaven && !pSoulSaved){
                this.aGui.println("You got back to Heaven without saving all the lost souls. God sent you in Hell forever to punish you. \n You lost the game !");
                this.aGui.showImage("Images/FallenAngel.png");
                this.endGame();
            }
    }//winloseConditions()

} // GameEngine
