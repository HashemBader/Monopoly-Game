package upei.project;

import java.util.ArrayList;
public class Player {
    // roll dice + money +  int position + strategy + status + landsowened.
    private int money;
    private int pos;
    private ArrayList<String> landsOwned;
    public Player(){
        this.money = 1500;
        this.pos = 0;
        this.landsOwned = new ArrayList<>();

    }
    public int RollDice(){
        int roll = (int)(Math.random()*6)+1;
        return roll;
    }

    public void strategy(){
        // I think the strategy will be in the main game while we make player1 have 80% probability to buy
        // because in the main we can make player1, player2, player3 and give each of them a strategy.
        // this class will have the core of a player that every player will have
    }


    public void getStatus(){
        System.out.printf("money: ",this.money);
        System.out.printf("landsOwned: ",this.landsOwned);
    }

    public int getPos(){return this.pos;}
    public int getMoney(){return this.money;}
    public ArrayList<String> getLandsOwned(){return this.landsOwned;}
    public void setPos(int a){this.pos = a;}
    public void addMoney(int a){this.money += a;}
    public void lessMoney(int a){this.money -= a;}
    public void addLandsOwned(String a){
        if (!getLandsOwned().contains(a)){
        this.landsOwned.add(a);}
        }
    public void removeLandsOwned(String a){
        if (getLandsOwned().contains(a)){
        this.landsOwned.remove(a);}
        }






}
