import java.util.ArrayList;

/**
 * Class TransporterRoom
 * @author Alois Fournier 18.05.23
 */
public class TransporterRoom extends Room {

    private RoomRandomizer aRoomRandomizer;

    /**
     * Constructeur de la classe TransporterRoom
     * @param pRoomName
     * @param pImageName
     * @param pIsTrapDoor
     */
    public TransporterRoom(final String pRoomName, final String pImageName, final boolean pIsTrapDoor) {
        super(pRoomName, pImageName, pIsTrapDoor);
        this.aRoomRandomizer = new RoomRandomizer();
    }

    /**
     * Ajoute une collection de Room au randomizer
     * @param pRooms
     */
    public void addRooms(final ArrayList<Room> pRooms) {
        this.aRoomRandomizer.addRooms(pRooms);
    }

    /**
     * Retourne une sortie aléatoire d'après l'ArrayList
     * 
     * @param pDirection Direction to get the exit
     * @return the exit room (random)
     */
    public Room getRandomExit() {
        return this.aRoomRandomizer.getRandomRoom();
    }

}