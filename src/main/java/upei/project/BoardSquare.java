package upei.project;

public abstract class BoardSquare {
    protected int iLoc;
    protected String name;

    /**
     * Abstract method that does something when player lands on a BoardSquare
     * @param player : player object
     */
    public abstract void playerOnLocation(Player player);

    /**
     * Constructor that creates a BoardSquare instance
     * @param iLoc : location on the board
     */
    public BoardSquare(int iLoc, String name){
        this.iLoc = iLoc;
        this.name = name;
    }

    @Override
    public String toString() {
        return "BoardLocation{" +
                "iLoc=" + iLoc +
                ", name='" + name + '\'' +
                '}';
    }
}
