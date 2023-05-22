import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RoomRandomizer Class
 */
public class RoomRandomizer {
    private List<Room> aRooms;
    private Random aRandom;

    /**
     * Constructor of RoomRandomizer class
     * 
     * @param pRooms The ArrayList containing all GameEngine's Rooms
     */
    public RoomRandomizer() {
        this.aRooms = new ArrayList<Room>();
        this.aRandom = new Random();
    }

    /**
     * Add a Room to the ArrayList
     * 
     * @param pRoom Room to add
     */
    public void addRoom(final Room pRoom) {
        this.aRooms.add(pRoom);
    }

    /**
     * Add a collection of Rooms to the ArrayList
     * 
     * @return
     */
    public void addRooms(final ArrayList<Room> pRooms) {
        this.aRooms.addAll(pRooms);
    }

    /**
     * Get a random room
     * 
     * @return the random Room generated
     */
    public Room getRandomRoom() {
        if (this.aRooms.equals(null)) {
            return null;
        }
        int aRandom = this.aRandom.nextInt(this.aRooms.size());
        return this.aRooms.get(aRandom);
    }
}
