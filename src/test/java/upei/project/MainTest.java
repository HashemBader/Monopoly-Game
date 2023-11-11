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

}
