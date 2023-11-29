package upei.project;

public class Dice {
    public static int rollDice1(){
        return (int) (Math.random() * 6) + 1;
    }

    public static int rollDice2(){
        return rollDice1() + rollDice1();
    }
}
