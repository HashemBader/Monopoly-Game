package upei.project;

import upei.project.Setup.BoardInit;
import upei.project.Setup.PlayersInit;

import java.util.ArrayList;
import java.util.HashMap;

public class SimulationExperiment {

    public static void main(String[] args) {
        HashMap<Integer, Player> dataset = new HashMap<>();
        ArrayList<Player> players;
        for(int i=0; i<1000; i++) {
            players = PlayersInit.getPlayers(new String[]{"Zeyad", "Hashem", "Nadz"},
                    new Player.strategy[]{Player.strategy.GREEDY, Player.strategy.STINGY, Player.strategy.DEFAULT});
            dataset.put(i, MonopolyGame.playGame(players));
        }

        for(int i=0; i< dataset.size(); i++){
            System.out.print(i + " " + dataset.get(i).getStratey().toString() + "\n");
        }
    }

   /* public double calcWinRatePerStrategy(String strategy){
/   / todo
    }*/
}
