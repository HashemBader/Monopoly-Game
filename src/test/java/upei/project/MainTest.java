package upei.project;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {



    @Test
    public void testGame(){
        ArrayList<BoardSquare> map = Driver.createBoard();
        Player zeyad = new Player(1500, Player.STRATEGY.GREEDY);
        Player hashem = new Player(1500, Player.STRATEGY.STATION_GUY);

        hashem.moveN(5);
        map.get(hashem.getPos()).playerOnLocation(hashem);
        assertEquals(1440, hashem.getMoney());

    }

    @Test
    public void testOwnerEquality(){
        Player p1 = new Player(1500,  Player.STRATEGY.GREEDY);
        Player p2 = new Player(1540, Player.STRATEGY.GREEDY);
        Player p3 = p1;
        System.out.print(p3);
        assertTrue(p1 == p3, "Failed");
    }
    @Test
    public void testOwner(){
        Country egypt = new Country(0, "Egypt", 1000, 190000);
        Player p1 = new Player(1500, Player.STRATEGY.GREEDY);
        egypt.setOwner(new Player(1000, Player.STRATEGY.GREEDY));
        egypt.playerOnLocation(p1);

        assertEquals(egypt.getOwner() == p1, p1.getMoney());
    }
}
