package upei.project;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {



    @Test
    public void testGame(){
        ArrayList<BoardSquare> map = Driver.createBoard();
        Player zeyad = new Player(1500);
        Player hashem = new Player(1500);

        zeyad.moveN(1);
        map.get(zeyad.getPos()).playerOnLocation(zeyad);
        assertEquals(1500-2, zeyad.getMoney());
        assertEquals(1500+2, hashem.getMoney());

    }

    @Test
    public void testOwnerEquality(){
        Player p1 = new Player(1500);
        Player p2 = new Player(1540);
        Player p3 = p1;
        System.out.print(p3);
        assertTrue(p1 == p3, "Failed");
    }
    @Test
    public void testOwner(){
        Country egypt = new Country(0, "Egypt", 1000, 190000);
        Player p1 = new Player(1500);
        egypt.setOwner(new Player(1000));
        egypt.playerOnLocation(p1);
        assertEquals(egypt.getOwner() == p1, p1.getMoney());
    }
}
