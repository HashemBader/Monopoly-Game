package upei.project;
/**
 * The abstract class BoardSquare represents a square on the game board.
 * It defines behavior when a player lands on a particular square.
 */
public abstract class BoardSquare {
    protected int iLoc;// Location index on the board
    protected String name; // Name of the board square


    /**
     * Abstract method defining the action when a player lands on a BoardSquare.
     * @param player The player object representing the player who landed on the square.
     */
    public abstract void playerOnLocation(Player player);

    /**
     * Constructor to create a BoardSquare instance with a specified location and name.
     * @param iLoc Location index on the board.
     * @param name Name of the board square.
     */
    public BoardSquare(int iLoc, String name){
        this.iLoc = iLoc;
        this.name = name;
    }
    /**
     * Provides a string representation of the BoardSquare.
     * @return A string displaying the location index and name of the BoardSquare.
     */
    @Override
    public String toString() {
        return "BoardLocation{" +
                "iLoc=" + iLoc +
                ", name='" + name + '\'' +
                '}';
    }
}
