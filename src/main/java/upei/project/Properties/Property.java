package upei.project.Properties;

import upei.project.BoardSquare;
import upei.project.Player;

/**
 * The abstract class Property represents properties on the game board.
 * It extends the BoardSquare class and serves as the base class for various property types.
 */
public abstract class Property extends BoardSquare {
    // Fields
    protected Player owner;// the owner of a property
    protected int buyPrice;// the price of a property
    protected int baseRent;// the rent of a property

    /**
     * Constructor to create a Property instance with specified parameters.
     * @param iLoc Location index of the property on the board.
     * @param name Name of the property.
     * @param buyPrice Buying price of the property.
     */
    public Property(int iLoc, String name, int buyPrice){
        super(iLoc, name);
        this.owner = null;
        this.buyPrice = buyPrice;
        baseRent = 0;
    }

    /**
     * Constructor to create a Property instance with specified parameters.
     * @param iLoc Location index of the property on the board.
     * @param name Name of the property.
     * @param buyPrice Buying price of the property.
     * @param baseRent Base rent amount for the property.
     */
    public Property(int iLoc, String name, int buyPrice, int baseRent){
        super(iLoc, name);
        this.owner = null;
        this.buyPrice = buyPrice;
        this.baseRent = baseRent;
    }

    /**
     * Retrieves the owner of the property.
     * @return The Player object representing the owner of the property.
     */
    public Player getOwner() {
        return this.owner;
    }

    /**
     * Sets the owner of the property.
     * @param owner The Player object to be set as the owner of the property.
     */
    public void setOwner(Player owner) {
        this.owner = owner;
        if(!owner.getLandsOwned().contains(this))
            owner.getLandsOwned().add(this);
    }

    /**
     * Retrieves the buying price of the property.
     * @return The buying price of the property.
     */
    public int getBuyPrice() {
        return this.buyPrice;
    }

    /**
     * Retrieves the base rent amount of the property.
     * @return The base rent amount of the property.
     */
    public int getBaseRent(){
        return this.baseRent;
    }

    /**
     * Abstract method to calculate the rent amount for the property.
     * Each specific property type must implement its own rent calculation logic.
     * @return The calculated rent amount.
     */
    abstract int calcRent();

    /**
     * Overrides the toString() method to provide a string representation of the Property object.
     * @return A string representation of the Property object.
     */
    @Override
    public String toString() {
        return "Property{" +
                "owner=" + owner.getName() +
                ", buyPrice=" + buyPrice +
                ", baseRent=" + baseRent +
                "} " + super.toString();
    }
}
