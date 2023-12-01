package upei.project;

import upei.project.Setup.BoardInit;
import upei.project.Setup.PlayersInit;

import java.util.ArrayList;

public class SimulationExperiment {

    private static class Node{
        Player player;
        Node next;
        Node prev;
        public Node(Player player){
            this.player = player;
        }
    }

    public static void main(String[] args) {
        ArrayList<BoardSquare> boardMap = BoardInit.createBoard();
        ArrayList<Player> players = PlayersInit.getPlayers(new String[] {"Zeyad","Hashem","Professor"},
                new Player.strategy[] {Player.strategy.GREEDY, Player.strategy.STINGY, Player.strategy.STATION_GUY});
        int numPlayers = players.size();
        int diceRolls = 0;
        int rounds;

        Node currPlayer;
        Node firstPlayer = new Node(players.get(0));
        currPlayer = firstPlayer;
        for (int i=1; i< players.size(); i++){
            currPlayer.next = new Node(players.get(i));
            currPlayer.next.prev = currPlayer;
            currPlayer = currPlayer.next;
        }
        currPlayer.next = firstPlayer;
        firstPlayer.prev = currPlayer;
        currPlayer = currPlayer.next;

        while(numPlayers > 1){
            diceRolls++;
            rounds = diceRolls / numPlayers;

            System.out.println("Round: " + rounds);
            currPlayer.player.rollDice();
            System.out.println("DICEVAL: " + Player.diceVal);
            currPlayer.player.moveN(Player.diceVal);
            System.out.println("PLAYER before: " + currPlayer.player);
            boardMap.get(currPlayer.player.getPos()).playerOnLocation(currPlayer.player);
            System.out.println("PLAYER after: " + currPlayer.player + "\n" +currPlayer.player.getLandsOwned());

            if (currPlayer.player.getMoney() == 0){
                currPlayer.prev.next = currPlayer.next;
                currPlayer.next.prev = currPlayer.prev;
                currPlayer = currPlayer.next;
                numPlayers--;
            }
            currPlayer = currPlayer.next;
        }
        System.out.print(currPlayer.player);

    }

}
