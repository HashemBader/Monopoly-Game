package upei.project;

import java.util.ArrayList;

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
        ArrayList<Player> players = getPlayers(new String[] {"Zeyad","Yasser"}, new Player.strategy[] {Player.strategy.GREEDY, Player.strategy.STATION_GUY});
        ArrayList<BoardSquare> map = Driver.createBoard();
        Node currPlayer;
        int numPlayers = players.size();
        int diceRolls = 0;
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
            diceRolls++;
            rounds = (int)(diceRolls / numPlayers);

            System.out.println("Round: " + rounds);
            currPlayer.player.rollDice();
            System.out.println("DICEVAL: " + Player.diceVal);
            currPlayer.player.moveN(Player.diceVal);
            System.out.println("PLAYER before: " + currPlayer.player);
            map.get(currPlayer.player.getPos()).playerOnLocation(currPlayer.player);
            System.out.println("PLAYER after: " + currPlayer.player);

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

    private static ArrayList<Player> getPlayers(String[] names, Player.strategy[] strategies) {
        ArrayList<Player> players = new ArrayList<>();
        for(int i=0; i<names.length; i++){
            players.add(new Player(1500, strategies[i], names[i]));
        }
        return players;
    }
}
