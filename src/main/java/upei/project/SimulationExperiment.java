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
        ArrayList<Player> players = getPlayers(new String[] {"zeyad","yasser","hashem"},new Player.strategy[] {Player.strategy.GREEDY, Player.strategy.STINGY, Player.strategy.STATION_GUY});
        ArrayList<BoardSquare> map = Driver.createBoard();
        int diceVal = 0;
        Node currPlayer;
        int numPlayers = players.size();
        int rounds = 0;
        int rounds_real = 0;

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
            rounds_real = (int)(rounds / numPlayers);

            System.out.println("Round: " + rounds_real);
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

    private static ArrayList<Player> getPlayers(String[] names, Player.strategy[] strategies) {
        ArrayList<Player> players = new ArrayList<>();
        for(int i=0; i<names.length; i++){
            players.add(new Player(1500, strategies[i], names[i]));
        }
        return players;
    }
}
