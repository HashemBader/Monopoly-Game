package upei.project;

public class Taxe extends BoardSquare{


    /**
     * Constructor that creates a BoardLocation instance of iLoc
     *
     * @param iLoc : location on the board
     * @param name
     */
    public Taxe(int iLoc, String name) {
        super(iLoc, name);
    }

    @Override
    public void playerOnLocation(Player player) {
        if (player.getPos() == 4){
            player.lessMoney(200);
        } else if (player.getPos() == 38) {
            player.lessMoney(100);
        }
    }
}
