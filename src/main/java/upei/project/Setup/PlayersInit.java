package upei.project.Setup;

import upei.project.Player;

import java.util.ArrayList;

public class PlayersInit {
    public static ArrayList<Player> getPlayers(String[] names, Player.strategy[] strategies) {
        ArrayList<Player> players = new ArrayList<>();
        for(int i=0; i<names.length; i++){
            players.add(new Player(1500, strategies[i], names[i]));
        }
        return players;
    }
}
