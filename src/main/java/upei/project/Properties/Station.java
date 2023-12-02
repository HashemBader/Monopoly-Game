package upei.project.Properties;

import upei.project.Player;

public class Station extends Property {

    /**
     * Constructor that creates a BoardSquare instance of iLoc
     *
     * @param iLoc : location on the board
     * @param name
     */
    public Station(int iLoc, String name, int buyPrice) {
        super(iLoc, name, buyPrice, 25);
    }

    @Override
    public void playerOnLocation(Player player) {
        if (this.getOwner() == player){
            return;
        }
        else if (this.getOwner() != player && this.getOwner() == null){ // not owned
            if (player.makeChoice(this)){
                this.setOwner(player);
                player.subtractMoney(this.getBuyPrice());
            }
        }
        else{ // a player has landed on owners land
            player.subtractMoney(this.calcRent());
            this.getOwner().addMoney(this.calcRent());
        }
    }

    public int calcRent() {
        return (int) (Math.pow(2, this.getOwner().getLandsOwnedOfType(Station.class).size()-1)) * this.baseRent;
    }

    @Override
    public String toString() {
        return "Station{" +
                "} " + super.toString();
    }
}
