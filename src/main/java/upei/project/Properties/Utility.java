package upei.project.Properties;

import upei.project.MonopolyGame;
import upei.project.Player;

public class Utility extends Property {

    public Utility(int iLoc, String name, int buyPrice){
        super(iLoc, name, buyPrice);
        this.owner = null;
    }
    @Override
    public void playerOnLocation(Player player) {
        if (getOwner() == player){
            return;
        }
        else if (this.getOwner() != player && this.getOwner() == null){
            if (player.makeChoice(this)){
                this.setOwner(player);
                player.subtractMoney(this.getBuyPrice());
            }
        }
        else{
            player.subtractMoney(this.calcRent());
            this.getOwner().addMoney(this.calcRent());
        }
    }
    /**
     * Returns the utility instance rent after determining it.
     * @return rent
     */

    public int calcRent(){
        if(this.getOwner().getLandsOwnedOfType(Utility.class).size() == 1){
            return MonopolyGame.getDiceVal() * 4;
        } else {
            return MonopolyGame.getDiceVal() * 10;
        }
    }

    @Override
    public String toString() {
        return "Utility{" +
                super.toString()
                + "}";
    }
}
