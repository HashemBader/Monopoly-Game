package upei.project;
/**
 * The Station class represents a type of property on the game board that can be owned by players.
 * It extends the Property class.
 */
public class Station extends Property {
    private int rent;// Rent amount to be paid when a player lands on this station
    private int buyPrice;// Purchase price for this station
    /**
     * Constructor to create a Station instance with a specified location and name.
     *
     * @param iLoc Location index on the board.
     * @param name Name of the station.
     */
    public Station(int iLoc, String name) {
        super(iLoc, name);// Calls the constructor of the parent class (Property)
        this.rent = 25; // Default rent for the station
        this.buyPrice = 200;// Default buying price for the station
    }
    /**
     * Defines the action to be taken when a player lands on this station.
     * @param player The player landing on this station.
     */
    @Override
    public void playerOnLocation(Player player) {
        if (this.getOwner() == player){
            return; //Do nothing if the current player is the owner
        }
        else if (this.getOwner() != player && this.getOwner() == null){ // Property not owned
            if (player.makeChoice(this.iLoc)){// make a choice depend on the strategy
                this.setOwner(player);
                this.getOwner().addLandsOwned(this.name);
                player.lessMoney(this.getBuyPrice());
            }
        }
        else{ // A player has landed on another player's property
            if (getOwner().getLandsOwned().size() == 2) {// double the rent if the owner has 2 stations
                player.lessMoney(2*getRent());// Deduct rent from the current player
                getOwner().addMoney(2*getRent());// Add rent to the property owner's money
            }
            else if (getOwner().getLandsOwned().size() == 3) {// 4 times the rent if the owner has 3 stations
                player.lessMoney(4*getRent());
                getOwner().addMoney(4*getRent());
            }
            else if (getOwner().getLandsOwned().size() == 4) {// 8 times the rent if the owner has 4 stations
                player.lessMoney(8*getRent());
                getOwner().addMoney(8*getRent());
            }
            else {// the rent stays the same
                player.lessMoney(getRent());
                getOwner().addMoney(getRent());
            }
        }
    }
    // Getter and setter methods for station attributes
    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getRent() {return rent;}

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getBuyPrice() {
        return buyPrice;
    }
}
