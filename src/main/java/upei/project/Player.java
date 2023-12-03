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
    public enum strategy {
        GREEDY, STATION_GUY, UTILITY_GUY, STINGY, DEFAULT
    }
    private int money;
    private String name = "anonymous";
    private int pos;
    private strategy pStrategy = strategy.DEFAULT;
    private ArrayList<Property> landsOwned;
    private Random random = new Random(); //for testing

    /**
     * Constructor to create a player with a specific initial money and default strategy.
     * @param money Initial amount of money the player has.
     */

    public Player(int money) {
        this.money = money;
        this.pos = 0;
        this.landsOwned = new ArrayList<>();
    }
    public Player(int money, strategy STRATEGY) {

        this.money = money;
        this.pStrategy = STRATEGY;
        this.pos = 0;
        this.landsOwned = new ArrayList<>();
    }
    public Player(int money, strategy STRATEGY, String name) {
        this.money = money;
        this.pos = 0;
        this.landsOwned = new ArrayList<>();
        this.name = name;
        this.pStrategy = STRATEGY;
    }

    public int getPos() {
        return this.pos;
    }

    public int getMoney() {
        return this.money;
    }

    public ArrayList<Property> getLandsOwned() {
        return this.landsOwned;
    }
/*    public ArrayList<Station> getStationsOwned() {
        ArrayList<Station> stationsOwned = new ArrayList<>();
        for(Property p : this.landsOwned){
            if(p instanceof Station){
                stationsOwned.add((Station)p);
            }
        }
        return stationsOwned;
    }
    public ArrayList<Country> getCountriesOwned() {
        ArrayList<Country> countriesOwned = new ArrayList<>();
        for(Property p : this.landsOwned){
            if(p instanceof Country){
                countriesOwned.add((Country)p);
            }
        }
        return countriesOwned;
    }
    public ArrayList<Utility> getUtilitiesOwned() {
        ArrayList<Utility> utilitiesOwned = new ArrayList<>();
        for(Property p : this.landsOwned){
            if(p instanceof Utility){
                utilitiesOwned.add((Utility) p);
            }
        }
        return utilitiesOwned;
    }*/

    //This reads: public ArrayList of T (such that T is a child of Property) getLandsOwnedOfType which takes a class of T propertyType
    public <T extends Property> ArrayList<T> getLandsOwnedOfType(Class<T> propertyType){
        ArrayList<T> ownedOfType = new ArrayList<>();
        for(Property p : this.landsOwned){
            if(propertyType.isInstance(p)){
                ownedOfType.add((T) p);
            }
        }
        return ownedOfType;
    }
    public void setPos(int pos) {
        this.pos = pos;
    } // goto pos
    // Method to move the player by 'n' steps on the game board
    public void moveN(int n) {
        final int BOARD_SIZE = 40;
        int oldPos = this.pos;
        this.pos = (this.pos + n + BOARD_SIZE) % BOARD_SIZE;
        // Adds 'size' again to account for negative positions
        if(this.pos < oldPos){ // player passed Go!
            this.addMoney(200);
        }
    }
    // Methods to update player's money
    public void addMoney(int amt) {
        this.money += amt;
    }

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
     *
     * @param property Index of the location on the game board.
     * @return A boolean representing the decision made by the player based on their strategy.
     */
    public boolean makeChoice(Property property) {
        if(this.getMoney() <= property.getBuyPrice()) {
            return false;
        }
        boolean res = this.random.nextFloat() <= 0.50;
        switch (this.pStrategy) {
            case GREEDY ->
            res = this.random.nextFloat() <= 0.70;

            case STINGY ->
                res = this.random.nextFloat() <= 0.3;

            case UTILITY_GUY -> {
                if (property instanceof Utility) {
                    res = this.random.nextFloat() <= 0.90;
                } else
                    res = this.random.nextFloat() <= 0.4;
            }
            case STATION_GUY -> {
                if (property instanceof Station) {
                    res = this.random.nextFloat() <= 0.90;
                } else {
                    res = this.random.nextFloat() <= 0.4;
                }
            }
            case DEFAULT ->
                res = this.random.nextFloat() <= 0.50;

        }
        return res;
    }
    public boolean makeHouseChoice(Country c) {
        if (this.getMoney() <= c.getHousePrice()) {
            return false;
        }
        switch (this.pStrategy) {
            case GREEDY -> {
                return this.random.nextFloat() <= 0.90;
            }

            case STINGY -> {
                return this.random.nextFloat() <= 0.30;

            }
            default -> {
                return this.random.nextFloat() <= 0.50;
            }
        }
    }


    public String getName(){
        return this.name;
    }

    public strategy getStratey(){
        return this.pStrategy;
    }

    public boolean hasCompleteSet(String color, int completeSetSize){
        boolean res = true;
        int counter = 0;
        ArrayList<Country> countriesOwned = this.getLandsOwnedOfType(Country.class);
        for (Country country : countriesOwned) {
            if (country.getColor().equals(color)) {
                counter++;
            }
        }
        return counter == completeSetSize;
    }

    public void setSeed(long seed){
        this.random.setSeed(seed);
    }

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

