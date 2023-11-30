package upei.project;

public class Tax extends BoardSquare{


    /**
     * Constructor that creates a BoardLocation instance of iLoc
     *
     * @param iLoc : location on the board
     * @param name
     */
    public Tax(int iLoc, String name) {
        super(iLoc, name);
    }

    @Override
    public void playerOnLocation(Player player) {
        if (player.getPos() == 4){
            player.subtractMoney(200);
        } else if (player.getPos() == 38) {
            player.subtractMoney(100);
        }
    }
}
