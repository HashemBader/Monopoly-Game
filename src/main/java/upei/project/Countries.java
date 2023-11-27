package upei.project;

/**
 * Countries are properties with rent
 */
public class Countries extends Property {
   // String color;
    private boolean isMortgaged;
    private int rent;
    private int buyPrice;
    public Countries(int iLoc, String name, int rent, int buyPrice){
        super(iLoc, name);
        //this.color = color;
        this.rent = rent;
        this.buyPrice = buyPrice;
        this.isMortgaged = false;
    }

    public void playerOnLocation(Player player){
        if (this.getOwner().equals(player)){
            return;
        }
        else if (this.getOwner() != player && this.getOwner() == null){
            if (player.makeChoice()){
                this.setOwner(player);
                player.lessMoney(this.getBuyPrice());
            }
        }
        else{ // a player has landed on owners land
            player.lessMoney(getRent());
            getOwner().addMoney(getRent());
        }
    }

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

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }
    public boolean isMortgaged(){return this.isMortgaged;}
    public int getMortgagePrice(){
        return (int)(this.buyPrice / 2);
    }
}
