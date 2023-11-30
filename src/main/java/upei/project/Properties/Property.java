package upei.project.Properties;

import upei.project.BoardSquare;
import upei.project.Player;

/**
 * Properties have an owner
 * */
public abstract class Property extends BoardSquare {
    protected Player owner;
    protected final int buyPrice;

    private boolean isMortgaged;
    public Property(int iLoc, String name, int buyPrice){
        super(iLoc, name);
        this.owner = null;
        this.buyPrice = buyPrice;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        owner.getLandsOwned().add(this);
    }

    public int getBuyPrice() {
        return this.buyPrice;
    }

    abstract int getRent();
}
