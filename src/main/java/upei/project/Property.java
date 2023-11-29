package upei.project;

/**
 * Properties have an owner
 * */
public abstract class Property extends BoardSquare {
    protected Player owner;
    private boolean isMortgaged;
    public Property(int iLoc, String name){
        super(iLoc, name);
        this.owner = null;

    }
}
