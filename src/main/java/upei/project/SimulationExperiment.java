package upei.project;

import java.util.ArrayList;
import java.util.LinkedList;

public class SimulationExperiment {

    public static class Node{
        Player player;
        Node next;
        Node prev;
        public Node(Player player){
            this.player = player;
        }
    }

    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();
        Player p1 = new Player(1500, Player.STRATEGY.STINGY, "Zeyad");
        Player p2 = new Player(1500, Player.STRATEGY.DEFAULT ,"Hashem");
        Player p3 = new Player(1500, Player.STRATEGY.GREEDY, "Nadz");
        Player p5 = new Player(1500, Player.STRATEGY.UTILITY_GUY, "RJ");
        Player p4 = new Player(1500, Player.STRATEGY.STATION_GUY, "Yasser");
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        players.add(p5);
        ArrayList<BoardSquare> map = Driver.createBoard();
        int diceVal = 0;
        Node currPlayer;
        int numPlayers = players.size();
        int rounds = 0;

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
            rounds++;
            System.out.println("Round: " + rounds);
            diceVal = Dice.rollDice2();
            System.out.println("DICEVAL: " + diceVal);
            currPlayer.player.moveN(diceVal);
            System.out.println("PLAYER before: " + currPlayer.player);
            map.get(currPlayer.player.getPos()).playerOnLocation(currPlayer.player);
            System.out.println("PLAYER after: " + currPlayer.player);


            currPlayer = currPlayer.next;

            if (currPlayer.player.getMoney() == 0){
                currPlayer.prev.next = currPlayer.next;
                currPlayer.next.prev = currPlayer.prev;
                currPlayer = currPlayer.next;
                numPlayers--;
            }

        }
        System.out.print(currPlayer.player);

    }
}
