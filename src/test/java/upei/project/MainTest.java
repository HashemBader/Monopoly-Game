package upei.project;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {


    @Test
    public void testMainConstructor(){
        Main myMain = new Main();


        assertEquals(myMain.getExampleVar(), 1, "Test Main Constructor: Expected: 1, Received: "+myMain.getExampleVar());

    }

    @Test
    public void testMainExampleVarSetter(){
        Main myMain = new Main();

        myMain.setExampleVar(100);
        assertTrue(myMain.getExampleVar()==100, "Test Setter: Expected: 100, Received: "+myMain.getExampleVar());


    }

    @Test
    public void testBoardLocationConstructor(){
        BoardLocation egypt = new Countries(1,"Egypt",new int[]{500}, 1000);
        BoardLocation hansenElectric = new Utility(2,"Hansen",new int[]{200}, 300);

        assertEquals(egypt.toString(), "BoardLocation{" +
                "iLoc=" + 1 +
                ", name='" + "Egypt" + '\'' +
                '}');
        assertEquals(hansenElectric.toString(), "BoardLocation{" +
                "iLoc=" + 2 +
                ", name='" + "Hansen" + '\'' +
                '}');
        hansenElectric.g
    }

}
