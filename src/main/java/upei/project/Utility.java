package upei.project;

public class Utility extends Property{
    public Utility(int iLoc, String name){
        super(iLoc, name);
    }

    @Override
    public void playerOnLocation(Player player) {

    }

    /**
     * Returns the utility instance rent after determining it.
     * @return rent
     */
    public int getRent(){
        return calcRent();
    }

    /**
     * Determines rent based on TODO
     * @return
     */
    private int calcRent(){
        return 0;
    }
}
