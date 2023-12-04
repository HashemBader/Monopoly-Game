package upei.project;

import java.util.ArrayList;
import java.util.Random;

/**
 * The MonopolyGame class manages the overall game logic for a Monopoly game simulation.
 * It controls player movements, dice rolling, game rounds, and determines the winner.
 * Implements Randomizable
 */
public class MonopolyGame implements Randomizable{
    private ArrayList<BoardSquare> boardMap;
    private ArrayList<Player> players;
    private int numOfInf = 0;
    private Player winner;
    public static int diceVal;
    private Random random = new Random(); // Random object for making decisions(for testing)

    /**
     * Constructor for MonopolyGame class.
     * Initializes the game with players and the game board.
     *
     * @param players  The list of players participating in the game.
     * @param boardMap The game board represented as an ArrayList of BoardSquare objects.
     */
    public MonopolyGame(ArrayList<Player> players, ArrayList<BoardSquare> boardMap){
        this.boardMap = boardMap;
        this.players = players;
    }

    /**
     * Nested Node class for managing player turns in a circular linked list.
     * Each node represents a player in the game.
     */
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
    public void playGame() {
        // Game initialization
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
                this.winner=null;
                break;
            }
        } //end while
        System.out.println("Winner: " + currPlayer.player + "\n" + currPlayer.player.getLandsOwned());
        this.winner = currPlayer.player;
    }
    /**
     * Simulates the Monopoly game with given players, providing an option to display details.
     *
     * @param displayRounds A boolean indicating whether to display simulation details.
     * @return The winning Player object or null if simulation encounters an issue.
     */
    public void playGame(boolean displayRounds) {
        if(displayRounds){
            playGame();
        }
        else {
            // Game initialization
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
                currPlayer.player.moveN(diceVal);
                pos = currPlayer.player.getPos(); // Stores old position
                this.boardMap.get(currPlayer.player.getPos()).playerOnLocation(currPlayer.player); // takes the action that needs to be taken on this square
                hasMoved = pos != currPlayer.player.getPos();

                if (hasMoved) {// checks if the player changed his position by a community chest or chance or jail
                    this.boardMap.get(currPlayer.player.getPos()).playerOnLocation(currPlayer.player);// takes the action again that needs to be taken on this square
                }
                // the player lost; unlink him
                if (currPlayer.player.hasLost()) {
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
                    this.winner = null;
                    break;
                }
            } //end while
            System.out.println("Winner: " + currPlayer.player.prettyPrint()  + "\n" + currPlayer.player.getLandsOwned());
            this.winner = currPlayer.player;
        }

    }


    /**
     * Simulates rolling of dice and returns the result.
     * @return The value obtained after rolling the dice.
     */
    /*public static int rollDice() {
        diceVal = DiceUtils.rollDice2();
        return diceVal;
    }*/
public int rollDice(){
    return (int)(random.nextFloat()*6)+1 +(int)(random.nextFloat()*6)+1;
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
    /**
     * Sets the seed value for the random number generator (for testing purposes).
     * @param seed The seed value to set for the random number generator.
     */
    public void setSeed(long seed) {
        this.random.setSeed(seed);
        for (Player p : this.players) {
            p.setSeed(seed);
        }
        for (BoardSquare square : this.boardMap) {
            if (square instanceof Randomizable) {
                ((Randomizable) square).setSeed(seed);
            }
        }
    }
    /**
     * Gets the number of non-ending games encountered during simulations.
     *
     * @return The count of non-ending games.
     */
    public int getNumOfInf() {
        return this.numOfInf;
    }
    /**
     * Retrieves the winning Player of the Monopoly game.
     *
     * @return The Player object representing the winner of the game.
     */
    public Player getWinner() {
        return winner;
    }
    public int getNumPlayers(){return this.players.size();}
}
