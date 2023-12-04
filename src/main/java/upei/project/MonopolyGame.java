package upei.project;

import upei.project.Setup.BoardInit;
import java.util.ArrayList;

/**
 * The MonopolyGame class manages the overall game logic for a Monopoly game simulation.
 * It controls player movements, dice rolling, game rounds, and determines the winner.
 */
public class MonopolyGame {
    private ArrayList<BoardSquare> boardMap;
    private ArrayList<Player> players;
    private int numOfInf = 0;
    public static int diceVal;

    public MonopolyGame(ArrayList<Player> players, ArrayList<BoardSquare> boardMap){
        this.boardMap = boardMap;
        this.players = players;
    }

    /** Nested Node class for managing player turns in a circular linked list **/
    private static class Node {
        Player player;
        Node next;
        Node prev;

        public Node(Player player) {
            this.player = player;
        }
    }

    /**
     * Simulates the Monopoly game with given players.
     *
     * @return The winning Player object or null if simulation encounters an issue.
     */
    public Player playGame() {
        // Game initialization
        //boardMap = BoardInit.createBoard();// creates the board game
        int numPlayers = this.players.size();// number of players
        int pos;// position of the player
        boolean hasMoved = false;// checks if the player moved to another position
        Node currPlayer;
        Node firstPlayer = new Node(this.players.get(0));
        currPlayer = firstPlayer;

        // Creating a circular linked list for managing player turns
        for (int i = 1; i < this.players.size(); i++) {
            currPlayer.next = new Node(this.players.get(i));
            currPlayer.next.prev = currPlayer;
            currPlayer = currPlayer.next;
        }
        currPlayer.next = firstPlayer;
        firstPlayer.prev = currPlayer;
        currPlayer = currPlayer.next;

        // Game simulation loop until 1 player is left
        while (numPlayers > 1) {
            diceVal = rollDice();
            System.out.println("DICE VALUE: " + MonopolyGame.getDiceVal());
            System.out.println("Player before moving: \n" + currPlayer.player + "\n" +currPlayer.player.getLandsOwned());
            currPlayer.player.moveN(diceVal);
            pos = currPlayer.player.getPos(); // Stores old position
            this.boardMap.get(currPlayer.player.getPos()).playerOnLocation(currPlayer.player); // takes the action that needs to be taken on this square
            hasMoved = pos != currPlayer.player.getPos();
            System.out.println("Player after moving: \n" + currPlayer.player + "\n" + currPlayer.player.getLandsOwned());

            if (hasMoved) {// checks if the player changed his position by a community chest or chance or jail
                this.boardMap.get(currPlayer.player.getPos()).playerOnLocation(currPlayer.player);// takes the action again that needs to be taken on this square
                System.out.println("Player has moved again!: \n" + currPlayer.player + "\n" +currPlayer.player.getLandsOwned());
            }
            // the player lost; unlink him
            if (currPlayer.player.hasLost()) {
                System.out.println("Player eliminated: \n" + currPlayer.player);
                currPlayer.prev.next = currPlayer.next;
                currPlayer.next.prev = currPlayer.prev;
                currPlayer = currPlayer.next;
                numPlayers--;
            }
            currPlayer = currPlayer.next;

            // it is believed that a 2 player monopoly game has a 12% chance to not end if the player uses a strategy similar to our Player.DEFAULT.
            // We will define a non-ending game to be a game such that a player has more than 100,000$
            if (currPlayer.player.getMoney() > 100000) {
                System.out.println("Game did not end !!");
                this.numOfInf += 1;
                return null;
            }
        } //end while
        System.out.println("Winner: " + currPlayer.player + "\n" + currPlayer.player.getLandsOwned());
        return currPlayer.player;
    }
    public Player playGame(boolean display) {
        if(display){
            return playGame();
        }
        else {
            // Game initialization
            //boardMap = BoardInit.createBoard();// creates the board game
            int numPlayers = this.players.size();// number of players
            int pos;// position of the player
            boolean hasMoved = false;// checks if the player moved to another position
            Node currPlayer;
            Node firstPlayer = new Node(this.players.get(0));
            currPlayer = firstPlayer;

            // Creating a circular linked list for managing player turns
            for (int i = 1; i < this.players.size(); i++) {
                currPlayer.next = new Node(this.players.get(i));
                currPlayer.next.prev = currPlayer;
                currPlayer = currPlayer.next;
            }
            currPlayer.next = firstPlayer;
            firstPlayer.prev = currPlayer;
            currPlayer = currPlayer.next;

            // Game simulation loop until 1 player is left
            while (numPlayers > 1) {
                diceVal = rollDice();
                //System.out.println("DICE VALUE: " + MonopolyGame.getDiceVal());
                //System.out.println("Player before moving: \n" + currPlayer.player + "\n" +currPlayer.player.getLandsOwned());
                currPlayer.player.moveN(diceVal);
                pos = currPlayer.player.getPos(); // Stores old position
                this.boardMap.get(currPlayer.player.getPos()).playerOnLocation(currPlayer.player); // takes the action that needs to be taken on this square
                hasMoved = pos != currPlayer.player.getPos();
                //System.out.println("Player after moving: \n" + currPlayer.player + "\n" + currPlayer.player.getLandsOwned());

                if (hasMoved) {// checks if the player changed his position by a community chest or chance or jail
                    this.boardMap.get(currPlayer.player.getPos()).playerOnLocation(currPlayer.player);// takes the action again that needs to be taken on this square
                    //System.out.println("Player has moved again!: \n" + currPlayer.player + "\n" +currPlayer.player.getLandsOwned());
                }
                // the player lost; unlink him
                if (currPlayer.player.hasLost()) {
                    //System.out.println("Player eliminated: \n" + currPlayer.player);
                    currPlayer.prev.next = currPlayer.next;
                    currPlayer.next.prev = currPlayer.prev;
                    currPlayer = currPlayer.next;
                    numPlayers--;
                }
                currPlayer = currPlayer.next;

                // it is believed that a 2 player monopoly game has a 12% chance to not end if the player uses a strategy similar to our Player.DEFAULT.
                // We will define a non-ending game to be a game such that a player has more than 100,000$
                // It tends to not end when all 5 strategies are playing
                if (currPlayer.player.getMoney() > 100000) {
                    System.out.println("Game did not end !!");
                    this.numOfInf += 1;
                    return null;
                }
            } //end while
            System.out.println("Winner: " + currPlayer.player.prettyPrint()  + "\n" + currPlayer.player.getLandsOwned());
            return currPlayer.player;
        }

    }


    /**
     * Simulates rolling of dice and returns the result.
     * @return The value obtained after rolling the dice.
     */
    public static int rollDice() {
        diceVal = DiceUtils.rollDice2();
        return diceVal;
    }

    /**
     * Retrieves the last rolled dice value.
     * @return The value obtained from the last dice roll.
     */
    public static int getDiceVal() {
        return diceVal;
    }

    /**
     * Sets the dice value (for testing purposes).
     * @param diceValue The value to set as the dice value.
     */
    public static void setDiceVal(int diceValue) {
        diceVal = diceValue;
    }

    public int getNumOfInf() {
        return this.numOfInf;
    }
}
