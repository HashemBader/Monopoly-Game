package upei.project;

import upei.project.Setup.BoardInit;

import java.util.ArrayList;

public class del {
    public static void main(String[] args) {
        long SEED = -101010; //Game is not non-ending with seed 42
        while(true) {
            ArrayList<Player> players = new ArrayList<>();
            Player p1 = new Player(1500);
            Player p2 = new Player(1500);
            p1.setSeed(SEED);
            p2.setSeed(SEED);
            players.add(p1);
            players.add(p2);
            ArrayList<BoardSquare> boardMap = BoardInit.createBoard();

            for (BoardSquare square : boardMap) {
                if (square instanceof Randomizable) {
                    ((Randomizable) square).setSeed(SEED);
                }
            }
            // Initialize the boardMap with appropriate squares
            MonopolyGame game = new MonopolyGame(players, boardMap);
            game.setSeed(SEED);
            game.playGame(false);
            Player winner = game.getWinner();
            if (winner != null) {// IMPORTANT TO GET SEED!!
                SEED++;
            }
            else {
                break;
            }
        }
        System.out.println(SEED);

    }
}
