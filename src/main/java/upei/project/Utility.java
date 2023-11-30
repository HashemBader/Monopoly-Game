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
        else if (getOwner() != player && getOwner() == null){
            if (player.makeChoice(this.iLoc)){
                setOwner(player);
                player.lessMoney(getBuyPrice());
            }
        }
        else{
            player.lessMoney(this.getRent());
            getOwner().addMoney(this.getRent());
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
