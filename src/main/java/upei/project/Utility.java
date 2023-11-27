package upei.project;

import java.util.Arrays;

public class Utility extends BoardSquare {
    private Player owner;
    private int[] rent;
    private int buyPrice;
    public Utility(int iLoc, String name,int[] rent, int buyPrice){
        super(iLoc, name);
        this.buyPrice = buyPrice;
        this.owner = null;
        this.rent = rent;

    }

    @Override
    public void playerOnLocation(Player player) {
        if (getOwner().equals(player)){
            return;
        }
        else if (!getOwner().equals(player) && getOwner() == null){
            if (player.makeChoice()){
                setOwner(player);
                player.lessMoney(getBuyPrice());
            }
        }
        else{
            player.lessMoney(rent[0]);
            getOwner().addMoney(rent[0]);
        }
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setRent(int[] rent) {
        this.rent = rent;
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
    public int getRent(){
        return calcRent();
    }

    /**
     * Determines rent based on TODO
     * @return
     */
    private int calcRent(){
        return 0;
    }

    @Override
    public String toString() {
        return "Utility{" +
                "iLoc=" +
                "owner=" + owner +
                ", rent=" + Arrays.toString(rent) +
                ", buyPrice=" + buyPrice +
                '}';
    }
}
