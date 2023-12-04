package upei.project.SetupUtils;

import upei.project.Player;

import java.util.ArrayList;
/**
 * Utility class responsible for initializing players for the game.
 */
public final class PlayersInit {
    private PlayersInit(){} //To prevent instantiating. Intentionally left empty
    /**
     * Generates an ArrayList of Player objects based on the provided names and strategies.
     *
     * @param names      An array of strings containing the names of the players.
     * @param strategies An array of Player.strategy enum values indicating the strategies for each player.
     * @return An ArrayList containing Player objects initialized with the given names and strategies.
     */
    public static ArrayList<Player> getPlayers(String[] names, Player.strategy[] strategies) {
        ArrayList<Player> players = new ArrayList<>();
        for(int i=0; i<names.length; i++){
            players.add(new Player(1500, strategies[i], names[i]));
        }
        return players;
    }
}
