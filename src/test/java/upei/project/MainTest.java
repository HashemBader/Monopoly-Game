package upei.project;
import org.junit.jupiter.api.Test;
import upei.project.Properties.Country;
import upei.project.Properties.Property;
import upei.project.Properties.Station;
import upei.project.Properties.Utility;
import upei.project.Setup.BoardInit;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {

    @Test
    public void testCountryConstructor(){
        Station country = new Station(15, "Canada",200);
        assertEquals(country, country);

    }

    /*@Test
    public void testUtilitiesRent(){
        Player p1 = new Player(1500);
        Player p2 = new Player(1500);
        ((Property) MonopolyGame.boardMap.get(12)).setOwner(p1);
        ((Property) MonopolyGame.boardMap.get(28)).setOwner(p1);
        p2.rollDice();
        MonopolyGame.boardMap.get(12).playerOnLocation(p2);
        assertEquals(1500, ""+p2+" "+Player.diceVal);
    }*/


    // ============== NEW ======================

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
        Player p1 = new Player(1500);
        ArrayList<BoardSquare> boardMap = BoardInit.createBoard();
        p1.setPos(30);
        boardMap.get(p1.getPos()).playerOnLocation(p1);
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
        Player player1 = new Player(1500, Player.strategy.STINGY, seed);
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
        Player player1 = new Player(1500, Player.strategy.STATION_GUY, seed);
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
*//*    void testPlayerOnLocation_AdvanceToGo() {
        WildSquare chance = new WildSquare(1, "Chance");
        Player player = new Player(1500);
        chance.playerOnLocation(player);

        assertEquals(0, player.getPos(), "Player should advance to 'Go'");
    }*/

/*    @Test
    void testPlayerOnLocation_BankError() {// it is random process so when the random is the same as the "iloc" it works
        WildSquare wildSquare = new WildSquare(2, "Wild Square");
        Player player = new Player(1000);

        wildSquare.playerOnLocation(player);

        assertEquals(1200, player.getMoney(), "Player should collect $200");
    }*/

/*    @Test
    void testPlayerOnLocation_DoctorsFees() {// it is random process so when the random is the same as the "iloc" it works
        WildSquare wildSquare = new WildSquare(3, "Wild Square");
        Player player = new Player(1000);

        wildSquare.playerOnLocation(player);

        assertEquals(950, player.getMoney(), "Player should pay $50 for doctor's fees");
    }*/
    // tax tests
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
        Player p2 = new Player(1500);
        Country egypt = new Country(39,"egypt",50,400,"darkBlue");
        Country qatar = new Country(37,"qatar",50,400,"darkBlue");
        Utility util = new Utility(1, "util", 100);
        Utility util2 = new Utility(1, "util", 100);
        util.setOwner(p1);
        util2.setOwner(p1);
        egypt.setOwner(p1);
        qatar.setOwner(p1);
        MonopolyGame.rollDice();
        util2.playerOnLocation(p2);
        System.out.println(MonopolyGame.getDiceVal());
        assertEquals(1500-MonopolyGame.getDiceVal()*10, p2.getMoney(), "Paid different rent!");
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
    void testBuyUtility(){}
}


