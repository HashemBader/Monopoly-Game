package upei.project;

import upei.project.Setup.BoardInit;

import java.util.ArrayList;

public class Del {
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player(1500);
        Player p2 = new Player(1500);
        p1.setSeed(1);
        p2.setSeed(10);
        players.add(p1);
        players.add(p2);
        ArrayList<BoardSquare> boardMap = BoardInit.createBoard();

        for(BoardSquare square:boardMap){
            if(square instanceof Randomizable){
                ((Randomizable) square).setSeed(3);
            }
        }
        // Initialize the boardMap with appropriate squares

        MonopolyGame game = new MonopolyGame(players, boardMap);
        game.setSeed(1);
        game.playGame(false);
        Player winner = game.getWinner();
    }
}
