package upei.project;

import upei.project.Properties.Country;
import upei.project.Properties.Property;
import upei.project.Properties.Station;
import upei.project.Properties.Utility;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Player class represents a player in a game.
 */
public class Player {
    // Enum defining different player strategies
    public enum strategy {
        GREEDY, STATION_GUY, UTILITY_GUY, STINGY, DEFAULT
    }
    private int money;
    private String name;
    private int pos;
    private strategy pStrategy;
    private ArrayList<Property> landsOwned;
    private Random random = new Random(); // Random object for making decisions(for testing)

    /**
     * Constructor to create a player with a specific initial money and default strategy.
     * @param money Initial amount of money the player has.
     */
    public Player(int money) {// fast testing
        this.money = money;
        this.pos = 0;
        this.landsOwned = new ArrayList<>();
        this.name = "anonymous";
        this.pStrategy  = strategy.DEFAULT;
    }
    /**
     * Constructor to create a player with a specific initial money and strategy.
     * @param money Initial amount of money the player has.
     * @param STRATEGY The strategy chosen for the player.
     */
    public Player(int money, strategy STRATEGY) {// fast testing

        this.money = money;
        this.pStrategy = STRATEGY;
        this.pos = 0;
        this.landsOwned = new ArrayList<>();
        this.name = "anonymous";
    }
    /**
     * Constructor to create a player with a specific initial money, strategy, and name.
     * @param money Initial amount of money the player has.
     * @param STRATEGY The strategy chosen for the player.
     * @param name The name of the player.
     */
    public Player(int money, strategy STRATEGY, String name) {
        this.money = money;
        this.pos = 0;
        this.landsOwned = new ArrayList<>();
        this.name = name;
        this.pStrategy = STRATEGY;
    }
    /**
     * Gets the current position of the player on the game board.
     * @return The current position of the player.
     */
    public int getPos() {
        return this.pos;
    }
    /**
     * Gets the current money owned by the player.
     * @return The current money owned by the player.
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Gets the list of properties owned by the player.
     * @return The list of properties owned by the player.
     */
    public ArrayList<Property> getLandsOwned() {
        return this.landsOwned;
    }


    //This reads: public ArrayList of T (such that T is a child of Property) getLandsOwnedOfType which takes a class of T propertyType
    // it returns a specific type of the player's lands owned
    public <T extends Property> ArrayList<T> getLandsOwnedOfType(Class<T> propertyType){
        ArrayList<T> ownedOfType = new ArrayList<>();
        for(Property p : this.landsOwned){
            if(propertyType.isInstance(p)){
                ownedOfType.add((T) p);
            }
        }
        return ownedOfType;
    }
    /**
     * Sets the current position of the player on the game board.
     * @param pos The position to set for the player.
     */
    public void setPos(int pos) {
        this.pos = pos;
    }
    /**
     * Moves the player by 'n' steps on the game board.
     * @param n The number of steps to move the player.
     */
    public void moveN(int n) {
        final int BOARD_SIZE = 40;
        int oldPos = this.pos;
        this.pos = (this.pos + n + BOARD_SIZE) % BOARD_SIZE;// Adds 'size' again to account for negative positions
        if(this.pos < oldPos){ // player passed Go!
            this.addMoney(200);
        }
    }
    /**
     * Adds money to the player's current balance.
     * @param amt The amount of money to add.
     */
    public void addMoney(int amt) {
        this.money += amt;
    }
    /**
     * Subtracts money from the player's current balance.
     * @param amt The amount of money to subtract.
     */
    public void subtractMoney(int amt) {
        if(this.money - amt < 0){
            this.money = 0;
        }
        else {
            this.money -= amt;
        }
    }

    /**
     * Method for the player to make a decision based on their strategy when interacting with a specific location.
     * @param property The property index of the location on the game board.
     * @return A boolean representing the decision made by the player based on their strategy.
     */
    public boolean makeChoice(Property property) {
        if(this.getMoney() <= property.getBuyPrice()) {// does not have the money to buy it
            return false;
        }
        boolean res = this.random.nextFloat() <= 0.50;
        switch (this.pStrategy) {
            case GREEDY ->// has a 70% chance to buy any property
            res = this.random.nextFloat() <= 0.70;

            case STINGY ->// has a 20% chance to buy any property
                res = this.random.nextFloat() <= 0.2;

            case UTILITY_GUY -> {// has a 90% chance to buy a utility and a 40% to buy any other properties
                if (property instanceof Utility) {
                    res = this.random.nextFloat() <= 0.90;
                } else
                    res = this.random.nextFloat() <= 0.4;
            }
            case STATION_GUY -> {// has a 90% chance to buy a station and a 40% to buy any other properties
                if (property instanceof Station) {
                    res = this.random.nextFloat() <= 0.90;
                } else {
                    res = this.random.nextFloat() <= 0.4;
                }
            }
            case DEFAULT ->// has a 50% chance to buy any property
                res = this.random.nextFloat() <= 0.50;

        }
        return res;
    }

    /**
     * Method for the player to make a decision about building houses on a country property based on their strategy.
     * @param c The country property for which the decision is made.
     * @return A boolean representing the decision made by the player based on their strategy.
     */
    public boolean makeHouseChoice(Country c) {
        if (this.getMoney() <= c.getHousePrice()) {
            return false;
        }
        switch (this.pStrategy) {
            case GREEDY -> {
                return this.random.nextFloat() <= 0.9;
            }

            case STINGY -> {
                return this.random.nextFloat() <= 0.1;

            }
            default -> {
                return this.random.nextFloat() <= 0.3;
            }
        }
    }

    /**
     * Retrieves the name of the player.
     * @return A string representing the name of the player.
     */
    public String getName(){
        return this.name;
    }
    /**
     * Retrieves the current strategy employed by the player.
     * @return The current strategy (enum) used by the player.
     */
    public strategy getStratey(){
        return this.pStrategy;
    }
    /**
     * Checks if the player has a complete set of properties of a specific color.
     * @param color The color of the property set to be checked.
     * @param completeSetSize The size of the complete property set.
     * @return True if the player has a complete set of properties of the given color; otherwise, false.
     */
    public boolean hasCompleteSet(String color, int completeSetSize){
        int counter = 0;
        ArrayList<Country> countriesOwned = this.getLandsOwnedOfType(Country.class);
        for (Country country : countriesOwned) {
            if (country.getColor().equals(color)) {
                counter++;
            }
        }
        return counter == completeSetSize;
    }
    /**
     * Sets the seed value for the random number generator (for testing purposes).
     * @param seed The seed value to set for the random number generator.
     */
    public void setSeed(long seed){
        this.random.setSeed(seed);
    }
    /**
     * Provides a string representation of the Player object.
     * @return A string containing player information: strategy, name, money, and position.
     */
    @Override
    public String toString() {
        return "Player{ " +
                "Strategy: " + this.pStrategy +"; " +
                "Name: " + this.name + "; " +
                "Money: " + this.money + "; " +
                "Position: " + this.pos +
                " }";
    }
}

