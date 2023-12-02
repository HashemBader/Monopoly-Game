package upei.project;

import upei.project.Properties.Property;
import upei.project.Setup.BoardInit;
import java.util.ArrayList;

public class MonopolyGame {
    public static ArrayList<BoardSquare> boardMap;
    public static int diceVal;
    private static class Node{
        Player player;
        Node next;
        Node prev;
        public Node(Player player){
            this.player = player;
        }
    }
    
    public static Player playGame(ArrayList<Player> players){
        boardMap = BoardInit.createBoard();
        int numPlayers = players.size();
        int diceRolls = 0;
        int rounds;
        int pos;
        boolean hasMoved = false;
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
            if(currPlayer.player.getMoney() > 10000)
                break;
            diceRolls++;
            rounds = diceRolls / numPlayers;
            System.out.println("Round: " + rounds);
            diceVal = rollDice();
            System.out.println("DICEVAL: " + MonopolyGame.getDiceVal());
            System.out.println("PLAYER before: " + currPlayer.player);
            currPlayer.player.moveN(diceVal);
            pos = currPlayer.player.getPos();
            boardMap.get(currPlayer.player.getPos()).playerOnLocation(currPlayer.player);
            hasMoved = pos != currPlayer.player.getPos();
            if(hasMoved){ // position has changed, e.g., move back 3 steps.
                boardMap.get(currPlayer.player.getPos()).playerOnLocation(currPlayer.player);
            }
            System.out.println("PLAYER after: " + currPlayer.player + "\n" +currPlayer.player.getLandsOwned().size());

            if (currPlayer.player.getMoney() == 0){
                currPlayer.prev.next = currPlayer.next;
                currPlayer.next.prev = currPlayer.prev;
                currPlayer = currPlayer.next;
                numPlayers--;
            }
            currPlayer = currPlayer.next;
            //System.out.println("PLAYER after: " + currPlayer.player + "\n" + currPlayer.player.getLandsOwned());
        }
        System.out.println("Winner: " + currPlayer.player + "\n" + currPlayer.player.getLandsOwned());

        return currPlayer.player;
    }
    public static int rollDice(){
        diceVal = DiceUtils.rollDice2();
        return diceVal;
    }
    public static int getDiceVal(){
        return diceVal;
    }
    public static void setDiceVal(int diceValue){//for testing
        diceVal = diceValue;
    }
}