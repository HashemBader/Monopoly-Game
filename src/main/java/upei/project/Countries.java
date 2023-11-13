package upei.project;

public class Countries extends BoardLocation{
   // String color;
    private Player owner;
    private boolean isMortgaged;
    private int[] rent;
    private int buyPrice;
    public Countries(int iLoc, String name, int[] rent, int buyPrice){
        super(iLoc, name);
        //this.color = color;
        this.rent = rent;
        this.buyPrice = buyPrice;
        this.owner = null;
        this.isMortgaged = false;
    }

    public void playerOnLocation(Player player){
        if (getOwner().equals(player)){
            return;
        }
        else if (!getOwner().equals(player) && getOwner() == null){
            if (player.makeChoice()){
                setOwner(player);
                player.lessMoney(getBuyPrice());
            }
        }
        else{
            player.lessMoney(getRent()[0]);
            getOwner().addMoney(getRent()[0]);
        }


    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int[] getRent() {
        return rent;
    }

    public void setRent(int[] rent) {
        this.rent = rent;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }
    public boolean isMortgaged(){return this.isMortgaged;}
    public int getMortgagePrice(){
        return (int)(this.buyPrice / 2);
    }
}
