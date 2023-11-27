package upei.project;

import java.util.Arrays;

public class Utility extends Property {
    private Player owner;
    private int buyPrice;
    public Utility(int iLoc, String name, int buyPrice){
        super(iLoc, name);
        this.buyPrice = buyPrice;
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
            player.lessMoney(this.getRent(Driver.getDiceVal()));
            getOwner().addMoney(this.getRent(Driver.getDiceVal()));
        }
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }


    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    /**
     * Returns the utility instance rent after determining it.
     * @return rent
     */
    public int getRent(int diceVal){
        return calcRent(diceVal);
    }

    /**
     * Determines rent based on TODO
     * @return
     */
    private int calcRent(int diceVal){
        return diceVal*3; // might delete later
    }

}
