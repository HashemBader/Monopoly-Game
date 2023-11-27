package upei.project;

public class Station extends BoardSquare{
    /**
     * Constructor that creates a BoardLocation instance of iLoc
     *
     * @param iLoc : location on the board
     * @param name
     */
    public Station(int iLoc, String name) {
        super(iLoc, name);
    }

    @Override
    public void playerOnLocation(Player player) {

    }
}
