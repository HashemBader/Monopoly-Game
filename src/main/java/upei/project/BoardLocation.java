package upei.project;

public abstract class BoardLocation {
    // index location in the board
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
    public BoardLocation(int iLoc, String name){
        this.iLoc = iLoc;
        this.name = name;
    }
}
