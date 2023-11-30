package upei.project;

import java.util.ArrayList;
/**
 * The Player class represents a player in a game.
 */
public class Player {
    public enum strategy {
        GREEDY, STATION_GUY, UTILITY_GUY, STINGY, DEFAULT
    }
    public static int diceVal;
    private int money;
    private String name = "anonymous";
    private int pos;
    private strategy pStrategy = strategy.DEFAULT;
    private ArrayList<String> landsOwned;

    public Player(int money, strategy STRATEGY) {

        this.money = money;
        this.pStrategy = STRATEGY;
        this.pos = 0;
        this.landsOwned = new ArrayList<>();
    }
    /**
     * Constructor to create a player with a specific initial money and default strategy.
     * @param money Initial amount of money the player has.
     */
    public Player(int money) {
        this.money = money;
        this.pos = 0;
        this.landsOwned = new ArrayList<>();
        //buys 80% of time
        //buys only utilities
        //buys only stations
        //buys 20%
    }
    public Player(int money, strategy STRATEGY, String name) {
        this.money = money;
        this.pos = 0;
        this.landsOwned = new ArrayList<>();
        this.name = name;
        this.pStrategy = STRATEGY;
        //buys 80% of time
        //buys only utilities
        //buys only stations
        //buys 20%

    }

    public int getPos() {
        return this.pos;
    }

    public int getMoney() {
        return this.money;
    }

    public ArrayList<String> getLandsOwned() {
        return this.landsOwned;
    }

    public void setPos(int pos) {
        this.pos = pos;
    } // goto pos
    // Method to move the player by 'n' steps on the game board
    public void moveN(int n) {
        this.pos = (this.pos + n + Driver.map.size()) % Driver.map.size();
        // Adds 'size' again to account for negative positions
    }
    // Methods to update player's money
    public void addMoney(int a) {
        this.money += a;
    }

    public void lessMoney(int a) {
        if(this.money - a < 0){
            this.money = 0;
        }
        else {
            this.money -= a;
        }

    }
    // Methods to manage properties owned by the player
    public void addLandsOwned(String a) {
        if (!getLandsOwned().contains(a)) {
            this.landsOwned.add(a);
        }
    }
    public void removeLandsOwned(String a) {
        if (getLandsOwned().contains(a)) {
            this.landsOwned.remove(a);
        }
    }
    /**
     * Method for the player to make a decision based on their strategy when interacting with a specific location.
     * @param iLoc Index of the location on the game board.
     * @return A boolean representing the decision made by the player based on their strategy.
     */
    public boolean makeChoice(int iLoc) {
        boolean res = Math.random() <= 0.5;
        switch (this.pStrategy) {
            case GREEDY ->
                res = Math.random() <= 0.8;

            case STINGY ->
                res = Math.random() <= 0.2;

            case UTILITY_GUY -> {
                if (Driver.map.get(iLoc) instanceof Utility) {
                    res = Math.random() <= 0.95;
                } else
                    res = Math.random() <= 0.5;
            }
            case STATION_GUY -> {
                if (Driver.map.get(iLoc) instanceof Station) {
                    res = Math.random() <= 0.95;
                } else {
                    res = Math.random() <= 0.5;
                }
            }
            case DEFAULT ->
                res = Math.random() <= 0.5;

        }
        return res;
    }

    public void rollDice(){
        Player.diceVal = Dice.rollDice2();
    }
    public static int getDiceVal(){
        return Player.diceVal;
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

