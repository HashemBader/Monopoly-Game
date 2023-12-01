package upei.project;
import org.junit.jupiter.api.Test;
import upei.project.Properties.Country;
import upei.project.Properties.Property;
import upei.project.Properties.Station;
import upei.project.Properties.Utility;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {

    @Test
    public void testCountryConstructor(){
        Station country = new Station(15, "Canada",200);
        assertEquals("BoardLocation{iLoc=1, name='Canada'}", country.toString());

    }

    @Test
    public void testOwnerEquality(){
        Player p1 = new Player(1500,  Player.strategy.GREEDY);
        Player p2 = new Player(1540, Player.strategy.GREEDY);
        Player p3 = p1;
        System.out.print(p3);
        assertTrue(p1 == p3, "Failed");
    }
    @Test
    public void testOwner(){
        Country egypt = new Country(0, "Egypt", 1000, 190000);
        Player p1 = new Player(1500, Player.strategy.GREEDY);
        egypt.setOwner(new Player(1000, Player.strategy.GREEDY));
        egypt.playerOnLocation(p1);

        assertEquals(egypt.getOwner() == p1, p1.getMoney());
    }

    @Test
    public void testGoToJail(){
        Player p1 = new Player(1500);
        p1.setPos(30);
        Driver.map.get(p1.getPos()).playerOnLocation(p1);
        assertEquals(10, p1.getPos());
    }
    @Test
    public void testGetOutOfJail(){
        Player p1 = new Player(1500);
        p1.setPos(30);
        Driver.map.get(p1.getPos()).playerOnLocation(p1);
        assertEquals(1500, p1.getMoney());
    }
    @Test
    public void testBuyStation(){
        Player p1 = new Player(1500, Player.strategy.STATION_GUY);
        p1.setPos(25);
        Driver.map.get(p1.getPos()).playerOnLocation(p1);
        assertEquals(1500, p1.getMoney());
    }

    @Test
    public void testBuyUtilities(){
        Player p1 = new Player(1500);
        Property s = (Property) Driver.map.get(5);
        Property u = (Utility) Driver.map.get(12);
        Property c = (Property) Driver.map.get(39);

        u.setOwner(p1);
        s.setOwner(p1);
        c.setOwner(p1);
        assertEquals(1500, p1.getLandsOwnedOfType(Station.class));
    }
    @Test
    public void testRentOfStations(){
        Player p1 = new Player(1500);
        Player p2 = new Player(1500);
        Property s = (Station) Driver.map.get(5);
        Property s2 = (Station) Driver.map.get(15);
        s.setOwner(p1);
        s2.setOwner(p1);
        s2.setOwner(p1);
        Driver.map.get(5).playerOnLocation(p2);
        assertEquals(1500, p2);
    }

    @Test
    public void testUtilitiesRent(){
        Player p1 = new Player(1500);
        Player p2 = new Player(1500);
        ((Property) Driver.map.get(12)).setOwner(p1);
        ((Property) Driver.map.get(28)).setOwner(p1);
        p2.rollDice();
        Driver.map.get(12).playerOnLocation(p2);
        assertEquals(1500, ""+p2+" "+Player.diceVal);
    }
}
