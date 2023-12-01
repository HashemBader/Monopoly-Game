package upei.project.Properties;

import upei.project.BoardSquare;
import upei.project.Player;

/**
 * Properties have an owner
 * */
public abstract class Property extends BoardSquare {
    protected Player owner;
    protected final int BUYPRICE;

    public Property(int iLoc, String name, int BUYPRICE){
        super(iLoc, name);
        this.owner = null;
        this.BUYPRICE = BUYPRICE;
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
        return this.BUYPRICE;
    }

    abstract int getRent();

    @Override
    public String toString() {
        return "Property{" +
                "owner=" + owner.getName() +
                ", buyPrice=" + BUYPRICE +
                "} " + super.toString();
    }
}
