package upei.project.Properties;

import upei.project.BoardSquare;
import upei.project.Player;

/**
 * Properties have an owner
 * */
public abstract class Property extends BoardSquare {
    protected Player owner;
    protected final int buyPrice;

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
        if(!owner.getLandsOwned().contains(this))
            owner.getLandsOwned().add(this);
    }

    public int getBuyPrice() {
        return this.buyPrice;
    }

    abstract int getRent();

    @Override
    public String toString() {
        return "Property{" +
                "owner=" + owner.getName() +
                ", buyPrice=" + buyPrice +
                "} " + super.toString();
    }
}
