package upei.project;
import upei.project.Setup.PlayersInit;
import java.util.ArrayList;
import java.util.HashMap;

public class SimulationExperiment {

    public static void main(String[] args) {
        HashMap<Integer, Player> dataset = new HashMap<>();
        ArrayList<Player> players;
        for(int i=0; i<1000; i++) {
            players = PlayersInit.getPlayers(new String[]{"Zeyad", "Hashem"},
                    new Player.strategy[]{Player.strategy.GREEDY, Player.strategy.STINGY});
            dataset.put(i, MonopolyGame.playGame(players));
        }

        /*for(int i=0; i< dataset.size(); i++){
            System.out.print(i + " " + dataset.get(i).getStratey().toString() + "\n");
        }*/
        System.out.print("Here is the HashMap: \n" + calcWinRatePerStrategy(dataset, "s"));
    }

    private static HashMap<String, Integer> calcWinRatePerStrategy(HashMap<Integer, Player> dataset, String strategy){
        HashMap<String, Integer> winsPerStrategy = new HashMap<>();
        for(int i=0; i< dataset.size(); i++){
            String tempStrategy = dataset.get(i).getStratey().toString();
            if(winsPerStrategy.containsKey(tempStrategy)){
                winsPerStrategy.put(tempStrategy, winsPerStrategy.get(tempStrategy)+1);
            }
            else{
                winsPerStrategy.put(tempStrategy, 1);
            }
        }
        return winsPerStrategy;
    }
}
