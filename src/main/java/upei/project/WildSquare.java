package upei.project;

public class WildSquare extends BoardSquare {
    /**
     * Constructor that creates a BoardLocation instance of iLoc
     *
     * @param iLoc : location on the board
     * @param name
     */
    public WildSquare(int iLoc, String name) {
        super(iLoc, name);
    }

    @Override
    public void playerOnLocation(Player player) {
        int rand =(int) (Math.random()*10 + 1);
        switch (rand){
            // advance to go
            case 1 -> player.setPos(0);
            //Bank error in your favor. Collect $200.
            case 2 -> player.addMoney(200);
            //Doctor's fees. {fee} Pay $50
            case 3 -> player.lessMoney(50);
            //From sale of stock you get $50
            case 4 -> player.addMoney(50);
            //School fees. Pay $50
            case 5 -> player.setPos(10);
            case 6 -> player.setPos(0);
            //Advance to EGYPT.
            case 7 -> player.setPos(39);
            //Advance to MOROCCO.
            case 8 -> player.setPos(8);
            //Go Back Three {3} Spaces
            case 9 -> player.moveN(-3);
            //Go Back Forward {3} Spaces
            case 10 -> player.moveN(4);

        }
    }
    //TODO
}
