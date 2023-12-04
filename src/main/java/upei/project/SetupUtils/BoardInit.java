package upei.project.SetupUtils;

import upei.project.*;
import upei.project.Properties.Country;
import upei.project.Properties.Station;
import upei.project.Properties.Utility;

import java.util.ArrayList;
/**
 * BoardInit class responsible for initializing the game board with different types of BoardSquare objects.
 * Used to create an ArrayList representing the game board layout.
 */
public final class BoardInit {
    /**
     * Creates and initializes the game board with various BoardSquare objects.
     * Each BoardSquare represents a specific property, utility, station, tax, or miscellaneous square on the board.
     *
     * @return ArrayList<BoardSquare> representing the initialized game board.
     */

    private BoardInit(){} //to prevent instantiating
    public static ArrayList<BoardSquare> createBoard(){
        ArrayList<BoardSquare> boardMap = new ArrayList<>();
        // Adding different types of BoardSquare objects to the board
        boardMap.add(new MiscSquare(0, "GO"));
        boardMap.add(new Country(1, "SUDAN", 2, 60, "brown"));
        boardMap.add(new WildSquare(2, "CHEST"));
        boardMap.add(new Country(3, "LEBANON", 4, 60, "brown"));
        boardMap.add(new Tax(4, "INCOME TAX"));
        boardMap.add(new Station(5, "GIZA STATION", 200));
        boardMap.add(new Country(6, "SYRIA", 6, 100, "lightBlue"));
        boardMap.add(new WildSquare(7, "CHANCE"));
        boardMap.add(new Country(8, "MOROCCO", 6, 100, "lightBlue"));
        boardMap.add(new Country(9, "ALGERIA", 8, 120, "lightBlue"));
        boardMap.add(new MiscSquare(10, "JUST VISITING"));
        boardMap.add(new Country(11, "JORDAN", 10, 140, "pink"));
        boardMap.add(new Utility(12, "ELECTRIC COMPANY", 150));
        boardMap.add(new Country(13, "IRAQ", 10, 140, "pink"));
        boardMap.add(new Country(14, "OMAN", 10, 140, "pink"));
        boardMap.add(new Station(15, "PORTSAID STATION", 200));
        boardMap.add(new Country(16, "YEMEN", 12, 160, "orange"));
        boardMap.add(new WildSquare(17, "CHEST"));
        boardMap.add(new Country(18, "KUWAIT", 14, 180, "orange"));
        boardMap.add(new Country(19, "TUNISIA", 14, 180, "orange"));
        boardMap.add(new MiscSquare(20, "FREE PARKING"));
        boardMap.add(new Country(21, "LIBYA", 16, 200, "red"));
        boardMap.add(new WildSquare(22, "CHANCE"));
        boardMap.add(new Country(23, "BAHRAIN", 18, 220, "red"));
        boardMap.add(new Country(24, "DJIBOUTI", 18, 220, "red"));
        boardMap.add(new Station(25, "T3 STATION", 200));
        boardMap.add(new Country(26, "MAURITANIA", 20, 240, "yellow"));
        boardMap.add(new Country(27, "COMOROS", 22, 260, "yellow"));
        boardMap.add(new Utility(28, "WATER COMPANY", 150));
        boardMap.add(new Country(29, "EMIRATES", 22, 260, "yellow"));
        boardMap.add(new Jail(30, "GO JAIL"));
        boardMap.add(new Country(31, "SAUDI ARABIA", 26, 300, "green"));
        boardMap.add(new Country(32, "TURKEY", 26, 300, "green"));
        boardMap.add(new WildSquare(33, "CHEST"));
        boardMap.add(new Country(34, "PALESTINE", 28, 220, "green"));
        boardMap.add(new Station(35, "QNL STATION", 200));
        boardMap.add(new WildSquare(36, "CHANCE"));
        boardMap.add(new Country(37, "QATAR", 35, 350, "darkBlue"));
        boardMap.add(new Tax(38, "LUXURY TAX"));
        boardMap.add(new Country(39, "EGYPT", 50, 400, "darkBlue"));

        return boardMap;
    }
}
