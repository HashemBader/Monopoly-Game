package upei.project;

import java.util.Arrays;

public class Utility extends Property {
    private Player owner;

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
            if (player.makeChoice(this.iLoc)){
                this.setOwner(player);
                player.lessMoney(this.getBuyPrice());
            }
        }
        else{
            player.lessMoney(this.getRent());
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
