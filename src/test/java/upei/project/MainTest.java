package upei.project;
import org.junit.jupiter.api.Test;
import upei.project.Properties.Country;
import upei.project.Properties.Station;
import upei.project.Properties.Utility;
import upei.project.Setup.BoardInit;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {

    @Test
    public void testCountryConstructor(){
        Station country = new Station(15, "Canada",200);
        assertEquals(country, country);

    }
    @Test
    public void testOwner(){
        Country egypt = new Country(0, "Egypt", 1000, 190000,"red");
        Player p1 = new Player(1500);
        egypt.setOwner(p1);
        egypt.playerOnLocation(p1);
        assertEquals(p1, egypt.getOwner());
    }

    @Test
    public void testGoToJail(){
        Player p1 = new Player(1500);
        p1.setPos(30); //Goto jail
        ArrayList<BoardSquare> boardMap = BoardInit.createBoard();
        boardMap.get(p1.getPos()).playerOnLocation(p1);
        assertEquals(10, p1.getPos());
    }
    @Test
    public void testGetOutOfJail(){
        //seed 1 gets out with no deduction
        //seed 2 gets out with 50 deduction
        Player p1 = new Player(1500);
        long seed = 1;
        ArrayList<BoardSquare> boardMap = BoardInit.createBoard();
        Jail jail = new Jail(10, "jail");
        jail.setSeed(seed);
        jail.playerOnLocation(p1);
        assertEquals(1500, p1.getMoney()); // got out with no deductions
    }
    @Test
    public void testStationOwned() {
        Player p1 = new Player(1500);
        Station s1 = new Station(0, "station1",250);
        Station s2 = new Station(1, "station2",250);
        s1.setOwner(p1);
        s2.setOwner(p1);
        assertEquals("[Station{}" +
                " Property{" +
                "owner=anonymous," +
                " buyPrice=250," +
                " baseRent=25" +
                "}" +
                " BoardLocation{" +
                "iLoc=0," +
                " name='station1'" +
                "}, Station{}" +
                " Property{" +
                "owner=anonymous," +
                " buyPrice=250," +
                " baseRent=25" +
                "} " +
                "BoardLocation{" +
                "iLoc=1, name='station2'}]", p1.getLandsOwnedOfType(Station.class).toString());
    }
    // country tests
    @Test
    public void testCountryConstruction() {
        Country country = new Country(1, "Canada", 50, 500, "red");
        assertNotNull(country, "Instance is null !");
    }

    @Test
    public void testPlayerOnLocation_OwnerExists() {
        Player player1 = new Player(1500);
        Player player2 = new Player(1500);
        Country country = new Country(1, "Mexico", 70, 700, "pink");
        country.setOwner(player1);
        country.playerOnLocation(player2);
        assertEquals(player1, country.getOwner());
        assertEquals(1500+70, player1.getMoney());
        assertEquals(1500-70, player2.getMoney());
    }

    @Test
    public void testPlayerOnLocation_CurrentPlayerIsOwner() {
        Player player1 = new Player(1500);
        Country country = new Country(1, "Brazil", 80, 800, "pink");
        country.setOwner(player1);
        country.playerOnLocation(player1);
        assertEquals(player1, country.getOwner());
        assertEquals(1500, player1.getMoney());
    }

    @Test
    public void testMortgagePriceCalculation() {
        Country country = new Country(1, "France", 90, 900, "lightBlue");
        int mortgagePrice = country.getMortgagePrice();
        assertEquals(450, mortgagePrice);
    }
    //Utilities tests
    @Test
    void testUtilityConstruction() {
        Utility utility = new Utility(1, "Electric Company", 150);
        assertEquals(150, utility.getBuyPrice(), "Buy price should match");
        assertNull(utility.getOwner(), "Owner should be null initially");
        assertNotNull(utility, "Utility should not be null!");
    }

    @Test
    void testPlayerOnLocation_NoOwner2_Seed1() {
        //seed 1 should buy the utility for all strategies
        long seed = 1;
        Player player1 = new Player(1500, Player.strategy.STINGY);
        player1.setSeed(seed);
        Utility utility = new Utility(2, "Water Works", 200);

        utility.playerOnLocation(player1);
        assertEquals(player1, utility.getOwner(), "Player should become the owner");
        assertEquals(1300, player1.getMoney(), "Player should have deducted money after buying the utility");
    }

    @Test
    void testPlayerOnLocation_OwnerExists2() {
        Player player1 = new Player(1500);
        Player player2 = new Player(1500);
        Utility utility = new Utility(3, "Gas Company", 250);
        utility.setOwner(player1);
        MonopolyGame.setDiceVal(10);
        utility.playerOnLocation(player2);
        assertEquals(player1, utility.getOwner(), "Owner should remain the same");
        assertEquals(1540, player1.getMoney(), "Owner should receive rent");
        assertEquals(1460, player2.getMoney(), "Player landing should pay rent");
    }

    @Test
    void testPlayerOnLocation_CurrentPlayerIsOwner2() {
        Player player1 = new Player(1500);
        Utility utility = new Utility(4, "Solar Company", 300);
        utility.setOwner(player1);
        utility.playerOnLocation(player1);
        assertEquals(player1, utility.getOwner(), "Owner should remain the same");
        assertEquals(1500, player1.getMoney(), "Player should not lose money");
    }
    @Test
    public void testStationOwnedOnly() {
        Player p1 = new Player(1500);
        Station s1 = new Station(0, "station1",250);
        Station s2 = new Station(1, "station2",250);
        Country country = new Country(3,"canada",3,200,"pink");
        s1.setOwner(p1);
        s2.setOwner(p1);
        country.setOwner(p1);
        assertEquals("[Station{}" +
                " Property{" +
                "owner=anonymous," +
                " buyPrice=250," +
                " baseRent=25" +
                "}" +
                " BoardLocation{" +
                "iLoc=0," +
                " name='station1'" +
                "}, Station{}" +
                " Property{" +
                "owner=anonymous," +
                " buyPrice=250," +
                " baseRent=25" +
                "} " +
                "BoardLocation{" +
                "iLoc=1, name='station2'}]", p1.getLandsOwnedOfType(Station.class).toString());
    }
    // station tests
    @Test
    void testStationConstruction() {
        Station station = new Station(1, "Kings Cross Station", 200);
        assertEquals(25, station.getBaseRent(), "Rent should match"); //rent is fixed for stations
        assertEquals(200, station.getBuyPrice(), "Buy price should match");
        assertNull(station.getOwner(), "Owner should be null initially");
    }

    @Test
    void testPlayerOnLocation_NoOwner3_Seed1() {
        // seed 1 should buy the station
        long seed = 1;
        Player player1 = new Player(1500, Player.strategy.STATION_GUY);
        player1.setSeed(1);
        Station station = new Station(2, "Fenchurch Street Station", 150);
        station.playerOnLocation(player1);
        assertEquals(player1, station.getOwner(), "Player should become the owner");
        assertEquals(1350, player1.getMoney(), "Player should have deducted money after buying the station");
    }


    @Test
    void testPlayerOnLocation_OwnerExists3() {
        Player player1 = new Player(1000);
        Player player2 = new Player(1000);
        Station station = new Station(3, "Liverpool Street Station", 150);
        station.setOwner(player1);
        station.playerOnLocation(player2);
        assertEquals(player1, station.getOwner(), "Owner should remain the same");
        assertEquals(1025, player1.getMoney(), "Owner should receive rent");
        assertEquals(975, player2.getMoney(), "Player landing should pay rent");
    }

    @Test
    void testPlayerOnLocation_MultipleStations() {
        Player player1 = new Player(1500);
        Player player2 = new Player(1500);
        Station station1 = new Station(5, "T3 Station", 150);
        Station station2 = new Station(6, "Euston Station", 150);
        station1.setOwner(player1);
        station2.setOwner(player1);
        station1.playerOnLocation(player2);

        assertEquals(1550, player1.getMoney(), "Owner should receive rent for two stations");
        assertEquals(1450, player2.getMoney(), "Player landing should pay rent for two stations");
    }
    //wildsquare tests
/*    @Test
*/

    @Test
    public void testWildSquareConstruction() {
        WildSquare wildSquare = new WildSquare(5, "Community Chest");
        assertNotNull(wildSquare);
        assertEquals(5, wildSquare.getILOC());
        assertEquals("Community Chest", wildSquare.getNAME());
    }
    @Test
    void testPlayerOnLocation_AdvanceToGo() {
        // -1 Go
        // 0 Morocco
        // 101010 Qatar
        // 1010101 Lebanon
        // 1417105 Go to Jail
        long seed = -1;
        WildSquare chance = new WildSquare(1, "Chance");
        Player player = new Player(1500);
        chance.setSeed(seed);
        chance.playerOnLocation(player);
        assertEquals(0, player.getPos(), "Player should advance to 'Go'");
    }
    @Test
    void testPlayerGoToJail_noDeduction() {
        // -1 Go
        // 0 Morocco
        // 101010 Qatar
        // 1010101 Lebanon
        // 1417105 Go to Jail
        long seed1 = 1417105; //WildSquare's go to jail
        long seed2 = 1; // Jail's 50 deduction
        WildSquare chance = new WildSquare(1, "Chance");
        Jail jail = new Jail(10, "jail");
        jail.setSeed(seed2);
        Player player = new Player(1500);
        chance.setSeed(seed1); // go to jail card
        chance.playerOnLocation(player);
        jail.playerOnLocation(player);

        assertEquals(1500, player.getMoney(), "Player should go to jail!");
    }
    @Test
    void testPlayerGoToJail_deduction() {
        long seed1 = 1417105; //WildSquare's go to jail
        long seed2 = -90; //Jail's 50 deduction
        WildSquare chance = new WildSquare(1, "Chance");
        Jail jail = new Jail(10, "jail");
        jail.setSeed(seed1);
        Player player = new Player(1500);
        chance.setSeed(seed2); // go to jail card
        chance.playerOnLocation(player);
        jail.playerOnLocation(player);

        assertEquals(1450, player.getMoney(), "Player should go to jail!");
    }
    @Test
    void testPlayerOnLocation_TaxAtPosition4() {
        Tax taxSquare = new Tax(4, "Income Tax");
        Player player = new Player(1000);
        player.setPos(4);

        taxSquare.playerOnLocation(player);

        assertEquals(800, player.getMoney(), "Player should pay $200 as income tax");
    }

    @Test
    void testPlayerOnLocation_TaxAtPosition38() {
        Tax taxSquare = new Tax(38, "Luxury Tax");
        Player player = new Player(1500);
        player.setPos(38);

        taxSquare.playerOnLocation(player);

        assertEquals(1400, player.getMoney(), "Player should pay $100 as luxury tax");
    }

    @Test
    void testPlayerOnLocation_NoTax() {
        Tax taxSquare = new Tax(10, "Random Tax");
        Player player = new Player(2000);
        player.setPos(10);
        int initialMoney = player.getMoney();

        taxSquare.playerOnLocation(player);

        assertEquals(initialMoney, player.getMoney(), "Player should not pay any tax");
    }
    @Test
    void testCompleteSetRent() {
        Player p1 = new Player(1500);
        Player p2 = new Player(1500);
        Country egypt = new Country(39,"egypt",50,400,"red");
        Country qatar = new Country(37,"qatar",50,400,"darkBlue");
        egypt.setOwner(p1);
        qatar.setOwner(p1);
        egypt.playerOnLocation(p2);
        assertEquals(1500-50, p2.getMoney(), "Paid different rent!");
    }
    @Test
    void testLandsOwned() {
        Player p1 = new Player(1500);
        Country egypt = new Country(39,"egypt",50,400,"darkBlue");
        Country qatar = new Country(37,"qatar",50,400,"darkBlue");
        Utility util = new Utility(1, "util", 100);
        util.setOwner(p1);
        egypt.setOwner(p1);
        qatar.setOwner(p1);
        assertEquals("[Utility{" +
                "Property{" +
                "owner=anonymous, buyPrice=100, baseRent=0} " +
                "" +
                "BoardLocation{iLoc=1, name='util'}}, " +
                "Country{COLOR='darkBlue'} " +
                "Property{owner=anonymous, buyPrice=400, baseRent=50} " +
                "BoardLocation{iLoc=39, name='egypt'}," +
                " Country{COLOR='darkBlue'}" +
                " Property{owner=anonymous, buyPrice=400, baseRent=50}" +
                " BoardLocation{iLoc=37, name='qatar'}]", p1.getLandsOwned().toString(), "checking the landsowned");
    }
    @Test
    void testLandsOwnedEmpty() {
        Player p1 = new Player(1500);
        Country egypt = new Country(39,"egypt",50,400,"darkBlue");
        Country qatar = new Country(37,"qatar",50,400,"darkBlue");
        Utility util = new Utility(1, "util", 100);
        assertEquals(0, p1.getLandsOwned().size(), "checking the landsowned");
    }
    @Test
    void testHasCompleteSet() {
        Player p1 = new Player(1500);
        Country egypt = new Country(39,"egypt",50,400,"darkBlue");
        Country qatar = new Country(37,"qatar",50,400,"darkBlue");
        egypt.setOwner(p1);
        qatar.setOwner(p1);
        assertTrue(p1.hasCompleteSet(egypt.getColor(), Country.colorSetMapper.get(egypt.getColor())), "Check hasCompleteSet in Country");
    }
    @Test
    void testHousePrice(){
        Country egypt = new Country(39, "egypt", 50, 400, "darkBlue");
        assertEquals(egypt.getHousePrice(), 200);
    }
    @Test
    void testBuildHouse(){
        // seed -42 buys on default
        Country egypt = new Country(39, "egypt", 50, 400, "darkBlue");
        Country qatar = new Country(37, "qatar", 50, 400, "darkBlue");
        Player p1 = new Player(1500);
        Player p2 = new Player(1500);
        p1.setSeed(-42);
        egypt.setOwner(p1);
        qatar.setOwner(p1);
        egypt.playerOnLocation(p1);
        assertEquals(egypt.getNumHouses(), 1);
    }
    @Test
    void testBuildHouseEdge(){
        // seed -42 buys on default
        Country egypt = new Country(39, "egypt", 50, 400, "darkBlue");
        Country qatar = new Country(37, "qatar", 50, 400, "darkBlue");
        Player p1 = new Player(1500);
        Player p2 = new Player(1500);
        p1.setSeed(-42);
        egypt.setOwner(p1);
        qatar.setOwner(p1);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p1);// it landed 5 times but max is 4 houses
        egypt.playerOnLocation(p1);
        assertEquals(egypt.getNumHouses(), 4);
    }
    @Test
    void testBuildHouseIllegally(){
        // seed -42 buys on default
        Country egypt = new Country(39, "egypt", 50, 400, "yellow");
        Country qatar = new Country(37, "qatar", 50, 400, "yellow");
        Country canada = new Country(37, "qatar", 50, 400, "yellow");
        Player p1 = new Player(1500);
        Player p2 = new Player(1500);
        p1.setSeed(-42);
        egypt.setOwner(p1);
        qatar.setOwner(p1);
        canada.setOwner(p2);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p2);
        egypt.playerOnLocation(p1);// he can not buy houses because he doesnt have the full set
        egypt.playerOnLocation(p1);
        assertEquals(egypt.getNumHouses(), 0);
    }
    @Test
    void testDeductMoney(){// pays only full set withour houses
        // seed -42 buys on default
        Country egypt = new Country(39, "egypt", 50, 400, "yellow");
        Country qatar = new Country(37, "qatar", 50, 400, "yellow");
        Country canada = new Country(37, "qatar", 50, 400, "yellow");
        Player p1 = new Player(1500);
        Player p2 = new Player(1500);
        p1.setSeed(-42);
        egypt.setOwner(p1);
        qatar.setOwner(p1);
        canada.setOwner(p1);
        egypt.playerOnLocation(p2);

        assertEquals(1500+50*2, p1.getMoney());
        assertEquals(1500-50*2, p2.getMoney());
    }
    @Test
    void testDeductMoney1House(){// pays only full set withour houses
        // seed -42 buys on default
        Country egypt = new Country(39, "egypt", 50, 400, "yellow");
        Country qatar = new Country(37, "qatar", 50, 400, "yellow");
        Country canada = new Country(37, "qatar", 50, 400, "yellow");
        Player p1 = new Player(1500);
        Player p2 = new Player(1500);
        p1.setSeed(-42);
        egypt.setOwner(p1);
        qatar.setOwner(p1);
        canada.setOwner(p1);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p2);

        assertEquals(1500+(50*5)-egypt.getHousePrice(), p1.getMoney());
        assertEquals(1500-(50*5), p2.getMoney());
    }
    @Test
    void testDeductMoney2Houses(){// pays only full set withour houses
        // seed -42 buys on default
        Country egypt = new Country(39, "egypt", 50, 400, "yellow");
        Country qatar = new Country(37, "qatar", 50, 400, "yellow");
        Country canada = new Country(37, "qatar", 50, 400, "yellow");
        Player p1 = new Player(1500);
        Player p2 = new Player(1500);

        p1.setSeed(-42);
        egypt.setOwner(p1);
        qatar.setOwner(p1);
        canada.setOwner(p1);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p2);

        assertEquals(1500+(50*5*3)-egypt.getHousePrice()*2, p1.getMoney());
        assertEquals(1500-(50*5*3), p2.getMoney());
    }
    @Test
    void testDeductMoney3Houses(){// pays only full set withour houses
        // seed -42 buys on default
        Country egypt = new Country(39, "egypt", 50, 400, "pink");
        Country qatar = new Country(37, "qatar", 50, 400, "pink");
        Country canada = new Country(37, "qatar", 50, 400, "pink");
        Player p1 = new Player(1500);
        Player p2 = new Player(1500);

        p1.setSeed(-42);
        egypt.setOwner(p1);
        qatar.setOwner(p1);
        canada.setOwner(p1);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p2);

        assertEquals(1500+(50*5*3*2)-egypt.getHousePrice()*3, p1.getMoney());
        assertEquals(1500-(50*5*3*2), p2.getMoney());
    }
    @Test
    void testDeductMoney4Houses(){// pays only full set withour houses
        // seed -42 buys on default
        Country egypt = new Country(39, "egypt", 50, 400, "pink");
        Country qatar = new Country(37, "qatar", 50, 400, "pink");
        Country canada = new Country(37, "qatar", 50, 400, "pink");
        Player p1 = new Player(4500);
        Player p2 = new Player(4500);
        p1.setSeed(5556853);
        egypt.setOwner(p1);
        qatar.setOwner(p1);
        canada.setOwner(p1);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p2);

        assertEquals(4500+(50*5*3*3)-egypt.getHousePrice()*4, p1.getMoney());
        assertEquals(4500-(50*5*3*3), p2.getMoney());
    }

}


