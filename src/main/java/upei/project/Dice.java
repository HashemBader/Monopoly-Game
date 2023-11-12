package upei.project;

public class Dice {
    private static int rollDice1(){
        return (int) (Math.random() * 6) + 1;
    }

    private static int rollDice2(){
        return rollDice1() + rollDice1();
    }
}
