package upei.project;
/**
 * The Dice class provides methods to simulate rolling dice.
 */
public class DiceUtils {
    /**
     * Simulates rolling a single six-sided die.
     * @return An integer representing the result of the roll (a number between 1 and 6 inclusive).
     */
    public static int rollDice1(){
        return (int) (Math.random() * 6) + 1;
    }
    /**
     * Simulates rolling two six-sided dice and calculating their sum.
     * @return An integer representing the sum of two dice rolls (a number between 2 and 12 inclusive).
     */
    public static int rollDice2(){// Rolling two dice by calling rollDice1() method twice and adding their results
        return rollDice1() + rollDice1();
    }
}
