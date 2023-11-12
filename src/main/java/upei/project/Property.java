package upei.project;

public abstract class Property extends BoardLocation{
    private Player owner;
    private boolean isMortgaged;
    public Property(int iLoc, String name){
        super(iLoc, name);
        this.owner = null;
        this.isMortgaged = false;

    }
}
