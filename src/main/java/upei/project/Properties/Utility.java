package upei.project.Properties;

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
            if (player.getMoney() > this.getBuyPrice() && player.makeChoice(this)){
                this.setOwner(player);
                player.subtractMoney(this.getBuyPrice());
            }
        }
        else{
            player.subtractMoney(this.getRent());
            this.getOwner().addMoney(this.getRent());
        }
    }
    /**
     * Returns the utility instance rent after determining it.
     * @return rent
     */
    @Override
    public int getRent(){
        return Player.getDiceVal()*3;
    }

}
