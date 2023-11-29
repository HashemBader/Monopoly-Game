package upei.project;

import java.util.ArrayList;

public class Driver {
    public static ArrayList<BoardSquare> map = createBoard();
    public static ArrayList<BoardSquare> createBoard(){
        ArrayList<BoardSquare> boardMap = new ArrayList<>();
        boardMap.add(new Go(0, "GO"));
        boardMap.add(new Country(1, "SUDAN", 2, 60));
        boardMap.add(new WildSquare(2, "CHEST"));
        boardMap.add(new Country(3, "LEBANON", 4, 60));
        boardMap.add(new Taxe(4, "INCOME TAX"));
        boardMap.add(new Station(5, "GIZA STATION"));
        boardMap.add(new Country(6, "SYRIA", 6, 100));
        boardMap.add(new WildSquare(7, "CHANCE"));
        boardMap.add(new Country(8, "MOROCCO", 6, 100));
        boardMap.add(new Country(9, "ALGERIA", 8, 120));
        boardMap.add(new Country(10, "JUST VISITING", 0, 0));
        boardMap.add(new Country(11, "JORDAN", 10, 140));
        boardMap.add(new Utility(12, "ELECTRIC COMPANY", 150));
        boardMap.add(new Country(13, "IRAQ", 10, 140));
        boardMap.add(new Country(14, "OMAN", 10, 140));
        boardMap.add(new Station(15, "PORTSAID STATION"));
        boardMap.add(new Country(16, "YEMEN", 12, 160));
        boardMap.add(new WildSquare(17, "CHEST"));
        boardMap.add(new Country(18, "KUWAIT", 14, 180));
        boardMap.add(new Country(19, "TUNISIA", 14, 180));
        boardMap.add(new Country(20, "FREE PARKING", 0, 0));
        boardMap.add(new Country(21, "LIBYA", 16, 200));
        boardMap.add(new WildSquare(22, "CHANCE"));
        boardMap.add(new Country(23, "BAHRAIN", 18, 220));
        boardMap.add(new Country(24, "DJIBOUTI", 18, 220));
        boardMap.add(new Station(25, "T3 STATION"));
        boardMap.add(new Country(26, "MAURITANIA", 20, 240));
        boardMap.add(new Country(27, "COMOROS", 22, 260));
        boardMap.add(new Utility(28, "WATER COMPANY", 150));
        boardMap.add(new Country(29, "EMIRATES", 22, 260));
        boardMap.add(new Jail(30, "GO JAIL"));
        boardMap.add(new Country(31, "SAUDI ARABIA", 26, 300));
        boardMap.add(new Country(32, "TURKEY", 26, 300));
        boardMap.add(new WildSquare(33, "CHEST"));
        boardMap.add(new Country(34, "PALESTINE", 28, 220));
        boardMap.add(new Station(35, "QNL STATION"));
        boardMap.add(new WildSquare(36, "CHANCE"));
        boardMap.add(new Country(37, "QATAR", 35, 350));
        boardMap.add(new Taxe(38, "LUXURY TAX"));
        boardMap.add(new Country(39, "EGYPT", 50, 400));

        return boardMap;
    }
    public static void main(String[] args) {
        ArrayList<BoardSquare> boardMap = createBoard();
    }

    public static int diceVal = 0;
    public static int getDiceVal(){
        return diceVal;
    }
    public static void setDiceVal(int value){
        diceVal = value;
    }
}
