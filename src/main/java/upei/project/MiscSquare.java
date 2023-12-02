package upei.project;

public class MiscSquare extends BoardSquare{
    /**
     * Constructor to create a BoardSquare instance with a specified location and name.
     *
     * @param iLoc Location index on the board.
     * @param name Name of the board square.
     */
    public MiscSquare(int iLoc, String name) {
        super(iLoc, name);
    }

    @Override
    public void playerOnLocation(Player player) {
        //Do nothing
    }
}
