package upei.project;

import java.util.ArrayList;
public class Player {
    public enum STRATEGY {
        GREEDY, STATION_GUY, UTILITY_GUY, STINGY, DEFAULT
    }

    private int money;
    private String name = "anonymous";
    private int pos;
    private STRATEGY STRATEGY = this.STRATEGY.DEFAULT;
    private ArrayList<String> landsOwned;

    public Player(int money, STRATEGY STRATEGY) {
        this.money = money;
        this.STRATEGY = STRATEGY;
        this.pos = 0;
        this.landsOwned = new ArrayList<>();
        //buys 80% of time
        //buys only utilities
        //buys only stations
        //buys 20%

    }
    public Player(int money) {
        this.money = money;
        this.pos = 0;
        this.landsOwned = new ArrayList<>();
        //buys 80% of time
        //buys only utilities
        //buys only stations
        //buys 20%
    }
    public Player(int money, STRATEGY STRATEGY, String name) {
        this.money = money;
        this.pos = 0;
        this.landsOwned = new ArrayList<>();
        this.name = name;
        //buys 80% of time
        //buys only utilities
        //buys only stations
        //buys 20%

    }


    @Override
    public String toString() {
        return "Player{ " +
                "Name: " + this.name + "; " +
                "Money: " + this.money + "; " +
                "Position: " + this.pos +
                " }";
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

    public void moveN(int n) {
        this.pos = (this.pos + n + Driver.map.size()) % Driver.map.size();
        // +size again to account for -ve's

    } // move n steps

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

    public boolean makeChoice(int iLoc) {
        boolean res = Math.random() <= 0.5;
        switch (this.STRATEGY) {
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
}

