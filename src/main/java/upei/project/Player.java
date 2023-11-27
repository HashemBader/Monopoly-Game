package upei.project;

import java.util.ArrayList;
public class Player {
    // roll dice + money +  int position + strategy + status + landsowened.
    private int money;
    private int pos;
    private ArrayList<String> landsOwned;
    public Player(int money){
        this.money = money;
        this.pos = 0;
        this.landsOwned = new ArrayList<>();
        //buys 80% of time
        //buys only utilities
        //buys only stations
        //buys 20%

    }



    @Override
    public String toString(){
        return "Player{ " +
                "Money: "+this.money+"; "+
                "Position: "+this.pos+
                " }";
    }

    public int getPos(){return this.pos;}
    public int getMoney(){return this.money;}
    public ArrayList<String> getLandsOwned(){return this.landsOwned;}
    public void setPos(int pos){this.pos = pos;} // goto pos
    public void moveN(int n){this.pos += n;} // move n steps

    public void addMoney(int a){this.money += a;}
    public void lessMoney(int a){this.money -= a;}
    public void addLandsOwned(String a){
        if (!getLandsOwned().contains(a)){
        this.landsOwned.add(a);}
        }
    public void removeLandsOwned(String a){
        if (getLandsOwned().contains(a)){
            this.landsOwned.remove(a);
        }
    }

    public boolean makeChoice(){
        return true;
        //TODO
    }

}
