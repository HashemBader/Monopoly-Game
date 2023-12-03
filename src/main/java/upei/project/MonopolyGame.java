package upei.project;

import upei.project.Properties.Property;
import upei.project.Setup.BoardInit;
import java.util.ArrayList;

/**
 * The MonopolyGame class manages the overall game logic for a Monopoly game simulation.
 * It controls player movements, dice rolling, game rounds, and determines the winner.
 */
public class MonopolyGame {
    public static ArrayList<BoardSquare> boardMap;
    public static int diceVal;

    // Nested Node class for managing player turns in a circular linked list
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
     * @param players The ArrayList containing Player objects participating in the game.
     * @return The winning Player object or null if simulation encounters an issue.
     */
    public static Player playGame(ArrayList<Player> players) {
        // Game initialization
        boardMap = BoardInit.createBoard();// creates the board game
        int numPlayers = players.size();// number of players
        int pos;// position of the player
        boolean hasMoved = false;// checks if the player moved to another position
        Node currPlayer;
        Node firstPlayer = new Node(players.get(0));
        currPlayer = firstPlayer;

        // Creating a circular linked list for managing player turns
        for (int i = 1; i < players.size(); i++) {
            currPlayer.next = new Node(players.get(i));
            currPlayer.next.prev = currPlayer;
            currPlayer = currPlayer.next;
        }
        currPlayer.next = firstPlayer;
        firstPlayer.prev = currPlayer;
        currPlayer = currPlayer.next;

        // Game simulation loop until 1 player is left
        while (numPlayers > 1) {
            diceVal = rollDice();
            currPlayer.player.moveN(diceVal);
            pos = currPlayer.player.getPos();
            boardMap.get(currPlayer.player.getPos()).playerOnLocation(currPlayer.player);// takes the action that needs to be taken in this square
            hasMoved = pos != currPlayer.player.getPos();

            if (hasMoved) {// checks if the player changed his position by a community chest or chance or jail
                boardMap.get(currPlayer.player.getPos()).playerOnLocation(currPlayer.player);// takes the action again that needs to be taken in this square
            }
            // the player lost
            if (currPlayer.player.getMoney() == 0) {
                currPlayer.prev.next = currPlayer.next;
                currPlayer.next.prev = currPlayer.prev;
                currPlayer = currPlayer.next;
                numPlayers--;
            }
            currPlayer = currPlayer.next;
            // it is believed that a 2 player monopoly game has a 12% chance to not end
            if (currPlayer.player.getMoney() > 100000) {
                SimulationExperiment.numOfInf += 1;
                return null;
            }
        }
        return currPlayer.player;
    }

    /**
     * Simulates rolling of a dice and returns the result.
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
}
