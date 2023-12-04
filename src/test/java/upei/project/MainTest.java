package upei.project;
import org.junit.jupiter.api.Test;
import upei.project.Properties.Country;
import upei.project.Properties.Station;
import upei.project.Properties.Utility;
import upei.project.Setup.BoardInit;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains JUnit test cases for various functionalities in the Monopoly game implementation.
 */
public class MainTest {
    /**
     * Tests setting an owner for a Country object.
     * Verifies if the owner of the Country is correctly set and updated.
     */
    @Test
    public void testOwner(){
        Country egypt = new Country(0, "Egypt", 1000, 190000,"red");
        Player p1 = new Player(1500);
        egypt.setOwner(p1);
        egypt.playerOnLocation(p1);// makes the action that needs to be taken
        assertEquals(p1, egypt.getOwner());
    }
    /**
     * Tests the scenario where the player moves over the boundary of the board.
     */
    @Test
    public void testMovesOverBoundary(){
        Player p1 = new Player(1500);
        p1.setPos(38);
        p1.moveN(5);
        assertEquals(3, p1.getPos());
    }
    /**
     * Tests the scenario where the player collects the reward for passing 'Go'.
     */
    @Test
    public void testCollectGo(){
        Player p1 = new Player(1500);
        p1.setPos(38);
        p1.moveN(5);
        assertEquals(1500+200, p1.getMoney());
    }
    /**
     * Tests the movement of a player to the jail square.
     * Verifies if the player is correctly moved to the jail square on the board.
     */
    @Test
    public void testGoToJail(){
        Player p1 = new Player(1500);
        p1.setPos(30); //Goto jail
        ArrayList<BoardSquare> boardMap = BoardInit.createBoard();
        boardMap.get(p1.getPos()).playerOnLocation(p1);// makes the action that needs to be taken
        assertEquals(10, p1.getPos());
    }
    /**
     * Tests the scenario of a player getting out of jail.
     * Verifies if the player gets out of jail without any money deduction.
     */
    @Test
    public void testGetOutOfJail_NoDeduction(){
        //seed 1 gets out with no deduction
        Player p1 = new Player(1500);
        final long SEED = 1;
        Jail jail = new Jail(10, "jail");
        jail.setSeed(SEED);
        jail.playerOnLocation(p1);
        assertEquals(1500, p1.getMoney()); // got out with no deductions
    }
    /**
     * Tests the scenario of a player getting out of jail.
     * Verifies if the player gets out of jail with money deduction.
     */
    @Test
    public void testGetOutOfJail_Deduction(){
        //seed 2 gets out with 50 deduction
        Player p1 = new Player(1500);
        final long SEED = 2;
        Jail jail = new Jail(10, "jail");
        jail.setSeed(SEED);
        jail.playerOnLocation(p1);
        assertEquals(1450, p1.getMoney()); // got out with no deductions
    }
    /**
     * Tests ownership of multiple stations by a player.
     * Verifies if the player owns multiple stations and returns them as expected.
     */
    @Test
    public void testStationOwned() {
        Player p1 = new Player(1500);
        Station s1 = new Station(0, "station1",250);
        Station s2 = new Station(1, "station2",250);
        s1.setOwner(p1);
        s2.setOwner(p1);
        assertEquals("[Station{iLoc=0, Name='station1}," +
                " Station{iLoc=1, Name='station2}]", p1.getLandsOwnedOfType(Station.class).toString());
    }
    /**
     * Tests the construction of a Country object.
     * Verifies if a Country object is properly instantiated.
     */
    @Test
    public void testCountryConstruction() {
        Country country = new Country(1, "Canada", 50, 500, "red");
        assertEquals(500, country.getBuyPrice(), "Buy price should match");
        assertNull(country.getOwner(), "Owner should be null initially");
        assertNotNull(country, "Instance is null !");
        assertEquals(50, country.getBaseRent(), "Validate base rent of the country");
        assertEquals(1, country.getILOC(), "Validate position of the country");
        assertEquals("Canada", country.getNAME(), "Validate name of the country");
        assertEquals("red", country.getColor(), "Validate color associated with the country");
    }
    /**
     * Tests the scenario when a player lands on a country where an owner exists.
     * Verifies if the player correctly pays rent to the owner.
     */
    @Test
    public void testPlayerOnLocation_OwnerExistsForCountry() {
        Player player1 = new Player(1500);
        Player player2 = new Player(1500);
        Country country = new Country(1, "Mexico", 70, 700, "pink");
        country.setOwner(player1);
        country.playerOnLocation(player2);
        assertEquals(player1, country.getOwner());
        assertEquals(1500+70, player1.getMoney());
        assertEquals(1500-70, player2.getMoney());
    }
    /**
     * Tests the scenario when the current player is the owner of a country.
     * Verifies if the player, being the owner, does not pay any rent.
     */
    @Test
    public void testPlayerOnLocation_CurrentPlayerIsOwnerForCountry() {
        Player player1 = new Player(1500);
        Country country = new Country(1, "Brazil", 80, 800, "pink");
        country.setOwner(player1);
        country.playerOnLocation(player1);
        assertEquals(player1, country.getOwner());
        assertEquals(1500, player1.getMoney());
    }
    /**
     * Tests the scenario where a player purchases a country when no owner exists for the country.
     * The player should successfully purchase the country.
     */
    @Test
    public void testPlayerOnLocation_NoOwnerForCountry() {
        final long SEED = 1;// should buy the country for all strategies
        Player player1 = new Player(1500);
        Country country = new Country(1, "Brazil", 80, 800, "pink");
        player1.setSeed(SEED);
        country.playerOnLocation(player1);
        assertEquals(player1, country.getOwner());
        assertEquals(700, player1.getMoney());
    }

    /**
     * Tests the construction of a Utility object.
     * Verifies if a Utility object is properly instantiated.
     */
    @Test
    void testUtilityConstruction() {
        Utility utility = new Utility(1, "Electric Company", 150);
        assertEquals(150, utility.getBuyPrice(), "Buy price should match");
        assertNull(utility.getOwner(), "Owner should be null initially");
        assertNotNull(utility, "Utility should not be null!");
        assertEquals(0, utility.getBaseRent(), "Validate base rent of the country");
        assertEquals(1, utility.getILOC(), "Validate position of the country");
        assertEquals("Electric Company", utility.getNAME(), "Validate name of the country");
    }
    /**
     * Tests the scenario when a player lands on a utility with no existing owner using seed 1.
     * Verifies if the player becomes the owner and money is deducted accordingly.
     */
    @Test
    void testPlayerOnLocation_NoOwnerForUtility() {
        //seed 1 should buy the utility for all strategies
        final long SEED = 1;
        Player player1 = new Player(1500, Player.strategy.STINGY);
        player1.setSeed(SEED);
        Utility utility = new Utility(2, "Water Works", 200);

        utility.playerOnLocation(player1);
        assertEquals(player1, utility.getOwner(), "Player should become the owner");
        assertEquals(1300, player1.getMoney(), "Player should have deducted money after buying the utility");
    }
    /**
     * Tests the scenario when a player lands on a utility where an owner exists.
     * Verifies if the owner remains the same and correct rent transactions occur between players.
     */
    @Test
    void testPlayerOnLocation_OwnerExistsForUtility() {
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
    /**
     * Tests the scenario when the current player is the owner of a utility.
     * Verifies if the owner remains the same and the player does not lose any money.
     */
    @Test
    void testPlayerOnLocation_CurrentPlayerIsOwnerForUtility() {
        Player player1 = new Player(1500);
        Utility utility = new Utility(4, "Solar Company", 300);
        utility.setOwner(player1);
        utility.playerOnLocation(player1);
        assertEquals(player1, utility.getOwner(), "Owner should remain the same");
        assertEquals(1500, player1.getMoney(), "Player should not lose money");
    }
    /**
     * Tests the ownership of stations only by a player.
     * Verifies if the player owns multiple stations and the country without issues.
     */
    @Test
    public void testStationOwnedOnly() {
        Player p1 = new Player(1500);
        Station s1 = new Station(0, "station1",250);
        Station s2 = new Station(1, "station2",250);
        Country country = new Country(3,"canada",3,200,"pink");
        s1.setOwner(p1);
        s2.setOwner(p1);
        country.setOwner(p1);
        assertEquals("[Station{iLoc=0, Name='station1}," +
                " Station{iLoc=1, Name='station2}]", p1.getLandsOwnedOfType(Station.class).toString());
    }
    /**
     * Tests the construction of a Station object.
     * Verifies if a Station object is properly instantiated with the correct attributes.
     */
    @Test
    void testStationConstruction() {
        Station station = new Station(1, "P6 Station", 200);
        assertEquals(25, station.getBaseRent(), "Rent should match"); //rent is fixed for stations
        assertEquals(200, station.getBuyPrice(), "Buy price should match");
        assertNull(station.getOwner(), "Owner should be null initially");
        assertNotNull(station, "Station should not be null!");
        assertEquals(1, station.getILOC(), "Validate position of the country");
        assertEquals("P6 Station", station.getNAME(), "Validate name of the country");
    }
    /**
     * Tests the scenario when a player lands on a station with no existing owner using seed 1.
     * Verifies if the player becomes the owner and money is deducted accordingly.
     */
    @Test
    void testPlayerOnLocation_NoOwnerForStation() {
        // seed 1 should buy the station
        final long SEED = 1;
        Player player1 = new Player(1500, Player.strategy.STATION_GUY);
        player1.setSeed(SEED);
        Station station = new Station(2, "T3 Station", 150);
        station.playerOnLocation(player1);
        assertEquals(player1, station.getOwner(), "Player should become the owner");
        assertEquals(1350, player1.getMoney(), "Player should have deducted money after buying the station");
    }
    /**
     * Tests the scenario when a player lands on a station where an owner exists.
     * Verifies if the owner remains the same and correct rent transactions occur between players.
     */
    @Test
    void testPlayerOnLocation_OwnerExistsForStation() {
        Player player1 = new Player(1000);
        Player player2 = new Player(1000);
        Station station = new Station(3, "Liverpool Station", 150);
        station.setOwner(player1);
        station.playerOnLocation(player2);
        assertEquals(player1, station.getOwner(), "Owner should remain the same");
        assertEquals(1025, player1.getMoney(), "Owner should receive rent");
        assertEquals(975, player2.getMoney(), "Player landing should pay rent");
    }
    /**
     * Tests the scenario where the current player landing on a station already owned by themselves.
     * The player remains the owner, and no money is deducted.
     */
    @Test
    void testPlayerOnLocation_CurrentPlayerIsOwnerForStation() {
        Player player1 = new Player(1500);
        Station station = new Station(4, "Solar Company", 300);
        station.setOwner(player1);
        station.playerOnLocation(player1);
        assertEquals(player1, station.getOwner(), "Owner should remain the same");
        assertEquals(1500, player1.getMoney(), "Player should not lose money");
    }
    /**
     * Tests the scenario when a player lands on multiple stations owned by the same owner.
     * Verifies if the correct rent is paid and received by the respective players.
     */
    @Test
    void testPlayerOnLocation_MultipleStations() {
        Player player1 = new Player(1500);
        Player player2 = new Player(1500);
        Station station1 = new Station(5, "T3 Station", 150);
        Station station2 = new Station(6, "T4 Station", 150);
        station1.setOwner(player1);
        station2.setOwner(player1);
        station1.playerOnLocation(player2);

        assertEquals(1550, player1.getMoney(), "Owner should receive rent for two stations");
        assertEquals(1450, player2.getMoney(), "Player landing should pay rent for two stations");
    }
    /**
     * Tests the construction of a WildSquare object.
     * Verifies if a WildSquare object is properly instantiated with the correct attributes.
     */
    @Test
    public void testWildSquareConstruction() {
        WildSquare wildSquare = new WildSquare(5, "Community Chest");
        assertNotNull(wildSquare, "WildSquare should not be null!");
        assertEquals(5, wildSquare.getILOC(), "Validate position of the country");
        assertEquals("Community Chest", wildSquare.getNAME(), "Validate name of the country");
    }
    /**
     * Tests the scenario when a player lands on a WildSquare advancing to 'Go'.
     * Verifies if the player advances to the 'Go' position correctly.
     */
    @Test
    void testPlayerOnLocation_AdvanceToGo() {
            final long SEED = -1;// WildSquare's Advance to go
        WildSquare chance = new WildSquare(1, "Chance");
        Player player = new Player(1500);
        chance.setSeed(SEED);// advance to go card
        chance.playerOnLocation(player);
        assertEquals(0, player.getPos(), "Player should advance to 'Go'");
    }
    /**
     * Tests the scenario where a player lands on a WildSquare with the effect of advancing the player to a specific station.
     * The player should advance to the position of a particular station ('Port Said' station in this case).
     */
    @Test
    void testPlayerOnLocation_AdvanceToPortSaidStation() {
        final long SEED = -653734342;// WildSquare's Advance to go
        WildSquare chance = new WildSquare(1, "Chance");
        Player player = new Player(1500);
        chance.setSeed(SEED);// advance to go card
        chance.playerOnLocation(player);
        assertEquals(15, player.getPos(), "Player should advance to 'PortSaid'");
    }
    /**
     * Tests the scenario where a player lands on a WildSquare with the effect of advancing the player to a specific country (in this case, 'Egypt').
     * The player should advance to the position representing the 'Egypt' country on the board.
     */
    @Test
    void testPlayerOnLocation_AdvanceToEgypt() {
        final long SEED = -14324312;// WildSquare's Advance to go
        WildSquare chance = new WildSquare(1, "Chance");
        Player player = new Player(1500);
        chance.setSeed(SEED);// advance to go card
        chance.playerOnLocation(player);
        assertEquals(39, player.getPos(), "Player should advance to 'Egypt'");
    }
    /**
     * Tests the scenario when a player lands on the 'Go to Jail' WildSquare.
     * Verifies if the player moves to the jail position without any deduction.
     */
    @Test
    void testPlayerGoToJail_noDeduction() {
        final long SEED1 = 1417105; //WildSquare's go-to jail
        final long SEED2 = 1; // Jail's 50 deduction
        WildSquare chance = new WildSquare(1, "Chance");
        Jail jail = new Jail(10, "jail");
        jail.setSeed(SEED2);
        Player player = new Player(1500);
        chance.setSeed(SEED1); // go to jail card
        chance.playerOnLocation(player);
        jail.playerOnLocation(player);

        assertEquals(1500, player.getMoney());
    }
    /**
     * Tests the scenario when a player lands on the 'Go to Jail' WildSquare with deduction.
     * Verifies if the player moves to the jail position with a deduction.
     */
    @Test
    void testPlayerGoToJail_deduction() {
        final long SEED1 = 1417105; //WildSquare's go-to jail
        final long SEED2 = -90; //Jail's 50 deduction
        WildSquare chance = new WildSquare(1, "Chance");
        Jail jail = new Jail(10, "jail");
        jail.setSeed(SEED1);
        Player player = new Player(1500);
        chance.setSeed(SEED2); // go to jail card
        chance.playerOnLocation(player);
        jail.playerOnLocation(player);

        assertEquals(1450, player.getMoney());
    }
    /**
     * Tests the scenario when a player lands on a tax square at position 4.
     * Verifies if the player pays the correct amount of income tax.
     */
    @Test
    void testPlayerOnLocation_TaxAtPosition4() {
        Tax taxSquare = new Tax(4, "Income Tax");
        Player player = new Player(1000);
        player.setPos(4);

        taxSquare.playerOnLocation(player);

        assertEquals(800, player.getMoney(), "Player should pay $200 as income tax");
    }
    /**
     * Tests the scenario when a player lands on a tax square at position 38.
     * Verifies if the player pays the correct amount of luxury tax.
     */
    @Test
    void testPlayerOnLocation_TaxAtPosition38() {
        Tax taxSquare = new Tax(38, "Luxury Tax");
        Player player = new Player(1500);
        player.setPos(38);

        taxSquare.playerOnLocation(player);

        assertEquals(1400, player.getMoney(), "Player should pay $100 as luxury tax");
    }
    /**
     * Tests the scenario when a player lands on a tax square that doesn't levy any tax.
     * Verifies if the player's money remains unchanged.
     */
    @Test
    void testPlayerOnLocation_NoTax() {
        Tax taxSquare = new Tax(10, "Random Tax");
        Player player = new Player(2000);
        player.setPos(10);
        int initialMoney = player.getMoney();

        taxSquare.playerOnLocation(player);

        assertEquals(initialMoney, player.getMoney(), "Player should not pay any tax");
    }
    /**
     * Tests the scenario when a player lands on multiple countries in a complete set owned by the same player.
     * Verifies if the correct rent is paid by another player who lands on one of the countries.
     */
    @Test
    void testCompleteSetRent() {
        Player p1 = new Player(1500);
        Player p2 = new Player(1500);
        Country egypt = new Country(39,"egypt",50,400,"red");
        Country qatar = new Country(37,"qatar",50,400,"darkBlue");
        egypt.setOwner(p1);
        qatar.setOwner(p1);
        egypt.playerOnLocation(p2);
        assertEquals(1500-50, p2.getMoney(), "Paid base rent");
    }
    /**
     * Tests the scenario when a player owns multiple properties and utilities.
     * Verifies if the list of lands owned by the player is correctly represented.
     */
    @Test
    void testLandsOwned() {
        Player p1 = new Player(1500);
        Country egypt = new Country(39,"egypt",50,400,"darkBlue");
        Country qatar = new Country(37,"qatar",50,400,"darkBlue");
        Utility util = new Utility(1, "util", 100);
        util.setOwner(p1);
        egypt.setOwner(p1);
        qatar.setOwner(p1);
        assertEquals("[Utility{iLoc=1, Name='util}," +
                " Country{iLoc=39, Name='egypt', COLOR='darkBlue'}," +
                " Country{iLoc=37, Name='qatar', COLOR='darkBlue'}]",
                p1.getLandsOwned().toString(), "checking the lands owned");
    }
    /**
     * Tests the scenario when a player doesn't own any properties or utilities.
     * Verifies if the list of lands owned by the player is empty.
     */
    @Test
    void testLandsOwnedEmpty() {
        Player p1 = new Player(1500);
        assertEquals(0, p1.getLandsOwned().size(), "checking the lands owned");
    }
    /**
     * Tests the scenario when a player owns a complete set of countries of the same color.
     * Verifies if the hasCompleteSet method correctly identifies the complete set.
     */
    @Test
    void testHasCompleteSet() {
        Player p1 = new Player(1500);
        Country egypt = new Country(39,"egypt",50,400,"darkBlue");
        Country qatar = new Country(37,"qatar",50,400,"darkBlue");
        egypt.setOwner(p1);
        qatar.setOwner(p1);
        assertTrue(p1.hasCompleteSet(egypt.getColor(), Country.colorSetMapper.get(egypt.getColor())), "Check hasCompleteSet in Country");
    }
    /**
     * Tests the method to retrieve the house price of a country.
     * Verifies if the retrieved house price matches the expected value.
     */
    @Test
    void testHousePrice(){
        Country egypt = new Country(39, "egypt", 50, 400, "darkBlue");
        assertEquals(egypt.getHousePrice(), 200);
    }
    /**
     * Tests the scenario when a player builds a house on a country.
     * Verifies if the number of houses on the country increases after the player builds a house.
     */
    @Test
    void testBuildHouse(){
        // seed -42 buys on default strategy
        Country egypt = new Country(39, "egypt", 50, 400, "darkBlue");
        Country qatar = new Country(37, "qatar", 50, 400, "darkBlue");
        Player p1 = new Player(1500);
        p1.setSeed(-42);
        egypt.setOwner(p1);
        qatar.setOwner(p1);
        egypt.playerOnLocation(p1);
        assertEquals(egypt.getNumHouses(), 1);
    }
    /**
     * Tests the scenario when a player builds houses on a country up to the maximum limit.
     * Verifies if the number of houses on the country reaches the maximum of 4.
     */
    @Test
    void testBuildHouseEdge(){
        // seed -42 buys on default strategy
        Country egypt = new Country(39, "egypt", 50, 400, "darkBlue");
        Country qatar = new Country(37, "qatar", 50, 400, "darkBlue");
        Player p1 = new Player(1500);
        p1.setSeed(-42);
        egypt.setOwner(p1);
        qatar.setOwner(p1);
        egypt.playerOnLocation(p1);
        p1.setSeed(-42);
        egypt.playerOnLocation(p1);
        p1.setSeed(-42);
        egypt.playerOnLocation(p1);
        p1.setSeed(-42);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p1);// it landed 5 times but max is 4 houses
        assertEquals(egypt.getNumHouses(), 4);
    }
    /**
     * Tests the scenario when a player tries to build houses illegally on a country without owning the full set.
     * Verifies if the number of houses remains zero due to not having the full set.
     */
    @Test
    void testBuildHouseIllegally(){
        // seed -42 buys on default strategy
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
        egypt.playerOnLocation(p1);// he can not buy houses because he doesn't have the full set
        egypt.playerOnLocation(p1);
        assertEquals(egypt.getNumHouses(), 0);
    }
    /**
     * Tests the scenario when a player deducts money based on the ownership of a full set without houses.
     * Verifies if the correct amount is deducted from the non-owner player.
     */
    @Test
    void testDeductMoney(){
        // seed -42 buys on default strategy
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
    /**
     * Tests the scenario when a player deducts money based on the ownership of a full set with one house.
     * Verifies if the correct amount is deducted from the non-owner player.
     */
    @Test
    void testDeductMoney1House(){
        // seed -42 buys on default strategy
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
    /**
     * Tests the scenario when a player deducts money based on the ownership of a full set with two houses.
     * Verifies if the correct amount is deducted from the non-owner player.
     */
    @Test
    void testDeductMoney2Houses(){
        // seed -42 buys on default strategy
        Country egypt = new Country(39, "egypt", 50, 400, "yellow");
        Country qatar = new Country(37, "qatar", 50, 400, "yellow");
        Country canada = new Country(37, "qatar", 50, 400, "yellow");
        Player p1 = new Player(1500);
        Player p2 = new Player(1500);

        egypt.setOwner(p1);
        qatar.setOwner(p1);
        canada.setOwner(p1);
        p1.setSeed(-42);
        egypt.playerOnLocation(p1);
        p1.setSeed(-42);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p2);

        assertEquals(1500+(50*5*3)-egypt.getHousePrice()*2, p1.getMoney());
        assertEquals(1500-(50*5*3), p2.getMoney());
    }
    /**
     * Tests the scenario when a player deducts money based on the ownership of a full set with three houses.
     * Verifies if the correct amount is deducted from the non-owner player.
     */
    @Test
    void testDeductMoney3Houses(){
        // seed -42 buys on default strategy
        Country egypt = new Country(39, "egypt", 50, 400, "pink");
        Country qatar = new Country(37, "qatar", 50, 400, "pink");
        Country canada = new Country(37, "qatar", 50, 400, "pink");
        Player p1 = new Player(1500);
        Player p2 = new Player(1500);
        egypt.setOwner(p1);
        qatar.setOwner(p1);
        canada.setOwner(p1);
        p1.setSeed(-42);
        egypt.playerOnLocation(p1);
        p1.setSeed(-42);
        egypt.playerOnLocation(p1);
        p1.setSeed(-42);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p2);

        assertEquals(1500+(50*5*3*2)-egypt.getHousePrice()*3, p1.getMoney());
        assertEquals(1500-(50*5*3*2), p2.getMoney());
    }
    /**
     * Tests the scenario when a player deducts money based on the ownership of a full set with four houses.
     * Verifies if the correct amount is deducted from the non-owner player.
     */
    @Test
    void testDeductMoney4Houses(){
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
        p1.setSeed(-42);
        egypt.playerOnLocation(p1);
        p1.setSeed(-42);
        egypt.playerOnLocation(p1);
        p1.setSeed(-42);
        egypt.playerOnLocation(p1);
        p1.setSeed(-42);
        egypt.playerOnLocation(p1);
        egypt.playerOnLocation(p2);

        assertEquals(4500+(50*5*3*3)-egypt.getHousePrice()*4, p1.getMoney());
        assertEquals(4500-(50*5*3*3), p2.getMoney());
    }
    /**
     * Tests the scenario of a single-player game in the Monopoly simulation.
     * It verifies that the game runs with a single player and ensures that only one player remains at the end.
     */
    @Test
    public void testSinglePlayerGame() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(1500));
        ArrayList<BoardSquare> boardMap = new ArrayList<>();
        // Initialize the boardMap with appropriate squares
        MonopolyGame game = new MonopolyGame(players, boardMap);
        game.playGame();
        Player winner = game.getWinner();

        assertNotNull(winner, "winner should not be null");
        assertEquals(1, players.size()); // Ensure that only one player remains at the end
    }
    /**
     * Tests the condition to verify the existence of a winner in the Monopoly game simulation.
     * It sets up a scenario with two players and ensures that a winner exists after the game ends.
     * The game uses a specific seed to avoid non-ending conditions and verifies the presence of a winner.
     */

    @Test
    public void testWinnerExists() {
        ArrayList<Player> players = new ArrayList<>();
        final long SEED = 42; //Game is not non-ending with seed 42
        Player p1 = new Player(1500);
        Player p2 = new Player(1500);
        p1.setSeed(SEED);
        p2.setSeed(SEED);
        players.add(p1);
        players.add(p2);
        ArrayList<BoardSquare> boardMap = BoardInit.createBoard();

        for(BoardSquare square:boardMap){
            if(square instanceof Randomizable){
                ((Randomizable) square).setSeed(SEED);
            }
        }
        MonopolyGame game = new MonopolyGame(players, boardMap);
        game.setSeed(SEED);
        game.playGame(false);
        Player winner = game.getWinner();
        assertNotNull(winner, "winner should not be null");
    }
    /**
     * Tests the losing condition for a player in the Monopoly game simulation.
     * It creates a player with an initial amount of money, then simulates a scenario where the player's money becomes zero.
     * The test verifies whether the player has indeed lost the game based on having zero money.
     */
    @Test
    public void testPlayerHasLost() {
        MonopolyGame game = new MonopolyGame(new ArrayList<>(), new ArrayList<>());
        Player player = new Player(1500);

        player.subtractMoney(1500); //set player's money to zero

        boolean hasLost = player.hasLost();
        assertTrue(hasLost);
    }
    /**
     * Tests that a player does not lose the game when having positive money after a deduction.
     * The test verifies that the player has not lost the game based on having some money left.
     */
    @Test
    public void testPlayerHasNotLostWithPositiveMoney() {
        Player player = new Player(1000);
        player.subtractMoney(500); // player has some money left

        boolean hasLost = player.hasLost();

        assertFalse(hasLost);
    }
    /**
     * Tests that a player loses the game when having negative money after a deduction.
     * The test verifies that the player has lost the game based on having negative money.
     */
    @Test
    public void testPlayerHasLostWithNegativeMoney() {
        Player player = new Player(500);
        player.subtractMoney(1000); // player has negative money

        boolean hasLost = player.hasLost();

        assertTrue(hasLost);
    }
    /**
     * Tests the range of dice values obtained after rolling the dice in a Monopoly game.
     * The test ensures that the dice value falls within the range of 2 and 12, inclusive.
     */
    @Test
    public void testRollDiceInRange() {
        MonopolyGame game = new MonopolyGame(new ArrayList<>(), new ArrayList<>());
        int diceValue = game.rollDice();
        assertTrue(diceValue >= 2 && diceValue <= 12, "Dice value should be between 2 and 12");
    }
    /**
     * Tests the count of non-ending games when simulating games where there are no non-ending games.
     * The test checks that the number of non-ending games is 0.
     */
    @Test
    public void testGetNumOfInfWithNoNonEndingGames() {
        ArrayList<BoardSquare> board = new ArrayList<>();
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(500));

        MonopolyGame game = new MonopolyGame(players, board);
        game.playGame(false);

        assertEquals(0, game.getNumOfInf(), "There should be no non-ending games");
    }
    /**
     * Tests the decrement of players upon elimination during a game.
     * The test verifies that the number of players decreases correctly after a player gets eliminated.
     */
    @Test
    public void testPlayersDecrementedUponElimination() {
        ArrayList<BoardSquare> boardMap = BoardInit.createBoard();
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player(1500);
        Player player2 = new Player(1500);
        players.add(player1);
        players.add(player2);
        player1.subtractMoney(1500);
        MonopolyGame game = new MonopolyGame(players, boardMap);
        game.playGame(false);

        int expectedPlayersRemaining = 1; // Only one player should remain after elimination
        assertEquals(expectedPlayersRemaining, game.getNumPlayers(), "One player should remain after elimination");
    }
}