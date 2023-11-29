package upei.project;

public class Jail extends BoardSquare{
    /**
     * Constructor that creates a BoardLocation instance of iLoc
     *
     * @param iLoc : location on the board
     * @param name
     */
    public Jail(int iLoc, String name) {
        super(iLoc, name);
    }

    @Override
    public void playerOnLocation(Player player) {
        player.setPos(10);
        int tries = 0;
        while (tries < 3){
            double hla = (Math.random()*6)+1;
            double hla2 = (Math.random()*6)+1;
            if (hla == hla2){
                player.addMoney(50);
                break;}
            tries++;
        }
        player.lessMoney(50);
        }
    }
