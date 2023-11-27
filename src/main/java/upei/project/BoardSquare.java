package upei.project;

public abstract class BoardSquare {
    private int iLoc;
    private String name;

    /**
     * Abstract method that does something when player lands on a BoardLocation
     * @param player : player object
     */
    public abstract void playerOnLocation(Player player);

    /**
     * Constructor that creates a BoardLocation instance of iLoc
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
