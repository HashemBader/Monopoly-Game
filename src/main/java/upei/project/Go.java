package upei.project;

public class Go extends BoardSquare{
    /**
     * Constructor that creates a BoardLocation instance of iLoc
     *
     * @param iLoc : location on the board
     * @param name
     */
    public Go(int iLoc, String name) {
        super(iLoc, name);
    }

    @Override
    public void playerOnLocation(Player player) {
        player.addMoney(200);
    }
}
