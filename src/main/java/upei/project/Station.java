package upei.project;

public class Station extends Property {
    /**
     * Constructor that creates a BoardLocation instance of iLoc
     *
     * @param iLoc : location on the board
     * @param name
     */
    private int rent;

    private int buyPrice;
    public Station(int iLoc, String name, int buyPrice) {
        super(iLoc, name, buyPrice);
        this.rent = 25;
    }

    @Override
    public void playerOnLocation(Player player) {
        if (this.getOwner() == player){
            return;
        }
        else if (this.getOwner() != player && this.getOwner() == null){ // not owned
            if (player.makeChoice(this.iLoc)){
                this.setOwner(player);
                player.lessMoney(this.getBuyPrice());
            }
        }
        else{ // a player has landed on owners land
            if (getOwner().getStationsOwned().size() == 2) {
                player.lessMoney(2 * getRent());
                getOwner().addMoney(2 * getRent());
            }
            else if (getOwner().getStationsOwned().size() == 3) {
                player.lessMoney(4*getRent());
                getOwner().addMoney(4*getRent());
            }
            else if (getOwner().getStationsOwned().size() == 4) {
                player.lessMoney(8*getRent());
                getOwner().addMoney(8*getRent());
            }
            else {
                player.lessMoney(getRent());
                getOwner().addMoney(getRent());
            }
        }
    }


    public int getRent() {return rent;}

    public void setRent(int rent) {
        this.rent = rent;
    }

}
