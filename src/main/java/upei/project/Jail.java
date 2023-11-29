package upei.project;

public class Jail extends BoardSquare{
    /**
     * Constructor that creates a BoardSquare instance
     *
     * @param iLoc : location on the board
     * @param name
     */
    public Jail(int iLoc, String name) {
        super(iLoc, name);
    }

    @Override
    public void playerOnLocation(Player player) {
        player.setPos(10); //goto jail
        player.lessMoney(50); // subtract money for now
        final int MAX_TRIES = 3;
        int tries = 0;
        int dice1;
        int dice2;
        while (tries < MAX_TRIES) {
            dice1 = Dice.rollDice1();
            dice2 = Dice.rollDice1();
            if (dice1 == dice2) {
                player.addMoney(50); // give the money back
                break;
            }
            tries++;
        }
    }
}
