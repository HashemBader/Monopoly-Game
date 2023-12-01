package upei.project;
/**
 * The abstract class BoardSquare represents a square on the game board.
 * It defines behavior when a player lands on a particular square.
 */
public abstract class BoardSquare {
    protected final int ILOC;// Location index on the board
    protected final String NAME; // Name of the board square

    /**
     * Constructor to create a BoardSquare instance with a specified location and name.
     * @param iLoc Location index on the board.
     * @param name Name of the board square.
     */
    public BoardSquare(int iLoc, String name){
        this.ILOC = iLoc;
        this.NAME = name;
    }

    /**
     * Abstract method defining the action when a player lands on a BoardSquare.
     * @param player The player object representing the player who landed on the square.
     */
    public abstract void playerOnLocation(Player player);

    /**
     * Provides a string representation of the BoardSquare.
     * @return A string displaying the location index and name of the BoardSquare.
     */
    @Override
    public String toString() {
        return "BoardLocation{" +
                "iLoc=" + ILOC +
                ", name='" + NAME + '\'' +
                '}';
    }
}
