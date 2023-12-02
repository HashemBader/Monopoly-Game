package upei.project.Properties;

import upei.project.BoardSquare;
import upei.project.Player;

/**
 * Properties have an owner
 * */
public abstract class Property extends BoardSquare {
    protected Player owner;
    protected int buyPrice;
    protected int baseRent;

    public Property(int iLoc, String name, int buyPrice){
        super(iLoc, name);
        this.owner = null;
        this.buyPrice = buyPrice;
        baseRent = 0;
    }
    public Property(int iLoc, String name, int buyPrice, int baseRent){
        super(iLoc, name);
        this.owner = null;
        this.buyPrice = buyPrice;
        this.baseRent = baseRent;
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

    public int getBaseRent(){
        return this.baseRent;
    }
    abstract int calcRent();


    @Override
    public String toString() {
        return "Property{" +
                "owner=" + owner.getName() +
                ", buyPrice=" + buyPrice +
                ", baseRent=" + baseRent +
                "} " + super.toString();
    }
}
