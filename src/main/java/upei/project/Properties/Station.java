package upei.project.Properties;

import upei.project.Player;

public class Station extends Property {
    /**
     * Constructor that creates a BoardLocation instance of iLoc
     *
     * @param iLoc : location on the board
     * @param name
     */
    private int rent;
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
            if (player.getMoney() > this.getBuyPrice() && player.makeChoice(this)){
                this.setOwner(player);
                player.subtractMoney(this.getBuyPrice());
            }
        }
        else{ // a player has landed on owners land
            player.subtractMoney(this.getRent());
            this.getOwner().addMoney(this.getRent());
        }
    }


    public int getRent() {return calcRent();}

    public int calcRent() {
        return (int) (Math.pow(2, this.getOwner().getStationsOwned().size()-1)) * this.rent;
    }

}
