 /**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau + A.Fournier + S.Guillermet-Laouad
 * @version 2008.03.30 + 2019.09.25 + 2023.04.18 + 2023.05.6
 */
public class CommandWords
{
     // a constant array that will hold all valid command words
    private final String[] aValidCommands;
    private static final String[] aValidDirection = {"north", "south", "east", "west", "up", "down"};
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        this.aValidCommands = new String[]{
            "go",
            "help",
            "quit",
            "look",
            "pray",
            "back",
            "take",
            "drop",
            "test",
            "respawn",
            "eat",
            "charge",
            "fire",
            "bless",
        };
    } // CommandWords()
    /**
     * Check whether a given String is a valid direction word.
     * @param pString
     * @return true if a given string is a valid direction, false otherwise.
     */
    public static boolean isDirection(final String pString) {
        for (int vI = 0; vI < aValidDirection.length; vI++) {
            if (aValidDirection[vI].equals(pString)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand( final String pString )
    {
        for ( int vI=0; vI<this.aValidCommands.length; vI++ ) {
            if ( this.aValidCommands[vI].equals( pString ) )
                return true;
        } // for
        // if we get here, the string was not found in the commands :
        return false;
    } // isCommand()

    /**
     * @return a string of all valid commands
     */
    public String[] getCommands() {
        return this.aValidCommands;
    }
    /**
     * @return a string of all valid commands
     */
    public String getCommandList() {
        String vReturn = "";
        for (int vI = 0; vI < this.aValidCommands.length; vI++) {
            vReturn += this.aValidCommands[vI] + " ";
        }
        return vReturn;
    }
} // CommandWords