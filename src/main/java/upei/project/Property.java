package upei.project;

/**
 * The abstract class Property represents properties on the game board that can be owned by players.
 * It extends the BoardSquare class and introduces ownership and mortgage status.
 */
public abstract class Property extends BoardSquare {
    protected Player owner;// The owner of the property
    private boolean isMortgaged;// Indicates whether the property is mortgaged or not
    /**
     * Constructor to create a Property instance with a specified location and name.
     * Initializes the owner as null.
     * @param iLoc Location index on the board.
     * @param name Name of the property.
     */
    public Property(int iLoc, String name){
        super(iLoc, name);
        this.owner = null;

    }
}
